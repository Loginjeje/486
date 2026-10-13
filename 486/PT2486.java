import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class PT2486 {
    public static void main(String[] args) {
        String entrada = "ejemplo.txt";
        String sortida = "sortida.txt";
        String desxifrat = "desxifrat.txt";

        int totalLinies = 0;
        int totalParaules = 0;
        int totalCaracters = 0;
        int i =0;
        int caracter;


        char[] lletres = new char[1000];

        try (
            // Obrim fluxos
            BufferedReader br = new BufferedReader(new FileReader(entrada));
            BufferedWriter bw = new BufferedWriter(new FileWriter(sortida));
            BufferedReader br2 = new BufferedReader(new FileReader(sortida));
            BufferedWriter bx = new BufferedWriter(new FileWriter(desxifrat));
            PrintWriter pw = new PrintWriter(bw) // PrintWriter envolta BufferedWriter
        ) {
            String linia;
           
  

                while ((caracter = br.read()) != -1) {
                        lletres[i] = (char) caracter;
                        i++;
                    }

                    for (int j = i - 1; j >= 0; j--) {
                        char letra = lletres[j];

                        if (letra >= 'a' && letra <= 'z') {
                            letra = (char) ('a' + (letra - 'a' + 3) % 26);
                        }

                        bw.write(letra);
                    }
                    
                    bw.flush();
                    i = 0;
                     while ((caracter = br2.read()) != -1) {
                        lletres[i] = (char) caracter;
                        i++;
                    }

                    for (int j = i - 1; j >= 0; j--) {
                    char letra = lletres[j];

                    if (letra >= 'a' && letra <= 'z') {
                        letra = (char) ('a' + (letra - 'a' - 3 + 26) % 26);
                    }

                    bx.write(letra);
                }




            while ((linia = br.readLine()) != null) {
                totalLinies++;
                String[] paraules = linia.trim().split("\\s+");
                totalParaules += paraules.length;
                totalCaracters += linia.replaceAll("\\s+", "").length();
            }
            // Afegim resum amb PrintWriter
            pw.println("\n--- RESUM ---");
            pw.println("Total de línies: " + totalLinies);
            pw.println("Total de paraules: " + totalParaules);
            pw.println("Total de caràcters (sense espais): " + totalCaracters);

            // Assegurar escriptura al fitxer
            pw.flush();

            System.out.println("Processament complet. Fitxer creat: " + sortida);

        } catch (FileNotFoundException e) {
            System.out.println("Fitxer no trobat: " + e.getMessage());
        } catch (IOException e) {
            System.out.println("Error d'entrada/sortida: " + e.getMessage());
        }
    }
}