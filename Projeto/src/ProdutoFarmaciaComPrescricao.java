/**
 * @author Guilherme Carvalho e Ricardo Simoes
 * @version 1.0
 */

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Classe que representa o objeto Produto de Farmacia Com Prescricao
 */
public class ProdutoFarmaciaComPrescricao extends ProdutoFarmacia implements Serializable {
    /**
     * Objeto Funcoes Uteis
     */
    FuncoesUteis funcoesUteis = new FuncoesUteis();
    /**
     * Medico
     */
    private String medico;

    /**
     * Construtor do Produto de Farmacia Com Prescricao
     *
     * @param codigo       codigo
     * @param nome         nome
     * @param descricao    descricao
     * @param quantidade   quantidade
     * @param valorUnidade valor sem iva
     * @param prescricao   prescricao
     * @param medico       medico
     */
    protected ProdutoFarmaciaComPrescricao(String codigo, String nome, String descricao, String quantidade, String valorUnidade, String prescricao, String medico){
        super(codigo, nome, descricao, quantidade, valorUnidade, prescricao);
        this.medico = medico;
    }

    /**
     * Getter do medico
     *
     * @return medico
     */
    protected String getMedico() {
        return medico;
    }

    public String toString(){
        return (super.toString() + "; Médico -> " + this.getMedico());
    }

    /**
     * Setter do medico
     *
     * @param medico medico
     */
    protected void setMedico(String medico) {
        this.medico = medico;
    }

    protected double obtemValorComIVA(Cliente clienteRecebido){
        final int[] arrayTaxas = {6,5,4};

        int taxaAplicada = getTaxaAplicada(clienteRecebido, arrayTaxas);

        double precoFinalComIVA = calculaPrecoFinalComIVA(taxaAplicada, this);
        return precoFinalComIVA;
    }

    /**
     * Cria um produto de farmacia com prescricao
     *
     * @param arrayProdutos array de produtos
     * @return produto de farmacia com prescricao
     */
    protected ProdutoFarmaciaComPrescricao criaProdutoComPrescricao(ArrayList<Produto> arrayProdutos){
        String prescricao = "Sim";
        boolean verificacaoNome;
        String medicoPrescritor;

        Scanner scannerObterResposta = new Scanner(System.in);

        String[] arrayInformacoesProdutoFarmacia = obterInformacaoProdutoFarmacia(arrayProdutos);

        do {
            System.out.print("Introduza o nome do médico que prescreveu o produto: ");
            medicoPrescritor = scannerObterResposta.nextLine();
            verificacaoNome = funcoesUteis.verificaNome(medicoPrescritor);
        } while(!verificacaoNome);

        return new ProdutoFarmaciaComPrescricao(arrayInformacoesProdutoFarmacia[0], arrayInformacoesProdutoFarmacia[1], arrayInformacoesProdutoFarmacia[2], arrayInformacoesProdutoFarmacia[3], arrayInformacoesProdutoFarmacia[4], prescricao, medicoPrescritor);
    }

    protected String getTipo() {
        return "Produto Farmacia Com Prescricao";
    }
}