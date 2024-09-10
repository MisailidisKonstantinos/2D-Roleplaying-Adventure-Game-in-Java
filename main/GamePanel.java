
package main;

import entity.Entity;
import entity.Player;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JPanel;
import tile.TileManager;

public class GamePanel extends JPanel implements Runnable{
    
    
    //------------SCREEN SETTINGS-------------//
    
    final int originalTileSize = 16;
    public int scale = 3; //scale of the size. From 16x16 to 48x48
    
    public final int tileSize = originalTileSize * scale; //48x48 tile
    
    public final int maxScreenCol = 20; //20 columns
    public final int maxScreenRow = 12; //12 rows
    //Screen ratio ~ 16:9
    
    public int screenWidth = tileSize * maxScreenCol; //960 pixels
    public int screenHeight = tileSize * maxScreenRow; //576 pixels
    
    //----------------------------------------//
    //-------------WORLD SETTINGS-------------//
    
    public final int maxWorldCol = 50;
    public final int maxWorldRow = 50;
    
    //----------------------------------------//
    //--------------STAGE STUFF---------------//
        
    public final int allStages = 4;
    public int stage = 0;
    
    //----------------------------------------//
    //--------------FPS AND TILES-------------//
    
    final int FPS = 60;
        
    TileManager tileM = new TileManager(this);
       
    //----------------------------------------//
    //---------------MUSIC STUFF--------------//
    
    Sound sound = new Sound();
    public int musicChanger = 0;
    
    //----------------------------------------//
    //-----COLLISION, PLAYER AND THREAD-------//
    
    //entity and object
    public CollisionChecker cChecker = new CollisionChecker(this);
    
    public KeyHandler keyH = new KeyHandler(this);
    
    public Player player = new Player(this, keyH);
    
    Thread gameThread;
    
    //----------------------------------------//
    //--------ASSETS AND RENDER ORDER---------//
    
    public AssetSetter aSetter = new AssetSetter(this);
    
    public Entity obj[][] = new Entity[allStages][10];
    
    public Entity npc[][] = new Entity[allStages][10];
    
    public Entity enemies[][] = new Entity[allStages][20];
    
    ArrayList<Entity> entityList = new ArrayList<>();
    
    //----------------------------------------//
    //-------------------UI-------------------//
    
    public UI ui = new UI(this);
    
    //----------------------------------------//
    //---------------GAME STATE---------------//
    
    public int gameState;
    public final int titleState = 0;
    public final int playState = 1;
    public final int pauseState = 2;
    public final int dialogueState = 3;
    public final int victoryState = 4;
    public final int deathState = 5;
    
    //----------------------------------------//
    
    
    public GamePanel(){
        
        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setBackground(Color.black);
        this.setDoubleBuffered(true); //the drawing for this component will be done in an offscreen painting buffer.
        this.addKeyListener(keyH); //adds key listener. Must create it first though.
        this.setFocusable(true); //GamePanel will be focused on receiving key input.
        gameState = titleState;
        
    }
    
    //----------------GAME SETUP AND THREAD---------------//
    
    public void setupGame(){        
        aSetter.setObject(); //adds the objects.
        
        aSetter.setNPC(); //adds an npc.
        
        aSetter.setEnemy();
    }
    
    public void startGameThread(){        
        gameThread = new Thread(this); //We pass GamePanel to the Thread constructor
        gameThread.start();               
    }

    @Override
    public void run() {
       
        double drawInterval = 1000000000/FPS; //uses nanoseconds. Basically 0.01 seconds.
        double nextDrawTime = System.nanoTime() + drawInterval;
        
        //Game Loop
        while(gameThread != null){
                                               
            //System.out.println("The game is running");
            
            // 1 Update information such as character position
            update();
            
            // 2 Draw the screen with the updated information
            repaint(); //calls the paintComponent() method.
                                    
            try {
                double remainingTime = nextDrawTime - System.nanoTime();
                remainingTime = remainingTime/1000000; //sleep() requires milli seconds. So we convert the remainingTime from nano to milli.
                
                if(remainingTime < 0){
                    remainingTime = 0;
                }
                
                Thread.sleep((long) remainingTime);
                
                nextDrawTime += drawInterval; //we add the delay time to the nextDrawTime.
                
            } catch (InterruptedException ex) {
                Logger.getLogger(GamePanel.class.getName()).log(Level.SEVERE, null, ex);
            }            
        }
    }
    
    //----------------------------------------------------//
    //--------------------MUSIC METHODS-------------------//
    
    public void playMusic(int i){
        
        sound.setFile(i);
        sound.play();
        sound.loop();        
    }
    
    public void stopMusic(){
        
        sound.stop();
        
    }
    
    
    public void chooseTrack(int gameState){
        
        switch(gameState){
            case 0 :
                playMusic(gameState);
                break;
            case 1:
                playMusic(stage + 1);
                break;
            case 2 :
                playMusic(5);
                break;
            case 4:
                playMusic(6);
                break;
            case 5:
                playMusic(7);
                break;
        }
    }
    
    //----------------------------------------------------//
    //------------------STAGE ADVANCEMENT-----------------//
    
    public void advanceStage(){
        stage++;
        musicChanger = 3;
        player.life = player.maxLife;
        player.getPlayerStartingPositions(stage);        
    }   
    
    //----------------------------------------------------//
    //-----------------------UPDATE-----------------------//
    
    public void update(){
        
        //---------------PLAY STATE---------------//
        
        if(gameState == playState){
            player.update(); //this way I will save some space.           
            
            if(musicChanger == 3){
                stopMusic();
                musicChanger = 0;
            }
            if(musicChanger == 0){
                chooseTrack(playState);
                musicChanger = 1;
            }
            
            //---------NPC AND ENEMIES UPDATE---------//
            
            for(int i = 0; i < npc[1].length; i++){
                if(npc[stage][i] != null){
                    npc[stage][i].update();
                }
            }

            for(int i = 0; i < enemies[1].length; i++){
                if(enemies[stage][i] != null){
                    enemies[stage][i].update();
                }
            }
            
            //----------------------------------------//
            //-------------WIN CONDITIONS-------------//
            
            if(stage == 0){
                if(obj[stage][0] == null && obj[stage][1] == null && obj[stage][2] == null && obj[stage][3] == null){
                    
                    advanceStage(); 
                    
                }
            }
            else if(stage == 1){
                if(obj[stage][0] == null){
                    
                    advanceStage(); 
                    
                }
            }
            else if(stage == 2){
                if(obj[stage][0] == null){
                    
                    advanceStage();
                    
                }
            }
            else if(stage == 3){
                if(enemies[stage][0] == null){                    

                    HighScoreSaver hss = new HighScoreSaver(this);
                    gameState = victoryState;
                    
                }
            }
            //----------------------------------------//
        }
        
        //----------------------------------------//
        //--------------PAUSE STATE---------------//
        
        if(gameState == pauseState){
            if(musicChanger == 1){
                stopMusic();
                musicChanger = 0;
            }
            if(musicChanger == 0){
                chooseTrack(pauseState);
                musicChanger = 3;
            }
        }
        
        //----------------------------------------//
        //-------------VICTORY STATE--------------//
        
        if(gameState == victoryState){
            if(musicChanger == 1){
                stopMusic();
                musicChanger = 4;
            }
            if(musicChanger == 4){
                
                chooseTrack(victoryState);
                musicChanger = 3;
            }
        }
        
        //----------------------------------------//
        //---------------DEATH CODE---------------//
        
        if(player.life == 0){
            setupGame();
            gameState = deathState;
            player.life = player.maxLife;
            player.worldX = player.startingWorldX;
            player.worldY = player.startingWorldY;
            player.direction = "down";
            
            if(stage == 0){
                player.maps = 0;
            }
            
            if(stage == 1){
                player.key = false;
            }
            
            if(stage == 2){
                player.slain = 0;
                player.blackKey = false;
            }
        }
        
        if(gameState == deathState){
            if(musicChanger == 1){
                stopMusic();
                musicChanger = 0;
            }
            if(musicChanger == 0){
                
                chooseTrack(deathState);
                musicChanger = 3;
            }
        }
        //----------------------------------------//
    }
    
    //----------------------------------------------------//
    //------------------PAINT COMPONENT-------------------//
    
    public void paintComponent(Graphics g){
        
        super.paintComponent(g); //Parent class = JPanel in this circumstance.
        
        Graphics2D g2 = (Graphics2D) g; //Typecasting as Graphics2D.

        
        //Title Screen
        if(gameState == titleState){
            //music
            if(musicChanger == 2){
                stopMusic();
                musicChanger = 0;
            }
            if(musicChanger == 0){
                chooseTrack(titleState);
                musicChanger = 3;
            }
            
            //window
            ui.draw(g2);
        }
        else{
            //Tile
            tileM.draw(g2);
            
            //fills the arraylist for the render order.
            entityList.add(player);
            
            for(int i = 0; i < npc[1].length; i++){
                if(npc[stage][i] != null){
                    entityList.add(npc[stage][i]);
                }
            }
            
            for(int i = 0; i < obj[1].length; i++){
                if(obj[stage][i] != null){
                    entityList.add(obj[stage][i]);
                }
            }
            
            for(int i = 0; i < enemies[1].length; i++){
                if(enemies[stage][i] != null){
                    entityList.add(enemies[stage][i]);
                }
            }
            
            //sort
            Collections.sort(entityList, new Comparator<Entity>() {
                
                @Override
                public int compare(Entity e1, Entity e2) {
                    
                    int result = Integer.compare(e1.worldY, e2.worldY);
                    return result;
                }               
            });
            
            //Draw entities
            for(int i = 0; i < entityList.size(); i++){
                entityList.get(i).draw(g2);
            }
            
            entityList.clear();

            //UI
            ui.draw(g2);
        }
                
        g2.dispose(); //releases any system resources that it is using. Saves memory this way.        
    }
    
    //----------------------------------------------------//         
}