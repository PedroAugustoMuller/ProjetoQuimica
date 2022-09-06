package com.example.user.componente;

import android.graphics.drawable.Drawable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;


import com.example.user.classesDominio.Pergunta;
import com.example.user.projetoquimica.R;

import java.util.ArrayList;


public class RelatorioPerguntaAdapter extends RecyclerView.Adapter<RelatorioPerguntaAdapter.MyViewHolder> {
    private ArrayList<Pergunta> listaPerguntas;
    private PerguntaOnClickListener perguntaOnClickListener;


    public RelatorioPerguntaAdapter(ArrayList<Pergunta> listaPerguntas, PerguntaOnClickListener perguntaOnClickListener) {
        this.listaPerguntas = listaPerguntas;
        this.perguntaOnClickListener = perguntaOnClickListener;
    }

    @Override
    public RelatorioPerguntaAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_list_row_pergunta, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final RelatorioPerguntaAdapter.MyViewHolder holder, final int position) {
        Pergunta perg = listaPerguntas.get(position);
        holder.tvRelatorioPergunta.setText("Enunciado: "+perg.getEnunciado());
        holder.tvRelatorioConteudoPergunta.setText("Conteudo: "+perg.getConteudo().getNomeConteudo());
        String respostaCorreta = String.valueOf(perg.getAlternativaCorreta());
        String respostaEscolhida = String.valueOf(perg.getOpcaoEscolhida());
        holder.tvRelatorioRespostaCorreta.setText("Reposta Correta: "+ respostaCorreta);
        holder.tvRelatorioRespostaEscolhida.setText("Resposta Escolhida: "+ respostaEscolhida);
        if (respostaCorreta.equals(respostaEscolhida)){
            holder.ivRelatorioResultado.setImageDrawable();
        } else{
            holder.ivRelatorioResultado.setVisibility(View.INVISIBLE);
        }
        /* CUIDADO: .setText() precisa sempre de String. Se for outro tipo de dado, deve ser feita a conversão com o String.valueOf() */

        // clique no item do cliente
        if (perguntaOnClickListener != null) {
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    perguntaOnClickListener.onClickPergunta(holder.itemView, position);
                }
            });
        }

    }

    @Override
    public int getItemCount() {
        return listaPerguntas.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView tvRelatorioPergunta, tvRelatorioConteudoPergunta, tvRelatorioRespostaCorreta, tvRelatorioRespostaEscolhida;
        ImageView ivRelatorioResultado;

        public MyViewHolder(View itemView) {
            super(itemView);
            //TextView
            tvRelatorioPergunta = itemView.findViewById(R.id.tvRelatorioPergunta);
            tvRelatorioConteudoPergunta = itemView.findViewById(R.id.tvRelatorioConteudoPergunta);
            tvRelatorioRespostaCorreta = itemView.findViewById(R.id.tvRelatorioRespostaCorreta);
            tvRelatorioRespostaEscolhida = itemView.findViewById(R.id.tvRelatorioRepostaEscolhida);
            //ImageView
            ivRelatorioResultado = itemView.findViewById(R.id.ivRelatorioResultado);
        }
    }

    public interface PerguntaOnClickListener {
        void onClickPergunta(View view, int position);
    }
}


