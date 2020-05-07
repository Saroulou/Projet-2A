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
    private final int NB_BOTS = 1; // nombre d'avions bots
    private Timer timer;
    private final int var=100;
    private ImageIcon sBackground; // permet de stocker l'image du fond d'écran
    private Image iBackground; 
    private final int largeurBackground = 1000; 
    public int xBackground; // permet de determiner l'abcisse de l'image 
    public final int vBackground = 2; // vitesse de déplacement de l'arrière-plan
    private int var1=0;// compteur de temps
    private int varBombe=0;// temps initial du lancement de la bombe
    private int varMissile=0;// temps initial du lancement de la bombe
    //JLabel explosions = new JLabel(); //contient les images des explosions des missiles
    private int varBalle=0;
    private int vieAvion=10;
    private int vieBot=10;
	
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
        for (int i = 0; i < NB_BOTS; i++) {
            avions.add(new AvionBot(avions,getHeight(),getWidth(),this));
        }

        addKeyListener(this);

        setVisible(true);
    }

    public Fenetre(){
        this("Skywar",1500,700);
    }

// METHODES

    // permet de dessiner l'écran et de rajouter les images "à la suite".
    private void movingBackground(Graphics g){
        if(this.xBackground == -this.largeurBackground){
            this.xBackground = 0;
            }
        g.drawImage(this.iBackground, this.xBackground, 0, null);
        g.drawImage(this.iBackground, this.xBackground + this.largeurBackground, 0, null);
        g.drawImage(this.iBackground, this.xBackground+ this.largeurBackground * 2, 0, null);
        g.drawImage(this.iBackground, this.xBackground + this.largeurBackground * 3, 0, null);
}

    public void paint (Graphics g){
        g.setColor(new Color (6,55,58));
        g.fillRect(0,0,this.getWidth(),this.getHeight());
        this.movingBackground(g);
        g.setColor(new Color (255,255,255));
        g.drawRect(20, 50,this.getWidth()/7,this.getHeight()/15);

        
        for(Avion av: avions){
            av.dessine(g);
            av.dessineVie(vieAvion, g);
            if(av.y > getHeight()) {
                av.exploser(g);
            }
            
            for (Missiles m: av.missiles) {
                if (m != null){
                    m.dessine(g);
                }
                if(var1-varMissile>=1500) {
                    m.exploser(g);

                    //av.missiles.remove(m); 
                }
                // explosions.remove(m); //suppresion image de l'explosion
            }
            for (Mitrailleuse b: av.balles) {
                if (b != null) {
                    b.dessine(g);
                }
            /*if(var1-varBalle>=5000) {
                av.balles.remove(b);
                }*/
            }
  
            for(Bombe b:av.listebombe){
                 if(b!=null){
                    b.dessine(g);
                }
            }
        }

    }


    public void actionPerformed(ActionEvent e){
		//var1++;
	   var1 = var1 + var;
		
        for(Avion av: avions){
            av.avancer((double) vBackground);
            av.tourner();
            for(Bombe b:av.listebombe){
                if(b!=null){
					System.out.println("Bombe");
                    b.tombe(this.var1, varBombe, (double) vBackground);//Ajout du temps dans la méthode tombe
                    if(b.estsorti()){
                        av.listebombesuppr.add(b);

                        }

                    }

                }
                for (Missiles m: av.missiles) {
                    if (m != null)
                        m.avancer((double) vBackground);
                        if(var1-varMissile>=2000) {
                        av.listemissilesuppr.add(m);
                    }

                }
                for (Mitrailleuse b: av.balles) {
                    if (b != null)
                        b.avancer((double) vBackground);
                        if(var1-varBalle>=5000) {
                    av.listeballesuppr.add(b);
                    }
                }
                 for(Missiles m: av.listemissilesuppr){
                av.missiles.remove(m);
                
            }
            for(Mitrailleuse b: av.listeballesuppr){
                av.balles.remove(b);
                
            }
            for(Bombe b: av.listebombesuppr){
                av.listebombe.remove(b);
            }
            
            av.listebombesuppr.clear();  
            av.listemissilesuppr.clear(); 
            av.listeballesuppr.clear();        
        }
        
        
        xBackground -= vBackground; // position actualisée avec la vitesse de l'arrière plan
        repaint(); // appel a la methode paint 
         
    }


    public void keyPressed(KeyEvent e){ 
        int code = e.getKeyCode();

        if(code==KeyEvent.VK_LEFT){
            avions.get(0).tourner(-10);

        }else if(code == KeyEvent.VK_RIGHT){

            avions.get(0).tourner(10);

        }else if(code==KeyEvent.VK_DOWN){
        avions.get(0).tirerBombe();
        varBombe=var1;
        
        }if(code == KeyEvent.VK_SPACE) {
            avions.get(0).tirerMissiles();
            varMissile=var1;// Temps de lancement initial quand on appuie sur la touche;
        }if(code == KeyEvent.VK_W) {
            avions.get(0).tirerBalles();
            varBalle=var1;
            }
    }


    public void keyReleased(KeyEvent e){}
    
    public void keyTyped(KeyEvent e){}
    
    public static void main (String[] args) {     
        Fenetre fenetre=new Fenetre();
    }
    
}





