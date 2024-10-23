package Ficha4.exercicio5;

import java.util.ArrayList;

public class ListaDeContactos{
    private int tamanho = 0;
    private ArrayList<Contacto> listaContactos;
    public ListaDeContactos(int tamanho) {
        listaContactos = new ArrayList<Contacto>(tamanho);
        this.tamanho = tamanho;
    }

    public Contacto insere(String nome, String email, String morada, String telefone) {
        if(listaContactos.size() < this.tamanho){
            Contacto adicionar = new Contacto(nome, email, morada, telefone);
            listaContactos.add(adicionar);
            return adicionar;
        } else {
            return null;
        }
    }

    public int tamanhoLista(){
        return listaContactos.size();
    }

    public Contacto pesquisa(String nomeAPesquisar){
        Contacto contactoDesejado = null;
       for(int i = 0; i < listaContactos.size(); i++){
            Contacto contacto = listaContactos.get(i);
            if(contacto.getNome().equalsIgnoreCase(nomeAPesquisar)) {
                contactoDesejado = contacto;
            }
       }
       return contactoDesejado;
    }

    public Contacto remove(String nomeAPesquisar){
        Contacto contactoDesejado = null;
        int indice = 0;
        for(int i = 0; i < listaContactos.size(); i++){
            Contacto contacto = listaContactos.get(i);
            if(contacto.getNome().equalsIgnoreCase(nomeAPesquisar)) {
                contactoDesejado = contacto;
                indice = i;
            }
        }
        if(contactoDesejado != null){
            listaContactos.remove(indice);
            return contactoDesejado;
        } else {
            return null;
        }
    }

    public Contacto obtemContacto(int indice) {
        return listaContactos.get(indice);
    }

    public void imprimeLista() {
        for(Contacto contacto: listaContactos) {
            System.out.printf("Contacto -> %s, %s, %s, %s ",contacto.getNome(), contacto.getEmail(), contacto.getMorada(), contacto.getTelefone());
            System.out.println();
        }
    }
}
