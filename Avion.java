import javax.swing.*;
import java.awt.event.*;
import java.awt.*;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JTextField;
import java.util.ArrayList;
import java.util.LinkedList;

public class Avion extends Objet{

    protected int nbMissiles = 0;
    protected final int nbMaxMissiles = 5;
    public ArrayList<Bombe> listebombe;
    public ArrayList<Missiles> missiles;
     public ArrayList <Bombe> listebombesuppr;

    public Avion (double x, double y, double vr, double vtheta, int h, int l) {
        super(x, y, vr, vtheta, h, l);

        listebombe=new ArrayList<Bombe>();
        missiles=new ArrayList<Missiles>();
        listebombesuppr=new ArrayList<>();

        listebombe.add(new Bombe(this.x, this.y,h,l,this));
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
        Bombe bombe=new Bombe(this.x, this.y,h,l,this);
        listebombe.add(bombe);
    }

    public void tirerMissiles() {

        if(nbMissiles<nbMaxMissiles) {
            Missiles missile = new Missiles(x,y,2*vr,vtheta,h,l);
            missiles.add(missile);
            nbMissiles++;
        }

    } 

}

