package Server1;
import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.List;
import java.io.File;
import twitter4j.Query;
import twitter4j.QueryResult;
import twitter4j.Status;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class Server1 {
	private int port;
	
	public Server1(int port) {
		Socket cnx = null;
		ServerSocket sSocket = null;
		
		try {
			sSocket = new ServerSocket(port);
			System.out.println("Escuchando");
			cnx = sSocket.accept();
			System.out.println("Conexion establecida");			
			InputStream is = cnx.getInputStream();
			InputStreamReader reader = new InputStreamReader(is);
			BufferedReader bReader = new BufferedReader(reader);
			BufferedInputStream s = new BufferedInputStream(is);
			
			BufferedReader bR = new BufferedReader(new InputStreamReader(cnx.getInputStream()));
			String msg = bR.readLine();
			//System.out.println("Recibi: " + msg);
			String linea ;
			Twitter twitter = new TwitterFactory().getInstance();
			FileWriter flwriter = null;
			try {
				//crea el flujo para escribir en el archivo
	            flwriter = new FileWriter("tweets.txt", true);
	            //crea un buffer o flujo intermedio antes de escribir directamente en el archivo
				BufferedWriter bfwriter = new BufferedWriter(flwriter);
		        Query query = new Query(msg);	        
		        QueryResult result;
		        do {
		            result = twitter.search(query);
		            List<Status> tweets = result.getTweets();
		            for (Status tweet : tweets) {
		            	linea = "@" + tweet.getUser().getScreenName()+ " - " + tweet.getText();
		            	bfwriter.write(linea);
		            	bfwriter.newLine();
		            	System.err.println(linea); 
		            }
		            bfwriter.close();
		        } while ((query = result.nextQuery()) != null);
		        System.exit(0);
		    } catch (TwitterException | IOException te) {
		        te.printStackTrace();
		        System.out.println("Failed to search tweets: " + te.getMessage());
		        System.exit(-1);
		    }
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if (cnx != null) {
				try {
					cnx.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			if (sSocket != null) {
				try {
					sSocket.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}
	
	public static void main (String args[]) {
		System.out.println("Iniciando: <localhost>, Puerto: <7001>");
		Server1 s = new Server1(7001);
		
	}
}
