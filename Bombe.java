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
		public double x;
        public double y;
        public int h;
        public int l;
        public final double masse=5;
        public final double vitesse=10;
        public final double acceleration=9.8;
        public double r;
        public Avion avion;
		private final double vrInit;
		private final double vthetaInit;
		private final double xInit;
		private final double yInit;
		private int degatbombe=3;
    
    public Bombe (double x, double y,int h, int l, Avion avion, Fenetre fenetre, String nom){
        super(x,y, 0.0, 0.0, h, l, 20, nom);
        this.avion=avion;
		this.vrInit=avion.vr;
		this.vthetaInit=avion.vtheta;
		this.xInit=avion.x;
		this.yInit=avion.y;
		
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
    
    public void avancer(int var1, int varBombe, double vBackground){
		int vartemps=(var1-varBombe);
		//x=vrInit*Math.cos(Math.toRadians(vthetaInit))*Math.sqrt(2*(double)avion.h/acceleration);
		x+=vrInit*Math.cos(Math.toRadians(vthetaInit)) - vBackground; // la vitesse reste constante
		// System.out.println("x =" +x);
		y+=acceleration/2*Math.pow(vartemps*0.001,2)+vrInit*Math.abs(Math.sin(Math.toRadians(vthetaInit)))*vartemps*0.001;
		// System.out.println("y = "+y);
		//y=acceleration/2*Math.pow(x/Math.sin(Math.toRadians(vthetaInit)),2)-(double)avion.h
		//y+=acceleration/(2*Math.pow(vrInit,2))*Math.pow(x,2)*(1+Math.pow((Math.tan(Math.toRadians(vthetaInit))),2))-x*Math.tan(Math.toRadians(vthetaInit))-(double)this.avion.h;
		
    }
    
    public boolean estsorti (){
        return y>h;
    }
    
}

