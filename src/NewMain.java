import java.text.NumberFormat;
import java.util.Scanner;

public class NewMain {
  
    public static void main(String[] args) {
     int howmany;
     Scanner s = new Scanner(System.in);
        System.out.print("How many ? > ");   
        howmany = s.nextInt();
        
      int x[] = new int [howmany];
        for (int i = 0; i < howmany; i++) {
            System.out.format("Enter value for location %d: ", i);
            x[i] = s.nextInt();
        }
        
      int largest = getLargest(x);
      System.out.format("The largest number is %d and it is found at location %d\n", x[largest], largest);
    }
    
    
    public static int getLargest(int nums[]){
        int loc = 0;
        int large=nums[0];
        for (int i = 1; i < nums.length; i++) {
            if(nums[i] > large){
                loc = i;
                large = nums[i];
            }
        }
        return loc;
    }
    
}
