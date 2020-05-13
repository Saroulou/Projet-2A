
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;


public class Audio{

	private Clip clip;

	public Audio(String son){

			try {
				AudioInputStream audio = AudioSystem.getAudioInputStream(getClass().getResource(son));
				clip = AudioSystem.getClip();
				clip.open(audio);		
			}
			catch (Exception e) {}
		}
		
		//get
		public Clip getClip(){ return clip;}
		
		//methodes
		
		public void off(){
			clip.stop();
			}
		
		public void playL(){ // play en boucle la musique 
			clip.start();
			clip.loop(Clip.LOOP_CONTINUOUSLY);
			}
			
		public void play(){ // play la musique
			clip.start();
			}
		
		public static void playSound (String son){
			Audio s =new Audio(son);
			s.play();
		}
		public static void playSoundL (String son){
			Audio s =new Audio(son);
			s.playL();
		}
		
}
    
