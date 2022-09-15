package com.example.user.classesDominio;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.media.Image;
import android.widget.Toast;

import com.example.user.componente.NivelConteudoEnum;
import com.example.user.projetoquimica.R;

import java.io.Serializable;
import java.util.ArrayList;

import static com.example.user.componente.NivelConteudoEnum.BRONZE;

public class NivelConteudo implements Serializable {
    private int idNivelConteudo;
    private NivelConteudoEnum nivel;
    private Usuario usuario;
    private Conteudo conteudo;
    private Image imagem;
    private int tentativas; //PEDRO ADICIONEI NUMERO DE TENTATIVAS

    //construtor 1 - sem id
    public NivelConteudo(NivelConteudoEnum nivel, Usuario usuario, Conteudo conteudo, int tentativas) {
        this.idNivelConteudo = -1;
        this.nivel = nivel;
        this.usuario = usuario;
        this.conteudo = conteudo;
        this.tentativas = tentativas;
    }

    //construtor 2 - com id
    public NivelConteudo(int idNivelConteudo, NivelConteudoEnum nivel, Usuario usuario, Conteudo conteudo, int tentativas) {
        this.idNivelConteudo = idNivelConteudo;
        this.nivel = nivel;
        this.usuario = usuario;
        this.conteudo = conteudo;
        this.tentativas = tentativas;
    }

    public NivelConteudo(NivelConteudoEnum nivel, Conteudo conteudo, Image imagem) {
        this.nivel = nivel;
        this.conteudo = conteudo;
        this.imagem = imagem;
    }

    // método para incrementar UM nível
    // retorno: 0- erro ao atualizar, 1- atualizado e 2- não atualizou por estar no último nível
    // método retorna quantidade de níveis incrementados
    public int incrementaUmNivel() {
        int retorno = -1;
        NivelConteudoEnum novoNivel = obtemIncrementoUmNivel();
        if (this.nivel != NivelConteudoEnum.DIAMANTE){
            this.nivel = novoNivel;
            retorno = 1;
        } else {
            retorno = 0;
        }
        return retorno;
    }
    //PEDRO DECAINIVEL
    public int decaiUmNivel(){
        int retorno =-1;
        NivelConteudoEnum novoNivel = obtemDecaiUmNivel();
        if (this.nivel != NivelConteudoEnum.COBRE){
            this.nivel = novoNivel;
            retorno = 1;
        } else {
            retorno = 0;
        }
        return retorno;
    }

    public void incrementaTentativas(){
        this.tentativas++;
    }
    public void decrementaTentativas(){
        if (this.tentativas > 0){
            this.tentativas--;
        } else {
            this.tentativas = 0;
        }
    }
    public void zeraTentativas(){
        this.tentativas = 0;
    }

    // obtem incrementaUmNivel
    public NivelConteudoEnum obtemIncrementoUmNivel() {
        NivelConteudoEnum retorno = null;
        if (this.nivel == NivelConteudoEnum.COBRE) {
            retorno = NivelConteudoEnum.BRONZE;
        } else if (this.nivel == BRONZE) {
            retorno = NivelConteudoEnum.PRATA;
        } else if (this.nivel == NivelConteudoEnum.PRATA) {
            retorno = NivelConteudoEnum.OURO;
        } else if (this.nivel == NivelConteudoEnum.OURO) {
            retorno = NivelConteudoEnum.DIAMANTE;
        } else if (this.nivel == NivelConteudoEnum.DIAMANTE) {
            retorno = null;
        }
        return retorno;
    }

    // método para o diagnóstico (incremento)
    // lembrar que pela regra do diagnostico nem sempre irá incrementar dois níveis (ex: quando estiver
    // no nível ouro)
    // retorno: 0- erro ao atualizar, 1- incrementou dois níveis, 2- incrementou um nível
    // método retorna quantidade de níveis incrementados
    public int incrementaDoisNiveis() {
        int retorno = 0;
        if (this.nivel == NivelConteudoEnum.COBRE || this.nivel == BRONZE || this.nivel == NivelConteudoEnum.PRATA){
            NivelConteudoEnum novoNivel = obtemIncrementoDoisNiveis();
            this.nivel = novoNivel;
            retorno = 2;
        } else if (this.nivel == NivelConteudoEnum.OURO) {
            NivelConteudoEnum novoNivel = obtemIncrementoUmNivel();
            this.nivel = novoNivel;
            retorno = 1;
        } else {
            retorno = 0;
        }
        return retorno;
    }

    // obtem incrementaDoisNiveis
    public NivelConteudoEnum obtemIncrementoDoisNiveis() {
        NivelConteudoEnum retorno = null;
        if (this.nivel == NivelConteudoEnum.COBRE) {
            retorno = NivelConteudoEnum.PRATA;
        } else if (this.nivel == BRONZE) {
            retorno = NivelConteudoEnum.OURO;
        } else if (this.nivel == NivelConteudoEnum.PRATA) {
            retorno = NivelConteudoEnum.DIAMANTE;
        } else if (this.nivel == NivelConteudoEnum.OURO) {
            retorno = NivelConteudoEnum.DIAMANTE;
        } else if (this.nivel == NivelConteudoEnum.DIAMANTE) {
            retorno = null;
        }
        return retorno;
    }
    //PEDRO OBTEMDECAINIVEL
    public NivelConteudoEnum obtemDecaiUmNivel(){
        NivelConteudoEnum retorno = null;
        if (this.nivel == NivelConteudoEnum.DIAMANTE){
            retorno = NivelConteudoEnum.OURO;
        } else if (this.nivel == NivelConteudoEnum.OURO){
            retorno = NivelConteudoEnum.PRATA;
        } else if (this.nivel == NivelConteudoEnum.PRATA){
            retorno = NivelConteudoEnum.BRONZE;
        } else if (this.nivel == NivelConteudoEnum.BRONZE){
            retorno = NivelConteudoEnum.COBRE;
        } else {
            retorno = null;
        }
        return retorno;
    }

    public Drawable getImagemUpgrade(Context context){
        Drawable drawable = context.getResources().getDrawable(R.drawable.ic_upgrade);
        return drawable;
    }

    //retorna a imagem conforme o nivel (ícone)
    public Drawable getImagemNivel(Context context){
        Drawable drawable = null;
        if (this.nivel == NivelConteudoEnum.COBRE) {
            drawable = context.getResources().getDrawable(R.drawable.ic_cobre); /*imagem ícone cobre*/
        } else if (this.nivel == BRONZE) {
            drawable = context.getResources().getDrawable(R.drawable.ic_bronze); /*imagem ícone bronze*/
        } else if (this.nivel == NivelConteudoEnum.PRATA) {
            drawable = context.getResources().getDrawable(R.drawable.ic_prata); /*imagem ícone prata*/
        } else if (this.nivel == NivelConteudoEnum.OURO) {
            drawable = context.getResources().getDrawable(R.drawable.ic_ouro); /*imagem ícone ouro*/
        } else if (this.nivel == NivelConteudoEnum.DIAMANTE) {
            drawable = context.getResources().getDrawable(R.drawable.ic_diamante); /*imagem ícone diamante*/
        }
        return drawable;
    }


    // retorna o ícone do nivel conteudo (ENUM) recebido como parâmetro
    public Drawable getImagemNivelParametro(NivelConteudoEnum nivelParametro, Context context) {
        Drawable drawable = null;
        if (nivelParametro == NivelConteudoEnum.COBRE) {
            drawable = context.getResources().getDrawable(R.drawable.ic_cobre); /*imagem ícone cobre*/
        } else if (nivelParametro == BRONZE) {
            drawable = context.getResources().getDrawable(R.drawable.ic_bronze); /*imagem ícone cobre*/
        } else if (nivelParametro == NivelConteudoEnum.PRATA) {
            drawable = context.getResources().getDrawable(R.drawable.ic_prata); /*imagem ícone bronze*/
        } else if (nivelParametro == NivelConteudoEnum.OURO) {
            drawable = context.getResources().getDrawable(R.drawable.ic_ouro); /*imagem ícone ouro*/
        } else if (nivelParametro == NivelConteudoEnum.DIAMANTE) {
            drawable = context.getResources().getDrawable(R.drawable.ic_diamante); /*imagem ícone diamante*/
        }
        return drawable;
    }

    //retorna a imagem conforme o nivel (lateralizado)
    public Drawable getImagemNivelAlternativo(Context context){
        Drawable drawable = null;
        if (this.nivel == NivelConteudoEnum.COBRE) {
            drawable = context.getResources().getDrawable(R.drawable.vis_nivel_cobre); /*imagem ícone cobre*/
        } else if (this.nivel == BRONZE) {
            drawable = context.getResources().getDrawable(R.drawable.vis_nivel_bronze); /*imagem ícone bronze*/
        } else if (this.nivel == NivelConteudoEnum.PRATA) {
            drawable = context.getResources().getDrawable(R.drawable.vis_nivel_prata); /*imagem ícone prata*/
        } else if (this.nivel == NivelConteudoEnum.OURO) {
            drawable = context.getResources().getDrawable(R.drawable.vis_nivel_ouro); /*imagem ícone ouro*/
        } else if (this.nivel == NivelConteudoEnum.DIAMANTE) {
            drawable = context.getResources().getDrawable(R.drawable.vis_nivel_diamante); /*imagem ícone diamante*/
        }
        return drawable;
    }

    //retorna a imagem conforme o nivel (caminho)
    public Drawable getImagemNivelCaminho(Context context){
        Drawable drawable = null;
        if (this.nivel == NivelConteudoEnum.COBRE) {
            drawable = context.getResources().getDrawable(R.drawable.caminho_cobre); /*imagem ícone cobre*/
        } else if (this.nivel == BRONZE) {
            drawable = context.getResources().getDrawable(R.drawable.caminho_bronze); /*imagem ícone bronze*/
        } else if (this.nivel == NivelConteudoEnum.PRATA) {
            drawable = context.getResources().getDrawable(R.drawable.caminho_prata); /*imagem ícone prata*/
        } else if (this.nivel == NivelConteudoEnum.OURO) {
            drawable = context.getResources().getDrawable(R.drawable.caminho_ouro); /*imagem ícone ouro*/
        } else if (this.nivel == NivelConteudoEnum.DIAMANTE) {
            drawable = context.getResources().getDrawable(R.drawable.caminho_diamante); /*imagem ícone diamante*/
        }
        return drawable;
    }





    public int getIdNivelConteudo() { return idNivelConteudo; }

    public void setIdNivelConteudo(int idNivelConteudo) { this.idNivelConteudo = idNivelConteudo; }

    public NivelConteudoEnum getNivel() { return nivel; }

    public void setNivel(NivelConteudoEnum nivel) { this.nivel = nivel; }

    public Usuario getUsuario() { return usuario; }

    public void setUsuario(Usuario usuario) { this.usuario = usuario; }

    public Conteudo getConteudo() { return conteudo; }

    public void setConteudo(Conteudo conteudo) { this.conteudo = conteudo; }

    public Image getImagem() {
        return imagem;
    }

    public void setImagem(Image imagem) {
        this.imagem = imagem;
    }

    public int getTentativas() {
        return tentativas;
    }

    public void setTentativas(int tentativas) {
        this.tentativas = tentativas;
    }
}


