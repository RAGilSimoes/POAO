package Projeto;

public class ProdutoAlimentarTaxaIntermedia extends ProdutoAlimentar {
    protected String categoria;
    protected int[] arrayTaxas = {13,12,9};
    protected int aumentoTaxa = 1;

    public ProdutoAlimentarTaxaIntermedia(int codigo, String nome, String descricao, int quantidade, double valorSemIVA, String biologico, String taxa, String categoria){
        super(codigo, nome, descricao, quantidade, valorSemIVA, biologico, taxa);
        this.categoria = categoria;
    }

    public String toString(){
        return (super.toString() + "; Categoria -> " + this.categoria);
    }
}
