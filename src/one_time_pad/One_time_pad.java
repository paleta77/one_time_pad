/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package one_time_pad;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import javax.swing.JFrame;

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
    
    public static void main(String[] args) {
        //inicjalizacja interfejsu
        JFrame frame = new JFrame();
        frame.getContentPane().add(new mainInteface());
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(410,500);
        frame.setVisible(true);
        //koniec interfejsu
        System.out.print(generujKluczSzyfrujacy(20));
        
    }
    
}
