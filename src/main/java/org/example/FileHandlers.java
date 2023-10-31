package org.example;

import java.io.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class FileHandlers {
    private static final String transaction = "src/main/resources/transactions.csv";
    private static final DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    private static final DateTimeFormatter timeFormat = DateTimeFormatter.ofPattern("HH:mm");

    public static List<Ledger> readTransactionsFromCSV() {
        List<Ledger> transactionList = new ArrayList<>();
        try {
            FileInputStream fis = new FileInputStream(transaction);
            Scanner scanner = new Scanner(fis);
            scanner.nextLine();
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String[] transaction = line.split("\\|");
                LocalDate date = LocalDate.parse(transaction[0], dateFormat);
                LocalTime time = LocalTime.parse(transaction[1], timeFormat);
                String description = transaction[2];
                String vendor = transaction[3];
                double amount = Double.parseDouble(transaction[4]);
                Ledger newTransaction = new Ledger(date, time, description, vendor, amount);
                transactionList.add(newTransaction);
            }
            fis.close();
            scanner.close();
        } catch (FileNotFoundException ex) {
            System.out.println("File not found: " + transaction);
        } catch (IOException ex) {
            System.out.println("Error reading transactions from CSV.");
        }
        return transactionList;
    }

    static void saveTransaction(Ledger newTransaction) {
        try {
            File file = new File("src/main/resources/transactions.csv");

            boolean fileExists = file.exists();

            if (!fileExists) {
                FileWriter newTransactionWriter = new FileWriter("src/main/resources/transactions.csv");
                newTransactionWriter.write("Date|Time|Description|Vendor|Amount\n");
                newTransactionWriter.close();
            }

            FileWriter appendTransactionWriter = new FileWriter("src/main/resources/transactions.csv", true);

            appendTransactionWriter.write(
                    newTransaction.getDate() + "|" +
                            newTransaction.getTime() + "|" +
                            newTransaction.getDescription() + "|" +
                            newTransaction.getVendor() + "|" +
                            newTransaction.getAmount() + "\n"
            );
            appendTransactionWriter.close();
        } catch (IOException ex) {
            System.out.println("There's a problem with saving the transaction");
        }
    }
}
