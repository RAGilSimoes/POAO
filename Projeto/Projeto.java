package Projeto;

public class Projeto {
    public static void main(String[] args) {
        POOFS pooofs = new POOFS();
        Cliente cliente = pooofs.criarEEditarCliente();
        System.out.println(cliente.toString());
    }
}