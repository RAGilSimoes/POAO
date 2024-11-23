package Projeto.src;

import java.util.Scanner;

/**
 * The type Produto farmacia com prescricao.
 */
public class ProdutoFarmaciaComPrescricao extends ProdutoFarmacia {
    /**
     * The Medico.
     */
    protected String medico;

    /**
     * Instantiates a new Produto farmacia com prescricao.
     *
     * @param codigo      the codigo
     * @param nome        the nome
     * @param descricao   the descricao
     * @param quantidade  the quantidade
     * @param valorSemIVA the valor sem iva
     * @param prescricao  the prescricao
     * @param medico      the medico
     */
    public ProdutoFarmaciaComPrescricao(String codigo, String nome, String descricao, String quantidade, String valorSemIVA, String prescricao, String medico){
        super(codigo, nome, descricao, quantidade, valorSemIVA, prescricao);
        this.medico = medico;
    }

    protected double obtemValorComIVA(Cliente clienteRecebido){
        final int[] arrayTaxas = {6,5,4};

        int taxaAplicada = getTaxaAplicada(clienteRecebido, arrayTaxas);

        double precoFinalComIVA = calculaPrecoFinalComIVA(taxaAplicada, this);
        return precoFinalComIVA;
    }

    /**
     * Cria produto com prescricao produto farmacia com prescricao.
     *
     * @return the produto farmacia com prescricao
     */
    protected ProdutoFarmaciaComPrescricao criaProdutoComPrescricao(){
        FuncoesUteis funcoesUteis = new FuncoesUteis();
        String prescricao = "Sim";
        boolean verificacaoNome = false;
        String medicoPrescritor = null;

        Scanner scannerObterResposta = new Scanner(System.in);

        String[] arrayInformacoesProdutoFarmacia = obterInformacaoProdutoFarmacia();

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
