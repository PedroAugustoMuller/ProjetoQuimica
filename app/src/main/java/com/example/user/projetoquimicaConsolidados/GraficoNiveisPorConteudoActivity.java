package com.example.user.projetoquimicaConsolidados;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import com.example.user.banco.ConteudoDB;
import com.example.user.banco.InformacoesApp;
import com.example.user.banco.NivelConteudoDB;
import com.example.user.classesDominio.NivelConteudo;
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;

public class GraficoNiveisPorConteudoActivity extends AppCompatActivity {

    BarChart bcGraficoNiveisPorConteudo;
    ConteudoDB conteudoDB;
    NivelConteudoDB nivelConteudoDB;
    InformacoesApp informacoesApp;
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

        listaNiveisConteudos = nivelConteudoDB.buscaConteudosComNivel(conteudoDB.buscaConteudos(informacoesApp.getTipoConteudo()), informacoesApp.getMeuUsuario());
        label = new String[listaNiveisConteudos.size()];

        ConfiguraGrafico();
    }

    public void ConfiguraGrafico(){
        ArrayList<BarEntry> listaEntradas = new ArrayList<>();
        for (int x = 0; x < listaNiveisConteudos.size(); x++){
            NivelConteudo meuNivelConteudo = listaNiveisConteudos.get(x);
            label[x] = listaNiveisConteudos.get(x).getConteudo().getNomeConteudo();
            listaEntradas.add(new BarEntry(x , (float)meuNivelConteudo.getNivel().getValor(), meuNivelConteudo.getImagemNivel(this)));
        }

        BarDataSet setNiveisPorConteudo = new BarDataSet(listaEntradas, "Níveis por Conteúdo");
        setNiveisPorConteudo.setDrawValues(false);
        setNiveisPorConteudo.setColors(ColorTemplate.JOYFUL_COLORS);
        BarData data = new BarData(setNiveisPorConteudo);
        data.setDrawValues(false);
        bcGraficoNiveisPorConteudo.setData(data);
        bcGraficoNiveisPorConteudo.animateY(2000);
        bcGraficoNiveisPorConteudo.getDescription().setEnabled(false);
        bcGraficoNiveisPorConteudo.setScaleEnabled(false);
        bcGraficoNiveisPorConteudo.setHighlightPerDragEnabled(false);
        bcGraficoNiveisPorConteudo.getLegend().setEnabled(false);
        //YAxis
        XAxis xAxis = bcGraficoNiveisPorConteudo.getXAxis();
        YAxis left = bcGraficoNiveisPorConteudo.getAxisLeft();
        YAxis right = bcGraficoNiveisPorConteudo.getAxisRight();
        left.setGranularity(1f);
        left.setAxisMinimum(0);
        left.setAxisMaximum(5);
        left.setValueFormatter(new IndexAxisValueFormatter(yLabel));
//        right.setGranularity(1f);
//        right.setAxisMinimum(0);
//        right.setAxisMaximum(5);
        right.setEnabled(false);
        //XAxis
        xAxis.setGranularity(1f);
        xAxis.setDrawGridLines(false);
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        xAxis.setValueFormatter(new IndexAxisValueFormatter(label));
        //xAxis.setDrawLabels();
        System.out.println("Terminei");
    }


}
