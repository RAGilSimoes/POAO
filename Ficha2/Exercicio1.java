package Ficha2;

public class Exercicio1 {
    public static void main(String[] args) {
        int soma = 0;

        int[] tabela = new int[10];
        for(int i = 0; i < 10; i++){
            tabela[i] = (int) (Math.random() * 100);
        }

        for(int i = 0; i < 10; i++){
            System.out.printf("Número: " + tabela[i] + " Posição: %d ", i);
            System.out.println();
            soma += tabela[i];
        }
        double media = (double)(soma / 10.0);

        System.out.println("Soma: " + soma);
        System.out.printf("Média: %.1f", media);
    }
}
