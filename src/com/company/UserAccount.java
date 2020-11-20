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
    double initialBalance;
    double depositAmount;
    double withdrawAmount;
    double userBalance;


    public UserAccount(){

    }

    public void getUsers(String un, String p){
        user = un;
        pass = p;
        initialBalance = 0;
    }

    public void setUserAccount(){
        //adding to an arrayList is simply .add()
        username.add(user);
        password.add(pass);
        totalBalance.add(0.0); // initialize user account with balance of 0
    }

    //   .get allows you to retrieve the value in the arrayList -> same as doing array[2] = "hello"
    //First time it goes through username.size == 1 meaning it doesn't go through the loop since 1-1 == 0 and 0 isn't less than 0 but equals to
    //Second time username.size == 2, it will then check against the first index of username and password, to the second index of username and password
    //For example if the size was five, then it would check username.get(i = 0) against username.get{j = 1,2,3,4), next iteration username.get(i = 1) against username.get(j = 2, 3, 4), and so on
    //Returns true if either username or password ("||") is the same, false if different
    public boolean checkDuplicateUserAccount(){
        for(int i = 0; i < (username.size() - 1); i++){
            for(int j = i + 1; j < username.size(); j++){
                if(username.get(i).equalsIgnoreCase(username.get(j)) || password.get(i).equals(password.get(j))){
                    return true;
                }
            }
        }
        return false;
    }

    //If checkUserAccount == true, then it removes the last username and password entered since that one would be the duplicate.
    public void deleteDuplicates(){
        if(checkDuplicateUserAccount()){
            username.remove(username.size() - 1);
            password.remove(password.size() - 1);
            totalBalance.remove(totalBalance.size() - 1); //removes the one created with the duplicate account
        }
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
            if (user.equals(username.get(i)) && pass.equals(password.get(i))) {
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
            //Find the user account
            for (int i = 0; i < totalBalance.size(); i++) {
                if (user.equals(username.get(i)) && pass.equals(password.get(i))) {
                    userBalance = totalBalance.get(i);
                    System.out.printf(user.substring(0,1).toUpperCase() + user.substring(1) + " your current balance is: $%.2f \n", userBalance);
                    System.out.println("Enter withdraw amount: ");
                    withdrawAmount = keyboard.nextDouble();

                    while (withdrawAmount > userBalance) {
                        System.out.println("Insufficient funds available." + "\n" + "Enter withdraw amount: ");
                        withdrawAmount = keyboard.nextDouble();
                    }

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
            if(user.equals(username.get(i)) && pass.equals(password.get(i))){
                userBalance = totalBalance.get(i);
                System.out.printf(user.substring(0,1).toUpperCase() + user.substring(1) + " your current balance is: $%.2f \n", userBalance);
                break;
            }
        }
    }
}
