package Balanceador;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;

public class ReadThread implements Runnable {
	BufferedReader bR;
	PrintWriter pW;
	
	public ReadThread(BufferedReader b, PrintWriter p) {
		bR = b;
		pW = p;
	}
	
	@Override
	public void run() {
		// TODO Auto-generated method stub
		String s;
		try {
			s = bR.readLine();
			System.out.println("Leyendo: "+s);
			while (!(s.equals("FIN"))) {
				pW.println(s);
				s = bR.readLine();
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
