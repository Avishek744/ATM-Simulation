import java.util.ArrayList;
import java.util.Scanner;

public class ATM {
    private static ArrayList<Account> accounts = new ArrayList<>();

    static {
        accounts.add(new Account("Avishek", "Avishek111", 5000));
        accounts.add(new Account("Ornob", "Ornob111", 5000));
        accounts.add(new Account("Tusher", "Tusher111", 5000));
        accounts.add(new Account("Urbi", "Urbi111", 5000));
        accounts.add(new Account("Rohit", "Rohit111", 5000));
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        while (true) {
            Account currentUser = null;

            System.out.println("1. Login");
            System.out.println("2. Register");
            System.out.print("Choose option: ");
            String option = sc.nextLine();

            if (option.equals("2")) {
                System.out.print("Enter new username: ");
                String newUsername = sc.nextLine();
                System.out.print("Enter new password: ");
                String newPassword = sc.nextLine();

                boolean exists = false;
                for (Account acc : accounts) {
                    if (acc.getUsername().equals(newUsername)) {
                        exists = true;
                        break;
                    }
                }

                if (exists) {
                    System.out.println("Username already taken. Try again.\n");
                    continue;
                }

                accounts.add(new Account(newUsername, newPassword, 0));
                System.out.println("Registration successful! You can now login.\n");
                continue;
            }

            while (currentUser == null) {
                System.out.print("Enter username: ");
                String username = sc.nextLine();
                System.out.print("Enter password: ");
                String password = sc.nextLine();

                for (Account acc : accounts) {
                    if (acc.getUsername().equals(username) && acc.authenticate(password)) {
                        currentUser = acc;
                        System.out.println("Login successful!\n");
                        break;
                    }
                }

                if (currentUser == null) {
                    System.out.println("Invalid username or password. Please try again.\n");
                }
            }

            while (true) {
                System.out.println("1. Withdraw");
                System.out.println("2. Deposit");
                System.out.println("3. Check Balance");
                System.out.println("4. Logout");
                System.out.print("Choose to option: ");

                int choice = sc.nextInt();

                switch (choice) {
                    case 1:
                        System.out.print("Enter money to be withdrawn: ");
                        int withdraw = sc.nextInt();
                        if (currentUser.withdraw(withdraw)) {
                            System.out.println("Please collect your money\n");
                        } else {
                            System.out.println("You don't have that much money.\n");
                        }
                        break;

                    case 2:
                        System.out.print("Enter money to be deposited: ");
                        int deposit = sc.nextInt();
                        currentUser.deposit(deposit);
                        System.out.println("Your Money has been successfully deposited\n");
                        break;

                    case 3:
                        System.out.println("Balance : " + currentUser.getBalance() + "\n");
                        break;

                    case 4:
                        System.out.println("You have been logged out.\n");
                        sc.nextLine();
                        break;

                    default:
                        System.out.println("Invalid Choice\n");
                }

                if (choice == 4) {
                    break;
                }
            }
        }
    }
}