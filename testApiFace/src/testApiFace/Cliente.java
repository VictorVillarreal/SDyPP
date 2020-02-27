package testApiFace;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.GroupLayout.Alignment;
import javax.swing.border.EmptyBorder;
import java.awt.BorderLayout;


public class Cliente extends JFrame{
	
	private JPanel contentPane;
	private JTextField textField;

	public Cliente (String ip, int port) {
		try {
			System.out.println("Conectando...");
			Socket s = new Socket(ip, port);
			System.out.println("Conectado");
			
			PrintWriter pWriter = new PrintWriter(s.getOutputStream(), true);
			
			// TODO Apéndice de método generado automáticamente

			setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			setBounds(100, 100, 450, 182);
			contentPane = new JPanel();
			contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
			setContentPane(contentPane);
			
			textField = new JTextField();
			textField.setColumns(10);
			
			JButton btnNewButton = new JButton("Buscar");
			btnNewButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					String cadena = textField.getText();
					String query = "";
					boolean flag = true;
					int l = cadena.length();
					//String cadena1 = cadena.replace(" ", "&");

					for(int i=0;i<l;i++){
						if (Character.isLetter(cadena.charAt(i)))  {
							char c = cadena.charAt(i);
							String s = String.valueOf(c);
							query = query.concat(s);
							flag = true;
						}
						else {
							if (flag) {
							query = query.concat("&");
							flag = false;
							}	
						}
					  }
					pWriter.println(query);
					//System.out.println(query);
					//JOptionPane.showMessageDialog(null, cadena1);
				}
			});
			
			JLabel lblNewLabel = new JLabel("Ingrese el tema de inter\u00E9s a buscar");
			GroupLayout gl_contentPane = new GroupLayout(contentPane);
			gl_contentPane.setHorizontalGroup(
				gl_contentPane.createParallelGroup(Alignment.LEADING)
					.addGroup(gl_contentPane.createSequentialGroup()
						.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
							.addGroup(gl_contentPane.createSequentialGroup()
								.addGap(141)
								.addComponent(btnNewButton, GroupLayout.PREFERRED_SIZE, 154, GroupLayout.PREFERRED_SIZE))
							.addGroup(gl_contentPane.createSequentialGroup()
								.addGap(56)
								.addComponent(textField, GroupLayout.PREFERRED_SIZE, 307, GroupLayout.PREFERRED_SIZE))
							.addComponent(lblNewLabel, GroupLayout.PREFERRED_SIZE, 246, GroupLayout.PREFERRED_SIZE))
						.addContainerGap(61, Short.MAX_VALUE))
			);
			gl_contentPane.setVerticalGroup(
				gl_contentPane.createParallelGroup(Alignment.LEADING)
					.addGroup(gl_contentPane.createSequentialGroup()
						.addComponent(lblNewLabel, GroupLayout.PREFERRED_SIZE, 28, GroupLayout.PREFERRED_SIZE)
						.addGap(18)
						.addComponent(textField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addGap(18)
						.addComponent(btnNewButton)
						.addGap(144))
			);
			contentPane.setLayout(gl_contentPane);
		
			
			//Scanner sc = new Scanner(System.in);
			//String a = sc.nextLine();
			//pWriter.println(a);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void main (String args[]) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Cliente frame = new Cliente("localhost", 5000);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});	
		//Cliente c = new Cliente("localhost",5000);
	}

}
