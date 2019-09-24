
import org.newdawn.slick.*;
import org.newdawn.slick.geom.Rectangle;
import java.awt.Font;

public class Terrain extends BasicGame {
    SpriteSheet grass;
   

    public Terrain(String title) {
        super(title);
    }

    public void init(GameContainer gc) throws SlickException {
        grass = new SpriteSheet("data/grass.png",32,32);
    }

    public void update(GameContainer gc, int i) throws SlickException {

    }
    
    public void drawGrid(Graphics g){
        g.setColor(new Color(200,200,200));
        TrueTypeFont ttf = new TrueTypeFont(new Font("Arial",0,10),true);
        for (int i = 0; i < 32 * 20; i+=32) {
            for (int j = 0; j < 32 * 15; j+=32) {
                Rectangle box = new Rectangle(i, j, 32, 32);
                g.draw(box);
                ttf.drawString(i+3, j+3, "" + i, Color.white);
                ttf.drawString(i+3, j+12, "" + j, Color.white);
            }
        }
    }
    
    public void drawTerrain(){
        //480 = 15 rows of 32 pixels
        int yloc=0; //goes up 32 for each for
        grass.startUse();
        //top grass
        Image lawn = grass.getSprite(0, 0);
        Image path = grass.getSprite(1, 2);
        
        //rows 1 to 5
        for (int x = 0; x < 640; x+=32) {
            for (int y = 0; y < 160; y+=32) {
               if(x==320 || x==352)
                   path.draw(x,y);
               else
                lawn.draw(x,y);
            }
        }
        //row 6
        yloc=160;
        for (int i = 0; i < 640; i+=32) {
            path.draw(i,yloc);
        }
        //row 7
        yloc+=32;
        for (int i = 0; i < 640; i+=32) {
            path.draw(i,yloc);
        }
        //row 8-15
        yloc+=32;
         for (int x = 0; x < 640; x+=32) {
            for (int y = yloc; y < 480; y+=32) {
               if(x==96 || x==128 || x==512 || x==544)
                   path.draw(x,y);
               else
                lawn.draw(x,y);
            }
        }
        grass.endUse();
    }

    public void render(GameContainer gc, Graphics g) throws SlickException {
       
        drawTerrain();
         drawGrid(g);
    }

    public static void main(String args[]) throws SlickException {
        Terrain game = new Terrain("Testing Game");
        AppGameContainer app = new AppGameContainer(game);
        app.setDisplayMode(640,480, false);
        app.setShowFPS(false);
        app.setTargetFrameRate(100);
        app.start();
    }

}
