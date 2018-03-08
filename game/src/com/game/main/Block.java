package com.game.main;
import java.awt.*;

public class Block extends GameObject{

    public Block(int x, int y, ID id, Handler handler){
        super(x, y, id, handler);
    }

    public void tick(){

    }

    public void render(Graphics g){
        g.setColor(Color.white);
        g.fillRect(x, y, 32, 32);
    }

    public Rectangle getBounds(){
        return new Rectangle(x, y, 32, 32);
    }
}
