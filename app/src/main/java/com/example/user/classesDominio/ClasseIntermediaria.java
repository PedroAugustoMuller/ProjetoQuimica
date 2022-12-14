package com.example.user.classesDominio;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.example.user.banco.Conexao;
import com.example.user.banco.NivelConteudoDB;
import com.example.user.banco.PerguntaDB;
import com.example.user.componente.NivelConteudoEnum;

import java.util.ArrayList;
import java.util.Date;

public class ClasseIntermediaria {
    Context context;
    NivelConteudoDB nivelConteudoDB;

    public ClasseIntermediaria(Context context) {
        this.context = context;
    }

    public ArrayList<Pergunta> carregaQuantPerguntasPorConteudo(ArrayList<NivelConteudo> listaNivelConteudos, int[][] quantidadePerguntasPorConteudo) {
        ArrayList<Pergunta> listaPerguntas;

        for (int x = 0; x < listaNivelConteudos.size(); x++) {
            NivelConteudo meuNivelConteudo = listaNivelConteudos.get(x);

            int quantidadeFaceis = 0;
            int quantidadeMedias = 0;
            int quantidadeDificeis = 0;

            Log.d("Teste", "Nivel: " + meuNivelConteudo.getNivel());
            if (meuNivelConteudo.getNivel() == NivelConteudoEnum.COBRE) {
                quantidadeFaceis = Math.round(quantidadePerguntasPorConteudo[x][0] * 0.7f);
                quantidadeMedias = quantidadePerguntasPorConteudo[x][0] - quantidadeFaceis;
            } else if (meuNivelConteudo.getNivel() == NivelConteudoEnum.BRONZE) {
                quantidadeFaceis = Math.round(quantidadePerguntasPorConteudo[x][0] * 0.5f);
                quantidadeDificeis = Math.round(quantidadePerguntasPorConteudo[x][0] * 0.1f);
                quantidadeMedias = quantidadePerguntasPorConteudo[x][0] - (quantidadeFaceis + quantidadeDificeis);
            } else if (meuNivelConteudo.getNivel() == NivelConteudoEnum.PRATA) {
                quantidadeFaceis = Math.round(quantidadePerguntasPorConteudo[x][0] * 0.3f);
                quantidadeDificeis = Math.round(quantidadePerguntasPorConteudo[x][0] * 0.3f);
                quantidadeMedias = quantidadePerguntasPorConteudo[x][0] - (quantidadeFaceis + quantidadeDificeis);
            } else if (meuNivelConteudo.getNivel() == NivelConteudoEnum.OURO) {
                quantidadeDificeis = Math.round(quantidadePerguntasPorConteudo[x][0] * 0.5f);
                quantidadeFaceis = Math.round(quantidadePerguntasPorConteudo[x][0] * 0.1f);
                quantidadeMedias = quantidadePerguntasPorConteudo[x][0] - (quantidadeDificeis + quantidadeFaceis);
            } else if (meuNivelConteudo.getNivel() == NivelConteudoEnum.DIAMANTE) {
                quantidadeDificeis = Math.round(quantidadePerguntasPorConteudo[x][0] * 0.7f);
                quantidadeMedias = quantidadePerguntasPorConteudo[x][0] - quantidadeDificeis;
            }
            quantidadePerguntasPorConteudo[x][1] = quantidadeFaceis;
            quantidadePerguntasPorConteudo[x][2] = quantidadeMedias;
            quantidadePerguntasPorConteudo[x][3] = quantidadeDificeis;
            Log.d("Teste", "Conteudo: " + x + "-" + listaNivelConteudos.get(x).getConteudo().getNomeConteudo() + ": Total " + quantidadePerguntasPorConteudo[x][0] + ", faceis " + quantidadePerguntasPorConteudo[x][1] + ", m??dias " + quantidadePerguntasPorConteudo[x][2] + ", dificeis " + quantidadePerguntasPorConteudo[x][3]);
        }
        PerguntaDB perguntaDB = new PerguntaDB(this.context);
        listaPerguntas = perguntaDB.buscaPerguntasPorConteudosUnionAll(listaNivelConteudos, quantidadePerguntasPorConteudo);
        return listaPerguntas;
    }

    /*public DesempenhoQuestionario ClassificaQuantitativoConteudo (Conteudo meuConteudo, DesempenhoQuestionario meuDesempenhoQuestionario){
        if(meuConteudo.getTipoConteudo() < 4 ){
            calculaDesempenhoQuestionario(meuConteudo);
        }
    }*/

    public DesempenhoQuestionario calculaDesempenhoQuestionario(ArrayList<NivelConteudo> listaNivelConteudos, int[][] quantidadePerguntasPorConteudo, ArrayList<Pergunta> listaPerguntas, Usuario meuUsuario, ArrayList<Feedback> listaFeedbacks) {
        int acertos = 0;
        int erros = 0;
        nivelConteudoDB = new NivelConteudoDB(context);
        ArrayList<NivelConteudo> listaNivelConteudoParaAtualizar = new ArrayList<>();
        ArrayList<NivelConteudo> listaNivelConteudoParaDecair = new ArrayList<>();

        // criando o objeto desempenho questionario para adicionar os desempenhos por conteudo
        // testar se o metodo "System...." volta uma data ou data com hora
        Date dataAtual = new Date(System.currentTimeMillis());
        Log.d("Teste", "data = " + dataAtual);
        DesempenhoQuestionario desempenhoQuestionario = new DesempenhoQuestionario
                (dataAtual, 1); //recebe 1 no tipo desempenho pois ?? um quiz

        int inicio = 0;

        // navegando na lista de conte??dos para saber as quantidades e tamb??m os n??veis de cada um
        for (int indice = 0; indice < listaNivelConteudos.size(); indice++) {
            // obtendo o nivel conteudo
            NivelConteudo meuNivelConteudo = listaNivelConteudos.get(indice);

            // definindo os valores de faceis, medias e dificeis de acordo com o n??vel que o usu??rio est??
            float valorFaceis = 0;
            float valorMedias = 0;
            float valorDificeis = 0;
            //TESTE PRA N??O DIVIDIR POR ZERO - PEDRO
            if (quantidadePerguntasPorConteudo[indice][1] == 0){
                quantidadePerguntasPorConteudo[indice][1] = 1;
            } else if ( quantidadePerguntasPorConteudo[indice][2] == 0){
                quantidadePerguntasPorConteudo[indice][2] = 1;
           } else if (quantidadePerguntasPorConteudo[indice][3] == 0){
               quantidadePerguntasPorConteudo[indice][3] = 1;
           }
            /*float percentualAuxiliar;*/
            if (meuNivelConteudo.getNivel() == NivelConteudoEnum.COBRE) { //PEDRO - o problema com as notas acontece pois estamos dividindo int por int
                valorFaceis = 68f / quantidadePerguntasPorConteudo[indice][1];
                valorMedias = 32f / quantidadePerguntasPorConteudo[indice][2];
            } else if (meuNivelConteudo.getNivel() == NivelConteudoEnum.BRONZE) {
                valorFaceis = 45f / quantidadePerguntasPorConteudo[indice][1];
                valorMedias = 35f / quantidadePerguntasPorConteudo[indice][2];
                valorDificeis = 20f / quantidadePerguntasPorConteudo[indice][3];
            } else if (meuNivelConteudo.getNivel() == NivelConteudoEnum.PRATA) {
                valorFaceis = 30f / quantidadePerguntasPorConteudo[indice][1];
                valorMedias = 40f / quantidadePerguntasPorConteudo[indice][2];
                valorDificeis = 30f / quantidadePerguntasPorConteudo[indice][3];
            } else if (meuNivelConteudo.getNivel() == NivelConteudoEnum.OURO) {
                valorFaceis = 20f / quantidadePerguntasPorConteudo[indice][1];
                valorMedias = 35f / quantidadePerguntasPorConteudo[indice][2];
                valorDificeis = 45f / quantidadePerguntasPorConteudo[indice][3];
            } else if (meuNivelConteudo.getNivel() == NivelConteudoEnum.DIAMANTE) {
                valorMedias =  32f  / quantidadePerguntasPorConteudo[indice][2];
                valorDificeis = 68f / quantidadePerguntasPorConteudo[indice][3];
            }
            Log.d("Teste", "Percentual valor f??ceis: " + valorFaceis + ", percentual m??dias: " + valorMedias + ", percentual d??ficeis: " + valorDificeis);
            float pontuacaoConteudo = 0; //PEDRO - Talvez mudar para double

            // fazendo o controle para saber at?? onde ele deve ir nas perguntas para esse determinado conte??do
            int fim = inicio + quantidadePerguntasPorConteudo[indice][0];
            // navegando nas perguntas do conte??do em quest??o
            for (int x = inicio; x < fim; x++) {
                // obtendo a pergunta do conte??do
                Pergunta minhaPergunta = listaPerguntas.get(x);

                // verificando se acertou ou n??o
                if (minhaPergunta.getOpcaoEscolhida() == minhaPergunta.getAlternativaCorreta()) {
                    // acertou
                    acertos++;
                    // verificando a dificuldade para saber o valor a ser acrescido (pesos diferentes)
                    if (minhaPergunta.getNivelDificuldade() == 1) {
                        pontuacaoConteudo = pontuacaoConteudo + valorFaceis;
                    } else if (minhaPergunta.getNivelDificuldade() == 2) {
                        pontuacaoConteudo = pontuacaoConteudo + valorMedias;
                    } else if (minhaPergunta.getNivelDificuldade() == 3) {
                        pontuacaoConteudo = pontuacaoConteudo + valorDificeis;
                    }
                } else {
                    // errou
                    erros++;
                }

                float media = 0;
                /*TESTE*/
                /*if(meuConteudo.getQuantidade() == 0){
                    meuConteudo.setQuantidade(1);
                } else if(meuConteudo.getQuantidade() <= 5){
                    int quantidade = 1;
                    meuConteudo.calculaPrecisao(meuConteudo, pontuacaoConteudo);
                    quantidade++;
                    meuConteudo.setQuantidade(quantidade);
                } else if(meuConteudo.getQuantidade() > 5){
                    if(meuConteudo.getPorcentagemNivel() >=0 || meuConteudo.getPorcentagemNivel() <=100){
                        float soma = meuConteudo.getPrecisao();
                        if(pontuacaoConteudo < 65){
                            soma = - soma;
                        }
                        if((meuConteudo.getPorcentagemNivel() + soma) >= 0 && (meuConteudo.getPorcentagemNivel() + soma) < 100){
                            meuConteudo.setPorcentagemNivel((int) (meuConteudo.getPorcentagemNivel() + soma));
                        } else if((meuConteudo.getPorcentagemNivel() + soma) > 100){
                            float somaFinal = soma - meuConteudo.getPorcentagemNivel();
                            // passou para o proximo nivel com essa soma final de pontos no proximo nivel
                        } else if ((meuConteudo.getPorcentagemNivel() + soma) < 0){
                            if(meuConteudo.getCaiuNivel() == 0){
                                meuConteudo.setCaiuNivel(1);
                                meuConteudo.setPorcentagemNivel(0);
                            } else {
                                if(meuNivelConteudo.getNivel() == NivelConteudoEnum.COBRE){
                                    // fica no cobre com 0 pontos
                                } else {
                                    float somaFinal = 100 + soma;
                                    // caiu nivel e ficou com essa soma final de pontos no nivel anterior
                                }
                            }
                        }
                    }
                }*/
            }

            Log.d("Teste", "Pontua????o conte??do: " + pontuacaoConteudo);
            // atualizando o novo in??cio
            inicio = fim;

            DesempenhoConteudo desempenhoConteudo = new DesempenhoConteudo(meuNivelConteudo.getConteudo(), quantidadePerguntasPorConteudo[indice][0], acertos, erros, pontuacaoConteudo);

            // adicionando no desempenho do question??rio
            desempenhoQuestionario.getListaDesempenhoConteudos().add(desempenhoConteudo);

            // reiniciando os acertos e erros
            acertos = 0;
            erros = 0;

            // verificando se pontua????o conte??do fez com que devesse pular de n??vel
            Log.d("Teste", "N??vel antigo: " + meuNivelConteudo.getNivel());
            //montar o feedback (parecido com o caso de desempenho entre 40 e 70 do diagnostico)
            if (pontuacaoConteudo >= 65) {
                if (meuNivelConteudo.getTentativas() >= 2){ //PEDRO - Apto a passar de n??vel
                    Feedback meuFeedback = new Feedback(meuNivelConteudo.getConteudo(), meuNivelConteudo.getNivel());
                    NivelConteudoEnum novoNivel = meuNivelConteudo.obtemIncrementoUmNivel();
                    meuFeedback.setNivelAtual(novoNivel);
                    int retorno = 0;
                    if (novoNivel != null) { //sinal de que pulou um n??vel
                        retorno = 1;
                    }
                    meuFeedback.setNiveisAvancados(retorno);
                    listaFeedbacks.add(meuFeedback);
                    retorno = meuNivelConteudo.incrementaUmNivel();
                    if (retorno == -1) {
                        Toast.makeText(context, "Erro ao atualizar n??vel!", Toast.LENGTH_SHORT).show();
                    } else if (retorno == 1) {
                        listaNivelConteudoParaAtualizar.add(meuNivelConteudo);
                        Toast.makeText(context, "Parab??ns!!! Voc?? pulou no conte??do: " + meuNivelConteudo.getConteudo().getNomeConteudo() + " para o n??vel: " + meuNivelConteudo.getNivel(), Toast.LENGTH_SHORT).show();
                        meuNivelConteudo.setTentativas(0);
                        meuNivelConteudo.setVidas(5);
                    } else if (retorno == 0) {
                        Toast.makeText(context, "Parab??ns!!! Voc?? ja completou todos os n??veis para o conte??do: " + meuNivelConteudo.getConteudo().getNomeConteudo() + ". Agora voc?? pode continuar praticando! \n A sua nota foi " + pontuacaoConteudo + ". Ser?? que voc?? consegue se superar?", Toast.LENGTH_SHORT).show();
                        meuNivelConteudo.setTentativas(0);
                        meuNivelConteudo.setVidas(5);
                    }

                } else{ //Pedro - N??o obteve tr??s notas acima da m??dia, ent??o ainda n??o est?? apto a passar de n??vel
                    meuNivelConteudo.setTentativas(meuNivelConteudo.getTentativas()+1);
                    Feedback meuFeedback = new Feedback(meuNivelConteudo.getConteudo(), meuNivelConteudo.getNivel(), meuNivelConteudo.getNivel(), 0 );
                    listaFeedbacks.add(meuFeedback);
                    Toast.makeText(context, "Parab??ns!!! Voc?? teve um desempenho acima da m??dia, caso consiga realizar isso mais "+(3- meuNivelConteudo.getTentativas())+" Voc?? passar?? de n??vel no conte??do", Toast.LENGTH_SHORT).show();

                }
            } else if (pontuacaoConteudo <65){ //Pedro - TRATAMENTO PARA NOTA ABAIXO DA M??DIA
                if (meuNivelConteudo.getVidas() > 1){ //Pedro - Ainda n??o zerou todas as vidas ent??o n??o decai de n??vel
                    if (pontuacaoConteudo<=10){ //PEDRO - N??mero de vidas ?? decrementado em dois por conta do desempenho menor ou igual a 10
                        meuNivelConteudo.setVidas(meuNivelConteudo.getVidas()-2);
                        System.out.println("Vidas: "+meuNivelConteudo.getVidas());
                        Feedback meuFeedback = new Feedback(meuNivelConteudo.getConteudo(), meuNivelConteudo.getNivel(), meuNivelConteudo.getNivel(), 0);
                        listaFeedbacks.add(meuFeedback);
                        Toast.makeText(context, "Sua nota foi abaixo ou igual a 10, voc?? perdeu duas vidas, voc?? tem mais "+(meuNivelConteudo.getVidas())+" vidas", Toast.LENGTH_SHORT).show();
                    } else{ //Pedro - N??mero de vidas ?? decrementado em 1
                        meuNivelConteudo.setVidas(meuNivelConteudo.getVidas()-1);
                        System.out.println("Vidas: "+meuNivelConteudo.getVidas());
                        Feedback meuFeedback = new Feedback(meuNivelConteudo.getConteudo(), meuNivelConteudo.getNivel(), meuNivelConteudo.getNivel(), 0);
                        listaFeedbacks.add(meuFeedback);
                        Toast.makeText(context, "Voc?? tirou abaixo de 65, voc?? perdeu uma vida, voc?? tem mais "+ (meuNivelConteudo.getVidas())+" vidas", Toast.LENGTH_SHORT).show();
                    }

                } else { //PEDRO - Decai um n??vel direto pois j?? zerou ou ir?? zerar o n??mero de vidas
                    meuNivelConteudo.setTentativas(0); //PEDRO - Zera as tentativas pois desceu de n??vel
                    meuNivelConteudo.setVidas(5); //PEDRO - Restaura o n??mero base vidas (4) pois desceu de n??vel

                    Feedback meuFeedback = new Feedback(meuNivelConteudo.getConteudo(), meuNivelConteudo.getNivel());
                    NivelConteudoEnum novoNivel = meuNivelConteudo.obtemDecaiUmNivel();
                    meuFeedback.setNivelAtual(novoNivel);
                    int retorno = 0;
                    if (novoNivel != null){ //PEDRO - Sinal de que decaiu um nivel
                        retorno = -1;
                    }
                    meuFeedback.setNiveisAvancados(retorno);
                    listaFeedbacks.add(meuFeedback);
                    retorno = meuNivelConteudo.decaiUmNivel();
                    if (retorno == -1){
                        Toast.makeText(context, "Erro ao atualizar o n??vel!", Toast.LENGTH_SHORT).show();
                    } else if ( retorno == 1){
                        listaNivelConteudoParaDecair.add(meuNivelConteudo);
                        Toast.makeText(context, "Que pena, infelizmente voc?? decaiu no conte??do: " + meuNivelConteudo.getConteudo().getNomeConteudo() + " para o n??vel: " + meuNivelConteudo.getNivel()+" seu n??mero de vidas foi restaurado", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(context, "Parece que voc?? est?? com dificuldades, gostaria de ler o material de apoio?", Toast.LENGTH_SHORT).show();
                    }
                }

            }
            else { // precisa indicar no FeedBack que n??o teve avan??o
                Feedback meuFeedback = new Feedback(meuNivelConteudo.getConteudo(), meuNivelConteudo.getNivel(), meuNivelConteudo.getNivel(), 0);
                listaFeedbacks.add(meuFeedback);

            }
            //PEDRO - Atualizando o n??mero de tentativas no banco
            nivelConteudoDB.atualizaTentativas(meuNivelConteudo, meuUsuario);
            //PEDRO - Atualizando o n??mero de vidas no banco
            nivelConteudoDB.atualizaVidas(meuNivelConteudo, meuUsuario);

            Log.d("Teste", "N??vel novo: " + meuNivelConteudo.getNivel());

        }

        // verificando se existem conte??dos a serem atualizados os n??veis no banco
        if (listaNivelConteudoParaAtualizar.size() > 0) {
            NivelConteudoDB nivelConteudoDB = new NivelConteudoDB(this.context);
            nivelConteudoDB.incrementaNivel(listaNivelConteudoParaAtualizar, meuUsuario);
        }
        if (listaNivelConteudoParaDecair.size() > 0){
            NivelConteudoDB nivelConteudoDB = new NivelConteudoDB(this.context);
            nivelConteudoDB.decaiNivel(listaNivelConteudoParaDecair, meuUsuario);
        }
        return desempenhoQuestionario;
    }

    public ArrayList<Pergunta> carregaQuantPerguntasPorConteudoDiagnostico(ArrayList<NivelConteudo> listaNivelConteudos, int[][] quantidadePerguntasPorConteudo) {
        ArrayList<Pergunta> listaPerguntas;

        for (int x = 0; x < listaNivelConteudos.size(); x++) {
            NivelConteudo meuNivelConteudo = listaNivelConteudos.get(x);

            int quantidadeFaceis = 0;
            int quantidadeMedias = 0;
            int quantidadeDificeis = 0;

            Log.d("Teste", "Nivel: " + meuNivelConteudo.getNivel());

            if (meuNivelConteudo.getNivel() == NivelConteudoEnum.COBRE) {
                // nesse caso aplica-se as regras do n??vel prata
                quantidadeFaceis = Math.round(quantidadePerguntasPorConteudo[x][0] * 0.3f);
                quantidadeDificeis = Math.round(quantidadePerguntasPorConteudo[x][0] * 0.3f);
                quantidadeMedias = quantidadePerguntasPorConteudo[x][0] - (quantidadeFaceis + quantidadeDificeis);
            } else if (meuNivelConteudo.getNivel() == NivelConteudoEnum.BRONZE) {
                quantidadeDificeis = Math.round(quantidadePerguntasPorConteudo[x][0] * 0.5f);
                quantidadeFaceis = Math.round(quantidadePerguntasPorConteudo[x][0] * 0.1f);
                quantidadeMedias = quantidadePerguntasPorConteudo[x][0] - (quantidadeDificeis + quantidadeFaceis);
            } else if (meuNivelConteudo.getNivel() == NivelConteudoEnum.PRATA) {
                quantidadeDificeis = Math.round(quantidadePerguntasPorConteudo[x][0] * 0.7f);
                quantidadeMedias = quantidadePerguntasPorConteudo[x][0] - quantidadeDificeis;
            } else if (meuNivelConteudo.getNivel() == NivelConteudoEnum.OURO) {
                quantidadeDificeis = Math.round(quantidadePerguntasPorConteudo[x][0] * 0.7f);
                quantidadeMedias = quantidadePerguntasPorConteudo[x][0] - quantidadeDificeis;
            } else if (meuNivelConteudo.getNivel() == NivelConteudoEnum.DIAMANTE) {
                quantidadeDificeis = Math.round(quantidadePerguntasPorConteudo[x][0] * 0.7f);
                quantidadeMedias = quantidadePerguntasPorConteudo[x][0] - quantidadeDificeis;
            }
            quantidadePerguntasPorConteudo[x][1] = quantidadeFaceis;
            quantidadePerguntasPorConteudo[x][2] = quantidadeMedias;
            quantidadePerguntasPorConteudo[x][3] = quantidadeDificeis;
            Log.d("Teste", "Conteudo: " + x + "-" + listaNivelConteudos.get(x).getConteudo().getNomeConteudo() + ": Total " + quantidadePerguntasPorConteudo[x][0] + ", faceis " + quantidadePerguntasPorConteudo[x][1] + ", m??dias " + quantidadePerguntasPorConteudo[x][2] + ", dificeis " + quantidadePerguntasPorConteudo[x][3]);
        }
        PerguntaDB perguntaDB = new PerguntaDB(this.context);
        listaPerguntas = perguntaDB.buscaPerguntasPorConteudosUnionAll(listaNivelConteudos, quantidadePerguntasPorConteudo);
        return listaPerguntas;
    }

    public DesempenhoQuestionario calculaDesempenhoDiagnostico(ArrayList<NivelConteudo> listaNivelConteudos, int[][] quantidadePerguntasPorConteudo, ArrayList<Pergunta> listaPerguntas, Usuario meuUsuario, ArrayList<Feedback> listaFeedbacks) {
        int acertos = 0;
        int erros = 0;

        // criando o objeto desempenho questionario para adicionar os desempenhos por conteudo
        // testar se o metodo "System...." volta uma data ou data com hora
        Date dataAtual = new Date(System.currentTimeMillis());
        Log.d("Teste", "data = " + dataAtual);
        DesempenhoQuestionario desempenhoQuestionario = new DesempenhoQuestionario
                (dataAtual, 2); // recebe 2 no tipo desempenho pois ?? um diagn??stico

        int inicio = 0;

        // navegando na lista de conte??dos para saber as quantidades e tamb??m os n??veis de cada um
        for (int indice = 0; indice < listaNivelConteudos.size(); indice++) {
            // obtendo o nivel conteudo
            NivelConteudo meuNivelConteudo = listaNivelConteudos.get(indice);

            // definindo os valores de faceis, medias e dificeis de acordo com o n??vel que o usu??rio est??
            float valorFaceis = 0;
            float valorMedias = 0;
            float valorDificeis = 0;

            if (meuNivelConteudo.getNivel() == NivelConteudoEnum.COBRE) {
                // nesse caso aplica-se a pontua????o conforme o n??vel Prata
                valorFaceis = 30f / quantidadePerguntasPorConteudo[indice][1];
                valorMedias = 40f / quantidadePerguntasPorConteudo[indice][2];
                valorDificeis = 30f / quantidadePerguntasPorConteudo[indice][3];
            } else if (meuNivelConteudo.getNivel() == NivelConteudoEnum.BRONZE) {
                valorFaceis = 20f / quantidadePerguntasPorConteudo[indice][1];
                valorMedias = 35f / quantidadePerguntasPorConteudo[indice][2];
                valorDificeis = 45f / quantidadePerguntasPorConteudo[indice][3];
            } else if (meuNivelConteudo.getNivel() == NivelConteudoEnum.PRATA) {
                valorMedias = 32f / quantidadePerguntasPorConteudo[indice][2];
                valorDificeis = 68f / quantidadePerguntasPorConteudo[indice][3];
            } else if (meuNivelConteudo.getNivel() == NivelConteudoEnum.OURO) {
                valorMedias = 32f / quantidadePerguntasPorConteudo[indice][2]; //Pedro - alterei pra tentar resolver as notas
                valorDificeis = 68f / quantidadePerguntasPorConteudo[indice][3]; //Pedro - alterei pra tentar resolver as notas
            } else if (meuNivelConteudo.getNivel() == NivelConteudoEnum.DIAMANTE) {
                valorMedias = 32f / quantidadePerguntasPorConteudo[indice][2]; //Pedro - alterei pra tentar resolver as notas
                valorDificeis = 68f / quantidadePerguntasPorConteudo[indice][3]; //Pedro - alterei pra tentar resolver as notas
            }
            Log.d("Teste", "Percentual valor f??ceis: " + valorFaceis + ", percentual m??dias: " + valorMedias + ", percentual d??ficeis: " + valorDificeis);

            float pontuacaoConteudo = 0;

            // fazendo o controle para saber at?? onde ele deve ir nas perguntas para esse determinado conte??do
            int fim = inicio + quantidadePerguntasPorConteudo[indice][0];
            // navegando nas perguntas do conte??do em quest??o
            for (int x = inicio; x < fim; x++) {
                // obtendo a pergunta do conte??do
                Pergunta minhaPergunta = listaPerguntas.get(x);

                // verificando se acertou ou n??o
                if (minhaPergunta.getOpcaoEscolhida() == minhaPergunta.getAlternativaCorreta()) {
                    // acertou
                    acertos++;
                    // verificando a dificuldade para saber o valor a ser acrescido (pesos diferentes)
                    if (minhaPergunta.getNivelDificuldade() == 1) {
                        pontuacaoConteudo = pontuacaoConteudo + valorFaceis;
                    } else if (minhaPergunta.getNivelDificuldade() == 2) {
                        pontuacaoConteudo = pontuacaoConteudo + valorMedias;
                    } else if (minhaPergunta.getNivelDificuldade() == 3) {
                        pontuacaoConteudo = pontuacaoConteudo + valorDificeis;
                    }
                } else {
                    // errou
                    erros++;
                }
            }
            pontuacaoConteudo = Math.round(pontuacaoConteudo);
            Log.d("Teste", "Pontua????o conte??do: " + pontuacaoConteudo);
            // atualizando o novo in??cio
            inicio = fim;

            DesempenhoConteudo desempenhoConteudo = new DesempenhoConteudo(meuNivelConteudo.getConteudo(), quantidadePerguntasPorConteudo[indice][0], acertos, erros, pontuacaoConteudo);

            // adicionando no desempenho do question??rio
            desempenhoQuestionario.getListaDesempenhoConteudos().add(desempenhoConteudo);

            // reiniciando os acertos e erros
            acertos = 0;
            erros = 0;

            // verificando se pontua????o conte??do fez com que devesse pular de n??vel
            Log.d("Teste", "N??vel antigo: " + meuNivelConteudo.getNivel());
            if (pontuacaoConteudo >= 70) {
                // preparando o feedback
                Feedback meuFeedback = new Feedback(meuNivelConteudo.getConteudo(), meuNivelConteudo.getNivel());
                NivelConteudoEnum novoNivel = meuNivelConteudo.obtemIncrementoDoisNiveis();
                meuFeedback.setNivelAtual(novoNivel);
                int retorno = 0;
                if (novoNivel != null) { // verificando se pulou um n??vel
                    if (meuNivelConteudo.getNivel() == NivelConteudoEnum.OURO) { // pulou e est?? no pen??ltimo n??vel, logo, avan??ou um
                        retorno = 1;
                    } else {
                        retorno = 2;
                    }
                }
                meuFeedback.setNiveisAvancados(retorno);
                listaFeedbacks.add(meuFeedback);
            } else if (pontuacaoConteudo >= 40 && pontuacaoConteudo < 70) {
                // preparando o feedback
                Feedback meuFeedback = new Feedback(meuNivelConteudo.getConteudo(), meuNivelConteudo.getNivel());
                NivelConteudoEnum novoNivel = meuNivelConteudo.obtemIncrementoUmNivel();
                meuFeedback.setNivelAtual(novoNivel);
                int retorno = 0;
                if (novoNivel != null) { //sinal de que pulou um n??vel
                    retorno = 1;
                }
                meuFeedback.setNiveisAvancados(retorno);
                listaFeedbacks.add(meuFeedback);
            } else if (pontuacaoConteudo < 40) {
                // preparando o feedback
                Feedback meuFeedback = new Feedback(meuNivelConteudo.getConteudo(), meuNivelConteudo.getNivel(), meuNivelConteudo.getNivel(), 0);
                listaFeedbacks.add(meuFeedback);
            }
            for (int x = 0; x < listaFeedbacks.size(); x++) {
                Log.d("Teste", "INTERMEDIARIA Lista Feedbacks = Conteudo: " + listaFeedbacks.get(x).getConteudo().getNomeConteudo() + ", nivel anterior: " + listaFeedbacks.get(x).getNivelAnterior() + ", nivel atual: " + listaFeedbacks.get(x).getNivelAtual());
            }
            Log.d("Teste", "N??vel novo: " + meuNivelConteudo.getNivel());
        }

        return desempenhoQuestionario;
    }
}