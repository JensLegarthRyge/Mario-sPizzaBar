import java.util.Arrays;
import java.util.Date;
public class Orders {
    private Pizza[] pizzasOrdered;


    public Orders(Pizza[] pizzasOrdered) {
        this.pizzasOrdered = pizzasOrdered;

    }

    @Override
    public String toString() {
        return "Orders{" +
                "pizzasOrdered=" + Arrays.toString(pizzasOrdered) +
                '}';
    }
}
