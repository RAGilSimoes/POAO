package Projeto;

import java.util.Scanner;

public class ProdutoAlimentarTaxaIntermedia extends ProdutoAlimentar {
    protected String categoria;


    public ProdutoAlimentarTaxaIntermedia(String codigo, String nome, String descricao, String quantidade, String valorSemIVA, String biologico, String taxa, String categoria){
        super(codigo, nome, descricao, quantidade, valorSemIVA, biologico, taxa);
        this.categoria = categoria;
    }

    public String toString(){
        return (super.toString() + "; Categoria -> " + this.categoria);
    }

    public String getCategoria() {
        return categoria;
    }

    protected double obtemValorComIVA(Cliente clienteRecebido){
        final int[] arrayTaxas = {13,12,9};
        final int aumentoTaxa = 1;
        int taxaAplicada = this.getTaxaAplicada(clienteRecebido, arrayTaxas);

        String categoria = this.getCategoria();
        if(categoria.equalsIgnoreCase("vinho")){
            taxaAplicada += aumentoTaxa;
        }

        double precoFinalComIVA = this.calculaPrecoFinalComIVA(taxaAplicada, this);

        return precoFinalComIVA;
    }

    protected ProdutoAlimentarTaxaIntermedia criaProdutoTaxaIntermedia(){
        FuncoesUteis funcoesUteis = new FuncoesUteis();
        String tipoTaxa = "Intermedia";
        String categoriaProduto = null;

        Scanner scannerObterResposta = new Scanner(System.in);

        boolean verificacaoCategoria = false;

        String[] arrayInformacoesProdutoAlimentar = this.obterInformacaoProdutoAlimentar();

        while(!verificacaoCategoria){
            System.out.print("Introduza a categoria do produto: ");
            categoriaProduto = scannerObterResposta.nextLine();
            verificacaoCategoria = funcoesUteis.verificaCategoria(categoriaProduto);
        }

        return new ProdutoAlimentarTaxaIntermedia(arrayInformacoesProdutoAlimentar[0], arrayInformacoesProdutoAlimentar[1], arrayInformacoesProdutoAlimentar[2], arrayInformacoesProdutoAlimentar[3], arrayInformacoesProdutoAlimentar[4], arrayInformacoesProdutoAlimentar[5], tipoTaxa, categoriaProduto);
    }

    protected String getTipo() {
        return "Produto Alimentar Taxa Intermedia";
    }
}
