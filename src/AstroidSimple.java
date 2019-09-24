import java.util.ArrayList;
import org.newdawn.slick.*;
import org.newdawn.slick.geom.Rectangle;

public class AstroidSimple extends BasicGame {
    //list of hitboxes for each astroid
    ArrayList<Rectangle> roids = new ArrayList<Rectangle>();
    Image astroid;
  
    public AstroidSimple(String title) {
        super(title);
    }

    public void init(GameContainer gc) throws SlickException {
        astroid = new Image("data/astroid.png");
        //create 10 rectangles at random locations
        for (int i = 0; i < 10; i++) {
            int rx = (int) (Math.random() * (800 - astroid.getWidth()));
            int ry = (int) (Math.random() * (600 - astroid.getHeight()));
            roids.add(new Rectangle(rx, ry, astroid.getWidth(), astroid.getHeight()));
        }
        
      
    }

    public void update(GameContainer gc, int i) throws SlickException {
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
    }

    
    public static void main(String args[]) throws SlickException {
        AstroidSimple game = new AstroidSimple("Testing Game");
        AppGameContainer app = new AppGameContainer(game);
        app.setDisplayMode(800, 600, false);
        app.setShowFPS(false);
        app.setTargetFrameRate(100);
        app.start();
    }

}
