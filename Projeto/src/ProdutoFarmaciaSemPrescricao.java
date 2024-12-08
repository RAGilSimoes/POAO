import java.io.Serializable;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * The type Produto farmacia sem prescricao.
 */
public class ProdutoFarmaciaSemPrescricao extends ProdutoFarmacia implements Serializable {
    /**
     * The Categoria.
     */
    private String categoria;

    /**
     * Instantiates a new Produto farmacia sem prescricao.
     *
     * @param codigo       the codigo
     * @param nome         the nome
     * @param descricao    the descricao
     * @param quantidade   the quantidade
     * @param valorUnidade the valor sem iva
     * @param prescricao   the prescricao
     * @param categoria    the categoria
     */
    protected ProdutoFarmaciaSemPrescricao(String codigo, String nome, String descricao, String quantidade, String valorUnidade, String prescricao, String categoria){
        super(codigo, nome, descricao, quantidade, valorUnidade, prescricao);
        this.categoria  = categoria;
    }

    public String toString(){
        return (super.toString() + "; Categoria -> " + this.getCategoria());
    }

    /**
     * Gets categoria.
     *
     * @return the categoria
     */
    protected String getCategoria() {
        return categoria;
    }

    /**
     * Sets categoria.
     *
     * @param categoria the categoria
     */
    protected void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    protected double obtemValorComIVA(Cliente clienteRecebido){
        final int[] arrayTaxas = {23,23,23};
        final int reducaoTaxa = -1;
        int taxaAplicada = getTaxaAplicada(clienteRecebido, arrayTaxas);

        String categoria = getCategoria();
        if(categoria.equalsIgnoreCase("animais")){
            taxaAplicada += reducaoTaxa;
        }

        double precoFinalComIVA = calculaPrecoFinalComIVA(taxaAplicada, this);
        return precoFinalComIVA;
    }

    /**
     * Cria produto sem prescricao produto farmacia sem prescricao.
     *
     * @param arrayProdutos the array produtos
     * @return the produto farmacia sem prescricao
     */
    protected ProdutoFarmaciaSemPrescricao criaProdutoSemPrescricao(ArrayList<Produto> arrayProdutos){
        String prescricao = "Nao";
        Scanner scannerObterResposta = new Scanner(System.in);

        String[] arrayInformacoesProdutoFarmacia = obterInformacaoProdutoFarmacia(arrayProdutos);

        System.out.print("Introduza a categoria do produto: ");
        String categoriaProduto = scannerObterResposta.nextLine();

        return new ProdutoFarmaciaSemPrescricao(arrayInformacoesProdutoFarmacia[0], arrayInformacoesProdutoFarmacia[1], arrayInformacoesProdutoFarmacia[2], arrayInformacoesProdutoFarmacia[3], arrayInformacoesProdutoFarmacia[4], prescricao, categoriaProduto);
    }

    protected String getTipo() {
        return "Produto Farmacia Sem Prescricao";
    }
}