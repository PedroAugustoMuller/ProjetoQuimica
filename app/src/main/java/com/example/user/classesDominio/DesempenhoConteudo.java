package com.example.user.classesDominio;

import com.example.user.classesDominio.Conteudo;
import com.example.user.componente.NivelConteudoEnum;

import java.io.Serializable;

public class DesempenhoConteudo implements Serializable {
    private int idDesempenhoConteudo;
    private Conteudo conteudo;
    private int quantidadePerguntas;
    private int quantidadeAcertos;
    private int quantidadeErros;
    private float pontuacaoConteudo;
    private NivelConteudoEnum nivelConteudoEnum;

    public DesempenhoConteudo(int idDesempenhoConteudo, Conteudo conteudo, int quantidadePerguntas, int quantidadeAcertos, int quantidadeErros, float pontuacaoConteudo) {
        this.idDesempenhoConteudo = idDesempenhoConteudo;
        this.conteudo = conteudo;
        this.quantidadePerguntas = quantidadePerguntas;
        this.quantidadeAcertos = quantidadeAcertos;
        this.quantidadeErros = quantidadeErros;
        this.pontuacaoConteudo = pontuacaoConteudo;
    }

    public DesempenhoConteudo(int idDesempenhoConteudo, Conteudo conteudo, int quantidadePerguntas, int quantidadeAcertos, int quantidadeErros, float pontuacaoConteudo, NivelConteudoEnum nivelConteudoEnum) {
        this.idDesempenhoConteudo = idDesempenhoConteudo;
        this.conteudo = conteudo;
        this.quantidadePerguntas = quantidadePerguntas;
        this.quantidadeAcertos = quantidadeAcertos;
        this.quantidadeErros = quantidadeErros;
        this.pontuacaoConteudo = pontuacaoConteudo;
        this.nivelConteudoEnum = nivelConteudoEnum;
    }

    public DesempenhoConteudo(Conteudo conteudo, int quantidadePerguntas, int quantidadeAcertos, int quantidadeErros, float pontuacaoConteudo, NivelConteudoEnum nivelConteudoEnum) {
        this.conteudo = conteudo;
        this.quantidadePerguntas = quantidadePerguntas;
        this.quantidadeAcertos = quantidadeAcertos;
        this.quantidadeErros = quantidadeErros;
        this.pontuacaoConteudo = pontuacaoConteudo;
        this.nivelConteudoEnum = nivelConteudoEnum;
    }

    public NivelConteudoEnum getNivelConteudoEnum() {
        return nivelConteudoEnum;
    }

    public void setNivelConteudoEnum(NivelConteudoEnum nivelConteudoEnum) {
        this.nivelConteudoEnum = nivelConteudoEnum;
    }

    public Conteudo getConteudo() {
        return conteudo;
    }

    public void setConteudos(Conteudo conteudo) {
        this.conteudo = conteudo;
    }

    public int getQuantidadePerguntas() {
        return quantidadePerguntas;
    }

    public void setQuantidadePerguntas(int quantidadeConteudos) {
        this.quantidadePerguntas = quantidadeConteudos;
    }

    public int getQuantidadeAcertos() {
        return quantidadeAcertos;
    }

    public void setQuantidadeAcertos(int quantidadeAcertos) {
        this.quantidadeAcertos = quantidadeAcertos;
    }

    public int getQuantidadeErros() {
        return quantidadeErros;
    }

    public void setQuantidadeErros(int quantidadeErros) {
        this.quantidadeErros = quantidadeErros;
    }

    public float getPontuacaoConteudo() {
        return pontuacaoConteudo;
    }

    public int getIdDesempenhoConteudo() {
        return idDesempenhoConteudo;
    }

    public void setIdDesempenhoConteudo(int idDesempenhoConteudo) {
        this.idDesempenhoConteudo = idDesempenhoConteudo;
    }

    public void setConteudo(Conteudo conteudo) {
        this.conteudo = conteudo;
    }

    public void setPontuacaoConteudo(float pontuacaoConteudo) {
        this.pontuacaoConteudo = pontuacaoConteudo;
    }
}