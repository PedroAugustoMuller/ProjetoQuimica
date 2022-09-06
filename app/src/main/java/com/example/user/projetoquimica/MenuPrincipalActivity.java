package com.example.user.projetoquimica;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageButton;

public class MenuPrincipalActivity extends AppCompatActivity{
    ImageButton ibResumos, ibTabela, ibQuiz, ibGaleria, ibMontar, ibPesquisa;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_principal);

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
                Intent itResumos = new Intent(MenuPrincipalActivity.this, ResumosActivity.class);
                startActivity(itResumos);
            } else if (v.getId() == ibTabela.getId()) {
                Intent itTabela = new Intent(MenuPrincipalActivity.this, TabelaPeriodicaActivity.class);
                startActivity(itTabela);
            } else if (v.getId() == ibQuiz.getId()) {
                //Intent itQuiz = new Intent(MenuPrincipalActivity.this, QuizActivity.class);
                // por enquanto, não chamaremos direto o quiz e sim uma tela intermediária para cadastros e o quiz
                Intent itQuiz = new Intent(MenuPrincipalActivity.this, QuizCadastroActivity.class);

                startActivity(itQuiz);
            } else if (v.getId() == ibGaleria.getId()) {
                Intent itGaleria = new Intent(MenuPrincipalActivity.this, GaleriaActivity.class);
                startActivity(itGaleria);
            } else if (v.getId() == ibMontar.getId()) {
                Intent itMontar = new Intent(MenuPrincipalActivity.this, MontarCompostoActivity.class);
                startActivity(itMontar);
            } else if(v.getId() == ibPesquisa.getId()) {
                Intent itPesquisar = new Intent(MenuPrincipalActivity.this, PesquisaElementoActivity.class);
                startActivity(itPesquisar);
            }
        }
    };
}
