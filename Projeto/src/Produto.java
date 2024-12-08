import java.io.Serializable;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * The type Produto.
 */
abstract class Produto implements Serializable {
    FuncoesUteis funcoesUteis = new FuncoesUteis();
    /**
     * The Codigo.
     */
    private String codigo;
    /**
     * The Nome.
     */
    private String nome;
    /**
     * The Descricao.
     */
    private String descricao;
    /**
     * The Quantidade.
     */
    private String quantidade;
    /**
     * The Valor sem iva.
     */
    private String valorUnidade;

    /**
     * Instantiates a new Produto.
     *
     * @param codigo       the codigo
     * @param nome         the nome
     * @param descricao    the descricao
     * @param quantidade   the quantidade
     * @param valorUnidade the valor sem iva
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
     * Gets nome.
     *
     * @return the nome
     */
    protected String getNome() {
        return nome;
    }

    /**
     * Gets codigo.
     *
     * @return the codigo
     */
    protected String getCodigo() {
        return codigo;
    }

    /**
     * Gets descricao.
     *
     * @return the descricao
     */
    protected String getDescricao() {
        return descricao;
    }

    /**
     * Gets quantidade.
     *
     * @return the quantidade
     */
    protected String getQuantidade() {
        return quantidade;
    }

    /**
     * Gets valor sem iva.
     *
     * @return the valor sem iva
     */
    protected String getValorUnidade() {
        return valorUnidade;
    }

    /**
     * Sets nome.
     *
     * @param nome the nome
     */
    protected void setNome(String nome) {
        this.nome = nome;
    }

    /**
     * Sets codigo.
     *
     * @param codigo the codigo
     */
    protected void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    /**
     * Sets descricao.
     *
     * @param descricao the descricao
     */
    protected void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    /**
     * Sets quantidade.
     *
     * @param quantidade the quantidade
     */
    protected void setQuantidade(String quantidade) {
        this.quantidade = quantidade;
    }

    /**
     * Sets valor sem iva.
     *
     * @param valorUnidade the valor sem iva
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
     * Verifica codigo boolean.
     *
     * @param stringRecebida the string recebida
     * @param arrayProdutos  the array produtos
     * @return the boolean
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
     * Obtem valor sem iva double.
     *
     * @return the double
     */
    protected double obtemValorSemIVA(){
        int quantidade = Integer.parseInt(this.getQuantidade());
        double precoPorUnidade = Double.parseDouble(this.getValorUnidade());
        double valorSemIVA = (quantidade * precoPorUnidade);
        valorSemIVA = (Math.floor(valorSemIVA * 100) /100);
        return valorSemIVA;
    }

    /**
     * Obtem valor com iva double.
     *
     * @param clienteRecebido the cliente recebido
     * @return the double
     */
    protected abstract double obtemValorComIVA(Cliente clienteRecebido);

    /**
     * Obter informacoes produto string [ ].
     *
     * @param arrayProdutos the array produtos
     * @return the string [ ]
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
     * Gets tipo.
     *
     * @return the tipo
     */
    protected abstract String getTipo();

    /**
     * Get taxa aplicada int.
     *
     * @param clienteRecebido the cliente recebido
     * @param arrayTaxas      the array taxas
     * @return the int
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