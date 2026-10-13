import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

// Classe que volem persistir
class Videojoc implements Serializable {
     private static final long serialVersionUID = 1L;
    private String títol;
    private String gènere;
    private String anyLlançament;
    private String plataforma;
    private double preu;

    public Videojoc(String títol, String gènere, String anyLlançament, String plataforma, double preu) {
        this.títol = títol;
        this.gènere = gènere;
        this.anyLlançament = anyLlançament;
        this.plataforma = plataforma;
        this.preu = preu;
    }
   
    @Override
    public String toString() {
        return "Videojoc [títol=" + títol + ", gènere=" + gènere + ", anyLlançament=" + anyLlançament + ", plataforma="
                + plataforma + ", preu=" + preu + ", getTítol()=" + getTítol() + ", getGènere()=" + getGènere()
                + ", getAnyLlançament()=" + getAnyLlançament() + ", getPlataforma()=" + getPlataforma() + ", getPreu()="
                + getPreu() + ", getClass()=" + getClass() + ", hashCode()=" + hashCode() + ", toString()="
                + super.toString() + "]";
    }

    public static long getSerialversionuid() {
        return serialVersionUID;
    }
    public String getTítol() {
        return títol;
    }
    public void setTítol(String títol) {
        this.títol = títol;
    }
    public String getGènere() {
        return gènere;
    }
    public void setGènere(String gènere) {
        this.gènere = gènere;
    }
    public String getAnyLlançament() {
        return anyLlançament;
    }
    public void setAnyLlançament(String anyLlançament) {
        this.anyLlançament = anyLlançament;
    }
    public String getPlataforma() {
        return plataforma;
    }
    public void setPlataforma(String plataforma) {
        this.plataforma = plataforma;
    }
    public double getPreu() {
        return preu;
    }
    public void setPreu(double preu) {
        this.preu = preu;
    }
    

 
}

public class PT3486 {

    private static final String FITXER = "persones.dat";

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        ArrayList<Videojoc> Joc = carregarPersones();

        int opcio = 0;
        do {
            System.out.println("\n--- Menú ---");
            System.out.println("1. Afegir Joc");
            System.out.println("2. Veure tots els videojocs");
            System.out.println("3. Eliminar");
            System.out.println("4. Modificar");
            System.out.println("5. Sortir");
            System.out.print("Tria una opció: ");
            opcio = sc.nextInt();
            sc.nextLine(); // netejar buffer

            switch (opcio) {
                case 1:
                    System.out.print("Títol: ");
                    String títol = sc.nextLine();
                    System.out.print("Any de llançament: ");
                    String anyLlançament = sc.nextLine();
                    System.out.print("Gènere: ");
                    String gènere = sc.nextLine();
                    System.out.print("Plataforma: ");
                    String plataforma = sc.nextLine();
                    System.out.print("Preu: ");
                    double preu = sc.nextDouble();
                    sc.nextLine(); // netejar buffer

                    Videojoc p = new Videojoc(títol, gènere, anyLlançament, plataforma, preu);
                    Joc.add(p);
                    desarVideojocs(Joc);
                    System.out.println("Videojoc desat correctament.");
                    break;

                case 2:
                    if (Joc.isEmpty()) {
                        System.out.println("No hi ha videojocs desats.");
                    } else {
                        System.out.println("--- Llista de videojocs ---");
                        for (Videojoc per : Joc) {
                            System.out.println(per);
                        }
                    }
                    break;

                case 3:
                    if (Joc.isEmpty()) {
                        System.out.println("No hi ha videojocs per eliminar.");
                    } else {
                        System.out.print("Títol del videojoc que vols eliminar: ");
                        String titolEliminar = sc.nextLine();
                        boolean eliminat = false;

                        for (int i = 0; i < Joc.size(); i++) {
                            if (Joc.get(i).getTítol().equalsIgnoreCase(titolEliminar)) {
                                Joc.remove(i);
                                eliminat = true;
                                break;
                            }
                        }

                        if (eliminat) {
                            desarVideojocs(Joc);
                            System.out.println("Videojoc eliminat correctament.");
                        } else {
                            System.out.println("No s'ha trobat cap videojoc amb aquest títol.");
                        }
                    }
                    break;

                case 4:
                    if (Joc.isEmpty()) {System.out.println("no hi ha ningun joc per modificar");}
                    else {
                        System.out.print("Títol del videojoc que vols modificar: ");
                        String titolModificar = sc.nextLine();
                        boolean trobat = false;

                        for (Videojoc joc : Joc) {
                            if (joc.getTítol().equalsIgnoreCase(titolModificar)) {
                                trobat = true;
                                System.out.print("Nou títol (deixa en blanc per no canviar): ");
                                String nouTitol = sc.nextLine();
                                if (!nouTitol.isEmpty()) {
                                    joc.setTítol(nouTitol);
                                }

                                System.out.print("Nou any de llançament (deixa en blanc per no canviar): ");
                                String nouAnyLlançament = sc.nextLine();
                                if (!nouAnyLlançament.isEmpty()) {
                                    joc.setAnyLlançament(nouAnyLlançament);
                                }

                                System.out.print("Nou gènere (deixa en blanc per no canviar): ");
                                String nouGènere = sc.nextLine();
                                if (!nouGènere.isEmpty()) {
                                    joc.setGènere(nouGènere);
                                }

                                System.out.print("Nova plataforma (deixa en blanc per no canviar): ");
                                String novaPlataforma = sc.nextLine();
                                if (!novaPlataforma.isEmpty()) {
                                    joc.setPlataforma(novaPlataforma);
                                }

                                System.out.print("Nou preu (deixa en blanc per no canviar): ");
                                String nouPreuStr = sc.nextLine();
                                if (!nouPreuStr.isEmpty()) {
                                    try {
                                        double nouPreu = Double.parseDouble(nouPreuStr);
                                        joc.setPreu(nouPreu);
                                    } catch (NumberFormatException e) {
                                        System.out.println("Preu invàlid. No s'ha canviat el preu.");
                                    }
                                }

                                desarVideojocs(Joc);
                                System.out.println("Videojoc modificat correctament.");
                                break;
                            }
                        }

                        if (!trobat) {
                            System.out.println("No s'ha trobat cap videojoc amb aquest títol.");
                        }
                    }

                case 5:
                    System.out.println("Sortint del programa...");
                    break;
            }

        } while (opcio != 5);

        sc.close();
    }

    // ---------- Mètodes de persistència ----------

    // Carregar persones del fitxer
    @SuppressWarnings("unchecked")
    public static ArrayList<Videojoc> carregarPersones() {
        ArrayList<Videojoc> persones = new ArrayList<>();
        File fitxer = new File(FITXER);
        if (fitxer.exists()) {
            try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(fitxer))) {
                persones = (ArrayList<Videojoc>) ois.readObject();
            } catch (IOException | ClassNotFoundException e) {
                System.out.println("Error carregant persones: " + e.getMessage());
            }
        }
        return persones;
    }

    // Desar persones al fitxer
    public static void desarVideojocs(ArrayList<Videojoc> persones) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(FITXER))) {
            oos.writeObject(persones);
        } catch (IOException e) {
            System.out.println("Error desant persones: " + e.getMessage());
        }
    }
}