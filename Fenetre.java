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


public class Fenetre extends JFrame implements ActionListener, KeyListener{
	
// ATTRIBUTS

    private ArrayList<Avion> avions;
    private ArrayList<AvionBot> avionsBot;
    private Timer timer;
    private final int var=10;
    private ImageIcon sBackground; // permet de stocker l'image du fond d'écran
	private Image iBackground; 
	private final int largeurBackground = 1000;	
	public int xBackground; // permet de determiner l'abcisse de l'image 
	private int var1=0;// compteur de temps
	private int varBombe=0;// temps initial du lancement de la bombe
	private int varMissile=0;// temps initial du lancement de la bombe
    //JLabel explosions = new JLabel(); //contient les images des explosions des missiles
    
	
// CONSTRUCTEURS

    public Fenetre(String nom, int width, int height){

        super(nom);
		this.sBackground = new ImageIcon(getClass().getResource("Background.jpg"));
		this.iBackground = this.sBackground.getImage();
		this.xBackground=0;
		
		
		
        setSize(width, height);
        setLocation(200,200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);
		
        timer=new Timer(var, this);
        timer.start();
        
        avions=new ArrayList<Avion>();
        avions.add(new Avion(getHeight(),getWidth(),this));
        
        
        avionsBot=new ArrayList<AvionBot>();
        avionsBot.add(new AvionBot(getHeight(),getWidth(),this));

        addKeyListener(this);

        setVisible(true);
    }

// METHODES

    public Fenetre(){
        this("Skywar",1500,700);
    }
    // permet de dessiner l'écran et de rajouter les images "à la suite".
    
    private void movingBackground(Graphics g){
		if(this.xBackground == -this.largeurBackground){this.xBackground = 0;}
		g.drawImage(this.iBackground, this.xBackground, 0, null);
		g.drawImage(this.iBackground, this.xBackground + this.largeurBackground, 0, null);
		g.drawImage(this.iBackground, this.xBackground+ this.largeurBackground * 2, 0, null);
		g.drawImage(this.iBackground, this.xBackground + this.largeurBackground * 3, 0, null);
}

    public void paint (Graphics g){
       this.movingBackground(g);
        g.setColor(new Color (100,20,70));
        //g.fillRect(0,0,this.getWidth(),this.getHeight());
        
         for(AvionBot avb: avionsBot){
            avb.dessine(g);
    	}
        	for(Avion av: avions){
            av.dessine(g);
            if(av.y==543) {av.exploser(g);} //si avion touche le sol y = 543
            
            
            for (Missiles m: av.missiles) {
                if (m != null)
                m.dessine(g);
                if(var1-varMissile>=1500) {
                	m.exploser(g);

                	av.missiles.remove(m);
                }
                // explosions.remove(m); //suppresion image de l'explosion
            }
            for (Mitrailleuse b: av.balles) {
				if (b != null)
            b.dessine(g);
            }
  
            for(Bombe b:av.listebombe){
                 if(b!=null){
                     b.dessine(g);
                }
            }
            for (Missiles m: av.missiles) {
                if (m != null)
                m.dessine(g);

            }
        }
    }


    public void actionPerformed(ActionEvent e){
		var1++;
		if(var1%10==0){
        for(Avion av: avions){
            av.avancer();
            for(Bombe b:av.listebombe){
                if(b!=null){
                    b.tombe(this.var1*this.var,varBombe);//Ajout du temps dans la méthode tombe
                    if(b.estsorti()){
                        av.listebombesuppr.add(b);

                    }

                }

            }
            for (Missiles m: av.missiles) {
                if (m != null)
                    m.avancer();

            }
            for(Bombe b: av.listebombesuppr){
			av.listebombe.remove(b);
		}
		av.listebombesuppr.clear();      
	}
        
        
		xBackground --; // on récupere xBackground - 1 
		repaint(); // appel a la methode paint 
		 
    }
}

    public void keyPressed(KeyEvent e){ 
        int code = e.getKeyCode();

        if(code==KeyEvent.VK_LEFT){
            avions.get(0).tourner(-10);

        }else if(code == KeyEvent.VK_RIGHT){

            avions.get(0).tourner(10);

        }else if(code==KeyEvent.VK_DOWN){
        avions.get(0).tirerBombe();
        
        }if(code == KeyEvent.VK_SPACE) {
            avions.get(0).tirerMissiles();
            varMissile=var1;// Temps de lancement initial quand on appuie sur la touche;
        }if(code == KeyEvent.VK_W) {
			avions.get(0).tirerBalles();
			}
    }


    public void keyReleased(KeyEvent e){}
    public void keyTyped(KeyEvent e){}

}




