package Projeto;

abstract class ProdutoFarmacia extends Produto {
    protected boolean precricao;

    public ProdutoFarmacia(int codigo, String nome, String descricao, int quantidade, double valorSemIVA, boolean prescricao){
        super(codigo, nome, descricao, quantidade, valorSemIVA);
        this.precricao = prescricao;
    }
}

