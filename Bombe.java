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
        public final double acceleration=2; // gravité
    
    public Bombe (double x, double y, double vr, double vtheta, int h, int l, Fenetre fenetre, String nom, int t){
        super(x,y, vr, vtheta, h, l, 20, nom, 3, 0.1);
        this.tLancement = t;
    }
    
    public void dessine(Graphics g){
        BufferedImage ImBombe = LoadImage("Bombe.png");
        AffineTransform at = AffineTransform.getTranslateInstance(x-ImBombe.getWidth()/2,y-ImBombe.getHeight()/2);

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
        vy += acceleration;
        vToPolaire();
        x += vx;
        y += vy;
    }
}

