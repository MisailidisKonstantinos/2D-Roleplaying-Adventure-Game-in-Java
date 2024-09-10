
package main;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyHandler implements KeyListener{

    public boolean upPressed, downPressed, leftPressed, rightPressed, enterPressed, lPressed;
    SaveGame saveG;
    LoadGame loadG;
    HighScoreLoader hsl;
    
    //debug
    boolean displayPlayTime = false;
    
    //Game state
    
    GamePanel gp;
    
    public KeyHandler(GamePanel gp){
        this.gp = gp;
    }
    
    @Override
    public void keyTyped(KeyEvent e) {
    }

    //-----------------KEY PRESSED------------------//
    @Override
    public void keyPressed(KeyEvent e) {
        
        int code = e.getKeyCode(); //gets the keycode.

        
        //----------------TITLE STATE---------------//
        
        if(gp.gameState == gp.titleState){
            if(code == KeyEvent.VK_W || code == KeyEvent.VK_UP){
                gp.ui.commandNum--;
                if(gp.ui.commandNum < 0){
                    gp.ui.commandNum = 2;
                }
            }
        
            if(code == KeyEvent.VK_S || code == KeyEvent.VK_DOWN){
                gp.ui.commandNum++;
                if(gp.ui.commandNum > 2){
                    gp.ui.commandNum = 0;
                }
            }
            
            if(code == KeyEvent.VK_ENTER){
                if(gp.ui.commandNum == 0){
                    gp.gameState = gp.playState;
                    hsl = new HighScoreLoader(gp);
                }
                if(gp.ui.commandNum == 1){
                    loadG = new LoadGame(gp);
                    hsl = new HighScoreLoader(gp);                    
                }
                if(gp.ui.commandNum == 2){
                    System.exit(0);
                }     
                gp.ui.commandNum = 0;
            }
        }
        
        //--------------------------------------------//
        //-------PLAY STATE AND PLAYER MOVEMENT-------//
        
        else if(gp.gameState == gp.playState){
            if(code == KeyEvent.VK_W || code == KeyEvent.VK_UP){
                upPressed = true;            
            }

            if(code == KeyEvent.VK_S || code == KeyEvent.VK_DOWN){
                downPressed = true;
            }

            if(code == KeyEvent.VK_A || code == KeyEvent.VK_LEFT){
                leftPressed = true;
            }

            if(code == KeyEvent.VK_D || code == KeyEvent.VK_RIGHT){
                rightPressed = true;
            }

            if(code == KeyEvent.VK_ESCAPE){
                gp.gameState = gp.pauseState;                                
            }
            
            if(code == KeyEvent.VK_ENTER){
                enterPressed = true;                                
            }

            if(code == KeyEvent.VK_T){
                if(displayPlayTime == false){
                    displayPlayTime = true;
                }
                else if(displayPlayTime == true){
                    displayPlayTime = false;
                }
            }
            
            if(code == KeyEvent.VK_L){
                gp.gameState = gp.dialogueState;
                switch(gp.stage){
                    case 0:
                        gp.ui.currentDialogue = "Collect the 4 pieces of the map in order to find\nZargnox's base.";
                        break;
                    case 1:
                        gp.ui.currentDialogue = "Find the Key to unlock the door.";
                        break;
                    case 2:
                        gp.ui.currentDialogue = "Find the Black Key and slay 7 enemies to unlock the\nBlack Door.";
                        break;
                    case 3:
                        gp.ui.currentDialogue = "Slay Zargnox!!!";
                        break;
                }                                       
            }
        }   
        
        //--------------------------------------//
        //-------------PAUSE STATE--------------//
        
        else if(gp.gameState == gp.pauseState){
            if(code == KeyEvent.VK_ESCAPE){
                gp.gameState = gp.playState;
            }
            
            if(code == KeyEvent.VK_W || code == KeyEvent.VK_UP){
                gp.ui.commandNum--;
                if(gp.ui.commandNum < 0){
                    gp.ui.commandNum = 2;
                }
            }
        
            if(code == KeyEvent.VK_S || code == KeyEvent.VK_DOWN){
                gp.ui.commandNum++;
                if(gp.ui.commandNum > 2){
                    gp.ui.commandNum = 0;
                }
            }
            
            if(code == KeyEvent.VK_ENTER){
                if(gp.ui.commandNum == 0){
                    saveG = new SaveGame(gp.stage, gp.player.maxLife, gp.player.speed, (int) gp.ui.totalTimePassed);
                    gp.gameState = gp.playState;
                }
                if(gp.ui.commandNum == 1){
                    gp.gameState = gp.dialogueState;
                    gp.ui.currentDialogue = "W,A,S,D & Arrows Key = Player movement\nEnter = Interact & Attack           L = Quest Log\nT = Playtime & High Score\nEscape = Pause";
                }  
                if(gp.ui.commandNum == 2){
                    System.exit(0);
                }  
                gp.ui.commandNum = 0;
            }                        
        }
        
        //-------------------------------------//
        //------------DEATH STATE--------------//
        
        else if(gp.gameState == gp.deathState){
            if(code == KeyEvent.VK_W || code == KeyEvent.VK_UP){
                gp.ui.commandNum--;
                if(gp.ui.commandNum < 0){
                    gp.ui.commandNum = 1;
                }
            }
        
            if(code == KeyEvent.VK_S || code == KeyEvent.VK_DOWN){
                gp.ui.commandNum++;
                if(gp.ui.commandNum > 1){
                    gp.ui.commandNum = 0;
                }
            }
            
            if(code == KeyEvent.VK_ENTER){                
                if(gp.ui.commandNum == 0){
                    gp.gameState = gp.playState;
                }
                if(gp.ui.commandNum == 1){
                    System.exit(0);
                }
                gp.ui.commandNum = 1;
            }
        }
        
        //--------------------------------------//
        //-----------DIALOGUE STATE-------------//
        
        else if(gp.gameState == gp.dialogueState){
            if(code == KeyEvent.VK_ENTER || code == KeyEvent.VK_L){
                gp.gameState = gp.playState;
            }
        }
        
        //--------------------------------------//
        //-----------VICTORY STATE--------------//
        
        else if(gp.gameState == gp.victoryState){
            if(code == KeyEvent.VK_ESCAPE){
                System.exit(0);
            }
        }
        
        //--------------------------------------//
        
    }

    //---------------------------------------//
    //--------------KEY RELEASED-------------//
    @Override
    public void keyReleased(KeyEvent e) {
        
        int code = e.getKeyCode();
        
        if(code == KeyEvent.VK_W || code == KeyEvent.VK_UP){
            upPressed = false;            
        }
        
        if(code == KeyEvent.VK_S || code == KeyEvent.VK_DOWN){
            downPressed = false;
        }
        
        if(code == KeyEvent.VK_A || code == KeyEvent.VK_LEFT){
            leftPressed = false;
        }
        
        if(code == KeyEvent.VK_D || code == KeyEvent.VK_RIGHT){
            rightPressed = false;
        }
    }
    //----------------------------------------//
}