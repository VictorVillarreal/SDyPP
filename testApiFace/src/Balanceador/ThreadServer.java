package Balanceador;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

public class ThreadServer implements Runnable {
	Socket sClient;
	Socket sServer;
	Host host;
	
	public ThreadServer(Socket s, Host h) {
		sClient = s;
		host = h;
	}
	
	@Override
	public void run() {
		// TODO Auto-generated method stub
		try {
			System.out.println("Thread conectando a " + host.getIp() + ":" + host.getPort());
			Logger l = new Logger();
			l.loggear("Thread conectando a " + host.getIp() + ":" + host.getPort());
			sServer = new Socket(host.getIp(), host.getPort());
			
			BufferedReader cReader = new BufferedReader(new InputStreamReader(sClient.getInputStream()));
			PrintWriter cWrite = new PrintWriter(sClient.getOutputStream(), true);
			
			BufferedReader sReader = new BufferedReader(new InputStreamReader(sServer.getInputStream()));
			PrintWriter sWrite = new PrintWriter(sServer.getOutputStream(), true);
			
			
			(new Thread(new ReadThread(cReader, sWrite))).start();
			(new Thread(new ReadThread(sReader, cWrite))).start();
			
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
