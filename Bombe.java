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
		private final double xInit;
		private final double yInit;
    
    public Bombe (double x, double y,double h, double l, Avion avion){
        this.x=x;
        this.y=y;
        this.h=h;
        this.l=l;
        this.r=20;
        this.avion=avion;
		this.vrInit=avion.vr;
		this.vthetaInit=avion.vtheta;
		this.xInit=avion.x;
		this.yInit=avion.y;
		
    }
    
    public void dessine(Graphics g){
        g.setColor(new Color (45,36,105));
        g.fillOval((int)(x-r),(int)(y-r),(int)(2*r),(int)(2*r));
        
    }
    
    public void tombe(int var1, int varBombe){
		int vartemps=var1-varBombe;
		//x=vrInit*Math.cos(Math.toRadians(vthetaInit))*Math.sqrt(2*(double)avion.h/acceleration);
		x+=vrInit*Math.cos(Math.toRadians(vthetaInit))*(vartemps)*0.001;
		System.out.println("x =" +x);
		y+=acceleration/2*Math.pow(vartemps*0.001,2)+vrInit*Math.abs(Math.sin(Math.toRadians(vthetaInit)))*vartemps*0.001;
		System.out.println("y = "+y);
		//y=acceleration/2*Math.pow(x/Math.sin(Math.toRadians(vthetaInit)),2)-(double)avion.h;
		//y+=acceleration/(2*Math.pow(vrInit,2))*Math.pow(x,2)*(1+Math.pow((Math.tan(Math.toRadians(vthetaInit))),2))-x*Math.tan(Math.toRadians(vthetaInit))-(double)this.avion.h;
		
    }
    
    public boolean estsorti (){
        return y>h;
    }
    
}

