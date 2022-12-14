#include <string>
#include <cassert>
#include<stdio.h>
#include "ecListFuncs.h"
#include <cstddef>
#include <iostream>

using namespace std;

// *********************************************************
// Node constructors: do not change
Node::Node(int item) { 
   data = item;
   next = NULL;
}

Node::Node(int item, Node *n) {
   data = item;
   next = n;
}
// *********************************************************


int numAdjDupes(ListType list) {
   
   Node *listptr = list;
   int count;
   int flag = 0;
   if(listptr == NULL || listptr->next == NULL){
      count = 0;
      return count;
   }
   else{
      while(listptr->next->next != NULL){
         if(listptr->data == listptr->next->data){
            if(flag == 0){
               count++;
               flag = 1;
            }
         }
         else{
            flag = 0;
         }
         
      }
   }
  return count;
}



void removeDiv(ListType & list, int k) {
   int t = k;
   Node *prev = list;
   Node *curr = list->next;
   if(prev == NULL){}
   else if(curr == NULL){
      if((prev->data %t == 0)){
         list=curr;
         delete prev;

      }
   }
   else{
      while(curr != NULL){
         if((prev->data)%t == 0){
            list=curr;
            delete prev;
            curr=curr->next;
            prev=list;
         }
         else if((curr->data)%t == 0){
            prev->next = curr->next;
            delete curr;
            curr = prev->next;
         }
         prev = curr;
         curr = prev->next;
      }
   }
}



void splitAtLoc(ListType &list, int index, ListType &a, ListType &b) {
   int count = 0;
   int firstHalf = 1;
   ListType newList = list;
   ListType newa;
   ListType newb;
   
   while(newList){
      if(count == index){
         firstHalf = 0;
      }
      else{
         if(firstHalf == 1){
            if(!a){
               a = new Node(newList->data, nullptr);
               newa = a;
            }
            else{
               newa->next = new Node(newList->data, nullptr);
               newa = newa->next;
            }
         }
         else{
            if(!b){
               b = new Node(newList->data, nullptr);
               newb = b;
            }
            else{
               newb->next = new Node(newList->data, nullptr);
               newb = newb->next;
            }
         }
      }
      count++;
      newList = newList->next;
   }

}
