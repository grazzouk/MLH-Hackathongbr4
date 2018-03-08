package com.game.main;

import java.awt.*;
import java.util.Random;

public class Enemy extends GameObject {
    Random r = new Random();
    int choose = 0;
    int hp = 100;

    public Enemy(int x, int y, ID id, Handler handler){
        super(x, y, id, handler);
    }

    public void tick(){
        x+= velX;
        y += velY;

        choose = r.nextInt(10);
        if (handler != null){
            for(GameObject tempObject: handler.object){
                //GameObject tempObject = handler.object.get(i);
            if (tempObject.getId() == ID.Block) {
                if (getBounds().intersects(tempObject.getBounds())) {
                    x += (velX * 2) * -1;
                    y += (velY * 2) * -1;
                    velX *= -1;
                    velY *= -1;
                } else if (choose == 0) {
                    velX = (r.nextInt(4 - -4) + -4);
                    velY = (r.nextInt(4 - -4) + -4);
                }
            }
            /*
            if(tempObject.getId() == ID.Bullet) {
                handler.removeObject(tempObject);
            }*/
        }

        }

    }

    public void render(Graphics g){
        g.setColor(Color.red);
        g.fillRect(x, y, 32, 48);
    }

    @Override
    public Rectangle getBounds(){
        return new Rectangle(x, y, 32, 32);
    }

}