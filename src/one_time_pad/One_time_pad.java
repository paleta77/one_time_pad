/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package one_time_pad;

<<<<<<< HEAD
//do generowania klucza

=======
import javafx.beans.property.DoubleProperty;

//do szyfrowania
>>>>>>> 375ee71eb6b8f87b754121e3fcc85c996fa31ad7
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

//do wczytywania i zapisu
<<<<<<< HEAD
import java.io.DataInputStream;
import java.io.DataOutputStream;
=======
>>>>>>> 375ee71eb6b8f87b754121e3fcc85c996fa31ad7
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

<<<<<<< HEAD
=======
import java.io.File;
import java.nio.file.Files;
import java.util.Locale;
import javax.swing.JFrame;

>>>>>>> 375ee71eb6b8f87b754121e3fcc85c996fa31ad7
/**
 *
 * @author pawel
 */
public class One_time_pad {

    /**
     * @param args the command line arguments
     *
     */
<<<<<<< HEAD
    public static List generujKluczSzyfrujacy(int dlugosc) {
        Random generator = new Random(System.currentTimeMillis()); //Inicjalizacja generatora ktory za ziarno pobiera aktualny czas w milisekunach
        List<Boolean> los = new ArrayList<>(dlugosc); //Lista tablicowa. Szybszy odczyt niz dla listy linkowanej
        for (int i = 0; i < dlugosc; i++) {
            los.add(generator.nextBoolean()); //Dodawanie do listy bit po bicie 
        }
        return los;
    }

    public static void zapis_i_odczyt_plik(String plik_1, int dlugosc, List klucz) {

        DataInputStream daneDoSzyfrowania = null;
        DataOutputStream zapisDoPliku = null;

        try {
            daneDoSzyfrowania = new DataInputStream(new FileInputStream(plik_1));
            zapisDoPliku = new DataOutputStream(new FileOutputStream("plik_2.pdf"));
        } catch (FileNotFoundException wyjatek) {
            System.out.println("Nie znaleziono takiego pliku!");
        }

        try {

            List<Boolean> myList = new ArrayList<>(dlugosc);

//  1) odczyt plik i zapis w buforze
            for (int i = 0; i < dlugosc; i++) {
                myList.add(daneDoSzyfrowania.readBoolean());
            }

//  2) xor - zmodyfikowac bufor 
            for (int j = 0; j < dlugosc; j++) {
                if (klucz.get(j) != myList.get(j)) {
                    myList.set(j, true);
                } else {
                    myList.set(j, false);
                }
            }

//  3) zapis do pliku   
            for (int k = 0; k < dlugosc; k++) {
                zapisDoPliku.writeBoolean(myList.get(k));
            }

        } catch (IOException wyjatek) {
            System.out.println("Błąd wejścia-wyjścia!");
        }

        try {
            if (daneDoSzyfrowania != null) {
                daneDoSzyfrowania.close();
            }
            if (zapisDoPliku != null) {
                zapisDoPliku.close();
            }
        } catch (IOException wyjatek) {
            System.out.println("Błąd zamykania strumieni!");
        }
    }

    public static void main(String[] args) {
        //podaj dowolną ścieżkę do pliku
        String ścieżka = "plik.pdf";

        //generowanie klucza
        List klucz = generujKluczSzyfrujacy(20);
        System.out.print(klucz);

        //odczyt z pliku + dołożenie klucza + zapis
        zapis_i_odczyt_plik(ścieżka, 20, klucz);
    }
=======
    public static byte[] generujKluczSzyfrujacy(int dlugosc) throws FileNotFoundException, IOException {
        Random generator = new Random(System.currentTimeMillis()); //Inicjalizacja generatora ktory za ziarno pobiera aktualny czas w milisekunach
        byte[] klucz = new byte[dlugosc]; //Lista tablicowa. Szybszy odczyt niz dla listy linkowanej
        generator.nextBytes(klucz); //Dodawanie do listy bit po bicie
        
        File fileOut = new File("klucz.txt");
        FileOutputStream fop = new FileOutputStream(fileOut);
        fop.write(klucz);
        
        return klucz;
    }

    public static void szyfruj(String path) throws IOException {
        //wczytanie pliku
        File file = new File(path);
        byte[] fileContent = Files.readAllBytes(file.toPath());
        //generowanie klucza szyfrujacego
        byte[] key = new byte[fileContent.length];
        key = generujKluczSzyfrujacy(fileContent.length);
        //tablica ktora zostanie pozniej zapisana do pliku
        byte[] output = new byte[fileContent.length];
        //szyfrowanie
        for (int i = 0; i < fileContent.length; i++) {
            System.out.print(fileContent[i] + " ");
            output[i] = (byte) (fileContent[i] ^ key[i]);
        }
        //zapis szyfrowanego pliku
        File fileOut = new File("plikZaszyfrowany.pdf");
        FileOutputStream fop = new FileOutputStream(fileOut);
        fop.write(output);
        fop.close();
    }
    
    public static void odszyfruj(String path) throws IOException{
        //otwieranie pliku z kluczem. Jest zapisywany przy szyfrowaniu
        File plikZKluczem = new File("klucz.txt");
        byte[] key = Files.readAllBytes(plikZKluczem.toPath());
        //otwieranie zaszyfrowanego pliku
        File file = new File(path);
        byte[] fileContent = Files.readAllBytes(file.toPath());
        //tablica ktora zostanie pozniej zapisana do pliku
        byte[] output = new byte[fileContent.length];
        //szyfrowanie
        for (int i = 0; i < fileContent.length; i++) {
            System.out.print(fileContent[i] + " ");
            output[i] = (byte) (fileContent[i] ^ key[i]);
        }
        //zapis odszyfrowanego pliku
        File fileOut = new File("plikOdszyfrowany.pdf");
        FileOutputStream fop = new FileOutputStream(fileOut);
        fop.write(output);
        fop.close();
    }

    public static void main(String[] args) throws FileNotFoundException, IOException {
        JFrame frame = new JFrame();
        frame.getContentPane().add(new NewJPanel());
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(440,150);
        frame.setVisible(true);
        frame.setLocationRelativeTo(null);
        frame.setTitle("One Time Pad");
        //szyfruj();
    }
>>>>>>> 375ee71eb6b8f87b754121e3fcc85c996fa31ad7
}
