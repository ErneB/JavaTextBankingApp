/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bankingapp;

import static bankingapp.createAccount.pl;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.text.DecimalFormat;

/**
 *
 * @author Kali
 */
public class Dashboard extends Account{
    public static void Dashboard(JSONObject obj){
        
        Account currentAcc;
        currentAcc = loadAcc(obj);
        checkIntnMonthly(currentAcc);
        greet(currentAcc);
        displayOptions(currentAcc);
    }
    public static void greet(Account a){
        System.out.println();
        System.out.println();
        System.out.println("Hello "+a.getFName()+" "+a.getLName());
        System.out.println("You can choose one of the following options:");
    }
    public static void displayOptions(Account a){
        boolean display = true;
        Scanner input = new Scanner(System.in);
        System.out.println("-------------------------------------------------------------------------------------");
        System.out.println("Display:");
        System.out.printf("|%-15s|%-15s|%-15s|%-15s|%-19s|%n","[1]First Name","[2]Last Name","[3]Birthdate","[4]Address","[5]City ");
        System.out.printf("|%-15s|%-15s|%-15s|%-15s|%-19s|%n","[6]State","[7]Zip","[8]Balance","[9]Account ID","[10]Account Info");
        System.out.println("-------------------------------------------------------------------------------------");
        System.out.println("Check:");
        System.out.printf("|%-19s|%-19s|%-21s|%-21s|%n", "[11]Balance","[12]Interest Rate","[13]Monthly Charge","[14]Withdraw/Deposit");
        System.out.println("-------------------------------------------------------------------------------------");
        System.out.printf("|%-27s|%-27s|%-27s|%n","[15]Edit Account","[16]Delete Account","[17]Log Out");
        System.out.println("-------------------------------------------------------------------------------------");
        int answer = input.nextInt();
        while(display = procAnswer(answer, a, display)){
            System.out.println("-------------------------------------------------------------------------------------");
            System.out.println("Success. ");
            System.out.println("Choose another option.");
            answer = input.nextInt();
        }
    }
    public static void saveToFile(Account acc){
        //File to write to:
        String json = "C:\\Users\\Kali\\Desktop\\IntrotoJava\\final\\BankingApp\\src\\bankingapp\\Database.json";
        //Open File:
        JSONParser parser = new JSONParser();
            Object filer;
            JSONArray jsonArr = null;
       try(FileReader file = new FileReader(
            json)) {
            filer = parser.parse(file);
            jsonArr = (JSONArray) filer;
        } 
        
        catch (IOException | org.json.simple.parser.ParseException e) {
        }
       JSONArray itemArr = new JSONArray();
       for (Object a : jsonArr){
           int i = 0;
                   
           
           JSONObject person = (JSONObject) a;
           int accountID = ((Long)person.get("Account ID")).intValue();
           if (accountID == acc.getAccountID()){
               JSONObject newInfo = acc.getJSONAccInfo();
               itemArr.add(i, newInfo);
           } else {
               itemArr.add(a);
           }
        i++;
       }
        System.out.print("Successfully updated Account.");
//Write to file
        try (FileWriter file = new FileWriter(json)) {
                file.write(itemArr.toJSONString());
                System.out.println("Successfully uploaded Account to Database...");
        } catch (IOException ex) {
        Logger.getLogger(createAccount.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public static Account loadAcc(JSONObject obj){
        DateTimeFormatter formatter_1=DateTimeFormatter.ofPattern("dd-MMM-yyyy");
        LocalDate Date= LocalDate.parse((String)obj.get("Date Created"),formatter_1);
        LocalDate lastCharged= LocalDate.parse((String)obj.get("Last Charged"),formatter_1);
        Account loadedAcc = new Account((String) obj.get("First Name"),(String) obj.get("Last Name"), (String) obj.get("Date of Birth"),
        (String) obj.get("Street Address"), (String) obj.get("City"),(String) obj.get("State"),((Long)obj.get("Zip")).intValue(), (double)obj.get("Balance"),
        ((Long)obj.get("Account ID")).intValue(), (LocalDate) Date, (LocalDate) lastCharged);
        return loadedAcc;
    }
    public static boolean procAnswer(int answer, Account a, boolean display){
                    switch(answer){
               case 1:
                   System.out.println(a.getFName());
                   break;
               case 2:
                   
                   System.out.println(a.getLName());
                   break;
               case 3:
                   
                   System.out.println(a.getDOB());
                   break;
               case 4:
                   
                   System.out.println(a.getAddress());
                   break;                   
               case 5:
                   
                   System.out.println(a.getCity());
                   break;
               case 6:
                   
                   System.out.println(a.getState());
                   break;
               case 7:
                   System.out.println(a.getZip());
                   break;
               case 8:
                   System.out.println(a.getBalance());
                   break;  
               case 9:
                   System.out.println(a.getAccountID());
                   break;
               case 10:
                   a.displayInfo();
                   break;
               case 11:
                   System.out.println(a.getBalance());
                   break;
               case 12:
                   System.out.println(a.getInterestRate());
                   break;
               case 13:
                   System.out.println(a.getMonthlyCharge());
                   break;
               case 14:
                   withdrawNdeposit(a);
                   break;
               case 15:
                   dashEdit(a);
                   saveToFile(a);
                   break;
               case 16:
                   deleteAccount(a);
                   break;
               case 17:
                   saveToFile(a);
                   display = false;
                   break;
            }
      return display;              
    }
    public static void dashEdit(Account acc){
    System.out.println("Which field would you like to edit?");
    pl("[1]First Name [2]Last Name [3]Date of Birth [4]Address [5]City [6]State [7]Zip Code");
    Scanner input = new Scanner(System.in);
    int answer = input.nextInt();
       switch(answer){
           case 1:
               System.out.print("Retype First Name:");
               acc.setFName(input.next());
               break;
           case 2:
               System.out.print("Retype Last Name:");
               acc.setLName(input.next());
               break;
           case 3:
               System.out.print("Retype Date of Birth(mm-dd-yyyy):");
               acc.setDOB(input.next());
               break;
           case 4:
               System.out.print("Retype Address:");
               acc.setAddress(input.next());
               break;                   
           case 5:
               System.out.print("Retype City:");
               acc.setCity(input.next());
               break;
           case 6:
               System.out.print("Retype State:");
               acc.setState(input.next());
               break;
           case 7:
               System.out.print("Retype Zip Code:");
               acc.setZip(input.nextInt());
               break;

       }
       System.out.println("*Edit successful*");
}
    public static void withdrawNdeposit(Account acc){
        Scanner input = new Scanner(System.in);
        System.out.println("Would you like to [1]Withdraw or [2]Deposit?");
        int answer = input.nextInt();
            switch(answer) {
                case 1:
                    withdraw(acc);
                    break;
                case 2:
                    deposit(acc);
                    break;
            }
    }
    public static void withdraw(Account a){
        Scanner input = new Scanner(System.in);
        System.out.print("\tHow much do you wish to withdraw today?: ");
                    double withdrawl =  input.nextDouble();
                    double newBalance = a.getBalance() - withdrawl;
                    a.setBalance(newBalance);
                    System.out.println("Your new balance is: " + a.getBalance());

    }
    public static void deposit(Account a){
            Scanner input = new Scanner(System.in);
            System.out.print("\tHow much do you wish to deposit today?: ");
			double deposit =  input.nextDouble();
                        double newBalance = a.getBalance() + deposit;
                        a.setBalance(newBalance);
                        System.out.println("Your new balance is: " + a.getBalance());
        }
    public static void checkIntnMonthly(Account a){
        LocalDate today = LocalDate.now();
        LocalDate datecreated = a.getlastCharged();        
        Period diff = Period.between((LocalDate)datecreated, (LocalDate)today);

        if(1 <= diff.getMonths()){
           monthlyServiceFee(a, diff.getMonths());
           creditInterest(a, diff.getMonths());
           a.setlastCharge(LocalDate.now());
        }
    }
    
    public static void creditInterest(Account a, int diff){
         double balance = a.getBalance();
            double interestRate = a.getInterestRate();
            double amountCredited = (balance*(interestRate * diff)/100);
            balance += amountCredited;
            a.setBalance(balance);
            System.out.println("Account credited an interest of: " + df2.format(amountCredited));
    }
    public static void monthlyServiceFee(Account a, int diff){
         double balance = a.getBalance();
            double monthlyCharge = a.getMonthlyCharge();
            balance -= (monthlyCharge * diff);
            a.setBalance(balance);
            System.out.println("Account debited a service fee of: " + monthlyCharge);
    }
    public static void deleteAccount(Account acc){
        //File to write to:
        String json = "C:\\Users\\Kali\\Desktop\\IntrotoJava\\final\\BankingApp\\src\\bankingapp\\Database.json";
        //Open File:
        JSONParser parser = new JSONParser();
            Object filer;
            JSONArray jsonArr = null;
       try(FileReader file = new FileReader(
            json)) {
            filer = parser.parse(file);
            jsonArr = (JSONArray) filer;
        } 
        
        catch (IOException | org.json.simple.parser.ParseException e) {
        }
       JSONArray itemArr = new JSONArray();
       for (Object a : jsonArr){
           
                   
           
           JSONObject person = (JSONObject) a;
           int accountID = ((Long)person.get("Account ID")).intValue();
           if (accountID == acc.getAccountID()){
              
           } else {
               itemArr.add(a);
           }
        
       }
//Write to file
        try (FileWriter file = new FileWriter(json)) {
                file.write(itemArr.toJSONString());
                System.out.println("Successfully deleted Account from Database...");
        } catch (IOException ex) {
        Logger.getLogger(createAccount.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    private static final DecimalFormat df2 = new DecimalFormat(".##");
}
