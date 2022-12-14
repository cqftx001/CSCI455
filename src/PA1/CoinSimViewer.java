package PA1;

import java.util.Scanner;
import javax.swing.JFrame;

//This class contains main function which creates the JFrame with CoinSimComponent class
public class CoinSimViewer {
   public static void main(String[] args) {
      // TODO Auto-generated method stub
      //get input
      Scanner in = new Scanner(System.in);
      System.out.println("Please enther th number of trials : ");
      int trials = in.nextInt();
      //wrong number exception
      while(trials <= 0) {
         System.out.println("ERROR: Number entered must be greater than 0");
         System.out.println("You want to (1)Continue or (2)Quiting ? ");
         int word = in.nextInt();
         if(word == 1) {
            System.out.println("Please enter again!");
            trials = in.nextInt();
         }
         else {
            System.out.println("Quiting.....");
            return;
         }

      }
      //initialize JFrame
      JFrame frame = new JFrame();
      //800 pixels * 500 pixels
      frame.setSize(800,500);
      frame.setTitle("CoinSim");
      frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      //draw bars
      CoinSimComponent comp = new CoinSimComponent(trials);
      frame.add(comp);
      frame.setVisible(true);
   }

}
