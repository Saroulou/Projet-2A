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
    private Fenetre fenetre;
    protected int tempsDepartMissile = 0 ;
    private String imAvion=" ";
    private int nbBalles;
    private int nbMissiles;

    public Avion (double x, double y, double vr, double vtheta, int h, int l,Fenetre fenetre, double r, String nom, double degats, double vie) {
        super(x, y, vr, vtheta, h, l, r, nom, degats, vie);
        
        this.fenetre = fenetre;
    }

    public Avion(int h,int l,Fenetre fenetre, String nom, String imAvion){
        this(100,100,10,0,h,l,fenetre, 30, nom, 1000, 100);
        this.imAvion=imAvion;
        
    }

    public String toString(){
        return "Avion " + this.nom;
    }

    public void dessine(Graphics g){
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
        x = (x + l + vr * Math.cos(Math.toRadians(vtheta)) - vBackground) % l; // si l'avion sort d'un côté, il rentre de l'autre
        y = y + vr * Math.sin(Math.toRadians(vtheta));
        if (y < 0) {
            vtheta = 90; // vtheta += 180;
        }
    }

    public void tourner(double theta){
        vtheta+=theta;

    }

    public Bombe tirerBombe(int t){
        return new Bombe(this.x, this.y+100, vr, vtheta, h, l, fenetre, "", t);
    }

    public Missiles tirerMissiles(int t) {
        nbMissiles++;
        return new Missiles(x + 100 * Math.cos(Math.toRadians(vtheta)),
                            y + 100 * Math.sin(Math.toRadians(vtheta)),
                            2.5 * vr, vtheta, h, l, fenetre, Integer.toString(nbMissiles), t
                           );
    }
    
    public Mitrailleuse tirerBalles(int t) {
        nbBalles++;
        return new Mitrailleuse(x + 100 * Math.cos(Math.toRadians(vtheta)),
                                y + 100 * Math.sin(Math.toRadians(vtheta)),
                                2 * vr, vtheta, h, l, fenetre, Integer.toString(nbBalles), t
                               );
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
