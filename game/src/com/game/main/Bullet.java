package com.game.main;

import java.awt.*;

public class Bullet extends GameObject{

    public Bullet(int x, int y, ID id,Handler handler, int mx, int my) {
        super(x, y, id, handler);
        velX = (mx - x)/8;
        velY = (my - y)/8;
    }

    public void tick() {
        x += velX;
        y += velY;

        for(int i = 0; i < handler.object.size(); i++){
            GameObject tempObject = handler.object.get(i);

            if(tempObject.getId() == ID.Block){
                if(getBounds().intersects(tempObject.getBounds())){
                    handler.removeObject(this);
                }
            }
        }

    }


    public void render(Graphics g) {
        g.setColor(Color.yellow);
        g.fillOval(x, y, 5, 5);
    }


    public Rectangle getBounds() {
        return new Rectangle(x, y, 5, 5);
    }
}
