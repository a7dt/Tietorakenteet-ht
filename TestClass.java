import java.util.Scanner;
import java.io.*;
import java.io.IOException;
import java.lang.Integer;

public class TestClass {
   
   private hashtableManip h;
   
   
   /* Lukee tekstitiedoston int-alkioiseen taulukkoon
    */
   public int[] readInput(String fname) {
      
      try {
         
         int counter = 0;
         
         int[] returned = new int[10000];
         
         // Alustetaan taulukon alkiot virhearvolla -1. Oletetaan,
         // ettei tekstitiedostoissa ole negatiivisia arvoja.
         for(int i = 0; i < returned.length; i++)
            returned[i] = -1;
         
         File file = new File(fname);
         
         Scanner sc = new Scanner(file);
         
         // Luetaan luvut tiedostosta rivi kerrallaan taulukkoon. Tehdään tarvittava
         // tyyppimuunnos.
         while(sc.hasNextLine()) {
            returned[counter] = Integer.parseInt(sc.nextLine().trim());
            counter++;
         }
         
         sc.close();
         
         return returned;  
               
      }
      catch(IOException e) {
         System.out.println("Tiedostoa ei löydy.");
         System.exit(0);
      }
      
      return null;   
   }
   
   /* Pääoperaatio. Kutsuu tiedostoja käsittelevää metodia, lukee käyttäjältä
    * mahdollisesti poistettavan alkion. Kutsuu toisen luokan metodeja luodun
    * osaolion (HashtableManip) kautta.
    */
   public void init() {
         
      // Luodaan taulukot, johon luvut tekstitiedostoista luetaan.
      int[] setA = new int[10000];
      int[] setB = new int[10000];
      
      // Luetaan tekstitiedostot taulukoihin.
      setA = readInput("setA.txt");
      setB = readInput("setB.txt");
      
      Scanner lukija = new Scanner(System.in);
      
      h = new hashtableManip();
      
      // Alustaa hajautustaulut taulukon arvoilla. Jos virheellisiä
      // arvoja löydettiin, lopetetaan ohjelman suoritus.
      try {
         h.init(setA, setB);
      }
      catch(Exception e) {
         System.out.println(e);
         System.exit(0);
      }
      
      // Tulostetaan ohjeita.
      System.out.println("Ohjelmalle annettava lukuja välillä 0 - 9999\n");
      System.out.println("Anna poistettava avain (poistaa kaikki alkiot,joilla kys. avain)");
      
      try {
         int poistettava = lukija.nextInt();
         
         // Virheenkäsittely, poistettavan tulee olla oikealla välillä.
         if(poistettava < 0 || poistettava > 9999)
            throw new Exception();
         
         System.out.println();
         
         // Poistaa tietyn avaimen omaavat alkiot, jos täsmääviä löytyy tiedostoista.
         if(h.removeKey(poistettava) == true) {
            System.out.println("Poistettu avaimet arvolla: " + poistettava + ".");
         }
         else {
            System.out.println("Ei täsmääviä avaimia.");
         }
      }
      catch(Exception e) {
         System.out.println("Virheellinen syöte. Ei poistettuja alkioita.");
      }
      
      // Suorittaa joukko-operaatiot
      h.getUnionCompAndXor();
      
   }
}
