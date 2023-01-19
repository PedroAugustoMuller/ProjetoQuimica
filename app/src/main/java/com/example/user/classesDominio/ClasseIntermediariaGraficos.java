package com.example.user.classesDominio;

import android.content.Context;
import android.util.Log;

import com.example.user.componente.templates.TemplateDeCores;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;

import java.util.ArrayList;

public class ClasseIntermediariaGraficos {
    Context context;

    public ClasseIntermediariaGraficos(Context context) {
        this.context = context;
    }

    public LineData configuraGraficoUltimosQuestionarios(ArrayList<DesempenhoQuestionario> listaDesempenhoQuestionario){
        Log.d("Teste","Entrei no configuraGraficoUltimosQuestionarios");
        LineData data;
        ArrayList<Entry> listaEntradas = new ArrayList<>();

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
        data = new LineData(dataSets);
        return data;
    }

    public BarData configuraGraficoNiveisPorConteudo(ArrayList<NivelConteudo> listaNiveisConteudos){
        ArrayList<BarEntry> listaEntradas = new ArrayList<>();
        for (int x = 0; x < listaNiveisConteudos.size(); x++){
            NivelConteudo meuNivelConteudo = listaNiveisConteudos.get(x);
            listaEntradas.add(new BarEntry(x , (float)meuNivelConteudo.getNivel().getValor(), meuNivelConteudo.getImagemNivel(context)));
        }

        BarDataSet setNiveisPorConteudo = new BarDataSet(listaEntradas, "Níveis por Conteúdo");
        setNiveisPorConteudo.setDrawValues(false);
        setNiveisPorConteudo.setColors(TemplateDeCores.ColorPrimaryDark);
        BarData data = new BarData(setNiveisPorConteudo);
        data.setDrawValues(false);
        return data;
    }
}
