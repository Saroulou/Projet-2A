import javax.swing.*;
import java.awt.*;
 

public class Avion {
	private double x; //position
    private double y;
    private double vr;//vitesse
    private double vtheta;//degres
    private int h; //hauteur fenetre 
    private int l;
	
    public Avion (double x, double y, double vr, double vtheta, int h, int l) {
		this.x=x;
        this.y=y;
        this.vr=vr;
        this.vtheta=vtheta;
        this.h = h;
        this.l = l;
	}
    
    public Avion(int h, int l){
        this(100,100,10,0,h,l);
    }
    
    public void dessine(Graphics g){
        int r = 50;
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
    
    
    
}

