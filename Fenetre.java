import javax.swing.*;
import java.awt.event.*;
import java.awt.*;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JTextField;
import java.util.ArrayList;
import java.util.HashSet;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.*;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.JOptionPane;



public class Fenetre extends JFrame implements ActionListener, KeyListener{
    
// ATTRIBUTS

    // private ArrayList<Avion> avions;
    private Avion avionJ; // avion du joueur
    private HashSet<Objet> objets;
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
        setLocation(0,100);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);
        
        timer=new Timer(var, this);
        timer.start();
        
        objets = new HashSet<Objet>();
        avionJ = new Avion(getHeight(),getWidth(),this, "Joueur","avionJC");
        objets.add(avionJ);
        for (int i = 0; i < NB_BOTS; i++) {
            objets.add(new AvionBot(objets,avionJ,getHeight(),getWidth(),this, Integer.toString(i)));
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
        
        objets = new HashSet<Objet>();
        avionJ = new Avion(getHeight(),getWidth(),this, "Joueur","avionJC");
        objets.add(avionJ);
        for (int i = 0; i < NB_BOTS; i++) {
            objets.add(new AvionBot(objets,avionJ,getHeight(),getWidth(),this, Integer.toString(i)));
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
        
        objets = new HashSet<Objet>();
        avionJ = new Avion(getHeight(),getWidth(),this, "Joueur","avionJC");
        objets.add(avionJ);
        for (int i = 0; i < NB_BOTS; i++) {
            objets.add(new AvionBot(objets,avionJ,getHeight(),getWidth(),this, Integer.toString(i)));
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

        
        for(Objet obj: objets){
            obj.dessine(g);
 
            if(obj.y > getHeight()) {
                obj.exploser(g);
            }
            
            if(obj instanceof Missiles && var1-varMissile>=1500) {
                obj.exploser(g);
            }
            obj.dessine(g);
        }
    }


    public void actionPerformed(ActionEvent e){
        //var1++;
          var1 = var1 + var ;
          // System.out.println("var 1= "+var1);
          // System.out.println("var = "+var);
        int r = (int) (Math.random()*100+1); //génération de nombres aléatoires pour les avions bot
        int p = (int) (Math.random()*50+1);

        HashSet<Objet> nvObjets = new HashSet<Objet>();
        for(Objet obj: objets){
            obj.avancer((double) vBackground);
            obj.tourner();
            
            if (obj instanceof AvionBot){//vérifier si l'objet est un bot
                AvionBot av = (AvionBot) obj;
                
                if(r%7==0 && r%4==0) { //tirs à tps aléatoires
                    nvObjets.add(av.tirerMissiles());     
                }
                
                if(p%7==0) { //tirs à tps aléatoires
                    nvObjets.add(av.tirerBalles());
                }
            }

            for (Objet obj2: objets){ // vérifie avec s'il y a une collison avec un autre avion/objet en vérifiant pour chaque autre objet
                System.out.println(obj2);
                if (obj != obj2) {
                    if (obj.collison(obj2)) {
                        //System.out.println("Collision entre " + obj.toString() + " et " + obj2.toString());
                        obj.vie -= obj2.degats;
                    }
                }
            }

            obj.avancer((double) vBackground); // l'objet avance (méthode avancer() commune à tous les objets)
        }
        objets.addAll(nvObjets);
        
        if (avionJ.vie==0){
            scoreFinal=var1/100; //score final
            timer.stop(); //arreter le timer (figer le jeu)
            JOptionPane jop = new JOptionPane();        
      int option = jop.showConfirmDialog(null, 
        "GAME OVER! Score : "+Integer.toString(scoreFinal)+"\n"+" Rejouer ?", 
        "Game over", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE); //création boite de dialogue
        if (option==JOptionPane.YES_OPTION){
            this.setVisible(false); 
        }else if(option==JOptionPane.NO_OPTION){
            System.exit(0); //arreter le programme
        }
    }
        
        texteScore="Score: "+Integer.toString(var1/100);
        System.out.println(avionJ.vie);
        xBackground -= vBackground; // position actualisée avec la vitesse de l'arrière plan
        repaint(); // appel a la methode paint 
         
}

    public void keyPressed(KeyEvent e){ 
        int code = e.getKeyCode();

        if (code==KeyEvent.VK_LEFT) {
            avionJ.tourner(-10);
        } else if (code == KeyEvent.VK_RIGHT) {
            avionJ.tourner(10);
        } else if (code==KeyEvent.VK_DOWN) {
            avionJ.tirerBombe();
            varBombe=var1;
        } if (code == KeyEvent.VK_SPACE) {
            objets.add(avionJ.tirerMissiles());
            varMissile=var1;// Temps de lancement initial quand on appuie sur la touche;
        }
        if(code == KeyEvent.VK_W) {
            objets.add(avionJ.tirerBalles());
            varBalle=var1;
            }
            
        if(code == KeyEvent.VK_UP) {
            avionJ.accelerer();
        }
    }


    public void keyReleased(KeyEvent e){}
    
    public void keyTyped(KeyEvent e){}
    
    public static void main (String[] args) {     
        Fenetre fenetre=new Fenetre();
    }
    
}







