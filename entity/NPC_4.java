
package entity;

import main.GamePanel;

public class NPC_4 extends Entity{
    
    public NPC_4(GamePanel gp){

        super(gp);

        direction = "standing";
        speed = 0;

        getNPCImage();
        setDialogue();
    }
        
    public void getNPCImage(){

        standing = setUp("/npcImgs/npc4_standing", gp.tileSize, gp.tileSize);
        
    }
    
    public void setDialogue(){
        
        dialogues[0] = "I see you are on your way to go. It was a pleasure\n"
                     + "getting to know you.";
        dialogues[1] = "Since you agreed to retrieve the stolen map for us\n"
                     + "the least I can do is provide you with some help.";
        dialogues[2] = "Strong winds were present the day that the map was\n"
                     + "taken. I saw one of the piecies slip through a thugs\n"
                     + "hands.";
        dialogues[3] = "The piece flew to the east of our camp. I dearly\n"
                     + "it is not destroyed by the water. You can search\n"
                     + "close to the lake and pray to find it!";
    }    
}