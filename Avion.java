import javax.swing.*;
import java.awt.event.*;
import java.awt.*;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JTextField;
import java.util.ArrayList;
import java.util.LinkedList;

public class Avion extends Objet implements ActionListener, KeyListener{

    public ArrayList<Bombe> listebombe;
    public ArrayList<Missiles> missiles;
    public ArrayList <Bombe> listebombesuppr;
    public ArrayList <Missiles> listemissilesuppr;
    public ArrayList <Mitrailleuse> listeballesuppr;
    
    public ArrayList<Mitrailleuse> balles;
    protected int nbMissiles = 0;
    protected int nbBalles = 0;
    protected final int nbMaxMissiles = 7;
    protected final int nbMaxBalles = 2000;
    public Fenetre fenetre;
    protected int tempsDepartMissile = 0 ;
    
        
    //image explosion de l'avion quand collision
    Toolkit T = Toolkit.getDefaultToolkit();
	Image im = T.getImage("explosion-gif-001.gif");
 

    public Avion (double x, double y, double vr, double vtheta, int h, int l,Fenetre fenetre) {
        super(x, y, vr, vtheta, h, l);
        
        this.fenetre = fenetre;
        listebombe=new ArrayList<Bombe>();
        listebombesuppr=new ArrayList<>();
        listemissilesuppr=new ArrayList<>();
        listeballesuppr=new ArrayList<>();
        missiles=new ArrayList<Missiles>();
        balles=new ArrayList<Mitrailleuse>();

        listebombe.add(new Bombe(this.x, this.y,h,l,this));
    }

     public Avion(int h, int l, Fenetre fenetre){
        this(100,100,10,0,h,l,fenetre);
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
        public void exploser(Graphics g) {
		g.drawImage(im,(int)(this.x-35),(int)(this.y-50),null);//this.x-x_im/2...
	 }

    public void tirerBombe(){
        Bombe bombe=new Bombe(this.x, this.y,h,l,this);
        listebombe.add(bombe);
    }

   
     public void tirerMissiles() {
    	
        if(nbMissiles<nbMaxMissiles) {
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
    
    public double getX() {return this.x;}
    public double getY() {return this.y;}
    
    
    

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}

}


