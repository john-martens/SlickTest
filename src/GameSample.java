import org.newdawn.slick.*;
import org.newdawn.slick.geom.Circle;
import org.newdawn.slick.geom.Polygon;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.geom.Shape;
import java.awt.Font;

public class GameSample extends BasicGame {
    private Shape c1, c2;
    private Shape poly;
    private Image andy;
    private String msg="Not Colliding";
    TrueTypeFont ttf;
    Font f;
    float ax, ay;

    public GameSample(String title) {
        super(title);
    }

    @Override
    public void init(GameContainer gc) throws SlickException {
        f = new Font("Arial",0,30);
        ttf = new TrueTypeFont(f, true);
        c1 = new Circle(50,50,10);
        c2 = new Rectangle(50,50,100,25);
        //                    x1,y1    x2,y2  x3,y3   x4,y4   x5,y5
        float polypoints[] = {250,50, 270,85, 300,80, 315,40, 285,10};
        poly = new Polygon(polypoints);
        andy = new Image("data/android.PNG");
    }

    @Override
    public void update(GameContainer gc, int i) throws SlickException {
        //move circle with mouse
        
        c1.setCenterX(gc.getInput().getMouseX());
        c1.setCenterY(gc.getInput().getMouseY());
        
        ax = (gc.getInput().getMouseX());
        ay = (gc.getInput().getMouseY());
        
        if(poly.intersects(c1))
            msg = "Colliding";
        else if(poly.contains(c1))
            msg = "In all the way";
        else
            msg = "Not Colliding";
    }

    @Override
    public void render(GameContainer gc, Graphics g) throws SlickException {
        g.setColor(Color.yellow);
        //draw message status
        ttf.drawString(200, 120, msg, Color.yellow);
        
        g.setLineWidth(1);
        g.fill(c1);
       
        g.setColor(Color.red);
        g.fill(c2);
        g.setLineWidth(5);
        g.draw(poly);
        andy.draw(ax - andy.getWidth()/2, ay - andy.getHeight()/2);
    }

    public static void main(String[] args) throws SlickException {
        GameSample game = new GameSample("Testing Game");
        AppGameContainer app = new AppGameContainer(game);
        app.setDisplayMode(800, 600, false);
        app.setShowFPS(false);
        app.setTargetFrameRate(500);
        app.start();
    }

}
