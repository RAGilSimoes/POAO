package Projeto;

public class ProdutoAlimentarTaxaReduzida extends ProdutoAlimentar {
    protected int quantidadeCertificacoes;
    protected String[] certificacoes;
    protected int[] arrayTaxas = {6,5,4};
    protected int reducaoTaxa = -1;

    public ProdutoAlimentarTaxaReduzida(int codigo, String nome, String descricao, int quantidade, double valorSemIVA, boolean biologico, String taxa, int quantidadeCertificacoes, String[] certificacoes){
        super(codigo, nome, descricao, quantidade, valorSemIVA, biologico, taxa);
        this.quantidadeCertificacoes = quantidadeCertificacoes;
        this.certificacoes = certificacoes;
    }

    public String toString(){
        return (super.toString() + "; Certificacoes -> " + this.quantidadeCertificacoes);
    }
}
