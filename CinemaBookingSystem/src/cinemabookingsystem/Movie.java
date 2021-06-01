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
public class Movie {
    private String movieName;
    private int duration;
    private ArrayList<Show> shows;
    private int screeningTime;
    
    public Movie(String name,int length){
        movieName=name;
        duration=length;
        shows=new ArrayList<Show>();
        screeningTime=3;
    }
    
    public void addShow(Show show){
        shows.add(show);
    }
    
    public int getDuration(){
        return duration;
    }
    
    public String getName(){
        return movieName;
    }
    
    public void extendScreening(){
        screeningTime++;
    }
    
    public String toString(){
        String s=movieName+"\n";
        for(int i=0; i<shows.size(); i++){
           s+="   ShowID: "+shows.get(i).getShowID()+" Theater: "+shows.get(i).getTheaterName()+shows.get(i).toString()+"\n";
        }
        return s;
    } 
}
