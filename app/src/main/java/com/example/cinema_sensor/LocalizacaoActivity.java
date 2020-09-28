package com.example.cinema_sensor;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;

import java.util.List;
import java.util.Locale;


public class LocalizacaoActivity extends AppCompatActivity implements OnSuccessListener<Location>, OnFailureListener, View.OnClickListener {

    TextView endereco;
    Button verificar;

    float[] results = new float[1];
    double latitude1, longitude1, latitude2, longitude2;
    public final static int CODIGO_LOCALIZACAO = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_localizacao);

        endereco = findViewById(R.id.txt_localizacao);
        verificar = findViewById(R.id.btn_verificar);

        verificar.setOnClickListener(this);
    }

    public void Mapa(View view){
        Intent intentMapa = new Intent();
        intentMapa.setAction(intentMapa.ACTION_VIEW);
        intentMapa.setData(Uri.parse("geo:-23.5580151,-46.6579820"));
        startActivity(intentMapa);
    }

    @Override
    public void onClick(View v) {
        if(ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, CODIGO_LOCALIZACAO);
            Toast toast = Toast.makeText(this, "A permissão não foi concedida", Toast.LENGTH_SHORT);
            toast.show();
        } else {
            final FusedLocationProviderClient fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(this);
            fusedLocationProviderClient.getLastLocation().addOnSuccessListener(this);
            fusedLocationProviderClient.getLastLocation().addOnFailureListener(this);
        }
    }
    @Override
    public void onFailure(@NonNull Exception e) {
        Log.e("Erro na localização", "errors", e);
    }

    @Override
    public void onSuccess(Location location) {
        if(location != null) {
            latitude1 = location.getLatitude();
            longitude1 = location.getLongitude();
            Geocoder geocoder = new Geocoder(LocalizacaoActivity.this, Locale.getDefault());
            try {
                List<Address> localizacoes = geocoder.getFromLocationName("Consolação - SP, 01307-001", 1);
                if(localizacoes.size() == 0){

                    return;
                }
                Address locais = localizacoes.get(0);
                latitude2 = locais.getLatitude();
                longitude2 = locais.getLongitude();
                Location.distanceBetween(latitude1, longitude1, latitude2, longitude2, results);
                float resultados = results[0] / 1000;

                endereco.setText(String.valueOf("Sua distância do cinema é de "+ resultados + " km"));
            } catch (Exception e) {
                Log.e("Exceção disparada", "errors", e);
            }
        }
    }
}
