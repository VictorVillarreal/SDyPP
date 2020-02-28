package Balanceador;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
 
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;

public class Replicacion extends Thread {
	ArrayList<Host> queue;
	Socket sServer;
	Host host;
	int next;
	String OrigenServer;
	String Tmp;
	
	public Replicacion(Host h){
		
		try {
			sServer = new Socket(host.getIp(), host.getPort());
			BufferedReader cReader = new BufferedReader(new InputStreamReader(sServer.getInputStream()));
			PrintWriter cWrite = new PrintWriter(sServer.getOutputStream(), true);
			next = 0;
			File Origen = new File(OrigenServer);
	        File Destino = new File(Tmp);
	        if (Origen.exists()) {
	            try {
	                InputStream in = new FileInputStream(Origen);
	                OutputStream out = new FileOutputStream(Destino);

	                byte[] buf = new byte[1024];
	                int len;
	                while ((len = in.read(buf)) > 0) {
	                    out.write(buf, 0, len);
	                }
	                in.close();
	                out.close();
	            } catch (IOException ioe) {
	                ioe.printStackTrace();
	            }
	        }
		} catch (UnknownHostException e) {
			// TODO Bloque catch generado automáticamente
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Bloque catch generado automáticamente
			e.printStackTrace();
		}
	}	
	

	public void run() {
//		  JavaIOUtils javaIOUtils = new JavaIOUtils();
	        OrigenServer = "Url Tweets.txt Server";
	        Tmp = "Tmp Local";
//	        boolean result = javaIOUtils.copyFile(OrigenServer, Tmp);
//	        
	 
	}
}
