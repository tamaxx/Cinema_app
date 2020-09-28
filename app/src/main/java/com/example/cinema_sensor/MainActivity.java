package com.example.cinema_sensor;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Service;
import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;


public class MainActivity extends AppCompatActivity implements SensorEventListener{

    TextView textView;
    SensorManager sensorManager;
    Sensor sensor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Bundle extras = getIntent().getExtras();
        TextView nome_digitado = findViewById(R.id.txt_nomeUsuario);

        if(extras != null){
            String msgnome = extras.getString(NomeActivity.NOME);
            nome_digitado.setText("Bem-vindo(a), " + msgnome);
        }

        textView = findViewById(R.id.txt_luz);

        sensorManager = (SensorManager) getSystemService(Service.SENSOR_SERVICE);
        sensor = sensorManager.getDefaultSensor(Sensor.TYPE_LIGHT);
    }

    @Override
    protected void onPause() {
        super.onPause();
        sensorManager.unregisterListener(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        sensorManager.registerListener(this, sensor, SensorManager.SENSOR_DELAY_NORMAL);
    }

    boolean escuro;

    @Override
    public void onSensorChanged(SensorEvent event) {
        float luminosidade = event.values[0];
        if(!escuro && luminosidade < 10){
            escuro = true;
            openDialog();

        }
        else{
        }
        textView.setText("Luminosidade do ambiente: " + luminosidade);
    }

    private void openDialog() {
        LuzDialog luzDialog = new LuzDialog();
        luzDialog.show(getSupportFragmentManager(), "example dialog");
    }


    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }

    public void RedirecionarFilmes(View v){
        Intent intentFilmes = new Intent(this, FilmesActivity.class);
        startActivity(intentFilmes);
    }

    public void RedirecionarLocalizacao(View v){
        Intent intentLocalizacao = new Intent(this, LocalizacaoActivity.class);
        startActivity(intentLocalizacao);
    }
    public void RedirecionarUrgente(View v){
        Intent intentUrgente = new Intent(this, UrgenteActivity.class);
        startActivity(intentUrgente);
    }
}
