package Projeto;

public class ProdutoAlimentarTaxaNormal extends ProdutoAlimentar {
    protected int[] arrayTaxas = {23,22,16};

    public ProdutoAlimentarTaxaNormal(int codigo, String nome, String descricao, int quantidade, double valorSemIVA, String biologico, String taxa){
        super(codigo, nome, descricao, quantidade, valorSemIVA, biologico, taxa);
    }
}
