package Ficha4.exercicio5;

import java.util.ArrayList;
import java.util.Scanner;

public class Exercicio5 {
    public static void main(String[] args) {
        String[][] matrizContactos = {{"Jose", "Jose@gmail.com", "rua dos macacos", "913931020"}, {"Maria", "Maria@gmail.com", "rua das leoas", "919569208"}, {"Alberto", "Alberto@gmail.com", "rua dos papagaios", "919233061"}};

        ListaDeContactos listaContactos = new ListaDeContactos(10);

        for(int i = 0; i < matrizContactos.length; i++) {
            Contacto contacto1 = listaContactos.insere(matrizContactos[i][0], matrizContactos[i][1], matrizContactos[i][2], matrizContactos[i][3]);
            if(contacto1 != null) {
                System.out.printf("Contacto inserido -> %s, %s, %s, %s", contacto1.getNome(), contacto1.getEmail(), contacto1.getMorada(), contacto1.getTelefone());
                System.out.printf(" Tamanho lista atual -> %d", listaContactos.tamanhoLista());
                System.out.println();
            } else {
                System.out.print("Deu null");
                System.out.println();
            }
        }

        System.out.println();
        Contacto contactoEncontrado = listaContactos.pesquisa("Jose");
        System.out.printf("Contacto encontrado -> %s, %s, %s, %s", contactoEncontrado.getNome(), contactoEncontrado.getEmail(), contactoEncontrado.getMorada(), contactoEncontrado.getTelefone());
        System.out.println();

        System.out.println();
        Contacto contactoRemovido = listaContactos.remove("Jose");
        System.out.printf("Contacto removido -> %s, %s, %s, %s", contactoRemovido.getNome(), contactoRemovido.getEmail(), contactoRemovido.getMorada(), contactoRemovido.getTelefone());
        System.out.println();

        System.out.println();
        for(int i = 0; i < listaContactos.tamanhoLista(); i++) {
            Contacto contacto = listaContactos.obtemContacto(i);
            System.out.printf("Contacto -> %s, %s, %s, %s", contacto.getNome(), contacto.getEmail(), contacto.getMorada(), contacto.getTelefone());
            System.out.println();
        }
    }
}

