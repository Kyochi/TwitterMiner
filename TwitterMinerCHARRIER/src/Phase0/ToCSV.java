package Phase0;

import twitter4j.Status;
import java.io.File;

public class ToCSV {
	
	public static String TweettoCSV(Status tweet) {
		
		
		String[] splitstatus = tweet.getText().split(" " + "|" + "\t" + "|" +"\n"); 		
		String TweetText = new String();
		for (int i = 0; i < splitstatus.length ; ++i)
			TweetText += "\"" + splitstatus[i] + "\";";
				
		
		// on enlève le dernier "/"
		TweetText = TweetText.substring(0, TweetText.length()-1);
				
			
		String pays = "";
				
			if (tweet.getPlace() != null)
				pays = tweet.getPlace().getCountry();
		// On assemble la "transaction" finale prête à être écrite dans le fichier csv
		String FinalLineCSV = "\"" + tweet.getCreatedAt() + "\";\"@" + tweet.getUser().getName() + "\";\"" + pays + "\";" +  TweetText;
			return FinalLineCSV;
		
	}
	
}
