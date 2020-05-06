import java.awt.*;
import java.util.ArrayList;


public class AvionBot extends Avion{
	
		//image explosion de l'avion quand collision
    	Toolkit T = Toolkit.getDefaultToolkit();
    	Image im = T.getImage("explosion-gif-001.gif");
	
	    protected double xJ = getPositionX(); //position joueur
	    protected double yJ = getPositionY();
	    protected double theta=30;//pas d'angle (pr�cision mini)
	    protected final int nbMaxMissilesBOT = 5;


	    

	public AvionBot(double xJ, double yJ,double x, double y, double vr, double vtheta, int h, int l,Fenetre fenetre) {
		super(xJ, yJ, vr, vtheta, h, l,fenetre);
		this.x=x;
		this.y=y;
		missiles=new ArrayList<Missiles>();
        balles=new ArrayList<Mitrailleuse>();
	}
		
	    public AvionBot(int h, int l,Fenetre fenetre){
	        this(100,100,200,200,10,0,h,l,fenetre); //initialiser coord. initiales au hasard
	    }
		

	    public void dessine(Graphics g){
	        int r = 30;
	        g.setColor(new Color (210,119,90));
	        g.fillPolygon(
	            new int[]{(int)(x+r*Math.cos(Math.toRadians(vtheta))),(int)(x+r*Math.cos(Math.toRadians(vtheta+120))),(int)(x+r*Math.cos(Math.toRadians(vtheta-120)))},
	            new int[]{(int)(y+r*Math.sin(Math.toRadians(vtheta))),(int)(y+r*Math.sin(Math.toRadians(vtheta+120))),(int)(y+r*Math.sin(Math.toRadians(vtheta-120)))},
	            3);
	    }

	    public void avancer(){
	        x=(x+vr*Math.cos(Math.toRadians(vtheta)))%l;
	        y=(y+vr*Math.sin(Math.toRadians(vtheta)))%h;
	        //System.out.println(x);
	    }

	    
	   //@Fonction asservissement 
	    public void tourner(){
	        //vtheta+=theta;
	    	double a = xJ-this.x;
	    	double b = yJ-this.y;
	    	vtheta = Math.atan2((Math.abs(b)),(Math.abs(a))); 
	    }
	    
	    
	    
	    
	    public void exploser(Graphics g) {
			g.drawImage(im,(int)(this.x-35),(int)(this.y-50),null);//this.x-x_im/2...
		 }
		

	    public void tirerMissiles() {
	    	
	        if(nbMissiles<nbMaxMissilesBOT) {
	            Missiles missile = new Missiles(x,y,2.5*vr,vtheta,h,l,fenetre);
	            missiles.add(missile);
	            nbMissiles++;
	        }
	    }
	    
	    public void tirerBalles() {
	    	
	        if(nbBalles<nbMaxBalles) {
	            Mitrailleuse balle = new Mitrailleuse(x,y,2*vr,vtheta,h,l,fenetre);
	            balles.add(balle);
	            nbBalles++;
	        }
	    }
	    

	}