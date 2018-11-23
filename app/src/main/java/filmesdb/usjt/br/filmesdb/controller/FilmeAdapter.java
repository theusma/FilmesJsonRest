package filmesdb.usjt.br.filmesdb.controller;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import filmesdb.usjt.br.filmesdb.R;
import filmesdb.usjt.br.filmesdb.model.Filme;
import filmesdb.usjt.br.filmesdb.model.Util;

public class FilmeAdapter extends BaseAdapter {
    private ArrayList<Filme> filmes;
    private Context context;

    public FilmeAdapter(ArrayList<Filme> filmes, Context context) {
        this.filmes = filmes;
        this.context = context;
    }

    @Override
    public int getCount() {
        return filmes.size();
    }

    @Override
    public Object getItem(int position) {
        return filmes.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View linhaLista = convertView;

        if(linhaLista == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

            linhaLista = inflater.inflate(R.layout.linha_filme_adapter, parent, false);

            ImageView fotoGenero = (ImageView) linhaLista.findViewById(R.id.foto_genero_filme);

            TextView linhaTitulo = (TextView) linhaLista.findViewById(R.id.linha_titulo_filme);

            TextView linhaDiretor = (TextView) linhaLista.findViewById(R.id.linha_diretor_filme);

            ViewHolder holder = new ViewHolder(fotoGenero, linhaTitulo, linhaDiretor);

            linhaLista.setTag(holder);
        }


        //Filme atual
        Filme filme = filmes.get(position);
        ViewHolder holder = (ViewHolder) linhaLista.getTag();

        //Adiciona os textos de título do filme e nome do diretor na linha inflada pelo LayoutInflater
        holder.getLinha1().setText(filme.getTitulo() + " - ID " + filme.getId());
        holder.getLinha2().setText(filme.getDiretor());

        //Adiciona a imagem do gênero do filme na linha inflada pelo LayoutInflater
        Drawable drawable = Util.getDrawable(context, filme.getGenero());
        holder.getImagem().setImageDrawable(drawable);

        return linhaLista;
    }
}
