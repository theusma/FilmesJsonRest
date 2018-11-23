package filmesdb.usjt.br.filmesdb.model;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

public class FilmeDb {
    FilmeDbHelper dbHelper;

    public FilmeDb(Context context) {
        dbHelper = new FilmeDbHelper(context);
    }

    public void insereFilmes(ArrayList<Filme> filmes) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        //Deleta tudo, para inserir novamente após...
        db.delete(FilmeDbContract.Filme.TABLE_NAME, null, null);

        ContentValues values = new ContentValues();
        for (Filme filme: filmes) {
            values.put(FilmeDbContract.Filme.ID, filme.getId());
            values.put(FilmeDbContract.Filme.TITULO, filme.getTitulo());
            values.put(FilmeDbContract.Filme.GENERO, filme.getGenero());
            values.put(FilmeDbContract.Filme.SINOPSE, filme.getSinopse());
            values.put(FilmeDbContract.Filme.DIRETOR, filme.getDiretor());
            values.put(FilmeDbContract.Filme.DATA_LANCAMENTO, filme.getDataLancamento());
            values.put(FilmeDbContract.Filme.POPULARIDADE, filme.getPopularidade());
            values.put(FilmeDbContract.Filme.POSTER, filme.getPoster());

            db.insert(FilmeDbContract.Filme.TABLE_NAME, null, values);
        }
    }

    public ArrayList<Filme> listarFilmes() {
        SQLiteDatabase db = dbHelper.getReadableDatabase();

        ArrayList<Filme> filmes = new ArrayList<>();

        String[] colunasFilmes = {
                FilmeDbContract.Filme.ID,
                FilmeDbContract.Filme.TITULO,
                FilmeDbContract.Filme.GENERO,
                FilmeDbContract.Filme.SINOPSE,
                FilmeDbContract.Filme.DIRETOR,
                FilmeDbContract.Filme.DATA_LANCAMENTO,
                FilmeDbContract.Filme.POPULARIDADE,
                FilmeDbContract.Filme.POSTER
        };

        String orderBy = FilmeDbContract.Filme.TITULO;

        Cursor c = db.query(FilmeDbContract.Filme.TABLE_NAME, colunasFilmes, null, null, null, null, orderBy);

        while (c.moveToNext()) {
            Filme filme = new Filme();
            filme.setId(c.getInt(c.getColumnIndex(FilmeDbContract.Filme.ID)));
            filme.setTitulo(c.getString(c.getColumnIndex(FilmeDbContract.Filme.TITULO)));
            filme.setGenero(c.getString(c.getColumnIndex(FilmeDbContract.Filme.GENERO)));
            filme.setSinopse(c.getString(c.getColumnIndex(FilmeDbContract.Filme.SINOPSE)));
            filme.setDiretor(c.getString(c.getColumnIndex(FilmeDbContract.Filme.DIRETOR)));
            filme.setDataLancamento(c.getString(c.getColumnIndex(FilmeDbContract.Filme.DATA_LANCAMENTO)));
            filme.setPopularidade(c.getInt(c.getColumnIndex(FilmeDbContract.Filme.POPULARIDADE)));
            filme.setPoster(c.getString(c.getColumnIndex(FilmeDbContract.Filme.TITULO)));

            filmes.add(filme);
        }

        c.close();
        return filmes;
    }
}
