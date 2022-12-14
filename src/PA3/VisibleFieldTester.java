package PA3;

/*
   VisibleFieldTester to test the VisibleField class
 */

public class VisibleFieldTester {

   public static void main(String[] args) {

      //test victory condition with fixed MineField
      boolean[][] mineData = {{true, false, false, false},
              {true, true, false, false},
              {false, true, false, true}};
      MineField mf = new MineField(mineData);
      System.out.print("initial minefield [Exp: \n1 0 0 0\n1 1 0 0\n0 1 0 1]: \n" + mf.toString());
      VisibleField vf = new VisibleField(mf);
      System.out.println("Number of Mines Left[Exp: 5]: " + vf.numMinesLeft());
      System.out.print("initial visiblefield [Exp: \n-1 -1 -1 -1\n-1 -1 -1 -1\n-1 -1 -1 -1]: \n");
      printVisibleField(vf);
      vf.uncover(0, 3);
      System.out.println("Is (0, 3) uncovered? [Exp: true]" + vf.isUncovered(0, 3));
      System.out.println("Is (0, 2) uncovered? [Exp: true]" + vf.isUncovered(0, 2));
      System.out.println("Is (1, 2) uncovered? [Exp: true]" + vf.isUncovered(1, 2));
      System.out.println("Is (1, 3) uncovered? [Exp: true]" + vf.isUncovered(1, 3));
      System.out.println("Is (0, 1) uncovered? [Exp: false]" + vf.isUncovered(0, 1));
      System.out.println("Is Game Over? [Exp: false]" + vf.isGameOver());
      System.out.print("visiblefield with (0, 3) uncovered [Exp: \n-1 -1 1 0\n-1 -1 3 1\n-1 -1 -1 -1]: \n");
      printVisibleField(vf);
      vf.cycleGuess(2, 1);
      vf.cycleGuess(2, 3);
      vf.cycleGuess(0, 1);
      System.out.println("Number of Mines Left [Exp: 2]: " + vf.numMinesLeft());
      vf.cycleGuess(2, 3);
      vf.cycleGuess(0, 1);
      System.out.println("Number of Mines Left [Exp: 4]: " + vf.numMinesLeft());
      vf.cycleGuess(0, 0);
      vf.cycleGuess(0, 0);
      vf.cycleGuess(0, 0);
      System.out.println("Status of (0, 0) [Exp: " + vf.COVERED + "]: " + vf.getStatus(0, 0));
      System.out.println("Status of (0, 1) [Exp: " + vf.QUESTION + "]: " + vf.getStatus(0, 1));
      System.out.println("Status of (2, 1) [Exp: " + vf.MINE_GUESS + "]: " + vf.getStatus(2, 1));
      System.out.print("current visiblefield [Exp: \n-1 -3 1 0\n-1 -1 3 1\n-1 -2 -1 -3]: \n");
      printVisibleField(vf);
      vf.uncover(2, 0);
      System.out.println("Is (2, 0) uncovered? [Exp: true]" + vf.isUncovered(2, 0));
      System.out.println("Is (1, 0) uncovered? [Exp: false]" + vf.isUncovered(1, 0));
      System.out.println("Is (1, 1) uncovered? [Exp: false]" + vf.isUncovered(1, 1));
      System.out.println("Is (2, 1) uncovered? [Exp: false]" + vf.isUncovered(2, 1));
      System.out.println("Is Game Over? [Exp: false]" + vf.isGameOver());
      System.out.print("current visiblefield [Exp: \n-1 -3 1 0\n-1 -1 3 1\n3 -2 -1 -3]: \n");
      printVisibleField(vf);
      vf.uncover(0, 1);
      vf.uncover(2, 2);
      System.out.println("Is Game Over? [Exp: true]" + vf.isGameOver());
      System.out.print("win visiblefield [Exp: \n-2 3 1 0\n-2 -2 3 1\n3 -2 3 -2]: \n");
      printVisibleField(vf);

      //reset the game
      vf.resetGameDisplay();
      System.out.print("reset visiblefield [Exp: \n-1 -1 -1 -1\n-1 -1 -1 -1\n-1 -1 -1 -1]: \n");
      printVisibleField(vf);
      System.out.println("Number of Mines Left[Exp: 5]: " + vf.numMinesLeft());
      System.out.print("underlying minefield [Exp: \n1 0 0 0\n1 1 0 0\n0 1 0 1]: \n" + mf.toString());

      //test GG condition with fixed minefield
      vf.cycleGuess(0, 2);
      vf.cycleGuess(1, 3);
      vf.cycleGuess(1, 2);
      vf.cycleGuess(1, 2);
      vf.uncover(0, 3);
      System.out.println("Is Game Over? [Exp: false]" + vf.isGameOver());
      System.out.print("current visiblefield [Exp: \n-1 -1 -2 0\n-1 -1 3 -2\n-1 -1 -1 -1]: \n");
      printVisibleField(vf);
      vf.cycleGuess(2, 3);
      vf.cycleGuess(2, 2);
      vf.cycleGuess(2, 0);
      vf.cycleGuess(0, 0);
      System.out.println("Number of Mines Left [Exp: -1]: " + vf.numMinesLeft());
      vf.uncover(2, 1);
      System.out.println("Is Game Over? [Exp: true]" + vf.isGameOver());
      System.out.print("lose visiblefield [Exp: \n-2 -1 10 0\n9 9 3 10\n10 11 10 -2]: \n");
      printVisibleField(vf);

   }

   /**
    Print the visibleField
    */
   private static void printVisibleField(VisibleField vf) {
      for (int i = 0; i < vf.getMineField().numRows(); i++) {
         for (int j = 0; j < vf.getMineField().numCols(); j++) {
            System.out.print(vf.getStatus(i, j) + " ");
         }
         System.out.print('\n');
      }
   }
}
