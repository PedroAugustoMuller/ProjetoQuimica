package com.example.user.projetoquimica;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.example.user.banco.DesempenhoConteudoDB;
import com.example.user.classesDominio.Conteudo;
import com.example.user.classesDominio.DesempenhoConteudo;
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.data.BarEntry;

import java.util.ArrayList;

public class GraficoDesempenhoConteudo extends AppCompatActivity {
    BarChart barchartDesempenhoConteudo;
    DesempenhoConteudoDB desempenhoConteudoDB = new DesempenhoConteudoDB(GraficoDesempenhoConteudo.this);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_grafico_desempenho_conteudo);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        barchartDesempenhoConteudo = findViewById(R.id.barchartDesempenhoConteudo);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        ArrayList<BarEntry> entradaBarra = new ArrayList<>();

        ArrayList<DesempenhoConteudo> listaDesempenho = desempenhoConteudoDB.buscaDesempenhoConteudo();

    }

}
