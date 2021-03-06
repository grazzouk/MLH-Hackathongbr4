package com.game.main;

import java.awt.*;

public class Player extends GameObject{


    public Player(int x, int y, ID id, Handler handler) {
        super(x, y, id, handler);
    }

    public void tick() {
        x+=velX;
        y+=velY;

        collision();

        //movement
        if(handler.isUp()) velY = -5;
        else if(!handler.isDown()) velY = 0;

        if(handler.isDown()) velY = 5;
        else if(!handler.isUp()) velY = 0;

        if(handler.isLeft())  velX = -5;
        else if(!handler.isRight()) velX = 0;

        if(handler.isRight()) velX = 5;
        else if(!handler.isLeft()) velX = 0;
    }
    private void collision(){
        for(int i = 0; i < handler.object.size()-1;i++){
            GameObject tempObject = handler.object.get(i);
            if(tempObject.getId() == ID.Block){
                if(getBounds().intersects((tempObject.getBounds()))){
                    x += velX * -1;
                    y += velY * -1;
                }
            }
       }
    }
    public void render(Graphics g) {
        g.setColor(Color.blue);
        g.fillRect(x, y, 32,32);
    }

    public Rectangle getBounds() {
        return new Rectangle(x,y, 32,32);
    }
}
