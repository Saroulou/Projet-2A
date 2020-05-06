
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


@SuppressWarnings("serial")
public class Menu extends JFrame implements ActionListener{


	
	protected JButton jouer;
	protected JButton magasin;
	public Fenetre fenetrejeu;

		

		public Menu() {
			
			this.setTitle("SkyWar");
			this.setLayout(null);
			this.setVisible(true);
			this.setResizable(false);
			//pour mettre la fenetre en full screen
			//this.setSize(this.getToolkit().getScreenSize());
			//this.setLocationRelativeTo(null);
			//this.validate();
			this.setSize(900,600);
			this.setLocation(0,0); //position de la fenetre sur l'écran
			this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			
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
			
			jouer = new JButton("Play");
			jouer.setBounds(350,300,200,50);
			jouer.setBackground(Color.white);
			jouer.addActionListener(this);
			background.add(jouer);
			
			magasin = new JButton("magasin");
			magasin.setBounds(350,370,200,50);
			magasin.setBackground(Color.white);
			background.add(magasin);
			
			JLabel title = new JLabel();
			ImageIcon image2 = new ImageIcon("titrejeu2.png");
			title.setIcon(image2);
			title.setBounds(250,50,500,300);
			background.add(title);
			
			JLabel titre = new JLabel();			
			ImageIcon Imagee = new ImageIcon("avion.png");
			titre.setIcon(Imagee);
			titre.setBounds(0,0,this.getWidth(),this.getHeight());
			background.add(titre);
			
			/** JLabel contenant le fond **/
			JLabel image = new JLabel();
			ImageIcon Imagee1 = new ImageIcon("avion.png");
			image.setIcon(Imagee1);
			image.setBounds(0,0,this.getWidth(),this.getHeight());
			background.add(image);
			 
			
			/** panneau 2**/
			
			panneauGlobal.add(background);
			this.add(panneauGlobal);
			this.setVisible(true);

		}
		
		

		public void actionPerformed(ActionEvent e) {
			if(e.getSource()==jouer) {
				fenetrejeu = new Fenetre();
				fenetrejeu.setVisible(true); //rend visible fenetre du jeu

			}
			
		}
		
		public static void main(String[] args) {
			new Menu();
		}
		
		
}



