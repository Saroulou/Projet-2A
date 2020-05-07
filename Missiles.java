import java.awt.Color;
import java.awt.Graphics;
import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

//import java.util.ArrayList;

public class Missiles extends Avion { 
	
	Toolkit T = Toolkit.getDefaultToolkit();
	Image im = T.getImage("explosion.gif");
	Image missile = T.getImage("missile.png");
	//private int tempsDepartMissile ;
		public int degatsM;
	
	public Missiles (double x, double y,double vr, double vtheta, int h, int l,Fenetre fenetre) {
		super(x,y,vr,vtheta,h,l,fenetre);

	}
	
	
	public void dessine(Graphics g){

		 /*int r = 20;
	        g.setColor(Color.black);
	        g.fillPolygon(
	            new int[]{(int)(x+r*Math.cos(Math.toRadians(vtheta))),(int)(x+r*Math.cos(Math.toRadians(vtheta+120))),(int)(x+r*Math.cos(Math.toRadians(vtheta-120)))},
	            new int[]{(int)(y+r*Math.sin(Math.toRadians(vtheta))),(int)(y+r*Math.sin(Math.toRadians(vtheta+120))),(int)(y+r*Math.sin(Math.toRadians(vtheta-120)))},
	            3);*/
	            
	             BufferedImage missile = LoadImage("missile.png");
	        AffineTransform at = AffineTransform.getTranslateInstance(x-missile.getWidth()/2,y-missile.getHeight()/2);
	        
	        at.rotate(Math.toRadians(vtheta),missile.getWidth()/2, missile.getHeight()/2);
	        at.scale(1.3, 1.3);
	        Graphics2D g2d = (Graphics2D) g;
	        g2d.drawImage(missile,at,null);
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
	
	 public void avancer(){
	        x=(x+vr*Math.cos(Math.toRadians(vtheta)));
	        y=(y+vr*Math.sin(Math.toRadians(vtheta)));
	       
	        
	 }
	 
	 public void exploser(Graphics g) {
		  
		g.drawImage(im,(int)(this.x-35),(int)(this.y-50),null);//this.x-x_im/2...
		
		//suppresion missile ensuite
	 }
	 
	
}
	
	
