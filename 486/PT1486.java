import java.io.*;
import java.nio.charset.StandardCharsets;

public class PT1486 {
    public static void main(String[] args) {
        String archivo = "ejemplo.txt";
        int letras = 0;
        int palabras = 0;
        int lineas = 0;
        int[] contador = new int[65536];
        int maxVeces = 0;
        char caracterMasRepetido = ' ';

        try (BufferedReader lector = new BufferedReader(new FileReader(archivo, StandardCharsets.UTF_8))) {
            String linea;

            while ((linea = lector.readLine()) != null) {
                lineas++;

                if (!linea.trim().isEmpty()) {
                    String[] trozos = linea.trim().split("\\s+");
                    palabras += trozos.length;
                }

                for (int i = 0; i < linea.length(); i++) {
                    char c = linea.charAt(i);

                    if (c == ' ' || c == '\t' || c == '\n' || c == '\r') {
                        continue;
                    }

                    letras++;
                    contador[c]++;

                    if (contador[c] > maxVeces) {
                        maxVeces = contador[c];
                        caracterMasRepetido = c;
                    }
                }
            }

            System.out.println("Hay " + palabras + " palabras");
            System.out.println("Hay " + letras + " letras");
            System.out.println("Hay " + lineas + " líneas");
            System.out.println("El carácter que más aparece es: '" + caracterMasRepetido + "' con " + maxVeces + " veces");

        } catch (IOException e) {
            System.out.println("Error al leer el archivo: " + e.getMessage());
        }
    }
}
