/**
 * @author Guilherme Carvalho e Ricardo Simoes
 * @version 1.0
 */

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Classe que representa o objeto Produto Alimentar com Taxa Normal
 */
public class ProdutoAlimentarTaxaNormal extends ProdutoAlimentar implements Serializable {
    /**
     * Construtor do Produto Alimentar com Taxa Normal
     *
     * @param codigo       codigo
     * @param nome         nome
     * @param descricao    descricao
     * @param quantidade   quantidade
     * @param valorUnidade valor sem iva
     * @param biologico    biologico
     * @param taxa         taxa
     */
    protected ProdutoAlimentarTaxaNormal(String codigo, String nome, String descricao, String quantidade, String valorUnidade, String biologico, String taxa){
        super(codigo, nome, descricao, quantidade, valorUnidade, biologico, taxa);
    }

    protected double obtemValorComIVA(Cliente clienteRecebido){
        final int[] arrayTaxas = {23,22,16};

        int taxaAplicada = getTaxaAplicada(clienteRecebido, arrayTaxas);

        double precoFinalComIVA = calculaPrecoFinalComIVA(taxaAplicada, this);

        return precoFinalComIVA;
    }

    /**
     * Cria Produto Alimentar com Taxa Normal
     *
     * @param arrayProdutos array de produtos
     * @return produto alimentar com taxa normal
     */
    protected ProdutoAlimentarTaxaNormal criaProdutoTaxaNormal(ArrayList<Produto> arrayProdutos){
        String tipoTaxa = "Normal";

        String[] arrayInformacoesProdutoAlimentar = obterInformacaoProdutoAlimentar(arrayProdutos);

        return new ProdutoAlimentarTaxaNormal(arrayInformacoesProdutoAlimentar[0], arrayInformacoesProdutoAlimentar[1], arrayInformacoesProdutoAlimentar[2], arrayInformacoesProdutoAlimentar[3], arrayInformacoesProdutoAlimentar[4], arrayInformacoesProdutoAlimentar[5], tipoTaxa);
    }

    protected String getTipo() {
        return "Produto Alimentar Taxa Normal";
    }
}