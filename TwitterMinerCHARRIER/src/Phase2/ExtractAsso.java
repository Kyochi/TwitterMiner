package Phase2;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class ExtractAsso {

	private static List<Regle> rules = new ArrayList<Regle>();
	
	public static void extract (String Ficout, String Ficasso, double minConf) throws IOException  {
		
		
		BufferedReader reader = new BufferedReader(new FileReader(Ficout));
		BufferedWriter writer = new BufferedWriter(new FileWriter(Ficasso));
		
		
		
		List<String> tabfreq = new ArrayList<String>();
		// Au saute l'extraction du nombre de motif
		reader.readLine(); 
		String lignelue;
		while ((lignelue = reader.readLine()) != null) {
			tabfreq.add(lignelue);
		}

		// On cherche la position du premier motif fréquent
		// qui a plus d'un attribut, c'est la position à laquelle
		// on va commancer à chercher les sous-ensembles,
		// car un motif à un seul attribut n'a pas de sous-ensemble non-vide
		String splitcaract = ";";
		int posfirstmotif = 0;
		for (int i = 0; i < tabfreq.size(); ++i) {
			if (tabfreq.get(i).split(splitcaract).length > 1) {
				posfirstmotif = i;
				break;
			}
		}
		
		
		for (int i = posfirstmotif; i < tabfreq.size(); ++i) {

			// On split les motifs fréquents en une liste
			// pour pouvoir utiliser les méthode containsAll() et removeAll()
			String strMotif = tabfreq.get(i);
			double freqItemset = (double) getFrequence(strMotif);
			List<String> itemset = splitItemset(strMotif);

			// on recherche les sous ensembles
			for (int j = 0;  i > j; ++j) {
				
				
				String strSubEns = tabfreq.get(j);
				double freqSubItemset = (double) getFrequence(strSubEns);
				List<String> subItemset = splitItemset(strSubEns);
				
				// détermination de la conf
				double conf = freqItemset / freqSubItemset;
				
				// on applique :  si conf >= minconf on crée une règle
				if (itemset.containsAll(subItemset) && conf >= minConf) {
					// On stocke le motif XUY
					List<String> ruleDest = new ArrayList<String>(itemset);
					// on enleve X
					ruleDest.removeAll(subItemset);
					// on ajoute la règle dans la list
					rules.add(new Regle(subItemset, ruleDest, conf, freqItemset));
					
					System.out.println("Règle d'association trouvée");
				}

			}
		}
		
		
		// écrit les régles trouvées dans le fichier regleasso.txt
		for (Regle r : rules) {
			
			writer.write(r.toString()); 
			writer.newLine();
		}
		writer.flush();
		
		reader.close();
		writer.close();
		
		
	}

	// retourne la fréquence contenue entre parenthèse
	public static int getFrequence(String line) {
		return Integer.parseInt(
				line.substring(line.lastIndexOf("(")+1, line.length()-1));
	}

	public static List<String> splitItemset(String str) {
		
		// Coupe avant la fréquence
		str = str.substring(0, str.lastIndexOf("("));
		// défini une nouvelle liste d'itemset du motif
		List<String> list = 
				new ArrayList<String>(Arrays.asList(str.split(";")));
		
		return list;
	}
	
	// séparation des éléments avec le bon séparateur pour toString()
	public static String SepareElements(List<String> list, String separator) {
		String str = new String("");

		if (0 == list.size()) return str;

		for (String s : list) {
			str += s + separator;
		}

		return str.substring(0, str.lastIndexOf(separator));
	}
	
	//Getter
	public static List<Regle> getRules() {
		return rules;
	}
	
	// main pour le test
	public static void main(String[] args) throws IOException {
		extract("resultatout.csv", "regleasso4.txt", 0.9);
	} 
}
