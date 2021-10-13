import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class MenuOverview {
    public static void main(String[] args) {
        showPizzaMenu();


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
