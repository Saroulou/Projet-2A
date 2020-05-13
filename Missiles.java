import java.awt.Color;
import java.awt.Graphics;
import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

//import java.util.ArrayList;

public class Missiles extends Avion { // héritage à changer
    
    Toolkit T = Toolkit.getDefaultToolkit();
    Image im = T.getImage("explosion.gif");
    Image missile = T.getImage("missile.png");
    //private int tempsDepartMissile ;
        // public int degatsM=2;
    
    public Missiles (double x, double y,double vr, double vtheta, int h, int l,Fenetre fenetre, double rayon, String nom, double degats, double vie, int t) {
        super(x,y,vr,vtheta,h,l,fenetre, 10, nom, degats, vie);
        this.tLancement = t;
    }

    public Missiles (double x, double y,double vr, double vtheta, int h, int l,Fenetre fenetre, String nom, int t) {
        this(x,y,vr,vtheta,h,l,fenetre, 10, nom, 2, 0.1, t);
    }

    public String toString(){
        return "Missile " + this.nom;
    }
    
    public void dessine(Graphics g){
        BufferedImage missile = LoadImage("missile.png");
        AffineTransform at = AffineTransform.getTranslateInstance(x-missile.getWidth()/2,y-missile.getHeight()/2);
        
        at.rotate(Math.toRadians(vtheta),missile.getWidth()/2, missile.getHeight()/2);
        at.scale(1.3, 1.3);
        Graphics2D g2d = (Graphics2D) g;
        g2d.drawImage(missile,at,null);
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
}
    
    
