import service.transaction.TransactionService;
import service.transaction.TransactionServiceImpl;
import service.user.UserService;
import service.user.UserServiceImpl;
import transaction.Transaction;
import user.Gender;
import user.User;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Scanner;

public class Main {
    private static final UserService userService = new UserServiceImpl();

    private static final TransactionService transactionService = new TransactionServiceImpl();

    private static User activeUser;
    public static void main(String[] args) {

        mainloop:
        while (true){
            printMenu();
            int option = getOption();
            boolean isCredentialsAreTrue = false;
            switch (option){
                case 1 -> signup();
                case 2 -> {
                    isCredentialsAreTrue = login();
                }
                case 3 -> {
                    break mainloop;
                }
            }

            if (isCredentialsAreTrue){
                accountloop:
                while (true){
                    printAccountMenu();
                    int accountOption = getAccountOption();
                    switch (accountOption){
                        case 1 -> makeTransaction();
                        case 2 -> listTransaction();
                        case 3 -> calculateBalance();
                        case 4 -> {
                            break accountloop;
                        }
                    }
                }
            }
        }

        System.out.println("*********** GOOD BYE ***********");
    }

    private static void calculateBalance() {
        System.out.println("Your balance is: " + transactionService.calculateBalance(activeUser.getTckn()));
    }

    private static void listTransaction() {
        for (Transaction t : transactionService.getTransactions(activeUser.getTckn())){
            System.out.println("**********************");
            System.out.println("Description: " + t.getDescription());
            System.out.println("Date: " + t.getTransactionDate());
            System.out.println("Amount: " + t.getAmount());
            System.out.println("**********************");
        }
    }

    private static void makeTransaction() {
        Scanner sc = new Scanner(System.in);

        String description;
        double amount;

        System.out.println(" Description: ");
        description= sc.nextLine();
        System.out.println(" Amount: ");
        amount = sc.nextDouble();

        Transaction t = new Transaction(description, LocalDate.now(), BigDecimal.valueOf(amount));
        transactionService.transaction(t, activeUser);
    }

    private static void printAccountMenu() {
        System.out.println(" To make transaction, press 1 ");
        System.out.println(" To list trancastion, press 2 ");
        System.out.println(" To calculate balance, press 3 ");
        System.out.println(" To log out, press 4 ");
    }

    private static int getAccountOption(){
        Scanner sc = new Scanner(System.in);
        return sc.nextInt();
    }

    private static boolean login() {
        String username;
        String password;

        Scanner sc= new Scanner(System.in);
        System.out.println(" Username : ");
        username=sc.next();
        System.out.println(" Password : ");
        password=sc.next();

        User loggedUser = userService.login(username,password);
        if (loggedUser == null){
            return false;
        }
        System.out.println("*********** WELCOME ***********");
        System.out.printf("*********** %s %s *********** \n", loggedUser.getName(), loggedUser.getSurname());
        activeUser = loggedUser;
        return true;
    }

    private static void signup() {
        String username;
        String password;
        String name;
        String surname;
        String tckn;
        String gender;

        Scanner sc= new Scanner(System.in);
        System.out.println(" Username : ");
        username=sc.next();
        System.out.println(" Password : ");
        password=sc.next();
        System.out.println(" Name : ");
        name=sc.next();
        System.out.println(" Surname : ");
        surname=sc.next();
        System.out.println(" TC : ");
        tckn=sc.next();
        System.out.println(" Gender; Female(F) or Male(M) ");
        gender=sc.next();

        User userToRegister = new User(username,password,name,surname,tckn,Gender.parse(gender));
        userService.createAccount(userToRegister);
    }

    public static void printMenu(){
        System.out.println("*********** WELCOME TO UPOD ACCOUNT ***********");
        System.out.println(" If you don't have an account, please press 1 to register ");
        System.out.println(" If you have an account, please press 2 to login ");
        System.out.println(" If you want to close application press 3 to exit ");
    }

    public static int getOption(){
        Scanner sc = new Scanner(System.in);
        return sc.nextInt();
    }
}