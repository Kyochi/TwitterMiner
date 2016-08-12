package Phase3;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.TreeMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import Phase2.Regle;

public class Cleanlift {

	private static List<String> m_saveRegle = new ArrayList<String>();
	private static List<String> m_saveRegleClean = new ArrayList<String>();
	private static List<String> m_saveMotInutiles = new ArrayList<String>();
	
	
	public static void Clean() {
		try {
			BufferedReader readerRule = new BufferedReader(new FileReader("regleasso2.txt"));
			BufferedReader readerWord = new BufferedReader(new FileReader("motinutiles.txt"));
			BufferedWriter writer = new BufferedWriter(new FileWriter("regleassoclean.txt"));
			
			// chargement de la liste avec les regles 
			String LigneLue = new String("");
			while ((LigneLue = readerRule.readLine()) != null) {
				m_saveRegle.add(LigneLue);
			}
			String motlu = new String("");
			while ((motlu = readerWord.readLine()) != null) {
				m_saveMotInutiles.add(motlu.toLowerCase());
			}
			// detection des mots inutiles dans les regles et suppression 
			LigneLue = "";
			boolean find = false;
			for (int indice = 0 ; indice < m_saveRegle.size() ; indice++ ) {
				find = false;
				String reglelue = m_saveRegle.get(indice);
				String part1 = reglelue.substring(0, reglelue.lastIndexOf("->")-1);
				String part2 = reglelue.substring(reglelue.indexOf(">")+2, reglelue.lastIndexOf(":")-1);
				reglelue = part1 + ";"+ part2;
				reglelue = reglelue.toLowerCase();
				List<String> itemsplit = new ArrayList<String>(Arrays.asList(reglelue.split(";")));
			
				
				
				for (int j = 0; j < m_saveMotInutiles.size(); j++) {
					
					
					if (itemsplit.contains(m_saveMotInutiles.get(j))) {
						System.out.println("TO REMOVE: "+reglelue + "   " + indice);
						m_saveRegle.remove(indice);
						find = true;
						
					}
				}
				if (find == false) m_saveRegleClean.add(m_saveRegle.get(indice)); 
					
					
			}
				
			
			
			for (String regleclean : m_saveRegleClean) {
				writer.write(regleclean + "\n");
			}
			writer.flush();
			
			readerRule.close();
			readerWord.close();
			writer.close();
		} catch (FileNotFoundException e) {
			System.out.println("Erreur dans le reader" + e.getMessage());
		} catch (IOException e) {
			System.out.println("Erreur dans le writer" + e.getMessage());
		}
		
		
		
		
	}
	public static void Lifting () {
		try {
			
			BufferedWriter writer = new BufferedWriter(new FileWriter("bestregleasso.txt"));
			BufferedReader readerRule = new BufferedReader(new FileReader("regleassoclean.txt"));
			BufferedReader readermotif = new BufferedReader(new FileReader("resultatout.csv"));
			String reglelue = new String("");
			String motiflu = new String("");
			double Lift = 0.0;
			int Freq = 0;
			String motifY = new String("");
			double conf = 0.0;
			while ((reglelue = readerRule.readLine()) != null) {
				Matcher m = Pattern.compile("-?\\d+(\\.\\d+)?").matcher(reglelue);
				m.find();
		        conf = Double.parseDouble(m.group());
		        motifY = reglelue.substring(reglelue.indexOf(">")+2, reglelue.lastIndexOf(":")-1);
		        
		        while ((motiflu = readermotif.readLine()) != null) {
		        	System.out.println(motiflu + " : "+ motifY);
		        	System.out.println(reglelue);
		        	if (motiflu.contains(motifY)) {
		        		Freq = Integer.parseInt(motiflu.substring(motiflu.lastIndexOf("(")+1, motiflu.length()-1));
		        		System.out.println(conf + " et "+ Freq);
		        		
		        		Lift = conf / Freq;
		        		System.out.println(Lift);
		        		System.exit(1);
		        		System.out.println(Lift);
		        		Lift = (double) (Math.round(Lift * 100) / 100);
		        		System.out.println(Lift);
		        		if (Lift > 1)
		        			writer.write(reglelue + ", Lift: " + Lift);
		        	}
		        }
		        
			}
			
		} catch (IOException ex) {
			System.out.println(ex.getMessage());
		}
	}
	
	// Integer.parseInt(line.substring(line.lastIndexOf("(")+1, line.length()-1));
	public static void main(String[] args) throws IOException {
		// Clean();
		Lifting();
		 /* List<Double> tab = new ArrayList<Double>();
		 tab.add(1.22);
		 tab.add(5.36);
		 tab.add(65.36);
		 tab.add(2.36);
		 tab.add(9.36);
		 tab.add(10.36);
		 Collections.sort(tab);
		 System.out.println(tab);
		 double i = tab.get(0);
		 System.out.println(i);
		 
		 HashMap<String,Double> map = new HashMap<String,Double>();
	        ValueComparator bvc =  new ValueComparator(map);
	        TreeMap<String,Double> sorted_map = new TreeMap<String,Double>(bvc);
		 
	        */
	}
		
	
	
	
}
