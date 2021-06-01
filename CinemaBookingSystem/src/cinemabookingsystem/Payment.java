/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cinemabookingsystem;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;
/**
 *
 * @author 17855
 */
public class Payment {
    private int totalPrice;
    private String method;
    private ArrayList<Ticket> tickets;
    private Scanner input;
    
    public Payment(String pMethod, ArrayList<Ticket> t){
        tickets=t;
        method=pMethod;
        tickets=t;
        input=new Scanner(System.in);
    }
    
    private boolean inputCardDetails(){
        int n=0;
        Scanner input=new Scanner(System.in);
        System.out.print("For payment enter 4 digit credit card pin: ");
        try {
            n=input.nextInt();
        } catch (InputMismatchException ex) {
            System.out.println("Enter digits only!");
            return false;
        }
        if(n>=1000 && n<10000) return true;
        return false;
    }
    
    public void calculateBill(){
        for(int i=0;i<tickets.size();i++){
            tickets.get(i).calcPrice();
            totalPrice+= tickets.get(i).getPrice();
        }
    }
    
    public void bill(){
        boolean flag=false;
        calculateBill();
        if (discountValid()) totalPrice-=300;
        System.out.println();
        System.out.println("Total price: "+getTotalPrice());
        if(method.equals("card")){
            flag=inputCardDetails();
            while (!flag) {
                System.out.println("Payment unsuccesful try again!");
                flag=inputCardDetails();
            }
            System.out.println();
            System.out.println("Card charged!");
            System.out.println("Tickets booked!");
            System.out.println("Your tickets: ");
            for(int i=0;i<tickets.size();i++){
                System.out.println("   "+tickets.get(i));
            }
        }
        else if(method.equals("cash")){
            while (!flag) {
                System.out.println();
                System.out.print("Enter payment here: ");
                int money=input.nextInt();
                flag=inputCash(money);
            }
        }
    }
    
    public boolean inputCash(int amount){
        System.out.println();
        if(amount==totalPrice) {
            System.out.println("Tickets booked!");
            System.out.println("Your tickets: ");
            for(int i=0;i<tickets.size();i++){
                System.out.println("   "+tickets.get(i));
            }
            return true;
        }
        else if(amount>totalPrice){
            System.out.println("Your change: "+(amount-totalPrice));
            System.out.println("Tickets booked!");
            System.out.println("Your tickets: ");
            for(int i=0;i<tickets.size();i++){
                System.out.println("   "+tickets.get(i));
            }
            return true;
        }
        System.out.println("Payment unsuccesful try again!");
        return false;
    }
    
    public void discountedBill(){
        totalPrice-=700;
        bill();
    }
    
    private boolean discountValid(){
        if (getTotalPrice()>3000) return true;
        return false;
    }

    public int getTotalPrice() {
        return totalPrice;
    }
}
