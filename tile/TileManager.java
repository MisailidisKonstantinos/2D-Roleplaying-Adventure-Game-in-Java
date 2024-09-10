
package tile;

import java.awt.Graphics2D;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import javax.imageio.ImageIO;
import main.GamePanel;
import main.UtilityTool;

public class TileManager {
    
    GamePanel gp;
    public Tile[] tile;
    public int mapTileNum[][][];
    
    public TileManager(GamePanel gp){
        
        this.gp = gp;
        
        tile = new Tile[20];
        mapTileNum = new int[gp.allStages][gp.maxWorldCol][gp.maxWorldRow];
        
        getTileImage();
        loadMap("/maps/map1.txt", 0);
        loadMap("/maps/map2.txt", 1);
        loadMap("/maps/map3.txt", 2);
        loadMap("/maps/map4.txt", 3);
    }
    
    //---------------------TILE IMAGE SETUP------------------//
    
    public void getTileImage(){
            
        setUp(0, "grass", false);

        setUp(1, "road", false);

        setUp(2, "tree", true);

        setUp(3, "wall", true);

        setUp(4, "water", true);
        
        setUp(5, "water_corner_bottomLeft", true);
        
        setUp(6, "water_corner_bottomRight", true);
        
        setUp(7, "water_corner_upperLeft", true);
        
        setUp(8, "water_corner_upperRight", true);
        
        setUp(9, "water_side_down", true);
        
        setUp(10, "water_side_left", true);
        
        setUp(11, "water_side_right", true);
        
        setUp(12, "water_side_up", true);
        
        setUp(13, "stone_wall", true);
        
        setUp(14, "dark_ground", false);
        
        setUp(15, "water_corner_small_bottomLeft", true);
        
        setUp(16, "water_corner_small_bottomRight", true);
        
        setUp(17, "water_corner_small_upperLeft", true);
        
        setUp(18, "water_corner_small_upperRight", true);
        
        setUp(19, "bridge", false);

    }
        
    
    public void setUp(int index, String imageName, boolean collision){
        
        UtilityTool uTool = new UtilityTool();
        
        try{
            
            tile[index] = new Tile();
            tile[index].image = ImageIO.read(getClass().getResourceAsStream("/tileImgs/" + imageName + ".png"));
            tile[index].image = uTool.scaleImage(tile[index].image, gp.tileSize, gp.tileSize);
            tile[index].collision = collision;
            
        }
        catch(IOException e){
            e.printStackTrace();
        }
    }
    
    //---------------------------------------------------------//
    //-------------------MAP LOADER----------------------------//
    
    public void loadMap(String mapPath, int stage){
        try{
            InputStream is = getClass().getResourceAsStream(mapPath);
            BufferedReader br = new BufferedReader(new InputStreamReader(is));
            
            int col = 0;
            int row = 0;
            
            while(col < gp.maxWorldCol && row < gp.maxWorldRow){
                
                String line = br.readLine();
                
                while(col < gp.maxWorldCol){
                    
                    String numbers[] = line.split(" ");
                    
                    int num = Integer.parseInt(numbers[col]);
                    
                    mapTileNum[stage][col][row] = num;
                    
                    col++;
                }
                
                if(col == gp.maxWorldCol){
                    col = 0;
                    row++;
                }
            }
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }
    
    //-----------------------------------------------------//
    //--------------------------DRAW-----------------------//
    
    public void draw(Graphics2D g2){
        
        int worldCol = 0;
        int worldRow = 0;
        
        while(worldCol < gp.maxWorldCol && worldRow < gp.maxWorldRow){
            
            int tileNum = mapTileNum[gp.stage][worldCol][worldRow];
            
            int worldX = worldCol * gp.tileSize;
            int worldY = worldRow * gp.tileSize;
            int screenX = worldX - gp.player.worldX + gp.player.screenX;
            int screenY = worldY - gp.player.worldY + gp.player.screenY;
            
            //Lines 96 to 104 make sure that the programm draws only the tiles that are in range of player.
            boolean outOfBoundsX = (worldX + gp.tileSize > gp.player.worldX - gp.player.screenX && 
               worldX - gp.tileSize < gp.player.worldX + gp.player.screenX);
            
            boolean outOfBoundsY = (worldY + gp.tileSize > gp.player.worldY - gp.player.screenY && 
               worldY - gp.tileSize < gp.player.worldY + gp.player.screenY);
            
            if(outOfBoundsX && outOfBoundsY){
                g2.drawImage(tile[tileNum].image, screenX, screenY, null);
            }
                 
            
            worldCol++;
            
            if(worldCol == gp.maxWorldCol){
                worldCol = 0;
                worldRow++;
            }
        }
    }
    
    //---------------------------------------------------//        
}