#include <iostream>

#include <cassert>

#include "listFuncs.h"

using namespace std;

Node::Node(const string &theKey, int theValue) {
   key = theKey;
   value = theValue;
   next = NULL;
}

Node::Node(const string &theKey, int theValue, Node *n) {
   key = theKey;
   value = theValue;
   next = n;
}




//*************************************************************************
// put the function definitions for your list functions below

bool listRemove(ListType & list, const string & target) {
   
   if(list == NULL) {
      
      return false;
      
   }
   
   ListType prev = NULL;
   
   ListType head = list;
   
   while(list->key != target) {
      
      prev = list;
      
      list = list->next;
      
      if(list == NULL) {
         
         list = head;
         
         return false;
      }
   }
   
   if(prev == NULL) {
      
      delete list;
      
      list = list->next;
      
      return true;
      
   }
   prev->next = list->next;
   
   delete list;
   
   list = head;
   
   return true;
   
}

bool listAppend(ListType & list, const string & target, int val) {
   
   if(list == NULL) {
      
      list = new Node(target, val);
      
      return true;
      
   }
   ListType head = list;
   
   ListType prev = NULL;
   
   while(list != NULL) {
      
      if(list->key == target) {
         
         list = head;
         
         return false;
         
      }
      prev = list;
      
      list = list->next;
      
   }
   
   prev->next = new Node(target, val);
   
   list = head;
   
   return true;
   
}

void printList(ListType list) {
   
   while(list != NULL) {
      
      cout<<list->key<<" "<<list->value<<endl;
      
      list = list->next;
      
   }
}

unsigned int listSize(ListType list) {
   
   unsigned int size = 0;
   
   while(list != NULL) {
      
      size++;
      
      list = list->next;
      
   }
   
   return size;
   
}
