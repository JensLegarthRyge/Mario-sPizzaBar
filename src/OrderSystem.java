import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

public class OrderSystem {
    static Scanner scanner = new Scanner(System.in);
    static boolean validation = true;
    public static void main(String[] args) {
        fillMenuArrayListFromTxtFile();

        chooseUser();

    }

    public static void chooseUser() {
        String userInfo = "Press 1 for Marios user \nPress 2 for Alfredo's user \nPress 3 to exit program \n";
        System.out.println(userInfo);

        int marioOrAlfredo;

        do {
            marioOrAlfredo = getIntegerInput();
            validation = false;
            if (marioOrAlfredo == 1) {
                marioProgram();
                System.out.println(userInfo);
            }
            else if (marioOrAlfredo == 2) {
                alfredoProgram();
                System.out.println(userInfo);
            } else if (marioOrAlfredo == 3) {
                System.out.println("Exiting program");
                validation = false;
            } else {
                System.out.println("\nPlease enter 1, 2 or 3\n");
                System.out.println(userInfo);

            }
        } while (validation);
    }

    public static void marioProgram() {

        String marioInfo = "Press 1 for menu card \nPress 2 for orders list \nPress 3 to go back \n";
        System.out.println("Welcome Mario");
        do {
            System.out.println("\n" + marioInfo + "\n");
            int marioChoice = getIntegerInput();
            if (marioChoice == 1) {
                printPizzaMenu();
            } else if (marioChoice == 2) {
                printAllOrders();
            } else if (marioChoice == 3) {
                validation = true;
            } else {
                System.out.println("\nPlease press 1, 2 or 3 \n");
            }
        } while (!validation);

    }

    public static void alfredoProgram() {
        String alfredoInfo = "Press 1 to add order \nPress 2 to remove order \nPress 3 to go back \n";
        System.out.println("Welcome Alfredo");
        do {
            System.out.println("\n" + alfredoInfo + "\n");
            int alfredoChoice = getIntegerInput();

            if (alfredoChoice == 1) {
                addOrder();
            } else if (alfredoChoice == 2) {
                removeOrder();
            } else if (alfredoChoice == 3) {
                validation = true;
            } else {
                System.out.println("\nPlease press 1, 2 or 3 \n");
            }
        }while (!validation);

    }

    public static void addOrder() {
        boolean loopChecker = true;
        Pizza[] pizzasArray = menu.toArray(new Pizza[menu.size()]);
        printWholePizzaMenu();
        System.out.println("Enter the pizza that have been ordered");
        int choice = getIntegerInput();
        ordersArrayList.add(pizzasArray[choice - 1]);
        System.out.println(ordersArrayList.toString());
        while (loopChecker) {
            System.out.println("Would you like to enter another order? Press 1 for Yes. 2 for No");
            int choice2 = getIntegerInput();
            if (choice2 == 1) {
                System.out.println("Enter the pizza that have been ordered");
                int choice3 = getIntegerInput();
                ordersArrayList.add(pizzasArray[choice3 - 1]);
                System.out.println(ordersArrayList.toString());
            } else if (choice2 == 2) {
                loopChecker = false;
            } else {
                System.out.println("Please enter 1 or 2");
            }
        }
    }
    public static void removeOrder() {
        boolean loopChecker = true;
        System.out.println("Choose which pizza to remove from order list");
        System.out.println(ordersArrayList.toString());
        int choice = getIntegerInput();
        while (choice >= ordersArrayList.size()+1 || choice <= 0) {
            System.out.println("Please enter valid input");
            choice = getIntegerInput();
        }
        System.out.println("Pizza removed :" + ordersArrayList.get(choice-1));
        ordersArrayList.remove(choice - 1);
        while (loopChecker) {
            System.out.println("Would you like to remove another pizza? 1 for Yes. 2 for No");
            int choice2 = getIntegerInput();
            if (choice2 == 1) {
                System.out.println("Choose which pizza to remove from order list");
                System.out.println(ordersArrayList.toString());
                while (choice2 >= ordersArrayList.size()+1 || choice2 <= 0) {
                    System.out.println("Please enter try again");
                    choice2 = getIntegerInput();
                }
                ordersArrayList.remove(choice - 1);
            } else if (choice2 == 2) {
                loopChecker = false;
            } else {
                System.out.println("Please enter 1 or 2");
            }
        }
    }

    public static int getIntegerInput() {
        Scanner scanner = new Scanner(System.in);
        return scanner.nextInt();
    }

    static ArrayList<Pizza> ordersArrayList = new ArrayList<Pizza>();
    static ArrayList<Orders> bookingKeepingArrayList = new ArrayList<>();


    public static void printAllOrders() {
        Pizza[] ordersInArrayList = ordersArrayList.toArray(new Pizza[ordersArrayList.size()]);
        for (Pizza orders : ordersInArrayList) {
            System.out.println(orders.toString());
        }
    }

    //Primarily Jens's code - Code responsible for creating Pizza menu and pulling pizzas from it
    static ArrayList<Pizza> menu = new ArrayList<>();
    private static void fillMenuArrayListFromTxtFile(){
        try {
            File pizzaMenuTxt = new File("src\\PIZZA.txt");
            Scanner scanner = new Scanner(pizzaMenuTxt);
            while (scanner.hasNextLine()) {
                String menuRaw = scanner.nextLine();
                //Splits each line of pizzas by '.' to an array
                String[] nextPizzaFromRawMenu = menuRaw.split("\\.");
                //Parses strings pizzaID and pizzaPrice to integers
                int pizzaID = Integer.parseInt(nextPizzaFromRawMenu[0]);
                int pizzaPrice = Integer.parseInt(nextPizzaFromRawMenu[3]);
                //Creates pizza from each line of pizza values
                Pizza nextPizza = new Pizza(pizzaID, nextPizzaFromRawMenu[1], nextPizzaFromRawMenu[2], pizzaPrice);
                //Adds pizza to global menu
                menu.add(nextPizza);
            }
            scanner.close();
        } catch (FileNotFoundException e) {
            try {//For MacOS - has different path to file, therefore catches exception and tries for MacOS path
                File pizzaMenu = new File("src/PIZZA.txt");
                Scanner scanner = new Scanner(pizzaMenu);
                while (scanner.hasNextLine()) {
                    String menuRaw = scanner.nextLine();
                    //Splits each line of pizzas into strings by '.' to an array
                    String[] pizza = menuRaw.split("\\.");
                    //Parses strings pizzaID and pizzaPrice to integers
                    int pizzaID = Integer.parseInt(pizza[0]);
                    int pizzaPrice = Integer.parseInt(pizza[3]);
                    //Creates pizza from each line of pizza values
                    Pizza nextPizza = new Pizza(pizzaID, pizza[1], pizza[2], pizzaPrice);
                    menu.add(nextPizza);
                }
                scanner.close();
                //if neither
            } catch (FileNotFoundException f) {
                System.out.println("An error occurred.");
            }
        }
    }
    private static String getPizzaFromMenu(int pizzaID){
        //turns menu to an array which we can use Pizza methods on
        Pizza[] pizzasArray = menu.toArray(new Pizza[menu.size()]);
        return pizzasArray[pizzaID-1].toString();
    }
    private static void printPizzaMenu(){
        //Prints out all Pizzas on the menu, will also be helpful when getting statistics from a day of sales
        Pizza[] pizzasArray = menu.toArray(new Pizza[menu.size()]);
        for (int i = 0; i < pizzasArray.length; i++) {
            System.out.println(pizzasArray[i].toString());
        }
    }


}

