/**
 * @author Guilherme Carvalho e Ricardo Simoes
 * @version 1.0
 */

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Classe que representa o Produto de Farmacia
 */
abstract class ProdutoFarmacia extends Produto implements Serializable {
    /**
     * Prescricao
     */
    private String prescricao;

    /**
     * Construtor do Produto de Farmacia
     *
     * @param codigo       codigo
     * @param nome         nome
     * @param descricao    descricao
     * @param quantidade   quantidade
     * @param valorUnidade valor sem iva
     * @param prescricao   prescricao
     */
    protected ProdutoFarmacia(String codigo, String nome, String descricao, String quantidade, String valorUnidade, String prescricao){
        super(codigo, nome, descricao, quantidade, valorUnidade);
        this.prescricao = prescricao;
    }

    /**
     * Obter a informacao do produto de farmacia
     *
     * @param arrayProdutos array de produtos
     * @return informacao do produto de farmacia
     */
    protected String[] obterInformacaoProdutoFarmacia(ArrayList<Produto> arrayProdutos) {
        return this.obterInformacoesProduto(arrayProdutos);
    }

    protected String getTipo() {
        return "Produto Farmacia";
    }

    /**
     * Getter da prescricao
     *
     * @return prescricao
     */
    protected String getPrescricao() {
        return prescricao;
    }

    /**
     * Setter da prescricao
     *
     * @param prescricao prescricao
     */
    protected void setPrescricao(String prescricao) {
        this.prescricao = prescricao;
    }

    public String toString(){
        return (super.toString() + "; Prescrição -> " + this.getPrescricao());
    }

    /**
     * Calcula preco final com iva
     *
     * @param taxaAplicada    taxa aplicada
     * @param produtoFarmacia produto de farmacia
     * @return preco final com iva
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