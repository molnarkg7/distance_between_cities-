package mestaEvropa;

import java.awt.Color;
import java.awt.EventQueue;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.JFrame;
import javax.swing.JComboBox;
import javax.swing.JTextArea;
import javax.swing.JButton;
import javax.swing.JLabel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Font;

public class Gui {

	private JFrame frmRastojanje;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Gui window = new Gui();
					window.frmRastojanje.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	} 
	
	JComboBox pocetniGrad = new JComboBox();
	JComboBox krajnjiGrad = new JComboBox();
	JTextArea textResenja = new JTextArea();
	JButton dugmeRuta = new JButton("Ruta");
	JButton dugmeObrisi = new JButton("Obri\u0161i");

	private void initialize() {
		frmRastojanje = new JFrame();
		frmRastojanje.getContentPane().setBackground(Color.PINK);
		frmRastojanje.setTitle("Rastojanje izme\u0111u dva mesta u Evropi");
		frmRastojanje.setBounds(0, 0, 450, 350);
		frmRastojanje.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmRastojanje.getContentPane().setLayout(null);
		
		//izgled padajuceg menija za odabir pocetnog mesta
		
		pocetniGrad.setBounds(10, 55, 90, 25);
        pocetniGrad.setFont(new Font("Roboto", Font.BOLD, 14));
        pocetniGrad.setForeground(Color.PINK);
        pocetniGrad.setBackground(Color.BLACK);
        
		frmRastojanje.getContentPane().add(pocetniGrad);
		
		//izgled padajuceg menija za odabir krajnjeg mesta
		
		krajnjiGrad.setBounds(10, 115, 90, 25);
        krajnjiGrad.setFont(new Font("Roboto", Font.BOLD, 14));
        krajnjiGrad.setForeground(Color.PINK);
        krajnjiGrad.setBackground(Color.BLACK);
        
		frmRastojanje.getContentPane().add(krajnjiGrad);

		//izgled prozora na kome se prikazuju rastojanje i putanja
		
		textResenja.setEditable(false);
		textResenja.setBounds(10, 165, 414, 108);
        textResenja.setFont(new Font("Roboto", Font.BOLD, 12));
        textResenja.setBackground(Color.BLACK);
        textResenja.setForeground(Color.PINK);
		textResenja.setEnabled(false);
		
		frmRastojanje.getContentPane().add(textResenja);

		dugmeRuta.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Scanner input = null;
				
				try {
					input = new Scanner(new File("D:/Faks/treca godina/softverski inzenjering/Ђорђе Молнар 660-2019/mestaEvropa/Tema23.txt"));
				} catch (FileNotFoundException ex) {
					Logger.getLogger(Gui.class.getName()).log(Level.SEVERE, null, ex);
				}
				
				dugmeObrisi.setEnabled(true);
				dugmeRuta.setEnabled(false);
				
				String line = input.nextLine();
				String[] divide = line.split(" ");
				
				int brojGradova = Integer.parseInt(divide[0]);
				int matrix[][] = new int[brojGradova + 1][brojGradova + 1];
				
				while (input.hasNextLine()) {
					String red = input.nextLine();
					String[] parts = red.split(" ");
					
					int grad1 = Integer.parseInt(parts[0]);
					int grad2 = Integer.parseInt(parts[1]);
					int rastojanje = Integer.parseInt(parts[2]);
					
					matrix[grad1][grad2] = rastojanje;
				}
				
				Dijkstra grad = new Dijkstra();
				int pocetno = Integer.parseInt((String) pocetniGrad.getSelectedItem());
				int krajnje = Integer.parseInt((String) krajnjiGrad.getSelectedItem());
				textResenja.setText(grad.alg(matrix, pocetno, krajnje));
			}
		});
		
		//izgled dugmeta za unos zeljenih mesta
		
		dugmeRuta.setBounds(150, 90, 90, 25);
		dugmeRuta.setBackground(Color.BLACK);
		dugmeRuta.setForeground(Color.PINK);
        dugmeRuta.setFont(new Font("Roboto", Font.BOLD, 14));
		frmRastojanje.getContentPane().add(dugmeRuta);
		dugmeObrisi.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dugmeObrisi.setEnabled(false);
				dugmeRuta.setEnabled(true);
				
				pocetniGrad.setSelectedIndex(0);
				krajnjiGrad.setSelectedIndex(0);
				textResenja.setText(null);
			}
		});		

		//izgled dugmeta za brisanje prethodnog rastojanja
		
		dugmeObrisi.setBounds(178, 280, 89, 23);
		dugmeObrisi.setBackground(Color.BLACK);
		dugmeObrisi.setForeground(Color.PINK);
        dugmeObrisi.setFont(new Font("Roboto", Font.BOLD, 14));
		dugmeObrisi.setEnabled(false);
		frmRastojanje.getContentPane().add(dugmeObrisi);
		
		//izgled labela pojedinacno
		
		JLabel lblNewLabel = new JLabel("Po\u010Detno mesto:");
		lblNewLabel.setBounds(10, 30, 100, 14);
		lblNewLabel.setFont(new Font("Roboto", Font.BOLD, 12));
		lblNewLabel.setForeground(Color.BLACK);
		frmRastojanje.getContentPane().add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Krajnje mesto:");
		lblNewLabel_1.setBounds(10, 90, 100, 14);
		lblNewLabel_1.setFont(new Font("Roboto", Font.BOLD, 12));
		lblNewLabel_1.setForeground(Color.BLACK);
		frmRastojanje.getContentPane().add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("Putanja od po\u010Detnog do krajnjeg mesta:");
		lblNewLabel_2.setBounds(120, 140, 250, 14);
		lblNewLabel_2.setFont(new Font("Roboto", Font.BOLD, 12));
		lblNewLabel_2.setForeground(Color.BLACK);
		frmRastojanje.getContentPane().add(lblNewLabel_2);
	}
	
	public Gui() throws FileNotFoundException {
		initialize();
		
		Scanner input = new Scanner(new File("D:/Faks/treca godina/softverski inzenjering/Ђорђе Молнар 660-2019/mestaEvropa/Tema23.txt"));
		String line = input.nextLine();
		String[] divide = line.split(" ");
		
		int brojGradova = Integer.parseInt(divide[0]);
		int listaGradova[] = new int[brojGradova + 1];
		
		while (input.hasNextLine()) {
			String red = input.nextLine();
			String[] parts = red.split(" ");
			
			int grad1 = Integer.parseInt(parts[0]);
			int grad2 = Integer.parseInt(parts[1]);
			
			listaGradova[grad1] = 1;
			listaGradova[grad2] = 1;
		}
		
		for(int i = 0; i < listaGradova.length; i++)
			if(listaGradova[i] == 1) {
				pocetniGrad.addItem(Integer.toString(i));
				krajnjiGrad.addItem(Integer.toString(i));
			}
	}
}

