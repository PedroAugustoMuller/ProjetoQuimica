package com.example.user.projetoquimicaConsolidados;

import android.content.Intent;
import android.os.Bundle;

import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.support.v7.widget.Toolbar;
import android.view.View;


import com.example.user.projetoquimicaTeste.TelaTeste;

public class QuizMenuActivity extends AppCompatActivity {
    CardView cvQuizMenuQuiz, cvQuizMenuGraficos, cvQuizMenuAddpergunta, cvQuizMenuAddconteudo, cvQuizMenuAddnivelconteudo, cvQuizMenuVisualizacaoConteudos;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz_menu);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        cvQuizMenuQuiz = findViewById(R.id.cvQuizMenuQuiz);
        cvQuizMenuGraficos = findViewById(R.id.cvQuizMenuGraficos);
        cvQuizMenuAddpergunta = findViewById(R.id.cvQuizMenuAddpergunta);
        cvQuizMenuAddconteudo = findViewById(R.id.cvQuizMenuAddconteudo);
        cvQuizMenuAddnivelconteudo = findViewById(R.id.cvQuizMenuAddnivelconteudo);
        cvQuizMenuVisualizacaoConteudos = findViewById(R.id.cvQuizMenuVisualizacaoConteudos);

        cvQuizMenuQuiz.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it = new Intent(QuizMenuActivity.this, QuizFiltroActivity.class);
                startActivity(it);
            }
        });

        cvQuizMenuGraficos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it = new Intent(QuizMenuActivity.this, VisualizacaoGraficosMenu.class);
                startActivity(it);

            }
        });

        cvQuizMenuAddpergunta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it = new Intent(QuizMenuActivity.this, CadastroPerguntaActivity.class);
                startActivity(it);
            }
        });

        cvQuizMenuAddconteudo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it = new Intent(QuizMenuActivity.this, CadastroConteudoActivity.class);
                startActivity(it);
            }
        });

        cvQuizMenuAddnivelconteudo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it = new Intent(QuizMenuActivity.this, TelaTeste.class);
                startActivity(it);
            }
        });

        cvQuizMenuVisualizacaoConteudos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it = new Intent(QuizMenuActivity.this, VisualizacaoNiveisActivity.class);
                startActivity(it);
            }
        });
    }

}
