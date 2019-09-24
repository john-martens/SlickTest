
import java.util.ArrayList;
import org.newdawn.slick.*;

public class AstroidObject extends BasicGame {

    ArrayList<Asteroid> rocks = new ArrayList<Asteroid>();
    TrueTypeFont ttf;  //will allow but is being phased out
    java.awt.Font f;
    int t = 0;

    public AstroidObject(String title) {
        super(title);
    }

    public void init(GameContainer gc) throws SlickException {
        Asteroid.setGameSize(800, 600);
        f = new java.awt.Font("Impact", 0, 55);
        ttf = new TrueTypeFont(f, true);
        for (int i = 0; i < 10; i++) {
            int rx = (int) (Math.random() * 750 + 1);
            int ry = (int) (Math.random() * 550 + 1);
            rocks.add(new Asteroid(rx, ry));
        }
        pickRock();
    }

    public void update(GameContainer gc, int i) throws SlickException {
        t++;
        if (t == 500) {
            t = 0;
            if (rocks.size() > 0) {
                int rx = (int) (Math.random() * 750 + 1);
                int ry = (int) (Math.random() * 550 + 1);
                rocks.add(new Asteroid(rx, ry));
            }
        }
        for (Asteroid a : rocks) {
            a.move();
        }
        //when mouse goes down, see if chosen rock was hit
        Input mouse = gc.getInput();
        if (mouse.isMousePressed(Input.MOUSE_LEFT_BUTTON)) {
            int mx = mouse.getMouseX(), my = mouse.getMouseY();
            for (int j = 0; j < rocks.size(); j++) {
                Asteroid ast = rocks.get(j);
                if (ast.hit(mx, my) && ast.isChosen()) {
                    rocks.remove(j);
                    if (rocks.size() > 0) {
                        pickRock();
                    }
                }

            }
        }
    }

    public void pickRock() {
        int randrock = (int) (Math.random() * rocks.size());
        rocks.get(randrock).setChosen();
    }

    public void render(GameContainer gc, Graphics g) throws SlickException {
        for (Asteroid a : rocks) {
            a.draw();
        }
        g.drawString("Asteroids Left: " + rocks.size(), 10, 10);
        if (rocks.size() == 0) {
            ttf.drawString(250, 200, "GAME OVER", Color.yellow);
        }
    }

    public static void main(String args[]) throws SlickException {
        AstroidObject game = new AstroidObject("Astroid Blaster");
        AppGameContainer app = new AppGameContainer(game);
        app.setDisplayMode(800, 600, false);
        app.setShowFPS(false);
        app.setTargetFrameRate(100);
        app.start();
    }

}
