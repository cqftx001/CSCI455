package PA1;

import java.util.*;
//This class is the test program for class CoinTossSimulator. It also manages the output. However, it doesn't handle any input.
public class CoinTossSimulatorTester {

   public static int TestCase(int numTrials, int totTrials, CoinTossSimulator test) {

      //create a method to test trials 1,10,100,1000.
      boolean AddUp = test.getNumTrials() == test.getHeadTails()+test.getTwoHeads()+test.getTwoTails();
      test.run(numTrials);
      totTrials += numTrials;
      System.out.println("After run("+numTrials+"): ");
      System.out.println("Number of trials [exp:"+totTrials+"]: "+test.getNumTrials());
      System.out.println("Two-head tosses: "+test.getTwoHeads());
      System.out.println("Two-tail tosses: "+test.getTwoTails());
      System.out.println("One-head one-tail tosses: "+test.getHeadTails());
      System.out.println("Tosses add up correctly? "+AddUp);
      System.out.println("");
      return totTrials;
   }
   public static void main(String[] args) {

      CoinTossSimulator test = new CoinTossSimulator();
      int totTrials = 0;
      //print the results after tests
      System.out.println("After constructor: ");
      System.out.println("Number of trials [exp:0]: "+test.getNumTrials());
      System.out.println("Two-head tosses: "+test.getTwoHeads());
      System.out.println("Two-tail tosses: "+test.getTwoTails());
      System.out.println("One-head one-tail tosses: "+test.getHeadTails());

      //simply test if the result is added correctly
      boolean initial = test.getNumTrials() == test.getHeadTails()+test.getTwoHeads()+test.getTwoTails();
      System.out.println("Tosses add up correctly? "+initial);
      System.out.println("");
      // test case 1
      totTrials = TestCase(1,totTrials,test);
      // test case 10
      totTrials = TestCase(10,totTrials,test);
      // test case 100
      totTrials = TestCase(100,totTrials,test);

      //reset the trials and numbers
      test.reset();
      totTrials = 0;
      System.out.println("After reset:");
      System.out.println("Number of trials [exp:0]: "+test.getNumTrials());
      System.out.println("Two-head tosses: "+test.getTwoHeads());
      System.out.println("Two-tail tosses: "+test.getTwoTails());
      System.out.println("One-head one-tail tosses: "+test.getHeadTails());
      System.out.println("Tosses add up correctly? "+initial);
      System.out.println("");

      // test case 1000
      totTrials = TestCase(1000,totTrials,test);





   }
}