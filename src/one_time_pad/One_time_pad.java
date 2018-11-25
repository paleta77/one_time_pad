/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package one_time_pad;

import javafx.beans.property.DoubleProperty;

//do szyfrowania
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

//do wczytywania i zapisu
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import java.io.File;
import java.nio.file.Files;
import java.util.Locale;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

/**
 *
 * @author pawel
 */
public class One_time_pad {

    /**
     * @param args the command line arguments
     *
     */
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

    public static String szyfrujTekst(String napis) throws IOException {
        //generowanie klucza szyfrujacego
        byte[] key = new byte[napis.length()];
        key = generujKluczSzyfrujacy(napis.length());
        //tablica ktora zostanie pozniej zapisana do pliku
        byte[] output = new byte[napis.length()];
        //szyfrowanie
        String zwrot = "";
        for (int i = 0; i < napis.length(); i++) {
            //System.out.print(napis.charAt(i) + " ");
            output[i] = (byte) ((byte) napis.charAt(i) ^ key[i]);
            //System.out.print(output[i] + " ");
            zwrot += (char) output[i];
        }
        //zapis szyfrowanego pliku
        File fileOut = new File("plikZaszyfrowany.txt");
        FileOutputStream fop = new FileOutputStream(fileOut);
        fop.write(output);
        fop.close();
        return zwrot;
    }

    public static String odszyfrujTekst(String napis) throws IOException {
        //otwieranie pliku z kluczem. Jest zapisywany przy szyfrowaniu
        File plikZKluczem = new File("klucz.txt");
        byte[] key = Files.readAllBytes(plikZKluczem.toPath());
        if (key.length == napis.length()) {
            //tablica ktora zostanie pozniej zapisana do pliku
            byte[] output = new byte[napis.length()];
            //szyfrowanie
            String zwrot = "";
            for (int i = 0; i < napis.length(); i++) {
                output[i] = (byte) ((byte) napis.charAt(i) ^ key[i]);
                //System.out.print(output[i] + " ");
                zwrot += (char) output[i];
            }
            //zapis odszyfrowanego pliku
            File fileOut = new File("plikOdszyfrowany.txt");
            FileOutputStream fop = new FileOutputStream(fileOut);
            fop.write(output);
            fop.close();

            return zwrot;
        } else {
            return napis;
        }
    }

    public static void odszyfruj(String path) throws IOException {
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
        frame.setSize(440, 350);
        frame.setVisible(true);
        frame.setLocationRelativeTo(null);
        frame.setTitle("One Time Pad");
        //szyfruj();
    }
}
