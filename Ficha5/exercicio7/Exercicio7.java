package Ficha5.exercicio7;

import java.util.ArrayList;

public class Exercicio7 {
    public static void main(String[] args) {
        ArrayList<Animal> array = new ArrayList<Animal>(4);
        Mamifero mamifero = new Mamifero("mamífero", "cão");
        array.add(mamifero);

        Ave ave = new Ave("ave", "águia");
        array.add(ave);

        Reptil reptil = new Reptil("réptil", "cobra");
        array.add(reptil);

        Peixe peixe = new Peixe("peixe", "peixe-palhaço");
        array.add(peixe);

        for(int i = 0; i < array.size(); i++){
            Animal animal = array.get(i);
            animal.desloca();
        }
    }
}
