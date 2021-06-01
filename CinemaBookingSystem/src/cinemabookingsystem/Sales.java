/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cinemabookingsystem;

/**
 *
 * @author Rafay
 */
public class Sales {
    private int ticketsSold, revenue;
    private Movie movie;
    
    public int getTicketsSold() {
        return ticketsSold;
    }

    public int getRevenue() {
        return revenue;
    }

    public Movie getMovie() {
        return movie;
    }
    
    public void updateTicketSales(int t){
        ticketsSold+=t;
    }
    
    public Sales(Movie m){
        movie=m;
    }

    public void setRevenue(int revenue) {
        this.revenue += revenue;
    }
    
    public String toString(){
        return movie.getName()+
               "\n   Tickets sold: "+ticketsSold+
               "\n   Revenue generated: "+revenue+
               "\n"; 
    }
}
