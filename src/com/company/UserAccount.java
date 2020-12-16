package com.company;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * The UserAccount class contains the variables and methods necessary to manage several created user accounts at one time
 * and present data about each individual account through the main UserAccountUI class.
 */
public class UserAccount{

    //Scanner object to collect user input
    Scanner keyboard = new Scanner(System.in);

    //ArrayList containing all usernames and passwords in separate lists
    ArrayList<String> username = new ArrayList<>();
    ArrayList<String> password = new ArrayList<>();

    //ArrayList for totalBalance
    ArrayList<Double> totalBalance = new ArrayList<>();

    //2D ArrayList for transactionHistory
    ArrayList<ArrayList<String>> transactionHistory = new ArrayList<>();
    int historyIndex = 0; //used to initialize the first row of ArrayLists when a new account is created


    //Variables
    String user; //Username
    String pass; //Password
    double depositAmount;
    double withdrawAmount;
    double userBalance;

    /**
     * This is the class constructor for the UserAccount class.
     * The constructor is called when an object of the UserAccount class is created.
     */
    public UserAccount(){

    }

    /**
     * This is the class getter for the UserAccount class.
     * @param username the username created by the user
     * @param password the password created by the user
     */
    public void getUsers(String username, String password){
        user = username;
        pass = password;
    }

    /**
     * The checkNewUserAccount() method verifies that a newly created user account is unique and
     * does not match any existing accounts.
     * @return true if the user account already exists
     */
    // This checks if an user already exists
    public boolean checkNewUserAccount(){
        for(int i = 0; i < username.size(); i++){
            if(user.equals(username.get(i))){
                return true;
            }
        }
        return false;
    }

    /**
     * The setUserAccount() method adds information to the username, password, totalBalance, and transactionHistory ArrayLists
     * when a new account is created.
     */
    public void setUserAccount(){
        //adding to an arrayList is simply .add()
        username.add(user);
        password.add(pass);
        totalBalance.add(0.0); // Initialize user account with balance of 0
        transactionHistory.add(new ArrayList());
        transactionHistory.get(historyIndex).add("You created a new account.");
        historyIndex++;
    }

    //Checks for matching username and password
    //Used to interact with the correct user account

    /**
     * The checkUserAccount() method verifies that the entered username and password match.
     * @return true if user account information is a match
     */
    public boolean checkUserAccount(){
        for(int i = 0; i < username.size(); i++){
            if(user.equalsIgnoreCase(username.get(i)) && pass.equals(password.get(i))){
                return true;
            }
        }
        return false;
    }

    /**
     * Prompts the user to enter a dollar amount to deposit, updates the user's total balance, and displays the new balance after the transaction.
     */
    public void depositMethod(){
        for (int i = 0; i < totalBalance.size(); i++) {
            //Find the correct user index and retrieve their total account balance
            if (user.equalsIgnoreCase(username.get(i)) && pass.equals(password.get(i))) {
                userBalance = (totalBalance.get(i));
                System.out.printf(user.substring(0,1).toUpperCase() + user.substring(1) + " your current balance is: $%.2f \n", userBalance);

                //Prompt user for deposit amount
                System.out.println("Enter deposit amount: ");
                depositAmount = keyboard.nextDouble();

                //Check if deposit amount is a positive number
                while (depositAmount < 0) {
                    System.out.println("Enter deposit amount: ");
                    depositAmount = keyboard.nextDouble();
                }

                //Update totalBalance value
                double newBalance = userBalance + depositAmount;
                totalBalance.set(i, newBalance);

                //Add deposit amount to transaction history array
                transactionHistory.get(i).add("You deposited $" + depositAmount);

                //Display new total balance
                System.out.printf(user.substring(0,1).toUpperCase() + user.substring(1) + " your new balance is: $%.2f \n", newBalance);
                break; //no point in continuing the loop
            }
        }
    }

    /**
     * Prompts the user to enter a dollar amount to withdraw, updates the user's total balance, and displays the total balance after the transaction.
     */
    public void withdrawMethod(){
            //Find the correct user account index and retrieve their totalBalance
            for (int i = 0; i < totalBalance.size(); i++) {
                if (user.equalsIgnoreCase(username.get(i)) && pass.equals(password.get(i))) {
                    userBalance = totalBalance.get(i);
                    System.out.printf(user.substring(0,1).toUpperCase() + user.substring(1) + " your current balance is: $%.2f \n", userBalance);

                    //Prompt user for withdrawal amount
                    System.out.println("Enter withdraw amount: ");
                    withdrawAmount = keyboard.nextDouble();

                    //Checks for negative withdrawal amount or insufficient funds
                    while (withdrawAmount < 0 || withdrawAmount > userBalance) {
                        System.out.println("Invalid withdrawal amount." + "\n" + "Enter withdraw amount: ");
                        withdrawAmount = keyboard.nextDouble();
                    }

                    //Update user's totalBalance
                    double newBalance = userBalance - withdrawAmount;
                    totalBalance.set(i, newBalance);

                    //Add withdraw action to transaction history
                    transactionHistory.get(i).add("You withdrew $" + withdrawAmount);

                    //Display new user balance
                    System.out.printf(user.substring(0,1).toUpperCase() + user.substring(1) + " your new balance is: $%.2f \n", newBalance);
                    break;
                }
            }
        }

    /**
     * Displays all user transaction history for a single user.
     */
    public void transactionHistory(){
        for (int i = 0; i < totalBalance.size(); i++) {
            //Find the index of the current user and print everything from that array
            if (user.equalsIgnoreCase(username.get(i)) && pass.equals(password.get(i)))
                for (String n : transactionHistory.get(i)){
                    System.out.println(n);
                }
            }
    }

    /**
     * The checkBalance() method retrieves the appropriate user balance depending on which user is currently logged in.
     */
    public void checkBalance(){
        for(int i = 0; i < totalBalance.size(); i++){
            //Find the index of that user and print the balance stored there
            if(user.equalsIgnoreCase(username.get(i)) && pass.equals(password.get(i))){
                userBalance = totalBalance.get(i);
                System.out.printf(user.substring(0,1).toUpperCase() + user.substring(1) + " your current balance is: $%.2f \n", userBalance);
                break;
            }
        }
    }
}