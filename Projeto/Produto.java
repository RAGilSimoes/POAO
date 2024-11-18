package Projeto;

import java.util.Scanner;

abstract class Produto{
    protected String codigo;
    protected String nome;
    protected String descricao;
    protected String quantidade;
    protected String valorSemIVA;

    public Produto(String codigo, String nome, String descricao, String quantidade, String valorSemIVA){
        this.codigo = codigo;
        this.nome = nome;
        this.descricao = descricao;
        this.quantidade = quantidade;
        this.valorSemIVA = valorSemIVA;
    }

    public String toString() {
        return ("Codigo -> " + this.codigo + "; Nome -> " + this.nome + "; Descricao -> " + this.descricao + "; Quantidade -> " + this.quantidade + "; Valor sem IVA -> " + this.valorSemIVA);
    }

    public String getNome() {
        return nome;
    }

    public String getCodigo() {
        return codigo;
    }

    public String getDescricao() {
        return descricao;
    }

    public String getQuantidade() {
        return quantidade;
    }

    public String getValorSemIVA() {
        return valorSemIVA;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public void setQuantidade(String quantidade) {
        this.quantidade = quantidade;
    }

    public void setValorSemIVA(String valorSemIVA) {
        this.valorSemIVA = valorSemIVA;
    }

    protected static boolean verificaCodigo(String stringRecebida){
        boolean verificacao = true;
        for(int i = 0; i < stringRecebida.length(); i++){
            if (FuncoesUteis.verificaCaracteres(stringRecebida,'0', '9')){
                System.out.println("\nO código introduzido não é válido.");
                verificacao = false;
            }
        }
        return verificacao;
    }

    protected static String verificaDescricao(String stringRecebida) {
        String descricao;
        if(stringRecebida.isEmpty()){
            descricao = "Este produto não tem descrição";
        } else {
            descricao = stringRecebida;
        }
        return descricao;
    }



    protected double obtemValorSemIVA(){
        int quantidade = Integer.parseInt(this.getQuantidade());
        double precoPorUnidade = Double.parseDouble(this.getValorSemIVA());

        return (quantidade * precoPorUnidade);
    }

    protected abstract double obtemValorComIVA(Cliente clienteRecebido);

    protected static String[] obterInformacoesProduto() {
        Scanner scannerObterResposta = new Scanner(System.in);

        String codigoProduto = null;
        String quantidadeProduto = null;
        String precoSemIVAProduto = null;

        boolean verificaoCodigo = false;
        boolean verificacaoQuantidade = false;
        boolean verificacaoPrecoSemIVA = false;

        while(!verificaoCodigo){
            System.out.print("\nIntroduza o código do produto: ");
            codigoProduto = scannerObterResposta.nextLine();
            verificaoCodigo = verificaCodigo(codigoProduto);
        }

        System.out.print("Introduza o nome do produto: ");
        String nomeProduto = scannerObterResposta.nextLine();

        System.out.print("Introduza a descricao do produto: ");
        String descricaoProduto = scannerObterResposta.nextLine();
        descricaoProduto = verificaDescricao(descricaoProduto);

        while (!verificacaoQuantidade) {
            System.out.print("Introduza a quantidade do produto (mínimo 1): ");
            quantidadeProduto = scannerObterResposta.nextLine();
            verificacaoQuantidade = FuncoesUteis.verificaInt(quantidadeProduto);
            if(!verificacaoQuantidade){
                System.out.println("\nO valor introduzido não é válido.");
            } else {
                if(quantidadeProduto.equals("0")){
                    System.out.println("\nO valor introduzido não é válido.");
                    verificacaoQuantidade = false;
                }
            }
        }

        while(!verificacaoPrecoSemIVA){
            System.out.print("Introduza o preço sem IVA do produto: ");
            precoSemIVAProduto = scannerObterResposta.nextLine();
            verificacaoPrecoSemIVA = FuncoesUteis.verificaDouble(precoSemIVAProduto);
        }

        return new String[]{codigoProduto, nomeProduto, descricaoProduto, quantidadeProduto, precoSemIVAProduto};
    }

    protected abstract String getTipo();
}

