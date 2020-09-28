package com.example.cinema_sensor;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;

public class UrgenteActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_urgente);
    }
    public void RedirecionarSite(View v){
        Uri site = Uri.parse("https://covid.saude.gov.br/");
        Intent intentSite = new Intent(Intent.ACTION_VIEW, site);
        startActivity(intentSite);
    }
}
