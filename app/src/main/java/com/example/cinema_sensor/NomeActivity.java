package com.example.cinema_sensor;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class NomeActivity extends AppCompatActivity {

    public final static String NOME = "com.example.intent.MESSAGE";
    EditText txt_nome;
    Button btn_enviar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nome);
    }

    public void EnviarNome(View v){
        txt_nome = findViewById(R.id.txt_digitaNome);
        btn_enviar = findViewById(R.id.btn_enviar);

        Intent intent = new Intent(this, MainActivity.class);
        String nomeUsuario = txt_nome.getText().toString();
        intent.putExtra(NOME, nomeUsuario);
        if(nomeUsuario.length() > 2){
            startActivity(intent);
        }
    }
}
