import java.io.Serializable;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * The type Produto farmacia com prescricao.
 */
public class ProdutoFarmaciaComPrescricao extends ProdutoFarmacia implements Serializable {
    /**
     * The Medico.
     */
    protected String medico;

    /**
     * Instantiates a new Produto farmacia com prescricao.
     *
     * @param codigo       the codigo
     * @param nome         the nome
     * @param descricao    the descricao
     * @param quantidade   the quantidade
     * @param valorUnidade the valor sem iva
     * @param prescricao   the prescricao
     * @param medico       the medico
     */
    protected ProdutoFarmaciaComPrescricao(String codigo, String nome, String descricao, String quantidade, String valorUnidade, String prescricao, String medico){
        super(codigo, nome, descricao, quantidade, valorUnidade, prescricao);
        this.medico = medico;
    }

    /**
     * Gets medico.
     *
     * @return the medico
     */
    protected String getMedico() {
        return medico;
    }

    public String toString(){
        return (super.toString() + "; Médico -> " + this.getMedico());
    }

    /**
     * Sets medico.
     *
     * @param medico the medico
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
     * Cria produto com prescricao produto farmacia com prescricao.
     *
     * @param arrayProdutos the array produtos
     * @return the produto farmacia com prescricao
     */
    protected ProdutoFarmaciaComPrescricao criaProdutoComPrescricao(ArrayList<Produto> arrayProdutos){
        FuncoesUteis funcoesUteis = new FuncoesUteis();
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
