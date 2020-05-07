import java.awt.Color;
import java.awt.Graphics;
import java.awt.*;
//import java.util.ArrayList;

public class Missiles extends Avion { 
	
	Toolkit T = Toolkit.getDefaultToolkit();
	Image im = T.getImage("explosion.gif");
	//private int tempsDepartMissile ;
	
	public Missiles (double x, double y,double vr, double vtheta, int h, int l,Fenetre fenetre) {
		super(x,y,vr,vtheta,h,l,fenetre);

	}
	
	
	public void dessine(Graphics g){

		 int r = 20;
	        g.setColor(Color.black);
	        g.fillPolygon(
	            new int[]{(int)(x+r*Math.cos(Math.toRadians(vtheta))),(int)(x+r*Math.cos(Math.toRadians(vtheta+120))),(int)(x+r*Math.cos(Math.toRadians(vtheta-120)))},
	            new int[]{(int)(y+r*Math.sin(Math.toRadians(vtheta))),(int)(y+r*Math.sin(Math.toRadians(vtheta+120))),(int)(y+r*Math.sin(Math.toRadians(vtheta-120)))},
	            3);
    } 

    public void avancer (double vBackground){
        x=(x+l+vr*Math.cos(Math.toRadians(vtheta))-vBackground)%l; // si l'avion sort d'un côté, il rentre de l'autre
        y=(y+h+vr*Math.sin(Math.toRadians(vtheta)))%h;

    }

    public void avancer (){
        avancer(0);
    }
	 
	 public void exploser(Graphics g) {
		  
		g.drawImage(im,(int)(this.x-35),(int)(this.y-50),null);//this.x-x_im/2...
		
		//suppresion missile ensuite
	 }
	 
	
}
	
	
