package PA2;

import java.util.*;

public class BookshelfTester {

   public static void main(String[] args) {
      // TODO Auto-generated method stub

      ArrayList<Integer> testArr = new ArrayList();
      List list = Arrays.asList(2,2);
      testArr.addAll(list);

      System.out.println("-----------------------");
      System.out.println("ex2:");
      Bookshelf bs1 = new Bookshelf();
      Bookshelf bs2 = new Bookshelf(testArr);

      System.out.println(bs1.toString());
      System.out.println(bs2.toString());
      System.out.println("-----------------------");
      System.out.println();

      System.out.println("-----------------------");
      System.out.println("ex3:");
      bs1.addFront(3);
      bs2.addFront(3);
      bs1.addLast(3);
      bs2.addLast(3);

      System.out.println("bs1 2 AddFront bs1 2 AddLast");
      System.out.println(bs1.toString());
      System.out.println(bs2.toString());
      System.out.println("Remove bs1 Front bs2 Last");
      bs1.removeFront();
      bs2.removeLast();

      System.out.println(bs1.toString());
      System.out.println(bs2.toString());
      System.out.println("-----------------------");
      System.out.println();

      System.out.println("-----------------------");
      System.out.println("ex4:");
      System.out.println("The height of index 1 in bs2: "+bs2.getHeight(1));
      System.out.println("If bs2 is sorted: "+bs2.isSorted());
      System.out.println("The size of bs2: "+bs2.size());
      System.out.println("-----------------------");
   }

}

