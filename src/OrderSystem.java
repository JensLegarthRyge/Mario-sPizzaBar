import java.util.ArrayList;
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

}

