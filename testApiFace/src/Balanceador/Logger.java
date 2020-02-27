package Balanceador;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class Logger {
	
	public boolean loggear(String texto){
		File archivo;
		BufferedWriter escribir;
		
		try{
			//String routePath = this.getClass().getClassLoader().getResource(File.separator).getPath();
			//System.out.println(routePath);
			File dir = new File("tmp/");
			dir.mkdirs();
			archivo = new File(dir, "log.txt");
			if(archivo.exists()) {
				escribir = new BufferedWriter(new FileWriter(archivo, true));
			} else {
				escribir = new BufferedWriter(new FileWriter(archivo));
			}
			escribir.write(texto);
			escribir.write("\n");
			escribir.close();
			return true;
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}
	}
}
