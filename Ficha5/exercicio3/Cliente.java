package Ficha5.exercicio3;

import java.util.Scanner;

public class Cliente {
     protected String nome;
     protected int numero;
     protected String tipoDeConta;
     protected double saldo;

     public Cliente(String nome, int numero, String tipoDeConta, double saldo) {
         this.nome = nome;
         this.numero = numero;
         this.tipoDeConta = tipoDeConta;
         this.saldo = saldo;
     }

     public String getNome(){
         return this.nome;
     }

     public int getNumero(){
         return this.numero;
     }

     public String getTipoDeConta(){
         return this.tipoDeConta;
     }

     public double getSaldo(){
         return this.saldo;
     }

     public String toString(){
         return ("Conta -> " + this.getNome() + "; Número da conta -> " + this.getNumero() + "; Tipo de Conta -> " + this.getTipoDeConta() + "; Saldo -> " + this.getSaldo());
     }

     public void setSaldo(double numero){
         this.saldo = numero;
     }

     public void levantamentos(){
         if (this.getSaldo() == 0) {
             System.out.println("Não é possível fazer levantamentos! ");
         } else {
             System.out.print("Introduza a quantidade que pretende retirar: ");
             Scanner sc = new Scanner(System.in);
             double retirar = sc.nextDouble();
             if(retirar < this.getSaldo()) {
                 this.setSaldo(this.getSaldo() - retirar);
             } else {
                 System.out.println("Saldo insuficiente!");
             }
         }
     }
}
