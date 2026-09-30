import java.io.*;

public class pt2 {
	private static final int CLAU = 3;

	public static void main(String[] args) {
		try {
			xifrar("entrada.txt", "xifrat.txt", CLAU);
			desxifrar("xifrat.txt", "desxifrat.txt", CLAU);

			System.out.println("Fitxer xifrat creat: xifrat.txt");
			System.out.println("Fitxer desxifrat creat: desxifrat.txt");
		} catch (FileNotFoundException e) {
			System.out.println("Fitxer no trobat: " + e.getMessage());
		} catch (IOException e) {
			System.out.println("Error de lectura o escriptura: " + e.getMessage());
		}
	}

	public static void xifrar(String entrada, String sortida, int clau)
			throws IOException {
		try (BufferedReader br = new BufferedReader(new FileReader(entrada));
			 BufferedWriter bw = new BufferedWriter(new FileWriter(sortida))) {

			String linia;
			while ((linia = br.readLine()) != null) {
				String liniaInvertida = invertir(linia);
				bw.write(desplacar(liniaInvertida, clau));
				bw.newLine();
			}
		}
	}

	public static void desxifrar(String entrada, String sortida, int clau)
			throws IOException {
		try (BufferedReader br = new BufferedReader(new FileReader(entrada));
			 BufferedWriter bw = new BufferedWriter(new FileWriter(sortida))) {

			String linia;
			while ((linia = br.readLine()) != null) {
				String liniaDesxifrada = desplacar(linia, -clau);
				bw.write(invertir(liniaDesxifrada));
				bw.newLine();
			}
		}
	}

	private static String invertir(String text) {
		return new StringBuilder(text).reverse().toString();
	}

	private static String desplacar(String text, int desplacament) {
		StringBuilder resultat = new StringBuilder();

		for (int i = 0; i < text.length(); i++) {
			resultat.append((char) (text.charAt(i) + desplacament));
		}

		return resultat.toString();
	}
}
