/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cinemabookingsystem;

import java.io.*;
import java.util.*;
public class CinemaBookingSystem {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws FileNotFoundException, IOException, InterruptedException{
        Cinema cinepax=new Cinema("cinepax",5816);
        Scanner input=new Scanner(System.in);
        MainMenu mainmenu=new MainMenu(cinepax,input);
        mainmenu.displayMenu();
    }    
}
