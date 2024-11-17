package Projeto;

import java.util.Scanner;

public class ProdutoAlimentarTaxaIntermedia extends ProdutoAlimentar {
    protected String categoria;
    protected int[] arrayTaxas = {13,12,9};
    protected int aumentoTaxa = 1;

    public ProdutoAlimentarTaxaIntermedia(String codigo, String nome, String descricao, String quantidade, String valorSemIVA, String biologico, String taxa, String categoria){
        super(codigo, nome, descricao, quantidade, valorSemIVA, biologico, taxa);
        this.categoria = categoria;
    }

    public String toString(){
        return (super.toString() + "; Categoria -> " + this.categoria);
    }

    protected static ProdutoAlimentarTaxaIntermedia criaProdutoTaxaIntermedia(){
        String tipoTaxa = "Intermedia";
        String categoriaProduto = null;

        Scanner scannerObterResposta = new Scanner(System.in);

        boolean verificacaoCategoria = false;

        String[] arrayInformacoesProdutoAlimentar = ProdutoAlimentar.obterInformacaoProdutoAlimentar();

        while(!verificacaoCategoria){
            System.out.print("Introduza a categoria do produto: ");
            categoriaProduto = scannerObterResposta.nextLine();
            verificacaoCategoria = FuncoesUteis.verificaCategoria(categoriaProduto);
        }

        return new ProdutoAlimentarTaxaIntermedia(arrayInformacoesProdutoAlimentar[0], arrayInformacoesProdutoAlimentar[1], arrayInformacoesProdutoAlimentar[2], arrayInformacoesProdutoAlimentar[3], arrayInformacoesProdutoAlimentar[4], arrayInformacoesProdutoAlimentar[5], tipoTaxa, categoriaProduto);
    }
}
