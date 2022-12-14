package PA2;

import java.util.*;


public class TestAssert {

   public static void main(String[] args) {
      // TODO Auto-generated method stub
      ArrayList<Integer> goodArr = new ArrayList();
      goodArr.add(1);
      goodArr.add(3);

      ArrayList<Integer> badArr = new ArrayList();
      badArr.add(0);
      badArr.add(-5);

      Bookshelf bs1 = new Bookshelf(goodArr);
      Bookshelf bs2 = new Bookshelf(badArr);

      bs2.addFront(-2);


      /**
       try {
       System.out.println(bs1.toString());
       System.out.println(bs2.toString());
       //System.out.println(bs2.isValidBookshelf());
       //System.out.println(bs1.);

       }catch(Exception e){
       System.out.println("Bad Array");
       }
       */

   }

}

