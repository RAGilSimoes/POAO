package POAO.Ficha4.testeClasses;

import java.util.Scanner;

public class Estudante {
    private String nome;
    private int[] notas;

    public Estudante() {
        System.out.print("Nome do estudante: ");
        Scanner sc = new Scanner(System.in);
        nome = sc.nextLine();
        System.out.print("Quantas notas? ");
        int numNotas = sc.nextInt();
        notas = new int[numNotas];
        for(int i = 0; i < numNotas; i++){
            System.out.printf("Nota (%d) do aluno: ", i + 1);
            notas[i] = sc.nextInt();
        }
    }
    public Estudante(String nomeEst, int[] notasEst) {
        nome = nomeEst;
        notas = new int[notasEst.length];
        for(int i = 0; i < notasEst.length; i++) {
            notas[i] = notasEst[i];
        }
    }
    private float calculaMedia() {
        float soma = 0;
        if (notas.length > 0) {
            for(int n: notas)
                soma += n;
            return soma / notas.length;
        } else return -1;
    }
    public int getMedia(){
        return Math.round(calculaMedia());
    }

    public void imprimeEstudante(){
        System.out.print("As notas de "+ nome+ " são: ");
        for(int n: notas)
            System.out.print(n + " ");
        System.out.println();
        System.out.println("A média é " + calculaMedia());
    }
}
