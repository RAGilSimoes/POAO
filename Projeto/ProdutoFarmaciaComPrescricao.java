package Projeto;

public class ProdutoFarmaciaComPrescricao extends ProdutoFarmacia {
    protected String medico;

    public ProdutoFarmaciaComPrescricao(int codigo, String nome, String descricao, int quantidade, double valorSemIVA, boolean prescricao, String medico){
        super(codigo, nome, descricao, quantidade, valorSemIVA, prescricao);
        this.medico = medico;
    }
}
