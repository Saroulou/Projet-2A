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
    public final int vBackground =5; // vitesse de déplacement de l'arrière-plan
    private int var1=0;// compteur de temps
    private int varBombe=0;// temps initial du lancement de la bombe
    private int varMissile=0;// temps initial du lancement de la bombe
    //JLabel explosions = new JLabel(); //contient les images des explosions des missiles
    private int varBalle=0;
    private int vieBot=10;
    private int scoreFinal;//donne le score final à stocker
    private String texteScore;
    private String avionChoisi=" ";
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
        avions.add(new Avion(getHeight(),getWidth(),this, "Joueur","avionJC"));
        for (int i = 0; i < NB_BOTS; i++) {
            avions.add(new AvionBot(avions,getHeight(),getWidth(),this, Integer.toString(i)));
        }

        addKeyListener(this);

        setVisible(true);
    }

    public Fenetre(){
        this("Skywar",1500,700);
    }
    public Fenetre (String Background){ //constructeur avec choix background
		super("Skywar");
        this.sBackground = new ImageIcon(getClass().getResource(Background+".jpg"));
        this.iBackground = this.sBackground.getImage();
        this.xBackground=0;

        setSize(1500, 700);
        setLocation(200,200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);
        
        timer=new Timer(var, this);
        timer.start();
        
        avions=new ArrayList<Avion>();
        avions.add(new Avion(getHeight(),getWidth(),this, "Joueur","avionJC"));
        for (int i = 0; i < NB_BOTS; i++) {
            avions.add(new AvionBot(avions,getHeight(),getWidth(),this, Integer.toString(i)));
        }

        addKeyListener(this);

        setVisible(true);
		

	}
	 public Fenetre (String Background,String avionChoisi){//contructeur avec choix background et avion;
		super("Skywar");
        this.sBackground = new ImageIcon(getClass().getResource(Background+".jpg"));
        this.iBackground = this.sBackground.getImage();
        this.xBackground=0;
        this.avionChoisi=avionChoisi;

        setSize(1500, 700);
        setLocation(200,200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);
        
        timer=new Timer(var, this);
        timer.start();
        
        avions=new ArrayList<Avion>();
        avions.add(new Avion(getHeight(),getWidth(),this, "Joueur",avionChoisi));
        for (int i = 0; i < NB_BOTS; i++) {
            avions.add(new AvionBot(avions,getHeight(),getWidth(),this, Integer.toString(i)));
        }

        addKeyListener(this);

        setVisible(true);
        
       
		

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
        //g.setColor(new Color (6,55,58));
        //g.fillRect(598,628,this.getWidth(),this.getHeight());
        this.movingBackground(g);
        g.setColor(new Color (255,255,255));
        g.drawRect(599, 629,this.getWidth()/7+1,this.getHeight()/15+1);
        
        //score:
        g.setColor(Color.white);
        g.drawString(texteScore,850,650);

        
        for(Avion av: avions){
            av.dessine(g);
 
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
          var1 = var1 + var ;
          // System.out.println("var 1= "+var1);
          // System.out.println("var = "+var);
        int r = (int) (Math.random()*100+1); //génération de nombres aléatoires pour les avions bot
         int p = (int) (Math.random()*50+1);
        for(Avion av: avions){
            av.avancer((double) vBackground);
            av.tourner();
            
            if (av instanceof AvionBot){//vérifier si l'avion est un bot
                
            if(r%7==0 && r%4==0) { //tirs à tps aléatoires
                av.tirerMissiles();     
            }
            
            if(p%7==0) { //tirs à tps aléatoires
                av.tirerBalles();
            }
            
        }
            

            ArrayList<Objet> objets = new ArrayList<Objet>(avions); // liste de tous les objets
            objets.addAll(av.listebombe);
            objets.addAll(av.missiles);
            objets.addAll(av.balles);

            for (Objet obj: objets){ // vérifie avec s'il y a une collison avec un autre avion/objet en vérifiant pour chaque autre objet
                System.out.println(obj);
                if (av != obj) {
                    if (av.collison(obj)) {
                        System.out.println("Collision entre " + av.toString() + " et " + obj.toString());
                        if(obj instanceof Bombe){
                            //av.vie=av.vie-obj.degatbombe;
                            av.vie=av.vie-3;
                        } else if (obj instanceof Missiles){
                            //av.vie=av.vie-obj.degatsM;
                            av.vie=av.vie-2;
                        }else if (obj instanceof Mitrailleuse){
                            //av.vie=av.vie-obj.degatMitr;
                            av.vie=av.vie-1;
                        }else if (obj instanceof Avion){
                            av.vie=0;
                        }
                    }
                }
            }

            for(Bombe b:av.listebombe){
                if(b!=null){
                    System.out.println("Bombe, var1 = "+var1+"varBombe = "+varBombe+"vBackground = "+vBackground);
                    b.avancer(this.var1,varBombe,vBackground); //Ajout du temps dans la méthode tombe/avancer
                    if(b.estsorti()){
                        av.listebombesuppr.add(b);

                        }

                    }

                }
                for (Missiles m: av.missiles) {
                    if (m != null){
                        m.avancer((double) vBackground);
                    }
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
        
        if (avions.get(0).vie==0){
			scoreFinal=var1/100;
		}
        
        texteScore="Score: "+Integer.toString(var1/100);
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
        }
        if(code == KeyEvent.VK_W) {
            avions.get(0).tirerBalles();
            varBalle=var1;
            }
            
        if(code == KeyEvent.VK_UP) {
            avions.get(0).accelerer();
        }
    }


    public void keyReleased(KeyEvent e){}
    
    public void keyTyped(KeyEvent e){}
    
    public static void main (String[] args) {     
        Fenetre fenetre=new Fenetre();
    }
    
}







