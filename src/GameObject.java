import java.util.ArrayList;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Rectangle;


abstract public class GameObject {
    
    protected Rectangle hitbox;
    protected Image image;
    
    public GameObject(int x, int y, String imgname)throws SlickException{
        image = new Image(imgname);
        hitbox = new Rectangle(x, y, image.getHeight(), image.getWidth());
    }
    
    abstract public void move(ArrayList<Rectangle> barriers);
    
    public void draw(){
        image.draw(hitbox.getX(), hitbox.getY());
    }
    
    public Rectangle getHitbox(){
        return hitbox;
    }
    
}
