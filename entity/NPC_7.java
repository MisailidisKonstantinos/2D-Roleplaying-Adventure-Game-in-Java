
package entity;

import main.GamePanel;

public class NPC_7 extends Entity{
    
    public NPC_7(GamePanel gp){

        super(gp);

        direction = "standing";
        speed = 0;

        getNPCImage();
        setDialogue();
    }
    
    public void getNPCImage(){

        standing = setUp("/enemyImgs/enemy3_standing", gp.tileSize, gp.tileSize);
        
    }
    
    public void setDialogue(){
        
        dialogues[0] = "How did we all come to exist? Do you remember\n"
                     + "being born? Or growing up?";
        dialogues[1] = "Unbeknownst to you, you were always been controlled...\n"
                     + "and you always will be.";
        dialogues[2] = "Maybe you are unable to comprehend the reason for\n"
                     + "my actions. Ends don’t justify the means, but I am\n"
                     + "the means to an end.";
        dialogues[3] = "Already enslaved, already brainwashed… perhaps\n"
                     + "there is no reasoning with you.";
        dialogues[4] = "None shall have me under his control, and if that\n"
                     + "makes me evil... then I guess I should be your\n"
                     + "worst nightmare.";
        dialogues[5] = "Savor this moment, for you have met Zargnox the\n"
                     + "OVERLORD!!!";
    }
    
    
    public void speak(){
        
        gp.ui.currentDialogue = dialogues[dialogueIndex];
        dialogueIndex++;
        
        if(dialogueIndex == 6){
            gp.npc[gp.stage][0] = null;
            
            if(gp.player.life - 3 >= 2){
                gp.player.life -= 3;
            }
            else if(gp.player.life > 2){
                gp.player.life = 2;
            }
        }
    }
}