/**
 * @author Guilherme Carvalho e Ricardo Simoes
 * @version 1.0
 */

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Classe que representa o objeto Produto
 */
abstract class Produto implements Serializable {
    /**
     * Objeto Funcoes Uteis
     */
    FuncoesUteis funcoesUteis = new FuncoesUteis();
    /**
     * Codigo
     */
    private String codigo;
    /**
     * Nome
     */
    private String nome;
    /**
     * Descricao
     */
    private String descricao;
    /**
     * Quantidade
     */
    private String quantidade;
    /**
     * Valor sem iva
     */
    private String valorUnidade;

    /**
     * Instantiates a new Produto
     *
     * @param codigo       codigo
     * @param nome         nome
     * @param descricao    descricao
     * @param quantidade   quantidade
     * @param valorUnidade valor sem iva
     */
    protected Produto(String codigo, String nome, String descricao, String quantidade, String valorUnidade){
        this.codigo = codigo;
        this.nome = nome;
        this.descricao = descricao;
        this.quantidade = quantidade;
        this.valorUnidade = valorUnidade;
    }

    public String toString() {
        return ("Codigo -> " + this.codigo + "; Nome -> " + this.nome + "; Descricao -> " + this.descricao + "; Quantidade -> " + this.quantidade + "; Valor por Unidade -> " + this.valorUnidade);
    }

    /**
     * Getter do nome
     *
     * @return nome
     */
    protected String getNome() {
        return nome;
    }

    /**
     * Getter do codigo
     *
     * @return codigo
     */
    protected String getCodigo() {
        return codigo;
    }

    /**
     * Getter da descricao
     *
     * @return descricao
     */
    protected String getDescricao() {
        return descricao;
    }

    /**
     * Getter da quantidade
     *
     * @return quantidade
     */
    protected String getQuantidade() {
        return quantidade;
    }

    /**
     * Getter do valor sem iva
     *
     * @return valor sem iva
     */
    protected String getValorUnidade() {
        return valorUnidade;
    }

    /**
     * Setter do nome
     *
     * @param nome nome
     */
    protected void setNome(String nome) {
        this.nome = nome;
    }

    /**
     * Setter do codigo
     *
     * @param codigo codigo
     */
    protected void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    /**
     * Setter da descricao
     *
     * @param descricao descricao
     */
    protected void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    /**
     * Setter da quantidade
     *
     * @param quantidade quantidade
     */
    protected void setQuantidade(String quantidade) {
        this.quantidade = quantidade;
    }

    /**
     * Setter do valor sem iva
     *
     * @param valorUnidade valor sem iva
     */
    protected void setValorUnidade(String valorUnidade) {
        this.valorUnidade = valorUnidade;
    }

    private boolean existeCodigo(ArrayList<Produto> arrayProdutos, String codigoProcurar) {
        boolean codigoValido = true;
        for (Produto produto: arrayProdutos){
            String nProdutoExistente = produto.getCodigo();
            if (nProdutoExistente.equals(codigoProcurar)){
                System.out.println("\nCodigo de produto ja existente.");
                codigoValido = false;
            }
        }
        return codigoValido;
    }

    /**
     * Verifica a validade do codigo
     *
     * @param stringRecebida string recebida
     * @param arrayProdutos  array de produtos
     * @return devolve booleano consoante a validade do codigo
     */
    protected boolean verificaCodigo(String stringRecebida, ArrayList<Produto> arrayProdutos){
        boolean verificacao = true;
        if(stringRecebida.isEmpty()){
            System.out.println("\nO código introduzido não é válido.");
            verificacao = false;
        } else {
            for(int i = 0; i < stringRecebida.length(); i++){
                if (funcoesUteis.verificaCaracteres(stringRecebida,'0', '9')){
                    verificacao = false;
                }
            }
            if (!verificacao){
                System.out.println("\nO código introduzido não é válido.");
                verificacao = false;
            } else {
                verificacao = existeCodigo(arrayProdutos, stringRecebida);
            }
        }
        return verificacao;
    }

    private String verificaDescricao(String stringRecebida) {
        String descricao;
        if(stringRecebida.isEmpty()){
            descricao = "Este produto não tem descrição";
        } else {
            descricao = stringRecebida;
        }
        return descricao;
    }


    /**
     * Obtem valor sem iva
     *
     * @return valor sem iva
     */
    protected double obtemValorSemIVA(){
        int quantidade = Integer.parseInt(this.getQuantidade());
        double precoPorUnidade = Double.parseDouble(this.getValorUnidade());
        double valorSemIVA = (quantidade * precoPorUnidade);
        valorSemIVA = (Math.floor(valorSemIVA * 100) /100);
        return valorSemIVA;
    }

    /**
     * Obtem valor com iva
     *
     * @param clienteRecebido cliente recebido
     * @return valor com iva
     */
    protected abstract double obtemValorComIVA(Cliente clienteRecebido);

    /**
     * Obter informacoes do produto
     *
     * @param arrayProdutos array de produtos
     * @return informacoes do produto
     */
    protected String[] obterInformacoesProduto(ArrayList<Produto> arrayProdutos) {
        Scanner scannerObterResposta = new Scanner(System.in);

        String codigoProduto = null;
        String quantidadeProduto = null;
        String precoSemIVAProduto = null;
        String nomeProduto = null;

        boolean verificaoCodigo = false;
        boolean verificacaoQuantidade = false;
        boolean verificacaoPrecoSemIVA = false;
        boolean verificaNome = false;

        while(!verificaoCodigo){
            System.out.print("\nIntroduza o código do produto: ");
            codigoProduto = scannerObterResposta.nextLine();
            verificaoCodigo = verificaCodigo(codigoProduto, arrayProdutos);
        }

        while(!verificaNome){
            System.out.print("Introduza o nome do produto: ");
            nomeProduto = scannerObterResposta.nextLine();
            verificaNome = funcoesUteis.verificaNome(nomeProduto);
        }

        System.out.print("Introduza a descricao do produto: ");
        String descricaoProduto = scannerObterResposta.nextLine();
        descricaoProduto = verificaDescricao(descricaoProduto);

        while (!verificacaoQuantidade) {
            System.out.print("Introduza a quantidade do produto (mínimo 1): ");
            quantidadeProduto = scannerObterResposta.nextLine();
            verificacaoQuantidade = funcoesUteis.verificaInt(quantidadeProduto);
            if(!verificacaoQuantidade){
                System.out.println("\nO valor introduzido não é válido.");
            } else {
                if(quantidadeProduto.equals("0")){
                    System.out.println("\nO valor introduzido não é válido.");
                    verificacaoQuantidade = false;
                }
            }
        }

        while(!verificacaoPrecoSemIVA){
            System.out.print("Introduza o valor por unidade do produto: ");
            precoSemIVAProduto = scannerObterResposta.nextLine();
            verificacaoPrecoSemIVA = funcoesUteis.verificaDouble(precoSemIVAProduto);
        }

        return new String[]{codigoProduto, nomeProduto, descricaoProduto, quantidadeProduto, precoSemIVAProduto};
    }

    /**
     * Getter do tipo.
     *
     * @return tipo
     */
    protected abstract String getTipo();

    /**
     * Verifica qual a taxa aplicada
     *
     * @param clienteRecebido cliente recebido
     * @param arrayTaxas      array de taxas
     * @return taxa aplicada
     */
    protected int getTaxaAplicada(Cliente clienteRecebido, int[] arrayTaxas){
        int taxaAplicada = 0;
        String localizacaoCliente = clienteRecebido.getLocalizacao();
        switch (localizacaoCliente) {
            case "Continente":
                taxaAplicada = arrayTaxas[0];
                break;

            case "Madeira":
                taxaAplicada = arrayTaxas[1];
                break;

            case "Açores":
                taxaAplicada = arrayTaxas[2];
                break;

            default:
                break;
        }

        return taxaAplicada;
    }
}