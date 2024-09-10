
package main;

import entity.Entity;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.text.DecimalFormat;
import object.OBJ_Heart;

public class UI {
    
    GamePanel gp;
    Graphics2D g2;
    Font arial;
    BufferedImage full_heart, half_heart, empty_heart;
    public String currentDialogue = "";
    public int commandNum = 0;
    
    double totalTimePassed;
    double secondsPassed;
    int minutesPassed;
    
    double highScore;
    double highScoreSeconds;
    int highScoreMinutes;
    DecimalFormat dFormat = new DecimalFormat("#0");
    
    public UI(GamePanel gp){
        this.gp = gp;
        
        arial = new Font("Arial", Font.PLAIN, 40);
        
        //Create HUD object
        Entity heart = new OBJ_Heart(gp);
        full_heart = heart.image;
        half_heart = heart.image2;
        empty_heart = heart.image3;
    }    
    
    //---------------DRAW-------------//
    
    public void draw(Graphics2D g2){
        
        this.g2 = g2;
        
        g2.setFont(arial);
        g2.setColor(Color.white); 
        
        if(gp.gameState == gp.playState || gp.gameState == gp.dialogueState){
            
            if((int) secondsPassed == 60){
                minutesPassed++;
                secondsPassed -= 60;
            }
            
            secondsPassed += (double)1/60;
            
            totalTimePassed += (double) 1/60;
          
            if(gp.keyH.displayPlayTime == true){
                drawUsefulText("High Score: " + dFormat.format(highScoreMinutes) 
                        + " minutes & " + dFormat.format(highScoreSeconds) 
                        + " seconds.", gp.tileSize/2, gp.tileSize * 10);
                drawUsefulText("Time passed: " + dFormat.format(minutesPassed) 
                        + " minutes & " + dFormat.format(secondsPassed) 
                        + " seconds.", gp.tileSize/2, gp.tileSize * 11);                
            }
            
        }
        
        if(gp.gameState == gp.playState){
            drawPlayerLife();
            
            drawUsefulText("Stage: " + (gp.stage + 1), gp.screenWidth 
                    - gp.tileSize * 3, gp.tileSize);
                       
            switch(gp.stage){
                case 0:
                    if(gp.player.maps != 4){
                        drawUsefulText("Map fragments remaining: " 
                                + (4 - gp.player.maps), gp.tileSize/2
                                , gp.tileSize/2 + gp.tileSize * 2);
                    }
                    break;
                case 1:
                    if(gp.player.key == true){
                        drawUsefulText("Key collected!!", gp.tileSize/2, 
                                gp.tileSize/2 + gp.tileSize * 2);
                    }
                    break;
                case 2:
                    if(gp.player.blackKey == true){
                        drawUsefulText("Black Key collected!!", gp.tileSize/2, 
                                gp.tileSize/2 + gp.tileSize * 3);
                    }

                    drawUsefulText("Enemies remaining: " + (7 - gp.player.slain), 
                            gp.tileSize/2, gp.tileSize/2 + gp.tileSize * 2);
                    break;
                case 3:
                    drawUsefulText("Zargnox's health: " + gp.enemies[3][0].life, 
                            gp.tileSize/2, gp.tileSize/2 + gp.tileSize * 2);
            }            
        }
        
        if(gp.gameState == gp.pauseState){
            drawPlayerLife();
            drawPauseScreen();
        }
        
        if(gp.gameState == gp.dialogueState){
            drawDialogueScreen();
        }
        
        //Title state
        if(gp.gameState == gp.titleState){
            drawTitleScreen();
        }
        
        //Victory state
        if(gp.gameState == gp.victoryState){
            drawEndingScreen();                        
        }
        
        //Death state
        if(gp.gameState == gp.deathState){
            drawDeathScreen();
        }        
    }
    
    //-------------------------------------//
    //---------DRAW PLAYER LIFE------------//
    
    public void drawPlayerLife(){
        
        int x = gp.tileSize/2;
        int y = gp.tileSize/2;
        int i = 0;
        
        //Draw max life
        while(i < gp.player.maxLife/2){
            g2.drawImage(empty_heart, x, y, null);
            i++;
            x += gp.tileSize;
        }
        
        x = gp.tileSize/2;
        y = gp.tileSize/2;
        i = 0;
        
        //Draw current life
        while(i < gp.player.life){
            g2.drawImage(half_heart, x, y, null);
            i++;
            
            if(i < gp.player.life){
                g2.drawImage(full_heart, x, y, null);
            }
            
            i++;
            x += gp.tileSize;
        }
    }
    
    //-----------------------------------//
    //---------DRAW USEFUL TEXT----------//
    
    public void drawUsefulText(String text, int x, int y){
        
        g2.setColor(Color.white);
        g2.setFont(g2.getFont().deriveFont(Font.BOLD, 25F));
        
        g2.drawString(text, x, y);
    }
    
    //-----------------------------------//
    //---------DRAW TITLE SCREEN---------//
    
    public void drawTitleScreen(){
        
        g2.setColor(new Color(0,0,0));
        g2.fillRect(0, 0, gp.screenWidth, gp.screenHeight);
        
        
        //Game name
        g2.setFont(g2.getFont().deriveFont(Font.BOLD,85F));
        String text = "War For Freedom";
        int x = getCenteredX(text);
        int y = gp.tileSize*3;
        
        //Game name shadow
        g2.setColor(Color.red);
        g2.drawString(text, x + 3, y + 3);
        //Game name color
        g2.setColor(Color.yellow);
        g2.drawString(text, x, y);
        
        //Character Image (or logo for me)
        x = gp.screenWidth/2 - (gp.tileSize*2)/2;
        y += gp.tileSize*2;
        g2.drawImage(gp.player.standing, x, y, gp.tileSize*2, gp.tileSize*2, null);
        
        //Menu
        g2.setFont(g2.getFont().deriveFont(Font.BOLD,38F));
        
        text = "NEW GAME";
        x = getCenteredX(text);
        y += gp.tileSize*4;
        g2.drawString(text, x, y);
        
        if(commandNum == 0){
            g2.drawString(">", x - gp.tileSize, y);
        }
        
        text = "LOAD GAME";
        x = getCenteredX(text);
        y += gp.tileSize;
        g2.drawString(text, x, y);
        
        if(commandNum == 1){
            g2.drawString(">", x - gp.tileSize, y);
        }
        
        text = "QUIT";
        x = getCenteredX(text);
        y += gp.tileSize;
        g2.drawString(text, x, y);
        
        if(commandNum == 2){
            g2.drawString(">", x - gp.tileSize, y);
        }
    }
    
    //--------------------------------------//
    //----------DRAW PAUSE SCREEN-----------//
    
    public void drawPauseScreen(){
        //Prints "Paused" on the center of the screen.
        
        g2.setFont(g2.getFont().deriveFont(Font.PLAIN, 80F));
        String text = "Paused";
        
        int x = getCenteredX(text);
                       
        int y = gp.screenHeight/2;
        
        
        g2.drawString(text, x, y);
        
        g2.setFont(g2.getFont().deriveFont(Font.PLAIN, 38F));
        
        text = "SAVE GAME";
        x = getCenteredX(text);
        y += gp.tileSize*3;
        g2.drawString(text, x, y);
        
        if(commandNum == 0){
            g2.drawString(">", x - gp.tileSize, y);
        }
        
        text = "KEYBINDS";
        x = getCenteredX(text);
        y += gp.tileSize;
        g2.drawString(text, x, y);
        
        if(commandNum == 1){
            g2.drawString(">", x - gp.tileSize, y);
        }

        
        text = "QUIT";
        x = getCenteredX(text);
        y += gp.tileSize;
        g2.drawString(text, x, y);
        
        if(commandNum == 2){
            g2.drawString(">", x - gp.tileSize, y);
        }
    }
    
    //----------------------------------------//
    //----------DRAW DIALOGUE SCREEN----------//
    
    public void drawDialogueScreen(){
        
        // Window
        int x = gp.tileSize*2;
        int y = gp.tileSize/2;
        int width = gp.screenWidth - gp.tileSize*6;
        int height = gp.tileSize*4;
        
        drawSubWindow(x, y, width, height);
        
        g2.setFont(g2.getFont().deriveFont(Font.PLAIN, 25F));
        x += gp.tileSize;
        y += gp.tileSize;
        
        for(String line : currentDialogue.split("\n")){
            g2.drawString(line, x, y);
            y += 40;
        }               
    }
    
    //----------------------------------------//
    //-----------DRAW ENDING SCREEN-----------//
    
    public void drawEndingScreen(){
        
        g2.setFont(g2.getFont().deriveFont(Font.PLAIN, 80F));
        String text = "Congratulations!!";
        
        int x = getCenteredX(text);
                       
        int y = gp.screenHeight/2;
        
        
        g2.drawString(text, x, y);       
    }
    
    //-----------------------------------------//
    //----------DRAW DEATH SCREEN--------------//
    
    public void drawDeathScreen(){
        
        g2.setColor(new Color(0,0,0));
        
        g2.fillRect(0, 0, gp.screenWidth, gp.screenHeight);
        
        g2.setColor(Color.RED);
        
        g2.setFont(g2.getFont().deriveFont(Font.PLAIN, 80F));
        String text = "You died!";
        
        int x = getCenteredX(text);
                       
        int y = gp.screenHeight/2;
                
        g2.drawString(text, x, y);
        
        g2.setFont(g2.getFont().deriveFont(Font.PLAIN, 38F));
        
        text = "RETRY";
        x = getCenteredX(text);
        y += gp.tileSize*4;
        g2.drawString(text, x, y);
        
        if(commandNum == 0){
            g2.drawString(">", x - gp.tileSize, y);
        }
       
        text = "QUIT";
        x = getCenteredX(text);
        y += gp.tileSize;
        g2.drawString(text, x, y);
        
        if(commandNum == 1){
            g2.drawString(">", x - gp.tileSize, y);
        }       
    }
    
    //---------------------------------------//
    //-----------DRAW SUB WINDOW-------------//
    
    public void drawSubWindow(int x, int y, int width, int height){
        
        Color c = new Color(0, 0, 0, 170); //the fourth number is the opacity
        g2.setColor(c);
        g2.fillRoundRect(x, y, width, height, 35, 35); //35 can be changed
        
        c = new Color(255, 255, 255);
        g2.setColor(c);
        g2.setStroke(new BasicStroke(5)); //defines the width of the stroke
        g2.drawRoundRect(x + 5, y + 5, width - 10, height - 10, 25, 25);
    }
    
    public void switchDialogueOn(int gameState){
        gp.gameState = gameState;
    }
    
    //-------------------------------------//
    //-----------TEXT ALIGNMENT------------//
    
    public int getCenteredX(String text){
        //calculates the x for the center of the screen based on the text.
        int length = (int) g2.getFontMetrics().getStringBounds(text, g2).getWidth();
        
        int x = gp.screenWidth / 2 - length / 2;
        
        return x;
    }
    
    //------------------------------------//
}
