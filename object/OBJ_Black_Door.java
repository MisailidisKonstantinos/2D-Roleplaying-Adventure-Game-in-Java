
package object;

import entity.Entity;
import main.GamePanel;

public class OBJ_Black_Door extends Entity{
    
    public OBJ_Black_Door(GamePanel gp){
        
        super(gp);

        collision = true;
        name = "Black Key";
        standing = setUp("/objects/black_door", gp.tileSize, gp.tileSize);
    }
}