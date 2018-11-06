/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package one_time_pad;

//do generowania klucza

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

//do wczytywania i zapisu
import java.io.DataInputStream;
import java.io.DataOutputStream;
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
}
