package PA3;

import java.util.*;
/**
 MineField
 class with locations of mines for a game.
 This class is mutable, because we sometimes need to change it once it's created.
 mutators: populateMineField, resetEmpty
 includes convenience method to tell the number of mines adjacent to a location.
 */
public class MineField {

   // <put instance variables here>

   /* invariant representative
    * mines row >= 1 and col >= 1
    * the column of each row should be the same
    * numMines >= 0
    *
    * @pram mines: the mines for the minefield
    *       numMines: the # of mines for the minefield
    */
   private boolean[][] mines;

   private int numMines;


   /**
    Create a minefield with same dimensions as the given array, and populate it with the mines in the array
    such that if mineData[row][col] is true, then hasMine(row,col) will be true and vice versa.  numMines() for
    this minefield will corresponds to the number of 'true' values in mineData.
    @param mineData  the data for the mines; must have at least one row and one col,
    and must be rectangular (i.e., every row is the same length)
    */
   public MineField(boolean[][] mineData) {

      assert isValidMineData(mineData);

      mines = new boolean[mineData.length][mineData[0].length];

      for(int i=0; i<mineData.length; i++){

         mines[i] = Arrays.copyOf(mineData[i],mineData[i].length);

      }
      //mines = mineData;

      getNumMines(mines);

   }


   /**
    Create an empty minefield (i.e. no mines anywhere), that may later have numMines mines (once
    populateMineField is called on this object).  Until populateMineField is called on such a MineField,
    numMines() will not correspond to the number of mines currently in the MineField.
    @param numRows  number of rows this minefield will have, must be positive
    @param numCols  number of columns this minefield will have, must be positive
    @param numMines   number of mines this minefield will have,  once we populate it.
    PRE: numRows > 0 and numCols > 0 and 0 <= numMines < (1/3 of total number of field locations).
    */
   public MineField(int numRows, int numCols, int numMines) {

      assert numRows > 0 && numCols > 0 && numMines >= 0 && numMines < (numRows * numCols) / 3;

      mines = new boolean[numRows][numCols];

      this.numMines = numMines;

   }


   /**
    Removes any current mines on the minefield, and puts numMines() mines in random locations on the minefield,
    ensuring that no mine is placed at (row, col).
    @param row the row of the location to avoid placing a mine
    @param col the column of the location to avoid placing a mine
    PRE: inRange(row, col) and numMines() < (1/3 * numRows() * numCols())
    */
   public void populateMineField(int row, int col) {

      assert inRange(row,col) && numMines < (numRows() * numCols()) / 3;

      resetEmpty();

      Random r = new Random();

      int count = 0;

      while(count < numMines){

         int x = r.nextInt(mines.length);

         int y = r.nextInt(mines[0].length);

         if(inRange(x,y) && (x != row || y != col) && mines[x][y] == false){

            mines[x][y] = true;

            count++;

         }
      }
   }


   /**
    Reset the minefield to all empty squares.  This does not affect numMines(), numRows() or numCols()
    Thus, after this call, the actual number of mines in the minefield does not match numMines().
    Note: This is the state a minefield created with the three-arg constructor is in
    at the beginning of a game.
    */
   public void resetEmpty() {

      mines = new boolean[mines.length][mines[0].length];

   }


   /**
    Returns the number of mines adjacent to the specified mine location (not counting a possible
    mine at (row, col) itself).
    Diagonals are also considered adjacent, so the return value will be in the range [0,8]
    @param row  row of the location to check
    @param col  column of the location to check
    @return  the number of mines adjacent to the square at (row, col)
    PRE: inRange(row, col)
    */
   public int numAdjacentMines(int row, int col) {


      assert inRange(row, col);

      int nums = 0;

      for (int i = row - 1; i <= row + 1; i++) {

         for (int j = col - 1; j <= col + 1; j++) {

            // in range & not counting a possible mine at (row, col) itself
            if (inRange(i, j) && !(i == row && j == col)) {

               if (mines[i][j] == true) {

                  nums++;

               }
            }
         }
      }

      return nums;
   }


   /**
    Returns true iff (row,col) is a valid field location.  Row numbers and column numbers
    start from 0.
    @param row  row of the location to consider
    @param col  column of the location to consider
    @return whether (row, col) is a valid field location
    */
   public boolean inRange(int row, int col) {

      if(row >= 0 && row < mines.length && col >= 0 && col < mines[0].length){

         return true;

      }

      return false;

   }


   /**
    Returns the number of rows in the field.
    @return number of rows in the field
    */
   public int numRows() {

      return mines.length;

   }


   /**
    Returns the number of columns in the field.
    @return number of columns in the field
    */
   public int numCols() {

      return mines[0].length;

   }


   /**
    Returns whether there is a mine in this square
    @param row  row of the location to check
    @param col  column of the location to check
    @return whether there is a mine in this square
    PRE: inRange(row, col)
    */
   public boolean hasMine(int row, int col) {

      if (inRange(row, col) && mines[row][col] == true) {

         return true;

      }

      return false;

   }


   /**
    Returns the number of mines you can have in this minefield.  For mines created with the 3-arg constructor,
    some of the time this value does not match the actual number of mines currently on the field.  See doc for that
    constructor, resetEmpty, and populateMineField for more details.
    * @return
    */
   public int numMines() {

      return numMines;

   }
   //override the toString method for MinefieldTester
   public String toString() {
      String field = "";
      for (int i = 0; i < mines.length; i++) {
         for (int j = 0; j < mines[0].length; j++) {
            if (mines[i][j] == true) {
               field = field.isEmpty() || field.charAt(field.length() - 1) == '\n' ? field + 1 : field + " " + 1;
            }
            else {
               field = field.isEmpty() || field.charAt(field.length() - 1) == '\n' ? field + 0 : field + " " + 0;
            }
            if (j == mines[0].length - 1) {
               field = field + '\n';
            }
         }
      }
      return field;
   }

   // <put private methods here>



   /*
    * To determine if the constructor is a valid mineData
    * @param mineData
    * return ture if it is a valid minedata
    */
   private boolean isValidMineData(boolean[][] mineData){

      if(mineData.length < 1){

         return false;

      }

      if(mineData.length == 1){

         if(mineData[0].length < 1){

            return false;

         }
         else{

            return true;

         }
      }

      for(int i=0; i<mineData.length-1; i++){

         if(mineData[i].length < 1 || mineData[i].length != mineData[i+1].length){

            return false;

         }
      }

      return true;

   }

   /*
    * get the # of mines for the 1-arg constructor
    * @param mineData
    */
   private void getNumMines(boolean[][] mineData){

      for(int i=0; i<mineData.length; i++){

         for(int j=0; j<mineData[0].length; j++){

            if(mineData[i][j] == true){

               numMines++;

            }
         }
      }
   }
}
