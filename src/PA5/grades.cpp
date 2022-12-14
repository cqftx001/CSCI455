/*
 * grades.cpp
 * A program to test the Table class.
 * How to run it:
 *      grades [hashSize]
 * 
 * the optional argument hashSize is the size of hash table to use.
 * if it's not given, the program uses default size (Table::HASH_SIZE)
 *
 */

#include "Table.h"

// cstdlib needed for call to atoi
#include <cstdlib>

using namespace std;

int main(int argc, char * argv[]) {

   // gets the hash table size from the command line

   int hashSize = Table::HASH_SIZE;

   Table * grades;  // Table is dynamically allocated below, so we can call
   // different constructors depending on input from the user.

   if (argc > 1) {
      
      hashSize = atoi(argv[1]);  // atoi converts c-string to int

      if (hashSize < 1) {
         
         cout << "Command line argument (hashSize) must be a positive number"
              << endl;
         
         return 1;
         
      }

      grades = new Table(hashSize);

   }
   
   else {   // no command line args given -- use default table size
      
      grades = new Table();
      
   }


   grades->hashStats(cout);

   // add more code here
   // Reminder: use -> when calling Table methods, since grades is type Table*
   string operation, name;
   
   int score;
   
   cout<<"cmd>";
   
   cin >> operation;
   
   //keep inputting the command until "quit" is met, different operations will be made depends on the specific command
   while(operation != "quit") {
      
      if(operation == "insert") {
         
         cin >> name >> score;
         
         if(!grades->insert(name, score)) 
            
         {
            cout << "name is already present" << endl;
            
         }
      }
      
      else if(operation == "change") {
         
         cin >> name >> score;
         
         int * valPointer = grades->lookup(name);
         
         if(valPointer == NULL) {
            
            cout << "name is not present" << endl;
            
         }
         else {
            
            *valPointer = score;
            
         }
      }
      
      else if(operation == "lookup") {
         
         cin >> name;
         
         int * valPointer = grades->lookup(name);
         
         if(valPointer == NULL) {
            
            cout << "name is not present" << endl;
            
         }
         else {
            
            cout << "score: " << *valPointer << endl;
            
         }
      }
      
      else if(operation == "remove") {
         
         cin >> name;
         
         if(!grades->remove(name)) {
            
            cout << "name is not present" << endl;
            
         }
         
      }
      
      else if(operation == "print") {
         
         grades->printAll();
         
      }
      else if(operation == "size") {
         
         cout << grades->numEntries() << endl;
         
      }
      else if(operation == "stats") {
         
         grades->hashStats(cout);
         
      }
      else if(operation == "help") {
         
         cout << "Valid Commands: " << endl
            
            << "insert name score" << endl
            
            << "change name newscore" << endl
            
            << "lookup name" << endl
            
            << "remove name" << endl
            
            << "print" << endl
            
            << "size" << endl
            
            << "stats" << endl
            
            << "help" << endl
            
            << "quit" << endl;
      }
      else {
         
         cout << "ERROR: invalid command" << endl;
         
      }
      cout << "cmd>";
      
      cin >> operation;
      
   }
   
   return 0;
   
}
