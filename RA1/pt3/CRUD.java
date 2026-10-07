package pt3;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Scanner;

public class CRUD {
    private static final String FITXER = "videojocs.dat";

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ArrayList<Videojoc> videojocs = carregarVideojocs();
        int opcio;

        do {
            mostrarMenu();
            opcio = llegirEnter(scanner, "Tria una opció: ");

            switch (opcio) {
                case 1 -> afegirVideojoc(scanner, videojocs);
                case 2 -> llistarVideojocs(videojocs);
                case 3 -> cercarPerTitol(scanner, videojocs);
                case 4 -> actualitzarVideojoc(scanner, videojocs);
                case 5 -> eliminarVideojoc(scanner, videojocs);
                case 6 -> desarVideojocs(videojocs);
                case 0 -> {
                    desarVideojocs(videojocs);
                    System.out.println("Canvis desats. Sortint del programa...");
                }
                default -> System.out.println("Opció incorrecta.");
            }
        } while (opcio != 0);

        scanner.close();
    }

    private static void mostrarMenu() {
        System.out.println("\n--- Gestió de videojocs ---");
        System.out.println("1. Afegir videojoc");
        System.out.println("2. Llistar tots els videojocs");
        System.out.println("3. Cercar videojocs per títol");
        System.out.println("4. Actualitzar un videojoc");
        System.out.println("5. Eliminar un videojoc");
        System.out.println("6. Desar canvis");
        System.out.println("0. Sortir");
    }

    private static void afegirVideojoc(Scanner scanner, ArrayList<Videojoc> videojocs) {
        videojocs.add(llegirDadesVideojoc(scanner));
        desarVideojocs(videojocs);
        System.out.println("Videojoc afegit correctament.");
    }

    private static void llistarVideojocs(ArrayList<Videojoc> videojocs) {
        if (videojocs.isEmpty()) {
            System.out.println("No hi ha videojocs desats.");
            return;
        }
        for (int i = 0; i < videojocs.size(); i++) {
            System.out.println((i + 1) + ". " + videojocs.get(i));
        }
    }

    private static void cercarPerTitol(Scanner scanner, ArrayList<Videojoc> videojocs) {
        String text = llegirText(scanner, "Text del títol: ").toLowerCase();
        boolean trobat = false;
        for (Videojoc videojoc : videojocs) {
            if (videojoc.getTitol().toLowerCase().contains(text)) {
                System.out.println(videojoc);
                trobat = true;
            }
        }
        if (!trobat) {
            System.out.println("No s'han trobat videojocs.");
        }
    }

    private static void actualitzarVideojoc(Scanner scanner, ArrayList<Videojoc> videojocs) {
        int index = seleccionarVideojoc(scanner, videojocs);
        if (index == -1) {
            return;
        }
        videojocs.set(index, llegirDadesVideojoc(scanner));
        desarVideojocs(videojocs);
        System.out.println("Videojoc actualitzat correctament.");
    }

    private static void eliminarVideojoc(Scanner scanner, ArrayList<Videojoc> videojocs) {
        int index = seleccionarVideojoc(scanner, videojocs);
        if (index == -1) {
            return;
        }
        videojocs.remove(index);
        desarVideojocs(videojocs);
        System.out.println("Videojoc eliminat correctament.");
    }

    private static int seleccionarVideojoc(Scanner scanner, ArrayList<Videojoc> videojocs) {
        if (videojocs.isEmpty()) {
            System.out.println("No hi ha videojocs desats.");
            return -1;
        }
        llistarVideojocs(videojocs);
        int numero = llegirEnter(scanner, "Número del videojoc: ");
        if (numero < 1 || numero > videojocs.size()) {
            System.out.println("Número incorrecte.");
            return -1;
        }
        return numero - 1;
    }

    private static Videojoc llegirDadesVideojoc(Scanner scanner) {
        String titol = llegirText(scanner, "Títol: ");
        String genere = llegirText(scanner, "Gènere: ");
        int any = llegirEnter(scanner, "Any de llançament: ");
        String plataforma = llegirText(scanner, "Plataforma: ");
        double preu = llegirDouble(scanner, "Preu: ");
        return new Videojoc(titol, genere, any, plataforma, preu);
    }

    private static String llegirText(Scanner scanner, String missatge) {
        System.out.print(missatge);
        return scanner.nextLine().trim();
    }

    private static int llegirEnter(Scanner scanner, String missatge) {
        while (true) {
            try {
                return Integer.parseInt(llegirText(scanner, missatge));
            } catch (NumberFormatException e) {
                System.out.println("Introdueix un número enter vàlid.");
            }
        }
    }

    private static double llegirDouble(Scanner scanner, String missatge) {
        while (true) {
            try {
                return Double.parseDouble(llegirText(scanner, missatge).replace(',', '.'));
            } catch (NumberFormatException e) {
                System.out.println("Introdueix un preu vàlid.");
            }
        }
    }

    @SuppressWarnings("unchecked")
    private static ArrayList<Videojoc> carregarVideojocs() {
        File fitxer = new File(FITXER);
        if (!fitxer.exists()) {
            return new ArrayList<>();
        }
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(fitxer))) {
            return (ArrayList<Videojoc>) ois.readObject();
        } catch (IOException | ClassNotFoundException | ClassCastException e) {
            System.out.println("Error carregant videojocs: " + e.getMessage());
            return new ArrayList<>();
        }
    }

    private static void desarVideojocs(ArrayList<Videojoc> videojocs) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(FITXER))) {
            oos.writeObject(videojocs);
        } catch (IOException e) {
            System.out.println("Error desant videojocs: " + e.getMessage());
        }
    }
}
