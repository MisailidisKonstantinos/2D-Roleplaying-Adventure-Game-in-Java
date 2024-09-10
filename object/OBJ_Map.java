
package object;

import entity.Entity;
import main.GamePanel;

public class OBJ_Map extends Entity{
    
    public OBJ_Map(GamePanel gp){
        
        super(gp);
        
        name = "Map Fragment";
        standing = setUp("/objects/map", gp.tileSize, gp.tileSize);
    }    
}