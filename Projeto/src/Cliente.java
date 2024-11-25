import java.io.Serializable;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * The type Cliente.
 */
public class Cliente implements Serializable {
    /**
     * The Nome.
     */
    protected String nome;
    /**
     * The Nif.
     */
    protected String nif;
    /**
     * The Localizacao.
     */
    protected String localizacao;

    /**
     * Instantiates a new Cliente.
     *
     * @param nome        the nome
     * @param nif         the nif
     * @param localizacao the localizacao
     */
    public Cliente(String nome, String nif, String localizacao) {
        this.nome = nome;
        this.nif = nif;
        this.localizacao = localizacao;
    }

    public String toString() {
        return ("Nome -> " + this.nome + "; NIF -> " + this.nif + "; Localização -> " + this.localizacao);
    }

    /**
     * Gets localizacao.
     *
     * @return the localizacao
     */
    public String getLocalizacao() {
        return localizacao;
    }

    /**
     * Gets nif.
     *
     * @return the nif
     */
    public String getNif() {
        return nif;
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
     * Sets localizacao.
     *
     * @param localizacao the localizacao
     */
    public void setLocalizacao(String localizacao) {
        this.localizacao = localizacao;
    }

    /**
     * Sets nif.
     *
     * @param nif the nif
     */
    public void setNif(String nif) {
        this.nif = nif;
    }

    /**
     * Sets nome.
     *
     * @param nome the nome
     */
    public void setNome(String nome) {
        this.nome = nome;
    }


    protected boolean existeNif(String nifProcurar, ArrayList<Cliente> arrayClientes){
        boolean nifValido = true;
        for (Cliente cliente: arrayClientes){
            String nifExistente = cliente.getNif();
            if (nifExistente.equals(nifProcurar)){
                System.out.println("\nNif do cliente já existente.");
                nifValido = false;
            }
        }
        return nifValido;
    }

    protected boolean verificaNif(String nif, ArrayList<Cliente> arrayClientes){
        FuncoesUteis funcoesUteis = new FuncoesUteis();
        boolean verificacao;
        if (nif.length() != 9 || funcoesUteis.verificaCaracteres(nif,'0', '9')){
            System.out.println("\nO nif introduzido não é válido");
            verificacao = false;
        } else {
            verificacao = existeNif(nif, arrayClientes);
        }
        return verificacao;
    }


    protected boolean escolherLocalizao(String escolha){
        boolean verificacao = true;

        switch(escolha){
            case "Continente":
                escolha = "Continente";
                break;
            case "Madeira":
                escolha = "Madeira";
                break;
            case "Açores":
                escolha = "Açores";
                break;
            default:
                System.out.println("Introduza uma opção válida");
                verificacao = false;
                break;
        }

        return verificacao;
    }

    /**
     * Cria cliente cliente.
     *
     * @param arrayClientes the array clientes
     * @return the cliente
     */
    protected Cliente criaCliente(ArrayList<Cliente> arrayClientes) {
        FuncoesUteis funcoesUteis = new FuncoesUteis();
        Scanner scannerCliente = new Scanner(System.in);

        String nome = null;
        String nif = null;
        String escolhaLocalizacao = null;

        boolean verificacaoNome = false;
        boolean verificacaoNIF = false;
        boolean verificacaoLocalizacao = false;

        while (!verificacaoNome) {
            System.out.print("\nIntroduza o nome do cliente: ");
            nome = scannerCliente.nextLine();
            verificacaoNome = funcoesUteis.verificaNome(nome);
        }


        while (!verificacaoNIF) {
            System.out.print("Introduza o nif do cliente: ");
            nif = scannerCliente.nextLine();
            verificacaoNIF = verificaNif(nif, arrayClientes);
        }

        while(!verificacaoLocalizacao){
            System.out.print("\nEscolha a localização do cliente: \nContinente | Madeira | Açores\n");
            escolhaLocalizacao = scannerCliente.nextLine();
            verificacaoLocalizacao = escolherLocalizao(escolhaLocalizacao);
        }

        return new Cliente(nome, nif, escolhaLocalizacao);
    }

    private void alteraInformacaoCliente(String tipo, Cliente clienteRecebido, ArrayList<Cliente> arrayClientes){
        FuncoesUteis funcoesUteis = new FuncoesUteis();
        Scanner editarCliente = new Scanner(System.in);

        switch (tipo){
            case "Nome":
                String nome = null;
                boolean verificacaoNome = false;
                while (!verificacaoNome) {
                    System.out.print("\nIntroduza o novo nome do cliente: ");
                    nome = editarCliente.nextLine();
                    if (nome.equals("0")) {
                        System.out.println("Nome não alterado.");
                        break;
                    } else {
                        verificacaoNome = funcoesUteis.verificaNome(nome);
                        if(verificacaoNome){
                            clienteRecebido.setNome(nome);
                            System.out.println("Nome alterado com sucesso.");
                        }
                    }
                }
                break;

            case "NIF":
                String nif = null;
                boolean verificacaoNif = false;
                while (!verificacaoNif) {
                    System.out.print("\nIntroduza o novo nif do cliente: ");
                    nif= editarCliente.nextLine();
                    if (nif.equals("0")){
                        System.out.println("Nif não alterado.");
                        break;
                    }else{
                        verificacaoNif = clienteRecebido.verificaNif(nif, arrayClientes);
                        if(verificacaoNif){
                            clienteRecebido.setNif(nif);
                            System.out.println("Nif alterado com sucesso.");
                        }
                    }
                }
                break;

            case "Localizacao":
                String escolhaLocalizacao = null;
                boolean verificacaoLocalizacao = false;
                while(!verificacaoLocalizacao){
                    System.out.print("\nEscolha a nova localização do cliente: \nPortugal | Madeira | Açores\n");
                    escolhaLocalizacao = editarCliente.nextLine();
                    if (escolhaLocalizacao.equals("0")){
                        System.out.println("Localização não alterada.");
                        break;
                    }else{
                        System.out.print("\nEscolha a localização do cliente: \nContinente | Madeira | Açores\n");
                        verificacaoLocalizacao = escolherLocalizao(escolhaLocalizacao);
                        if(verificacaoLocalizacao) {
                            clienteRecebido.setLocalizacao(escolhaLocalizacao);
                            System.out.println("Localização alterada com sucesso.");
                        }
                    }
                }
                break;

            default:
                break;
        }
    }


    /**
     * Altera informacoes cliente.
     *
     * @param clienteRecebido the cliente recebido
     * @param arrayClientes   the array clientes
     */
    protected void alteraInformacoesCliente(Cliente clienteRecebido, ArrayList<Cliente> arrayClientes) {
        System.out.print("\n(Pressione 0 para não alterar alguma informacão)");
        alteraInformacaoCliente("Nome", clienteRecebido, arrayClientes);
        alteraInformacaoCliente("NIF", clienteRecebido, arrayClientes);
        alteraInformacaoCliente("Localizacao", clienteRecebido, arrayClientes);
    }
}
