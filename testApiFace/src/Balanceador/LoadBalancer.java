package Balanceador;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.management.ManagementFactory;
import java.net.ServerSocket;
import java.net.UnknownHostException;
import java.util.ArrayList;
import javax.management.Attribute;
import javax.management.MBeanServer;
import javax.management.ObjectName;
import javax.swing.text.html.parser.AttributeList;
 
public class LoadBalancer {
	ServerSocket s;
	Thread ProcesoReplicar;
	int port;
	ArrayList<Host> queue;
	int nextH;
	int cont;
	
	public LoadBalancer(int port) {
		this.port = port;
		nextH = 0;
		queue = new ArrayList<Host>();
		cont= 0;
	}
	
	@SuppressWarnings("deprecation")
	public void start() throws Exception {
		try {
			s = new ServerSocket(port);
			System.out.println("Server Balanceador levantado");
			Logger l = new Logger();
//			ProcesoReplicar = new Replicacion(queue.get(nextH));
//			ProcesoReplicar.start();
			//l.loggear("Server Balanceador levantado");
			//int portNew = 7002;
			while (true) {
				(new Thread(new ThreadServer(s.accept(),queue.get(nextH)))).start();
				//System.out.println("Host levantado");
				//loggear("Host levantado");
				if (++nextH >= queue.size())
					nextH = 0;
				System.out.println("Cliente conectado en el puerto: " + port);
				l.loggear("Cliente conectado en el puerto: " + port);
				cont++;
				//cada 10 clientes conectados creo un nuevo servicio
//				if(cont % 10 == 0) { 
//					portNew++;
//					//System.out.println("Levanto un nuevo servicio en el puerto: " + portNew);
//					//System.in.read();
//					l.loggear("Levanto un nuevo servicio en el puerto: " + portNew);
//					new MasterNodeNew(portNew, "test");
//					addHost(new Host("localhost", portNew));
//				}
				
				//System.out.println("Uso de CPU: " + getProcessCpuLoad());
				//si el uso del server baja mucho bajo un servicio
				//if(getProcessCpuLoad() < 0.1 &&  queue.size() > 1) {					
					//System.out.println("Bajo el servicio ubicado en el puerto: " + portNew);
					//l.loggear("Bajo el servicio ubicado en el puerto: " + portNew);
					//delHost(new Host("localhost", portNew));
					//portNew--;
				//}
				}
				
			} catch (UnknownHostException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}finally{
				
				try {
					if(s!= null) {
						s.close();
//						ProcesoReplicar.suspend();
					}	
				} catch (IOException e) {
					
					e.printStackTrace();
				}
		}
		
	}
	
	public void addHost(Host h) {
		queue.add(h);
	}
	
	public void delHost(Host h) {
		queue.remove(h);
	}
	
	public static double getProcessCpuLoad() throws Exception {

	    MBeanServer mbs    = ManagementFactory.getPlatformMBeanServer();
	    ObjectName name    = ObjectName.getInstance("java.lang:type=OperatingSystem");
	    javax.management.AttributeList list = mbs.getAttributes(name, new String[]{ "ProcessCpuLoad" });

	    if (list.isEmpty())     return Double.NaN;

	    Attribute att = (Attribute)list.get(0);
	    Double value  = (Double)att.getValue();

	    // usually takes a couple of seconds before we get real values
	    if (value == -1.0)      return Double.NaN;
	    // returns a percentage value with 1 decimal point precision
	    return ((int)(value * 1000) / 10.0);
	}
	
	/*public boolean loggear(String texto){
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
	}*/
	
	public static void main (String args[]) throws Exception {
		LoadBalancer server = new LoadBalancer(5000);
		server.addHost(new Host("localhost", 7001));
		server.addHost(new Host("localhost", 7002));
		server.addHost(new Host("localhost", 7003));
		server.start();
	}
}
