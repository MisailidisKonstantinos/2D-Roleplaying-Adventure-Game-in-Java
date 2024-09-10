
package main;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class HighScoreLoader {
    
    final String file = "src\\main\\highScore.txt";
    String line = "";
    BufferedReader read;
    BufferedWriter writer;
    
    public HighScoreLoader(GamePanel gp){
                
        try{
            BufferedReader read = new BufferedReader(new FileReader(file));
            
            while((line = read.readLine()) != null){
                gp.ui.highScore = Integer.parseInt(line);
                gp.ui.highScoreSeconds = gp.ui.highScore;
            
                while((int) gp.ui.highScoreSeconds >= 60){
                    gp.ui.highScoreMinutes++;
                    gp.ui.highScoreSeconds -= 60;
                }
            }                      
        }
        catch(FileNotFoundException e){
            e.printStackTrace();
        }
        catch(IOException e){
            e.printStackTrace();
        }
    }
}