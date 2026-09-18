import java.io.*;

public class pt1 {
    public static void main(String[] args) {
        int numCaracters = 0;
        int numLinies = 0;
        int numParaules = 0;

        try (FileReader fr = new FileReader("RA1/text.txt")) {
            int c;

            while ((c = fr.read()) != -1) {
                System.out.print((char) c);
            }
        } catch (IOException e) {
            System.out.println("No se pudo abrir el archivo: " + e.getMessage());
        }
    }
}
    