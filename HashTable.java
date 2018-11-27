public class HashTable {
   
   // Attribuutit, hajautustaulun koko ja taulukko, jonka alkioiden tietotyyppinä
   // on linkitetty lista.
   public final int SIZE = 10000;
   LinkedList[] hashtable;
   
   
   // Rakentaja, luo hajautustaulun ja alustaa alkiot null-arvoiksi.
   public HashTable() {
      
      hashtable = new LinkedList[SIZE];
      
      for(int i = 0; i < SIZE; i++) {
         hashtable[i] = null;
      }
   }
   
   // Lisää int-tyyppisen avaimen hajautustauluun.
   public void addToHash(int key, int value) {
      
      // Hajautusfunktion tulos hajautusarvoksi
      int hash = (key % SIZE);
      
      if(hashtable[hash] == null) {
         hashtable[hash] = new LinkedList();
      }
      
      // Luodaan avain-arvo - olio
      ListNode n = new ListNode(key, value);
      
      // Lisätään hajautustauluun
      hashtable[hash].add(n);
   }
   
   
   // Palauttaa tietyn avaimen omaavien alkioiden lukumäärän tai -1 jos kyseisen
   // avaimen omaavia alkioita ei löytynyt.
   public int getAmountOfKey(int key) {
      
      int hash = (key % SIZE);
      
      if(hashtable[hash] == null) {
         return -1;
      }
      
      else {
         int val = hashtable[hash].getAmount(key);
         return val;
      }
   }
   
   
   // Palauttaa avain-arvo parista arvon.
   public int getValue(int key) {
      
      int hash = (key % SIZE);
      
      if(hashtable[hash] == null) {
         return -1;
      }
      else {
         int val = hashtable[hash].getVal(key);
         return val;
      }
      
   }
   
   
   // poistaa tietyn avaimen omaavan alkion hajautustaulusta.
   public boolean removeFromHash(int key) {
      
      int hash = (key % SIZE);
      
      if(hashtable[hash] == null) {
         return false;
      }
      else {
         hashtable[hash].remove(key);
      }
      
      // Jos hajautustaulun alkiot tietyssä indeksissä tyhjenee,
      // poistetaan linkitetty lista kyseisestä indeksistä.
      if(hashtable[hash].isEmpty()) {
         hashtable[hash] = null;
      }
      
      return true;
   }
}
