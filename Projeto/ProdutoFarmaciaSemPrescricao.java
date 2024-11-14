package Projeto;

public class ProdutoFarmaciaSemPrescricao extends ProdutoFarmacia {
    protected String categoria;

    public ProdutoFarmaciaSemPrescricao(int codigo, String nome, String descricao, int quantidade, double valorSemIVA, boolean prescricao, String categoria){
        super(codigo, nome, descricao, quantidade, valorSemIVA, prescricao);
        this.categoria  = categoria;
    }
}
