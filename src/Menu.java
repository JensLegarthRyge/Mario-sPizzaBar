import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Menu {
    public static void main(String[] args) throws FileNotFoundException {
    //https://www.w3schools.com/java/showjava.asp?filename=demo_files_read source
        try {
            File pizzaMenu = new File("src\\PIZZA.txt");
            Scanner scanner = new Scanner(pizzaMenu);
            ArrayList<Pizza> menu = new ArrayList<>();
            while (scanner.hasNextLine()) {
                String menuRaw = scanner.nextLine();
                String[] pizza = menuRaw.split("\\.");
                int pizzaID = Integer.parseInt(pizza[0]);
                int pizzaPrice = Integer.parseInt(pizza[3]);
                Pizza nextPizza = new Pizza(pizzaID,pizza[1],pizza[2],pizzaPrice);
                menu.add(nextPizza);
            }
            Pizza[] pizzaArrayListToArray = menu.toArray(new Pizza[menu.size()]);
            for (int i = 0; i < pizzaArrayListToArray.length; i++) {
                System.out.println(pizzaArrayListToArray[i].toString());
            }
            scanner.close();
        } catch (FileNotFoundException e) {
            try {
                File pizzaMenu = new File("src/PIZZA.txt");
                Scanner scanner = new Scanner(pizzaMenu);
                ArrayList<Pizza> menu = new ArrayList<>();
                while (scanner.hasNextLine()) {
                    String menuRaw = scanner.nextLine();
                    String[] pizza = menuRaw.split("\\.");
                    int pizzaID = Integer.parseInt(pizza[0]);
                    int pizzaPrice = Integer.parseInt(pizza[3]);
                    Pizza nextPizza = new Pizza(pizzaID,pizza[1],pizza[2],pizzaPrice);
                    menu.add(nextPizza);
                }
                Pizza[] pizzaArrayListToArray = menu.toArray(new Pizza[menu.size()]);
                for (int i = 0; i < pizzaArrayListToArray.length; i++) {
                    System.out.println(pizzaArrayListToArray[i].toString());
                }
                scanner.close();
            } catch (FileNotFoundException f) {
                System.out.println("An error occurred.");
            }
        }
    }
}