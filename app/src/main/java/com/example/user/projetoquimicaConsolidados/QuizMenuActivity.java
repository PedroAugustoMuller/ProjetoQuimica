package com.example.user.projetoquimicaConsolidados;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;

import com.example.user.projetoquimicaFuturo.FiltroDiagnosticoActivity;
import com.example.user.projetoquimicaTeste.TelaTeste;

public class QuizMenuActivity extends AppCompatActivity {

    Button bCadastroPerguntas, bCadastroConteudos, bQuiz, bConteudoNivel, bVisualizarNiveisConteudos,
    bDiagnostico, bGraficoDesempenhoConteudo, bGraficoDezQuestionarios, bGraficoNiveisPorConteudo;

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
        bGraficoDezQuestionarios = findViewById(R.id.bGraficoDezQuestionarios);
        bGraficoNiveisPorConteudo = findViewById(R.id.bGraficoNiveisPorConteudo);


        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        bCadastroPerguntas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it = new Intent(QuizMenuActivity.this, CadastroPerguntaActivity.class);
                startActivity(it);
            }
        });

        bCadastroConteudos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it = new Intent(QuizMenuActivity.this, CadastroConteudoActivity.class);
                startActivity(it);
            }
        });

        bQuiz.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it = new Intent(QuizMenuActivity.this, QuizFiltroActivity.class);
                startActivity(it);
            }
        });

        bConteudoNivel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent it = new Intent(QuizMenuActivity.this, TelaTeste.class);
                startActivity(it);
            }
        });

        bVisualizarNiveisConteudos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent it = new Intent(QuizMenuActivity.this, VisualizacaoNiveisActivity.class);
                startActivity(it);
            }
        });

        bDiagnostico.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent it = new Intent(QuizMenuActivity.this, FiltroDiagnosticoActivity.class);
                startActivity(it);
            }
        });

        bGraficoDesempenhoConteudo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it = new Intent(QuizMenuActivity.this, VisualizacaoConteudosActivity.class);
                startActivity(it);
            }
        });
        bGraficoDezQuestionarios.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent it = new Intent(QuizMenuActivity.this, GraficoDezQuestionariosActivity.class);
                startActivity(it);
            }
        });
        bGraficoNiveisPorConteudo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent it = new Intent(QuizMenuActivity.this, GraficoNiveisPorConteudoActivity.class);
                startActivity(it);
            }
        });

    }

}
