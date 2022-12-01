package com.example.user.projetoquimicaConsolidados;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageButton;

import com.example.user.projetoquimicaFuturo.GaleriaActivity;
import com.example.user.projetoquimicaFuturo.MontarCompostoActivity;
import com.example.user.projetoquimicaFuturo.PesquisaElementoActivity;
import com.example.user.projetoquimicaFuturo.ResumosActivity;
import com.example.user.projetoquimicaFuturo.TabelaPeriodicaActivity;

public class MainMenuActivity extends AppCompatActivity{
    ImageButton ibResumos, ibTabela, ibQuiz, ibGaleria, ibMontar, ibPesquisa;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);

        ibResumos = findViewById(R.id.ibResumos);
        ibTabela = findViewById(R.id.ibTabela);
        ibGaleria = findViewById(R.id.ibGaleria);
        ibMontar = findViewById(R.id.ibMontar);
        ibQuiz = findViewById(R.id.ibQuiz);
        ibPesquisa = findViewById(R.id.ibPesquisa);

        ibResumos.setOnClickListener(trataEvento);
        ibTabela.setOnClickListener(trataEvento);
        ibPesquisa.setOnClickListener(trataEvento);
        ibMontar.setOnClickListener(trataEvento);
        ibQuiz.setOnClickListener(trataEvento);
        ibGaleria.setOnClickListener(trataEvento);
    }

    View.OnClickListener trataEvento = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if (v.getId() == ibResumos.getId()) {
                Intent itResumos = new Intent(MainMenuActivity.this, ResumosActivity.class);
                startActivity(itResumos);
            } else if (v.getId() == ibTabela.getId()) {
                Intent itTabela = new Intent(MainMenuActivity.this, TabelaPeriodicaActivity.class);
                startActivity(itTabela);
            } else if (v.getId() == ibQuiz.getId()) {
                //Intent itQuiz = new Intent(MainMenuActivity.this, QuizResultadoActivity.class);
                // por enquanto, não chamaremos direto o quiz e sim uma tela intermediária para cadastros e o quiz
                Intent itQuiz = new Intent(MainMenuActivity.this, QuizMenuActivity.class);

                startActivity(itQuiz);
            } else if (v.getId() == ibGaleria.getId()) {
                Intent itGaleria = new Intent(MainMenuActivity.this, GaleriaActivity.class);
                startActivity(itGaleria);
            } else if (v.getId() == ibMontar.getId()) {
                Intent itMontar = new Intent(MainMenuActivity.this, MontarCompostoActivity.class);
                startActivity(itMontar);
            } else if(v.getId() == ibPesquisa.getId()) {
                Intent itPesquisar = new Intent(MainMenuActivity.this, PesquisaElementoActivity.class);
                startActivity(itPesquisar);
            }
        }
    };
}
