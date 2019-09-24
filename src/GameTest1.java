import org.newdawn.slick.*;

/*
SLICK SETUP
===========
1 - Import 4 jar files:
    a) slick  b)lwjgl(light-weight java game lib) c)lwjgl_util  d)jinput
2 - Copy windowsdll folder to root of projet folder (not in src but 1 above)
3 - Configure Run:  File -> Project Properties -> Run
4 - Add this line to VM Options (Virtual Machine):  -Djava.library.path=./windowsdll
*/

public class GameTest1 extends BasicGame{
    
    private Image android = null;
    private int xloc=100,yloc=100, ydir=1,xdir=1;

    public GameTest1(String title){
        super(title);
    }
 
    @Override
    public void init(GameContainer gc) throws SlickException {
       android = new Image("data/android.png");
    }

    @Override
    public void update(GameContainer gc, int delta) throws SlickException {
        xloc+=xdir;
        yloc+=ydir;
        
        if(xloc > 800 - android.getWidth() || xloc < 0) xdir = -xdir;
        if(yloc > 600 - android.getHeight() || yloc < 0) ydir = -ydir;
    }

    @Override
    public void render(GameContainer gc, Graphics g) throws SlickException {
       android.draw(xloc,yloc);
       g.drawString("Slick is cool", 100, 100);
    }
       
    public static void main(String[] args) throws SlickException{
        GameTest1 game = new GameTest1("Testing Game");
        AppGameContainer app = new AppGameContainer(game);
        app.setDisplayMode(800, 600, false);
        app.setShowFPS(false);
        app.setTargetFrameRate(500);
        app.start();
    }

    
}
