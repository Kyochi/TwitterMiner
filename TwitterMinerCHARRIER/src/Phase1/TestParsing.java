package Phase1;

import java.io.IOException;
import java.security.KeyStore.Entry;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TestParsing {

	/**
	 * @param args
	 */
	
	
	
	
	public static void main(String[] args) {
		Converter Con = new Converter();
		//Con.CsvToTrans("resultats.csv", "resultats2.trans");
		Con.Outtocsv("motiffreq.out", "resultatout.csv");
		
		  /* String Lignelue = "12 25 53 5 44";
		 String Lignelue1 = "";
		 String Lignelue2 = "";
		 
		for (int i =0; i != Lignelue.length() ; i++) {
			if (Lignelue.charAt(i) == '(') {
				Lignelue1 = Lignelue.substring(0, i -1);
				Lignelue2 = Lignelue.substring(i , Lignelue.length());
				
				break;
			}
			if (!Lignelue.contains("(")) {
				Lignelue1 = Lignelue;
			}
		}
		
		String[] tokens = Lignelue1.split(" ");
		for (String numbers : tokens) {
			
			System.out.println(numbers);
		}
		System.out.println(Lignelue + "\n" + Lignelue1 + "\n" + Lignelue2 );
	}
	*/
		
}
}
