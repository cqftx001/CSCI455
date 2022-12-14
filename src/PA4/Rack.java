package PA4;

import java.util.ArrayList;
import java.lang.String;
import java.lang.Character;
import java.util.Map;
import java.util.HashMap;
import java.lang.Integer;
import java.util.Iterator;

/**
 A Rack of Scrabble tiles
 */

public class Rack {

   /**
    Representation Invariant:
    -- letters can't be null and letters.length() > 0;
    @param letters a string that represents the scrabble tiles in the rack
    */
   private String letters;

   /**
    Constructor of class Rack.
    PRE: string.length() > 0
    @param string the current scrabble tiles in the rack
    */

   public Rack(String string) {
      letters = string;
      assert isValidLetters();
   }

   /**
    Find all subsets of the given scrabble tiles and store them in an ArrayList
    @return an ArrayList<String> storing all possible subsets of scrabble tiles
    */

   public ArrayList<String> getSubsets() {

      Map<Character, Integer> letterMap = letterMap();

      Iterator<Map.Entry<Character, Integer>> iter = letterMap.entrySet().iterator();

      char[] chars = new char[letterMap.size()];

      int[] mult = new int[letterMap.size()];

      int i = 0;

      while(iter.hasNext()) {

         Map.Entry<Character, Integer> curr = iter.next();

         chars[i] = curr.getKey();

         mult[i++] = curr.getValue();

      }

      String unique = new String(chars);

      assert isValidLetters();

      return allSubsets(unique, mult, 0);

   }

   /**
    Loop over the scrabble tiles (instance variable letters) to find all unique chars and their occurence times in
    letters. Store the data in a HashMap.
    @return a HashMap storing all unique chars and their occurrence times
    */

   private Map<Character, Integer> letterMap() {

      Map<Character, Integer> letterMap = new HashMap<>();

      for(int i = 0; i < letters.length(); i++) {

         if(letterMap.containsKey(letters.charAt(i))) {

            letterMap.put(letters.charAt(i), letterMap.get(letters.charAt(i)) + 1);

         }

         else {

            letterMap.put(letters.charAt(i), 1);

         }
      }

      assert isValidLetters();

      return letterMap;
   }

   /**
    Finds all subsets of the multiset starting at position k in unique and mult.
    unique and mult describe a multiset such that mult[i] is the multiplicity of the char
    unique.charAt(i).
    PRE: mult.length must be at least as big as unique.length()
    0 <= k <= unique.length()
    @param unique a string of unique letters
    @param mult the multiplicity of each letter from unique.
    @param k the smallest index of unique and mult to consider.
    @return all subsets of the indicated multiset.  Unlike the multiset in the parameters,
    each subset is represented as a String that can have repeated characters in it.
    @author Claire Bono
    */
   private static ArrayList<String> allSubsets(String unique, int[] mult, int k) {

      ArrayList<String> allCombos = new ArrayList<>();

      if (k == unique.length()) {  // multiset is empty

         allCombos.add("");

         return allCombos;
      }

      // get all subsets of the multiset without the first unique char

      ArrayList<String> restCombos = allSubsets(unique, mult, k+1);

      // prepend all possible numbers of the first char (i.e., the one at position k)
      // to the front of each string in restCombos.  Suppose that char is 'a'...

      String firstPart = "";          // in outer loop firstPart takes on the values: "", "a", "aa", ...

      for (int n = 0; n <= mult[k]; n++) {

         for (int i = 0; i < restCombos.size(); i++) {  // for each of the subsets

            // we found in the recursive call
            // create and add a new string with n 'a's in front of that subset

            allCombos.add(firstPart + restCombos.get(i));

         }

         firstPart += unique.charAt(k);  // append another instance of 'a' to the first part

      }

      return allCombos;

   }

   /**
    Check if the letters is valid scrabble tiles
    @return true if valid, otherwise false
    */
   private boolean isValidLetters() {

      if(letters == null || letters.length() == 0) {

         return false;

      }

      return true;

   }
}
