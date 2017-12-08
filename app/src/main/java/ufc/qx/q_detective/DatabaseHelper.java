package ufc.qx.q_detective;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by mel on 07/12/17.
 */

public class DatabaseHelper extends SQLiteOpenHelper {

    private static final String BANCO_DADOS = "q-detective";
    private static final int VERSAO = 1;

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE denuncia ( _id INTEGER PRIMARY KEY," +
                "decricao VARCHAR(150), "+
                "data DATE, " +
                "longitude DOUBLE, " +
                "latitude DOUBLE, " +
                "uriMidia TEXT, " +
                "usuario TEXT, " +
                "categoria TEXT);");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE denuncia;");
        onCreate(db);
    }

    public static class Denuncia {

        public static final String TABELA = "denuncia";

        public static final String _ID = "_id";
        public static final String DESCRICAO = "descricao";
        public static final String DATA = "data";
        public static final String LONGITUDE = "longitude";
        public static final String LATITUDE = "latitude";
        public static final String URIMIDIA = "uriMidia";
        public static final String USUARIO = "usuario";
        public static final String CATEGORIA = "categoria";

        public static final String[] COLUNAS =
                new String[]{_ID, DESCRICAO, DATA, LONGITUDE, LATITUDE, URIMIDIA, USUARIO, CATEGORIA };
    }

    public DatabaseHelper(Context context) {
        super(context, BANCO_DADOS, null, VERSAO);
    }
}