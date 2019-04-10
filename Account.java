/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bankingapp;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.InputMismatchException;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;


/**
 *
 * @author Kali
 */
public class Account {  
    //Variables           
    private double interestRate = 2.5;
    private LocalDate lastCharged;
    private double monthlyCharge = 10;
    private int numberOfAccounts = 1000 + currentNumberofAcc();
    private int zip, accountID;
    private String firstName;
    private String lastName;
    private String d_o_b, address, city, state ;
    private double balance;
    private LocalDate dateCreated;
    
    //Constructors
    Account(){
        numberOfAccounts++;
        firstName = "No First Name";
        lastName = "No Last Name";
        d_o_b = "No Date of Birth";
        address = "No address";
        city = "No city";
        state = "No state";
        zip = 00000;
        balance = 0;
        accountID = numberOfAccounts;
        dateCreated = LocalDate.now();
        lastCharged = dateCreated;
    }
    Account(String fname, String lname, String dob, String adr,
            String cty,String st, int z, double bal){
        numberOfAccounts++;
        firstName = fname;
        lastName = lname;
        d_o_b = dob;
        address = adr;
        city = cty;
        state = st;
        zip = z;
        balance = bal;
        accountID = numberOfAccounts;
        dateCreated = LocalDate.now();
        lastCharged = dateCreated;
    }
    Account(String fname, String lname, String dob, String adr,
            String cty,String st, int z, double bal, int accID, LocalDate d, LocalDate c){
        numberOfAccounts++;
        firstName = fname;
        lastName = lname;
        d_o_b = dob;
        address = adr;
        city = cty;
        state = st;
        zip = z;
        balance = bal;
        accountID = accID;
        dateCreated = d;
        lastCharged = c;
    }
    //Set Methods
    public void setFName(String name){
        if (name.matches("^[A-Z][a-z]+( [A-Z][a-z]+)?")) {
			this.firstName = name;
		}
    }
    public void setLName(String name){
        if (name.matches("^[A-Z][a-z]+( [A-Z][a-z]+)?")) {
			this.lastName = name;
		}
    }
    public void setDOB(String dob){
       
        if (dob.matches("[0-9]{2}-[0-9]{2}-[0-9]{4}")) {
			this.d_o_b = dob;
		}
        
    }
    public void setAddress(String addr){
      
        if (addr.matches("^[a-zA-Z0-9\\s,'-]*$")) {
			this.address = addr;
		}
    }
    public void setCity(String cty){
    
        if (cty.matches("^[A-Z][a-z]+( [A-Z][a-z]+)?")) {
			this.city = cty;
		}
    }
    public void setState(String st){
        if (st.matches("^[A-Z][a-z]+( [A-Z][a-z]+)?")) {
			this.state = st;
		}
    }
    public void setZip(int z){
        if (z == (int)z) {
			this.zip = z;
		}
    }
    public void setBalance(double b){
        try {
			if (b > 0) {
				this.balance = b;
			}
		}
        catch (InputMismatchException ex) {
			System.out.print("Not valid input.");
			}
		
    }
    public void setlastCharge(LocalDate a){
        this.lastCharged = a;
    }
    //Get methods
    public void displayInfo(){
        System.out.println("//////////////////////////////////////");
        System.out.printf("%-15s:%20s//%n","First Name",firstName);
        System.out.printf("%-15s:%20s//%n", "Last Name",lastName);
        System.out.printf("%-15s:%20d//%n", "Account ID",accountID);
        System.out.printf("%-15s:%20s//%n", "Date of Birth",d_o_b);
        System.out.printf("%-15s:%20s//%n", "Street Address",address);
        System.out.printf("%-15s:%20s//%n", "City",city);
        System.out.printf("%-15s:%20s//%n", "State",state);
        System.out.printf("%-15s:%20s//%n", "Zip code",zip);
        System.out.printf("%-15s:%20s//%n", "Balance",balance);
        System.out.printf("%-15s:%20s//%n", "Account made",dateCreated);
        System.out.printf("%-15s:%20s//%n", "Interest Rate",interestRate);
        System.out.printf("%-15s:%20s//%n", "Last Charged",lastCharged);
        System.out.println("//////////////////////////////////////");
    }
    public String getFName(){
        return firstName;
    }
    public String getLName(){
        return lastName;
    }
    public String getDOB(){
        return d_o_b;
    }
    public String getAddress(){
        return address;
    }
    public String getCity(){
        return city;
    }
    public String getState(){
        return state;
    }
    public int getZip(){
        return zip;
    }
    public double getInterestRate(){
        return interestRate;
    }
    public double getBalance(){
        return balance;
    }
    public int getAccountID(){
        return accountID;
    }
    public LocalDate getDateCreated(){
        return dateCreated;
    }
    public LocalDate getlastCharged(){
        return this.lastCharged;
    }
    public double getMonthlyCharge(){
        return monthlyCharge;
    }
    public JSONObject getJSONAccInfo(){
        DateTimeFormatter formatter_1 = DateTimeFormatter.ofPattern("dd-MMM-yyyy");
        String date=(this.getDateCreated()).format(formatter_1);
        String lastcharge=(this.getlastCharged()).format(formatter_1);
      JSONObject obj = new JSONObject();
      obj.put("First Name", this.getFName());
      obj.put("Last Name", this.getLName());
      obj.put("Balance", this.getBalance());
      obj.put("Date of Birth", this.getDOB());
      obj.put("Stree Address", this.getAddress());
      obj.put("City", this.getCity());
      obj.put("State", this.getState());
      obj.put("Zip", this.getZip());
      obj.put("Account ID", this.getAccountID());
      obj.put("Date Created", date);
      obj.put("Last Charged", lastcharge);
      return obj;
    }
    int currentNumberofAcc(){
            JSONParser parser = new JSONParser();
            Object obj;
            JSONArray jsonArr = null;
            
 
        try(FileReader file = new FileReader(
            "C:\\Users\\Kali\\Desktop\\IntrotoJava\\final\\BankingApp\\src\\bankingapp\\Database.json")) {
            obj = parser.parse(file);
            jsonArr = (JSONArray) obj;
        } 
        
        catch (IOException | org.json.simple.parser.ParseException e) {
        }
        if (jsonArr == null) {
            return 0;
        }
        return jsonArr.size();
    }
    
}
