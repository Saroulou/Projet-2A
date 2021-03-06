
import java.awt.*;

public abstract class Objet {

    protected double x; //position
    protected double y;
    protected double vr;//vitesse
    protected double vtheta;//degres
    protected double vx;
    protected double vy;
    protected int h; //hauteur fenetre
    protected int l;
    protected double rayon; // rayon de l'objet (utilisé pour les collisions)
    protected String nom;
    protected double degats;
    protected double vie;
    protected int tLancement;

    public Objet(double x, double y, double vr, double vtheta, int h, int l, double r, String nom, double degats, double vie) {
        this.x=x;
        this.y=y;
        this.vr=vr;
        this.vtheta=vtheta;
        vToCartesien();

        this.h = h;
        this.l = l;
        this.nom = nom;
        this.rayon = r;
        this.degats = degats;
        this.vie = vie;
    }

    public String toString(){
        return "Objet " + this.nom;
    }

    public boolean collison(Objet avion) {
        return Math.sqrt(Math.pow(x-avion.getX(), 2) + Math.pow(y-avion.getY(), 2)) < this.rayon + avion.getRayon();
    }

    protected void vToPolaire() {
        vr     = Math.sqrt(vx*vx + vy*vy);
        vtheta = Math.atan2(vy, vx);
    }
    protected void vToCartesien() {
        vx = vr * Math.cos(Math.toRadians(vtheta));
        vy = vr * Math.sin(Math.toRadians(vtheta));
    }

    public void avancer (double vBackground){}

    public void avancer (){
        avancer(0);
    }

    public void tourner(){}

    public void dessine(Graphics g) {}

    public void prendreDegats(double ptVie) {
        this.vie -= ptVie;
    }
    
    // getteurs :
    public double getX() {return this.x;}
    public double getY() {return this.y;}
    public double getRayon() {return this.rayon;}
    public double getVie() {return this.vie;}
    public double getDegats() {return this.degats;}
    public int getT() {return tLancement;}
}
