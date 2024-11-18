package Projeto;

import java.util.InputMismatchException;
import java.util.Scanner;

public class FuncoesUteis {
    protected static int protecaoEscolha(int menor, int maior){
        int escolha = 0;
        boolean sairVerificacao = false;
        do {
            try {
                Scanner scannerEscolha = new Scanner(System.in);
                escolha = scannerEscolha.nextInt();
                if(escolha >= menor && escolha <= maior){
                    sairVerificacao = true;
                } else {
                    System.out.println("\nIntroduza um caracter numérico válido!");
                }
            } catch (InputMismatchException exception) {
                System.out.println("\nIntroduza um caracter numérico válido!");
            }
        } while(!sairVerificacao);

        return escolha;
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
            }
            if(doubleRecebido == 0.0) {
                System.out.println("\nO valor introduzido não é válido.");
                verificacao = false;
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
