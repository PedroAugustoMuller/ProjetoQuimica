package com.example.user.componente;

import android.support.v7.widget.RecyclerView;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.user.classesDominio.Conteudo;
import com.example.user.projetoquimicaConsolidados.R;

import java.util.ArrayList;

public class ListaConteudosAdapter extends RecyclerView.Adapter<ListaConteudosAdapter.MyViewHolder> {
    private ArrayList<Conteudo> lstConteudos;
    private ConteudoOnClickListener conteudoOnClickListener;


    public ListaConteudosAdapter(ArrayList<Conteudo> lstConteudos,ConteudoOnClickListener conteudoOnClickListener) {
        this.lstConteudos = lstConteudos;
        this.conteudoOnClickListener = conteudoOnClickListener;
    }

    @Override
    public ListaConteudosAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_list_row_conteudos, parent, false);

        return new ListaConteudosAdapter.MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final ListaConteudosAdapter.MyViewHolder holder, final int position) {
        Conteudo cont = lstConteudos.get(position);
        holder.tvItemListRowConteudosNomeConteudo.setText(cont.getNomeConteudo());
        /* CUIDADO: .setText() precisa sempre de String. Se for outro tipo de dado, deve ser feita a conversão com o String.valueOf() */

        // clique no item do cliente
        if (conteudoOnClickListener != null) {
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    conteudoOnClickListener.onClickConteudo(holder.itemView, position);
                }
            });
        }

    }

    @Override
    public int getItemCount() {
        return lstConteudos.size();
    }


    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView tvItemListRowConteudosNomeConteudo;

        public MyViewHolder(View itemView) {
            super(itemView);
            tvItemListRowConteudosNomeConteudo = (TextView) itemView.findViewById(R.id.tvItemListRowConteudosNomeConteudo);
        }
    }

    public interface ConteudoOnClickListener {
        public void onClickConteudo(View view, int position);
    }
}
