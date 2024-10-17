package com.ps;

import java.util.Scanner;
import java.util.ArrayList;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.InputMismatchException;

public class Main {
    static Scanner commandScanner = new Scanner(System.in);
    static Scanner inputScanner = new Scanner(System.in);
    static ArrayList<Transaction> transactions = new ArrayList<>(); // Store transactions

    public static void main(String[] args) {
        // Load existing transactions at startup
        loadTransactionFromFile();

        int mainMenuCommand;
        do {
            System.out.println("Choose an option:");
            System.out.println("1 - Add Deposit");
            System.out.println("2 - Make Payment");
            System.out.println("3 - Ledger");
            System.out.println("0 - Exit");

            try {
                mainMenuCommand = commandScanner.nextInt();
            } catch (InputMismatchException ime) {
                ime.printStackTrace();
                mainMenuCommand = 0;
                commandScanner.nextLine(); // Clear invalid input
            }

            // Switch statement to match the user command to the provided cases
            switch (mainMenuCommand) {
                case 1:
                    addDeposit();
                    break;
                case 2:
                    makePayment();
                    break;
                case 3:
                    displaySubMenu();
                    break;
                case 0:
                    System.out.println("Exiting");
                    break;
                default:
                    System.out.println("Command not found, please try again");
            }

        } while (mainMenuCommand != 0);
    }

    private static void loadTransactionFromFile() {
    }

    public static void addDeposit() {
        System.out.println("Command for adding a Deposit");
        System.out.print("Enter description: ");
        String description = inputScanner.nextLine();
        System.out.print("Enter vendor name: ");
        String vendor = inputScanner.nextLine();
        System.out.print("Enter amount: ");
        double amount = inputScanner.nextDouble();
        inputScanner.nextLine(); // Clear the buffer

        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String formattedDateTime = now.format(formatter);


        Transaction newTransaction = new Transaction(formattedDateTime, description, vendor, amount);
        transactions.add(newTransaction);


        try (BufferedWriter writer = new BufferedWriter(new FileWriter("transactions.csv", true))) {
            writer.write(String.format("%s|%s|%s|%.2f%n", formattedDateTime, description, vendor, amount));
            System.out.println("Deposit added successfully.");

        } catch (IOException e) {
            System.out.println("Error writing to file: " + e.getMessage());
        }
    }

    public static void makePayment() {
        System.out.println("Command for making a Payment");
        System.out.print("Enter description: ");
        String description = inputScanner.nextLine();
        System.out.print("Enter vendor name: ");
        String vendor = inputScanner.nextLine();
        System.out.print("Enter amount: ");
        double amount = inputScanner.nextDouble();
        inputScanner.nextLine(); // Clear the buffer


        amount = -Math.abs(amount);

        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String formattedDateTime = now.format(formatter);

        Transaction newTransaction = new Transaction(formattedDateTime, description, vendor, amount);
        transactions.add(newTransaction);

        try (BufferedWriter writer = new BufferedWriter(new FileWriter("transactions.csv", true))) {
            writer.write(String.format("%s|%s|%s|%.2f%n", formattedDateTime, description, vendor, amount));
            System.out.println("Payment made successfully.");
        } catch (IOException e) {
            System.out.println("Error writing to file: " + e.getMessage());
        }
    }

    public static void displaySubMenu() {
        int subMenuCommand;

        do {
            // Display the submenu options

            System.out.println("Please enter an option: ");
            System.out.println("1) Display All Transactions");
            System.out.println("2) Display All Deposits");
            System.out.println("3) Display All Payments (Debits)");
            System.out.println("4) Reports");
            System.out.println("5) Search by Vendor");
            System.out.println("0) Back to Main Menu");

            subMenuCommand = commandScanner.nextInt();

            // Switch statement to trigger corresponding methods
            switch (subMenuCommand) {
                case 1:
                    displayAllTransactions();
                    break;
                case 2:
                    displayAllDeposits();
                    break;
                case 3:
                    displayAllPayments();
                    break;
                case 4:
                    displayReports();
                    break;
                case 0:
                    System.out.println("Going back to the main menu...");
                    break;
                default:
                    System.out.println("Command not found. Try again");
            }
        } while (subMenuCommand != 0);
    }

    public static void displayAllTransactions() {
        System.out.println("Displaying All Transactions:");
        for (Transaction transaction : transactions) {
            System.out.println(transactions);
        }
    }

    public static void displayAllDeposits() {
        System.out.println("Displaying All Deposits:");
        boolean foundDeposit = false;
        for (Transaction transaction : transactions) {
            if (transaction.getAmount() > 0) {
                System.out.println(transactions);
                foundDeposit = true;
            }
        }
        if (!foundDeposit) {
            System.out.println("No deposits found.");
        }
    }

    public static void displayAllPayments() {
        System.out.println("Displaying All Payments:");
        boolean foundPayment = false;
        for (Transaction transaction : transactions) {
            if (transaction.getAmount() < 0) {
                System.out.println(transactions);
                foundPayment = true;
            }
        }
        if (!foundPayment) {
            System.out.println("No payments found.");
        }
    }

    public static void displayReports() {
        System.out.println("Reports feature is not yet implemented.");
    }
}
