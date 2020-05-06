import javax.swing.*;
import java.awt.event.*; 
import java.awt.*;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JTextField;
import java.util.ArrayList; 

public class Bombe {
		public double x;
        public double y;
        public double h;
        public double l;
        public final double masse=5;
        public final double vitesse=10;
        public final double acceleration=9.8;
        public double r;
        public Avion avion;
		private final double vrInit;
		private final double vthetaInit;
    
    public Bombe (double x, double y,double h, double l, Avion avion){
        this.x=x;
        this.y=y;
        this.h=h;
        this.l=l;
        this.r=20;
        this.avion=avion;
		this.vrInit=avion.vr;
		this.vthetaInit=avion.vtheta;
    }
    
    public void dessine(Graphics g){
        g.setColor(new Color (45,36,105));
        g.fillOval((int)(x-r),(int)(y-r),(int)(2*r),(int)(2*r));
        
    } 
    
    public void tombe(){
		//x=vrInit*Math.cos(Math.toRadians(vthetaInit))*Math.sqrt(2*(double)avion.h/acceleration);
		x+=vrInit*Math.cos(Math.toRadians(vthetaInit));
        y+=acceleration/2+vrInit*Math.abs(Math.sin(Math.toRadians(vthetaInit)));
		//y=acceleration/2*Math.pow(x/Math.sin(Math.toRadians(vthetaInit)),2)-(double)avion.h;
    }
    
    public boolean estsorti (){
        return y>h;
    }
    
}

