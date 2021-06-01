package cinemabookingsystem;

import java.io.*;
import java.util.*;

/**
 *
 * @author 17855
 */
public class Cinema {
    private String cineName;
    private ArrayList<Movie> movies;
    private ArrayList<Theater> theaters;
    private ArrayList<Show> shows;
    private ArrayList<Ticket> tickets;
    private ArrayList<Sales> sales;
    private ArrayList<UserAccount> accounts;
    private int activeAccountID, accessCode;

    Scanner input;
    
    public Cinema(String name, int sysCode) throws FileNotFoundException{
        cineName=name; accessCode=sysCode;
        activeAccountID=-1;
        input=new Scanner(System.in);
        movies=new ArrayList<>();
        theaters=new ArrayList<>();
        sales=new ArrayList<>();
        shows=new ArrayList<>();
        tickets=new ArrayList<>();
        accounts=new ArrayList<>();
        initialise();
    }
    
    public void addMovie(String name, int dur){ //<--------Employee Interface
        movies.add(new Movie(name,dur));
    }
    
    public void addTheater(String theaterID, int seats){ //<--------Employee Interface
        theaters.add(new Theater(theaterID,seats));
    }
    
    public void addAccount(String u,String p){ //<-------Internal Use
        accounts.add(new UserAccount(u,p));
    }
    
    public void addShow(Movie movie, String theaterID, int time){ //<--------Employee Interface
        for(int i=0;i<theaters.size();i++){
            if(theaterID.equals(theaters.get(i).getTheaterID())){
                Show sh=new Show(time,movie,theaters.get(i));
                shows.add(sh);
                movies.get(movies.indexOf(movie)).addShow(sh);
                theaters.get(i).addShow(sh);
                break;
            }
        }
    }
    
    public void addSales(){ //<--------External use
        for(int i=0;i<movies.size();i++){
            sales.add(new Sales(movies.get(i)));
        }
    }
    
    public UserAccount getAccount(String u){ //<--------------Internal Use
        for(int i=0;i<accounts.size();i++){
            if(accounts.get(i).getUsername().equals(u)) return accounts.get(i);
        }
        return null;
    }
    
    public boolean checkAccount(String username, String pw){ //<--------External Use
        UserAccount ua=getAccount(username);
        if(ua!=null){
            if(ua.getPassword().equals(pw)) { activeAccountID=accounts.indexOf(getAccount(username)); return true;}
        }
        return false;
    }
    
    public void initialise() throws FileNotFoundException{ //<-------Internal Use
        System.out.println("Loading files.....");
        Scanner fileReader;
        String movName="",thID="";
        File file = new File("theaters.txt"); //<----loading theaters from file
        fileReader=new Scanner(file);
        fileReader.useDelimiter("-|,");
        while(fileReader.hasNextLine()){
            thID=fileReader.next();
            int cap=Integer.parseInt(fileReader.next());
            fileReader.nextLine();
            addTheater(thID.trim(),cap);
        }
        System.out.println("   All theaters activated succesfully");
        fileReader.close();
        File file2 = new File("movies.txt"); //<----loading movies from file
        fileReader=new Scanner(file2);
        fileReader.useDelimiter("-|,");
        while(fileReader.hasNextLine()){
            movName=fileReader.next();
            int dur=Integer.parseInt(fileReader.next());
            fileReader.nextLine();
            addMovie(movName.trim(),dur);
        }
        System.out.println("   All movies loaded succesfully");
        fileReader.close();
        File file3 = new File("shows.txt"); //<----loading shows from file
        fileReader=new Scanner(file3);
        fileReader.useDelimiter("-|,");
        while(fileReader.hasNextLine()){
            movName=fileReader.next();
            thID=fileReader.next();
            int startTime=Integer.parseInt(fileReader.next());
            fileReader.nextLine();
            addShow(getMovie(movName.trim()),thID.trim(),startTime);
        }
        System.out.println("   All shows loaded succesfully");
        fileReader.close();
        addSales(); //<------for storing movie ticket sales
        //add accounts loading
        File file4 = new File("accounts.txt"); //<----loading shows from file
        fileReader=new Scanner(file4);
        fileReader.useDelimiter("-|,");
        while(fileReader.hasNextLine()){
            String username=fileReader.next();
            String password=fileReader.next();
            fileReader.nextLine();
            addAccount(username.trim(),password.trim());
        }
        System.out.println("   All accounts loaded succesfully");
        fileReader.close();
        
    }
    
    public void logout(){ //<------Internal Use
        activeAccountID=-1;
        System.out.println("Logged out");
    }
    
    public void printAllSales(){ //<-------External Use
        for(int i=0;i<sales.size();i++){
            System.out.println(sales.get(i));
        }
    }
    
    public void printMovieSales(String movName){ //<-------External Use
        int i=movies.indexOf(getMovie(movName));
        System.out.println(sales.get(i));
    }
    
    public Movie getMovie(String movName){ //<------External Use
        for(int i=0;i<movies.size();i++){
            if(movName.equals(movies.get(i).getName())) return movies.get(i);
        }
        System.out.println("Wrong name. Movie name "+movName+" not found!");
        return null;
    }
    
    public Show getShow(int showID){ //<-----------Internal Use
        return shows.get(showID);
    }
    
    public Theater getTheater(String theaterID){ //<-----------Internal Use
        for(int i=0;i<theaters.size();i++){
            if(theaterID.equals(theaters.get(i).getTheaterID())) return theaters.get(i);
        }
        System.out.println("Wrong ID. Theater not found!");
        return null;
    }
    
    public void currentMovies(){ //<------------------------Customer Interface
        for(int i=0;i<movies.size();i++){
            System.out.println(movies.get(i));
        }
    }
    
    public void createTicket(int showID){ //<-----------------Internal Use
        int age=0;
        System.out.print("Please enter the age for person "+(tickets.size()+1)+": ");
        age=input.nextInt();
        tickets.add(new Ticket(shows.get(showID),age));
    }
    
    public void buyTicket(String theaterID, Movie movie, int showID, int noOfTickets){ //<---Customer Interface
        if(!getShow(showID).checkSeats(noOfTickets)) {
            System.out.println("Sorry! "+noOfTickets+" tickets not available!"); 
            return;
        }
        for(int i=1;i<=noOfTickets;i++){
            createTicket(showID);
        }
        sales.get(movies.indexOf(movie)).updateTicketSales(noOfTickets);
        System.out.println();
        System.out.print("Enter preferred payment method (card/cash): ");
        input.nextLine();
        String m=input.nextLine();
        while(!(m.equals("cash"))&&!(m.equals("card")))
        {
            System.out.println("Method not recognized");
            System.out.print("Re-enter preferred payment method (card/cash): ");
            m=input.nextLine();
        }
        generatePayment(m,tickets, movie);
        getShow(showID).updateSeats(noOfTickets);
        tickets.clear();
        if(activeAccountID!=-1) logout();
    }
    
    public void generatePayment(String pMethod,ArrayList<Ticket> t, Movie movie){ //<-----------Internal Use
        Payment p=new Payment(pMethod,t);
        if(activeAccountID!=-1){
            UserAccount u=accounts.get(activeAccountID);
            if(u.earnedFreeTicket()){
                p.discountedBill();
                accounts.get(activeAccountID).updatePoints(-50);
            }
            else{
                p.bill();
                accounts.get(activeAccountID).updatePoints(5);
            }
        }
        else p.bill();
        sales.get(movies.indexOf(movie)).setRevenue(p.getTotalPrice());
    }
    
    public void updateScreenings(){ //<---External Use 
        for(int i=0;i<sales.size();i++){
            if(sales.get(i).getTicketsSold()>50) movies.get(i).extendScreening();
        }
    }
    
    public int getAccessCode() { //<-----------External Use
        return accessCode;
    }
    
}
