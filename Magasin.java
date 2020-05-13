import javax.swing.*;
import java.awt.event.*;
import java.awt.*;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JTextField;
import java.util.ArrayList;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class Magasin extends JFrame implements ActionListener{
	
	protected JButton Avion1;
	protected JButton Avion2;
	protected JButton Avion3;
	protected JButton Avion4;
	protected JButton Avion5;
	protected JButton Avion6;
	protected String AvionChoisi=" "; //accès au nom du Avion choisi;
	
public Magasin () {
			
			this.setTitle("Magasin");
			this.setLayout(null);
			this.setResizable(false);
			this.setSize(987,593);
			this.setLocation(450,250); //position de la fenetre sur l'écran
			
			Toolkit kit = Toolkit.getDefaultToolkit();
			
			
		    // Modifier l'icône de JFrame
		    Image img = kit.getImage("icone.jpg");
		    setIconImage(img);
			
			
			/**panneau Global**/ 
			JPanel panneauGlobal = new JPanel();
			panneauGlobal.setLayout(null);
			panneauGlobal.setBounds(0,0,this.getWidth(),this.getHeight());
	
						
			/** panneau 1**/
			JPanel background = new JPanel();
			background.setLayout(null);
			background.setBounds(0,0,this.getWidth(),this.getHeight());	
			
			Avion1 = new JButton("Avion1");
			Avion1.setBounds(100,225,200,50);
			Avion1.setBackground(Color.white);
			Avion1.addActionListener(this);
			background.add(Avion1);
			
			
			Avion2 = new JButton("Avion2");
			Avion2.setBounds(100,500,200,50);
			Avion2.setBackground(Color.white);
			Avion2.addActionListener(this);
			background.add(Avion2);
			
			
			Avion3 = new JButton("Avion3");
			Avion3.setBounds(400,225,200,50);
			Avion3.setBackground(Color.white);
			Avion3.addActionListener(this);
			background.add(Avion3);
			
			Avion4 = new JButton("Avion4");
			Avion4.setBounds(400,500,200,50);
			Avion4.setBackground(Color.white);
			Avion4.addActionListener(this);
			background.add(Avion4);
			
			Avion5 = new JButton("Avion5");
			Avion5.setBounds(700,225,200,50);
			Avion5.setBackground(Color.white);
			Avion5.addActionListener(this);
			background.add(Avion5);
			
			Avion6 = new JButton("Avion6");
			Avion6.setBounds(700,500,200,50);
			Avion6.setBackground(Color.white);
			Avion6.addActionListener(this);
			background.add(Avion6);
			
			
			JLabel ImAvion1 = new JLabel();
			ImageIcon im1 = new ImageIcon(new ImageIcon("Avion1.png").getImage().getScaledInstance(175, 85, Image.SCALE_DEFAULT)); // redimensionne l'image 
			ImAvion1.setIcon(im1);
			ImAvion1.setBounds(100,20,350,175);
			background.add(ImAvion1);	
			
			JLabel ImAvion2 = new JLabel();
			ImageIcon im2 = new ImageIcon(new ImageIcon("Avion2.png").getImage().getScaledInstance(175, 85, Image.SCALE_DEFAULT)); // redimensionne l'image 
			ImAvion2.setIcon(im2);
			ImAvion2.setBounds(100,300,350,175);
			background.add(ImAvion2);	
			
			JLabel ImAvion3 = new JLabel();
			ImageIcon im3 = new ImageIcon(new ImageIcon("Avion3.png").getImage().getScaledInstance(175, 85, Image.SCALE_DEFAULT)); // redimensionne l'image 
			ImAvion3.setIcon(im3);
			ImAvion3.setBounds(400,20,350,175);
			background.add(ImAvion3);
			
				JLabel ImAvion4 = new JLabel();
			ImageIcon im4 = new ImageIcon(new ImageIcon("Avion4.png").getImage().getScaledInstance(175, 85, Image.SCALE_DEFAULT)); // redimensionne l'image 
			ImAvion4.setIcon(im4);
			ImAvion4.setBounds(400,300,350,175);
			background.add(ImAvion4);	
			
			JLabel ImAvion5 = new JLabel();
			ImageIcon im5 = new ImageIcon(new ImageIcon("Avion5.png").getImage().getScaledInstance(175, 85, Image.SCALE_DEFAULT)); // redimensionne l'image 
			ImAvion5.setIcon(im5);
			ImAvion5.setBounds(700,20,350,175);
			background.add(ImAvion5);
			
			JLabel ImAvion6 = new JLabel();
			ImageIcon im6 = new ImageIcon(new ImageIcon("Avion6.png").getImage().getScaledInstance(175, 85, Image.SCALE_DEFAULT)); // redimensionne l'image 
			ImAvion6.setIcon(im6);
			ImAvion6.setBounds(700,300,350,175);
			background.add(ImAvion6);	
			
			/** JLabel contenant le Avion **/
			JLabel image = new JLabel();
			ImageIcon Imagee1 = new ImageIcon("Fond.jpg");
			image.setIcon(Imagee1);
			image.setBounds(0,0,this.getWidth(),this.getHeight());
			background.add(image);
			 
			
			/** panneau 2**/
			
			panneauGlobal.add(background);
			this.add(panneauGlobal);

		}
		
		

		public void actionPerformed(ActionEvent e) {
			if(e.getSource()==Avion1) {
				Audio.playSound("/son/click.wav");
				AvionChoisi=Avion1.getText();
				System.out.println(AvionChoisi);
				this.setVisible(false);
			}
			if(e.getSource()==Avion2) {
				Audio.playSound("/son/click.wav");
				AvionChoisi=Avion2.getText();
				System.out.println(AvionChoisi);
				this.setVisible(false);
			}
			if(e.getSource()==Avion3) {
				Audio.playSound("/son/click.wav");
				AvionChoisi=Avion3.getText();
				System.out.println(AvionChoisi);
				this.setVisible(false);
			}	
			if(e.getSource()==Avion4) {
				Audio.playSound("/son/click.wav");
				AvionChoisi=Avion4.getText();
				System.out.println(AvionChoisi);
				this.setVisible(false);
			}
			if(e.getSource()==Avion5) {
				Audio.playSound("/son/click.wav");
				AvionChoisi=Avion5.getText();
				System.out.println(AvionChoisi);
				this.setVisible(false);
			}
			if(e.getSource()==Avion6) {
				Audio.playSound("/son/click.wav");
				AvionChoisi=Avion6.getText();
				System.out.println(AvionChoisi);
				this.setVisible(false); //Rendre la fenetre invisible après le clic
			}
				
		}
		
		public static void main (String[] args) {     
        Magasin mondes=new Magasin();
    }
		
}
