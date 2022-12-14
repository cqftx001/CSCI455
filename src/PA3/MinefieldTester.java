package PA3;

/*
 * MinefieldTester to test the MineField class
 */
public class MinefieldTester {
   public static void main(String[] args){

      // test the constructor with 1 arg
      System.out.println("Testing 1-arg constructor!");
      System.out.println("");
      boolean[][] mineTester1 = {{false, false, false},{false, true, false},{false, false, false}};
      boolean[][] mineTester2 = {{true, true, true},{false, false, false}};
      boolean[][] mineTester3 = {{true}};
      boolean[][] mineTester4 = {{false}};

      MineField mf = new MineField(mineTester1);
      System.out.println("Tester1: ");
      System.out.println("[Exp:\n0 0 0\n0 1 0\n0 0 0 ]");
      System.out.println("");
      System.out.println(mf.toString());
      System.out.println("# of Rows [Exp: 3] " + mf.numRows());
      System.out.println("# of Cols [Exp: 3] " + mf.numCols());
      System.out.println("# of Mines [Exp: 1] " + mf.numMines());
      System.out.println("hasMine(0, 0)? [Exp: false] " + mf.hasMine(0,1));
      System.out.println("hasMine(0, 1)? [Exp: true] " + mf.hasMine(1, 1));
      System.out.println("inRange(2, 2)? [Exp: true] " + mf.inRange(2, 2));
      System.out.println("inRange(3, 3)? [Exp: false] " + mf.inRange(3, 3));
      System.out.println("inRange(-1, 0)? [Exp: false]: " + mf.inRange(-1, 0));
      System.out.println("inRange(0, -1)? [Exp: false]: " + mf.inRange(0, -1));
      System.out.println("Num of Adjacent Mines in [0, 0]? [Exp: 1] " + mf.numAdjacentMines(0, 0));
      System.out.println("Num of Adjacent Mines in [1, 1]? [Exp: 0] " + mf.numAdjacentMines(1, 1));
      System.out.println("Num of Adjacent Mines in [2, 0]? [Exp: 1] " + mf.numAdjacentMines(2, 0));
      System.out.println("Num of Adjacent Mines in [2, 2]? [Exp: 1] " + mf.numAdjacentMines(2, 2));

      mf = new MineField(mineTester2);
      System.out.println("Tester2: ");
      System.out.println("[Exp:\n1 1 1\n0 0 0 ]");
      System.out.println("");
      System.out.println(mf.toString());
      System.out.println("# of Rows [Exp: 2] " + mf.numRows());
      System.out.println("# of Columns [Exp: 3] " + mf.numCols());
      System.out.println("# of Mines [Exp: 3] " + mf.numMines());
      System.out.println("hasMine(0, 0)? [Exp: true] " + mf.hasMine(0, 0));
      System.out.println("hasMine(1, 0)? [Exp: false] " + mf.hasMine(1, 0));
      System.out.println("inRange(3, 3)? [Exp: false] " + mf.inRange(3, 3));
      System.out.println("inRange(-1, 0)? [Exp: false] " + mf.inRange(-1, 0));
      System.out.println("inRange(0, -1)? [Exp: false] " + mf.inRange(0, -1));
      System.out.println("Num of Adjacent Mines in [0, 0]? [Exp: 1] " + mf.numAdjacentMines(0, 0));
      System.out.println("Num of Adjacent Mines in [1, 1]? [Exp: 3] " + mf.numAdjacentMines(1, 1));

      mf = new MineField(mineTester3);
      System.out.println("Tester3: ");
      System.out.println("[Exp: 1 ]");
      System.out.println("");
      System.out.println(mf.toString());
      System.out.println("# of Rows [Exp: 1] " + mf.numRows());
      System.out.println("# of Cols [Exp: 1] " + mf.numCols());
      System.out.println("# of Mines [Exp: 1] " + mf.numMines());
      System.out.println("hasMine(0,0)? [Exp: true] " + mf.hasMine(0,0));
      System.out.println("inRange(2, 2)? [Exp: false] " + mf.inRange(2, 2));
      System.out.println("inRange(-1, 0)? [Exp: false] " + mf.inRange(-1, 0));
      System.out.println("inRange(0, -1)? [Exp: false] " + mf.inRange(0, -1));
      System.out.println("Num of Adjacent Mines in [0, 0]? [Exp: 0] " + mf.numAdjacentMines(0, 0));

      mf = new MineField(mineTester4);
      System.out.println("Tester4: ");
      System.out.println("[Exp: 0 ]");
      System.out.println("");
      System.out.println(mf.toString());
      System.out.println("# of Rows [Exp: 1] " + mf.numRows());
      System.out.println("# of Cols [Exp: 1] " + mf.numCols());
      System.out.println("# of Mines [Exp: 0] " + mf.numMines());
      System.out.println("hasMine(0,0)? [Exp: flase] " + mf.hasMine(0,0));
      System.out.println("inRange(2, 2)? [Exp: false] " + mf.inRange(2, 2));
      System.out.println("inRange(-1, 0)? [Exp: false] " + mf.inRange(-1, 0));
      System.out.println("inRange(0, -1)? [Exp: false] " + mf.inRange(0, -1));
      System.out.println("Num of Adjacent Mines in [0, 0]? [Exp: 0] " + mf.numAdjacentMines(0, 0));

      System.out.println("");
      System.out.println("Testing resetEmpty()");
      System.out.println("Tester1: ");
      mf = new MineField(mineTester1);
      mf.resetEmpty();
      System.out.println("[Exp:\n0 0 0\n0 0 0\n0 0 0 ]");
      System.out.println("");
      System.out.println(mf.toString());

      System.out.println("Tester2: ");
      mf = new MineField(mineTester3);
      mf.resetEmpty();
      System.out.println("[Exp: 0 ]");
      System.out.println("");
      System.out.println(mf.toString());


      System.out.println("Testing populateMineField");
      boolean[][] mineTester5 = {{false, true, false}, {false, false, false}, {false, false, true}};
      mf = new MineField(mineTester5);
      mf.populateMineField(0,1);
      System.out.println("# of Mines [Exp: 2] " + mf.numMines());
      System.out.print("[Exp: 0 in (0, 1) with two random 1]: \n");
      System.out.println("");
      System.out.println(mf.toString());

      mf = new MineField(6,6,2);
      System.out.println("Before populating Mines: [Exp:\n0 0\n0 0]");
      System.out.println("# of Rows [Exp: 6] " + mf.numRows());
      System.out.println("# of Cols [Exp: 6] " + mf.numCols());
      System.out.println("# of Mines [Exp: 2] " + mf.numMines());
      mf.populateMineField(0,0);
      System.out.println("[Exp: 0 in (0, 0) with two random 1] ");
      System.out.println(mf.toString());
      mf.populateMineField(1,1);
      System.out.println("[Exp: 0 in (1, 1) with two random 1] ");
      System.out.println(mf.toString());

   }

}

