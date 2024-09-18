package Ficha1;

import java.math.*;
import java.util.Scanner;

public class Exercicio1 {
    static int fatorial(int numero) {
        int resultado = 1;
        while(numero > 0) {
            resultado *= numero;
            numero--;
        }
        return resultado;
    }

    public static void main(String[] args) {
        int n = 0;
        int k = 0;
        System.out.println("Combinações C(n,k)");
        System.out.print("Introduza o n: ");
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        System.out.print("Introduza o k: ");
        Scanner sc2 = new Scanner(System.in);
        k = sc2.nextInt();
        int nFatorial = fatorial(n);
        int kFatorial = fatorial(k);
        int nk = (n - k);
        int nkFatorial = fatorial(nk);

        double combinacoes = (double) (nFatorial) / ((kFatorial * nkFatorial));
        System.out.println(combinacoes);
    }
}
