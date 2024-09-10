
package main;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class LoadGame {
    
    final String file = "src\\main\\save.txt";
    String line = "";
    
    public LoadGame(GamePanel gp){
        
        
        try
        {
            int i = 0;
            BufferedReader read = new BufferedReader(new FileReader(file));
            while((line = read.readLine()) != null)
            {
               switch(i){
                   case 0:
                       gp.stage = Integer.parseInt(line);
                       break;
                   case 1:
                       gp.player.maxLife = Integer.parseInt(line);
                       gp.player.life = gp.player.maxLife;
                       break;
                   case 2:
                       gp.player.speed = Integer.parseInt(line);
                       break;
                   case 3:
                       gp.ui.totalTimePassed = Double.parseDouble(line);
                       gp.ui.secondsPassed = gp.ui.totalTimePassed;
                       
                       while((int) gp.ui.secondsPassed >= 60){
                           gp.ui.minutesPassed++;
                           gp.ui.secondsPassed -= 60;
                       }
               }
               
               i++;
            }
            
            gp.gameState = gp.playState;
            gp.player.getPlayerStartingPositions(gp.stage);
        }
        catch(FileNotFoundException e)
        {
            e.printStackTrace();
        }
        catch(IOException e)
        {
            e.printStackTrace();
        }
    }
}