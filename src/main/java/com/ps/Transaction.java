package com.ps;

public class Transaction {
    private String date;
    private String description;
    private String vendor;
    private double amount;

    public Transaction(String date, String description, String vendor, double amount) {
        this.date = date;
        this.description = description;
        this.vendor = vendor;
        this.amount = amount;
    }

    public String getDate() {
        return date;
    }

    public String getDescription() {
        return description;
    }

    public String getVendor() {
        return vendor;
    }

    public double getAmount() {
        return amount;
    }

    @Override
    public String toString() {
        return String.format("Date: %s | Description: %s | Vendor: %s | Amount: %.2f", date, description, vendor, amount);
    }
}

