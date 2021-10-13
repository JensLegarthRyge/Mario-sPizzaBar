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
        boolean validation1 = true;
        boolean validation2 = true;
        boolean validation3 = true;
        String userInfo = "Press 1 for Marios user \nPress 2 for Alfredo's user \nPress 3 to exit program \n";
        String alfredoInfo = "Press 1 to add order \nPress 2 to remove order \nPress 3 to go back \n";
        String marioInfo = "Press 1 for menu card \nPress 2 for orders list \nPress 3 to go back \n";

        System.out.println(userInfo);

        int marioOrAlfredo = getIntegerInput();
        /*
        while (marioOrAlfredo !=3) {
            outofloop:
            if (marioOrAlfredo == 1) {
                System.out.println(marioInfo);
                int choice = getIntegerInput();
                while (choice !=3) {
                    if (choice == 1) {
                        showPizzaMenu();
                    } else if (choice == 2) {
                        printAllOrders();
                    } else {
                        System.out.println(marioInfo);
                        break outofloop;
                    }
                }
            } else if (marioOrAlfredo == 2) {
                System.out.println(alfredoInfo);
                int choice = getIntegerInput();
                while (choice != 3) {

                    if (choice == 1) {
                        addOrder();

                    } else if (choice == 2) {
                        removeOrder();

                    } else {
                        System.out.println("Closing program");
                        break outofloop;
                    }
                    System.out.println(alfredoInfo);
                    choice = getIntegerInput();
                }
            }
        }

         */

        do {
            if (marioOrAlfredo == 1) {
                System.out.println(marioInfo);
                int choice = getIntegerInput();
                do {
                    if (choice == 1) {
                        printWholePizzaMenu();
                        validation2 = false;
                    } else if (choice == 2) {
                        printAllOrders();
                        validation2 = false;
                    } else {
                        System.out.println(userInfo);
                        validation2 = true;

                    }
                } while (!validation2);
                validation1 = false;

            } else if (marioOrAlfredo == 2) {
                System.out.println(alfredoInfo);
                int choice = getIntegerInput();
                do {

                    if (choice == 1) {
                        addOrder();
                        validation3 = false;

                    } else if (choice == 2) {
                        removeOrder();
                        validation3 = false;

                    } else {
                        System.out.println(userInfo);
                        validation3 = true;
                    }
                    choice = getIntegerInput();
                } while (!validation3);
                validation1 = false;

            } else {
                validation1 = false;
            }

        } while (!validation1);
    }

    public static int getIntegerInput() {
        Scanner scanner = new Scanner(System.in);
        return scanner.nextInt();
    }

    static ArrayList<Orders> ordersArrayList = new ArrayList<>();
    public static void addOrder() {
        Pizza hawaii = new Pizza(1, "Hawaii", "onion", 59);
        Pizza pepperoni = new Pizza(2, "Pepperoni", "Cheese", 78);
        Pizza[] newOrder = {hawaii, pepperoni};
        Orders nyeOrdre = new Orders(newOrder);
        ordersArrayList.add(nyeOrdre);

        Pizza trippleCheese = new Pizza(3, "Tripple Cheese ", "Cheese", 100);
        Pizza[] jensOrder = {trippleCheese};
        Orders nyereOrdre = new Orders(jensOrder);
        ordersArrayList.add(nyereOrdre);


        printAllOrders();


    }
    public static void removeOrder() {
        printAllOrders();
        System.out.println("Choose which pizza to remove from order list");
        int userInput = scanner.nextInt();
        ordersArrayList.remove(userInput - 1);

    }
    public static void printAllOrders() {
        Orders[] ordersInArrayList = ordersArrayList.toArray(new Orders[ordersArrayList.size()]);
        for (Orders orders : ordersInArrayList) {
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
    private static void printPizzaFromMenu(int pizzaID){
        //turns menu to an array which we can use Pizza methods on
        Pizza[] pizzasArray = menu.toArray(new Pizza[menu.size()]);
        System.out.println(pizzasArray[pizzaID-1].toString());
    }
    private static void printWholePizzaMenu(){
        //Prints out all Pizzas on the menu, will also be helpful when getting statistics from a day of sales
        Pizza[] pizzasArray = menu.toArray(new Pizza[menu.size()]);
        for (int i = 0; i < pizzasArray.length; i++) {
            System.out.println(pizzasArray[i].toString());
        }
    }
}

