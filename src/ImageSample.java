
import org.newdawn.slick.*;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.geom.Shape;

public class ImageSample extends BasicGame {
    private Image andy;
    //hitbox for image
    private Shape andysbox, statusbox;
    private boolean istouching=false;
    private String status = "Mouse touching? " + istouching;
    
    public ImageSample(String title) {
        super(title);
    }

    public void init(GameContainer gc) throws SlickException {
        andy = new Image("data/android.PNG");
        //make hitbox for image
        andysbox = new Rectangle(100,100, andy.getWidth(), andy.getHeight());
        //only draw this when mouse is touching
        statusbox = new Rectangle(80,80,andy.getWidth()+40, andy.getHeight()+40);
    }

    public void update(GameContainer gc, int i) throws SlickException {
        //get Mouse x and Mouse y
        int mx = gc.getInput().getMouseX();
        int my = gc.getInput().getMouseY();
        //see if mouse is touching image box
        istouching = andysbox.contains(mx, my);   
        //update status
        status = "Mouse touching " + istouching;
    }

    public void render(GameContainer gc, Graphics g) throws SlickException {
        //draw image using box x-y location
        andy.draw(andysbox.getX(), andysbox.getY());
        //print status on screen
        g.setColor(Color.white);
        g.drawString(status, 100, 80);
        //draw red status box if mouse is touching image
        if(istouching){
            g.setColor(Color.red);
            g.draw(statusbox);
        }
    }

    public static void main(String args[]) throws SlickException {
        ImageSample game = new ImageSample("Testing Game");
        AppGameContainer app = new AppGameContainer(game);
        app.setDisplayMode(800, 600, false);
        app.setShowFPS(false);
        app.setTargetFrameRate(500);
        app.start();
    }

}
