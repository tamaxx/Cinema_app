package com.example.cinema_sensor;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class ConsultaActivity extends AppCompatActivity {
    private ListView lista;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_consulta);

        BDController crud = new BDController(getBaseContext());
        Cursor cursor = crud.carregaDados();
        List<String> lista_user = new ArrayList<>();
        while(cursor.moveToNext()){
            lista_user.add("ID: " + cursor.getString(0 ) + " - Usuário: " + cursor.getString(1) + " / Senha: " + cursor.getString(2));
        }

        lista = findViewById(R.id.listView);
        ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, lista_user);
        lista.setAdapter(adapter);

        String text = TextUtils.join("\n", lista_user);
        FileOutputStream fos = null;

        try {
            fos = openFileOutput("usuarios.txt", MODE_PRIVATE);
            fos.write(text.getBytes());
            Toast.makeText(this, "Saved to " + getFilesDir() + "/" + "usuarios.txt", Toast.LENGTH_SHORT).show();
        } catch (FileNotFoundException e){
            e.printStackTrace();
        } catch (IOException e){
            e.printStackTrace();
        }

    }
}