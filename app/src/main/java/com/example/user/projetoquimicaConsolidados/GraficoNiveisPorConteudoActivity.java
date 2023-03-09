package com.example.user.projetoquimicaConsolidados;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import com.example.user.banco.ConteudoDB;
import com.example.user.banco.InformacoesApp;
import com.example.user.banco.NivelConteudoDB;
import com.example.user.classesDominio.ClasseIntermediariaGraficos;
import com.example.user.classesDominio.NivelConteudo;
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter;


import java.util.ArrayList;

public class GraficoNiveisPorConteudoActivity extends AppCompatActivity {

    BarChart bcGraficoNiveisPorConteudo;
    ConteudoDB conteudoDB;
    NivelConteudoDB nivelConteudoDB;
    InformacoesApp informacoesApp;
    ClasseIntermediariaGraficos classeIntermediariaGraficos;
    ArrayList<NivelConteudo> listaNiveisConteudos = new ArrayList<>();
    String[] label;
    String[] yLabel = {"","Cobre","Bronze","Prata","Ouro","Diamante"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_grafico_niveis_por_conteudo);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        //Barchart
        bcGraficoNiveisPorConteudo = findViewById(R.id.bcGraficoNiveisPorConteudo);
        //ConteudoDB
        conteudoDB = new ConteudoDB(this);
        //NivelConteudoDB
        nivelConteudoDB = new NivelConteudoDB(this);
        //InformacoesApp
        informacoesApp = (InformacoesApp) getApplicationContext();
        //ClasseIntermediariaGraficos
        classeIntermediariaGraficos = new ClasseIntermediariaGraficos(this);


        ConfiguraGrafico();
    }

    public void ConfiguraGrafico(){

        listaNiveisConteudos = nivelConteudoDB.buscaConteudosComNivel(conteudoDB.buscaConteudos(informacoesApp.getTipoConteudo()), informacoesApp.getMeuUsuario());
        label = new String[listaNiveisConteudos.size()];
        for (int x = 0; x < listaNiveisConteudos.size(); x++){
            label[x] = listaNiveisConteudos.get(x).getConteudo().getNomeConteudo();
        }

        BarData data = classeIntermediariaGraficos.configuraGraficoNiveisPorConteudo(listaNiveisConteudos);
        bcGraficoNiveisPorConteudo.setData(data);
        bcGraficoNiveisPorConteudo.animateY(2000);
        bcGraficoNiveisPorConteudo.getDescription().setEnabled(false);
        bcGraficoNiveisPorConteudo.setScaleEnabled(false);
        bcGraficoNiveisPorConteudo.setHighlightPerDragEnabled(false);
        bcGraficoNiveisPorConteudo.getLegend().setEnabled(false);
        bcGraficoNiveisPorConteudo.setExtraBottomOffset(10f);
        bcGraficoNiveisPorConteudo.isDragEnabled();
        bcGraficoNiveisPorConteudo.setVisibleXRangeMaximum(5f);
        //YAxis
        XAxis xAxis = bcGraficoNiveisPorConteudo.getXAxis();
        YAxis left = bcGraficoNiveisPorConteudo.getAxisLeft();
        YAxis right = bcGraficoNiveisPorConteudo.getAxisRight();
        left.setGranularity(1f);
        left.setTextSize(15f);
        left.setAxisMinimum(0);
        left.setAxisMaximum(5);
        left.setValueFormatter(new IndexAxisValueFormatter(yLabel));

        right.setEnabled(false);

        xAxis.setGranularity(1f);
        xAxis.setTextSize(15f);
        xAxis.setDrawGridLines(false);
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        xAxis.setValueFormatter(new IndexAxisValueFormatter(label));
        
    }


}
