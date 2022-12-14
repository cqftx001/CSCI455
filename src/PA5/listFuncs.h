//*************************************************************************
// Node class definition 
// and declarations for functions on ListType

// Note: we don't need Node in Table.h
// because it's used by the Table class; not by any Table client code.

// Note2: it's good practice to *not* put "using" statement in *header* files.  Thus
// here, things from std libary appear as, for example, std::string

#ifndef LIST_FUNCS_H
#define LIST_FUNCS_H

#include <string>
  

struct Node {
   std::string key;
   int value;

   Node *next;

   Node(const std::string &theKey, int theValue);

   Node(const std::string &theKey, int theValue, Node *n);
};


typedef Node * ListType;

//*************************************************************************
// add function headers (aka, function prototypes) for your functions
// that operate on a list here (i.e., each includes a parameter of type
// ListType or ListType&).  No function definitions go in this file.

// remove the node with key == target
// return true for successful remove, false for no such key
bool listRemove(ListType & list, const std::string & target);

// add the node to the end of the list with key == target, value == val
// return true for successful append, false for an already present target
bool listAppend(ListType & list, const std::string & target, int val);

// print the list
void printList(ListType list);

// get the size of the list
// return the size of the list
unsigned int listSize(ListType list);



// keep the following line at the end of the file
#endif
