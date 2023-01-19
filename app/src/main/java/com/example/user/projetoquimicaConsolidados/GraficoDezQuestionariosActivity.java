package com.example.user.projetoquimicaConsolidados;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;

import com.example.user.banco.DesempenhoQuestionarioDB;
import com.example.user.banco.InformacoesApp;
import com.example.user.classesDominio.ClasseIntermediariaGraficos;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.LineData;


public class GraficoDezQuestionariosActivity extends AppCompatActivity {

    LineChart lcGraficoDezQuestionariosLinha;
    InformacoesApp informacoesApp;
    DesempenhoQuestionarioDB desempenhoQuestionarioDB;
    ClasseIntermediariaGraficos classeIntermediariaGraficos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_grafico_dez_questionarios);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        //LineChart
        lcGraficoDezQuestionariosLinha = findViewById(R.id.lcGraficoDezQuestionariosLinha);
        //InformacoesApp
        informacoesApp = (InformacoesApp) getApplicationContext();
        //DesempenhoQuestionarioDB
        desempenhoQuestionarioDB = new  DesempenhoQuestionarioDB (getApplicationContext());
        //ClasseIntermediariaGraficos
        classeIntermediariaGraficos = new ClasseIntermediariaGraficos(this);
        ConfiguraGrafico();
        Log.d("Teste","Voltei do configuraGrafico");
    }

    public void ConfiguraGrafico() {
        Log.d("Teste","Entrei no configuraGrafico");
        LineData data = classeIntermediariaGraficos.configuraGraficoUltimosQuestionarios(desempenhoQuestionarioDB.buscaDesempenhoQuestionario(informacoesApp.getMeuUsuario()));

        lcGraficoDezQuestionariosLinha.setData(data);
        lcGraficoDezQuestionariosLinha.setScaleEnabled(false);
        lcGraficoDezQuestionariosLinha.getLegend().setEnabled(false);
        lcGraficoDezQuestionariosLinha.getDescription().setEnabled(false);
        XAxis xAxis = lcGraficoDezQuestionariosLinha.getXAxis();
        YAxis left = lcGraficoDezQuestionariosLinha.getAxisLeft();
        lcGraficoDezQuestionariosLinha.getAxisRight().setEnabled(false);
        lcGraficoDezQuestionariosLinha.setExtraBottomOffset(5f);
        //XAxis
        xAxis.setGranularity(1f);
        xAxis.setTextSize(15f);
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);

        //YAxis
        left.setGranularity(10f);
        left.setDrawZeroLine(false);
        left.setTextSize(15f);



    }

}
