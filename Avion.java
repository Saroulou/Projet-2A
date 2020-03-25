import javax.swing.*;
import java.awt.event.*; 
import java.awt.*;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JTextField;
import java.util.ArrayList; 

public class Avion {
	private double x; //position
    private double y;
    private double vr;//vitesse
    private double vtheta;//degres
    private int h; //hauteur fenetre 
    private int l;
    public ArrayList <Bombe> listebombe;
	
    public Avion (double x, double y, double vr, double vtheta, int h, int l) {
		this.x=x;
        this.y=y;
        this.vr=vr;
        this.vtheta=vtheta;
        this.h = h; 
        this.l = l;
        listebombe=new ArrayList<Bombe>();
        //bombe.add(new Bombe(this.x, this.y,getHeight(),getWidth()));
	}
    
    public Avion(int h, int l){
        this(100,100,10,0,h,l);
    }
    
    public void dessine(Graphics g){
        int r = 30;
        g.setColor(new Color (243,149,70));
        g.fillPolygon(
            new int[]{(int)(x+r*Math.cos(Math.toRadians(vtheta))),(int)(x+r*Math.cos(Math.toRadians(vtheta+120))),(int)(x+r*Math.cos(Math.toRadians(vtheta-120)))},
            new int[]{(int)(y+r*Math.sin(Math.toRadians(vtheta))),(int)(y+r*Math.sin(Math.toRadians(vtheta+120))),(int)(y+r*Math.sin(Math.toRadians(vtheta-120)))},
            3);
        
    }
    
    public void avancer (){
        x=(x+l+vr*Math.cos(Math.toRadians(vtheta)))%l;
        y=(y+h+vr*Math.sin(Math.toRadians(vtheta)))%h;
        
    }
    
    public void tourner(double theta){
        vtheta+=theta;
        
    }
    
    public void tirerBombe(){
        Bombe bombe=new Bombe(this.x, this.y,h,l);
        listebombe.add(bombe);
    }

}

