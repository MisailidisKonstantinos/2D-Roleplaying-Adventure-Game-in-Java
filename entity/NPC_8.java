
package entity;

import main.GamePanel;

public class NPC_8 extends Entity{
    
    public NPC_8(GamePanel gp){
        
        super(gp);
        
        direction = "standing";
        speed = 0;
        
        getNPCImage();
        setDialogue();
    }
        
    public void getNPCImage(){
        
        standing = setUp("/npcImgs/npc1_standing", gp.tileSize, gp.tileSize);
        
    }
    
    public void setDialogue(){
        
        dialogues[0] = "We made it!!";
        dialogues[1] = "At the eastern side of the forest lies his arena... his\n"
                     + "base of operations.";
        dialogues[2] = "It is said that in order to enter you need to collect the\n"
                     + "Black Key. But be careful for this area is guarded!\n";
        dialogues[3] = "Find the key and kill every guard that you find. If you\n"
                     + "succeed Zargnox will be next!\n";
    }
}