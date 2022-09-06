package com.example.user.projetoquimica;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.example.user.classesDominio.Pergunta;
import com.example.user.componente.RelatorioPerguntaAdapter;

import java.util.ArrayList;

public class RelatorioActivity extends AppCompatActivity {

    RecyclerView tvRelatorioVisualizaPerguntas;
    ArrayList<Pergunta> listaPerguntas = new ArrayList<>();
    RelatorioPerguntaAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_relatorio);
        Toolbar toolbar = findViewById(R.id.toolbar);
        tvRelatorioVisualizaPerguntas = findViewById(R.id.tvRelatorioVisualizaPerguntas);
        setSupportActionBar(toolbar);

        Intent it = getIntent();

        if (it.hasExtra("listaPerguntas")) {
            listaPerguntas = (ArrayList<Pergunta>) it.getSerializableExtra("listaPerguntas");
            adapter = new RelatorioPerguntaAdapter(listaPerguntas, trataCliqueItem);
            tvRelatorioVisualizaPerguntas.setLayoutManager(new LinearLayoutManager(RelatorioActivity.this));
            tvRelatorioVisualizaPerguntas.setItemAnimator(new DefaultItemAnimator());
            tvRelatorioVisualizaPerguntas.setAdapter(adapter);
        }
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    RelatorioPerguntaAdapter.PerguntaOnClickListener trataCliqueItem = new RelatorioPerguntaAdapter.PerguntaOnClickListener() {
        @Override
        public void onClickPergunta(View view, int position) {
            Pergunta pergunta = listaPerguntas.get(position);
            Intent it = new Intent(RelatorioActivity.this, VisualizacaoDetalhadaaquiActivity.class);
            it.putExtra("pergunta", pergunta);
            startActivity(it);
            finish();
        }
    };

}
