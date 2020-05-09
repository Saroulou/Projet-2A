import java.awt.*;
import java.util.ArrayList;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;


public class AvionBot extends Avion{

    //image explosion de l'avion quand collision
    Toolkit T = Toolkit.getDefaultToolkit();
    Image im = T.getImage("explosion-gif-001.gif");
    
    protected double theta=30;//pas d'angle (pr�cision mini)
    protected final int nbMaxMissilesBOT = 5;
    public Avion avion;


    public AvionBot(ArrayList<Avion> avions,double x, double y, double vr, double vtheta, int h, int l,Fenetre fenetre, String nom) {
        super(x,y, vr, vtheta, h, l, fenetre, 30, nom);
        this.x=x;
        this.y=y;
        this.avion = avions.get(0);
        missiles=new ArrayList<Missiles>();
        balles=new ArrayList<Mitrailleuse>();
        listemissilesuppr=new ArrayList<>();
        listeballesuppr=new ArrayList<>();
    }
        
    public AvionBot(ArrayList<Avion> avions, int h, int l,Fenetre fenetre, String nom){
        this(avions,600,600,5,0,h,l,fenetre, nom); //initialiser coord. initiales au hasard
    }

    public String toString(){
        return "Avion bot " + this.nom;
    }
        

    public void dessine(Graphics g){
       /* int r = 30;
        g.setColor(new Color (210,19,90));
        g.fillPolygon(
            new int[]{(int)(x+r*Math.cos(Math.toRadians(vtheta))),(int)(x+r*Math.cos(Math.toRadians(vtheta+120))),(int)(x+r*Math.cos(Math.toRadians(vtheta-120)))},
            new int[]{(int)(y+r*Math.sin(Math.toRadians(vtheta))),(int)(y+r*Math.sin(Math.toRadians(vtheta+120))),(int)(y+r*Math.sin(Math.toRadians(vtheta-120)))},
            3);*/
            
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
    

    public void exploser(Graphics g) {
        g.drawImage(im,(int)(this.x-35),(int)(this.y-50),null);//this.x-x_im/2...
     }
    

    // public void tirerMissiles() {
        
    //  //if(direction avionBot est proche à un angle x de avionjoueur : tirer) 
        
    //     if(nbMissiles<nbMaxMissilesBOT) {
    //         Missiles missile = new Missiles(x,y,2.5*vr,vtheta,h,l,fenetre);
    //         missiles.add(missile);
    //         nbMissiles++;
    //     }
    // }
    
    // public void tirerBalles() {
        
    //     if(nbBalles<nbMaxBalles) {
    //         Mitrailleuse balle = new Mitrailleuse(x,y,2*vr,vtheta,h,l,fenetre);
    //         balles.add(balle);
    //         nbBalles++;
    //     }
    // }
    

    }
