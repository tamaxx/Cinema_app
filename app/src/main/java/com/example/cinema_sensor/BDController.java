package com.example.cinema_sensor;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class BDController {

    private SQLiteDatabase db;
    private LoginHelper banco;

    public BDController(Context context){
        banco = new LoginHelper(context);
    }

    public String insereDado(String nome, String senha){
        ContentValues valores;
        long resultado;

        db = banco.getWritableDatabase();
        valores = new ContentValues();
        valores.put(LoginHelper.USER_COLUMN_NAME, nome);
        valores.put(LoginHelper.USER_COLUMN_SENHA, senha);

        resultado = db.insert(LoginHelper.USER_TABLE_NAME, null, valores);
        db.close();

        if(resultado == -1){
            return "Erro ao inserir registro";
        } else{
            return "Registro inserido com sucesso!";
        }
    }

    public Cursor carregaDados(){
        Cursor cursor;
        String[] campos =  {banco.USER_COLUMN_ID, banco.USER_COLUMN_NAME, banco.USER_COLUMN_SENHA};
        db = banco.getReadableDatabase();
        cursor = db.query(banco.USER_TABLE_NAME, campos, null, null, null, null, null, null);

        if(cursor!=null){
            cursor.moveToFirst();
        }
        db.close();
        return cursor;
    }

}
