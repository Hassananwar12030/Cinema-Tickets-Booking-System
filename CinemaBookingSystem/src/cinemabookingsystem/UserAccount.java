
package cinemabookingsystem;

/**
 *
 * @author Rafay
 */
public class UserAccount {
    private int points, userID;
    private static int userCount=0;
    private LoginDetails<String, String> credentials;
    
    public UserAccount(String username, String password){
        userID=userCount;
        userCount++;
        points=0;
        credentials=new LoginDetails<>(username, password);
    }
    
    public int getUserID(){
        return userID;
    }

    public int getPoints() {
        return points;
    }

    public void updatePoints(int p) {
        points+= p;
    }
    
    public String getUsername(){
        return credentials.getUsername();
    }
    
    public String getPassword(){
        return credentials.getPassword();
    }
    
    public boolean earnedFreeTicket(){
        return points>=50;
    }
    
    public String toString(){
        return "UserID: "+userID+ ", Username: "+getUsername() + ", Password: "+getPassword()+
                "\nPoints: "+points;
    }
    
}
