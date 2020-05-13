import java.awt.*;
import java.util.ArrayList;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import java.util.HashSet;


public class AvionBot extends Avion{
    public Avion avion; // avion du joueur que le bot prend piur cible

    public AvionBot(HashSet<Objet> objets,Avion avion, double x, double y, double vr, double vtheta, int h, int l,Fenetre fenetre, String nom) {
        super(x,y, vr, vtheta, h, l, fenetre, 30, nom, 1000, 50);
        this.avion = avion;
    }
        
    public AvionBot(HashSet<Objet> objets, Avion avion, int h, int l,Fenetre fenetre, String nom){
        this(objets,avion,(int)(Math.random()*(fenetre.getWidth()-200))+200,(int)(Math.random()*(fenetre.getWidth()-300))+200,5,0,h,l,fenetre, nom); //à faire : initialiser coord. initiales au hasard
    }

    public String toString(){
        return "Avion bot " + this.nom;
    }

    public void dessine(Graphics g){
        BufferedImage avionBC = LoadImage("avionBC.png");
        AffineTransform at = AffineTransform.getTranslateInstance(x-avionBC.getWidth()/2,y-avionBC.getHeight()/2);
        
        at.rotate(Math.toRadians(vtheta),avionBC.getWidth()/2, avionBC.getHeight()/2);
        at.scale(0.75, 0.75);
        
        Graphics2D g2d = (Graphics2D) g;
        g2d.drawImage(avionBC,at,null);
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
    
   //@Fonction asservissement 
    public void tourner(){
        double a = avion.getX()-this.x;
        double b = avion.getY()-this.y;
        double dir = Math.toDegrees(Math.atan2(b,a));
        vtheta = dir;
    }
}
