import java.io.Serializable;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * The type Produto alimentar taxa intermedia.
 */
public class ProdutoAlimentarTaxaIntermedia extends ProdutoAlimentar implements Serializable {
    /**
     * The Categoria.
     */
    protected String categoria;


    /**
     * Instantiates a new Produto alimentar taxa intermedia.
     *
     * @param codigo      the codigo
     * @param nome        the nome
     * @param descricao   the descricao
     * @param quantidade  the quantidade
     * @param valorUnidade the valor sem iva
     * @param biologico   the biologico
     * @param taxa        the taxa
     * @param categoria   the categoria
     */
    public ProdutoAlimentarTaxaIntermedia(String codigo, String nome, String descricao, String quantidade, String valorUnidade, String biologico, String taxa, String categoria){
        super(codigo, nome, descricao, quantidade, valorUnidade, biologico, taxa);
        this.categoria = categoria;
    }

    public String toString(){
        return (super.toString() + "; Categoria -> " + this.categoria);
    }

    /**
     * Gets categoria.
     *
     * @return the categoria
     */
    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    protected double obtemValorComIVA(Cliente clienteRecebido){
        final int[] arrayTaxas = {13,12,9};
        final int aumentoTaxa = 1;
        int taxaAplicada = this.getTaxaAplicada(clienteRecebido, arrayTaxas);

        String categoria = this.getCategoria();
        if(categoria.equalsIgnoreCase("vinho")){
            taxaAplicada += aumentoTaxa;
        }

        double precoFinalComIVA = this.calculaPrecoFinalComIVA(taxaAplicada, this);

        return precoFinalComIVA;
    }

    /**
     * Cria produto taxa intermedia produto alimentar taxa intermedia.
     *
     * @return the produto alimentar taxa intermedia
     */
    protected ProdutoAlimentarTaxaIntermedia criaProdutoTaxaIntermedia(ArrayList<Produto> arrayProdutos){
        FuncoesUteis funcoesUteis = new FuncoesUteis();
        String tipoTaxa = "Intermedia";
        String categoriaProduto = null;

        Scanner scannerObterResposta = new Scanner(System.in);

        boolean verificacaoCategoria = false;

        String[] arrayInformacoesProdutoAlimentar = this.obterInformacaoProdutoAlimentar(arrayProdutos);

        while(!verificacaoCategoria){
            System.out.print("Introduza a categoria do produto: ");
            categoriaProduto = scannerObterResposta.nextLine();
            verificacaoCategoria = funcoesUteis.verificaCategoria(categoriaProduto);
        }

        return new ProdutoAlimentarTaxaIntermedia(arrayInformacoesProdutoAlimentar[0], arrayInformacoesProdutoAlimentar[1], arrayInformacoesProdutoAlimentar[2], arrayInformacoesProdutoAlimentar[3], arrayInformacoesProdutoAlimentar[4], arrayInformacoesProdutoAlimentar[5], tipoTaxa, categoriaProduto);
    }

    protected String getTipo() {
        return "Produto Alimentar Taxa Intermedia";
    }
}
