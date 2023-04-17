package com.example.user.projetoquimicaConsolidados;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.example.user.banco.InformacoesApp;
import com.example.user.classesDominio.Conteudo;
import com.example.user.classesDominio.Feedback;
import com.example.user.classesDominio.NivelConteudo;

public class VisualizaPercursoActivity extends AppCompatActivity {
    TextView tvVisualizaTitulo, tvVisualizaSaudacao;
    ImageView ivVisualizaUpgrade;
    Button bVisualizaPercursoQuiz, bVisualizaPercursoHistorico;
    Conteudo conteudo;
    Context context;
    RatingBar rtbVisualizaPercursoVidas;

    InformacoesApp informacoesApp;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_visualiza_percurso);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        tvVisualizaTitulo = findViewById(R.id.tvVisualizaTitulo);
        tvVisualizaSaudacao = findViewById(R.id.tvVisualizaSaudacao);
        ivVisualizaUpgrade = findViewById(R.id.ivVisualizaUpgrade);

        bVisualizaPercursoQuiz = findViewById(R.id.bVisualizaPercursoQuiz);
        bVisualizaPercursoHistorico = findViewById(R.id.bVisualizaPercursoHistorico);

        rtbVisualizaPercursoVidas = findViewById(R.id.rtbVisualizaPercursoVidas);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        Intent it = getIntent();

        context = getApplicationContext();

        informacoesApp = (InformacoesApp)getApplicationContext();



        if(it != null){
            NivelConteudo meuNivel = (NivelConteudo) it.getSerializableExtra("nivel"); //aqui n seria
            conteudo = meuNivel.getConteudo();//para mandar o conteudo pela intent
            ivVisualizaUpgrade.setImageDrawable(meuNivel.getImagemNivelCaminho(context));
            rtbVisualizaPercursoVidas.setRating(meuNivel.getVidas());
            //rtbVisualizaPercursoVidas.setEnabled(false);
            rtbVisualizaPercursoVidas.setIsIndicator(true);
            tvVisualizaTitulo.setText(informacoesApp.getMeuUsuario().getNomeUsuario().substring(0,1).toUpperCase() + informacoesApp.getMeuUsuario().getNomeUsuario().substring(1).toLowerCase() + ", confira o seu percurso no conteúdo " + meuNivel.getConteudo().getNomeConteudo() + ":");

            if (it.hasExtra("feedback")) { // sinal que está sendo chamado através da tela de desempenho
                Feedback meuFeedback = (Feedback) it.getSerializableExtra("feedback");
                if (meuFeedback.getNiveisAvancados() > 0) {
                    tvVisualizaSaudacao.setText("Parabéns, você avançou de nível!");
                }
                // desabilitando os botões
                bVisualizaPercursoQuiz.setVisibility(View.INVISIBLE);
                bVisualizaPercursoHistorico.setVisibility(View.INVISIBLE);
            }
        }

        bVisualizaPercursoHistorico.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it = new Intent(VisualizaPercursoActivity.this, GraficoDesempenhoConteudo.class);
                it.putExtra("conteudo",conteudo);
                startActivity(it);
            }
        });

        bVisualizaPercursoQuiz.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it = new Intent(VisualizaPercursoActivity.this, QuizFiltroActivity.class);
                startActivity(it);
            }
        });
    }

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
        } else if (id == android.R.id.home) {
            finish();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
