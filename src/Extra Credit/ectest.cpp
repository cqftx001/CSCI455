#include <iostream>
#include <string>
#include <string.h>

// for istringstream used in buildList (defined below)
#include <sstream>

#include "ecListFuncs.h"

typedef Node* ListType;

using namespace std;


// You may use the following two utility functions that will make it easier to
// test your list functions on hard-coded list data and compare it to expected
// output:
// (function definitions for them appear at the bottom of this file)


/*
 * listToString
 *
 * PRE: list is a well-formed list.
 *
 * converts the list to a string form that has the following format shown by example.
 * the list is unchanged by the function.
 *
 *   string format:
 *
 *   "()"        an empty list
 *   "(3)        a list with one element, 3
 *   "(3 4 5)"   a list with multiple elements: 3 followed by 4 followed by 5
 *
 */
string listToString(ListType list);


/*
 * buildList
 * 
 * PRE: listString only contains numbers (valid integer format) and spaces
 *
 * creates and returns a linked list from a string of space separated numbers
 * 
 *
 * Examples:
 *  listString         return value of buildList(listString)
 *
 *    ""               ()
 *    "-32"            (-32)
 *    "     -32   "    (-32)
 *    "1 3 2"          (1 3 2)
 *    "  1 3 2"        (1 3 2)
 *
 */
ListType buildList(const string & listString);
void testmethod1();
void testmethod2();
void testmethod3();



int main ()
{
   void testmethod1();
   void testmethod2();
   void testmethod3();
   
   return 0;
}



/*********************************************************
 * Utility function definitions
 *
 */
void testmethod1(){
   ListType test1 = buildList("");
   ListType test2 = buildList("1 2 3 4 5");
   ListType test3 = buildList("1 1 1 2");
   ListType test4 = buildList("1 1 2 2");
   
   int result1 = numAdjDupes(test1);
   int result2 = numAdjDupes(test2);
   int result3 = numAdjDupes(test3);
   int result4 = numAdjDupes(test4);
   
   cout<<"--------------------Testing numAdjDupes!---------------------\n"<<endl;
   cout<<"Test1:  Expected: 0"<<result1<<"\n";
   cout<<"Test2:  Expected: 0"<<result2<<"\n";
   cout<<"Test3:  Expected: 1"<<result3<<"\n";
   cout<<"Test4:  Expected: 2"<<result4<<"\n";

}

void testmethod2(){
   ListType test1 = buildList("");
   ListType test2 = buildList("1 2 3 4 5");
   ListType test3 = buildList("3 6 9");
   ListType test4 = buildList("3 6 9");
   /* if could change the method type 
   ListType result1 = removeDiv(test1, 2);
   ListType result2 = removeDiv(test2, 2);
   ListType result3 = removeDiv(test3, 3);
   ListType result4 = removeDiv(test4, 2);
   */ 
   cout<<"--------------------Testing removeDiv!---------------------\n"<<endl;
   cout<<"Test1:  Expected: ()"<<"\n";
   if(listToString(test1).compare("()") == 0){
      cout<<"Go as expected!\n";
   } 
   else{
      cout<<"Something went wrong!\n";
   }
   
   cout<<"Test2:  Expected: (1 3 5)"<<"\n";
   if(listToString(test2).compare("(1 3 5)") == 0){
      cout<<"Go as expected!\n";
   } 
   else{
      cout<<"Something went wrong!\n";
   }
   
   cout<<"Test3:  Expected: ()"<<"\n";
   if(listToString(test3).compare("()") == 0){
      cout<<"Go as expected!\n";
   } 
   else{
      cout<<"Something went wrong!\n";
   }
   
   cout<<"Test4:  Expected: (3 6 9)"<<"\n";
   if(listToString(test4).compare("(3 6 9)") == 0){
      cout<<"Go as expected!\n";
   } 
   else{
      cout<<"Something went wrong!\n";
   }
   

}

void testmethod3(){
   ListType test1 = buildList("");
   ListType left1 = nullptr;
   ListType right1 = nullptr;
   splitAtLoc(test1, 2, left1, right1);
   
   ListType test2 = buildList("1 2 3 4 5");
   ListType left2 = nullptr;
   ListType right2 = nullptr;
   splitAtLoc(test2, 0, left2, right2);
   
   ListType test3 = buildList("1 2 3 4 5");
   ListType left3 = nullptr;
   ListType right3 = nullptr;
   splitAtLoc(test3, 4, left3, right3);
   
   ListType test4 = buildList("1 2 3 4 5");
   ListType left4 = nullptr;
   ListType right4 = nullptr;
   splitAtLoc(test4, 6, left3, right3);
   
   cout<<"--------------------Testing removeDiv!---------------------\n"<<endl;
   cout<<"Test1:  Expected: (1 2) (4 5)"<<left1<<" "<<right1<<"\n";
   cout<<"Test2:  Expected: () (2 3 4 5)"<<left2<<" "<<right2<<"\n";
   cout<<"Test3:  Expected: (1 2 3 4) ()"<<left3<<" "<<right3<<"\n";
   cout<<"Test4:  Expected: (1 2 3 4 5) ()"<<left4<<" "<<right4<<"\n";
}
string listToString(ListType list) {

   string listString = "(";

   if (list == NULL) {
      listString += ")";
      return listString;
   }

   Node *p = list;
   while (p->next != NULL) {
      listString += to_string(p->data) + " ";
      p = p->next;
   }

   // print last one with no trailing space
   listString += to_string(p->data) + ")";

   return listString;

}   


ListType buildList(const string & listString) {

   ListType nums = NULL;

   istringstream istr(listString);  // similar to a Java Scanner over a String

   int num;

   if (istr >> num) { // is there one value there?
      nums = new Node(num);
   }
   else {
      return NULL;
   }

   Node *last = nums;

   while (istr >> num) { 
      last->next = new Node(num);
      last = last->next;
   }

   return nums;
}
