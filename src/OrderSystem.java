import java.time.*;
import java.util.*;
import java.text.*;
import java.io.*;
import java.util.stream.Collectors;
import java.util.stream.*;



public class OrderSystem {
    static boolean validation = true;
    static LocalDate today = LocalDate.now();

    public static void main(String[] args) {
        fillMenuArrayListFromTxtFile();
        chooseUser();
    }

    //Primarily Mads's code - Code responsible for statistics and navigating the whole system
    public static void chooseUser() {
        //A do while loop that navigates through the different programs
        String userInfo = "Press '1' for Mario's Menu\nPress '2' for Alfonso's menu\nPress '3' to exit program";
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
                System.out.println("Exiting program...");
                validation = false;
            } else {
                System.out.println("\nPlease enter '1', '2' or '3'");
                System.out.println(userInfo);

            }
        } while (validation);
    }
    public static void marioProgram() {
        //A menu to navigate through the options in the marioProgram
        String marioInfo = "Press '1' for menu card🧾\nPress '2' for orders list🛒\nPress '3' to see statistics📊\nPress '4' to go back🔙";
        System.out.println("\nWelcome Mario👨🏻‍🍳");
        do {
            System.out.println(marioInfo);
            int marioChoice = getIntegerInput();
            if (marioChoice == 1) {
                printPizzaMenu();
            } else if (marioChoice == 2) {
                printAllOrders();
            } else if (marioChoice == 3) {
                statistics();
            } else if (marioChoice == 4) {
                validation = true;
            } else {
                System.out.println("\nPlease enter '1', '2' or '3'\n");
            }
        } while (!validation);

    }
    public static void alfredoProgram() {
        //A menu to navigate through the options in the alfredoProgram
        String alfredoInfo = "Press '1' to add order🛒\nPress '2' to remove order␡\nPress '3' to go back🔙";
        System.out.println("\nWelcome Alfonso🤵🏾");
        do {
            System.out.println(alfredoInfo);
            int alfredoChoice = getIntegerInput();

            if (alfredoChoice == 1) {
                addOrder();
            } else if (alfredoChoice == 2) {
                removeOrder();
            } else if (alfredoChoice == 3) {
                validation = true;
            } else {
                System.out.println("\nPlease enter '1', '2' or '3' \n");
            }
        }while (!validation);

    }
    public static void statistics() {
        //A method that takes and process the elements from statisticsArrayList
        boolean loopChecker = true;
        //statisticsArrayList is converted back into a Pizza array.
        Pizza[] statisticsOnPizzas = statisticsArrayList.toArray(new Pizza[0]);
        //A double array used for DoubleSummaryStatistics
        double dataset[] = new double[statisticsOnPizzas.length];
        //This for loop takes the prices of each pizza in the statisticsOnPizzas array and puts it in the double array
        for (int i = 0; i < statisticsOnPizzas.length; i++) {
            dataset[i] = statisticsOnPizzas[i].getPizzaPrice();
        }
        //DoubleSummaryStatistics is an object made for collecting statistics such as count, sum, average, min, max etc.
        //Here DoubleSummaryStatistics is used with Stream to filter the objects of dataset
        DoubleSummaryStatistics stats = DoubleStream.of(dataset).summaryStatistics();
        double amountOfPizzasSold = stats.getCount();
        double sumOfPizzaRevenue = stats.getSum();

        //Do while loop that gives the options to either print todays sales (amountOfPizzasSold) and the amount of revenue
        //or print the most popular pizzas in a visualRepresentation.
        do {
            System.out.println("\nTo see revenue from today's sales, press '1'💰\n" +
                    "To see the most popular pizza today, press '2'🍕\nTo go back, press '3'🔙");
            int choice = getIntegerInput();
            if (choice == 1) {
                System.out.println("\nThis is the amount of pizzas that were sold today: " + amountOfPizzasSold);
                System.out.println("\nThis is the revenue from the pizzas sold: " + sumOfPizzaRevenue);
            } else if (choice == 2) {
                mostPopularPizza(statisticsOnPizzas);
            } else if (choice == 3) {
                loopChecker = false;
            }

        }while (loopChecker);

    }
    public static void mostPopularPizza(Pizza[] statisticsOnPizzas) {
        double pizzaList[] = new double[statisticsOnPizzas.length];
        for (int i = 0; i < statisticsOnPizzas.length; i++) {
            pizzaList[i] = statisticsOnPizzas[i].getPizzaID();
        }

        //https://dev.to/habeebcycle/performing-basic-stats-in-java-8-5dfl
        //A code that makes use of java.util.Stream. With Stream you can take input and process it, which is what the
        //following code is doing.
        //Here we make use of Map to make a sort of a histogram that can take the numbers of pizzaList[] and count how
        //many times each pizza is sold.
        Map<Double,Long> visualRepresentation = DoubleStream.of(pizzaList).boxed().collect(Collectors.groupingBy(e -> e,Collectors.counting()));

        for (Double data : visualRepresentation.keySet()) {
            int pizzaID = data.intValue();
            int amountSold = visualRepresentation.get(data).intValue();
            System.out.println(("You sold " + amountSold + " number "+getPizzaFromMenuWithoutPrice(pizzaID)+"\t" + getStars(visualRepresentation.get(data))));

        }
        System.out.println();
    }
    public static String getStars (long number) {
        //A method that creates a star that are printed to visualRepresentation in the mostPopularPizza method.
        //The star is printed for the amount of times a certain pizza is ordered.
        String output = "";
        for (int i = 1; i <= number; i++) {
            output += "⭐️";
        }
        return output;
    }

    //Primarily Christoffer's  code - Code responsible for Alfredos order system
    //We use to arraylists to store orders and statistics
    static ArrayList<Orders> ordersArrayList = new ArrayList<>();
    static ArrayList<Pizza> statisticsArrayList = new ArrayList<>();
    //Method to add orders
    public static void addOrder() {
        boolean loopChecker = true;
        printPizzaMenu();
        System.out.println("\nTo enter the pizza that has been ordered, press '1'🍕\nTo go back press '2'🔙");
        do {
            int choice = getIntegerInput();
            if (choice == 1) {
                System.out.println("Please provide pizza number🔢:");
                int chosenPizzaID = getIntegerInput();
                while (chosenPizzaID > 38 || chosenPizzaID <= 0)  {
                    System.out.println("Please provide a valid pizza number🔢:");
                    chosenPizzaID = getIntegerInput();
                }
                System.out.println(getPizzaFromMenu(chosenPizzaID));
                //Add the chosen pizza from the pizza menu to the statistics arraylist
                statisticsArrayList.add(getPizzaFromMenu(chosenPizzaID));
                System.out.println("When should it be finished by?⏲");
                Date pizzaFinishTime = getFinishedByTime();
                //Instantiate a new order based on what pizza alfredo chose from the menu and when it is to be finished by
                Orders nextOrder = new Orders(getPizzaFromMenu(chosenPizzaID), pizzaFinishTime);
                ordersArrayList.add(nextOrder);
                //Sorts order arraylist by time
                Collections.sort(ordersArrayList);
                System.out.println("Press '1' to add another order\nPress '2' to go back🔙");
            } else if (choice == 2) {
                //exits loop
                loopChecker = false;
            }else {
                System.out.println("Please enter '1' or '2'");
            }
        }while (loopChecker);
    }
    public static void removeOrder() {
        //Method to remove orders
        boolean loopChecker = true;
        System.out.println();
        System.out.println("Press '1' to remove pizza🍕\nPress '2' to go back🔙");
        do {
            int choice = getIntegerInput();
            if (choice == 1) {
                System.out.println("Choose which pizza to remove from order list🧾");
                for(int i = 0; i < ordersArrayList.size(); i++) {
                    System.out.println(i+1 + " " + ordersArrayList.get(i).toString());
                }
                int removePizza = getIntegerInput();
                while (removePizza >= ordersArrayList.size() + 1 || removePizza <= 0) {
                    System.out.println("Please enter valid input");
                    removePizza = getIntegerInput();
                }
                System.out.println("Pizza removed ✓: " + ordersArrayList.get(removePizza - 1));
                //Removes pizza from the orders Arraylist
                ordersArrayList.remove(removePizza - 1);
            } else if (choice == 2) {
                loopChecker = false;
            } else {
                System.out.println("Please enter '1' or '2'");
            }
        } while (!loopChecker);
    }
    public static void printAllOrders() {
        //Tweaked sorting by Mads and Jens
        //Creates copy of ordersArrayList
        ArrayList<Orders> sortedOrdersArrayList = new ArrayList<Orders>(ordersArrayList);
        //Sorts the copy by Date
        Collections.sort(sortedOrdersArrayList);
        //Converts arraylist to Orders array
        Orders[] ordersInArray = sortedOrdersArrayList.toArray(new Orders[sortedOrdersArrayList.size()]);
        int listing = 1;
        //Christoffer - prints the elements in the orders array
        for (Orders orders : ordersInArray) {
            System.out.println(listing + ". " + orders.toString());
            listing++;
        }
        System.out.println();
    }
    public static int getIntegerInput() {
        Scanner scanner = new Scanner(System.in);
        return scanner.nextInt();
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
    private static Pizza getPizzaFromMenu(int pizzaID){
        //turns menu to an array which we can use Pizza methods on
        Pizza[] pizzasArray = menu.toArray(new Pizza[menu.size()]);
        return pizzasArray[pizzaID-1];
    }
    private static void printPizzaMenu(){
        //Prints out all Pizzas on the menu, will also be helpful when getting statistics from a day of sales
        Pizza[] pizzasArray = menu.toArray(new Pizza[menu.size()]);
        for (int i = 0; i < pizzasArray.length; i++) {
            System.out.println(pizzasArray[i].toString());
        }
        System.out.println();
    }
    static String getStringInput(){
        Scanner scanner = new Scanner(System.in);
        return scanner.nextLine();
    }
    public static Date getFinishedByTime(){
        System.out.println("hh:mm");
        String dateFromUser = getStringInput();

        SimpleDateFormat format = new SimpleDateFormat("HH:mm yyyy-MM-dd");
        Date formattedDate = null;
        try{
            formattedDate = format.parse(dateFromUser+" "+today);
        } catch (ParseException e){
            e.printStackTrace();
        }
        return formattedDate;

    }
    private static String getPizzaFromMenuWithoutPrice(int pizzaID){
        Pizza[] pizzasArray = menu.toArray(new Pizza[menu.size()]);
        return pizzasArray[pizzaID-1].modifiedToString();
    }
}

