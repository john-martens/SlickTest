
import org.newdawn.slick.*;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.geom.Shape;

public class KeyMouseTest extends BasicGame {
    Shape redbox;
    int rx=10, ry=10;
    Color boxColor = Color.red;

    public KeyMouseTest(String title) {
        super(title);
    }

    public void init(GameContainer gc) throws SlickException {
        redbox = new Rectangle(100,100,20,20);
    }

    public void update(GameContainer gc, int i) throws SlickException {
        Input in = gc.getInput();
        if(in.isKeyDown(Input.KEY_RIGHT)) rx++;
        if(in.isKeyDown(Input.KEY_LEFT)) rx--;
        if(in.isKeyDown(Input.KEY_DOWN)) ry++;
        if(in.isKeyDown(Input.KEY_UP)) ry--;

        if(in.isKeyPressed(Input.KEY_SPACE))
            movebox();
        //set red box to its x-y
        redbox.setX(rx);
        redbox.setY(ry);
        
        //get the mouse co-ordinated
        int mouseX = in.getMouseX(), mouseY = in.getMouseY();
        //if left button clicked and mouse is on the shape, change color
        if(in.isMousePressed(Input.MOUSE_LEFT_BUTTON) && redbox.contains(mouseX, mouseY)){
            changeColor();
        }
    }
    public void changeColor(){
        //get randomg r-g-b values
        int r=(int)(Math.random() * 256), g=(int)(Math.random() * 256), b=(int)(Math.random() * 256);
        //set Color
        boxColor = new Color(r, g, b);
    }
    
    public void movebox(){
        rx = (int)(Math.random() * 780 + 1);
        ry = (int)(Math.random() * 580 + 1);    
        //reset Color
        boxColor = Color.red;
    }

    public void render(GameContainer gc, Graphics g) throws SlickException {
        g.setColor(boxColor);
        g.fill(redbox);
    }

    public static void main(String args[]) throws SlickException {
        KeyMouseTest game = new KeyMouseTest("Testing Game");
        AppGameContainer app = new AppGameContainer(game);
        app.setDisplayMode(800, 600, false);
        app.setShowFPS(false);
        app.setTargetFrameRate(500);
        app.start();
    }

}
