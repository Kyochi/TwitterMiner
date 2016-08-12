package Phase0;
import twitter4j.*;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.List;





public class SearchTweets {
   public SearchTweets(){}
 public Void FindTweet(String Keyword){
        if (!Keyword.equals(null)){
        Twitter twitter = new TwitterFactory().getInstance();
        
        
        try {
            Query query = new Query(Keyword);
            query.setCount(100);
            QueryResult result;
            int N = 0;
            String Linetweet="";
            
            java.io.File csvfile = new java.io.File("resultatsvideogames.csv");
            if (! csvfile.exists())
            	csvfile.createNewFile();
            
            
            OutputStreamWriter Writer = new OutputStreamWriter ( new FileOutputStream (csvfile) );
            do {
            	
            	result = twitter.search(query);
            	List<Status> tweets = result.getTweets();
            	
            	for (Status tweet : tweets) {
            		
            		
            		++N;
            		Linetweet = "@" + tweet.getUser().getScreenName() + " - " + tweet.getText();
            		if(!Linetweet.contains("RT")) { // vererification si c'est un retweet ou non
            			
            		
	            		System.out.println(N + " "+ Linetweet);
	            		
	            		Writer.write(ToCSV.TweettoCSV(tweet));
						Writer.write("\n");
            		}
            		
            	}
            	
            } while ((query = result.nextQuery()) != null  );
         
        }
        // gestion des exceptions
        catch (TwitterException TExc) {
        	System.out.println("Problème à l'extraction des tweets : " + TExc.getMessage());
        	
        }
        catch (IOException FileExc) {
        	System.out.println("Probleme de fichier : " + FileExc.getMessage());
        }
        
        
            
        
        }
    return null;
}
};



