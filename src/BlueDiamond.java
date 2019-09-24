
import java.util.ArrayList;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Rectangle;


public class BlueDiamond extends GameObject{
    
    public BlueDiamond(int x, int y, String s)throws SlickException{
        super(x, y, s);
    }

    @Override
    public void move(ArrayList<Rectangle> barriers) {
        boolean hitting;
        do{
            hitting=false;
            hitbox.setX((int)(Math.random() * 760));
            hitbox.setY((int)(Math.random() * 600));
            for (Rectangle barrier : barriers) {
                if(hitbox.intersects(barrier))
                    hitting=true;
            }
        }while(hitting);
    }
    
    
    
    
}
