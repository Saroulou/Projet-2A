import javax.swing.*;
import java.awt.event.*; 
import java.awt.*;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JTextField;
import java.util.ArrayList; 


public class Fenetre extends JFrame implements ActionListener, KeyListener{
    private ArrayList<Avion> avions; 
    private Timer timer;
    private final int var=100;
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
        
        addKeyListener(this);
    }
    public Fenetre(){
        this("Skywar",2000,1000);
    }
    
    public void paint (Graphics g){
      g.setColor(new Color (100,20,70));
        g.fillRect(0,0,this.getWidth(),this.getHeight());
        for(Avion av: avions){
            av.dessine(g);
        }   
    }
    
    public void actionPerformed(ActionEvent e){
         for(Avion av: avions){
             av.avancer();
         }
         repaint();
    }
    
    public void keyPressed(KeyEvent e){
        int code = e.getKeyCode();
        
        if(code==KeyEvent.VK_LEFT){
            avions.get(0).tourner(-10);
            
        }else if(code == KeyEvent.VK_RIGHT){
            
            avions.get(0).tourner(10);
            
        }
        
        
    }
        
        
        
    public void keyReleased(KeyEvent e){}
    public void keyTyped(KeyEvent e){}
    
    
    
} 
   

