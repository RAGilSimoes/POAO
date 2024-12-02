import java.util.ArrayList;

/**
 * The type Listar faturas.
 */
public class ListarFaturas {
    /**
     * Listar faturas.
     *
     * @param arrayFaturas the array faturas
     */
    protected void listarFaturas(ArrayList<Fatura> arrayFaturas) {
        if (arrayFaturas.isEmpty()) {
            System.out.println("\nA lista de faturas está vazia.");

        } else {
            System.out.println("\nLista de faturas: ");
            for (Fatura fatura : arrayFaturas) {
                System.out.println("Fatura " + (arrayFaturas.indexOf(fatura) + 1) + ": " + fatura + "; Valor total sem IVA -> " + fatura.valorTotalSemIVA + "; Valor total com IVA -> " + fatura.valorTotalComIVA);
            }
        }
    }
}

