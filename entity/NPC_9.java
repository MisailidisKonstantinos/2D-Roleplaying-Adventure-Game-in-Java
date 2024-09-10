
package entity;

import main.GamePanel;

public class NPC_9 extends Entity{
    
    public NPC_9(GamePanel gp){
        
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
        
        dialogues[0] = "The hour of his demise is nigh!";
        dialogues[1] = "This is your fight my brother. I know that I will see\n"
                     + "you again!";
        dialogues[2] = "Good luck!";
    }
    
    public void speak(){
        
        if(dialogues[dialogueIndex] == null){
            dialogueIndex = 0;
        }
        
        gp.ui.currentDialogue = dialogues[dialogueIndex];
        dialogueIndex++;
        
        if(dialogueIndex == 3){
            gp.npc[gp.stage][0] = null;
        }
    }
}