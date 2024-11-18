package Projeto;

import java.util.InputMismatchException;
import java.util.Scanner;

public class FuncoesUteis {
    protected static int protecaoEscolha(int menor, int maior){
        String escolha = null;
        boolean sairVerificacao = false;
        int escolhaFinal = 0;
        do {
            try {
                Scanner scannerEscolha = new Scanner(System.in);
                escolha = scannerEscolha.nextLine();
                boolean verificaEscolha = verificaInt(escolha);
                if(verificaEscolha) {
                    escolhaFinal = Integer.parseInt(escolha);
                    if(escolhaFinal >= menor && escolhaFinal <= maior) {
                        sairVerificacao = true;
                    } else {
                        System.out.println("\nIntroduza um caracter numérico válido!");
                        sairVerificacao = false;
                    }
                } else {
                    System.out.println("\nIntroduza um caracter numérico válido!");
                    sairVerificacao = false;
                }
            } catch (InputMismatchException exception) {
                System.out.println("\nIntroduza um caracter numérico válido!");
                exception.printStackTrace();
                sairVerificacao = false;
            }
        } while(!sairVerificacao);

        return escolhaFinal;
    }

    protected static boolean verificaCaracteres(String stringRecebida, char min, char max) {
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

    protected static boolean verificaInt(String stringRecebida){
        boolean verificacao = true;
        int inteiroRecebido = 0;
        if(stringRecebida.isEmpty()){
            System.out.println("\nO valor introduzido não é válido.");
            verificacao = false;
        } else {
            try {
                inteiroRecebido = Integer.parseInt(stringRecebida);
            } catch (NumberFormatException exception) {
                verificacao = false;
            }
        }
        return verificacao;
    }

    protected static boolean verificaDouble(String stringRecebida){
        boolean verificacao = true;
        double doubleRecebido = 0.0;
        if(stringRecebida.isEmpty()){
            System.out.println("\nO valor introduzido não é válido.");
            verificacao = false;
        } else {
            try {
                doubleRecebido = Double.parseDouble(stringRecebida);
            } catch (NumberFormatException exception) {
                System.out.println("\nO valor introduzido não é válido.");
                verificacao = false;
                exception.printStackTrace();
            }
        }
        return verificacao;
    }

    protected static boolean verificaNome(String nomeRecebido){
        boolean verificacao = false;
        String[] nomeSeparado = nomeRecebido.split(" ");
        for(String nome: nomeSeparado){
            if (nome.length() <= 2 || (verificaCaracteres(nome.substring(0,1),'A', 'Z') || verificaCaracteres(nome.substring(1),'a', 'z'))){
                System.out.println("\nO nome introduzido não é válido");
                verificacao = false;
                break;
            } else {
                verificacao = true;
            }
        }
        return verificacao;
    }

    protected static boolean verificaCategoria(String stringRecebida) {
        boolean verificacao;
        if(verificaCaracteres(stringRecebida, 'a', 'z')) {
            verificacao = true;
        } else {
            System.out.println("Categoria inválida!");
            verificacao = false;
        }
        return verificacao;
    }
}
