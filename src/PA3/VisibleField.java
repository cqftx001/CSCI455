package PA3;

/**
 VisibleField class
 This is the data that's being displayed at any one point in the game (i.e., visible field, because it's what the
 user can see about the minefield). Client can call getStatus(row, col) for any square.
 It actually has data about the whole current state of the game, including
 the underlying mine field (getMineField()).  Other accessors related to game status: numMinesLeft(), isGameOver().
 It also has mutators related to actions the player could do (resetGameDisplay(), cycleGuess(), uncover()),
 and changes the game state accordingly.

 It, along with the MineField (accessible in mineField instance variable), forms
 the Model for the game application, whereas GameBoardPanel is the View and Controller in the MVC design pattern.
 It contains the MineField that it's partially displaying.  That MineField can be accessed (or modified) from
 outside this class via the getMineField accessor.
 */
public class VisibleField {
   // ----------------------------------------------------------
   // The following public constants (plus numbers mentioned in comments below) are the possible states of one
   // location (a "square") in the visible field (all are values that can be returned by public method
   // getStatus(row, col)).

   // The following are the covered states (all negative values):
   public static final int COVERED = -1;   // initial value of all squares
   public static final int MINE_GUESS = -2;
   public static final int QUESTION = -3;

   // The following are the uncovered states (all non-negative values):

   // values in the range [0,8] corresponds to number of mines adjacent to this opened square

   public static final int MINE = 9;      // this loc is a mine that hasn't been guessed already (end of losing game)
   public static final int INCORRECT_GUESS = 10;  // is displayed a specific way at the end of losing game
   public static final int EXPLODED_MINE = 11;   // the one you uncovered by mistake (that caused you to lose)
   // ----------------------------------------------------------

   // <put instance variables here>
   /*
    * invariant representative
    *  vf's size should align with mineField
    *  numGuess >= 0 && numGuess <= vf.size() - numMines()
    * @param mf: MineField
    *        vf: the view model of the field that player can see
    *        numGuess: the number of guess squares
    *        numUncovered: the number of uncovered squares
    *        gaveOver: indicate the status of the game
    */
   private MineField mf;

   private int[][] vf;

   private int numGuess;

   private boolean gameOver;

   private int numUncovered;


   /**
    Create a visible field that has the given underlying mineField.
    The initial state will have all the locations covered, no mines guessed, and the game
    not over.
    @param mineField  the minefield to use for for this VisibleField
    */
   public VisibleField(MineField mineField) {

      mf = mineField;

      initVisibleField(mf);

      numGuess = 0;

      gameOver = false;

      numUncovered = 0;

   }


   /**
    Reset the object to its initial state (see constructor comments), using the same underlying
    MineField.
    */
   public void resetGameDisplay() {

      initVisibleField(mf);

      numGuess = 0;

      gameOver = false;

      numUncovered = 0;

   }


   /**
    Returns a reference to the mineField that this VisibleField "covers"
    @return the minefield
    */
   public MineField getMineField() {

      return mf;

   }


   /**
    Returns the visible status of the square indicated.
    @param row  row of the square
    @param col  col of the square
    @return the status of the square at location (row, col).  See the public constants at the beginning of the class
    for the possible values that may be returned, and their meanings.
    PRE: getMineField().inRange(row, col)
    */
   public int getStatus(int row, int col) {

      return vf[row][col];

   }


   /**
    Returns the the number of mines left to guess.  This has nothing to do with whether the mines guessed are correct
    or not.  Just gives the user an indication of how many more mines the user might want to guess.  This value will
    be negative if they have guessed more than the number of mines in the minefield.
    @return the number of mines left to guess.
    */
   public int numMinesLeft() {

      return mf.numMines() - numGuess;

   }


   /**
    Cycles through covered states for a square, updating number of guesses as necessary.  Call on a COVERED square
    changes its status to MINE_GUESS; call on a MINE_GUESS square changes it to QUESTION;  call on a QUESTION square
    changes it to COVERED again; call on an uncovered square has no effect.
    @param row  row of the square
    @param col  col of the square
    PRE: getMineField().inRange(row, col)
    */
   public void cycleGuess(int row, int col) {

      switch (vf[row][col]) {

         case COVERED:

            vf[row][col] = MINE_GUESS;

            numGuess++;

            break;

         case MINE_GUESS:

            vf[row][col] = QUESTION;

            numGuess--;

            break;

         case QUESTION:

            vf[row][col] = COVERED;

            break;
      }
   }



   /**
    Uncovers this square and returns false iff you uncover a mine here.
    If the square wasn't a mine or adjacent to a mine it also uncovers all the squares in
    the neighboring area that are also not next to any mines, possibly uncovering a large region.
    Any mine-adjacent squares you reach will also be uncovered, and form
    (possibly along with parts of the edge of the whole field) the boundary of this region.
    Does not uncover, or keep searching through, squares that have the status MINE_GUESS.
    Note: this action may cause the game to end: either in a win (opened all the non-mine squares)
    or a loss (opened a mine).
    @param row  of the square
    @param col  of the square
    @return false   iff you uncover a mine at (row, col)
    PRE: getMineField().inRange(row, col)
    */
   public boolean uncover(int row, int col) {

      if (isUncovered(row, col) || vf[row][col] == MINE_GUESS) {

         return true;

      }
      if (mf.hasMine(row, col)) {

         gameOver = true;

         vf[row][col] = EXPLODED_MINE;

         if (isGameOver()) {

            gG();
         }

         return false;
      }

      depthFirstSearch(row, col);

      if (isGameOver()) {

         victory();
      }

      return true;
   }


   /**
    Returns whether the game is over.
    (Note: This is not a mutator.)
    @return whether game has ended
    */
   public boolean isGameOver() {

      if (gameOver == true) {

         return gameOver;
      }

      if (numUncovered == vf.length * vf[0].length - mf.numMines()) {

         gameOver = true;
      }

      return gameOver;
   }


   /**
    Returns whether this square has been uncovered.  (i.e., is in any one of the uncovered states,
    vs. any one of the covered states).
    @param row of the square
    @param col of the square
    @return whether the square is uncovered
    PRE: getMineField().inRange(row, col)
    */
   public boolean isUncovered(int row, int col) {

      if (vf[row][col] < 0) {

         return false;
      }

      return true;
   }



   // <put private methods here>

   // update the win condition to vf
   private void victory() {

      for (int i = 0; i < vf.length; i++) {

         for (int j = 0; j < vf[0].length; j++) {

            if (vf[i][j] < 0) {

               vf[i][j] = MINE_GUESS;
            }
         }
      }
   }

   // update the lose condition to vf
   private void gG() {

      for (int i = 0; i < vf.length; i++) {

         for (int j = 0; j < vf[0].length; j++) {

            if (vf[i][j] == MINE_GUESS && !mf.hasMine(i, j)) {

               vf[i][j] = INCORRECT_GUESS;
            }

            if (vf[i][j] == COVERED && mf.hasMine(i, j)) {

               vf[i][j] = MINE;
            }
         }
      }
   }

   /*
    * Initialize the vf according to the size of mf
    * @param mineField that underlies the vf
    */
   private void initVisibleField(MineField mineField){

      vf = new int[mineField.numRows()][mineField.numCols()];

      for(int i=0; i<vf.length; i++){

         for(int j=0; j<vf[0].length; j++){

            vf[i][j] = COVERED;
         }
      }
   }

   /*
    * return the position of adjacent squares of the specified square(row, col)
    * inRange will be performed when executing
    * @param row: row of the square
    * @param col: col of the square
    * return an array that carries the position of adjacent squares
    */
   private int[][] adjcentSquares(int row, int col){

      int[][] adjcent = new int[8][2];

      adjcent[0][0] = row - 1;

      adjcent[0][1] = col -1 ;

      adjcent[1][0] = row - 1;

      adjcent[1][1] = col;

      adjcent[2][0] = row - 1;

      adjcent[2][1] = col + 1;

      adjcent[3][0] = row;

      adjcent[3][1] = col - 1;

      adjcent[4][0] = row;

      adjcent[4][1] = col + 1;

      adjcent[5][0] = row + 1;

      adjcent[5][1] = col - 1;

      adjcent[6][0] = row + 1;

      adjcent[6][1] = col;

      adjcent[7][0] = row + 1;

      adjcent[7][1] = col + 1;

      return adjcent;

   }

   /*
    * perform depth first search if the starting square in (row, col) is COVERED and its uncovered status is 0.
    *    The search will end when face the uncovered squares and squares with MINE_GUESS status.
    *    It will also count the total uncovered squares.
    *
    * @param row: row of the square
    * @param col: col of the square
    * PRE: getMineField().inRange(row,col)
    */
   private void depthFirstSearch(int row, int col) {

      if (isUncovered(row, col) || vf[row][col] == MINE_GUESS) {

         return;
      }

      vf[row][col] = mf.numAdjacentMines(row, col);

      numUncovered++;

      if (vf[row][col] != 0) {

         return;
      }

      else {

         int[][] adj = adjcentSquares(row, col);

         for (int i = 0; i < adj.length; i++) {

            if (mf.inRange(adj[i][0], adj[i][1])) {

               depthFirstSearch(adj[i][0], adj[i][1]);
            }
         }

         return;
      }
   }

}
