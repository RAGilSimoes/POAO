import java.io.Serializable;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Classe que representa o objeto cliente
 */
public class Cliente implements Serializable {
    FuncoesUteis funcoesUteis = new FuncoesUteis();
    /**
     * Nome
     */
    private String nome;
    /**
     * Nif
     */
    private String nif;
    /**
     * Localizacao
     */
    private String localizacao;

    /**
     * Construtor do Cliente
     *
     * @param nome        nome
     * @param nif         nif
     * @param localizacao localizacao
     */
    protected Cliente(String nome, String nif, String localizacao) {
        this.nome = nome;
        this.nif = nif;
        this.localizacao = localizacao;
    }

    public String toString() {
        return ("Nome -> " + this.nome + "; NIF -> " + this.nif + "; Localização -> " + this.localizacao);
    }

    /**
     * Getter da localizacao.
     *
     * @return localizacao
     */
    protected String getLocalizacao() {
        return localizacao;
    }

    /**
     * Getter do nif.
     *
     * @return nif
     */
    protected String getNif() {
        return nif;
    }

    /**
     * Getter do nome.
     *
     * @return nome
     */
    protected String getNome() {
        return nome;
    }

    /**
     * Setter da localizacao.
     *
     * @param localizacao localizacao
     */
    protected void setLocalizacao(String localizacao) {
        this.localizacao = localizacao;
    }

    /**
     * Setter do nif.
     *
     * @param nif  nif
     */
    protected void setNif(String nif) {
        this.nif = nif;
    }

    /**
     * Setter do nome.
     *
     * @param nome  nome
     */
    protected void setNome(String nome) {
        this.nome = nome;
    }


    /**
     * Verifica a existencia do nif
     *
     * @param nifProcurar    nif procurar
     * @param arrayClientes  array clientes
     * @return  devolve booleano consoante a existencia do nif
     */
    private boolean existeNif(String nifProcurar, ArrayList<Cliente> arrayClientes){
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

    /**
     * Verifica a validade do nif
     *
     * @param nif            nif
     * @param arrayClientes  array clientes
     * @return  devolve booleano consoante a validade do nif
     */
    protected boolean verificaNif(String nif, ArrayList<Cliente> arrayClientes){
        boolean verificacao;
        if (nif.length() != 9 || funcoesUteis.verificaCaracteres(nif,'0', '9')){
            System.out.println("\nO nif introduzido não é válido");
            verificacao = false;
        } else {
            verificacao = existeNif(nif, arrayClientes);
        }
        return verificacao;
    }


    /**
     * Escolher localizao
     *
     * @param escolha  escolha
     * @return  devolve booleano consoante a validade da escolha da localizacao
     */
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
     * Cria cliente
     *
     * @param arrayClientes  array de clientes
     * @return  cliente
     */
    protected Cliente criaCliente(ArrayList<Cliente> arrayClientes) {
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
        Scanner editarCliente = new Scanner(System.in);

        switch (tipo){
            case "Nome":
                String nome = null;
                boolean verificacaoNome = false;
                while (!verificacaoNome) {
                    System.out.print("\nIntroduza o novo nome do cliente: ");
                    nome = editarCliente.nextLine();
                    verificacaoNome = funcoesUteis.verificaNome(nome);
                    if(verificacaoNome){
                        clienteRecebido.setNome(nome);
                        System.out.println("Nome alterado com sucesso.");
                    }
                }
                break;

            case "NIF":
                String nif;
                boolean verificacaoNif = false;
                while (!verificacaoNif) {
                    System.out.print("\nIntroduza o novo nif do cliente: ");
                    nif= editarCliente.nextLine();
                    verificacaoNif = clienteRecebido.verificaNif(nif, arrayClientes);
                    if(verificacaoNif){
                        clienteRecebido.setNif(nif);
                        System.out.println("Nif alterado com sucesso.");
                        verificacaoNif = true;
                    }
                }
                break;

            case "Localizacao":
                String escolhaLocalizacao;
                boolean verificacaoLocalizacao = false;
                while(!verificacaoLocalizacao){
                    System.out.print("\nEscolha a localização do cliente: \nContinente | Madeira | Açores\n");
                    escolhaLocalizacao = editarCliente.nextLine();
                    verificacaoLocalizacao = escolherLocalizao(escolhaLocalizacao);
                    if(verificacaoLocalizacao) {
                        clienteRecebido.setLocalizacao(escolhaLocalizacao);
                        System.out.println("Localização alterada com sucesso.");
                    }
                }
                break;

            default:
                break;
        }
    }


    /**
     * Altera as informacoes do cliente.
     *
     * @param clienteRecebido  cliente recebido
     * @param arrayClientes    array de clientes
     */
    protected void alteraInformacoesCliente(Cliente clienteRecebido, ArrayList<Cliente> arrayClientes) {
        Scanner scannerEscolha = new Scanner(System.in);
        boolean verificacao = false;
        String escolha;

        System.out.println("\nPretende alterar o nome do cliente? (S/N)");
        while(!verificacao) {
            escolha = scannerEscolha.nextLine();
            switch (escolha) {
                case "S" , "s":
                    alteraInformacaoCliente("Nome", clienteRecebido, arrayClientes);
                    verificacao = true;
                    break;
                case "N" , "n":
                    System.out.println("Nome do cliente não alterado");
                    verificacao = true;
                    break;
                default:
                    System.out.println("Opção inválida.");
                    break;
            }
        }

        verificacao = false;
        System.out.println("\nPretende alterar o NIF do cliente? (S/N)");
        while(!verificacao) {
            escolha = scannerEscolha.nextLine();
            switch (escolha) {
                case "S":
                    alteraInformacaoCliente("NIF", clienteRecebido, arrayClientes);
                    verificacao = true;
                    break;
                case "N":
                    System.out.println("NIF do cliente não alterado");
                    verificacao = true;
                    break;
                default:
                    System.out.println("Opção inválida.");
                    break;
            }
        }

        verificacao = false;
        System.out.println("\nPretende alterar a Localização do cliente? (S/N)");
        while(!verificacao) {
            escolha = scannerEscolha.nextLine();
            switch (escolha) {
                case "S":
                    alteraInformacaoCliente("Localizacao", clienteRecebido, arrayClientes);
                    verificacao = true;
                    break;
                case "N":
                    System.out.println("Localização do cliente não alterado");
                    verificacao = true;
                    break;
                default:
                    System.out.println("Opção inválida.");
                    break;
            }
        }
    }
}