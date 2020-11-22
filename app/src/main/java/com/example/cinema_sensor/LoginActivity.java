package com.example.cinema_sensor;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {

    public final static String NOME = "com.example.intent.MESSAGE";
    EditText txt_nome;
    EditText txt_senha;
    Button btn_enviar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
    }

    public void EnviarNome(View v){
        BDController crud = new BDController(getBaseContext());
        txt_nome = findViewById(R.id.txt_digitaNome);
        txt_senha = findViewById(R.id.txt_digitaSenha);
        btn_enviar = findViewById(R.id.btn_enviar);

        Intent intent = new Intent(this, MainActivity.class);
        String nomeUsuario = txt_nome.getText().toString();
        String senhaUsuario = txt_senha.getText().toString();
        String resultado;

        resultado = crud.insereDado(nomeUsuario, senhaUsuario);

        Toast.makeText(getApplicationContext(), resultado, Toast.LENGTH_LONG).show();

        intent.putExtra(NOME, nomeUsuario);
        if(nomeUsuario.length() > 2){
            startActivity(intent);
        }
    }
}
