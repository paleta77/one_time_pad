/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package one_time_pad;

import java.util.Arrays;



/**
 *
 * @author pawel
 */
public class One_time_pad {

    /**
     * @param args the command line arguments
     */
    public static boolean relatywniePierwsza(int a, double b)
    {
        boolean relatywniePierwsza = false;
        
        while (a != b){
            if (a > b) a -= b;               
            else b -= a;              
            }
        
        if (a==1) return true;
        else return false;
    }
    
    public static boolean[] blum_blum_shub(int p, int q, int dlugosc){
        boolean pad[] = new boolean[dlugosc]; //klucz
        int m = p*q;
        int x;
        
        for(x=m;;x--) //x ma byc releatywnie pierwsze do m
        {
            if (relatywniePierwsza(x,m)==true) break;
        }
        
        System.out.print(x);
        
        return pad;
    }
    
    public static void main(String[] args) {
        // TODO code application logic here
        blum_blum_shub(20,2,5);
        //System.out.print(relatywniePierwsza(20,4));
    }
    
}
