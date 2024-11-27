import java.io.Serializable;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * The type Produto alimentar taxa reduzida.
 */
public class ProdutoAlimentarTaxaReduzida extends ProdutoAlimentar implements Serializable {
    /**
     * The Quantidade certificacoes.
     */
    protected int quantidadeCertificacoes;
    /**
     * The Certificacoes.
     */
    protected ArrayList<String> certificacoes;

    /**
     * Instantiates a new Produto alimentar taxa reduzida.
     *
     * @param codigo                  the codigo
     * @param nome                    the nome
     * @param descricao               the descricao
     * @param quantidade              the quantidade
     * @param valorSemIVA             the valor sem iva
     * @param biologico               the biologico
     * @param taxa                    the taxa
     * @param quantidadeCertificacoes the quantidade certificacoes
     * @param certificacoes           the certificacoes
     */
    public ProdutoAlimentarTaxaReduzida(String codigo, String nome, String descricao, String quantidade, String valorSemIVA, String biologico, String taxa, int quantidadeCertificacoes, ArrayList<String> certificacoes){
        super(codigo, nome, descricao, quantidade, valorSemIVA, biologico, taxa);
        this.quantidadeCertificacoes = quantidadeCertificacoes;
        this.certificacoes = certificacoes;
    }

    public String toString(){
        return (super.toString() + "; Certificacoes -> " + this.certificacoes);
    }

    /**
     * Gets quantidade certificacoes.
     *
     * @return the quantidade certificacoes
     */
    public int getQuantidadeCertificacoes() {
        return quantidadeCertificacoes;
    }

    /**
     * Get certificacoes string [ ].
     *
     * @return the string [ ]
     */
    public ArrayList<String> getCertificacoes() {
        return certificacoes;
    }

    /**
     * Sets quantidade certificacoes.
     *
     * @param quantidadeCertificacoes the quantidade certificacoes
     */
    public void setQuantidadeCertificacoes(int quantidadeCertificacoes) {
        this.quantidadeCertificacoes = quantidadeCertificacoes;
    }

    /**
     * Sets certificacoes.
     *
     * @param certificacoes the certificacoes
     */
    public void setCertificacoes(ArrayList<String> certificacoes) {
        if(certificacoes != null){
            this.certificacoes = certificacoes;
        }
    }

    private ArrayList<String> obtemCertificacoes(){
        Scanner scannerObterResposta = new Scanner(System.in);
        String[] arrayCertificacoesDisponiveis = {"ISO22000", "FSSC22000", "HACCP", "GMP"};
        int controloCertificacoes = 0;

        String escolha = null;

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
     * Cria produto taxa reduzida produto alimentar taxa reduzida.
     *
     * @return the produto alimentar taxa reduzida
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