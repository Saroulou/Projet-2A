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
    private HashSet<int[]> explosions; // 
    private final int NB_BOTS = 2; // nombre d'avions bots
    private Timer timer;
    private final int var=100;
    private ImageIcon sBackground; // permet de stocker l'image du fond d'écran
    private Image iBackground; 
    private final int largeurBackground = 1000; 
    public int xBackground; // permet de determiner l'abcisse de l'image 
    public final int vBackground =5; // vitesse de déplacement de l'arrière-plan
    private int var1=0;// compteur de temps
    //JLabel explosions = new JLabel(); //contient les images des explosions des missiles
    private int scoreFinal;//donne le score final à stocker
    private String texteScore="0";
    private String avionChoisi=" ";

    Toolkit T = Toolkit.getDefaultToolkit();
    Image im = T.getImage("explosion.gif");

    // CONSTRUCTEURS

    public Fenetre(String nom, int width, int height){

        super(nom);
        this.sBackground = new ImageIcon(getClass().getResource("Background.jpg"));
        this.iBackground = this.sBackground.getImage();
        this.xBackground=0;
       
        setSize(width, height);
        // setLocation(200,200);
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

        explosions = new HashSet<int[]>();

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

        explosions = new HashSet<int[]>();

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
        avionJ = new Avion(getHeight(),getWidth(),this, "Joueur",avionChoisi);
        objets.add(avionJ);
        for (int i = 0; i < NB_BOTS; i++) {
            objets.add(new AvionBot(objets,avionJ,getHeight(),getWidth(),this, Integer.toString(i)));
        }

        explosions = new HashSet<int[]>();

        addKeyListener(this);

        setVisible(true);
    }

    // METHODES

    // permet de dessiner l'écran et de rajouter les images "à la suite".
    private void movingBackground(Graphics g){
        if(this.xBackground == -this.largeurBackground){
            this.xBackground = 0;
            }
        for (int i = 0; i < getWidth()/this.largeurBackground + 2; i++) {
            g.drawImage(this.iBackground, this.xBackground + this.largeurBackground * i, 0, null);
        }
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
            if (obj != null) obj.dessine(g);
        }

        HashSet<int[]> exploFini = new HashSet<int[]>();
        for (int[] coord:explosions) {
            g.drawImage(im, coord[0] - 35, coord[1] - 50, null);
            coord[2] --;
            if (coord[2] <= 0) {
                exploFini.add(coord);
            }
        }
        explosions.removeAll(exploFini);
    }


    public void actionPerformed(ActionEvent e){
        var1 += var ;
        int r = (int) (Math.random()*100+1); //génération de nombres aléatoires pour les avions bot
        int p = (int) (Math.random()*50+1);

        HashSet<Objet> nvObjets = new HashSet<Objet>(); // nouveau objets créés
        HashSet<Objet> ancObjets = new HashSet<Objet>(); // anciens objets détruits
        for(Objet obj: objets){
            if (obj != null) {
                obj.avancer((double) vBackground);
                obj.tourner();
                
                if (obj instanceof AvionBot){//vérifier si l'objet est un bot
                    AvionBot av = (AvionBot) obj;
                    
                    if(r%7==0 && r%4==0) { //tirs à tps aléatoires
                        nvObjets.add(av.tirerMissiles(var1));     
                    }
                    
                    if(p%7==0) { //tirs à tps aléatoires
                        nvObjets.add(av.tirerBalles(var1));
                    }
                }

                for (Objet obj2: objets){ // vérifie avec s'il y a une collison avec un autre avion/objet en vérifiant pour chaque autre objet
                    if (obj2 != null && obj != obj2 && !(obj instanceof AvionBot && obj instanceof AvionBot)) {
                        if (obj.collison(obj2)) {
                            obj.prendreDegats(obj2.getDegats());
                            obj2.prendreDegats(obj.getDegats());
                        }
                    }
                }

                obj.avancer((double) vBackground); // l'objet avance (méthode avancer() commune à tous les objets)

                if (obj.getVie() <= 0 || obj.getY() >= getHeight() 
                    || ((obj instanceof Missiles || obj instanceof Mitrailleuse) && var1-obj.getT()>=1500)) {
                    explosions.add(new int[]{(int)obj.getX(), (int)obj.getY(), 3});
                    if (obj != avionJ) ancObjets.add(obj); // on met l'objet dans la liste des objets à supprimer seulement s'il ne s'agit pas de l'vion du joueur
                }
            }
        }
        objets.addAll(nvObjets);
        objets.removeAll(ancObjets);

        if (avionJ.getVie()<=0){
            Audio.playSound("/son/gameover.wav"); 
            scoreFinal=var1/100; //score final
            timer.stop(); //arreter le timer (figer le jeu)
            JOptionPane jop = new JOptionPane();        
            int option = jop.showConfirmDialog(null, 
            "GAME OVER! Score : "+Integer.toString(scoreFinal)+"\n"+" Rejouer ?", 
            "Game over", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE); //création boite de dialogue
            if (option==JOptionPane.YES_OPTION){
                Audio.playSound("/son/click.wav");
                this.dispose();
            }else if(option==JOptionPane.NO_OPTION){
                Audio.playSound("/son/click.wav");
                System.exit(0); //arreter le programme
            }
        }

        if (aucunBot(objets)){ // s'il n'y a plus de bot, le joueur a gagné
            scoreFinal=var1/100; //score final
            timer.stop(); //arreter le timer (figer le jeu)
            JOptionPane jop = new JOptionPane();        
            int option = jop.showConfirmDialog(null, 
            "WIN! Score : "+Integer.toString(scoreFinal)+"\n"+" Rejouer ?", 
            "Gagné!", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE); //création boite de dialogue
            if (option==JOptionPane.YES_OPTION){
                this.dispose(); 
            }else if(option==JOptionPane.NO_OPTION){
                System.exit(0); //arreter le programme
            }
        }
        
        texteScore="Score: "+Integer.toString(var1/100);
        // System.out.println(avionJ.getVie());
        xBackground -= vBackground; // position actualisée avec la vitesse de l'arrière plan

        repaint(); // appel a la methode paint
         
    }

    private boolean aucunBot(HashSet<Objet> objets) {
        for (Objet obj : objets) {
            if (obj instanceof AvionBot) return false;
        }
        return true;
    }

    public void keyPressed(KeyEvent e){ 
        int code = e.getKeyCode();

        if (code==KeyEvent.VK_LEFT) {
            avionJ.tourner(-10);
        } else if (code == KeyEvent.VK_RIGHT) {
            avionJ.tourner(10);
        } else if (code==KeyEvent.VK_DOWN) {
        Audio.playSound("/son/bombe.wav");
            objets.add(avionJ.tirerBombe(var1));
        } else if (code == KeyEvent.VK_SPACE) {
            Audio.playSound("/son/missile.wav");
            objets.add(avionJ.tirerMissiles(var1));
        } else if (code == KeyEvent.VK_W) {
            Audio.playSound("/son/balles.wav");
            objets.add(avionJ.tirerBalles(var1));
        } else if(code == KeyEvent.VK_UP) {
            Audio.playSound("/son/boost.wav");
            avionJ.accelerer();
        }
    }

    public void keyReleased(KeyEvent e){}
    public void keyTyped(KeyEvent e){}
    
    public static void main (String[] args) {     
        Fenetre fenetre = new Fenetre();
    }
    
}
