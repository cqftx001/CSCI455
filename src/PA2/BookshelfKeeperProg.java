package PA2;

import java.util.*;

/**
 * class BookshelfKeeperProg


 * This class takes user's input and does error checkings.
 */

public class BookshelfKeeperProg {

   public static void main(String[] args) {

      Scanner in = new Scanner(System.in);

      System.out.println("Please enter initial arrangement of books followed by newline:");

      // read the string of books, construct a new scanner only containing the string
      Scanner input = new Scanner(in.nextLine());

      // convert string to arraylist
      ArrayList<Integer> pileOfBook = readArrayList(input);

      // do error checking for the arraylist of books
      if (!checkPositive(pileOfBook)) {

         return;
      }
      if (!checkOrder(pileOfBook)) {

         return;
      }

      Bookshelf bookshelf = new Bookshelf(pileOfBook);

      BookshelfKeeper bookshelfKeeper = new BookshelfKeeper(bookshelf);

      System.out.println(bookshelfKeeper.toString());

      System.out.println("Type pick <index> or put <height> followed by newline. Type end to exit.");

      // do input operations until meet "end" or meet EOF
      while (in.hasNext()) {

         if (!doOperation(in.next(), in, bookshelfKeeper)) {

            return;
         }
      }
   }

   /** Returns the converted arraylist of scanned book strings
    */
   private static ArrayList<Integer> readArrayList(Scanner input) {

      ArrayList<Integer> pileOfBook = new ArrayList<Integer>();

      while (input.hasNextInt()) {

         pileOfBook.add(input.nextInt());

      }

      return pileOfBook;
   }

   /** Do the input operation according to the command type
    * and do error checking
    */
   private static boolean doOperation(String operation, Scanner in, BookshelfKeeper bookshelfKeeper) {

      if (!checkCommand(operation)) {

         return false;
      }

      else if (operation.equals("put")) {

         int height = in.nextInt();

         if (!checkPut(height)) {

            return false;
         }

         bookshelfKeeper.putHeight(height);

         System.out.println(bookshelfKeeper.toString());
      }

      else if (operation.equals("pick")) {

         int index = in.nextInt();

         int size = bookshelfKeeper.getNumBooks();

         if (!checkPick(index, size)) {

            return false;
         }

         bookshelfKeeper.pickPos(index);

         System.out.println(bookshelfKeeper.toString());
      }

      else if (operation.equals("end")) {

         System.out.println("Exiting Program.");

         return false;
      }

      return true;
   }

   // Check if the input books are all positive
   private static boolean checkPositive(ArrayList<Integer> pileOfBook) {

      for (int i = 0; i < pileOfBook.size(); i++) {

         if (pileOfBook.get(i) <= 0) {

            System.out.println("ERROR: Height of a book must be positive.");

            System.out.println("Exiting Program.");

            return false;
         }
      }

      return true;
   }

   //Check if the input books are in non-decreasing order
   private static boolean checkOrder(ArrayList<Integer> pileOfBook) {

      for (int i = 0; i < pileOfBook.size() - 1; i++) {

         if (pileOfBook.get(i) > pileOfBook.get(i + 1)) {

            System.out.println("ERROR: Heights must be specified in non-decreasing order.");

            System.out.println("Exiting Program.");

            return false;
         }
      }

      return true;
   }

   //Check the validity of input command
   private static boolean checkCommand(String operation) {

      if (!operation.equals("put") && !operation.equals("pick") && !operation.equals("end")) {

         System.out.println("ERROR: Invalid command. Valid commands are pick, put, or end.");

         System.out.println("Exiting Program.");

         return false;
      }

      return true;
   }


   // Check the validity of "pick"
   private static boolean checkPick(int index, int size) {

      if (index < 0 || index >= size) {

         System.out.println("ERROR: Entered pick operation is invalid on this shelf.");

         System.out.println("Exiting Program.");

         return false;
      }

      return true;
   }

   // Check the validity of "put"
   private static boolean checkPut(int height) {

      if (height <= 0) {

         System.out.println("ERROR: Height of a book must be positive.");

         System.out.println("Exiting Program.");

         return false;
      }

      return true;
   }
}

