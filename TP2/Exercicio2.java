package TP2;

import java.util.ArrayList;

public class Exercicio2 {
    public static void main(String[] args) {
        ArrayList<Computador> arrayComputadores = new ArrayList<Computador>();
//        for(int i = 0; i < 5; i++){
//            int potenciaRam = Math.random()
//        }
        Computador servidor = new Computador("Cloud", 1, 128, 1024, 3.0, "x64");
        System.out.println(servidor.toString());
    }
}

