import java.util.ArrayList;
import org.newdawn.slick.*;
import org.newdawn.slick.geom.Rectangle;

public class AstroidBlaster extends BasicGame {
    //list of hitboxes for each astroid
    ArrayList<Rectangle> roids = new ArrayList<Rectangle>();
    //list of x-y directions for each hitbox / asteroid
    ArrayList<int[]> dir = new ArrayList<int[]>();
    Image astroid;
    String status;

    public AstroidBlaster(String title) {
        super(title);
    }

    public void init(GameContainer gc) throws SlickException {
        astroid = new Image("data/astroid.png");
        //create 10 rectangles at random locations
        for (int i = 0; i < 10; i++) {
            int rx = (int) (Math.random() * (800 - astroid.getWidth()));
            int ry = (int) (Math.random() * (600 - astroid.getHeight()));
            roids.add(new Rectangle(rx, ry, astroid.getWidth(), astroid.getHeight()));
            //create 10 diffrent x-y directions from -2 to +2
            rx = (int) (Math.random() * 5 - 2);
            ry = (int) (Math.random() * 5 - 2);
            //do not get 0 or it wont move, so reassign it
            rx = rx == 0 ? (int) (Math.random() * 2 + 1) : rx;
            ry = ry == 0 ? -(int) (Math.random() * 2 + 1) : ry;
            dir.add(new int[]{rx, ry});
        }
        status = "Asteroids to zap: " + roids.size();
    }

    public void update(GameContainer gc, int i) throws SlickException {
        //move astroid
        for (int j = 0; j < roids.size(); j++) {
            Rectangle r = roids.get(j);
            int loc[] = dir.get(j);
            r.setX(r.getX() + loc[0]);
            r.setY(r.getY() + loc[1]);
            //chnage location if asteroid box hits a wall
            loc[0] = r.getX() <= 0 || r.getX() > 800 - astroid.getWidth() ? -loc[0] : loc[0];
            loc[1] = r.getY() <= 0 || r.getY() > 600 - astroid.getHeight() ? -loc[1] : loc[1];
            //put updated rectangle and direction back into array list
            roids.set(j, r);
            dir.set(j, loc);
        }
        
        //detect mouse press and collision with asteroid
        Input in = gc.getInput();
        //if mouse is down ...
        if (in.isMousePressed(Input.MOUSE_LEFT_BUTTON)) {
            int mx = in.getMouseX(), my = in.getMouseY();
            //go thru rectangles to see if it hit one
            for (int j = 0; j < roids.size(); j++) {
                Rectangle r = roids.get(j);
                //if hit, remove rectangle and direction
                if (r.contains(mx, my)) {
                    roids.remove(j);
                    dir.remove(j);
                }
            }
        }
    }

    public void render(GameContainer gc, Graphics g) throws SlickException {
        //draw asteroid at each rectangle x-y location
        for (int i = 0; i < roids.size(); i++) {
            Rectangle r = roids.get(i);
            astroid.draw(r.getX(), r.getY());
        }
        //update status
        status = roids.size() == 0? "Game Over" : "Asteroids to zap: " + roids.size();
        g.drawString(status, 10, 10);
    }

    
    public static void main(String args[]) throws SlickException {
        AstroidBlaster game = new AstroidBlaster("Testing Game");
        AppGameContainer app = new AppGameContainer(game);
        app.setDisplayMode(800, 600, false);
        app.setShowFPS(false);
        app.setTargetFrameRate(100);
        app.start();
    }

}
