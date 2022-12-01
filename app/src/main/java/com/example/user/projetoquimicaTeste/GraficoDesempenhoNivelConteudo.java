package com.example.user.projetoquimicaTeste;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import com.example.user.banco.NivelConteudoDB;
import com.example.user.classesDominio.Conteudo;
import com.example.user.projetoquimicaConsolidados.R;
import com.github.mikephil.charting.charts.LineChart;

public class GraficoDesempenhoNivelConteudo extends AppCompatActivity {
    Conteudo conteudo;
    NivelConteudoDB nivelConteudoDB = new NivelConteudoDB(GraficoDesempenhoNivelConteudo.this);
    LineChart linechartDesempenhoNivelConteudo;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_grafico_desempenho_nivel_conteudo);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        linechartDesempenhoNivelConteudo = findViewById(R.id.linechartDesempenhoNivelConteudo);
    }




}
