package PA1;

/**
 * class CoinTossSimulator
 *
 * Simulates trials of repeatedly tossing two coins and allows the user to access the
 * cumulative results.
 *
 * NOTE: we have provided the public interface for this class.  Do not change
 * the public interface.  You can add private instance variables, constants,
 * and private methods to the class.  You will also be completing the
 * implementation of the methods given.
 *
 * Invariant: getNumTrials() = getTwoHeads() + getTwoTails() + getHeadTails()
 *
 */
import java.util.Random;

public class CoinTossSimulator {
   private int Two_Heads;
   private int Two_Tails;
   private int One_Head_One_Tail;
   private Random TossGenerator = new Random();
   /**
    Creates a coin toss simulator with no trials done yet.
    */
   public CoinTossSimulator() {

      // initialize all the variables which should be 0
      Two_Heads = 0;
      Two_Tails = 0;
      One_Head_One_Tail = 0;
   }


   /**
    Runs the simulation for numTrials more trials. Multiple calls to this method
    without a reset() between them *add* these trials to the current simulation.

    @param numTrials  number of trials to for simulation; must be >= 1
    */
   public void run(int numTrials) {
      for(int i = 1; i <= numTrials; ++i) {
         int total = TossGenerator.nextInt(2) + TossGenerator.nextInt(2);
         //flip 2 coins, 0 for tail and 1 for head
         if(total == 0) {
            Two_Tails++;
         }
         else if(total == 1) {
            One_Head_One_Tail++;
         }
         else {
            Two_Heads++;
         }

      }

   }


   /**
    Get number of trials performed since last reset.
    */
   public int getNumTrials() {
      return getTwoHeads()+getTwoTails()+getHeadTails();
   }


   /**
    Get number of trials that came up two heads since last reset.
    */
   public int getTwoHeads() {
      return Two_Heads;
   }


   /**
    Get number of trials that came up two tails since last reset.
    */
   public int getTwoTails() {
      return Two_Tails;
   }


   /**
    Get number of trials that came up one head and one tail since last reset.
    */
   public int getHeadTails() {
      return One_Head_One_Tail;
   }


   /**
    Resets the simulation, so that subsequent runs start from 0 trials done.
    */
   public void reset() {
      //reset all the instance variables to be 0
      Two_Heads = 0;
      Two_Tails = 0;
      One_Head_One_Tail = 0;
   }
}
