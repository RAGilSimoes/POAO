import java.io.Serializable;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * The type Produto.
 */
abstract class Produto implements Serializable {
    /**
     * The Codigo.
     */
    protected String codigo;
    /**
     * The Nome.
     */
    protected String nome;
    /**
     * The Descricao.
     */
    protected String descricao;
    /**
     * The Quantidade.
     */
    protected String quantidade;
    /**
     * The Valor sem iva.
     */
    protected String valorSemIVA;

    /**
     * Instantiates a new Produto.
     *
     * @param codigo      the codigo
     * @param nome        the nome
     * @param descricao   the descricao
     * @param quantidade  the quantidade
     * @param valorSemIVA the valor sem iva
     */
    public Produto(String codigo, String nome, String descricao, String quantidade, String valorSemIVA){
        this.codigo = codigo;
        this.nome = nome;
        this.descricao = descricao;
        this.quantidade = quantidade;
        this.valorSemIVA = valorSemIVA;
    }

    public String toString() {
        return ("Codigo -> " + this.codigo + "; Nome -> " + this.nome + "; Descricao -> " + this.descricao + "; Quantidade -> " + this.quantidade + "; Valor sem IVA -> " + this.valorSemIVA);
    }

    /**
     * Gets nome.
     *
     * @return the nome
     */
    public String getNome() {
        return nome;
    }

    /**
     * Gets codigo.
     *
     * @return the codigo
     */
    public String getCodigo() {
        return codigo;
    }

    /**
     * Gets descricao.
     *
     * @return the descricao
     */
    public String getDescricao() {
        return descricao;
    }

    /**
     * Gets quantidade.
     *
     * @return the quantidade
     */
    public String getQuantidade() {
        return quantidade;
    }

    /**
     * Gets valor sem iva.
     *
     * @return the valor sem iva
     */
    public String getValorSemIVA() {
        return valorSemIVA;
    }

    /**
     * Sets nome.
     *
     * @param nome the nome
     */
    public void setNome(String nome) {
        this.nome = nome;
    }

    /**
     * Sets codigo.
     *
     * @param codigo the codigo
     */
    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    /**
     * Sets descricao.
     *
     * @param descricao the descricao
     */
    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    /**
     * Sets quantidade.
     *
     * @param quantidade the quantidade
     */
    public void setQuantidade(String quantidade) {
        this.quantidade = quantidade;
    }

    /**
     * Sets valor sem iva.
     *
     * @param valorSemIVA the valor sem iva
     */
    public void setValorSemIVA(String valorSemIVA) {
        this.valorSemIVA = valorSemIVA;
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

    protected boolean verificaCodigo(String stringRecebida, ArrayList<Produto> arrayProdutos){
        FuncoesUteis funcoesUteis = new FuncoesUteis();
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
        double precoPorUnidade = Double.parseDouble(this.getValorSemIVA());
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
     * @return the string [ ]
     */
    protected String[] obterInformacoesProduto(ArrayList<Produto> arrayProdutos) {
        FuncoesUteis funcoesUteis = new FuncoesUteis();
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
            System.out.print("Introduza o preço sem IVA do produto: ");
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

