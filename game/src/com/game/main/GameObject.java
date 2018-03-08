package com.game.main;

import java.awt.*;

public abstract class GameObject {
    protected int x, y;
    protected ID id;
    protected float velX = 0, velY = 0;
    protected Handler handler;

    public GameObject(int x, int y, ID id, Handler handler){
        this.x = x;
        this.y = y;
        this.id = id;
        this.handler = handler;
    }

    public abstract void tick();

    public abstract void render(Graphics g);

    public abstract Rectangle getBounds();

    public void setY(){
        this.y = y;
    }

    public int getX(){
        return x;
    }

    public void setX(int x){
        this.x = x;
    }

    public int getY(){
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public ID getId(){
        return id;
    }

    public void setId(ID id) {
        this.id = id;
    }

    public float getVelX() {
        return velX;
    }

    public void setVelX(float velX) {
        this.velX = velX;
    }

    public float getVelY() {
        return velY;
    }

    public void setVelY(float velY) {
        this.velY = velY;
    }
}
