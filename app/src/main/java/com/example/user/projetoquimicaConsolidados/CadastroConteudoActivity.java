package com.example.user.projetoquimicaConsolidados;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.user.banco.ConteudoDB;
import com.example.user.banco.InformacoesApp;
import com.example.user.classesDominio.Conteudo;

public class CadastroConteudoActivity extends AppCompatActivity{
    EditText etNomeConteudo;
    Button bSalvarConteudo, bCancelarConteudo;
    InformacoesApp informacoesApp;

    ConteudoDB conteudoDB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro_conteudo);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        etNomeConteudo = findViewById(R.id.etNomeConteudo);
        bSalvarConteudo = findViewById(R.id.bSalvarConteudo);
        bCancelarConteudo = findViewById(R.id.bCancelarConteudo);
        informacoesApp = (InformacoesApp)getApplicationContext();

        conteudoDB = new ConteudoDB(getApplicationContext());
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        bSalvarConteudo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!etNomeConteudo.getText().toString().equals("")){
                    String nomeConteudo = etNomeConteudo.getText().toString();

                    Conteudo meuConteudo = new Conteudo(nomeConteudo, informacoesApp.getTipoConteudo());
                    Log.d("Teste", "Tipo do conteudo: " + informacoesApp.getTipoConteudo());

                    String retornoConteudo = conteudoDB.insereConteudo(meuConteudo);
                    limpaCampos();
                    Toast.makeText(CadastroConteudoActivity.this, retornoConteudo, Toast.LENGTH_SHORT).show();
                } else {
                    etNomeConteudo.setError("Informe o nome do conteúdo");
                    etNomeConteudo.requestFocus();
                }

            }
        });
        bCancelarConteudo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                limpaCampos();
            }
        });
    }

        public void limpaCampos(){
            etNomeConteudo.setText("");

        }

}
