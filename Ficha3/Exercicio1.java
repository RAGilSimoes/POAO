package Ficha3;

public class Exercicio1 {
    public static void main(String[] args) {
        String frase = new String("Ola seu banana!");
        char[] caracteres = frase.toCharArray();

        for(char caracter: caracteres) {
            if(caracter != ' '){
                System.out.println(caracter);
            }
        }
    }
}
