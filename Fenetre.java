import javax.swing.*;
import java.awt.event.*;
import java.awt.*;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JTextField;
import java.util.ArrayList;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.*;


public class Fenetre extends JFrame implements ActionListener, KeyListener{
    private ArrayList<Avion> avions;
    private Timer timer;
    private final int var=100;
     private BufferedImage image= null;
    public Fenetre(String nom, int width, int height){

        super(nom);

        setSize(width, height);
        setLocation(200,200);
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);

        avions=new ArrayList<Avion>();
        avions.add(new Avion(getHeight(),getWidth()));
        timer=new Timer(var, this);
        timer.start();
        try {
        image = ImageIO.read(new File("Background.jpg"));
    }
    catch (IOException e){
        e.printStackTrace();
    }

        addKeyListener(this);
    }
    public Fenetre(){
        this("Skywar",1000,700);
    }

    public void paint (Graphics g){
        g.drawImage(image,0,0,null);
      g.setColor(new Color (100,20,70));
        //g.fillRect(0,0,this.getWidth(),this.getHeight());
        for(Avion av: avions){
            av.dessine(g);
            for(Bombe b:av.listebombe){
                 if(b!=null){
                     b.dessine(g);
                }
            }
        }        
        for(Avion av: avions){
            av.dessine(g);
            for (Missiles m: av.missiles) {
            	if (m != null)
            	m.dessine(g);
            
            }
        }
    }

        
        
    public void actionPerformed(ActionEvent e){
         for(Avion av: avions){
             av.avancer();
             /*for(Bombe b:av.listebombe){
                 if(b!=null){
                     b.tombe();
                     if(b.estsorti()){
                         av.listebombe.remove(b);

                     }

                }

            }*/
             for(Avion avion: avions){
                 av.avancer();
                 for (Missiles m: avion.missiles) {
                	 if (m != null)
                 	m.avancer();
                 
                 }
             }
        }repaint();
    }

    public void keyPressed(KeyEvent e){
        int code = e.getKeyCode();

        if(code==KeyEvent.VK_LEFT){
            avions.get(0).tourner(-10);

        }else if(code == KeyEvent.VK_RIGHT){

            avions.get(0).tourner(10);

        }else if(code==KeyEvent.VK_RIGHT){
        avions.get(0).tirerBombe();
        }if(code == KeyEvent.VK_SPACE) {
        	avions.get(0).tirerMissiles();
        	
        }       
    }


    public void keyReleased(KeyEvent e){}
    public void keyTyped(KeyEvent e){}



}


