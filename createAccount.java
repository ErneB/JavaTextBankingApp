/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bankingapp;

import java.util.Scanner;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
 
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

/**
 *
 * @author Kali
 */
public class createAccount extends Account{
    public static void createAccount(){
        
       pl("Working!");
       Account newAcc;
        Scanner input = new Scanner(System.in);
        pr("First Name? ");
        String fname = input.next();
        
        pr("Last Name? ");
        String lname = input.next();
       
        pr("Date of Birth(mm-dd-yyyy)? ");
        String dob = input.next();
     
        System.out.print("Street Address? ");
        String addr = input.next() + input.nextLine();
        System.out.print("City? ");
        String cty = input.next() + input.nextLine();
        System.out.print("State? ");
        String st = input.next();
        System.out.print("Zip Code? ");
        int z = input.nextInt();
        pr("Deposit? ");
        double bal = input.nextDouble();
        newAcc = new Account(fname,lname,dob,addr,cty,st,z,bal);
        System.out.println("\nAcc Summary: ");
        newAcc.displayInfo();
        boolean upload = true;
        while (upload){
            pl("Is account information correct?Y/N");
            String answer = input.next();
            if ("y".equals(answer.toLowerCase()) || "yes".equals(answer.toLowerCase())){
                writeToFile(newAcc);
                System.out.printf("\nThank you for creating an account at FKCC Banking.\n Your new account number is: %d.  \nPlease keep this number for your records.\n", newAcc.getAccountID());
                break;
            } else if ("n".equals(answer.toLowerCase()) || "no".equals(answer.toLowerCase())){
                editAcc(newAcc);
                newAcc.displayInfo();
            }
        }
       
    }
    public static void pr(String p){
        System.out.print(p);
    }
    public static void pl(String p){
        System.out.println(p);
    }
     //Edit the accoutn before saving to file
    public static void editAcc(Account acc){
        System.out.println("Which field would you like to edit?");
        pl("[1]First Name [2]Last Name [3]Date of Birth [4]Address [5]City [6]State [7]Zip Code [8]Deposit");
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
               case 8:
                   System.out.print("Enter new Deposit Amount:");
                   acc.setBalance(input.nextDouble());
                   break;                   
           }
           System.out.println("*Edit successful*");
    }
    //Write to JSON file
    public static void writeToFile(Account acc){
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
       //Account being created converted to JSONObject to save:
       JSONObject obj = acc.getJSONAccInfo();
		jsonArr.add(obj);
		
        //Write to file
        try (FileWriter file = new FileWriter(json)) {
                file.write(jsonArr.toJSONString());
                System.out.println("Successfully uploaded Account to Database...");
        } catch (IOException ex) {
        Logger.getLogger(createAccount.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
   
}
