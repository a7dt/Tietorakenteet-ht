public class LinkedList {
   
   public ListNode head;
   public ListNode tail;
   public int count;
   
   public LinkedList() {   
      head = tail = null;
      count = 0;  
   }
   
   public boolean isEmpty() {
      return count == 0;
   }
   
   // Lisää alkion linkitetyn listan loppuun.
   public void add(ListNode newNode) {
         
      if(head == null) {
         head = tail = newNode;
      }
      else {   
         tail.next = newNode;
         tail = newNode;
      }
      
      count++;
      
   }
   
   
   // Palauttaa tietyn arvon omaavien solmujen lukumäärän.
   public int getAmount(int k) {
      
      int numOfSeen = 0;
         
      ListNode temp = head;
      
      while(temp != null) {
         
         if(temp.key == k) {
            numOfSeen++;
         }     
         temp = temp.next;
      }
      return numOfSeen;
   }
   
   
   // Kertoo, onko tietyn arvon omaava solmu listassa.
   public int getVal(int k) {
      
      ListNode temp = head;
      
      while(temp != null) {
         
         if(temp.key == k) {
            return temp.value;
         }
      }
      return -1;
   }
   
   
   // Poistaa tietyn arvon omaavan solmun listasta.
   public void remove(int k) {
      
      if(count == 0) {
         return;
      }
      else {
         
         ListNode temp = head;
         int tempCounter = 0;
         
         while(temp != null) {
            
            if(temp.key == k) {
               
               // Tapaus, kun lista tyhjenee kokonaan.
               if(temp == head && count == 1) {
                  head = tail = null;
                  count--;
               }
               
               // Listan nykyinen pää poistetaan.
               else if(tempCounter == 0 && count > 1) {
                  head = head.next;
                  count--;
               }
               
               // Listan nykyinen häntä poistetaan.
               else if(tempCounter == count-1 && count > 1) {
                  
                  ListNode tmp = head;
                  
                  while(tmp.next.next != null) {
                     tmp = tmp.next;
                  }
                  
                  tmp.next = null;
                  tail = tmp;
                  
                  count--;
                  
               }
               
               // Listasta poistetaan arvo "keskeltä"
               else {
                  ListNode tmp = head;
                  
                  while(tmp.next != temp) {
                     tmp = tmp.next;
                  }
                  
                  ListNode remove = tmp.next;
                  tmp.next = remove.next;
                  
                  count--;
               }  
            }
            
            tempCounter++;
            temp = temp.next;
         }
      }
   }
}
