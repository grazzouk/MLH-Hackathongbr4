package com.game.main;

import java.awt.*;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;

public class Game extends Canvas implements Runnable{

    public static final int WIDTH = 1000, HEIGHT = 666;
    private Thread thread;
    private boolean running = false;
    private Handler handler;
    private Camera camera;

    private BufferedImage level = null;

    public Game(){
        new Window(WIDTH, HEIGHT, "game", this);

        handler = new Handler();
        camera = new Camera(0, 0);
        this.addKeyListener(new KeyInput(handler));
        this.addMouseListener(new MouseInput(handler, camera));
        BufferedImageLoader loader = new BufferedImageLoader();
        level = loader.loadImage("arena.png");
        loadLevel(level);


    }

    public synchronized void stop(){
        try {
            thread.join();
            running = false;
        }catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void run(){
        long lastTime = System.nanoTime();
        double amountOfTicks = 60.0;
        double ns = 1000000000 / amountOfTicks;
        double delta = 0;
        double timer = System.currentTimeMillis();
        int frames = 0;
        while(running){
            long now = System.nanoTime();
            delta += (now - lastTime) / ns;
            lastTime = now;
            while(delta >= 1){
                tick();
                delta--;
            }
            if(running)
                render();
            frames++;

            if(System.currentTimeMillis() - timer > 1000){
                timer += 1000;
                System.out.println("FPS: " + frames);
                frames = 0;
            }
        }
        stop();
    }

    private void tick(){
        for(int i = 0; i < handler.object.size(); i++){
            if (handler.object.get(i).getId() == ID.Player){
                camera.tick(handler.object.get(i));
            }
        }
        handler.tick();
    }

    private void render(){
        BufferStrategy bs =this.getBufferStrategy();
        if(bs == null){
            this.createBufferStrategy(3);
            return;
        }
        Graphics g = bs.getDrawGraphics();
        Graphics2D g2d =(Graphics2D) g;
        g.setColor(Color.black);
        g.fillRect(0, 0, WIDTH, HEIGHT);

        g2d.translate(-camera.getX(), -camera.getY());
        handler.render(g);

        g.dispose();
        bs.show();

    }

    public synchronized void start(){
        thread = new Thread(this);
        thread.start();
        running = true;
    }

    public void loadLevel(BufferedImage image){
        int w = image.getWidth();
        int h = image.getHeight();

        for (int xx = 0; xx < w; xx++ ){
            for (int yy = 0; yy < h; yy++){
                int pixel = image.getRGB(xx, yy);
                int red = (pixel >> 16) & 0xff;
                int green = (pixel >> 8) & 0xff;
                int blue = (pixel) & 0xff;
                if (green == 255)  {
                    if(red == 255){
                        if (blue == 255) {
                            handler.addObject(new Block(xx * 32, yy * 32, ID.Block, handler));
                        }
                    }
                }

                else if (blue == 255) {
                    handler.addObject(new Player(xx*32, yy*32, ID.Player, handler));
                }

                else if(red == 255){
                    handler.addObject(new Enemy(xx*32, yy*32, ID.Enemy, handler));
                }

                }



            }
        }



    public static void main(String args[]){
        new Game();
    }

}
