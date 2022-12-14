package PA1;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import javax.swing.JComponent;

//This class is built to set the window which extends JComponent
public class CoinSimComponent extends JComponent{
   private int Two_Heads;
   private int Two_Tails;
   private int One_Head_One_Tail;
   private int numTrials;
   int total;
   CoinTossSimulator test = new CoinTossSimulator();
   //variables for percentage
   double TwoHeads;
   double TwoTails;
   double HeadTail;


   public CoinSimComponent(int total){
      this.total = total;
      test.run(total);
      //get the number of 3 the circumstances
      Two_Heads = test.getTwoHeads();
      Two_Tails = test.getTwoTails();
      One_Head_One_Tail = test.getHeadTails();
      numTrials = test.getNumTrials();
      //get the percentage of 3 circumstances
      TwoHeads = (double)Two_Heads/numTrials;
      TwoTails = (double)Two_Tails/numTrials;
      HeadTail = (double)One_Head_One_Tail/numTrials;
   }
   public void paintComponent(Graphics g) {
      Graphics2D g2 = (Graphics2D) g;
      int x = getWidth();
      int y = getHeight();
      //calculate the width and the location of the bottom of the bar according to the size of the panel
      int width = (int)(x*0.0625);
      int bottom = (int)(y*0.86);

      //calculate the location of the left side of the three bars according to the size of the panel
      int left1 = (int)(x*0.25-0.5*width);
      int left2 = (int)(x*0.5-0.5*width);
      int left3 = (int)(x*0.75-0.5*width);

      //calculate the height of the bar according to the size of the panel
      int barHeight = (int)(y*0.8);

      //calculate the scale
      double scaleOfTwoHeads = TwoHeads;
      double scaleOfAHeadAndATail = HeadTail;
      double scaleOfTwoTails = TwoTails;


      //Creat three bars and give the parameters
      Bar twoh = new Bar(bottom,left1,width,barHeight,
              scaleOfTwoHeads,Color.red, "Two Heads: "+Two_Heads+"("+Math.round(TwoHeads*100)+"%)");
      twoh.draw(g2);

      Bar ht = new Bar(bottom,left2,width,barHeight,
              scaleOfAHeadAndATail,Color.green, "A Head and A Tail: "+One_Head_One_Tail+"("+Math.round(HeadTail*100)+"%)");
      ht.draw(g2);

      Bar twot = new Bar(bottom,left3,width,barHeight,
              scaleOfTwoTails,Color.blue, "Two Tails: "+Two_Tails+"("+Math.round(TwoTails*100)+"%)");
      twot.draw(g2);


   }


}
