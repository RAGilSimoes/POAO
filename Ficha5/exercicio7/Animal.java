package Ficha5.exercicio7;

public class Animal{
    protected String tipo;
    protected String especie;
    public Animal(String tipo, String especie) {
        this.tipo = tipo;
        this.especie = especie;
    }

    public void desloca(){
        System.out.println("Animal a deslocar-se");
    }
}

class Mamifero extends Animal{
    public Mamifero(String tipo, String especie) {
        super(tipo, especie);
    }

    public void desloca(){
        System.out.println(this.tipo + " " + this.especie + " a caminhar");
    }
}

class Ave extends Animal{
    public Ave(String tipo, String especie) {
        super(tipo, especie);
    }

    public void desloca(){
        System.out.println(this.tipo + " " + this.especie + " a voar");
    }
}

class Reptil extends Animal{
    public Reptil(String tipo, String especie) {
        super(tipo, especie);
    }

    public void desloca(){
        System.out.println(this.tipo + " " + this.especie + " a rastejar");
    }
}

class Peixe extends Animal{
    public Peixe(String tipo, String especie) {
        super(tipo, especie);
    }

    public void desloca(){
        System.out.println(this.tipo + " " + this.especie + " a nadar");
    }
}
