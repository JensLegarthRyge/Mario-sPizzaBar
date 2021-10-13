import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

public class OrderSystem {
    static Scanner scanner = new Scanner(System.in);
    static ArrayList<Orders> ordersArrayList = new ArrayList<>();

    public static void main(String[] args) {
        String info = "Press 1 to add order \nPress 2 to remove order \nPress 3 to exit \n";
        System.out.println(info);



        int choice = scanner.nextInt();

        while (choice != 3) {
            if (choice == 1) {
                addOrder();

            } else if (choice == 2) {
                removeOrder();

            } else {
                System.out.println("Closing program");
            }
            System.out.println(info);
            choice = scanner.nextInt();
        }

    }

    public static void addOrder() {
        System.out.println("add");


    }
    public static void removeOrder() {
        System.out.println("remove");

    }
    public static void printAllOrders() {
        Orders[] ordersInArrayList = ordersArrayList.toArray(new Orders[ordersArrayList.size()]);
        for (Orders orders : ordersInArrayList) {
            System.out.println(orders.toString());
        }
    }

    public static void showPizzaMenu() {

        try {
            File myObj = new File("src\\pizza.txt");
            Scanner myReader = new Scanner(myObj);
            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
                System.out.println(data);
            }
            myReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

}

