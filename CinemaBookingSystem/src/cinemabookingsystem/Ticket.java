/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cinemabookingsystem;

/**
 *
 * @author 17855
 */
public class Ticket {
    private int age, price;
    private Show show;
    private final int adultPrice=700, childPrice=500, seniorPrice=800; 
    
    public Ticket(Show show, int age){
        this.show=show;
        this.age=age;
    }
    
    public void calcPrice(){
        if (age<14) price= childPrice;
        else if(age>=14 && age<=50) price= adultPrice;
        else price= seniorPrice;
    }
    
    public int getPrice(){
        return price;
    }
    
    public String toString(){
        return "----------------------------------------------\n   Ticket for show: " + show.getMovieName()+ ", "+show.getTheaterName()+
                show.getStartTime()+":00-"+show.getEndTime()+":00\n   "+
                "Price: " + price+"\n   ----------------------------------------------";
    }
}
