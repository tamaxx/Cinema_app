package com.example.cinema_sensor;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.CompositePageTransformer;
import androidx.viewpager2.widget.MarginPageTransformer;
import androidx.viewpager2.widget.ViewPager2;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class FilmesActivity extends AppCompatActivity implements
        com.example.cinema_sensor.SliderAdapter.OnItemSelectedListener {

    private AlertDialog.Builder dialogBuilder;
    private AlertDialog dialog;

    private ViewPager2 viewPager2;
    private Handler sliderHandler = new Handler();
    private ImageView imagemFilme;
    private TextView tituloFilme;
    private TextView generosFilme;
    private TextView diretorFilme;
    private TextView sinopseFilme;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_filmes);

        viewPager2 = findViewById(R.id.viewPagerImageSlider);

        List<SliderItem> sliderItems = new ArrayList<>();
        sliderItems.add(new SliderItem(R.drawable.midsommar, "Midsommar", "Terror, Suspense", "Ari Aster", "Após vivenciar uma tragédia pessoal, Dani vai com o namorado Christian e um grupo de amigos até a Suécia para participar de um festival local de verão. Mas, ao invés das férias tranquilas com a qual todos sonhavam, o grupo se depara com rituais bizarros de uma adoração pagã."));
        sliderItems.add(new SliderItem(R.drawable.vida_invisivel, "A Vida Invisível", "Drama", "Karim Aïnouz", "Antigas cartas de sua irmã Guida, há muito desaparecida, surpreendem Eurídice, uma senhora de 80 anos. No Rio de Janeiro dos anos 1950, Guida e Eurídice são cruelmente separadas, impedidas de viverem os sonhos que alimentaram juntas ainda adolescentes. Veja a história destas duas mulheres, duas irmãs, tentando lutar contra as forças sociais que insistem em frustrá-las. Invisíveis em uma sociedade paternalista e conservadora, elas se desdobram para seguir em frente."));
        sliderItems.add(new SliderItem(R.drawable.o_farol, "O Farol", "Drama, Suspense", "Robert Eggers", "No final do século 19, um novo zelador chega a uma remota ilha na Nova Inglaterra para ajudar o faroleiro local, mas o isolamento causa tensão na convivência entre os dois homens. Entre tempestades e goles de querosene, o novato tenta desvendar os mistérios que existem nas histórias de pescador de seu chefe."));


        viewPager2.setAdapter(new com.example.cinema_sensor.SliderAdapter(this, sliderItems, viewPager2));

        viewPager2.setClipToPadding(false);
        viewPager2.setClipChildren(false);
        viewPager2.setOffscreenPageLimit(3);
        viewPager2.getChildAt(0).setOverScrollMode(RecyclerView.OVER_SCROLL_NEVER);

        CompositePageTransformer compositePageTransformer = new CompositePageTransformer();
        compositePageTransformer.addTransformer(new MarginPageTransformer(40));
        compositePageTransformer.addTransformer((new ViewPager2.PageTransformer() {
            @Override
            public void transformPage(@NonNull View page, float position) {
                float r = 1 - Math.abs(position);
                page.setScaleY(0.85f + r * 0.15f);
            }
        }));

        viewPager2.setPageTransformer(compositePageTransformer);

        viewPager2.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);
                sliderHandler.removeCallbacks(sliderRunnable);
                sliderHandler.postDelayed(sliderRunnable, 5000);
            }
        });

    }

    private Runnable sliderRunnable = new Runnable() {
        @Override
        public void run() {
            viewPager2.setCurrentItem(viewPager2.getCurrentItem()+1);
        }
    };

    @Override
    protected void onPause() {
        super.onPause();
        sliderHandler.removeCallbacks(sliderRunnable);
    }

    @Override
    protected void onResume() {
        super.onResume();
        sliderHandler.postDelayed(sliderRunnable, 3000);
    }

    @Override
    public void onItemSelected(SliderItem item) {
        dialogBuilder = new AlertDialog.Builder(this);
        final View DetalhesPopupView = getLayoutInflater().inflate(R.layout.popup, null);

        imagemFilme = DetalhesPopupView.findViewById(R.id.img_capaFilme);
        imagemFilme.setImageResource(item.getImage());

        tituloFilme = DetalhesPopupView.findViewById(R.id.txt_tituloFilme);
        tituloFilme.setText(item.getNome());

        generosFilme = DetalhesPopupView.findViewById(R.id.txt_generosFilme);
        generosFilme.setText(item.getGeneros());

        diretorFilme = DetalhesPopupView.findViewById(R.id.txt_diretorFilme);
        diretorFilme.setText(item.getDiretor());

        sinopseFilme = DetalhesPopupView.findViewById(R.id.txt_sinopseFilme);
        sinopseFilme.setText(item.getSinopse());

        dialogBuilder.setView(DetalhesPopupView);
        dialog = dialogBuilder.create();
        dialog.show();
    }
}
