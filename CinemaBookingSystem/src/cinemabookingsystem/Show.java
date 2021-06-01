package cinemabookingsystem;

/**
 *
 * @author 17855
 */
public class Show {
    private int showID, startTime, endTime, emptySeats;
    private Movie movie;
    private Theater theater;
    private static int showNumber=0;
    
    public Show(int showTime, Movie mov, Theater th){
        startTime=showTime;
        movie=mov;
        theater=th;
        endTime=startTime+movie.getDuration();
        if(endTime>23) endTime=endTime-24;
        emptySeats=theater.getCapacity();
        showID=showNumber;
        showNumber++;
    }
    
    public boolean isFullHouse(){
        if (emptySeats==0) return true;
        return false;
    }
    
    public int seatsAvailable(){
        return emptySeats;
    }
    
    public int getShowID() {
        return showID;
    }
    
    public String getMovieName(){
        return movie.getName();
    }
    
    public String getTheaterName(){
        return theater.getTheaterID();
    }
    
    public boolean checkSeats(int seats){
        if(emptySeats>=seats) return true;
        return false;
    }
    
    public void updateSeats(int nSeats){
        emptySeats-=nSeats;
    }

    public int getStartTime() {
        return startTime;
    }

    public int getEndTime() {
        return endTime;
    }
    
    public String toString(){
        String s="";
        s=", "+startTime+":00"+"-"+endTime+":00";
        if(!isFullHouse()) s+=", Seats available: "+emptySeats;
        else s+=", FULL HOUSE!";
        return s;
    }

}
