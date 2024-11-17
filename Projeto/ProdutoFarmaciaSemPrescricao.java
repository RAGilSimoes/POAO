package Projeto;

import java.util.Scanner;

public class ProdutoFarmaciaSemPrescricao extends ProdutoFarmacia {
    protected String categoria;
    protected int[] arrayTaxas = {23,23,23};
    protected int reducaoTaxa = -1;

    public ProdutoFarmaciaSemPrescricao(String codigo, String nome, String descricao, String quantidade, String valorSemIVA, String prescricao, String categoria){
        super(codigo, nome, descricao, quantidade, valorSemIVA, prescricao);
        this.categoria  = categoria;
    }

    protected static ProdutoFarmaciaSemPrescricao criaProdutoSemPrescricao(){
        String prescricao = "Nao";
        Scanner scannerObterResposta = new Scanner(System.in);

        String[] arrayInformacoesProdutoFarmacia = ProdutoFarmacia.obterInformacaoProdutoFarmacia();

        System.out.print("Introduza a categoria do produto: ");
        String categoriaProduto = scannerObterResposta.nextLine();

        return new ProdutoFarmaciaSemPrescricao(arrayInformacoesProdutoFarmacia[0], arrayInformacoesProdutoFarmacia[1], arrayInformacoesProdutoFarmacia[2], arrayInformacoesProdutoFarmacia[3], arrayInformacoesProdutoFarmacia[4], prescricao, categoriaProduto);
    }
}
