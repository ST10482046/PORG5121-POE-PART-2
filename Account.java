
package com.mycompany.account;
import java.util.*;
import org.json.JSONArray;
import org.json.JSONObject;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
public class Account {
    //Creation of all variables that will be required and can be used in all classes.
public static String firstName;
public static String lastName;
public static String Username;
public static String passWord;
public static String phoneNumber;
public static String checkUsername;
public static String checkPassword;
public static String sentMessage;
public static String tendigitnumber;
public static int optiontwo;
public static String messageOpt;
public static int MessageCount = 0;

public static Random random = new Random();

public static String recipientNumber;
public static Scanner scanner= new Scanner(System.in);
public static int numOfMessages=0;

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
public class message {
//Generates a 10 digit number
public static String messageID(){
                StringBuilder number = new StringBuilder();
for(int i = 0;i<10;i++){
                
               
number.append(random.nextInt(0,10));

}
tendigitnumber = number.toString();
return tendigitnumber;
}
//Checkes wheter the ID has 10 digits
public static boolean checkMessageID(String tendigitnumber){
return tendigitnumber.length() == 10;
}
//Checks if recipient cellphone number is correctly formatted
public static boolean checkrecipientCell(String recipientNumber){
return recipientNumber.matches("^(\\+27|27|0)(6|7|8)[0-9]{8}$");

}
//Creates the message hash
public static String createMessageHash(String tendigitnumber,int numOfMessages,String sentMessage){
String FirstTwo = tendigitnumber.substring(0,2);

// Trim to remove any extra leading/trailing whitespace
sentMessage = sentMessage.trim();

// Extract first word
int firstSpace = sentMessage.indexOf(' ');
String firstWord = (firstSpace != -1) ? sentMessage.substring(0, firstSpace) : sentMessage;

// Extract last word
int lastSpace = sentMessage.lastIndexOf(' ');
 String lastWord = (lastSpace != -1) ? sentMessage.substring(lastSpace + 1) :sentMessage;

// Combine and convert to uppercase
String result = (firstWord + lastWord).toUpperCase();

            
return FirstTwo + ":"+ numOfMessages + ":"+result;
            
 }
public static String sentMessage(){
System.out.println("**********************");
System.out.println("1)Send message");
System.out.println("2)Disregard Message");
System.out.println("3)Store Message to send later");
System.out.print("Pick an option between 1-3: ");
optiontwo = scanner.nextInt();
if(optiontwo==1){
numOfMessages++;
MessageCount++;
messageOpt = "Select send";
return "Message succesfully sent sent";
}else if(optiontwo==2){
numOfMessages++;
messageOpt = "Select Discard";
return "Message discarded";
}else{
numOfMessages++;
messageOpt = "Select Stored";
return "Message succesfully stored";
}
}
//method to print hte message sent,however it is still under development
public static String printMessage(){
StringBuilder output = new StringBuilder();

try {
File file = new File("stored_messages.json");

if (!file.exists() || file.length() == 0) {
return "No messages stored.";
}

 FileReader reader = new FileReader(file);
char[] chars = new char[(int) file.length()];
reader.read(chars);
reader.close();

String content = new String(chars);
JSONArray messagesArray = new JSONArray(content);

for (int i = 0; i < messagesArray.length(); i++) {
JSONObject message = messagesArray.getJSONObject(i);
output.append("Message ").append(i + 1).append(":\n");
output.append("ID: ").append(message.getString("messageID")).append("\n");
output.append("Recipient: ").append(message.getString("recipient")).append("\n");
output.append("Text: ").append(message.getString("message")).append("\n");
output.append("Hash: ").append(message.getString("messageHash")).append("\n");
output.append("Sendmessage: ").append(message.getString("messageOpt")).append("\n");
output.append("Return total number sent: ").append(message.getInt("Return total number sent: ")).append("\n\n");
                
}

} catch (Exception e) {
return "Error reading stored messages: " + e.getMessage();
}

return output.toString();
}
            
public static int returnTotalMessages(){
return MessageCount;
    
}
public static String storeMessage(String recipientNumber, String sentMessage, String messageOpt) {
try {
            
JSONObject messageObject = new JSONObject();
messageObject.put("messageID", message.messageID());
messageObject.put("recipient", recipientNumber);
messageObject.put("message", sentMessage);
messageObject.put("messageHash", message.createMessageHash(tendigitnumber ,numOfMessages, sentMessage));
messageObject.put("messageOpt", messageOpt);
messageObject.put("Return total number sent: ", message.returnTotalMessages());
           
File file = new File("stored_messages.json");
JSONArray messagesArray = new JSONArray();
 if (file.exists() && file.length() > 0) {
 FileReader reader = new FileReader(file);
char[] chars = new char[(int) file.length()];
 reader.read(chars);
reader.close();
String content = new String(chars);
messagesArray = new JSONArray(content);
}

 messagesArray.put(messageObject);
FileWriter writer = new FileWriter(file);
writer.write(messagesArray.toString(4));
writer.close();
return "Message successfully stored.";
} catch (IOException e) {
return "Error writing to file: " + e.getMessage();
} catch (Exception e) {
return "General error: " + e.getMessage();
}
}
public static String clearStoredMessages() {
try {
FileWriter writer = new FileWriter("stored_messages.json");
writer.write("[]"); // Empty JSON array
writer.close();
return "All stored messages have been deleted.";
} catch (IOException e) {
return "Error clearing stored messages: " + e.getMessage();
}
}     
}

public static void main(String[] args) {
System.out.println("====Registration====");
System.out.println(Login.registerUser());
System.out.println("====Login====");
System.out.print("Please enter your username: ");
checkUsername = scanner.nextLine();
System.out.print("Please enter your password: ");
checkPassword = scanner.nextLine();
System.out.println(Login.returnLoginStatus());

if(Login.loginUser(Username, passWord)){
int option;
boolean run = true;
while(run){
System.out.println("===Welcome to QuickChat===");
System.out.println("Option 1)Send Messages");
System.out.println("Option 2) Show recently sent messeages");
System.out.println("Option 3) Quit");
System.out.print("Please select option: ");
option = scanner.nextInt();
if(option == 1){
System.out.println("**********************");
System.out.print("How many messages do you want to send?: ");
int numOFMessages = scanner.nextInt();
for(int i = 0;i<numOFMessages;i++){
scanner.nextLine();
System.out.println("**********************");
System.out.print("Enter Recipient number: ");
recipientNumber = scanner.nextLine();
if(message.checkrecipientCell(recipientNumber)){
System.out.println("Cell phone number succesfully captured");
}else{
while(!message.checkrecipientCell(recipientNumber)){
System.out.println("Cell phone number is incorrectly formatted or odes not contain an international code. ");
System.out.print("Please correct the number and try again: ");
recipientNumber = scanner.nextLine();
}
System.out.println("Cell phone number succesfully captured");
                          
}
System.out.print("Enter your message: ");
sentMessage = scanner.nextLine();
int result = sentMessage.length() - 250;
while(sentMessage.length()>250){
System.out.println("Message exceeds 250 characters by " + result +" ,please reduce size: " );
sentMessage = scanner.nextLine();
}
System.out.println("Message ready to send");
System.out.println(message.sentMessage());                         
 message.storeMessage(recipientNumber, sentMessage, messageOpt);
 }                  
}else if(option ==2){
System.out.println(message.printMessage());
}else if(option == 3){
message.clearStoredMessages();
run = false;
}                      
}           
System.out.println( "Program exited");
}}}



