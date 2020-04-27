
public abstract class Objet {

    protected double x; //position
    protected double y;
    protected double vr;//vitesse
    protected double vtheta;//degres
    protected double vx;
    protected double vy;
    protected int h; //hauteur fenetre
    protected int l;

    public Objet(double x, double y, double vr, double vtheta, int h, int l) {
        this.x=x;
        this.y=y;
        this.vr=vr;
        this.vtheta=vtheta;
        vToCartesien();
        this.h = h;
        this.l = l;

    }

    protected void vToPolaire() {
        vr     = Math.sqrt(vx*vx + vy*vy);
        vtheta = Math.atan2(vy, vx);
    }
    protected void vToCartesien() {
        vx = vr * Math.cos(vtheta);
        vx = vr * Math.sin(vtheta);
    }


}
