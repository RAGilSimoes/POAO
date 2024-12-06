import java.io.Serializable;
import java.util.ArrayList;

/**
 * The type Produto farmacia.
 */
abstract class ProdutoFarmacia extends Produto implements Serializable {
    /**
     * The Prescricao.
     */
    protected String prescricao;

    /**
     * Instantiates a new Produto farmacia.
     *
     * @param codigo       the codigo
     * @param nome         the nome
     * @param descricao    the descricao
     * @param quantidade   the quantidade
     * @param valorUnidade the valor sem iva
     * @param prescricao   the prescricao
     */
    protected ProdutoFarmacia(String codigo, String nome, String descricao, String quantidade, String valorUnidade, String prescricao){
        super(codigo, nome, descricao, quantidade, valorUnidade);
        this.prescricao = prescricao;
    }

    /**
     * Obter informacao produto farmacia string [ ].
     *
     * @param arrayProdutos the array produtos
     * @return the string [ ]
     */
    protected String[] obterInformacaoProdutoFarmacia(ArrayList<Produto> arrayProdutos) {
        return this.obterInformacoesProduto(arrayProdutos);
    }

    protected String getTipo() {
        return "Produto Farmacia";
    }

    /**
     * Gets prescricao.
     *
     * @return the prescricao
     */
    protected String getPrescricao() {
        return prescricao;
    }

    /**
     * Sets prescricao.
     *
     * @param prescricao the prescricao
     */
    protected void setPrescricao(String prescricao) {
        this.prescricao = prescricao;
    }

    public String toString(){
        return (super.toString() + "; Prescrição -> " + this.getPrescricao());
    }

    /**
     * Calcula preco final com iva double.
     *
     * @param taxaAplicada    the taxa aplicada
     * @param produtoFarmacia the produto farmacia
     * @return the double
     */
    protected double calculaPrecoFinalComIVA(int taxaAplicada, ProdutoFarmacia produtoFarmacia){
        int quantidadeProduto = Integer.parseInt(produtoFarmacia.getQuantidade());
        double valorPorUnidade = Double.parseDouble(produtoFarmacia.getValorUnidade());

        double taxaAplicadaDecimal = (taxaAplicada / 100.0);

        double valorImposto = (valorPorUnidade * taxaAplicadaDecimal);

        double precoFinalComIVA = (quantidadeProduto * (valorPorUnidade + valorImposto));
        precoFinalComIVA = (Math.floor(precoFinalComIVA * 100) /100);

        return precoFinalComIVA;
    }
}

