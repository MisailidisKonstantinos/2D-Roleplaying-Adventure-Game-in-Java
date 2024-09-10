
package object;

import entity.Entity;
import main.GamePanel;

public class OBJ_Heart extends Entity{

    
    public OBJ_Heart(GamePanel gp){
        
        super(gp);
        
        collision = true;
        
        name = "Heart";
        image = setUp("/objects/Full Heart", gp.tileSize, gp.tileSize);
        image2 = setUp("/objects/Half Heart", gp.tileSize, gp.tileSize);
        image3 = setUp("/objects/Empty Heart", gp.tileSize, gp.tileSize);               
    }
}