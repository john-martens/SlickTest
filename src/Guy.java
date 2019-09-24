import java.util.ArrayList;
import org.newdawn.slick.Animation;
import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.SpriteSheet;
import org.newdawn.slick.geom.Rectangle;

public class Guy {

    private Animation ani[] = new Animation[4];
    private Image walk[][] = new Image[4][4];
    private Image stopImage[] = new Image[4];
    private SpriteSheet gsprite;
    private boolean stopped;
    private Rectangle hitbox;
    private int dir; //0=D, 1=L, 2=U, 3=R

    public Guy(int x, int y) throws SlickException {
        gsprite = new SpriteSheet("data/george.png", 48, 48);
        gsprite.startUse();
        for (int i = 0; i < 4; i++) {
            stopImage[i] = gsprite.getSubImage(i, 0);
            for (int j = 0; j < 4; j++) {
                walk[i][j] = gsprite.getSubImage(i, j);
            }
            ani[i] = new Animation(walk[i], 100);
        }
        gsprite.endUse();
        hitbox = new Rectangle(x, y, 26, 30); //smaller rect = less sensitive to barriers
        stopped = true;
        dir = 3;
    }
    
    public boolean isHitting(ArrayList<Rectangle> rect){
        for (Rectangle r : rect) {
            if(hitbox.intersects(r)) return true;
        }
        return false;
    }
    
    public void move(Input kb,ArrayList<Rectangle> walls){
         
        stopped = false;
        int x = (int)hitbox.getX();
        int y = (int)hitbox.getY();
        int origx = x, origy = y;
        
        if (kb.isKeyDown(Input.KEY_RIGHT)) {
            x++;
            dir = 3;
        } else if (kb.isKeyDown(Input.KEY_LEFT)) {
            x--;
            dir = 1;
        } else if (kb.isKeyDown(Input.KEY_DOWN)) {
            y++;
            dir = 0;
        } else if (kb.isKeyDown(Input.KEY_UP)) {
            y--;
            dir = 2;
        } else {
            stopped = true;
        }
        hitbox.setX(x);
        hitbox.setY(y);
        
        if(isHitting(walls)){
            hitbox.setX(origx);
            hitbox.setY(origy);
        }
        
    }
    
    public boolean isHitting(GameObject go){
        return hitbox.intersects(go.getHitbox());
    }
    
    public void draw(Graphics g){
          if (stopped) {
            ani[dir].stop();
            stopImage[dir].draw(hitbox.getX()-12, hitbox.getY()-12);
        } else {
            ani[dir].start();
            ani[dir].draw(hitbox.getX()-12, hitbox.getY()-12);
        }
     //  g.setColor(Color.red);
     //  g.draw(hitbox);
    }
    
    
}
