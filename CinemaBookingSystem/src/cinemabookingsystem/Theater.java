/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cinemabookingsystem;
import java.util.ArrayList;
/**
 *
 * @author 17855
 */

public class Theater {
    private String theaterID;
    private int capacity;
    private ArrayList<Show> shows;
    private boolean[] timeslot;
    
    public Theater(String id, int seats){
        capacity=seats;
        theaterID=id;
        shows= new ArrayList<Show>();
        timeslot=new boolean[24];
    }
    public String getTheaterID() {
        return theaterID;
    }    
    public int getCapacity() {
        return capacity;
    }
    public void addShow(Show show){
        shows.add(show);
        int firstIndex=show.getStartTime();
        int secondIndex=show.getEndTime();
        for(int i=firstIndex;i<secondIndex;i++){
            timeslot[i]=true;
        }
    }
    
    public boolean[] getTimeslots(){
        return timeslot;
    }
    
    public String toString(){
        String s=theaterID+"\n";
        for(int i=0; i<shows.size(); i++){
           s+="   ShowID: "+shows.get(i).getShowID()+" Movie: "+shows.get(i).getMovieName()+shows.get(i).toString()+"\n";
        }
        return s;
    }
    
}
