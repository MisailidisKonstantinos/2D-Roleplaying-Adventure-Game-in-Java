
package main;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class HighScoreSaver {
    
    final String fileOutput = "src\\main\\highScore.txt";
    BufferedWriter writer;
    
    HighScoreSaver(GamePanel gp){
        
        if(gp.ui.totalTimePassed < gp.ui.highScore || gp.ui.highScore == 0){
            
            File file = new File(fileOutput);
            
            try{
                writer = new BufferedWriter(new FileWriter(file));
                writer.write(Integer.toString((int) gp.ui.totalTimePassed));
                
                writer.flush();
            }
            catch(IOException e){
                e.printStackTrace();
            }
        }                
    }
}