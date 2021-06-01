/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cinemabookingsystem;

import java.io.*;
import java.util.Scanner;
import javax.swing.JOptionPane;

/**
 *
 * @author 17855
 */
public class MainMenu {
    private Cinema cinema;
    private Scanner input;
    
    public MainMenu(Cinema cinema,Scanner input){
        this.cinema=cinema;
        this.input=input;
    }
    
    public void displayMenu() throws IOException, InterruptedException{
        boolean exit=false;
        while(!exit){
            System.out.println("====================================");
            System.out.println("             MAIN MENU              ");
            System.out.println("====================================");
            System.out.println("1. Employee Menu");
            System.out.println("2. Customer Menu");
            System.out.println("3. Exit");
            System.out.print("   Choose an option: ");
            int option= input.nextInt();
            input.nextLine();
            System.out.println();
            if(option==1){
                System.out.print("Enter system access code: ");
                if(input.nextInt()==cinema.getAccessCode()) employeeMenu();
                else{
                    System.out.println("Access Denied!");
                    exit=true;
                }
            }
            else if(option==2){
                customerMenu();
            }
            else if(option==3) exit=true;
            else{
                System.out.println("Wrong option");
            } 
        }
    }
    
    public void customerMenu() throws InterruptedException{
        boolean exit=false;
        while(!exit){
            System.out.println("====================================");
            System.out.println("           CUSTOMER MENU            ");
            System.out.println("====================================");
            System.out.println("1. Show current movie details");
            System.out.println("2. Create account");
            System.out.println("3. Login");
            System.out.println("4. Buy tickets");
            System.out.println("5. Exit");
            System.out.print("   Choose an option: ");
            int option=input.nextInt();
            input.nextLine();
            System.out.println();
            if(option==1){
                boolean back=false;
                while(!back){
                    System.out.println("====================================");
                    System.out.println("             SHOW MENU              ");
                    System.out.println("====================================");
                    System.out.println("1. Display all shows");
                    System.out.println("2. Display shows for specific movie");
                    System.out.println("3. Display shows for specific theater");
                    System.out.println("4. Go back");
                    System.out.print("   Choose an option: ");
                    int choice=input.nextInt();
                    input.nextLine();
                    System.out.println();
                    if(choice==1){
                        cinema.currentMovies();
                    }
                    else if (choice==2){
                        System.out.print("Enter name of movie: ");
                        String movName=input.nextLine();
                        System.out.println(cinema.getMovie(movName));
                    }
                    else if (choice==3){
                        System.out.print("Enter ID of theater: ");
                        String theaterID=input.nextLine();
                        System.out.println(cinema.getTheater(theaterID));
                    }
                    else if(choice==4) back=true;
                    else System.out.println("Wrong input");
                }
            }
            else if(option==2){
                new CreateAccountFrame().setVisible(true);
            }
            else if(option==3){
                boolean keepRunning=true;
                LoginFrame lf=new LoginFrame();
                lf.setVisible(true);
                while(keepRunning){
                    while(!lf.isFlag()){
                        Thread.sleep(1000);
                    }
                    if(cinema.checkAccount(lf.getLogID(),lf.getPw())) {
                        JOptionPane.showMessageDialog(null,"Logged In");
                        keepRunning=false;
                    }
                    else{
                        JOptionPane.showMessageDialog(null,"Login Failed");
                        lf.setVisible(true);
                        lf.setFlag(false);
                    }
                }
            }
            else if(option==4){
                System.out.print("Enter desired showID: ");
                int showID=input.nextInt();
                String movName=cinema.getShow(showID).getMovieName();
                String thID=cinema.getShow(showID).getTheaterName();
                System.out.print("Enter no. of tickets you want for this show: ");
                int nTickets=input.nextInt();
                cinema.buyTicket(thID,cinema.getMovie(movName),showID,nTickets);
                
            }
            else if(option==5){
                exit=true;
            }
            else{
                System.out.println("Wrong input!");
            }
        }
    }
    
    public void employeeMenu() throws IOException{
        //Make changes for file writing
        boolean exit=false;
        Scanner scanner=null;
        while(!exit){
            System.out.println("====================================");
            System.out.println("           EMPLOYEE MENU            ");
            System.out.println("====================================");
            System.out.println("1. Add Theaters");
            System.out.println("2. Add Movies");
            System.out.println("3. Add Shows");
            System.out.println("4. Show movie sales");
            System.out.println("5. Exit");
            System.out.print("   Choose an option: ");
            int option=input.nextInt();
            input.nextLine();
            System.out.println();
            PrintWriter printWriter=null;
            if(option==1){
                System.out.print("Enter new theaterID: ");//input new theaterID and seats
                String theID=input.nextLine();
                try {
                    scanner = new Scanner(new File("theaters.txt"));
                    while (scanner.hasNextLine()) {
                        String line = scanner.nextLine();
                        if(line.contains(theID)) { 
                            System.out.println("Theater already exists");
                            scanner.close();
                            return;
                        }
                    }
                    //Add that to theaters file
                    System.out.print("Enter theater capacity: ");
                    int capacity=input.nextInt();
                    input.nextLine();
                    printWriter = new PrintWriter(new FileWriter("theaters.txt",true));
                    printWriter.println(theID+"-"+capacity+",");
                } 
                catch(FileNotFoundException e) { 
                    System.out.println("File not found");
                }
                catch(NullPointerException ex) { 
                    System.out.println("");
                }
                finally{
                    printWriter.close();
                }
                System.out.print("Must restart program for changes! Press enter to exit: ");
                if(input.nextLine().equals("")){
                    System.exit(0);
                }
            }
            else if(option==2){
                //input new movie name and duration
                System.out.print("Enter new movie name: ");
                String movieName=input.nextLine();
                try {
                    scanner = new Scanner(new File("movies.txt"));
                    while (scanner.hasNextLine()) {
                        String line = scanner.nextLine();
                        if(line.contains(movieName)) { 
                            System.out.println("Movie already exists");
                            scanner.close();
                            return;
                        }
                    }
                    //Add that to movies file
                    System.out.print("Enter movie duration: ");
                    int duration=input.nextInt();
                    input.nextLine();
                    printWriter = new PrintWriter(new FileWriter("movies.txt",true));
                    printWriter.println(movieName+"-"+duration+",");
                } 
                catch(FileNotFoundException e) { 
                    System.out.println("File not found");
                }
                catch(NullPointerException ex) { 
                    System.out.println("");
                }
                finally{
                    printWriter.close();
                }
                //Print msg to restart program for changes
                System.out.print("Must restart program for changes! Press enter to exit: ");
                if(input.nextLine().equals("")){
                    System.exit(0);
                }
            }
            else if(option==3){
                //input show details(movie name, theaterID, start time)
                System.out.print("Enter new movie name: ");
                String movieName=input.nextLine();
                scanner = new Scanner(new File("movies.txt"));
                boolean exist=false;
                while (scanner.hasNextLine()) {
                    String line = scanner.nextLine();
                    if(line.contains(movieName)) {
                        exist=true;
                        scanner.close();
                        break;
                    }
                }
                if(!exist) {
                    System.out.println("Movie doesn't exist in movies file");
                    return;
                }
                System.out.print("Enter theater ID: ");
                String theaterID=input.nextLine();
                System.out.print("Enter show start time: ");
                int stTime=input.nextInt();
                input.nextLine();
                boolean[] timeslots=cinema.getTheater(theaterID).getTimeslots();
                int duration=cinema.getMovie(movieName).getDuration();
                for(int i=stTime;i<stTime+duration;i++){
                    if(timeslots[i]) {
                        System.out.println("Theater not available for this show time");
                        return;
                    }
                }
                try {
                    //Add that to shows file
                    printWriter = new PrintWriter(new FileWriter("shows.txt",true));
                    printWriter.println(movieName+"-"+theaterID+"-"+stTime+",");
                    
                } 
                catch(FileNotFoundException e) { 
                    System.out.println("File not found");
                }
                catch(NullPointerException ex) { 
                    System.out.println("");
                }
                finally{
                    printWriter.close();
                }
                //Print msg to restart program for changes
                System.out.print("Must restart program for changes! Press enter to exit: ");
                if(input.nextLine().equals("")){
                    System.exit(0);
                }
            }
            else if(option==4){
                boolean back=false;
                while(!back){
                    System.out.println("====================================");
                    System.out.println("             SALES MENU             ");
                    System.out.println("====================================");
                    System.out.println("1. Display all sales");
                    System.out.println("2. Display sales for specific movie");
                    System.out.println("3. Go back");
                    System.out.print("   Choose an option: ");
                    int choice=input.nextInt();
                    input.nextLine();
                    System.out.println();
                    if(choice==1){
                        cinema.printAllSales();
                    }
                    else if (choice==2){
                        System.out.print("Enter name of movie: ");
                        String movName=input.nextLine();
                        System.out.println();
                        cinema.printMovieSales(movName);
                    }
                    else if (choice==3){
                        back=true;
                    } 
                    else System.out.println("Wrong input");
                }
            }
            else if(option==5){
                exit=true;
                
            }
            else{
                System.out.println("Wrong input!");
            }
        }
    }
}
