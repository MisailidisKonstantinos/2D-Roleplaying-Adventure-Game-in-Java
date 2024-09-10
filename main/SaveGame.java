package main;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;


public class SaveGame {
    
    final String fileOutput = "src\\main\\save.txt";
    
    public SaveGame(int stage, int maxLife, int speed, int time){
        
        File file = new File(fileOutput);
        
        try
        {
            BufferedWriter writer = new BufferedWriter(new FileWriter(file));
            
            writer.write(Integer.toString(stage));
            writer.newLine();
            if(stage >= 2){
                writer.write(Integer.toString(maxLife));
                writer.newLine();
                writer.write(Integer.toString(speed));
            }
            else{
                writer.write("6");
                writer.newLine();
                writer.write("4");
            }
            
            writer.newLine();
            writer.write(Integer.toString(time));
                        
            writer.flush();
        }
        catch(IOException e)
        {
            e.printStackTrace();
        }
    }
}