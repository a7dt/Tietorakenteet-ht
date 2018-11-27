import java.io.*;

public class hashtableManip {
   
   // Luodaan oliot itse toteutetusta HashTable-luokasta.
   HashTable a = new HashTable();
   HashTable b = new HashTable();
   
   /* Metodi, joka alustaa hajautustaulut.
    */
   public void init(int[] t1, int[] t2) throws Exception {
      
      // Taulukon "oikeat koot" laskureiden avulla. Näitä kasvatetaan
      // jos taulukon alkion arvo ei ole alustettu virhekoodi -1.
      // Fyysisesti t1 ja t2 ovat 10000 alkion taulukoita. Lisätään hajautus-
      // tauluun vain taulukon alkiot, joita ei ole alustettu arvolla -1 tai
      // jotka eivät ole lukua 9999 suurempia.
      
      int koko1 = -1;
      int koko2 = -1;
      
      for(int i = 0; i < t1.length; i++) {
         if(t1[i] != -1) {
            
            if(t1[i] > 9999 || t1[i] < 0) {
               throw new Exception("Arvoalue ohitettu!");
            }
            koko1++;
            
            // t1[i] on avain, eli taulukossa oleva luku. i on arvo, eli
            // tässä sen rivin indeksi, jolla luku oli.
            a.addToHash(t1[i], i);
         }
      }
      
      for(int i = 0; i < t2.length; i++) {
         if(t2[i] != -1) {
            
            if(t2[i] > 9999 || t2[i] < 0) {
               throw new Exception("Arvoalue ohitettu!");
            }  
            koko2++;
            
            // t2[i] on avain, eli taulukossa oleva luku. i on arvo, eli
            // tässä sen rivin indeksi, jolla luku oli.
            b.addToHash(t2[i], i);
         }
      }
      
      // Tulostetaan taulukoiden koot.
      System.out.println("Tiedostojen numeroiden lukumäärät:\n");
      System.out.println("A: " + koko1);
      System.out.println("B: " + koko2 + "\n");
   }
   
   
   /* Metodi, joka poistaa avaimen hajautustaulusta
    */
   public boolean removeKey(int key) {
      
      boolean one;
      boolean two;
      
      // Hyödynnetään oman HashTable-luokan poistometodia.
      one = a.removeFromHash(key);
      two = b.removeFromHash(key);
      
      // Jos poistettava alkio löytyi, palautuu true, muuten false.
      return (one || two);
      
   }
   
   
   /*
    * Suorittaa harjoitustyössä vaadittavat joukko-operaatiot. Kirjoittaa
    * tiedostot.
    */
   public void getUnionCompAndXor() {
      
      /*
       *  Hajautustaulussa: Avain = luku itse, Arvo = rivi, jolla luku on tekstitiedostossa.
       */
      
      try {
         
         PrintWriter A = new PrintWriter("or.txt");
         PrintWriter B = new PrintWriter("and.txt");
         PrintWriter C = new PrintWriter("xor.txt");
      
         // Kaksi aputaulukkoa
         int[] uniontable = new int[10000];  
         int[] xortable = new int[10000];
         
         // Hajautustaulun koko voi olla 10000, joten silmukassa on käytävä
         // luvut läpi väliltä 0-9999.
         for(int i = 0; i < 10000; i++) {
         
            // Alustetaan virhekoodilla.
            xortable[i] = -1;
         
            // GetAmountOfKey-metodi palauttaa, montako i-avaimista lukua hajautus
            // taulusta löytyi. -1 tarkoittaa, ettei yhtäkään.
            
            // Jos molemmista löytyi luku, lasketaan esiintymät yhteen.
            if(a.getAmountOfKey(i) != -1 && b.getAmountOfKey(i)!= -1) {
               uniontable[i] = a.getAmountOfKey(i) + b.getAmountOfKey(i);
            }           
            
            // Tämä ehto pätee, kuin vain toisesta hajautustaulusta löytyi tietty luku.
            else if(a.getAmountOfKey(i) != -1 && b.getAmountOfKey(i) == -1) {
               uniontable[i] = a.getAmountOfKey(i);
            }
            
            // Tämä ehto pätee, kuin vain toisesta hajautustaulusta löytyi tietty luku.
            else {
               uniontable[i] = b.getAmountOfKey(i);
            }
         
            // Tässä xortableen lisätään vain sellainen i:n arvo, joka oli vain joko hajautustaulusaa A tai joukossa B,
            // ei molemmissa.
            if
            ( 
               (!(a.getAmountOfKey(i) != -1 && b.getAmountOfKey(i) != -1)) && 
               (a.getAmountOfKey(i) != -1 || b.getAmountOfKey(i) != -1)  
            )
               xortable[i] = i;
         
         }
      
      
         // Tulostetaan OR tiedostoon
         for(int i = 0; i < 10000; i++) {
            if(uniontable[i] != -1) {
               // i = avain, uniontable[i] = esiintymien lkm
               A.format("%4d %4d", i, uniontable[i]);
               A.println();
            }
         }
         
         // Tulostetaan AND tiedostoon
         for(int i = 0; i < 10000; i++) {
            if(uniontable[i] != -1 && xortable[i] == -1) {
               // getValue-metodi palauttaa rivin numeron, josta luku löytyi tekstitiedostossa.
               // indeksointi alkaa nollasta.
               if(a.getValue(i) != -1)
                  B.format("%4d %4d", i, (a.getValue(i) + 1));
               B.println();
            }
         }
      
      
         // Tulostetaan XOR tiedostoon
         for(int i = 0; i < 10000; i++) {
            // xortable[i] = avain, joka löytyi vain toisesta tekstitiedostosta
            if(xortable[i] != -1) {
               
               // Jos a-hajautustaulussa, symboliksi "1"
               if(a.getValue(i) != -1) {
                  C.format("%4d %4d", i, 1);
                  C.println();
               }
               
               // muuten symboliksi "2"
               else {
                  C.format("%4d %4d", i, 2);
                  C.println();
               }
            }
         }
         
         System.out.println();
         System.out.println("Tekstitiedostojen kirjoitus onnistui.");
         
         A.close();
         B.close();
         C.close();
      }
      catch(Exception e) {
         e.printStackTrace();
      }
   }
}
