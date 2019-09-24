import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Rectangle;

public class Asteroid {
    private Image image;
    private Rectangle hitbox; //every image needs one
    private int xdir,ydir;  //x-y movement of hitbox
    private boolean isChosen; //will turn red if true
    //so we can set to play in any size game
    private static int GAME_WIDTH;
    private static int GAME_HEIGHT;
    
    //make asteroid by giving initial x-y location
    Asteroid(int x, int y) throws SlickException{
        image = new Image("data/astroid.png");
       //also make hitbox
        hitbox = new Rectangle(x, y, image.getHeight(), image.getWidth());
        //set movement to random x-y dirction
        do{
            xdir = (int)(Math.random() * 7 -3); //-3 to +3
            ydir = (int)(Math.random() * 7 -3); //-3 to +3
            //make sur both are not 0
        }while(xdir==0 || ydir==0);
        isChosen=false;  //not chosen by default
    }
    
    public static void setGameSize(int w, int h){
        GAME_WIDTH = w;
        GAME_HEIGHT = h;
    }
    
    public void move(){
        //change x&y location of hitbox by its direction
        hitbox.setX(hitbox.getX() + xdir);
        hitbox.setY(hitbox.getY() + ydir);
        //if you hit any corner of the game, reverse that direction
        if(hitbox.getX() <=0 || hitbox.getX() >= GAME_WIDTH - hitbox.getWidth())
            xdir = -xdir;
        if(hitbox.getY() <=0 || hitbox.getY() >= GAME_HEIGHT - hitbox.getHeight())
            ydir = -ydir;
    }
    
    public boolean hit(int mx, int my){
        return hitbox.contains(mx, my);
    }
    
    public void draw(){
        if(isChosen)
             image.setColor(1, 200, 0, 0, .5f);
        image.draw(hitbox.getX(), hitbox.getY());
    }
    
    public void setChosen(){
        isChosen=true;
    }
    
    public boolean isChosen(){
        return isChosen;
    }
    
    
    
}
