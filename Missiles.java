

import java.awt.Color;
import java.awt.Graphics;
//import java.util.ArrayList;

public class Missiles extends Avion {



    public Missiles (double x, double y,double vr, double vtheta, int h, int l) {
        super(x,y,vr,vtheta,h,l);

    }


    public void dessine(Graphics g){

         int r = 20;
            g.setColor(Color.black);
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
