package com.example.user.projetoquimicaConsolidados;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import android.widget.TextView;
import android.widget.Toast;

import com.example.user.banco.InformacoesApp;
import com.example.user.classesDominio.DesempenhoConteudo;
import com.example.user.classesDominio.DesempenhoQuestionario;
import com.example.user.classesDominio.Feedback;
import com.example.user.classesDominio.NivelConteudo;
import com.example.user.classesDominio.Pergunta;
import com.example.user.componente.DesempenhoFeedbackAdapter;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class QuizResultadoActivity extends AppCompatActivity {
    DesempenhoFeedbackAdapter adapter;
    RecyclerView rvDesempenhoConteudo;
    InformacoesApp informacoesApp;
    ArrayList<Feedback> listaFeedbacks;
    ArrayList<NivelConteudo> listaNivelConteudo;
    ArrayList<DesempenhoConteudo> listaDesempenhoConteudos;
    ArrayList<Pergunta> listaPerguntas;
    Context context;
    TextView tvDesempenhoData, tvDesempenhoPontuacaoFinal, tvTituloDesempenho;
    Button bQuizRelatorio,bQuizGrafico10Questionarios;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz_resultado);
        //Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        //setSupportActionBar(toolbar);
        rvDesempenhoConteudo = (RecyclerView) findViewById(R.id.rvDesempenhoConteudo);
        tvTituloDesempenho = findViewById(R.id.tvTituloDesempenho);
        tvDesempenhoData = findViewById(R.id.tvDesempenhoData);
        tvDesempenhoPontuacaoFinal = findViewById(R.id.tvDesempenhoPontuacaoFinal);
        bQuizRelatorio = findViewById(R.id.bQuizRelatorio);
        bQuizGrafico10Questionarios = findViewById(R.id.bQuizGrafico10Questionarios);
        //getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        informacoesApp = (InformacoesApp) getApplicationContext();
        Intent it = getIntent();
        context = getApplicationContext();
        if (it != null) {

            DesempenhoQuestionario desempenhoQuestionario = (DesempenhoQuestionario) it.getSerializableExtra("desempenho");
            listaDesempenhoConteudos = desempenhoQuestionario.getListaDesempenhoConteudos();
            listaFeedbacks = (ArrayList<Feedback>)it.getSerializableExtra("listaFeedback");
            listaPerguntas = (ArrayList<Pergunta>)it.getSerializableExtra("listaPerguntas");
            listaNivelConteudo = (ArrayList<NivelConteudo>)it.getSerializableExtra("listaNivelConteudos");
            tvTituloDesempenho.setText(informacoesApp.getMeuUsuario().getNomeUsuario().substring(0,1).toUpperCase() + informacoesApp.getMeuUsuario().getNomeUsuario().substring(1).toLowerCase() + ", confira o seu desempenho:");
            tvDesempenhoPontuacaoFinal.setText(String.valueOf(desempenhoQuestionario.getPontuacaoFinal()));
            Log.d("NovoTeste", "Desempenho: " + listaDesempenhoConteudos.size() + ", nivel: " + listaNivelConteudo.size() + ", feedback: " + listaFeedbacks.size());
            Date data = desempenhoQuestionario.getData();
            DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
            String dataFormatada = dateFormat.format(data);
            tvDesempenhoData.setText(dataFormatada);

            // apenas para QuizMenuActivity
            String[] desempenho = new String[desempenhoQuestionario.getListaDesempenhoConteudos().size()];

            for (int x = 0; x < desempenhoQuestionario.getListaDesempenhoConteudos().size(); x++) {
                DesempenhoConteudo desempenhoConteudo = desempenhoQuestionario.getListaDesempenhoConteudos().get(x);
                desempenho[x] = desempenhoConteudo.getPontuacaoConteudo() + " - " + desempenhoConteudo.getQuantidadeAcertos();
                Toast.makeText(QuizResultadoActivity.this, "Conteúdo: " + desempenhoConteudo.getConteudo().getNomeConteudo()
                        + ", Acertos: " + desempenhoConteudo.getQuantidadeAcertos()
                        + ", Erros: " + desempenhoConteudo.getQuantidadeErros()
                        + ", Pontuação: " + desempenhoConteudo.getPontuacaoConteudo(), Toast.LENGTH_LONG).show();
            }

            adapter = new DesempenhoFeedbackAdapter(listaDesempenhoConteudos, listaNivelConteudo, listaFeedbacks, trataCliqueItem, context); //NivelConteudoOnClickListener
            rvDesempenhoConteudo.setLayoutManager(new LinearLayoutManager(QuizResultadoActivity.this));
            rvDesempenhoConteudo.setItemAnimator(new DefaultItemAnimator());
            rvDesempenhoConteudo.setAdapter(adapter);
        }

        bQuizRelatorio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent it = new Intent(QuizResultadoActivity.this, QuizRelatorioActivity.class );
                it.putExtra("listaPerguntas", listaPerguntas);
                startActivityForResult(it,123);
            }
        });

        bQuizGrafico10Questionarios.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it = new Intent(QuizResultadoActivity.this, GraficoDezQuestionariosActivity.class);
                startActivity(it);
            }
        });
    }

    DesempenhoFeedbackAdapter.DesempenhoFeedbackOnClickListener trataCliqueItem = new DesempenhoFeedbackAdapter.DesempenhoFeedbackOnClickListener() {
        @Override
        public void onClickDesempenhoFeedback(View view, int position) {
            NivelConteudo nivelConteudo = listaNivelConteudo.get(position);
            Feedback feedback = listaFeedbacks.get(position);
            Intent it = new Intent(QuizResultadoActivity.this, VisualizaPercursoActivity.class);
            it.putExtra("nivel", nivelConteudo);
            it.putExtra("feedback", feedback);
            startActivity(it);
        }
    };


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_info, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_informacoes) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

}