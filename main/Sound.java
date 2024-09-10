
package main;

import java.net.URL;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

public class Sound {
    
    Clip clip; //used to import audio file
    URL soundURL[] = new URL[8]; //the number can change
    
    
    public Sound(){
        
        soundURL[0] = getClass().getResource("../sound/0.wav");
        soundURL[1] = getClass().getResource("../sound/1.wav");
        soundURL[2] = getClass().getResource("../sound/2.wav");
        soundURL[3] = getClass().getResource("../sound/3.wav");
        soundURL[4] = getClass().getResource("../sound/4.wav");
        soundURL[5] = getClass().getResource("../sound/5.wav");
        soundURL[6] = getClass().getResource("../sound/6.wav");
        soundURL[7] = getClass().getResource("../sound/7.wav");
    }
    
    //----------------SOUND FILES-----------------//
    
    public void setFile(int i){
        
        try{            
            AudioInputStream ais = AudioSystem.getAudioInputStream(soundURL[i]);
            clip = AudioSystem.getClip();
            clip.open(ais);
            
        }
        catch(Exception e){
            e.printStackTrace();
        }        
    }
    
    //---------------------------------------------//
    //-----------------SOUND METHODS---------------//
    
    public void play(){
        
        clip.start();
        
    }
    
    public void loop(){
        
        clip.loop(Clip.LOOP_CONTINUOUSLY);
        
    }
    
    public void stop(){
        
        clip.stop();
        
    }
    
    //------------------------------------------//
}