// pa5list.cpp
// a program to test the linked list code necessary for a hash table chain

// You are not required to submit this program for pa5.

// We gave you this starter file for it so you don't have to figure
// out the #include stuff.  The code that's being tested will be in
// listFuncs.cpp, which uses the header file listFuncs.h

// The pa5 Makefile includes a rule that compiles these two modules
// into one executable.

#include <iostream>
#include <string>
#include <cassert>

using namespace std;

#include "listFuncs.h"


int main() {
   
   //construct lists
   ListType list1 = NULL;
   
   ListType list2 = new Node("A", 1, new Node("B", 2, new Node("C", 3)));
   
   
   //print the lists
   printList(list1);
   
   printList(list2);
   
   
   //test remove
   cout<< "remove \"D\" in list1 [Exp: false] :" << listRemove(list1, "D") << endl;
   
   printList(list1);
   
   cout<< "remove \"D\" in list2 [Exp: false] :" << listRemove(list2, "D") << endl;
   
   printList(list2);
   
   cout<< "remove \"B\" in list2 [Exp: true] :" << listRemove(list2, "B") << endl;
   
   printList(list2);
   
   cout<< "remove \"C\" in list2 [Exp: true] :" << listRemove(list2, "C") << endl;
   
   printList(list2);
   
   cout<< "remove \"A\" in list2 [Exp: true] :" << listRemove(list2, "A") << endl;
   
   printList(list2);
   
   
   //test append
   cout<< "append \"A\", 10 in list1 [Exp: true] :" << listAppend(list1, "A", 10) << endl;
   
   printList(list1);
   
   cout<< "append \"B\", 20 in list1 [Exp: true] :" << listAppend(list1, "B", 20) << endl;
   
   printList(list1);
   
   cout<< "append \"B\", 30 in list1 [Exp: false] :" << listAppend(list1, "B", 30) << endl;
   
   printList(list1);
   
   delete list2;
   
   list2 = new Node("A", 1, new Node("B", 2, new Node("C", 3)));
   
   cout<< "append \"B\", 20 in list2 [Exp: false] :" << listAppend(list2, "B", 20) << endl;
   
   printList(list2);
   
   cout<< "append \"D\", 4 in list2 [Exp: true] :" << listAppend(list2, "D", 4) << endl;
   
   printList(list2);
   
   
   return 0;
}
