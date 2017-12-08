package ufc.qx.q_detective.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import ufc.qx.q_detective.DatabaseHelper;
import ufc.qx.q_detective.dominio.Denuncia;

/**
 * Created by mel on 07/12/17.
 */

public class DenunciaDAO {

    private DatabaseHelper helper;
    private SQLiteDatabase db;
    private Cursor cursor;
    private SimpleDateFormat fmt = new SimpleDateFormat("dd/MM/yyyy HH:mm");

    public DenunciaDAO(Context context) {
        this.helper = new DatabaseHelper(context);
    }

    public List<Map<String, Object>> listarDenuncias() {

        db = helper.getReadableDatabase();

        cursor = db.query(DatabaseHelper.Denuncia.TABELA, DatabaseHelper.Denuncia.COLUNAS, null, null, null, null, null);

        List<Map<String, Object>> denuncias = new ArrayList<>();

        while (cursor.moveToNext()) {

            int id = cursor.getInt(cursor.getColumnIndex(DatabaseHelper.Denuncia._ID));
            String descricao = cursor.getString(cursor.getColumnIndex(DatabaseHelper.Denuncia.DESCRICAO));
            Date data = new Date(cursor.getLong(cursor.getColumnIndex(DatabaseHelper.Denuncia.DATA)));
            Double longitude  = cursor.getDouble(cursor.getColumnIndex(DatabaseHelper.Denuncia.LONGITUDE));
            Double latitude  = cursor.getDouble(cursor.getColumnIndex(DatabaseHelper.Denuncia.LATITUDE));
            String uriMidia = cursor.getString(cursor.getColumnIndex(DatabaseHelper.Denuncia.URIMIDIA));
            String usuario = cursor.getString(cursor.getColumnIndex(DatabaseHelper.Denuncia.USUARIO));
            String categoria = cursor.getString(cursor.getColumnIndex(DatabaseHelper.Denuncia.CATEGORIA));

            Map<String, Object> denuncia = new HashMap<>();

            denuncia.put(DatabaseHelper.Denuncia._ID, id);
            denuncia.put(DatabaseHelper.Denuncia.DESCRICAO, descricao);
            denuncia.put(DatabaseHelper.Denuncia.DATA, fmt.format(data));
            denuncia.put(DatabaseHelper.Denuncia.LONGITUDE, longitude);
            denuncia.put(DatabaseHelper.Denuncia.LATITUDE, latitude);
            denuncia.put(DatabaseHelper.Denuncia.URIMIDIA, uriMidia);
            denuncia.put(DatabaseHelper.Denuncia.USUARIO, usuario);
            denuncia.put(DatabaseHelper.Denuncia.CATEGORIA, categoria);
            denuncias.add(denuncia);
        }
        cursor.close();
        return denuncias;
    }

    public List<Denuncia> listar() {
        db = helper.getReadableDatabase();
        cursor = db.query(DatabaseHelper.Denuncia.TABELA, DatabaseHelper.Denuncia.COLUNAS,
                    null, null, null, null, null);

        List<Denuncia> denuncias = new ArrayList<>();

        while (cursor.moveToNext()) {
            denuncias.add(this.criarDenuncia(cursor));
        }
        cursor.close();
        return denuncias;
    }

    public Denuncia buscarDenunciaPorId(Integer id) {
        db = helper.getReadableDatabase();
        cursor = db.query(DatabaseHelper.Denuncia.TABELA, DatabaseHelper.Denuncia.COLUNAS, DatabaseHelper.Denuncia._ID + " = ?",
                new String[]{id.toString()}, null, null, null);

        if (cursor.moveToNext()) {
            Denuncia denuncia = criarDenuncia(cursor);
            cursor.close();
            return denuncia;
        }
        return null;
    }

    public long inserirDenuncia(Denuncia denuncia) {

        ContentValues values = new ContentValues();

        if (denuncia.getId() > 0) {
            values.put(DatabaseHelper.Denuncia._ID, denuncia.getId());
        }


        values.put(DatabaseHelper.Denuncia.DESCRICAO, denuncia.getDescricao());
        values.put(DatabaseHelper.Denuncia.DATA, denuncia.getData().getTime());
        values.put(DatabaseHelper.Denuncia.LONGITUDE, denuncia.getLongitude());
        values.put(DatabaseHelper.Denuncia.LATITUDE, denuncia.getLatitude());
        values.put(DatabaseHelper.Denuncia.URIMIDIA, denuncia.getUriMidia());
        values.put(DatabaseHelper.Denuncia.USUARIO, denuncia.getUsuario());
        values.put(DatabaseHelper.Denuncia.CATEGORIA, String.valueOf(denuncia.getCategoria()));


        db = helper.getWritableDatabase();
        long qtdInseridos = db.insert(DatabaseHelper.Denuncia.TABELA, null, values);

        return qtdInseridos;
    }

    public long atualizarDenuncia(Denuncia denuncia) {
        ContentValues values = new ContentValues();

        values.put(DatabaseHelper.Denuncia.DESCRICAO, denuncia.getDescricao());
        values.put(DatabaseHelper.Denuncia.DATA, denuncia.getData().getTime());
        values.put(DatabaseHelper.Denuncia.LONGITUDE, denuncia.getLongitude());
        values.put(DatabaseHelper.Denuncia.LATITUDE, denuncia.getLatitude());
        values.put(DatabaseHelper.Denuncia.URIMIDIA, denuncia.getUriMidia());
        values.put(DatabaseHelper.Denuncia.USUARIO, denuncia.getUsuario());
        values.put(DatabaseHelper.Denuncia.CATEGORIA, String.valueOf(denuncia.getCategoria()));

        db = helper.getWritableDatabase();
        long qtdAtualizados = db.update(DatabaseHelper.Denuncia.TABELA,
                                        values,DatabaseHelper.Denuncia._ID + " = ?",
                                        new String[]{denuncia.getId().toString()});

        return qtdAtualizados;
    }

    public boolean removerDenuncia(Integer id) {

        db = helper.getWritableDatabase();
        String where[] = new String[]{id.toString()};
        int removidos = db.delete(DatabaseHelper.Denuncia.TABELA, "_id = ?", where);
        return removidos > 0;
    }

    private Denuncia criarDenuncia(Cursor cursor) {

        int id = cursor.getInt(cursor.getColumnIndex(DatabaseHelper.Denuncia._ID));
        String descricao = cursor.getString(cursor.getColumnIndex(DatabaseHelper.Denuncia.DESCRICAO));
        Date data = new Date(cursor.getLong(cursor.getColumnIndex(DatabaseHelper.Denuncia.DATA)));
        Double longitude  = cursor.getDouble(cursor.getColumnIndex(DatabaseHelper.Denuncia.LONGITUDE));
        Double latitude  = cursor.getDouble(cursor.getColumnIndex(DatabaseHelper.Denuncia.LATITUDE));
        String uriMidia = cursor.getString(cursor.getColumnIndex(DatabaseHelper.Denuncia.URIMIDIA));
        String usuario = cursor.getString(cursor.getColumnIndex(DatabaseHelper.Denuncia.USUARIO));
        String categoria = cursor.getString(cursor.getColumnIndex(DatabaseHelper.Denuncia.CATEGORIA));

        Denuncia denuncia = new Denuncia(id, descricao, data, longitude, latitude, uriMidia, usuario, categoria);
        return denuncia;
    }

    public void close() {
        helper.close();
        db = null;
    }
}
