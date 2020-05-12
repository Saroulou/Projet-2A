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


public class Mondes extends JFrame implements ActionListener{
	
	protected JButton Fond1;
	protected JButton Fond2;
	protected JButton Fond3;
	protected JButton Fond4;
	protected String FondChoisi=" "; //accès au nom du fond choisi;
	
public Mondes () {
			
			this.setTitle("mondes");
			this.setLayout(null);
			this.setResizable(false);
			this.setSize(987,593);
			this.setLocation(200,200); //position de la fenetre sur l'écran
			
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
			
			Fond1 = new JButton("neige");
			Fond1.setBounds(150,225,200,50);
			Fond1.setBackground(Color.white);
			Fond1.addActionListener(this);
			background.add(Fond1);
			
			
			Fond2 = new JButton("Desert");
			Fond2.setBounds(630,225,200,50);
			Fond2.setBackground(Color.white);
			Fond2.addActionListener(this);
			background.add(Fond2);
			
			
			Fond3 = new JButton("neige2");
			Fond3.setBounds(150,500,200,50);
			Fond3.setBackground(Color.white);
			Fond3.addActionListener(this);
			background.add(Fond3);
			
			Fond4 = new JButton("Crystal");
			Fond4.setBounds(630,500,200,50);
			Fond4.setBackground(Color.white);
			Fond4.addActionListener(this);
			background.add(Fond4);
			
			JLabel ImFond4 = new JLabel();
			ImageIcon im4 = new ImageIcon(new ImageIcon("crystal.jpg").getImage().getScaledInstance(350, 175, Image.SCALE_DEFAULT)); // redimensionne l'image 
			ImFond4.setIcon(im4);
			ImFond4.setBounds(540,300,350,175);
			background.add(ImFond4);	
			
			
			JLabel ImFond1 = new JLabel();
			ImageIcon im1 = new ImageIcon(new ImageIcon("neige.jpg").getImage().getScaledInstance(350, 175, Image.SCALE_DEFAULT)); // redimensionne l'image 
			ImFond1.setIcon(im1);
			ImFond1.setBounds(80,20,350,175);
			background.add(ImFond1);	
			
			JLabel ImFond3 = new JLabel();
			ImageIcon im3 = new ImageIcon(new ImageIcon("neige2.jpg").getImage().getScaledInstance(350, 175, Image.SCALE_DEFAULT)); // redimensionne l'image 
			ImFond3.setIcon(im3);
			ImFond3.setBounds(80,300,350,175);
			background.add(ImFond3);
			
			JLabel ImFond2 = new JLabel();
			ImageIcon im2 = new ImageIcon(new ImageIcon("Desert.jpg").getImage().getScaledInstance(350, 175, Image.SCALE_DEFAULT)); // redimensionne l'image 
			ImFond2.setIcon(im2);
			ImFond2.setBounds(540,20,350,175);
			background.add(ImFond2);	
			
			/** JLabel contenant le fond **/
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
			if(e.getSource()==Fond1) {
				FondChoisi=Fond1.getText();
				System.out.println(FondChoisi);
			}
			if(e.getSource()==Fond2) {
				FondChoisi=Fond2.getText();
				System.out.println(FondChoisi);
			}
			if(e.getSource()==Fond3) {
				FondChoisi=Fond3.getText();
				System.out.println(FondChoisi);
			}	
			if(e.getSource()==Fond4) {
				FondChoisi=Fond4.getText();
				System.out.println(FondChoisi);
			}	
		}
		
		public static void main (String[] args) {     
        Mondes mondes=new Mondes();
    }
		
}



