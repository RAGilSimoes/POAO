import java.io.Serializable;
import java.util.ArrayList;

/**
 * The type Produto alimentar taxa normal.
 */
public class ProdutoAlimentarTaxaNormal extends ProdutoAlimentar implements Serializable {
    /**
     * Instantiates a new Produto alimentar taxa normal.
     *
     * @param codigo       the codigo
     * @param nome         the nome
     * @param descricao    the descricao
     * @param quantidade   the quantidade
     * @param valorUnidade the valor sem iva
     * @param biologico    the biologico
     * @param taxa         the taxa
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
     * Cria produto taxa normal produto alimentar taxa normal.
     *
     * @param arrayProdutos the array produtos
     * @return the produto alimentar taxa normal
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
