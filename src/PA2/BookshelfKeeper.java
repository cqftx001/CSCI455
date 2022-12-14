package PA2;

import java.util.*;
/**
 * Class BookshelfKeeper
 *
 * Enables users to perform efficient putPos or pickHeight operation on a bookshelf of books kept in
 * non-decreasing order by height, with the restriction that single books can only be added
 * or removed from one of the two *ends* of the bookshelf to complete a higher level pick or put
 * operation.  Pick or put operations are performed with minimum number of such adds or removes.
 */
public class BookshelfKeeper {

   /**
    Representation invariant:

    <put rep. invar. comment here>

    */

   // <add instance variables here>

   //   total is the total number of operations
   //   num   is the number of operations for one call of mutator

   private Bookshelf bookshelf;

   private int total;

   private int num;


   /**
    * Creates a BookShelfKeeper object with an empty bookshelf
    */
   public BookshelfKeeper() {

      bookshelf = new Bookshelf();

      total = 0;

      num = 0;

      assert isValidBookshelfKeeper();
   }

   /**
    * Creates a BookshelfKeeper object initialized with the given sorted bookshelf.
    * Note: method does not make a defensive copy of the bookshelf.
    *
    * PRE: sortedBookshelf.isSorted() is true.
    */
   public BookshelfKeeper(Bookshelf sortedBookshelf) {

      bookshelf = sortedBookshelf;

      total = 0;

      num = 0;

      assert isValidBookshelfKeeper();
   }

   /**
    * Removes a book from the specified position in the bookshelf and keeps bookshelf sorted
    * after picking up the book.
    *
    * Returns the number of calls to mutators on the contained bookshelf used to complete this
    * operation. This must be the minimum number to complete the operation.
    *
    * PRE: 0 <= position < getNumBooks()
    */
   public int pickPos(int position) {

      num = 0;

      final int FRONT = 0;

      final int LAST = 1;

      // Rearrange books from the front
      if(position < bookshelf.size() / 2) {

         int direction = FRONT;

         Bookshelf temp = countRemove(bookshelf, position, direction);

         temp.removeLast();

         countBack(temp, direction);

         total = total + num;

         assert isValidBookshelfKeeper();

         return num;
      }

      // Rearrange books form the back
      else {

         int direction = LAST;

         int size = bookshelf.size();

         Bookshelf temp = countRemove(bookshelf, position, direction);

         bookshelf.removeLast();

         num++;

         countBack(temp, direction);

         total = total + num;

         assert isValidBookshelfKeeper();

         return num;
      }
   }



   /**
    * Inserts book with specified height into the shelf.  Keeps the contained bookshelf sorted
    * after the insertion.
    *
    * Returns the number of calls to mutators on the contained bookshelf used to complete this
    * operation. This must be the minimum number to complete the operation.
    *
    * PRE: height > 0
    */
   public int putHeight(int height) {

      num = 0;

      final int FRONT = 0;

      final int LAST = 1;

      // Find the insertion position
      int position = findPosition(height);

      // Insert from the front
      if(position < bookshelf.size() / 2) {

         int direction = FRONT;

         Bookshelf temp = countRemove(bookshelf, position, direction);

         bookshelf.addFront(height);

         num++;

         countBack(temp, direction);

         total = total + num;

         assert isValidBookshelfKeeper();

         return num;
      }

      // Insert from the back
      else {

         int direction = LAST;

         Bookshelf temp = countRemove(bookshelf, position, direction);

         bookshelf.addLast(height);

         num++;

         countBack(temp, direction);

         total = total + num;

         assert isValidBookshelfKeeper();

         return num;
      }
   }

   /**
    * Returns the total number of calls made to mutators on the contained bookshelf
    * so far, i.e., all the ones done to perform all of the pick and put operations
    * that have been requested up to now.
    */
   public int getTotalOperations() {

      assert isValidBookshelfKeeper();

      return total;
   }

   /**
    * Returns the number of books on the contained bookshelf.
    */
   public int getNumBooks() {

      assert isValidBookshelfKeeper();

      return bookshelf.size();
   }

   /**
    * Returns string representation of this BookshelfKeeper. Returns a String containing height
    * of all books present in the bookshelf in the order they are on the bookshelf, followed
    * by the number of bookshelf mutator calls made to perform the last pick or put operation,
    * followed by the total number of such calls made since we created this BookshelfKeeper.
    *
    * Example return string showing required format: “[1, 3, 5, 7, 33] 4 10”
    *
    */
   public String toString() {

      String heightOfBooks = bookshelf.toString() + " " + num + " " + total;

      assert isValidBookshelfKeeper();

      return heightOfBooks;
   }

   /**
    * Returns true iff the BookshelfKeeper data is in a valid state.
    * (See representation invariant comment for details.)
    */
   private boolean isValidBookshelfKeeper() {

      for(int i = 0; i < bookshelf.size()-1; ++i){

         if (bookshelf.getHeight(i) > bookshelf.getHeight(i + 1)){

            return false;
         }

      }

      return true;
   }

   // add any other private methods here

   /**
    * binary search
    * return the position of the biggest bookheight comparatively (less than target)
    */
   private int search1(int height) {

      int high = bookshelf.size();

      int low = 0;

      while (high - low > 0) {

         int mid = (low + high) / 2;

         if (height < bookshelf.getHeight(mid)) {

            high = mid;
         }

         else {

            low = mid + 1;
         }
      }

      return --low;
   }

   /**
    * returns the position of the biggest bookheight that is smaller than target
    */
   private int search2(int height) {

      int high = bookshelf.size();

      int low = 0;

      while (high - low > 0) {

         int mid = (low + high) / 2;

         // less or equals to the mid's height
         if (height <= bookshelf.getHeight(mid)) {

            high = mid;
         }

         else {

            low = mid + 1;
         }
      }

      return --low;
   }
   /**
    * returns the position that will be used for insertion
    */
   private int findPosition(int height) {

      int position1 = search1(height);

      int position2 = search2(height);

      int position = 0;

      if (bookshelf.size() - position1 - 1 <= position2 + 1) {

         position = position1;
      }

      else {

         position = position2;
      }

      return position;
   }

   /**
    * returns the temporary Bookshelf Object that stores the values removed from original            * Bookshelf object and count the operations
    */
   private Bookshelf countRemove(Bookshelf bookshelf, int position, int direction) {

      Bookshelf temp = new Bookshelf();

      if(direction == 0) {

         for(int i = 0; i <= position; i++) {

            temp.addLast(bookshelf.removeFront());

            num++;
         }
      }
      if (direction == 1) {

         int size = bookshelf.size();

         for(int i = position + 1; i < size; i++) {

            temp.addLast(bookshelf.removeLast());

            num++;
         }
      }

      return temp;
   }
   /**
    * add the elements of temporary Bookshelf Object back to the original Bookshelf object
    * and count the operations
    */
   private void countBack(Bookshelf temp, int direction) {

      int size = temp.size();

      if (direction == 0) {

         for(int i = 0; i < size; i++) {

            bookshelf.addFront(temp.removeLast());

            num++;
         }
      }
      if (direction == 1) {

         for(int i = 0; i < size; i++) {

            bookshelf.addLast(temp.removeLast());

            num++;
         }
      }
   }

}
