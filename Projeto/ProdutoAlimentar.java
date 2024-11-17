package Projeto;

abstract class ProdutoAlimentar extends Produto{
    protected String biologico;
    protected String taxa;

    public ProdutoAlimentar(int codigo, String nome, String descricao, int quantidade, double valorSemIVA, String biologico, String taxa){
        super(codigo, nome, descricao, quantidade, valorSemIVA);
        this.biologico = biologico;
        this.taxa = taxa;
    }

    public String toString() {
        return (super.toString() + "; Biologico -> " + this.biologico + "; Taxa -> " + this.taxa);
    }
}

