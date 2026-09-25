import java.io.*;

public class pt1 {
    public static void main(String[] args) {
        int numCaracters = 0;
        int numLinies = 0;
        int numParaules = 0;
        int[] frequencia = new int[65536];

        boolean dinsParaula = false;
        boolean ultimaLecturaVaSerSalt = false;
        boolean saltCarro = false;
        boolean hiHaContingut = false;

        try (FileReader fr = new FileReader("text.txt")) {
            int c;

            while ((c = fr.read()) != -1) {
                char caracter = (char) c;
                hiHaContingut = true;

                if (caracter == '\r') {
                    numLinies++;
                    saltCarro = true;
                    ultimaLecturaVaSerSalt = true;
                    dinsParaula = false;
                } else if (caracter == '\n') {
                    if (!saltCarro) {
                        numLinies++;
                    }
                    saltCarro = false;
                    ultimaLecturaVaSerSalt = true;
                    dinsParaula = false;
                } else {
                    saltCarro = false;
                    ultimaLecturaVaSerSalt = false;
                    numCaracters++;

                    if (caracter != ' ' && caracter != '\t') {
                        frequencia[caracter]++;
                    }

                    if (caracter == ' ' || caracter == '\t') {
                        dinsParaula = false;
                    } else if (!dinsParaula) {
                        numParaules++;
                        dinsParaula = true;
                    }
                }
            }

            if (hiHaContingut && !ultimaLecturaVaSerSalt) {
                numLinies++;
            }

            int caracterMesRepetit = 0;
            int maxFreq = 0;

            for (int i = 0; i < frequencia.length; i++) {
                if (frequencia[i] > maxFreq) {
                    maxFreq = frequencia[i];
                    caracterMesRepetit = i;
                }
            }

            System.out.println("Nombre de caràcters: " + numCaracters);
            System.out.println("Nombre de línies: " + numLinies);
            System.out.println("Nombre de paraules: " + numParaules);

            if (maxFreq > 0) {
                System.out.println("Caràcter més repetit: " + (char) caracterMesRepetit);
            } else {
                System.out.println("Caràcter més repetit: cap");
            }
        } catch (FileNotFoundException e) {
            System.out.println("El fitxer no existeix.");
        } catch (IOException e) {
            System.out.println("S'ha produït un error de lectura.");
        } catch (SecurityException e) {
            System.out.println("No tens permisos per accedir al fitxer.");
        }
    }
}
