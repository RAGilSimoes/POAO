package Projeto;

public class ProdutoAlimentarTaxaIntermedia extends ProdutoAlimentar {
    protected String categoria;

    public ProdutoAlimentarTaxaIntermedia(int codigo, String nome, String descricao, int quantidade, double valorSemIVA, boolean biologico, String taxa, String categoria){
        super(codigo, nome, descricao, quantidade, valorSemIVA, biologico, taxa);
        this.categoria = categoria;
    }
}
