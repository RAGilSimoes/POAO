package Ficha1;

import java.util.Scanner;

public class somaDigitos {
    public static void main(String[] args) {
        int num;
        int soma = 0;
        System.out.print("Digite um numero: ");
        Scanner sc = new Scanner(System.in);
        num = sc.nextInt();
        while(num > 0) {
            soma = soma + (num % 10);
            num = num / 10;
        }
        System.out.printf("Soma dos dígitos = %d", soma);
    }
}
