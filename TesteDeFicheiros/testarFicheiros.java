package TesteDeFicheiros;

import java.io.*;

public class testarFicheiros {
    public static void main(String[] args) {
        try {
            System.out.println(System.getProperties());
            File f = new File("ficheiro.txt");
            System.out.println(f.getAbsolutePath());
            FileWriter fs = new FileWriter(f);
            BufferedWriter bw = new BufferedWriter(fs);
            bw.write("Olá e ");
            bw.newLine();
            bw.write("Adeus");
            bw.close();
        } catch(IOException exception){
            System.out.println("Erro ao abrir o ficheiro");
        }
    }
}