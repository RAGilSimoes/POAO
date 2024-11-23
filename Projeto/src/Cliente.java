package Projeto.src;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * The type Cliente.
 */
public class Cliente {
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

    private boolean verificaNif(String nif, ArrayList<Cliente> arrayClientes){
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


    private String escolherLocalizao(String escolha){
        String localizacao = null;

        switch(escolha){
            case "1":
                localizacao = "Continente";
                break;
            case "2":
                localizacao = "Madeira";
                break;
            case "3":
                localizacao = "Açores";
                break;
            default:
                System.out.println("Introduza um caracter numérico válido.");
                break;
        }

        return localizacao;
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
        String localizacao = null;
        String escolhaLocalizacao;

        boolean verificacaoNome = false;
        boolean verificacaoNIF = false;

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

        while(localizacao == null){
            System.out.print("\nEscolha a localização do cliente: \n1-> Continente | 2-> Madeira | 3-> Açores\n");
            escolhaLocalizacao = scannerCliente.nextLine();
            localizacao = escolherLocalizao(escolhaLocalizacao);
        }

        return new Cliente(nome, nif, localizacao);
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
                String localizacao = null;
                String escolhalocalizacao = null;
                boolean verificacaoLocalizacao = false;
                while(!verificacaoLocalizacao){
                    System.out.print("\nEscolha a nova localização do cliente: \n1-> Portugal | 2-> Madeira | 3-> Açores\n");
                    escolhalocalizacao = editarCliente.nextLine();
                    if (escolhalocalizacao.equals("0")){
                        System.out.println("Localização não alterada.");
                        break;
                    }else{
                        localizacao = clienteRecebido.escolherLocalizao(escolhalocalizacao);

                        if(localizacao != null){
                            clienteRecebido.setLocalizacao(localizacao);
                            System.out.println("Localização alterada com sucesso.");
                            verificacaoLocalizacao = true;
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
