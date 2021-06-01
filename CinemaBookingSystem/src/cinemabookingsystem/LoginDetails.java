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
public class LoginDetails<U,P> {
    private U username;
    private P password;
    
    public LoginDetails(U usern,P passw){
        username=usern;
        password=passw;
    }
    
    public U getUsername(){
        return username;
    }
    
    public P getPassword(){
        return password;
    }
    
    public void changePassword(P newPass){
        password=newPass;
    }
    
    public void print(){
        System.out.println("Username: "+ username);
        System.out.println("Password: "+ password);
    }
}
