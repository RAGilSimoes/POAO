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

    protected boolean verificaDigitos(String stringRecebida, char min, char max) {
        int tamanho = stringRecebida.length();
        boolean verifica = false;
        for (int i = 0; i < tamanho; i++) {
            char caracter = stringRecebida.charAt(i);
            if (caracter < min || caracter > max) {
                verifica = true;
                break;
            }
        }
        return verifica;
    }

    protected boolean existeNif(String nifProcurar, ArrayList<Cliente> arrayClientes){
        boolean nifValido = true;
        for (Cliente cliente: arrayClientes){
            String nifExistente = cliente.getNif();
            if (nifExistente.equals(nifProcurar)){
                System.out.println("Nif do cliente já existente.");
                nifValido = false;
            }
        }
        return nifValido;
    }

    protected boolean verificaNif(String nif, boolean verificacao, ArrayList<Cliente> arrayClientes){
        if (nif.length() != 9 || verificaDigitos(nif,'0', '9')){
            System.out.println("\nO nif introduzido não é válido");
            verificacao = false;
        } else {
            verificacao = existeNif(nif, arrayClientes);
        }
        return verificacao;
    }

    protected boolean verificaNome(String nomeRecebido, boolean verificacao){
        String[] nomeSeparado = nomeRecebido.split(" ");
        for(String nome: nomeSeparado){
            if (nome.length() <= 2 || (verificaDigitos(nome.substring(0,1),'A', 'Z') || verificaDigitos(nome.substring(1),'a', 'z'))){
                System.out.println("\nO nome introduzido não é válido");
                verificacao = false;
                break;
            } else {
                verificacao = true;
            }
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
                        verificacaoNome = clienteRecebido.verificaNome(nome, verificacaoNome);
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
                        verificacaoNif = clienteRecebido.verificaNif(nif, verificacaoNif, arrayClientes);
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
