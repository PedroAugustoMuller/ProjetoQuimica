package com.example.user.projetoquimica;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;

public class QuizCadastroActivity extends AppCompatActivity {

    Button bCadastroPerguntas, bCadastroConteudos, bQuiz, bConteudoNivel, bVisualizarNiveisConteudos,
    bDiagnostico, bGraficoDesempenhoConteudo;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz_cadastro);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        bCadastroPerguntas = findViewById(R.id.bCadastroPerguntas);
        bCadastroConteudos = findViewById(R.id.bCadastroConteudos);
        bQuiz = findViewById(R.id.bQuiz);
        bConteudoNivel = findViewById(R.id.bConteudoNivel);
        bVisualizarNiveisConteudos = findViewById(R.id.bVisualizarlistaNiveisConteudos);
        bDiagnostico = findViewById(R.id.bDiagnostico);
        bGraficoDesempenhoConteudo = findViewById(R.id.bGraficoDesempenhoConteudo);


        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        bCadastroPerguntas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it = new Intent(QuizCadastroActivity.this, EntradaDePerguntasActivity.class);
                startActivity(it);
            }
        });

        bCadastroConteudos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it = new Intent(QuizCadastroActivity.this, EntradaDeConteudosActivity.class);
                startActivity(it);
            }
        });

        bQuiz.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it = new Intent(QuizCadastroActivity.this, FiltroActivity.class);
                startActivity(it);
            }
        });

        bConteudoNivel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent it = new Intent(QuizCadastroActivity.this, TelaTeste.class);
                startActivity(it);
            }
        });

        bVisualizarNiveisConteudos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent it = new Intent(QuizCadastroActivity.this, VisualizacaoNiveisActivity.class);
                startActivity(it);
            }
        });

        bDiagnostico.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent it = new Intent(QuizCadastroActivity.this, FiltroDiagnosticoActivity.class);
                startActivity(it);
            }
        });

        bGraficoDesempenhoConteudo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it = new Intent(QuizCadastroActivity.this, GraficoDesempenhoConteudo.class);
                startActivity(it);
            }
        });
    }

}
