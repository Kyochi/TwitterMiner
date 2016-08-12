package Phase1;

import java.io.BufferedReader;

import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStreamWriter;


import java.util.HashMap;
import java.util.Map;

import org.omg.CORBA.portable.OutputStream;
import java.io.BufferedWriter;
public class Converter {

	// Fonction permettant d'acceder a l'indice d'une valeur dans un map
	public int getKeyFromValue(Map <Integer, String> Tab, String Value) {
		if (Tab.containsValue(Value)) {
			for (java.util.Map.Entry<Integer, String> entry : Tab.entrySet()) {
	            if (entry.getValue().equals(Value)) {
	            	return entry.getKey();
	                
	            }
	        }
		}
		return 0;
	}
	
	public void Outtocsv(String FileIn, String Fileout) {
		
		try {
			Map<Integer, String> Motif = new HashMap<Integer, String>(); 
			java.io.File Ficcsv = new java.io.File(Fileout);
			
			if (!Ficcsv.exists())
				Ficcsv.createNewFile();
			
			BufferedReader ReadTrans = new BufferedReader(new FileReader(FileIn));
			BufferedReader ReadTrad = new BufferedReader(new FileReader("traduction.txt"));
			
			// Chargement du map avec les traductions
			String Lignetrad ="";
			for (int i = 1; (Lignetrad = ReadTrad.readLine()) != null ; i++) 
				Motif.put(i, Lignetrad);
				
				
			
			
			
			OutputStreamWriter WriterTrans = new OutputStreamWriter ( new FileOutputStream (Ficcsv) );
			String Lignelue = new String();
			
			// on lit le fichier .trans
			while ((Lignelue = ReadTrans.readLine()) != null) {
				String Lignelue1 = "";
				 String Lignelue2 = "";
				
				// pour chanque ligne lue , on extrait de la chaine les 2 parties qui nous interresse
				for (int i =0; i != Lignelue.length() ; i++) {
					if (Lignelue.charAt(i) == '(') {
						Lignelue1 = Lignelue.substring(0, i);
						Lignelue2 = Lignelue.substring(i , Lignelue.length());
						
						break;
					}
					if (!Lignelue.contains("(")) {
						Lignelue1 = Lignelue;
					}
				}
				// on extrait chaque clé de motif de la ligne et on récupère le motif associé dans le map
				
				if (!Lignelue.matches(".*\\d+.*")) continue;
				String lignetest = Lignelue;
				String[] tokens = Lignelue1.split(" ");
				for (String Key : tokens) {
					if (Lignelue1.isEmpty()) break;
					
					Integer cle = new Integer(Key.trim());
					// on écrit chaque motif avec le séparateur
					WriterTrans.write(Motif.get(cle) + ";");
					
				}
				// on écrit la fréquence et on fait un retour à la ligne
				WriterTrans.write(Lignelue2 + "\n");
			
			}
			WriterTrans.flush();
			WriterTrans.close();
			
			System.out.println("FIN");
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}
		
		
	}
	
	public void CsvToTrans(String FileIn, String FileOut) {
		try {
		Map<Integer, String> Motif = new HashMap<Integer, String>(); 

		String Lignelue = new String();
		
		java.io.File FicTrad = new java.io.File("traduction.txt");
		java.io.File FicTrans = new java.io.File(FileOut);
		if (true)
			try {
				FicTrans.createNewFile();
				FicTrad.createNewFile();
				BufferedReader Read = new BufferedReader(new FileReader(FileIn));
				OutputStreamWriter WriterTrans = new OutputStreamWriter ( new FileOutputStream (FicTrans) );
				OutputStreamWriter WriterTrad = new OutputStreamWriter ( new FileOutputStream (FicTrad) );
				
				
				
			
		int NbMotif = 0;
		// on lit chaque ligne du fichier .csv
		System.out.println("ligne");
		while ((Lignelue = Read.readLine()) != null) {
			//on sépare chaque motif
			String prefix = ";";
			String noPrefixStr = Lignelue.substring(Lignelue.indexOf(prefix) + prefix.length());
			String[] tokens = noPrefixStr.split("\";\"");
			int Key;
			// on extrait de la ligne chaque mot
			
			for (String word : tokens) {
				if (!word.isEmpty()  && !word.contains("@")) {
					// on enlève le hashtag 
					 if ((word.charAt(0)) == '#') 
						 word = word.substring(1,word.length());
					 	
					 	
					 // on filtre les mots inutiles comme , ! ^
					 if (word.length() == 1) 
					 	break;
					 	
					 // Si le mot existe déjà on inscrit sa clé direct dans .trans
					 if(Motif.containsValue(word) ) {
					 	Key = getKeyFromValue(Motif, word);
					 	WriterTrans.write(Key + " ");
					 }
					 	
					NbMotif++;
					// on met le motif dans le hashmap
					Motif.put(NbMotif, word);
					Key = getKeyFromValue(Motif, word);
					// on écrit la clé du motif dans le fichier trans et la traduction à la ligne clé dans le fichier trad
					WriterTrans.write(Key + " ");
					WriterTrad.write(word +"\n");
					 	
				}
			}
		// on écrit le retour à la ligne
		WriterTrans.write('\n');
		}
		
		WriterTrad.flush();
		WriterTrans.flush();
		WriterTrad.close();
		WriterTrans.close();
	}
		
		catch (IOException Ex) {
			System.out.println("Erreur dans l'ouverture du fichier : " + Ex.getMessage());
		}
		} 
		finally  {
			System.out.println("Fin");
		}
	}}
	



