package Projeto;

public class ProdutoFarmaciaSemPrescricao extends ProdutoFarmacia {
    protected String categoria;
    protected int[] arrayTaxas = {23,23,23};
    protected int reducaoTaxa = -1;

    public ProdutoFarmaciaSemPrescricao(int codigo, String nome, String descricao, int quantidade, double valorSemIVA, boolean prescricao, String categoria){
        super(codigo, nome, descricao, quantidade, valorSemIVA, prescricao);
        this.categoria  = categoria;
    }
}
