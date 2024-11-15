import java.io.File;
import java.io.IOException;
import java.io.FileWriter;

public class testarFicheiros {
    public static void main(String[] args) {
        String diretorio = System.getProperty("user.dir");
        File ficheiro = new File(diretorio + "/ficheiro.txt");
        System.out.println(ficheiro.getAbsolutePath());
        try {
            boolean ficheirocriado = ficheiro.createNewFile();
            if(ficheirocriado) {
                System.out.println("Ficheiro criado: " + ficheiro.getName());
            } else {
                System.out.println("Ficheiro já existe");
            }
        } catch (IOException exception) {
            System.out.println("Aconteceu um erro");
            exception.printStackTrace();
        }

        try {
            FileWriter myWriter = new FileWriter(ficheiro.getAbsolutePath());
            myWriter.write("Files in Java might be tricky, but it is fun enough!");
            myWriter.close();
            System.out.println("Successfully wrote to the file.");
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }
}