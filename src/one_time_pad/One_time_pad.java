/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package one_time_pad;

//do szyfrowania
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

//do wczytywania i zapisu
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

/**
 *
 * @author pawel
 */
public class One_time_pad {

    /**
     * @param args the command line arguments
     *
     */
    public static List generujKluczSzyfrujacy(int dlugosc) {
        Random generator = new Random(System.currentTimeMillis()); //Inicjalizacja generatora ktory za ziarno pobiera aktualny czas w milisekunach
        List los = new ArrayList(); //Lista tablicowa. Szybszy odczyt niz dla listy linkowanej
        for (int i = 0; i < dlugosc; i++) {
            los.add(generator.nextBoolean()); //Dodawanie do listy bit po bicie 
        }
        return los;
    }

    public static void en_odczyt_i_zapis_plik(String plik_1) {

        FileInputStream daneDoSzyfrowania = null;
        FileOutputStream zapisDoPliku = null;

        //wczytywanie i zapis
        try {
            daneDoSzyfrowania = new FileInputStream(plik_1);
            zapisDoPliku = new FileOutputStream("plik_2.pdf");
        } catch (FileNotFoundException wyjatek) {
            System.out.println("Nie znaleziono takiego pliku!");
        }

        try {
            byte[] bufor = new byte[1024];          //tablica typu byte
            byte[] wynik = new byte[1024];
            int i = 0;                              //iterator

            while (daneDoSzyfrowania.read(bufor) != -1) {

                System.out.println("tablica z buforem: [" + i + "]= " + bufor[i]);
                wynik[i] = (byte) (bufor[i] + 15);                  //zaszyfrowanie
                System.out.println("tablica zaszyfrowaniem: [" + i + "]= " + wynik[i]);
                i++;
                zapisDoPliku.write(wynik);

            }

            //System.out.println(bufor);
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

    public static void de_odczyt_i_zapis_plik(String plik_2) {

        FileInputStream daneDoSzyfrowania = null;
        FileOutputStream zapisDoPliku = null;

        //wczytywanie i zapis
        try {
            daneDoSzyfrowania = new FileInputStream(plik_2);
            zapisDoPliku = new FileOutputStream("plik_3.pdf");
        } catch (FileNotFoundException wyjatek) {
            System.out.println("Nie znaleziono takiego pliku!");
        }

        try {
            byte[] bufor = new byte[1024];          //tablica typu byte
            byte[] wynik = new byte[1024];
            int i = 0;                              //iterator

            while (daneDoSzyfrowania.read(bufor) != -1) {

                System.out.println("2 tablica z zaszyfrowaniem: [" + i + "]= " + bufor[i]);
                wynik[i] = (byte) (bufor[i] - 15);                  //odszyfrowanie
                System.out.println("2 tablica po odkodowaniu: [" + i + "]= " + wynik[i]);
                i++;
                zapisDoPliku.write(wynik);

            }

            //System.out.println(bufor);
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
        String plik_1 = "plik.pdf";
        String plik_2 = "plik_2.pdf";                    //dowolna ścieżka do pliku
        en_odczyt_i_zapis_plik(plik_1);
        de_odczyt_i_zapis_plik(plik_2);
        //szyfrowanie
        //System.out.print(generujKluczSzyfrujacy(20));
    }
}
