import java.util.ArrayList;
import java.util.Scanner;

public class ATMSimulation {

    private static final String DEFAULT_PIN = "1234";
    private static final double INITIAL_BALANCE = 1000.0;

    private double balance;
    private String pin;
    private ArrayList<String> transactionHistory;
    
    public ATMSimulation() {
        this.balance = INITIAL_BALANCE;
        this.pin = DEFAULT_PIN;
        this.transactionHistory = new ArrayList<>();
    }
    
    public boolean verifyPin(String enteredPin) {
        return this.pin.equals(enteredPin);
    }

    public void changePin(String newPin) {
        this.pin = newPin;
        addToTransactionHistory("PIN changed");
    }

    public double getBalance() {
        return balance;
    }

    public void deposit(double amount) {
        if (amount > 0) {
            this.balance += amount;
            addToTransactionHistory("Deposited: $" + amount);
        } else {
            System.out.println("Deposit amount must be positive.");
        }
    }

    public boolean withdraw(double amount) {
        if (amount > 0 && amount <= balance) {
            this.balance -= amount;
            addToTransactionHistory("Withdrew: $" + amount);
            return true;
        } else if (amount <= 0) {
            System.out.println("Withdrawal amount must be positive.");
        } else {
            System.out.println("Insufficient funds.");
        }
        return false;
    }

    public void viewTransactionHistory() {
        if (transactionHistory.isEmpty()) {
            System.out.println("No transactions yet.");
        } else {
            for (String transaction : transactionHistory) {
                System.out.println(transaction);
            }
        }
    }

    private void addToTransactionHistory(String transaction) {
        transactionHistory.add(transaction);
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ATMSimulation atm = new ATMSimulation();
        
        System.out.println("Welcome to the ATM");
        
        while (true) {
            System.out.println("Enter your PIN:");
            String enteredPin = scanner.nextLine();
            
            if (!atm.verifyPin(enteredPin)) {
                System.out.println("Invalid PIN. Try again.");
                continue;
            }
            
            while (true) {
                System.out.println("\n1. Check Balance");
                System.out.println("2. Deposit Cash");
                System.out.println("3. Withdraw Cash");
                System.out.println("4. Change PIN");
                System.out.println("5. View Transaction History");
                System.out.println("6. Exit");
                System.out.print("Choose an option: ");
                
                int option = scanner.nextInt();
                scanner.nextLine();  // Consume newline

                switch (option) {
                    case 1:
                        System.out.println("Current Balance: $" + atm.getBalance());
                        break;

                    case 2:
                        System.out.print("Enter amount to deposit: $");
                        double depositAmount = scanner.nextDouble();
                        scanner.nextLine();  // Consume newline
                        atm.deposit(depositAmount);
                        System.out.println("Deposit successful.");
                        break;

                    case 3:
                        System.out.print("Enter amount to withdraw: $");
                        double withdrawAmount = scanner.nextDouble();
                        scanner.nextLine();  // Consume newline
                        if (atm.withdraw(withdrawAmount)) {
                            System.out.println("Withdrawal successful.");
                        }
                        break;

                    case 4:
                        System.out.print("Enter new PIN: ");
                        String newPin = scanner.nextLine();
                        atm.changePin(newPin);
                        System.out.println("PIN changed successfully.");
                        break;

                    case 5:
                        System.out.println("Transaction History:");
                        atm.viewTransactionHistory();
                        break;

                    case 6:
                        System.out.println("Exiting. Thank you for using the ATM.");
                        scanner.close();
                        return;

                    default:
                        System.out.println("Invalid option. Please try again.");
                        break;
                }
            }
        }
    }
}
