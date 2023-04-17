package com.example.user.componente;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.user.classesDominio.DesempenhoConteudo;
import com.example.user.classesDominio.Feedback;
import com.example.user.classesDominio.NivelConteudo;
import com.example.user.projetoquimicaConsolidados.R;

import java.util.List;

public class DesempenhoFeedbackAdapter extends RecyclerView.Adapter<DesempenhoFeedbackAdapter.MyViewHolder> {
    private List<DesempenhoConteudo> listaDesempenhoConteudos;
    private List<NivelConteudo> listaNivelConteudos;
    private List<Feedback> listaFeedback;
    private DesempenhoFeedbackOnClickListener desempenhoFeedbackOnClickListener;
    private Context context;

    public DesempenhoFeedbackAdapter(List<DesempenhoConteudo> listaDesempenhoConteudos, List<NivelConteudo> listaNivelConteudos, List<Feedback> listaFeedback, DesempenhoFeedbackOnClickListener desempenhoFeedbackOnClickListener, Context context) {
        this.listaDesempenhoConteudos = listaDesempenhoConteudos;
        this.listaNivelConteudos = listaNivelConteudos;
        this.listaFeedback = listaFeedback;
        this.desempenhoFeedbackOnClickListener = desempenhoFeedbackOnClickListener;
        this.context = context;
    }

    @Override
    public DesempenhoFeedbackAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType){
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_list_row_desempenho, parent, false);

        return new DesempenhoFeedbackAdapter.MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final DesempenhoFeedbackAdapter.MyViewHolder holder, final int position) {
        DesempenhoConteudo meuDesempenhoConteudo = listaDesempenhoConteudos.get(position);
        NivelConteudo meuNivelConteudo = listaNivelConteudos.get(position);
        Feedback meuFeedback = listaFeedback.get(position);
        holder.tvNomeConteudo.setText(meuDesempenhoConteudo.getConteudo().getNomeConteudo());
        holder.tvPontuacaoConteudo.setText("Pontuação: " + meuDesempenhoConteudo.getPontuacaoConteudo());
        //NÃO - holder.tvPontuacaoConteudo.setText(meuNivelConteudo.getNivel().name()); //senão funcionar usar "toString()"4
        //obter qual o icone do nivel conteudo (img)
        // vai mostrar o nível atual
        holder.imImagemNivel.setImageDrawable(meuNivelConteudo.getImagemNivel(this.context));

        //Verificar a quantidade de níveis pulados do feedback
        int quantidadeVidas = meuNivelConteudo.getVidas();
        //holder.imVidaConteudo.setImageDrawable();
        holder.tvQuantidadeVidas.setText(""+quantidadeVidas);
        if (meuFeedback.getNiveisAvancados() > 0) {
            holder.imImagemNivel.setImageDrawable(meuNivelConteudo.getImagemNivelParametro(meuFeedback.getNivelAnterior(), this.context)); // mostrar o anterior para indicar que atualizou! Esse comportamento?
            holder.imImagemUpgrade.setImageDrawable(meuNivelConteudo.getImagemUpgrade(this.context));
        } else if (meuFeedback.getNiveisAvancados() < 0){
            holder.imImagemNivel.setImageDrawable(meuNivelConteudo.getImagemNivelParametro(meuFeedback.getNivelAnterior(), this.context));
            holder.imImagemUpgrade.setImageDrawable(this.context.getResources().getDrawable(R.drawable.ic_downgrade)); //PEDRO - Depois adicionar a imagem de downgrade em NivelConteudo e NivelConteudoDB
        } else {
            holder.imImagemNivel.setImageDrawable(meuNivelConteudo.getImagemNivel(this.context));
        }

        //clique no item de conteudo
        if (desempenhoFeedbackOnClickListener != null){
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    desempenhoFeedbackOnClickListener.onClickDesempenhoFeedback(holder.itemView,position);
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return listaDesempenhoConteudos.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        ImageView imImagemNivel, imImagemUpgrade, imVidaConteudo;
        TextView tvNomeConteudo, tvPontuacaoConteudo, tvQuantidadeVidas;
        public MyViewHolder(View itemView) {
            super(itemView);
            tvNomeConteudo = (TextView) itemView.findViewById(R.id.tvNomeConteudo);
            tvPontuacaoConteudo = (TextView) itemView.findViewById(R.id.tvPontuacaoConteudo);
            imImagemNivel = (ImageView) itemView.findViewById(R.id.imImagemNivel);
            imImagemUpgrade = (ImageView) itemView.findViewById(R.id.imUpgradeNivel);
            imVidaConteudo = itemView.findViewById(R.id.imVidaConteudo);
            tvQuantidadeVidas = itemView.findViewById(R.id.tvQuantidadeVidas);
        }
    }

    public interface DesempenhoFeedbackOnClickListener {
        public void onClickDesempenhoFeedback(View view, int position);
    }
}

