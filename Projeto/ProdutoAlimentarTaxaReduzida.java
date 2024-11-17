package Projeto;

import java.util.Scanner;

public class ProdutoAlimentarTaxaReduzida extends ProdutoAlimentar {
    protected int quantidadeCertificacoes;
    protected String[] certificacoes;
    protected int[] arrayTaxas = {6,5,4};
    protected int reducaoTaxa = -1;

    public ProdutoAlimentarTaxaReduzida(int codigo, String nome, String descricao, int quantidade, double valorSemIVA, String biologico, String taxa, int quantidadeCertificacoes, String[] certificacoes){
        super(codigo, nome, descricao, quantidade, valorSemIVA, biologico, taxa);
        this.quantidadeCertificacoes = quantidadeCertificacoes;
        this.certificacoes = certificacoes;
    }

    public String toString(){
        return (super.toString() + "; Certificacoes -> " + this.quantidadeCertificacoes);
    }

    protected ProdutoAlimentarTaxaReduzida criaProdutoTaxaReduzida(){
        String tipoTaxa = "Reduzida";
        Scanner scannerObterResposta = new Scanner(System.in);
        System.out.print("\nIntroduza o código do produto: ");
        int codigoProduto = Integer.parseInt(scannerObterResposta.nextLine());
        System.out.print("Introduza o nome do produto: ");
        String nomeProduto = scannerObterResposta.nextLine();
        System.out.print("Introduza a descricao do produto: ");
        String descricaoProduto = scannerObterResposta.nextLine();
        System.out.print("Introduza a quantidade do produto: ");
        int quantidadeProduto = Integer.parseInt(scannerObterResposta.nextLine());
        System.out.print("Introduza o preço sem IVA do produto: ");
        double precoSemIVAProduto = Double.parseDouble(scannerObterResposta.nextLine());

        System.out.print("O produto é biológico? ");
        String biologicoProduto = scannerObterResposta.nextLine();

        System.out.print("Introduza a quantidade de certificações do produto: ");
        int quantidadeCertificacoesProduto = Integer.parseInt(scannerObterResposta.nextLine());

        String[] arrayCertificacoes = new String[quantidadeCertificacoesProduto];

        for(int l = 0; l < quantidadeCertificacoesProduto; l++){
            System.out.print("Introduza as certificações do produto: ");
            String certificacoesProduto = scannerObterResposta.nextLine();
            arrayCertificacoes[l] = certificacoesProduto;
        }

        return new ProdutoAlimentarTaxaReduzida(codigoProduto, nomeProduto, descricaoProduto, quantidadeProduto, precoSemIVAProduto, biologicoProduto, tipoTaxa, quantidadeCertificacoesProduto, arrayCertificacoes);
    }
}
