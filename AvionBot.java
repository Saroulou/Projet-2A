import java.awt.*;
import java.util.ArrayList;


public class AvionBot extends Avion{
	
		//image explosion de l'avion quand collision
    	Toolkit T = Toolkit.getDefaultToolkit();
    	Image im = T.getImage("explosion-gif-001.gif");
    	
	    protected double theta=30;//pas d'angle (pr�cision mini)
	    protected final int nbMaxMissilesBOT = 5;
	    public Avion avion;



	public AvionBot(Avion avion,double x, double y, double vr, double vtheta, int h, int l,Fenetre fenetre) {
		super(x,y, vr, vtheta, h, l,fenetre);
		this.x=x;
		this.y=y;
		this.avion = avion;
		missiles=new ArrayList<Missiles>();
        balles=new ArrayList<Mitrailleuse>();
        listemissilesuppr=new ArrayList<>();
        listeballesuppr=new ArrayList<>();
	}
		
	    public AvionBot(Avion avion, int h, int l,Fenetre fenetre){
	        this(avion,600,600,5,0,h,l,fenetre); //initialiser coord. initiales au hasard
	    }
		

	    public void dessine(Graphics g){
	        int r = 30;
	        g.setColor(new Color (210,19,90));
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
	    	
	    	double a = avion.getX()-this.x;
            double b = avion.getY()-this.y;
            double dir = Math.toDegrees(Math.atan2(b,a));
            // System.out.println(dir-vtheta);
	    	vtheta = dir;
	    	vtheta += Math.signum(dir-vtheta) * 10; 

	    }
	    
 
	    public void exploser(Graphics g) {
			g.drawImage(im,(int)(this.x-35),(int)(this.y-50),null);//this.x-x_im/2...
		 }
		

	    public void tirerMissiles() {
	    	
	    	//if(direction avionBot est proche à un angle x de avionjoueur : tirer) 
	    	
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
