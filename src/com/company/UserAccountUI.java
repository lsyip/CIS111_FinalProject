package com.company;

import java.util.Scanner;

/**
 * The UserAccountUI class contains the main User Interface method for a basic banking application.
 */
public class UserAccountUI {

    /**
     * The main method presents the user interface for a basic banking application.
     * The first menu asks the user to create a new account or to sign in, for returning users.
     * The second menu presents banking options including deposit, withdraw, check balance, and show transaction history
     * @param args The command line arguments.
     */
    public static void main(String[] args) {

        //Creates a UserAccount object for interacting with user accounts
        UserAccount useraccount = new com.company.UserAccount();

        //Create a Scanner object for keyboard input
        Scanner kbInput = new Scanner(System.in);

        //Variables
        int userInput;
        String username;
        String password;
        boolean isDone = false; // Checks status of program

        //Initial menu asks user to create new account, log in with existing account, or exit
        while(!isDone){
            System.out.print("Welcome to CIS111 Bank. Please choose an option to continue:\n" +
                             "1: Create a new account\n" +
                             "2: Sign in\n" +
                             "3: Exit\n");

            //Variable to hold the option the user chooses
            userInput = kbInput.nextInt();

            //Create new account if option 1 is chosen
            if(userInput == 1){
                do{
                    System.out.println("Please create a new account below.");
                    System.out.println("Please enter an username that is not taken");
                    username = kbInput.next();
                    System.out.println("\n" + "Please enter a password that is not taken");
                    password = kbInput.next();
                    useraccount.getUsers(username, password); //Checks existing user accounts to verify no duplicates
                    if(useraccount.checkNewUserAccount()){
                        System.out.println("Sorry that username or password was taken. Try again.");
                    }
                }while(useraccount.checkNewUserAccount());

                //When the Create Account process is successful, the account is set with the setUserAccount() method
                useraccount.setUserAccount();

                    // The second menu presents banking options to users and executes the appropriate method given the user input
                    while(!isDone){
                        System.out.println("Thank you for choosing us today, " + username.substring(0,1).toUpperCase() + username.substring(1));
                        System.out.println("1: Deposit\n" +
                                           "2: Withdraw\n" +
                                           "3: Check Balance\n" +
                                           "4: Show Transaction History\n" +
                                           "5: Exit\n");
                        userInput = kbInput.nextInt();
                        switch (userInput){
                            case 1: //Deposit
                                useraccount.depositMethod();
                                break;
                            case 2: //Withdraw
                                useraccount.withdrawMethod();
                                break;
                            case 3: //Check Balance
                                useraccount.checkBalance();
                                break;
                            case 4: //Print Transaction History
                                useraccount.transactionHistory();
                                break;
                            default: //Default case exits program
                                System.out.println("Thank you for banking with us today" + "\n" + "\n");
                                isDone = true;
                                break;
                        }
                    }
                    isDone = false; //Lets the program continue

            }else if(userInput == 2){ //If returning user, program will prompt user to log in using their credentials
                System.out.println("Please enter your username");
                username = kbInput.next(); //nextLine() was causing it to skip over, I don't exactly know why but I think it has something to do with nextInt();
                System.out.println("\n" + "Please enter your password");
                password = kbInput.next();
                useraccount.getUsers(username, password);

                //If checkUserAccount() method returns true, the user will be presented with the banking options menu
                if(useraccount.checkUserAccount()){
                    System.out.println("\n" + "Welcome back, " + username.substring(0,1).toUpperCase() + username.substring(1));
                    while(!isDone){
                        System.out.println("1: Deposit\n" +
                                "2: Withdraw\n" +
                                "3: Check Balance\n" +
                                "4: Show Transaction History\n" +
                                "5: Exit\n");
                        userInput = kbInput.nextInt();
                        switch (userInput){
                            case 1: //Deposit
                                useraccount.depositMethod();
                                break;
                            case 2: //Withdraw
                                useraccount.withdrawMethod();
                                break;
                            case 3: //Check Balance
                                useraccount.checkBalance();
                                break;
                            case 4: //Transaction History
                                useraccount.transactionHistory();
                                break;
                            default: //Default case exits the program
                                System.out.println("Thank you for banking with us today" + "\n" + "\n");
                                isDone = true;
                                break;
                        }
                    }
                    isDone = false;
                }else{ //If the checkUserAccount() returns false, the user wil be prompted to retry the login
                    System.out.println("Some of the information above was entered incorrectly. Please try again" + "\n");
                }
            }else {
                System.out.println("Thank you for banking with us today.");
                isDone = true;
            }
        }
    }
}

