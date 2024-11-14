package Projeto;

public class ProdutoFarmaciaComPrescricao extends ProdutoFarmacia {
    protected String medico;
    protected int[] arrayTaxas = {6,5,4};

    public ProdutoFarmaciaComPrescricao(int codigo, String nome, String descricao, int quantidade, double valorSemIVA, boolean prescricao, String medico){
        super(codigo, nome, descricao, quantidade, valorSemIVA, prescricao);
        this.medico = medico;
    }
}
