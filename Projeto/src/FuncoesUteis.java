import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * The type Funcoes uteis.
 */
public class FuncoesUteis {
    /**
     * Protecao escolha int.
     *
     * @param menor the menor
     * @param maior the maior
     * @return the int
     */
    protected int protecaoEscolha(int menor, int maior){
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
     * Verifica caracteres boolean.
     *
     * @param stringRecebida the string recebida
     * @param min            the min
     * @param max            the max
     * @return the boolean
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
     * Verifica int boolean.
     *
     * @param stringRecebida the string recebida
     * @return the boolean
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
     * Verifica double boolean.
     *
     * @param stringRecebida the string recebida
     * @return the boolean
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
     * Verifica nome boolean.
     *
     * @param nomeRecebido the nome recebido
     * @return the boolean
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
     * Verifica categoria boolean.
     *
     * @param stringRecebida the string recebida
     * @return the boolean
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
