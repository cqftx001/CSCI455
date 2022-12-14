package PA4;

import java.lang.String;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;
import java.io.FileNotFoundException;
import java.lang.Integer;
import java.util.Comparator;

/**
 Class WordFinder contains main method to run the program. Use a dictionary passed by the command-line argument or
 the default "sowpods.txt". Illegal dictionary or file will throw out exceptions and exit the program. The program
 will keep reading in the rack of scrabble tiles and find all possible words until meet a period.
 */

public class WordFinder{

   public static void main(String[] args) {

      String fileName = "sowpods.txt";

      if(args.length > 0){

         fileName = args[0];

      }

      try {

         AnagramDictionary ana = new AnagramDictionary(fileName);

         System.out.println("Type . to quit.");

         System.out.print("Rack? ");

         Scanner in = new Scanner(System.in);

         String input = in.next();

         while(!input.equals(".")) {

            Rack rack = new Rack(input);

            ArrayList<Map.Entry<String, Integer>> words = getAllWords(rack, ana);

            words.sort(new newComparator());

            System.out.println("We can make " + words.size() + " words from \"" + input + "\"");

            if(words.size() != 0) {

               System.out.println("All of the words with their scores (sorted by score): ");

               printResults(words);

            }

            input = in.next();

            System.out.print("Rack? ");

         }

      }

      catch(FileNotFoundException e) {

         System.out.println("ERROR: Dictionary file \"" + fileName + "\" does not exist.");

         System.out.println("Exiting program.");

      }

      catch(IllegalDictionaryException e) {

         System.out.println("ERROR: Illegal dictionary: dictionary file has a duplicate word: " + e.getMessage());

         System.out.println("Exiting program.");

      }
   }

   /**
    * get the words and score that we can collect from the given rack.
    * @param rack instance of class Rack
    * @param ana instance of class AnagramDictionary
    * return ArrayList storing the words and the scores
    */

   private static ArrayList<Map.Entry<String, Integer>> getAllWords(Rack rack, AnagramDictionary ana) {

      ArrayList<String> subsets = rack.getSubsets();

      ArrayList<String> words = new ArrayList<>();

      for(int i = 0; i < subsets.size(); i++) {

         words.addAll(ana.getAnagramsOf(subsets.get(i)));

      }

      Map<String, Integer> wordScore = new HashMap<>();

      ScoreTable scoreTable = new ScoreTable();

      for(int i = 0; i < words.size(); i++) {

         wordScore.put(words.get(i), scoreTable.getTotal(words.get(i)));

      }

      return new ArrayList<>(wordScore.entrySet());

   }

   /**
    A class that implements Comparator to help sort the words and their scores. Sort the words in the decreasing
    order of their scores. If two words have the same score, sort them in alphabetic order.
    */

   static class newComparator implements Comparator<Map.Entry<String, Integer>> {

      public int compare(Map.Entry<String, Integer> a, Map.Entry<String, Integer> b) {

         if(a.getValue() == b.getValue()) {

            return a.getKey().compareTo(b.getKey());

         }

         return b.getValue() - a.getValue();

      }
   }

   /*
    * Print the ArrayList which stores the scores and words
    */

   private static void printResults(ArrayList<Map.Entry<String, Integer>> words) {

      for(int i = 0; i < words.size(); i++) {

         System.out.println(words.get(i).getValue() + ": " + words.get(i).getKey());

      }
   }

}
