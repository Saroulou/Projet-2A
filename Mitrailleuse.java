import java.awt.Color;
import java.awt.Graphics;


public class Mitrailleuse extends Avion { // héritage à changer
    // public int degatMitr=1;

    public Mitrailleuse(double x, double y, double vr, double vtheta, int h, int l,Fenetre fenetre, String nom) {
        super(x, y, vr, vtheta, h, l,fenetre, 5, nom, 1, 0.1);
        
    }

public String toString(){
    return "Missile " + this.nom;
}
    
    public void dessine(Graphics g){

         int r = 5;
            g.setColor(Color.red);
            g.fillPolygon(
                new int[]{(int)(x+r*Math.cos(Math.toRadians(vtheta))),(int)(x+r*Math.cos(Math.toRadians(vtheta+120))),(int)(x+r*Math.cos(Math.toRadians(vtheta-120)))},
                new int[]{(int)(y+r*Math.sin(Math.toRadians(vtheta))),(int)(y+r*Math.sin(Math.toRadians(vtheta+120))),(int)(y+r*Math.sin(Math.toRadians(vtheta-120)))},
                3);
   } 
    
     public void avancer(){
            x=(x+vr*Math.cos(Math.toRadians(vtheta)));
            y=(y+vr*Math.sin(Math.toRadians(vtheta)));
     }
}

