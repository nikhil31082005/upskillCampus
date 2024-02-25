import java.util.*;

class Client {
    private String name;
    private String address;
    private String contactInfo;
    private double balance;

    public Client(String name, String address, String contactInfo, double balance) {
        this.name = name;
        this.address = address;
        this.contactInfo = contactInfo;
        this.balance = balance;
    }

    public String getName() {
        return name;
    }

    public String getAddress() {
        return address;
    }

    public String getContactInfo() {
        return contactInfo;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }
}

public class BankingInformationSystem {
    static Map<Integer, Client> users = new HashMap<>();
    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        while (true) {
            System.out.println("Welcome to the Banking Information System");
            System.out.println("1. Register\n2. Login\n3. Exit");
            System.out.print("Enter your choice: ");
            int choice = sc.nextInt();
            sc.nextLine(); // Consume newline

            if (choice == 1) {
                registerUser();
            }
            else if (choice == 2) {
                loginUser();
            }
            else if (choice == 3) {
                System.out.println("Thank you for using the Banking Information System. Goodbye!");
                System.exit(0);
            }
            else {
                System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    public static void registerUser() {
        System.out.println("User Registration");
        System.out.print("Enter your name: ");
        String name = sc.nextLine();
        System.out.print("Enter your address: ");
        String address = sc.nextLine();
        System.out.print("Enter your contact information: ");
        String contactInfo = sc.nextLine();
        System.out.print("Enter initial deposit amount: ");
        double initialDeposit = sc.nextDouble();
        sc.nextLine(); // Consume newline

        int accountNumber = generateAccountNumber();
        users.put(accountNumber, new Client(name, address, contactInfo, initialDeposit));
        System.out.println("Registration successful. Your account number is: " + accountNumber);
    }

    private static void loginUser() {
        System.out.print("Enter your account number: ");
        int accountNumber = sc.nextInt();
        Client user = users.get(accountNumber);
        if (user != null) {
            System.out.println("Login successful. Welcome, " + user.getName() + "!");
            showMenu(user);
        } else {
            System.out.println("Invalid account number. Please try again.");
        }
    }

    private static void showMenu(Client user) {
        while (true) {
            System.out.println("\nAccount Management Menu");
            System.out.println("1. View Account Details\n2. Deposit\n3. Withdraw\n4. Transfer Funds\n5. Logout");
            System.out.print("Enter your choice: ");
            int choice = sc.nextInt();
            sc.nextLine(); // Consume newline


            if (choice == 1) {
                viewAccountDetails(user);
            }
            else if (choice == 2) {
                deposit(user);
            }
            else if (choice == 3) {
                withdraw(user);
            }
            else if (choice == 4) {
                transferFunds(user);
            }
            else if (choice == 5) {
                System.out.println("Logout successful.");
                return;
            }
            else {
                System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private static void viewAccountDetails(Client user) {
        System.out.println("\nAccount Details");
        System.out.println("Name: " + user.getName());
        System.out.println("Address: " + user.getAddress());
        System.out.println("Contact Information: " + user.getContactInfo());
        System.out.println("Account Balance: $" + user.getBalance());
    }

    private static void deposit(Client user) {
        System.out.print("Enter deposit amount: ");
        double amount = sc.nextDouble();
        if (amount > 0) {
            user.setBalance(user.getBalance() + amount);
            System.out.println("Deposit successful. Your new balance is: $" + user.getBalance());
        } else {
            System.out.println("Invalid amount. Please enter a positive value.");
        }
    }

    private static void withdraw(Client user) {
        System.out.print("Enter withdrawal amount: ");
        double amount = sc.nextDouble();
        if (amount > 0 && amount <= user.getBalance()) {
            user.setBalance(user.getBalance() - amount);
            System.out.println("Withdrawal successful. Your new balance is: $" + user.getBalance());
        } else {
            System.out.println("Invalid amount or insufficient funds.");
        }
    }

    private static void transferFunds(Client sender) {
        System.out.print("Enter recipient's account number: ");
        int recipientAccountNumber = sc.nextInt();
        if (users.containsKey(recipientAccountNumber)) {
            Client recipient = users.get(recipientAccountNumber);
            System.out.print("Enter transfer amount: ");
            double amount = sc.nextDouble();
            if (amount > 0 && amount <= sender.getBalance()) {
                sender.setBalance(sender.getBalance() - amount);
                recipient.setBalance(recipient.getBalance() + amount);
                System.out.println("Transfer successful. Your new balance is: $" + sender.getBalance());
            } else {
                System.out.println("Invalid amount or insufficient funds.");
            }
        } else {
            System.out.println("Recipient account not found or invalid.");
        }
    }

    private static int generateAccountNumber() {
        // This is a simplified way to generate a random account number
        Random random = new Random();
        int max = 999999999;
        int min = 100000000;
        return random.nextInt(max-min+1);
    }
}
