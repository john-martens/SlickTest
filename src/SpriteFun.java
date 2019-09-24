
import java.util.ArrayList;
import org.newdawn.slick.*;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.geom.Shape;

public class SpriteFun extends BasicGame {

    SpriteSheet gsprite;
    SpriteSheet grass;
    Animation ani[] = new Animation[4];
    Image walk[][] = new Image[4][4];
    Image stopImage[] = new Image[4];
    int guyx = 0, guyy = 160;
    int dir = 3;  //0=D, 1=L, 2=U, 3=R
    boolean stop = true, gridOn = false, roadOn = false;
    int timer = 0;
    int togo = 10;
    Guy guy;
    ArrayList<GameObject> diamonds = new ArrayList<GameObject>();
    
    GameObject house;
    GameObject ghost, ghost2;
    //barriers
    ArrayList<Rectangle> barriers = new ArrayList<Rectangle>();

    public SpriteFun(String title) {
        super(title);
    }

    public void init(GameContainer gc) throws SlickException {
        grass = new SpriteSheet("data/grass.png", 32, 32);
        guy = new Guy(0, 170);
        ghost = new Ghost(100, 170, "data/ghost.png");
        ghost2 = new Ghost(700, 170, "data/ghost.png");
        house = new BlueDiamond(280,400,"data/house.png");
        barriers.add(new Rectangle(0, 0, 320, 160));
        barriers.add(new Rectangle(384, 0, 32 * 13, 160));
        barriers.add(new Rectangle(0, 224, 32 * 3, 32 * 13));
        barriers.add(new Rectangle(160, 224, 32 * 11, 32 * 13));  //remove
        barriers.add(new Rectangle(576, 224, 32 * 7, 32 * 13));
        for (int i = 0; i < 10; i++) {
            GameObject d = new BlueDiamond(0, 0, "data/diamond.png");
            d.move(barriers);
            diamonds.add(d);
        }

    }

    public void drawGrid(Graphics g) {
        g.setColor(new Color(200, 200, 200));
        TrueTypeFont ttf = new TrueTypeFont(new java.awt.Font("Arial", 0, 10), true);
        for (int i = 0; i < 32 * 25; i += 32) {
            for (int j = 0; j < 32 * 20; j += 32) {
                Rectangle box = new Rectangle(i, j, 32, 32);
                g.draw(box);
                ttf.drawString(i + 3, j + 3, "" + i, Color.white);
                ttf.drawString(i + 3, j + 12, "" + j, Color.white);
            }
        }
    }

    public void update(GameContainer gc, int i) throws SlickException {
        Input in = gc.getInput();
        guy.move(in, barriers);
        timer++;

        for (int j = 0; j < diamonds.size(); j++) {
            if (guy.isHitting(diamonds.get(j))) {
                diamonds.remove(j);
                togo--;
                if (togo == 0) {
                    roadOn = true;
                    barriers.remove(3);
                    barriers.add(new Rectangle(160, 224, 32 * 11, 32 * 5 -3));
                    barriers.add(new Rectangle(320, 384, 32 * 6, 32 * 8));
                    barriers.add(new Rectangle(256, 448, 32 * 2, 32 * 6));
                    barriers.add(new Rectangle(160, 416+3, 32 * 3, 32 * 7-3));
                    togo=-1;
                }
            }
        }
        
        if(guy.isHitting(ghost) || guy.isHitting(ghost2))
            System.out.println("Game Over");
        
        if(guy.isHitting(house))
            System.out.println("You WIN!!");
        
        if (timer == 200) {
            for (GameObject bd : diamonds) {
                bd.move(barriers);
            }
            timer = 0;
        }
        if (timer % 2 == 0) {
            ghost.move(barriers);
            ghost2.move(barriers);
        }
    }

    public void drawTerrain() {
        //480 = 15 rows of 32 pixels
        int yloc = 0; //goes up 32 for each for
        grass.startUse();
        //top grass
        Image lawn = grass.getSprite(0, 0);
        Image path = grass.getSprite(1, 2);
        Image stone = grass.getSprite(6, 5);

        //rows 1 to 5
        for (int x = 0; x < 800; x += 32) {
            for (int y = 0; y < 160; y += 32) {
                if (x == 320 || x == 352) {
                    path.draw(x, y);
                } else {
                    lawn.draw(x, y);
                }
            }
        }

        //row 6
        yloc = 160;
        for (int i = 0; i < 800; i += 32) {
            path.draw(i, yloc);
        }
        //row 7
        yloc += 32;
        for (int i = 0; i < 800; i += 32) {
            path.draw(i, yloc);
        }
        //row 8-15
        yloc += 32;
        for (int x = 0; x < 800; x += 32) {
            for (int y = yloc; y < 640; y += 32) {
                if (x == 96 || x == 128 || x == 512 || x == 544) {
                    path.draw(x, y);
                } else {
                    lawn.draw(x, y);
                }
            }
        }
        if (roadOn) {
            for (int i = 160; i <= 288; i += 32) {
                stone.draw(i, 384);
            }
            stone.draw(256, 416);
            stone.draw(288, 416);

        }

        grass.endUse();
    }

    public void render(GameContainer gc, Graphics g) throws SlickException {
        drawTerrain();
        guy.draw(g);
        for (GameObject dm : diamonds) {
            dm.draw();
        }
        if(diamonds.size()==0)
            house.draw();
        ghost.draw();
        ghost2.draw();
        if (gridOn) {
            drawGrid(g);
        }
        g.setColor(Color.yellow);

        //   for (Rectangle b : barriers) {
        //       g.draw(b);
        //   }
    }

    public static void main(String args[]) throws SlickException {
        SpriteFun game = new SpriteFun("Testing Game");
        AppGameContainer app = new AppGameContainer(game);
        app.setDisplayMode(800, 640, false);
        app.setShowFPS(false);
        app.setTargetFrameRate(200);
        app.start();
    }

}
