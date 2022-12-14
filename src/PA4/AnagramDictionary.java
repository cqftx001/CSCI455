package PA4;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.*;

/**
 A dictionary of all anagram sets.
 Note: the processing is case-sensitive; so if the dictionary has all lower
 case words, you will likely want any string you test to have all lower case
 letters too, and likewise if the dictionary words are all upper case.
 */
public class AnagramDictionary {

   /**
    Create an anagram dictionary from the list of words given in the file
    indicated by fileName.
    @param fileName  the name of the file to read from
    @throws FileNotFoundException  if the file is not found
    @throws IllegalDictionaryException  if the dictionary has any duplicate words
    */

   private Map<String, ArrayList<String>> ana;

   public AnagramDictionary(String fileName) throws FileNotFoundException,
           IllegalDictionaryException {
      File inFile = new File(fileName);

      try(Scanner in = new Scanner(inFile)){

         ana = new HashMap<>();

         while(in.hasNext()){

            String word = in.next();

            //Put the elements contained in the String into an Array, and then sort the array in alphabetic order
            char[] chars = word.toCharArray();

            Arrays.sort(chars);

            String newWord = new String(chars);

            if(ana.containsKey(newWord)){

               if(ana.get(newWord).contains(word)){

                  throw new IllegalDictionaryException(word);
               }

               //if word is not present, add word to the array
               ana.get(newWord).add(word);

            }

            else{

               //if newWord is not present yet, add it to the Map with value of an empty ArrayList and put the word in it
               ana.put(newWord, new ArrayList<>());

               ana.get(newWord).add(word);

            }
         }
      }

      assert isValidAna();

   }


   /**
    Get all anagrams of the given string. This method is case-sensitive.
    E.g. "CARE" and "race" would not be recognized as anagrams.
    @param string string to process
    @return an ArrayList of the anagrams of string
    */
   public ArrayList<String> getAnagramsOf(String string) {

      //discussed above
      char[] chars = string.toCharArray();

      Arrays.sort(chars);

      String newWord = new String(chars);

      assert isValidAna();

      if(!ana.containsKey(newWord)){

         return new ArrayList<>();

      }

      return new ArrayList<String>(ana.get(newWord));
   }

   /**
    * Check if the dictionary is valid
    * @return true, if ana is valid
    */
   public  boolean isValidAna(){

      if(ana != null){

         return true;

      }

      return false;

   }
}

