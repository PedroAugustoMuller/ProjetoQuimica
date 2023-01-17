package com.example.user.projetoquimicaConsolidados;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import com.example.user.banco.DesempenhoQuestionarioDB;
import com.example.user.banco.InformacoesApp;
import com.example.user.classesDominio.DesempenhoQuestionario;
import com.example.user.componente.templates.TemplateDeCores;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;

public class GraficoDezQuestionariosActivity extends AppCompatActivity {

    LineChart lcGraficoDezQuestionariosLinha;
    InformacoesApp informacoesApp;
    DesempenhoQuestionarioDB desempenhoQuestionarioDB;

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
        ConfiguraGrafico();
    }

    public void ConfiguraGrafico() {
        ArrayList<Entry> listaEntradas = new ArrayList<>();
        ArrayList<DesempenhoQuestionario> listaDesempenhoQuestionario = desempenhoQuestionarioDB.buscaDesempenhoQuestionario(informacoesApp.getMeuUsuario());
        final String []labels;
        if (listaDesempenhoQuestionario.size() < 10){
            labels = new String[listaDesempenhoQuestionario.size()];
        } else {
            labels = new String[10];
        }

        float cont = 0;
        System.out.println(listaDesempenhoQuestionario.size());
        for (int x = listaDesempenhoQuestionario.size(); x >= (listaDesempenhoQuestionario.size()-4); x--){
            DesempenhoQuestionario meuDesempenhoQuestionario = listaDesempenhoQuestionario.get(x-1);

            System.out.println(meuDesempenhoQuestionario.getPontuacaoFinal());

           labels[(int)cont] = String.valueOf(meuDesempenhoQuestionario.getData());

           listaEntradas.add(new Entry(cont, meuDesempenhoQuestionario.getPontuacaoFinal()));
           cont++;
        }

        LineDataSet setDesempenhoQuestionarios = new LineDataSet(listaEntradas, "Dez");
        setDesempenhoQuestionarios.setLineWidth(4f);
        setDesempenhoQuestionarios.setColor(TemplateDeCores.ColorPrimaryDark);
        setDesempenhoQuestionarios.setValueTextSize(15f);
        setDesempenhoQuestionarios.setValueTextColor(TemplateDeCores.ColorPrimaryDark);
        setDesempenhoQuestionarios.setAxisDependency(YAxis.AxisDependency.LEFT);
        ArrayList<ILineDataSet> dataSets = new ArrayList<>();
        dataSets.add(setDesempenhoQuestionarios);
        LineData data = new LineData(dataSets);
        lcGraficoDezQuestionariosLinha.setData(data);
        lcGraficoDezQuestionariosLinha.invalidate();
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
        left.setDrawZeroLine(true);
        left.setTextSize(15f);



    }

}
