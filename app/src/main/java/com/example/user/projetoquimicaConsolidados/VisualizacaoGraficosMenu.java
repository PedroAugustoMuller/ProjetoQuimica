package com.example.user.projetoquimicaConsolidados;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;

public class VisualizacaoGraficosMenu extends AppCompatActivity {
    Button bVisualizacaoGraficosDezQuestionarios,bVisualizacaoGraficosDesempenhoConteudo,bVisualizacaoGraficosNiveisPorConteudo;
    Intent it;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_visualizacao_graficos_menu);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        bVisualizacaoGraficosNiveisPorConteudo = findViewById(R.id.bVisualizacaoGraficosNiveisPorConteudo);
        bVisualizacaoGraficosDesempenhoConteudo = findViewById(R.id.bVisualizacaoGraficosDesempenhoConteudo);
        bVisualizacaoGraficosDezQuestionarios = findViewById(R.id.bVisualizacaoGraficosDezQuestionarios);

        bVisualizacaoGraficosNiveisPorConteudo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                it = new Intent(VisualizacaoGraficosMenu.this,GraficoNiveisPorConteudoActivity.class);
                startActivity(it);
            }
        });

        bVisualizacaoGraficosDesempenhoConteudo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                it = new Intent(VisualizacaoGraficosMenu.this,VisualizacaoConteudosActivity.class);
                startActivity(it);
            }
        });

        bVisualizacaoGraficosDezQuestionarios.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                it = new Intent(VisualizacaoGraficosMenu.this,GraficoDezQuestionariosActivity.class);
                startActivity(it);
            }
        });
    }

}
