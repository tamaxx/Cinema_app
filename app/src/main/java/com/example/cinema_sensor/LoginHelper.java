package com.example.cinema_sensor;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class LoginHelper extends SQLiteOpenHelper {

    public static  final String BANCO_DADOS = "BD_Login";
    public static final String USER_TABLE_NAME = "usuario";
    public static final String USER_COLUMN_ID = "_id";
    public static final String USER_COLUMN_NAME = "nome";
    public static  final String USER_COLUMN_SENHA = "senha";
    public static int VERSAO = 1;

    public LoginHelper(Context context) {
        super(context, BANCO_DADOS, null, VERSAO);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(
                "create table usuario " +
                        "(_id integer primary key autoincrement, nome text, senha text)"
        );
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public boolean insertUser (Usuario u){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(USER_COLUMN_NAME, u.getNome());
        contentValues.put(USER_COLUMN_SENHA, u.getSenha());
        db.insert(USER_TABLE_NAME, null, contentValues);
        return true;
    }
}
