import javax.swing.*;
import java.awt.event.*;
import java.awt.*;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JTextField;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import javax.imageio.ImageIO;
import java.io.IOException;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.io.File;

public class Avion extends Objet implements ActionListener, KeyListener{

    // public ArrayList<Bombe> listebombe;
    // public ArrayList<Missiles> missiles;
    // public ArrayList <Bombe> listebombesuppr;
    // public ArrayList <Missiles> listemissilesuppr;
    // public ArrayList <Mitrailleuse> listeballesuppr;
    
    // public ArrayList<Mitrailleuse> balles;
    protected int nbMissiles = 0;
    protected int nbBalles = 0;
    protected final int nbMaxMissiles = 7;
    protected final int nbMaxBalles = 2000;
    public Fenetre fenetre;
    protected int tempsDepartMissile = 0 ;
    public String imAvion=" ";
        
    //image explosion de l'avion quand collision
    Toolkit T = Toolkit.getDefaultToolkit();
    Image im = T.getImage("explosion-gif-001.gif");
 

    public Avion (double x, double y, double vr, double vtheta, int h, int l,Fenetre fenetre, double r, String nom, double degats, double vie) {
        super(x, y, vr, vtheta, h, l, r, nom, degats, vie);
        
        this.fenetre = fenetre;
        // listebombe=new ArrayList<Bombe>();          // ajouter des commentaires pour expliquer à quoi servent les différentes listes SVP
        // listebombesuppr=new ArrayList<>();
        // listemissilesuppr=new ArrayList<>();
        // listeballesuppr=new ArrayList<>();
        // missiles=new ArrayList<Missiles>();
        // balles=new ArrayList<Mitrailleuse>();

    }

    /*public Avion(int h, int l, Fenetre fenetre, String nom){
        this(100,100,10,0,h,l,fenetre, 30, nom);
    }*/
    public Avion(int h,int l,Fenetre fenetre, String nom, String imAvion){
        this(100,100,10,0,h,l,fenetre, 30, nom, 1000, 100);
        this.imAvion=imAvion;
        
    }

    public String toString(){
        return "Avion " + this.nom;
    }

    public void dessine(Graphics g){
        /*int r = 30;
        g.setColor(new Color (243,149,70));
        g.fillPolygon(
            new int[]{(int)(x+r*Math.cos(Math.toRadians(vtheta))),(int)(x+r*Math.cos(Math.toRadians(vtheta+120))),(int)(x+r*Math.cos(Math.toRadians(vtheta-120)))},
            new int[]{(int)(y+r*Math.sin(Math.toRadians(vtheta))),(int)(y+r*Math.sin(Math.toRadians(vtheta+120))),(int)(y+r*Math.sin(Math.toRadians(vtheta-120)))},
            3);*/
            
         
        BufferedImage avionJC = LoadImage(imAvion+".png");//pour changer l'image de l'avion
        AffineTransform at = AffineTransform.getTranslateInstance(x-avionJC.getWidth()/2,y-avionJC.getHeight()/2);
        
        
        at.rotate(Math.toRadians(vtheta),avionJC.getWidth()/2, avionJC.getHeight()/2);
        at.scale(0.75, 0.75); //modification taille
        
        Graphics2D g2d = (Graphics2D) g;
        g2d.drawImage(avionJC,at,null);
        
        g.setColor(new Color (58, 137, 35));
        if(vie>=0){
        g.fill3DRect(600,630,(int)this.vie*this.fenetre.getWidth()/700,this.fenetre.getHeight()/15,false);
        }
    }
    
    BufferedImage LoadImage(String NomFichier) {
        BufferedImage img = null;
         try {
                img = ImageIO.read(new File(NomFichier));
            }
            catch (IOException e){
                e.printStackTrace();
            }
         return img;
    }

    public void avancer (double vBackground){
        x = (x + l + 1.2*vr * Math.cos(Math.toRadians(vtheta)) - vBackground) % l; // si l'avion sort d'un côté, il rentre de l'autre
        y = y + 1.2*vr * Math.sin(Math.toRadians(vtheta));
        if (y < 0) {
            vtheta = 90; // vtheta += 180;
        }
    }

    public void tourner(double theta){
        vtheta+=theta;

    }

    public Bombe tirerBombe(){
        return new Bombe(this.x+50, this.y+100,h,l,this, fenetre, "");
    }

   
    public Missiles tirerMissiles() {
        if(nbMissiles<nbMaxMissiles) {
            nbMissiles++;
            return new Missiles(x + 100 * Math.cos(Math.toRadians(vtheta)),
                                y + 100 * Math.sin(Math.toRadians(vtheta)),
                                2.5 * vr, vtheta, h, l, fenetre, Integer.toString(nbMissiles)
                               );
        }
        return null;
    }
    
    public Mitrailleuse tirerBalles() {
        if(nbBalles<nbMaxBalles) {
            nbBalles++;
            return new Mitrailleuse(x + 100 * Math.cos(Math.toRadians(vtheta)),
                                    y + 100 * Math.sin(Math.toRadians(vtheta)),
                                    2 * vr, vtheta, h, l, fenetre, Integer.toString(nbMissiles)
                                   );
        }
        return null;
    }
    
    public void accelerer() {
        x=(x+this.l+1.5*vr*Math.cos(Math.toRadians(this.vtheta)))%this.l;
        y=(y+this.h+1.5*vr*Math.sin(Math.toRadians(this.vtheta)))%this.h;
    }

    public void keyTyped(KeyEvent e) {}
    public void keyPressed(KeyEvent e) {}
    public void keyReleased(KeyEvent e) {}
    public void actionPerformed(ActionEvent e) {}

}


