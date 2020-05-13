
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


@SuppressWarnings("serial")
public class Menu extends JFrame implements ActionListener{

<<<<<<< HEAD

    protected JButton mondes;
    protected JButton Score;
    protected JButton jouer;
    protected JButton magasin;
    public Fenetre fenetrejeu;
    public Mondes fenetreMondes;
    public Magasin fenetreMagasin;
=======
	protected JOptionPane BestScore;
	public JFrame frame;
	protected JButton mondes;
	protected JButton Score;
	protected JButton jouer;
	protected JButton magasin;
	public Fenetre fenetrejeu;
	public Mondes fenetreMondes;
	public Magasin fenetreMagasin;
>>>>>>> b71fbe77dc6f8f84f025b8f206d87064d38f0815

        

<<<<<<< HEAD
        public Menu() {
            
            this.setTitle("SkyWar");
            this.setLayout(null);
            //this.setVisible(true);
            this.setResizable(false);
            //pour mettre la fenetre en full screen
            //this.setSize(this.getToolkit().getScreenSize());
            //this.setLocationRelativeTo(null);
            //this.validate();
            this.setSize(987,593);
            this.setLocation(200,200); //position de la fenetre sur l'Ã©cran
            this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            fenetreMondes=new Mondes();// instanciation de Mondes
            fenetreMagasin=new Magasin();
            Toolkit kit = Toolkit.getDefaultToolkit();
            
            
            // Modifier l'icÃ´ne de JFrame
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
            
            Score = new JButton("Score");
            Score.setBounds(260,250,200,50);
            Score.setBackground(Color.white);
            background.add(Score);
            Score.addActionListener(this);
            
            mondes = new JButton("mondes");
            mondes.setBounds(260,400,200,50);
            mondes.setBackground(Color.white);
            background.add(mondes);
            mondes.addActionListener(this);
            
            jouer = new JButton("Jouer");
            jouer.setBounds(530,250,200,50);
            jouer.setBackground(Color.white);
            jouer.addActionListener(this);
            background.add(jouer);
            
            magasin = new JButton("magasin"); 
            magasin.setBounds(530,400,200,50);
            magasin.setBackground(Color.white);
            background.add(magasin);
            magasin.addActionListener(this);
            
            JLabel title = new JLabel();
            ImageIcon image2 = new ImageIcon("titrejeu2.png");
            title.setIcon(image2);
            title.setBounds(295,-50,500,300);
            background.add(title);
            
            JLabel titre = new JLabel();            
            ImageIcon Imagee = new ImageIcon("BackgroundMenue1.png");
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
                if(fenetreMondes.FondChoisi.equals(" ") && fenetreMagasin.AvionChoisi.equals(" ")){ //dÃ©fini le background par dÃ©faut
                fenetrejeu = new Fenetre(); //appel du 2eme constructeur
                fenetrejeu.setVisible(true);// rend la fenetre de jeu visible
                }else if(fenetreMondes.FondChoisi.equals(" ")){
                fenetrejeu = new Fenetre("Background",fenetreMagasin.AvionChoisi);//appel du 3eme constructeur
                fenetrejeu.setVisible(true);
                }else if(fenetreMagasin.AvionChoisi.equals(" ")){
                fenetrejeu = new Fenetre(fenetreMondes.FondChoisi);//appel du 3eme constructeur
                fenetrejeu.setVisible(true);
                }else{
                fenetrejeu = new Fenetre(fenetreMondes.FondChoisi,fenetreMagasin.AvionChoisi);//appel du 3eme constructeur
                fenetrejeu.setVisible(true);
                    
                }


            }
            if(e.getSource()==mondes){
                fenetreMondes.setVisible(true);
                // rend visible la fenetre Mondes   
            }
            if(e.getSource()==magasin){
                fenetreMagasin.setVisible(true);
                // rend visible la fenetre du Magasin
            }
            
    }
        
        public static void main(String[] args) {
            new Menu();
        }
        
        
=======
		public Menu() {
			
			this.setTitle("SkyWar");
			this.setLayout(null);
			//this.setVisible(true);
			this.setResizable(false);
			//pour mettre la fenetre en full screen
			//this.setSize(this.getToolkit().getScreenSize());
			//this.setLocationRelativeTo(null);
			//this.validate();
			this.setSize(987,593);
			this.setLocation(450,250); //position de la fenetre sur l'écran
			this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			fenetreMondes=new Mondes();// instanciation de Mondes
			fenetreMagasin=new Magasin();
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
			
			Score = new JButton("Score");
			Score.setBounds(260,250,200,50);
			Score.setBackground(Color.white);
			background.add(Score);
			Score.addActionListener(this);
			
			mondes = new JButton("mondes");
			mondes.setBounds(260,400,200,50);
			mondes.setBackground(Color.white);
			background.add(mondes);
			mondes.addActionListener(this);
			
			jouer = new JButton("Jouer");
			jouer.setBounds(530,250,200,50);
			jouer.setBackground(Color.white);
			jouer.addActionListener(this);
			background.add(jouer);
			
			magasin = new JButton("magasin"); 
			magasin.setBounds(530,400,200,50);
			magasin.setBackground(Color.white);
			background.add(magasin);
			magasin.addActionListener(this);
			
			JLabel title = new JLabel();
			ImageIcon image2 = new ImageIcon("titrejeu2.png");
			title.setIcon(image2);
			title.setBounds(295,-50,500,300);
			background.add(title);
			
			JLabel titre = new JLabel();			
			ImageIcon Imagee = new ImageIcon("BackgroundMenue1.png");
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
			Audio.playSoundL("/son/musiquedefond.wav"); 
			frame= new JFrame();// fenetre score
			frame.setBounds(530,250,200,50);
			frame.setBackground(Color.white);
			background.add(frame);
		}
		
		

		public void actionPerformed(ActionEvent e) {
			if(e.getSource()==jouer) {
				Audio.playSound("/son/click.wav"); 
				//Audio.playSound("/son/song.wav");
				if(fenetreMondes.FondChoisi.equals(" ") && fenetreMagasin.AvionChoisi.equals(" ")){ //défini le background par défaut
				fenetrejeu = new Fenetre(); //appel du 2eme constructeur
				fenetrejeu.setVisible(true);// rend la fenetre de jeu visible
				}else if(fenetreMondes.FondChoisi.equals(" ")){
				fenetrejeu = new Fenetre("Background",fenetreMagasin.AvionChoisi);//appel du 3eme constructeur
				fenetrejeu.setVisible(true);
				}else if(fenetreMagasin.AvionChoisi.equals(" ")){
				fenetrejeu = new Fenetre(fenetreMondes.FondChoisi);//appel du 2eme constructeur
				fenetrejeu.setVisible(true);
				}else{
				fenetrejeu = new Fenetre(fenetreMondes.FondChoisi,fenetreMagasin.AvionChoisi);//appel du 3eme constructeur
				fenetrejeu.setVisible(true);
					
				}


			}
			if(e.getSource()==mondes){
				Audio.playSound("/son/click.wav");
				fenetreMondes.setVisible(true);
				
				// rend visible la fenetre Mondes	
			}
			if(e.getSource()==magasin){
				Audio.playSound("/son/click.wav");
				fenetreMagasin.setVisible(true);
				// rend visible la fenetre du Magasin
			}
			if(e.getSource ()==Score){
				Audio.playSound("/son/click.wav");
				BestScore.showMessageDialog(frame,"Best Score=");
				// donne le meilleure score obtenue 
			}
			
	}
		
		public static void main(String[] args) {
			new Menu();
		}
		
		
>>>>>>> b71fbe77dc6f8f84f025b8f206d87064d38f0815
}



