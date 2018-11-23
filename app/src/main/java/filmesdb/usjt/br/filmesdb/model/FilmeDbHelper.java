package filmesdb.usjt.br.filmesdb.model;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class FilmeDbHelper extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "filmes.db";
    public static final int DATABASE_VERSION = 2;
    public static String SQL_CREATE_FILME =
            "CREATE TABLE " + FilmeDbContract.Filme.TABLE_NAME + " (" +
            FilmeDbContract.Filme._ID + " INTEGER PRIMARY KEY, " +
            FilmeDbContract.Filme.ID + " INTEGER, " +
            FilmeDbContract.Filme.TITULO + " TEXT, " +
            FilmeDbContract.Filme.GENERO + " TEXT, " +
            FilmeDbContract.Filme.SINOPSE + " TEXT, " +
            FilmeDbContract.Filme.DIRETOR + " TEXT, " +
            FilmeDbContract.Filme.DATA_LANCAMENTO + " TEXT, " +
            FilmeDbContract.Filme.POPULARIDADE + " TEXT, " +
            FilmeDbContract.Filme.POSTER + " TEXT )";

    public static final String SQL_DROP_FILME = "DROP TABLE IF EXISTS " + FilmeDbContract.Filme.TABLE_NAME;

    public FilmeDbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }
    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(SQL_CREATE_FILME);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL(SQL_DROP_FILME);
        sqLiteDatabase.execSQL(SQL_CREATE_FILME);
    }
}
