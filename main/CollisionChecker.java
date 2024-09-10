
package main;

import entity.Entity;

public class CollisionChecker {
    GamePanel gp;
    
    public CollisionChecker(GamePanel gp){
        this.gp = gp;
    }
    
    //--------------------------CHECK TILE----------------------------//
    
    public void checkTile(Entity entity){
        
        int entityLeftWorldX = entity.worldX + entity.solidArea.x;
        int entityRightWorldX = entity.worldX + entity.solidArea.x + entity.solidArea.width;
        int entityTopWorldY = entity.worldY + entity.solidArea.y;
        int entityBottomWorldY = entity.worldY + entity.solidArea.y + entity.solidArea.height;
        
        int entityLeftCol = entityLeftWorldX / gp.tileSize;
        int entityRightCol = entityRightWorldX / gp.tileSize;
        int entityTopRow = entityTopWorldY / gp.tileSize;
        int entityBottomRow = entityBottomWorldY / gp.tileSize;
        
        int tileNum1, tileNum2;
        
        switch(entity.direction){
            case "up" :
                entityTopRow = (entityTopWorldY - entity.speed) / gp.tileSize;
                tileNum1 = gp.tileM.mapTileNum[gp.stage][entityLeftCol][entityTopRow];
                tileNum2 = gp.tileM.mapTileNum[gp.stage][entityRightCol][entityTopRow];
                
                if(gp.tileM.tile[tileNum1].collision == true || gp.tileM.tile[tileNum2].collision == true){
                    entity.collisionOn = true;
                }                
                break;
            case "down" :
                entityBottomRow = (entityBottomWorldY + entity.speed) / gp.tileSize;
                tileNum1 = gp.tileM.mapTileNum[gp.stage][entityLeftCol][entityBottomRow];
                tileNum2 = gp.tileM.mapTileNum[gp.stage][entityRightCol][entityBottomRow];
                
                if(gp.tileM.tile[tileNum1].collision == true || gp.tileM.tile[tileNum2].collision == true){
                    entity.collisionOn = true;
                }
                break;
            case "left" :
                entityLeftCol = (entityLeftWorldX - entity.speed) / gp.tileSize;
                tileNum1 = gp.tileM.mapTileNum[gp.stage][entityLeftCol][entityTopRow];
                tileNum2 = gp.tileM.mapTileNum[gp.stage][entityLeftCol][entityBottomRow];
                
                if(gp.tileM.tile[tileNum1].collision == true || gp.tileM.tile[tileNum2].collision == true){
                    entity.collisionOn = true;
                }
                break;
            case "right" :
                entityRightCol = (entityRightWorldX + entity.speed) / gp.tileSize;
                tileNum1 = gp.tileM.mapTileNum[gp.stage][entityRightCol][entityTopRow];
                tileNum2 = gp.tileM.mapTileNum[gp.stage][entityRightCol][entityBottomRow];
                
                if(gp.tileM.tile[tileNum1].collision == true || gp.tileM.tile[tileNum2].collision == true){
                    entity.collisionOn = true;
                }
                break;
        }        
    }
    
    //----------------------------------------------------------------//
    //-------------------------CHECK OBJECT---------------------------//
    
    public int checkObject(Entity entity, boolean player){
        
        int index = 999;
        
        for(int i = 0; i < gp.obj[1].length; i++){
            
            if(gp.obj[gp.stage][i] != null){
                //Get entity's solid area position
                entity.solidArea.x = entity.worldX + entity.solidArea.x;
                entity.solidArea.y = entity.worldY + entity.solidArea.y;
                
                //Get object's solid area position
                gp.obj[gp.stage][i].solidArea.x = gp.obj[gp.stage][i].worldX + gp.obj[gp.stage][i].solidArea.x; //The last part does not add anything since it's 0. But the code will still work if we decide to change it
                gp.obj[gp.stage][i].solidArea.y = gp.obj[gp.stage][i].worldY + gp.obj[gp.stage][i].solidArea.y;
                
                switch(entity.direction){
                    case "up" :
                        entity.solidArea.y -= entity.speed;
                        break;
                    case "down" :
                        entity.solidArea.y += entity.speed;
                        break;
                    case "left" :
                        entity.solidArea.x -= entity.speed;
                        break;
                    case "right" :
                        entity.solidArea.x += entity.speed;                        
                        break;
                }
                
                if(entity.solidArea.intersects(gp.obj[gp.stage][i].solidArea)){
                    if(gp.obj[gp.stage][i].collision == true){
                        entity.collisionOn = true;
                    }
                    if(player == true){
                        index = i;
                    }
                }
                
                //Reset
                entity.solidArea.x = entity.solidAreaDefaultX;
                entity.solidArea.y = entity.solidAreaDefaultY;
                
                gp.obj[gp.stage][i].solidArea.x = gp.obj[gp.stage][i].solidAreaDefaultX;
                gp.obj[gp.stage][i].solidArea.y = gp.obj[gp.stage][i].solidAreaDefaultY;                
            }
        }
        
        return index;
    }
    
    //----------------------------------------------------------------//
    //-------------------------CHECK ENTITY---------------------------//
    
    public int checkEntity(Entity entity, Entity[][] target){
        
        int index = 999;
        
        for(int i = 0; i < target[1].length; i++){
            
            if(target[gp.stage][i] != null){
                //Get entity's solid area position
                entity.solidArea.x = entity.worldX + entity.solidArea.x;
                entity.solidArea.y = entity.worldY + entity.solidArea.y;
                
                //Get object's solid area position
                target[gp.stage][i].solidArea.x = target[gp.stage][i].worldX + target[gp.stage][i].solidArea.x; //The last part does not add anything since it's 0. But the code will still work if we decide to change it
                target[gp.stage][i].solidArea.y = target[gp.stage][i].worldY + target[gp.stage][i].solidArea.y;
                
                switch(entity.direction){
                    case "up" :
                        entity.solidArea.y -= entity.speed;                        
                        break;
                    case "down" :
                        entity.solidArea.y += entity.speed;
                        break;
                    case "left" :
                        entity.solidArea.x -= entity.speed;
                        break;
                    case "right" :
                        entity.solidArea.x += entity.speed;                        
                        break;
                }
                
                if(entity.solidArea.intersects(target[gp.stage][i].solidArea)){
                     //this happens so an npc will not consider itself for collision
                    if(target[gp.stage][i] != entity){
                        entity.collisionOn = true;                            
                        index = i;
                    }                    
                }
                
                //Reset
                entity.solidArea.x = entity.solidAreaDefaultX;
                entity.solidArea.y = entity.solidAreaDefaultY;
                
                target[gp.stage][i].solidArea.x = target[gp.stage][i].solidAreaDefaultX;
                target[gp.stage][i].solidArea.y = target[gp.stage][i].solidAreaDefaultY;                
            }
        }
        
        return index;
    }
    
    //----------------------------------------------------------------//
    //-------------------------CHECK PLAYER---------------------------//
    
    public boolean checkPlayer(Entity entity){ 
        //checks if NPCs or enemies collide with the player     
        boolean contactPlayer = false;
            //Get entity's solid area position
            entity.solidArea.x = entity.worldX + entity.solidArea.x;
            entity.solidArea.y = entity.worldY + entity.solidArea.y;

            //Get object's solid area position
            gp.player.solidArea.x = gp.player.worldX + gp.player.solidArea.x; //The last part does not add anything since it's 0. But the code will still work if we decide to change it
            gp.player.solidArea.y = gp.player.worldY +gp.player.solidArea.y;

            switch(entity.direction){ 
                case "up" :
                    entity.solidArea.y -= entity.speed;                    
                    break;
                case "down" :
                    entity.solidArea.y += entity.speed;
                    break;
                case "left" :
                    entity.solidArea.x -= entity.speed;
                    break;
                case "right" :
                    entity.solidArea.x += entity.speed;
                    break;
            }
            
            if(entity.solidArea.intersects(gp.player.solidArea)){
                entity.collisionOn = true;
                contactPlayer = true;
            }
            
            //Reset
            entity.solidArea.x = entity.solidAreaDefaultX;
            entity.solidArea.y = entity.solidAreaDefaultY;

            gp.player.solidArea.x = gp.player.solidAreaDefaultX;
            gp.player.solidArea.y = gp.player.solidAreaDefaultY;
            
            return contactPlayer;
    }
}