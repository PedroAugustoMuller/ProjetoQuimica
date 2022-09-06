package com.example.user.projetoquimica;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.EditText;

import com.example.user.classesDominio.Pergunta;

public class VisualizacaoDetalhadaaquiActivity extends AppCompatActivity {
    EditText etVisualizacaoDetalhadaEnunciado, etVisualizacaoDetalhadaOpcaoA, etVisualizacaoDetalhadaOpcaoB, etVisualizacaoDetalhadaOpcaoC, etVisualizacaoDetalhadaOpcaoD, etVisualizacaoDetalhadaOpcaoE, etVisualizacaoDetalhadaRespostaEscolhida, etVisualizacaoDetalhadaRespostaCerta;
    Pergunta pergunta;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_visualizacao_detalhadaaqui);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        etVisualizacaoDetalhadaEnunciado = findViewById(R.id.etVisualizacaoDetalhadaEnunciado);
        etVisualizacaoDetalhadaOpcaoA = findViewById(R.id.etVisualizacaoDetalhadaOpcaoA);
        etVisualizacaoDetalhadaOpcaoB = findViewById(R.id.etVisualizacaoDetalhadaOpcaoB);
        etVisualizacaoDetalhadaOpcaoC = findViewById(R.id.etVisualizacaoDetalhadaOpcaoC);
        etVisualizacaoDetalhadaOpcaoD = findViewById(R.id.etVisualizacaoDetalhadaOpcaoD);
        etVisualizacaoDetalhadaOpcaoE = findViewById(R.id.etVisualizacaoDetalhadaOpcaoE);
        etVisualizacaoDetalhadaRespostaCerta = findViewById(R.id.etVisualizacaoDetalhadaRespostaCerta);
        etVisualizacaoDetalhadaRespostaEscolhida = findViewById(R.id.etVisualizacaoDetalhadaRespostaEscolhida);

        Intent it = getIntent();
        if(it != null && it.hasExtra("pergunta")){
            pergunta = (Pergunta) getIntent().getSerializableExtra("pergunta");
            etVisualizacaoDetalhadaEnunciado.setText(pergunta.getEnunciado());
            etVisualizacaoDetalhadaOpcaoA.setText(pergunta.getOpcaoA());
            etVisualizacaoDetalhadaOpcaoB.setText(pergunta.getOpcaoB());
            etVisualizacaoDetalhadaOpcaoC.setText(pergunta.getOpcaoC());
            etVisualizacaoDetalhadaOpcaoD.setText(pergunta.getOpcaoD());
            etVisualizacaoDetalhadaOpcaoE.setText(pergunta.getOpcaoE());
            etVisualizacaoDetalhadaRespostaEscolhida.setText(Character.toString(pergunta.getOpcaoEscolhida()));
            etVisualizacaoDetalhadaRespostaCerta.setText(Character.toString(pergunta.getAlternativaCorreta()));
        }

    }

}