package com.example.user.projetoquimicaConsolidados;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.example.user.banco.DesempenhoConteudoDB;
import com.example.user.banco.InformacoesApp;
import com.example.user.banco.NivelConteudoDB;
import com.example.user.classesDominio.Conteudo;
import com.example.user.classesDominio.DesempenhoConteudo;
import com.example.user.componente.NivelConteudoEnum;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;

import java.util.ArrayList;

public class GraficoDesempenhoConteudo extends AppCompatActivity {
    LineChart linechartDesempenhoConteudo;
    InformacoesApp informacoesApp;
    NivelConteudoEnum nivelConteudoEnum;
    Conteudo conteudo;
    RadioGroup rgSelecionaNivelConteudo;
    RadioButton rbGraficoNivelConteudoCobre, rbGraficoNivelConteudoBronze, rbGraficoNivelConteudoPrata, rbGraficoNivelConteudoOuro, rbGraficoNivelConteudoDiamante;
    DesempenhoConteudoDB desempenhoConteudoDB = new DesempenhoConteudoDB(GraficoDesempenhoConteudo.this);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_grafico_desempenho_conteudo);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        informacoesApp = (InformacoesApp) getApplicationContext();
        rgSelecionaNivelConteudo = findViewById(R.id.rgSelecionaNivelConteudo);
        rbGraficoNivelConteudoCobre = findViewById(R.id.rbGraficoNivelConteudoCobre);
        rbGraficoNivelConteudoBronze = findViewById(R.id.rbGraficoNivelConteudoBronze);
        rbGraficoNivelConteudoPrata = findViewById(R.id.rbGraficoNivelConteudoPrata);
        rbGraficoNivelConteudoOuro = findViewById(R.id.rbGraficoNivelConteudoOuro);
        rbGraficoNivelConteudoDiamante = findViewById(R.id.rbGraficoNivelConteudoDiamante);
        linechartDesempenhoConteudo = findViewById(R.id.linechartDesempenhoConteudo);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        Intent it = getIntent(); //pegando o conteudo selecionado na activity VisualizacaoConteudosActivity
        conteudo = (Conteudo)it.getSerializableExtra("conteudo"); //atribuindo ao conteudo

        LineDataSet lineDataSet = new LineDataSet(carregaLineChartSemFiltro(),"teste");
        ArrayList<ILineDataSet> dataSets = new ArrayList<>();
        dataSets.add(lineDataSet);

        LineData data = new LineData(dataSets);
        linechartDesempenhoConteudo.setData(data);
        linechartDesempenhoConteudo.invalidate();


        rgSelecionaNivelConteudo.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {

                    LineDataSet lineDataSet = new LineDataSet(carregaLineChartFiltro(),"teste");
                    ArrayList<ILineDataSet> dataSets = new ArrayList<>();
                    dataSets.add(lineDataSet);

                    LineData data = new LineData(dataSets);
                    linechartDesempenhoConteudo.setData(data);
                    linechartDesempenhoConteudo.invalidate();

            }
        });

    }

    private ArrayList<Entry> carregaLineChartSemFiltro(){
        ArrayList<Entry> listaEntradas = new ArrayList<Entry>();
        ArrayList<DesempenhoConteudo> listaDesempenhoConteudo = desempenhoConteudoDB.buscaDesempenhoConteudo();

        float cont=0;
        for(int x = 0; x < listaDesempenhoConteudo.size(); x++){

            if(conteudo.getIdConteudo() == listaDesempenhoConteudo.get(x).getConteudo().getIdConteudo()){
                DesempenhoConteudo desempenhoConteudo = listaDesempenhoConteudo.get(x);
                listaEntradas.add(new Entry(cont, desempenhoConteudo.getPontuacaoConteudo()));
                cont++;
            }
        }
        return listaEntradas;
    }

    private ArrayList<Entry> carregaLineChartFiltro(){
        ArrayList<Entry> listaEntradas = new ArrayList<>();
        NivelConteudoEnum opcao = null;
        if(rbGraficoNivelConteudoCobre.isChecked()){
            opcao = nivelConteudoEnum.COBRE;
        }else if(rbGraficoNivelConteudoBronze.isChecked()){
            opcao = nivelConteudoEnum.BRONZE;
        }else if(rbGraficoNivelConteudoPrata.isChecked()){
            opcao = nivelConteudoEnum.PRATA;
        }else if(rbGraficoNivelConteudoOuro.isChecked()){
            opcao = nivelConteudoEnum.OURO;
        }else if(rbGraficoNivelConteudoDiamante.isChecked()){
            opcao = nivelConteudoEnum.DIAMANTE;
        }
        Log.d("Teste", "" + opcao.getValor());
        ArrayList<DesempenhoConteudo> listaDesempenhoConteudoFiltro = desempenhoConteudoDB.buscaDesempenhoConteudoFiltro(conteudo, opcao.getValor());

        float cont=0;
        for(int x =0; x < listaDesempenhoConteudoFiltro.size(); x++){

                DesempenhoConteudo desempenhoConteudo = listaDesempenhoConteudoFiltro.get(x);
                listaEntradas.add(new Entry(cont, desempenhoConteudo.getPontuacaoConteudo()));
                cont++;

        }
        return listaEntradas;
    }

}
