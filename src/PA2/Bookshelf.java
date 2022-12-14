package PA2;

/**
 * Class Bookshelf
 * Implements idea of arranging books into a bookshelf.
 * Books on a bookshelf can only be accessed in a specific way so books don’t fall down;
 * You can add or remove a book only when it’s on one of the ends of the shelf.
 * However, you can look at any book on a shelf by giving its location (starting at 0).
 * Books are identified only by their height; two books of the same height can be
 * thought of as two copies of the same book.
 */
import java.util.*;

public class Bookshelf {
   /**
    * height of books in piles > 0
    * piles != null
    */

   private ArrayList<Integer> piles;
   /**
    Representation invariant:

    <put rep. invar. comment here>

    */

   // <add instance variables here>


   /**
    * Creates an empty Bookshelf object i.e. with no books
    */
   public Bookshelf() {
      /**
       * height of books in piles > 0
       * piles != null
       */

      piles = new ArrayList<>();

      assert isValidBookshelf();  // sample assert statement (you will be adding more of these calls)

   }

   /**
    * Creates a Bookshelf with the arrangement specified in pileOfBooks. Example
    * values: [20, 1, 9].
    *
    * PRE: pileOfBooks contains an array list of 0 or more positive numbers
    * representing the height of each book.
    */
   public Bookshelf(ArrayList<Integer> pileOfBooks) {
      // constructor overload
      /**
       * the height input must greater than 0
       * height > 0
       */
      piles = new ArrayList<>(pileOfBooks);

      assert isValidBookshelf();

   }

   /**
    * Inserts book with specified height at the start of the Bookshelf, i.e., it
    * will end up at position 0.
    *
    * PRE: height > 0 (height of book is always positive)
    */
   public void addFront(int height) {
      //ArrayList has the method .add(index,Integer value)
      //assert the height > 0
      piles.add(0,height);

      assert isValidBookshelf();

   }

   /**
    * Inserts book with specified height at the end of the Bookshelf.
    *
    * PRE: height > 0 (height of book is always positive)
    */
   public void addLast(int height) {
      //assert the height > 0
      piles.add(height);

      assert isValidBookshelf();

   }

   /**
    * Removes book at the start of the Bookshelf and returns the height of the
    * removed book.
    *
    * PRE: this.size() > 0 i.e. can be called only on non-empty BookShelf
    */
   public int removeFront() {

      //remove the book in index 0 and return the height of that book
      int bookHeight = piles.remove(0);

      assert isValidBookshelf();

      return bookHeight;

   }

   /**
    * Removes book at the end of the Bookshelf and returns the height of the
    * removed book.
    *
    * PRE: this.size() > 0 i.e. can be called only on non-empty BookShelf
    */
   public int removeLast() {

      //remove the last book which is in index size-1 and return the height of that book
      int bookHeight = piles.remove(piles.size()-1);

      assert isValidBookshelf();

      return bookHeight;

   }

   /*
    * Gets the height of the book at the given position.
    *
    * PRE: 0 <= position < this.size()
    */
   public int getHeight(int position) {
      //assert position >= 0 && position < book.size()
      int Height = piles.get(position);

      assert isValidBookshelf();

      return Height;

   }

   /**
    * Returns number of books on the this Bookshelf.
    */
   public int size() {

      assert isValidBookshelf();

      return piles.size();
   }

   /**
    * Returns string representation of this Bookshelf. Returns a string with the height of all
    * books on the bookshelf, in the order they are in on the bookshelf, using the format shown
    * by example here:  “[7, 33, 5, 4, 3]”
    */
   public String toString() {

//      String bookShelfString = "[";
//      for(int i=0; i<=piles.size()-1; ++i) {
//    	  if(i == piles.size()-1) {
//    		  bookShelfString = bookShelfString + piles.get(i);
//    	  }
//    	  else {
//    		  bookShelfString = bookShelfString + piles.get(i)+", ";
//    	  }
//      }

      assert isValidBookshelf();

      return piles.toString();
   }

   /**
    * Returns true iff the books on this Bookshelf are in non-decreasing order.
    * (Note: this is an accessor; it does not change the bookshelf.)
    */
   public boolean isSorted() {

      for(int i=0; i<piles.size()-1; ++i) {

         if(piles.get(i+1)<piles.get(i)) {

            return false;
         }
      }

      assert isValidBookshelf();

      return true;

   }

   /**
    * Returns true iff the Bookshelf data is in a valid state.
    * (See representation invariant comment for more details.)
    */

   private boolean isValidBookshelf() {

      // the input height must greater than 0
      if(piles==null) {

         return false;

      }

      for(int i=0; i<piles.size(); ++i) {

         if(piles.get(i) <= 0) {

            return false;

         }
      }

      return true;

   }
}

