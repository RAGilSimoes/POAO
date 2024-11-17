package Projeto;

import java.util.Scanner;

public class ProdutoAlimentarTaxaReduzida extends ProdutoAlimentar {
    protected int quantidadeCertificacoes;
    protected String[] certificacoes;
    protected int[] arrayTaxas = {6,5,4};
    protected int reducaoTaxa = -1;

    public ProdutoAlimentarTaxaReduzida(String codigo, String nome, String descricao, String quantidade, String valorSemIVA, String biologico, String taxa, int quantidadeCertificacoes, String[] certificacoes){
        super(codigo, nome, descricao, quantidade, valorSemIVA, biologico, taxa);
        this.quantidadeCertificacoes = quantidadeCertificacoes;
        this.certificacoes = certificacoes;
    }

    public String toString(){
        return (super.toString() + "; Certificacoes -> " + this.quantidadeCertificacoes);
    }

    protected static ProdutoAlimentarTaxaReduzida criaProdutoTaxaReduzida(){
        String tipoTaxa = "Reduzida";

        boolean verificacaoQuantidade = false;
        String quantidadeCertificacoesProduto = null;
        int quantidadeCertificacoes = 0;

        Scanner scannerObterResposta = new Scanner(System.in);

        String[] arrayInformacoesProdutoAlimentar = ProdutoAlimentar.obterInformacaoProdutoAlimentar();

        while (!verificacaoQuantidade) {
            System.out.print("Introduza a quantidade de certificações do produto: ");
            quantidadeCertificacoesProduto = scannerObterResposta.nextLine();
            verificacaoQuantidade = FuncoesUteis.verificaInt(quantidadeCertificacoesProduto);
            if(verificacaoQuantidade) {
                quantidadeCertificacoes = Integer.parseInt(quantidadeCertificacoesProduto);
            }
        }

        String[] arrayCertificacoes = new String[quantidadeCertificacoes];

        for(int l = 0; l < quantidadeCertificacoes; l++){
            System.out.print("Introduza as certificações do produto: ");
            String certificacoesProduto = scannerObterResposta.nextLine();
            arrayCertificacoes[l] = certificacoesProduto;
        }

        return new ProdutoAlimentarTaxaReduzida(arrayInformacoesProdutoAlimentar[0], arrayInformacoesProdutoAlimentar[1], arrayInformacoesProdutoAlimentar[2], arrayInformacoesProdutoAlimentar[3], arrayInformacoesProdutoAlimentar[4], arrayInformacoesProdutoAlimentar[5], tipoTaxa, quantidadeCertificacoes, arrayCertificacoes);
    }
}