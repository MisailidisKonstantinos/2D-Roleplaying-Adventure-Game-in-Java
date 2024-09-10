
package object;

import entity.Entity;
import main.GamePanel;

public class OBJ_Sign extends Entity{
    
    public OBJ_Sign(GamePanel gp){
        
        super(gp);
        
        name = "Sign";
        collision = true;
        standing = setUp("/objects/sign", gp.tileSize, gp.tileSize);
        
        setDialogue();
    }
    
    public void setDialogue(){
        
        dialogues[0] = "Do not go any further for this is where our world ends.";
        dialogues[1] = "No one knows what exists outside the boundaries of\nour "
                     + "planet, if anything exists at all.";
        dialogues[2] = "I can only see endless void, emptiness that will end\n"
                     + "this execution upon stepping over it!";
        dialogues[3] = "Do… not… go over there…";
        dialogues[4] = "                                      - MK";
    }
    
    public void speak(){     
        
        gp.ui.currentDialogue = dialogues[dialogueIndex];
        
        if(dialogueIndex < 4){
            dialogueIndex++;
        }        
    }    
}