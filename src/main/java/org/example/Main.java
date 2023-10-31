package org.example;

import java.util.ArrayList;
import java.util.List;


public class Main {
    public static void main(String[] args) {
        List<Ledger> transactionList = FileHandlers.readTransactionsFromCSV();

        while (true) {
            int choice = UserInput.homeScreen();
            switch (choice) {
                case 1:
                    HomeScreen.addDeposit((ArrayList<Ledger>) transactionList);
                    break;
                case 2:
                    HomeScreen.makePayment((ArrayList<Ledger>) transactionList);
                    break;
                case 3:
                    HomeScreen.displayLedger((ArrayList<Ledger>) transactionList);
                    break;
                case 4:
                    System.exit(0);
            }
        }
    }
}



