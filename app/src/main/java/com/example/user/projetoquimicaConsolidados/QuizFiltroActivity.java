package com.example.user.projetoquimicaConsolidados;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.user.banco.ConteudoDB;
import com.example.user.banco.InformacoesApp;
import com.example.user.classesDominio.Conteudo;
import com.example.user.componente.MultiSelectionSpinner;

import java.util.ArrayList;

public class QuizFiltroActivity extends AppCompatActivity {
    ConteudoDB conteudoDB;
    Button bSalvar;
    ImageView ivFiltroRemover, ivFiltroAdicionar, ivFiltroRemover10, ivFiltroAdicionar10;
    TextView tvFiltroQuantidadePerguntas;
    RadioButton rbSelecionar, rbSortear;
    EditText etQuantidadeConteudos;
    ArrayList<Conteudo> listaConteudos;
    ArrayList<Conteudo> listaConteudosSelecionados;
    MultiSelectionSpinner spMultiConteudos;
    int quantidadePerguntas = 0, quantidadeConteudos;
    InformacoesApp informacoesApp;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        int QuantidadePerguntas =1;
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz_filtro);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ivFiltroRemover10 = findViewById(R.id.ivFiltroRemover10);
        ivFiltroAdicionar10 = findViewById(R.id.ivFiltroAdicionar10);
        ivFiltroAdicionar = findViewById(R.id.ivFiltroAdicionar);
        ivFiltroRemover = findViewById(R.id.ivFiltroRemover);
        tvFiltroQuantidadePerguntas = findViewById(R.id.tvFiltroQuantidadePerguntas);
        //etQuantidadePerguntas = findViewById(R.id.etQuantidadePerguntas);
        etQuantidadeConteudos = findViewById(R.id.etQuantidadeConteudos);
        bSalvar = findViewById(R.id.bSalvar);
        rbSelecionar = findViewById(R.id.rbSelecionar);
        rbSortear = findViewById(R.id.rbSortear);
        spMultiConteudos = findViewById(R.id.spMultiConteudos);

        informacoesApp = (InformacoesApp)getApplicationContext();

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        listaConteudos = new ArrayList<>();

        conteudoDB = new ConteudoDB(getApplicationContext());
        listaConteudos = conteudoDB.buscaConteudos(informacoesApp.getTipoConteudo());
        Log.d("Teste"," Lista de Conteudos: " + listaConteudos.size() + " Tipo Conteudo:" + informacoesApp.getTipoConteudo());
        spMultiConteudos.setItems(listaConteudos);


        spMultiConteudos.setEnabled(false);

        rbSelecionar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tvFiltroQuantidadePerguntas.setEnabled(true);
                ivFiltroAdicionar.setEnabled(true);
                ivFiltroRemover.setEnabled(true);
                etQuantidadeConteudos.setEnabled(false);
                spMultiConteudos.setEnabled(true);
            }
        });

        rbSortear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tvFiltroQuantidadePerguntas.setEnabled(true);
                ivFiltroAdicionar.setEnabled(true);
                ivFiltroRemover.setEnabled(true);
                etQuantidadeConteudos.setEnabled(true);
                spMultiConteudos.setEnabled(false);
            }
        });

        //Leandro - botao de diminuir uma pergunta
        ivFiltroRemover.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(quantidadePerguntas>1){
                    quantidadePerguntas = quantidadePerguntas--;
                }
                tvFiltroQuantidadePerguntas.setText(String.valueOf(quantidadePerguntas));
            }
        });
        //Leandro - botao de adicionar uma pergunta
        ivFiltroAdicionar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                quantidadePerguntas = quantidadePerguntas++;
                tvFiltroQuantidadePerguntas.setText(String.valueOf(quantidadePerguntas));
            }
        });
        //Leandro - botao de diminuir 10 perguntas
        ivFiltroRemover10.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(quantidadePerguntas>=10){
                    quantidadePerguntas = quantidadePerguntas-10;
                }else{
                    ivFiltroRemover10.setEnabled(false);
                }
                tvFiltroQuantidadePerguntas.setText(String.valueOf(quantidadePerguntas));
            }
        });
        //Leandro - botao de adicionar 10 perguntas
        ivFiltroAdicionar10.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                quantidadePerguntas = quantidadePerguntas+10;
                tvFiltroQuantidadePerguntas.setText(String.valueOf(quantidadePerguntas));
            }
        });

        bSalvar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!tvFiltroQuantidadePerguntas.getText().toString().equals("")){

                        quantidadePerguntas = Integer.parseInt(tvFiltroQuantidadePerguntas.getText().toString());

                        if (rbSelecionar.isChecked()){
                            if (spMultiConteudos.getSelectedSize() > 0) {
                                // chamando o método para obter a lista de conteúdos que foram selecionados
                                listaConteudosSelecionados = spMultiConteudos.getSelectedItems();
                                // fazer o que precisar com a lista, vou mostrar na tela
                                String msg = "";
                                for (int x = 0; x < listaConteudosSelecionados.size(); x++) {
                                    Conteudo conteudo = listaConteudosSelecionados.get(x);

                                    msg = msg + "\nConteúdo: " + conteudo.getNomeConteudo();
                                }
                                Intent it = new Intent(QuizFiltroActivity.this, QuizActivity.class);
                                it.putExtra("listaConteudos", listaConteudosSelecionados);
                                it.putExtra("tipo",1); //1 = quiz, 2 = diagnóstico (FiltroDiagnosticoActivity)
                                it.putExtra("quantidade",quantidadePerguntas);
                                startActivity(it);
                                Toast.makeText(QuizFiltroActivity.this, "Conteúdos selecionados: " + msg, Toast.LENGTH_SHORT).show();
                            } else {
                                Toast.makeText(QuizFiltroActivity.this, "Selecione os conteúdos que deseja!", Toast.LENGTH_SHORT).show();
                            }
                        } else if (rbSortear.isChecked()){
                            if(!etQuantidadeConteudos.getText().toString().equals("")){
                                quantidadeConteudos = Integer.parseInt(etQuantidadeConteudos.getText().toString());
                                int tipoConteudo = informacoesApp.getTipoConteudo();
                                listaConteudosSelecionados = conteudoDB.buscaConteudosAleatorios(quantidadeConteudos, tipoConteudo);
                                Intent it = new Intent(QuizFiltroActivity.this, QuizActivity.class);
                                it.putExtra("listaConteudos", listaConteudosSelecionados);
                                it.putExtra("quantidade",quantidadePerguntas);
                                it.putExtra("tipo", 1); //1 = quiz, 2 = diagnóstico (FiltroDiagnosticoActivity)
                                startActivity(it);
                            } else {
                                etQuantidadeConteudos.setError("Informe a quantidade de conteúdos!");
                                etQuantidadeConteudos.requestFocus();
                            }
                        }
                } else {
                    tvFiltroQuantidadePerguntas.setError("Informe a quantidade de perguntas!");
                    tvFiltroQuantidadePerguntas.requestFocus();
                }
            }
        });

    }
}
