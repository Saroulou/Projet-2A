import javax.swing.*;
import java.awt.event.*; 
import java.awt.*;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JTextField;
import java.util.ArrayList;
import javax.imageio.ImageIO;
import java.io.IOException;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.io.File;
 

public class Bombe extends Objet {
        // public double x;
        // public double y;
        // public int h;
        // public int l;
        // public final double masse=5;
        // public final double vitesse=10;
        public final double acceleration=2;
        // public double r;
        // public Avion avion;
        // private final double vrInit;
        // private final double vthetaInit;
        // private final double xInit;
        // private final double yInit;
        // private int degatbombe=3;
    
    public Bombe (double x, double y, double vr, double vtheta, int h, int l, Fenetre fenetre, String nom, int t){
        super(x,y, vr, vtheta, h, l, 20, nom, 3, 0.1);
        // this.avion = avion;
        // this.vrInit = avion.vr;
        // this.vthetaInit = avion.vtheta;
        // this.xInit = avion.x;
        // this.yInit = avion.y;
        this.tLancement = t;
    }
    
    public void dessine(Graphics g){
        // g.setColor(new Color (45,36,105));
        // g.fillOval((int)(x-r),(int)(y-r),(int)(2*r),(int)(2*r));
        
        BufferedImage ImBombe = LoadImage("Bombe.png");
        AffineTransform at = AffineTransform.getTranslateInstance(x-ImBombe.getWidth()/2,y-ImBombe.getHeight()/2);
        
        
        //at.rotate(Math.toRadians(vtheta),ImBombe.getWidth()/2, ImBombe.getHeight()/2);
        at.scale(0.15, 0.15); //modification taille
        
        Graphics2D g2d = (Graphics2D) g;
        g2d.drawImage(ImBombe,at,null);
        
    }

    private BufferedImage LoadImage(String NomFichier) {
        BufferedImage img = null;
        try {
            img = ImageIO.read(new File(NomFichier));
        }
        catch (IOException e){
            e.printStackTrace();
        }
        return img;
    }
    
    public void avancer(double vBackground){
        System.out.println(x + " "+ y);
        vy += acceleration;
        vToPolaire();
        x += vx;
        y += vy;
    }
    
    // public boolean estsorti (){
    //     return y>h;
    // }
    
}

