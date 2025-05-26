
package com.mycompany.account;
import java.util.*;
public class Account {
    //Creation of all variables that will be required and can be used in all classes.
public static String firstName;
public static String lastName;
public static String Username;
public static String passWord;
public static String phoneNumber;
public static String checkUsername;
public static String checkPassword;
public class Login {
    //This method checks wheteher username is correctly formatted or not.
 public static boolean checkUserName(String Username){
//Returns true is password length is less than five characters and contains a underscore
return Username.length()<= 5 && Username.contains("_")  ; 
   
   } 
 //This method checks wheteher password is correctly formatted or not.
 public static boolean checkPasswordComplexity(String passWord){
 //Returns true if password length is greater than 8, contains letters, numbers and a special character of user's choice.
return  passWord.length() >= 8 &&
passWord.matches(".*[A-Z].*") &&
passWord.matches(".*[0-9].*") &&
passWord.matches(".*[!@#$%^&*()_+=<>?/{}~`|\\\\].*");
   }
//This method checks wheteher cellphone is correctly formatted or not.
public static boolean checkCellPhoneNumber(String phoneNumber){
//Returns true if cellphone number contains a correct format o a South African number. 
return phoneNumber.matches("^(\\+27|27|0)(6|7|8)[0-9]{8}$");
}

//This method contains all coding to register a user.
public static String registerUser(){
//Enter user information
System.out.print("Please enter your Firstname: ");
firstName = scanner.nextLine();
System.out.print("Please enter your lastname: ");
lastName = scanner.nextLine();
System.out.print("Please enter your username: ");
Username = scanner.nextLine();
if(Login.checkUserName(Username)){
System.out.println("Username succesfully captured." );               
 }else{
 while(!Login.checkUserName(Username)){
 System.out.println("Username is not correctly formatted, please ensure that your username contains an underscore and is no more than five characters in length.");
 System.out.print("Please enter username again: ");
 Username = scanner.nextLine();
}
System.out.println("Username succesfully captured" );
}
System.out.print("Please enter password: ");
  passWord= scanner.nextLine();         
 if(Login.checkPasswordComplexity(passWord)){
System.out.println("Password succesfully captured");                  
}                    
else{                      
while(!Login.checkPasswordComplexity(passWord)){
System.out.println("Password is not correctly formatted.Please ensure that the password contains at least eight characters, a capital letter ,  a number and a special character: ");
 passWord = scanner.nextLine();
}
 System.out.println("Password succesfully captured");
}

System.out.print("Please enter cell phone number: ");
phoneNumber = scanner.nextLine();
if(Login.checkCellPhoneNumber( phoneNumber)){
return "Cell phone number successfully added.\n"  + "You have been registered";
 }
else{
 while(!Login.checkCellPhoneNumber( phoneNumber)){
System.out.println("Cell phone number incorrectly formatted or does not contain international code");
System.out.print("Please enter cell phone number again: ");
phoneNumber = scanner.nextLine();
}
 return "Cell phone number successfully added.\n"  + "You have been registered";
}

}
//Checks if login information entered matches registered information 
public static boolean loginUser(String Username,String passWord){
return checkUsername.matches(Username)&& checkPassword.matches(passWord);
}
//Prints whether user has succesfully logged in or not
public static String returnLoginStatus(){
  if(Login.loginUser(Username,passWord)){
return "Welcome "+firstName +" "+ lastName +" it is great to see you";
}else{
return  "Username or password is incorrect please try again " ;
}
}
}

public static Scanner scanner = new Scanner(System.in);
public static void main(String[] args) {
System.out.println("====Registration====");
System.out.println(Login.registerUser());
System.out.println("====Login====");
System.out.print("Please enter your username: ");
checkUsername = scanner.nextLine();
System.out.print("Please enter your password: ");
checkPassword = scanner.nextLine();
System.out.println(Login.returnLoginStatus());
}   
}
