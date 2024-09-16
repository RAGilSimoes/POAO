import java.util.Scanner;

public class Teste {
    public static void main(String[] args) {
        System.out.print("Escreva o número: ");
        Scanner sc = new Scanner(System.in);
        int num = sc.nextInt();
        int soma = 0;
        while (num > 0) {
            soma += num % 10;
            num /= 10;
        }
        System.out.printf("Soma dos dígitos = %d", soma);
    }
}
