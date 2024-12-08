/**
 * @author Guilherme Carvalho e Ricardo Simoes
 * @version 1.0
 */

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Classe que representa o objeto Produto Alimentar com Taxa Reduzida
 */
public class ProdutoAlimentarTaxaReduzida extends ProdutoAlimentar implements Serializable {
    /**
     * Quantidade de certificacoes
     */
    private int quantidadeCertificacoes;
    /**
     * Certificacoes
     */
    private ArrayList<String> certificacoes;

    /**
     * Construtor do Produto Alimentar com Taxa Reduzida
     *
     * @param codigo                  codigo
     * @param nome                    nome
     * @param descricao               descricao
     * @param quantidade              quantidade
     * @param valorUnidade            valor sem iva
     * @param biologico               caracteristica biologica
     * @param taxa                    taxa
     * @param quantidadeCertificacoes quantidade de certificacoes
     * @param certificacoes           certificacoes
     */
    protected ProdutoAlimentarTaxaReduzida(String codigo, String nome, String descricao, String quantidade, String valorUnidade, String biologico, String taxa, int quantidadeCertificacoes, ArrayList<String> certificacoes){
        super(codigo, nome, descricao, quantidade, valorUnidade, biologico, taxa);
        this.quantidadeCertificacoes = quantidadeCertificacoes;
        this.certificacoes = certificacoes;
    }

    public String toString(){
        return (super.toString() + "; Certificacoes -> " + this.certificacoes);
    }

    /**
     * Getter da quantidade de certificacoes
     *
     * @return quantidade de certificacoes
     */
    protected int getQuantidadeCertificacoes() {
        return quantidadeCertificacoes;
    }

    /**
     * Getter certificacoes
     *
     * @return certificacoes
     */
    protected ArrayList<String> getCertificacoes() {
        return certificacoes;
    }

    /**
     * Setter da quantidade de certificacoes
     *
     * @param quantidadeCertificacoes quantidade de certificacoes
     */
    protected void setQuantidadeCertificacoes(int quantidadeCertificacoes) {
        this.quantidadeCertificacoes = quantidadeCertificacoes;
    }

    /**
     * Setter das certificacoes
     *
     * @param certificacoes certificacoes
     */
    protected void setCertificacoes(ArrayList<String> certificacoes) {
        if(certificacoes != null){
            this.certificacoes = certificacoes;
        }
    }

    /**
     * Verifica a validade da escolha da certificacao
     *
     * @param certificacoes certificacoes
     * @return devolve booleano consoante a validade da escolha da certificacao
     */
    protected boolean verificaCertificacoes(ArrayList<String> certificacoes) {
        boolean verificacao = false;
        try{
            for(String string: certificacoes){
                switch (string) {
                    case "ISO22000", "FSSC22000", "HACCP", "GMP":
                        verificacao = true;
                        break;

                    default:
                        throw new IOException();
                }
            }
        } catch (IOException exception) {
            verificacao = false;
        }
        return verificacao;
    }

    private ArrayList<String> obtemCertificacoes(){
        Scanner scannerObterResposta = new Scanner(System.in);
        String[] arrayCertificacoesDisponiveis = {"ISO22000", "FSSC22000", "HACCP", "GMP"};
        int controloCertificacoes = 0;

        String escolha;

        ArrayList<String> arrayCertificacoes = new ArrayList<String>();

        do {
            System.out.printf("\nPretende introduzir a certificação %s? \n1-> Sim | 2-> Nao: ", arrayCertificacoesDisponiveis[controloCertificacoes]);
            escolha = scannerObterResposta.nextLine();
            switch (escolha) {
                case "1":
                    arrayCertificacoes.add(arrayCertificacoesDisponiveis[controloCertificacoes]);
                    controloCertificacoes++;
                    break;
                case "2":
                    controloCertificacoes++;
                    break;
                default:
                    System.out.println("Caracter inválido.");
                    break;
            }
        } while(controloCertificacoes < arrayCertificacoesDisponiveis.length);

        return arrayCertificacoes;
    }

    protected double obtemValorComIVA(Cliente clienteRecebido){
        final int[] arrayTaxas = {6,5,4};
        final int reducaoTaxa = -1;
        int taxaAplicada = getTaxaAplicada(clienteRecebido, arrayTaxas);

        if(getQuantidadeCertificacoes() == 4){
            taxaAplicada += reducaoTaxa;
        }

        double precoFinalComIVA = calculaPrecoFinalComIVA(taxaAplicada, this);

        return precoFinalComIVA;
    }


    /**
     * Cria um produto alimentar com taxa reduzida
     *
     * @param arrayProdutos array de produtos
     * @return produto alimentar com taxa reduzida
     */
    protected ProdutoAlimentarTaxaReduzida criaProdutoTaxaReduzida(ArrayList<Produto> arrayProdutos){
        String tipoTaxa = "Reduzida";

        String[] arrayInformacoesProdutoAlimentar = obterInformacaoProdutoAlimentar(arrayProdutos);

        ArrayList<String> arrayCertificacoes = obtemCertificacoes();

        int quantidadeCertificacoes = arrayCertificacoes.size();

        return new ProdutoAlimentarTaxaReduzida(arrayInformacoesProdutoAlimentar[0], arrayInformacoesProdutoAlimentar[1], arrayInformacoesProdutoAlimentar[2], arrayInformacoesProdutoAlimentar[3], arrayInformacoesProdutoAlimentar[4], arrayInformacoesProdutoAlimentar[5], tipoTaxa, quantidadeCertificacoes, arrayCertificacoes);
    }

    protected String getTipo() {
        return "Produto Alimentar Taxa Reduzida";
    }
}