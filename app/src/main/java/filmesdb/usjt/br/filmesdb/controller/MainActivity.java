package filmesdb.usjt.br.filmesdb.controller;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.io.IOException;
import java.util.ArrayList;

import filmesdb.usjt.br.filmesdb.R;
import filmesdb.usjt.br.filmesdb.model.Filme;
import filmesdb.usjt.br.filmesdb.model.FilmeDAO;
import filmesdb.usjt.br.filmesdb.model.FilmeDb;
import filmesdb.usjt.br.filmesdb.model.Genero;
import filmesdb.usjt.br.filmesdb.model.GeneroDAO;
import filmesdb.usjt.br.filmesdb.model.Util;

import static android.os.Build.HOST;
import static android.provider.ContactsContract.CommonDataKinds.Website.URL;

public class MainActivity extends Activity {
    public static final String GENERO = "filmesdb.usjt.br.filmesdb.controller.genero";
    public static final String FILMES = "filmesdb.usjt.br.filmesdb.controller.filmes";
    private String spinnerValue;
    public static final String HOSTFILME = "https://api.themoviedb.org/3/discover/movie";
    public static final String HOSTGENERO = "https://api.themoviedb.org/3/genre/movie/list?language=pt-BR";
    public static final String APIKEY = "?api_key=60b8c8663c5b8544a67b2192f513891f";
    public static final String URLFILME = "&language=pt-BR&include_adult=false&sort_by=popularity.desc";
    Intent intent;
    Context context;
    ArrayList<Genero> generos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        context = this;

        criaSpinner();
    }

    private void criaSpinner() {
        //Popula o spinner
        Spinner spinner = (Spinner) findViewById(R.id.generos_spinner);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.generos_array, android.R.layout.simple_spinner_item);

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        //Adiciona o spinner populado na tela
        spinner.setAdapter(adapter);

        //Evento de seleção do spinner
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                spinnerValue = adapterView.getItemAtPosition(i).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        spinnerValue = spinner.getSelectedItem().toString();
    }

    public void buscarFilmes(View view) {
        intent = new Intent(this, ListaFilmesActivity.class);

        if(Util.isConnected(this)) {
            if(spinnerValue != null) {
                int idGenero = 0;
                generos = GeneroDAO.getGenero();
                for(Genero genero: generos) {
                    if(genero.getNome().toUpperCase().contains(spinnerValue.toUpperCase())) {
                        idGenero = genero.getId();
                    }
                }
                new DownloadFilmes().execute(HOSTFILME + APIKEY + URLFILME + "&with_genres=" + idGenero);
            }
            else {
                new DownloadFilmes().execute(HOSTFILME + APIKEY + URLFILME);
            }
        } else {
            Toast toast = Toast.makeText(this, "Conexão com a internet não encontrada. Utilizando cache.", Toast.LENGTH_LONG);
            toast.show();
            //Função para buscar do cache
            new CarregaFilmes().execute("filmes");
        }
    }

    //Busca os filmes no endpoint da TheMovieDB, com base na categoria selecionada.
    private class DownloadFilmes extends AsyncTask<String, Void, ArrayList<Filme>> {

        @Override
        protected ArrayList<Filme> doInBackground(String... strings) {
            try {
                ArrayList<Filme> filmes = FilmeDAO.getFilmes(strings[0]);
                //Adicionar função de carregar cache(sqlite)
                FilmeDb db = new FilmeDb(context);
                db.insereFilmes(filmes);
                return filmes;
            } catch (IOException e) {
                e.printStackTrace();
            }
            return new ArrayList<Filme>();
        }

        protected void onPostExecute(ArrayList<Filme> filmes) {
            intent.putExtra(GENERO, spinnerValue);
            intent.putExtra(FILMES, filmes);
            startActivity(intent);
        }
    }

    //Função responsável por carregar a lista de filmes do SQlite,
    //caso não haja conexão com a internet
    private class CarregaFilmes extends AsyncTask<String, Void, ArrayList<Filme>> {

        @Override
        protected ArrayList<Filme> doInBackground(String... strings) {
            FilmeDb db = new FilmeDb(context);
            ArrayList<Filme> filmes = db.listarFilmes();
            return filmes;
        }

        protected void onPostExecute(ArrayList<Filme> filmes){
            intent.putExtra(FILMES, filmes);
            startActivity(intent);
        }
    }
}
