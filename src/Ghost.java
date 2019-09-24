
import java.util.ArrayList;
import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Rectangle;


public class Ghost extends GameObject{
    
    int xdir, ydir;
    
    public Ghost(int x, int y, String s)throws SlickException{
        super(x, y, s);
        xdir = 1;
        ydir = 0;
        hitbox.setWidth(image.getWidth());
        hitbox.setHeight(image.getHeight());
    }

    @Override
    public void move(ArrayList<Rectangle> barriers) {
     
        int x = (int)hitbox.getX();
        int y = (int)hitbox.getY(); //600
        
        if(y >=600 || y <=0)
            ydir = -ydir;  //-1
        
        if(x >=760 || x <=0)
            xdir = -xdir;  //-1
        
        
        //go down first hall from main
       if(x == 110 && ydir == 0 && xdir==1){
            xdir=0;
            ydir=1;
        }
       
        //down 3rd
         if(x==525 && xdir==1){
            ydir = 1;
            xdir = 0;
        }
       
       //up second from main
       if(x==330 && xdir==-1){
            y--;
            ydir = -1;
            xdir = 0;
        }
       
      
         
        //back to main from upper hall
       if(y==170 && ydir==1 && x==330){
            ydir = 0;
            xdir = -1;
        }
         
       //back to main from a lower hall
       if(y==170 && ydir==-1){
            ydir = 0;
            xdir = 1;
        }
       
        x+=xdir;
        y+=ydir; 
         
        hitbox.setY(y);
        hitbox.setX(x);
        
    }
   
    
    
    
    
}