/**
 * @author Guilherme Carvalho e Ricardo Simoes
 * @version 1.0
 */

import java.io.Serializable;
import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * Classe que representa o objeto Funcoes Uteis
 */
public class FuncoesUteis implements Serializable {
    /**
     * Protecao para a escolha de numeros entre intervalos
     *
     * @param menor menor
     * @param maior maior
     * @return numero verificado
     */
    protected int protecaoEscolha(int menor, int maior){
        String escolha;
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
                    }
                } else {
                    System.out.println("\nIntroduza um caracter numérico válido!");
                }
            } catch (InputMismatchException exception) {
                System.out.println("\nIntroduza um caracter numérico válido!");
            }
        } while(!sairVerificacao);

        return escolhaFinal;
    }

    /**
     * Protecao para a escolha de caracteres entre intervalos
     *
     * @param stringRecebida string recebida
     * @param min            min
     * @param max            max
     * @return boolean
     */
    protected boolean verificaCaracteres(String stringRecebida, char min, char max) {
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

    /**
     * Verifica se e possivel passar a string para inteiro
     *
     * @param stringRecebida string recebida
     * @return devolve booleano consoante a possibilidade de passar a string para inteiro
     */
    protected boolean verificaInt(String stringRecebida){
        boolean verificacao = true;
        if(stringRecebida.isEmpty()){
            System.out.println("\nO valor introduzido não é válido.");
            verificacao = false;
        } else {
            try {
                Integer.parseInt(stringRecebida);
            } catch (NumberFormatException exception) {
                System.out.println("\nO valor introduzido não é válido.");
                verificacao = false;
            }
        }
        return verificacao;
    }

    /**
     * Verifica se e possivel passar a string para double
     *
     * @param stringRecebida string recebida
     * @return devolve booleano consoante a possibilidade de passar a string para double
     */
    protected boolean verificaDouble(String stringRecebida){
        boolean verificacao = true;
        if(stringRecebida.isEmpty()){
            System.out.println("\nO valor introduzido não é válido.");
            verificacao = false;
        } else {
            try {
                Double.parseDouble(stringRecebida);
            } catch (NumberFormatException exception) {
                System.out.println("\nO valor introduzido não é válido.");
                verificacao = false;
            }
        }
        return verificacao;
    }

    /**
     * Protecao para a validade do nome escolhido
     *
     * @param nomeRecebido nome recebido
     * @return devolve booleano consoante a validade do nome escolhido
     */
    protected boolean verificaNome(String nomeRecebido){
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

    /**
     * Protecao para a validade da categoria escolhida
     *
     * @param stringRecebida string recebida
     * @return devolve booleano consoante a validade da categoria escolhida
     */
    protected boolean verificaCategoria(String stringRecebida) {
        boolean verificacao;
        if(stringRecebida.length() <= 2 || (verificaCaracteres(stringRecebida.substring(0,1),'A', 'Z') || verificaCaracteres(stringRecebida.substring(1),'a', 'z'))) {
            System.out.println("Categoria inválida!");
            verificacao = false;
        } else {
            verificacao = true;
        }
        return verificacao;
    }
}