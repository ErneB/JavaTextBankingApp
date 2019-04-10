/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bankingapp;

import static bankingapp.Dashboard.Dashboard;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

/**
 *
 * @author Kali
 */
public class SignIn extends Account {
    public static void SignIn(){
        Scanner input = new Scanner(System.in);
        String signInName;
        int signInID;
        System.out.print("Name?");
        signInName = input.next();
        System.out.print("AccountID?");
        signInID = input.nextInt();
        
         String json = "C:\\Users\\Kali\\Desktop\\IntrotoJava\\final\\BankingApp\\src\\bankingapp\\Database.json";
        //Open File:
        JSONParser parser = new JSONParser();
        Object filer;
        JSONArray jsonArr = null;
        try(FileReader file = new FileReader(json)) {
            filer = parser.parse(file);
            jsonArr = (JSONArray) filer;
        } 
        catch (IOException | org.json.simple.parser.ParseException e) {
        }
        
        int validCnt = 1;
        for (Object o : jsonArr)
        {
          JSONObject person = (JSONObject) o;

          String name = (String) person.get("First Name");
          int accountID =  ((Long)person.get("Account ID")).intValue();
          if(name.equals(signInName) && accountID == signInID){
              System.out.println("Log in successful");
              Dashboard(person);
          } else if (validCnt++ == jsonArr.size()){
            System.out.println("Sign in unsuccessful");
        
          }
          validCnt++;
        }

    }
}
