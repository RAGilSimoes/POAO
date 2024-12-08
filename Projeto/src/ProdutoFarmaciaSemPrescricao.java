/**
 * @author Guilherme Carvalho e Ricardo Simoes
 * @version 1.0
 */

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Classe que representa o objeto Produto de Farmacia Sem Prescricao
 */
public class ProdutoFarmaciaSemPrescricao extends ProdutoFarmacia implements Serializable {
    /**
     * Objeto Funcoes Uteis
     */
    FuncoesUteis funcoesUteis = new FuncoesUteis();
    /**
     * Categoria
     */
    private String categoria;

    /**
     * Construtor do Produto de Farmacia Sem Prescricao
     *
     * @param codigo       codigo
     * @param nome         nome
     * @param descricao    descricao
     * @param quantidade   quantidade
     * @param valorUnidade valor sem iva
     * @param prescricao   prescricao
     * @param categoria    categoria
     */
    protected ProdutoFarmaciaSemPrescricao(String codigo, String nome, String descricao, String quantidade, String valorUnidade, String prescricao, String categoria){
        super(codigo, nome, descricao, quantidade, valorUnidade, prescricao);
        this.categoria  = categoria;
    }

    public String toString(){
        return (super.toString() + "; Categoria -> " + this.getCategoria());
    }

    /**
     * Getter da categoria
     *
     * @return categoria
     */
    protected String getCategoria() {
        return categoria;
    }

    /**
     * Setter categoria
     *
     * @param categoria categoria
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
     * Cria um produto de farmacia sem prescricao
     *
     * @param arrayProdutos array de produtos
     * @return produto de farmacia sem prescricao
     */
    protected ProdutoFarmaciaSemPrescricao criaProdutoSemPrescricao(ArrayList<Produto> arrayProdutos){
        String prescricao = "Nao";
        String categoriaProduto = null;
        Scanner scannerObterResposta = new Scanner(System.in);

        boolean verificacaoCategoria = false;

        String[] arrayInformacoesProdutoFarmacia = obterInformacaoProdutoFarmacia(arrayProdutos);

        while(!verificacaoCategoria){
            System.out.print("Introduza a categoria do produto: ");
            categoriaProduto = scannerObterResposta.nextLine();
            verificacaoCategoria = funcoesUteis.verificaCategoria(categoriaProduto);
        }

        return new ProdutoFarmaciaSemPrescricao(arrayInformacoesProdutoFarmacia[0], arrayInformacoesProdutoFarmacia[1], arrayInformacoesProdutoFarmacia[2], arrayInformacoesProdutoFarmacia[3], arrayInformacoesProdutoFarmacia[4], prescricao, categoriaProduto);
    }

    protected String getTipo() {
        return "Produto Farmacia Sem Prescricao";
    }
}