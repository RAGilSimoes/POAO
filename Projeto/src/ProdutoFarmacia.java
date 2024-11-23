package Projeto.src;

/**
 * The type Produto farmacia.
 */
abstract class ProdutoFarmacia extends Produto {
    /**
     * The Prescricao.
     */
    protected String prescricao;

    /**
     * Instantiates a new Produto farmacia.
     *
     * @param codigo      the codigo
     * @param nome        the nome
     * @param descricao   the descricao
     * @param quantidade  the quantidade
     * @param valorSemIVA the valor sem iva
     * @param prescricao  the prescricao
     */
    public ProdutoFarmacia(String codigo, String nome, String descricao, String quantidade, String valorSemIVA, String prescricao){
        super(codigo, nome, descricao, quantidade, valorSemIVA);
        this.prescricao = prescricao;
    }

    /**
     * Obter informacao produto farmacia string [ ].
     *
     * @return the string [ ]
     */
    protected String[] obterInformacaoProdutoFarmacia() {
        return this.obterInformacoesProduto();
    }

    protected String getTipo() {
        return "Produto Farmacia";
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
        double valorPorUnidade = Double.parseDouble(produtoFarmacia.getValorSemIVA());

        double taxaAplicadaDecimal = (taxaAplicada / 100.0);

        double valorImposto = (valorPorUnidade * taxaAplicadaDecimal);

        double precoFinalComIVA = (quantidadeProduto * (valorPorUnidade + valorImposto));

        return precoFinalComIVA;
    }
}

