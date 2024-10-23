package Ficha5.exercicio1;

public class Exercicio1 {
    public static void main(String[] args) {
        Pessoa pessoa = new Pessoa("Zé");
        System.out.println(pessoa.comunica());
        System.out.println("banana");
        Aluno aluno = new Aluno("Joao", 20);
        System.out.println(aluno.toString());
        Docente docente = new Docente("Tiago Cruz", 2023212489);
        System.out.println(docente.toString());
        System.out.println("banana");
        System.out.println(aluno.comunica());
        System.out.println(docente.comunica());
    }
}

