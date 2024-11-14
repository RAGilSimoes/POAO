package Projeto;

public class ProdutoAlimentarTaxaNormal extends ProdutoAlimentar {
    public ProdutoAlimentarTaxaNormal(int codigo, String nome, String descricao, int quantidade, double valorSemIVA, boolean biologico, String taxa){
        super(codigo, nome, descricao, quantidade, valorSemIVA, biologico, taxa);
    }
}
