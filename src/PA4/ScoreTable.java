package PA4;

import java.util.Arrays;
import java.lang.String;

/**
 A ScoreTable of Scrabble tiles to calculate the total points of a word.
 */

public class ScoreTable{

   /**
    * Representation Invariants: points should not be null and the length should be equals to NUM_LETTERS(26)
    */

   private int[] points;

   /**
    Constructor of class ScoreTable.
    Initialize the points array with hard-coded values.
    */

   public ScoreTable(){

      //According to the points table, hardcoded the points array
      points = new int[]{ 1, 3, 3, 2, 1, 4, 2, 4, 1, 8, 5, 1, 3, 1, 1, 3, 10, 1, 1, 1, 1, 4, 4, 8, 4, 10};

   }

   /**
    * Iterate the char in a word, and calculate the points we get
    * @param word: the word we are given
    * @Return points: the total points after calculating
    */

   public int getTotal(String word) {

      int sum = 0;

      for(int i = 0; i < word.length(); i++) {

         int score = 0;

         if(word.charAt(i) <= 'z' && word.charAt(i) >= 'a') {

            score = points[word.charAt(i) - 'a'];

         }

         if(word.charAt(i) <= 'Z' && word.charAt(i) >= 'A') {

            score = points[word.charAt(i) - 'A'];

         }

         sum += score;

      }

      return sum;

   }


}
