package Projeto;

import java.util.ArrayList;
import java.util.Scanner;

public class Cliente {
    protected String nome;
    protected String nif;
    protected String localizacao;

    public Cliente(String nome, String nif, String localizacao) {
        this.nome = nome;
        this.nif = nif;
        this.localizacao = localizacao;
    }

    public String toString() {
        return ("Nome -> " + this.nome + "; NIF -> " + this.nif + "; Localização -> " + this.localizacao);
    }

    public String getLocalizacao() {
        return localizacao;
    }

    public String getNif() {
        return nif;
    }

    public String getNome() {
        return nome;
    }

    public void setLocalizacao(String localizacao) {
        this.localizacao = localizacao;
    }

    public void setNif(String nif) {
        this.nif = nif;
    }

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
        boolean verificacao;
        if (nif.length() != 9 || FuncoesUteis.verificaCaracteres(nif,'0', '9')){
            System.out.println("\nO nif introduzido não é válido");
            verificacao = false;
        } else {
            verificacao = existeNif(nif, arrayClientes);
        }
        return verificacao;
    }


    protected String escolherLocalizao(String escolha){
        String localizacao = null;

        switch(escolha){
            case "1":
                localizacao = "Portugal";
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

    protected Cliente criaCliente(ArrayList<Cliente> arrayClientes) {
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
            verificacaoNome = FuncoesUteis.verificaNome(nome);
        }


        while (!verificacaoNIF) {
            System.out.print("Introduza o nif do cliente: ");
            nif = scannerCliente.nextLine();
            verificacaoNIF = verificaNif(nif, arrayClientes);
        }

        while(localizacao == null){
            System.out.print("\nEscolha a localização do cliente: \n1-> Portugal | 2-> Madeira | 3-> Açores\n");
            escolhaLocalizacao = scannerCliente.nextLine();
            localizacao = escolherLocalizao(escolhaLocalizacao);
        }

        return new Cliente(nome, nif, localizacao);
    }

    protected void alteraInformacao(String tipo, Cliente clienteRecebido, ArrayList<Cliente> arrayClientes){
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
                        verificacaoNome = FuncoesUteis.verificaNome(nome);
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
        }
    }


    protected void alteraInformacoesCliente(Cliente clienteRecebido, ArrayList<Cliente> arrayClientes) {
        System.out.print("\n(Pressione 0 para não alterar alguma informacão)");
        alteraInformacao("Nome", clienteRecebido, arrayClientes);
        alteraInformacao("NIF", clienteRecebido, arrayClientes);
        alteraInformacao("Localizacao", clienteRecebido, arrayClientes);
    }
}
