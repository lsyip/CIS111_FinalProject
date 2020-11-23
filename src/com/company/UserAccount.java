package com.company;

import java.util.ArrayList;
import java.util.Scanner;

public class UserAccount{

    //Scanner object to collect user input
    Scanner keyboard = new Scanner(System.in);

    //ArrayList containing: this will store all usernames and passwords in separate lists
    ArrayList<String> username = new ArrayList<>();
    ArrayList<String> password = new ArrayList<>();

    //ArrayList spot for totalBalance
    ArrayList<Double> totalBalance = new ArrayList<>();

    //Variables
    String user;
    String pass;
    double depositAmount;
    double withdrawAmount;
    double userBalance;


    public UserAccount(){

    }

    public void getUsers(String un, String p){
        user = un;
        pass = p;
    }

    // This checks if an user already exists
    public boolean checkNewUserAccount(){
        for(int i = 0; i < username.size(); i++){
            if(user.equalsIgnoreCase(username.get(i)) || pass.equalsIgnoreCase(password.get(i))){
                return true;
            }
        }
        return false;
    }

    public void setUserAccount(){
        //adding to an arrayList is simply .add()
        username.add(user);
        password.add(pass);
        totalBalance.add(0.0); // initialize user account with balance of 0
    }

    public boolean checkUserAccount(){
        for(int i = 0; i < username.size(); i++){
            if(user.equalsIgnoreCase(username.get(i)) && pass.equals(password.get(i))){
                return true;
            }
        }
        return false;
    }

    // When doing the deposit and withdraw method and then setting the total balance, make sure the total balance corresponds to the correct account ->
    // Relate to the if statement in the checkBalance method and you'll end up using the .set() method rather than the .add() since you're changing the value already there
    // .set(i, depositValue or withdrawValue) -> this'll correspond to whatever account is active.
    // totalBalance.get(i) will retrieve the value corresponding to the account

    //deposit method here
    public void depositMethod(){
        for (int i = 0; i < totalBalance.size(); i++) {
            if (user.equalsIgnoreCase(username.get(i)) && pass.equals(password.get(i))) {
                userBalance = (totalBalance.get(i));
                System.out.printf(user.substring(0,1).toUpperCase() + user.substring(1) + " your current balance is: $%.2f \n", userBalance);
                System.out.println("Enter deposit amount: ");
                depositAmount = keyboard.nextDouble();

                while (depositAmount < 0) {
                    System.out.println("Enter deposit amount: ");
                    depositAmount = keyboard.nextDouble();
                }

                double newBalance = userBalance + depositAmount;
                totalBalance.set(i, newBalance);
                System.out.printf(user.substring(0,1).toUpperCase() + user.substring(1) + " your new balance is: $%.2f \n", newBalance);
                break; //no point in continuing the loop
            }
        }
    }


    //withdraw method here
    public void withdrawMethod(){
            //Find the user account and access totalBalance
            for (int i = 0; i < totalBalance.size(); i++) {
                if (user.equalsIgnoreCase(username.get(i)) && pass.equals(password.get(i))) {
                    userBalance = totalBalance.get(i);
                    System.out.printf(user.substring(0,1).toUpperCase() + user.substring(1) + " your current balance is: $%.2f \n", userBalance);
                    System.out.println("Enter withdraw amount: ");
                    withdrawAmount = keyboard.nextDouble();

                    //Checks for negative number and insufficient funds
                    while (withdrawAmount < 0 || withdrawAmount > userBalance) {
                        System.out.println("Invalid withdrawal amount." + "\n" + "Enter withdraw amount: ");
                        withdrawAmount = keyboard.nextDouble();
                        //Checks for sufficient funds.
                        while (withdrawAmount > userBalance) {
                            System.out.println("Insufficient funds available." + "\n" + "Enter withdraw amount: ");
                            withdrawAmount = keyboard.nextDouble();
                        }
                    }

                    //Update user's totalBalance and print information
                    double newBalance = userBalance - withdrawAmount;
                    totalBalance.set(i, newBalance);
                    System.out.printf(user.substring(0,1).toUpperCase() + user.substring(1) + " your new balance is: $%.2f \n", newBalance);
                    break;
                }
            }
        }

    //This method allows for the correct balance to be shown for the corresponding account
    //totalBalance.get(0) == username.get(0) and so on
    public void checkBalance(){
        for(int i = 0; i < totalBalance.size(); i++){
            if(user.equalsIgnoreCase(username.get(i)) && pass.equals(password.get(i))){
                userBalance = totalBalance.get(i);
                System.out.printf(user.substring(0,1).toUpperCase() + user.substring(1) + " your current balance is: $%.2f \n", userBalance);
                break;
            }
        }
    }
}
