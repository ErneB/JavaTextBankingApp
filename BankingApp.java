/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bankingapp;


import static bankingapp.createAccount.createAccount;
import static bankingapp.SignIn.SignIn;
import java.util.Scanner;
public class BankingApp {
 
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        

        welcomeScreen();
        
        Scanner input = new Scanner(System.in);
        boolean active = true;
        
        while (active) {
            int custAnswer = input.nextInt();
            switch(custAnswer){
                case 1:
                    System.out.println("Creating Account...");
                    createAccount();
                    break;
                case 2:
                    System.out.println("Signing in...");
                    SignIn();
                    break;
                case 3:
                    System.out.println("Exit");
                    active = false;
                    break;    
            }
            System.out.print("(1)Create Account (2)Sign In (3)Exit");
        }
    }
    public static void welcomeScreen() {
        System.out.println("#############################################");
        System.out.println("###Welcome to the FKCC Banking Application###");
        System.out.println("#############################################");
        System.out.println();
        System.out.println();
        System.out.println("Please choose from one of the following: ");
        System.out.println("(1) Create Account");
        System.out.println("(2) Sign In");
        System.out.println("(3) Exit");
    }
    



        
}

