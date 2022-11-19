package com.example.user.projetoquimica;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.example.user.banco.ConteudoDB;
import com.example.user.banco.DesempenhoConteudoDB;
import com.example.user.classesDominio.Conteudo;
import com.example.user.classesDominio.DesempenhoConteudo;
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.data.BarEntry;

import java.util.ArrayList;

public class GraficoDesempenhoConteudo extends AppCompatActivity {
    LineChart barchartDesempenhoConteudo;
    DesempenhoConteudoDB desempenhoConteudoDB = new DesempenhoConteudoDB(GraficoDesempenhoConteudo.this);
    ConteudoDB conteudoDB = new ConteudoDB(GraficoDesempenhoConteudo.this);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_grafico_desempenho_conteudo);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        barchartDesempenhoConteudo = findViewById(R.id.linechartDesempenhoConteudo);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        ArrayList<LineChart> entradaLine = new ArrayList<>();
        ArrayList<Conteudo> listaConteudos = conteudoDB.buscaConteudos(1); //1 = orgânico, fixo por enquanto.
        ArrayList<DesempenhoConteudo> listaDesempenho = desempenhoConteudoDB.buscaDesempenhoConteudo();

        for(int x=0; x<listaDesempenho.size(); x++){
            DesempenhoConteudo desempenhoConteudo = listaDesempenho.get(x);
            for(int y=0; y<listaConteudos.size(); y++){
                if(listaConteudos.get(y).getIdConteudo() == desempenhoConteudo.getConteudo().getIdConteudo()){

                }
            }



        }
    }

}
