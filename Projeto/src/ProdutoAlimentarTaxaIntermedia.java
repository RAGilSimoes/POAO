/**
 * @author Guilherme Carvalho e Ricardo Simoes
 * @version 1.0
 */

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Classe que representa o objeto Produto Alimentar com Taxa Intermedia
 */
public class ProdutoAlimentarTaxaIntermedia extends ProdutoAlimentar implements Serializable {
    /**
     * Objeto Funcoes Uteis
     */
    FuncoesUteis funcoesUteis = new FuncoesUteis();
    /**
     * Categoria
     */
    private String categoria;


    /**
     * Construtor do Produto Alimentar com Taxa Intermedia 
     *
     * @param codigo       codigo
     * @param nome         nome
     * @param descricao    descricao
     * @param quantidade   quantidade
     * @param valorUnidade valor sem iva
     * @param biologico    caracteristica biologica
     * @param taxa         taxa
     * @param categoria    categoria
     */
    protected ProdutoAlimentarTaxaIntermedia(String codigo, String nome, String descricao, String quantidade, String valorUnidade, String biologico, String taxa, String categoria){
        super(codigo, nome, descricao, quantidade, valorUnidade, biologico, taxa);
        this.categoria = categoria;
    }

    public String toString(){
        return (super.toString() + "; Categoria -> " + this.categoria);
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
     * Setter da categoria
     *
     * @param categoria categoria
     */
    protected void setCategoria(String categoria) {
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
     * Cria Produto Alimentar com Taxa Intermedia
     *
     * @param arrayProdutos array de produtos
     * @return produto alimentar com taxa intermedia
     */
    protected ProdutoAlimentarTaxaIntermedia criaProdutoTaxaIntermedia(ArrayList<Produto> arrayProdutos){
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