import java.util.Arrays;
import java.util.Date;
public class Orders {
    private Pizza pizzaOrdered;
    private Date finishedByTime;


    public Orders(Pizza pizzaOrdered, Date finishedByTime) {
        this.pizzaOrdered = pizzaOrdered;
        this.finishedByTime = finishedByTime;

    }

    @Override
    public String toString() {
        return "Orders{" +
                "pizzasOrdered=" + pizzaOrdered +
                '}';
    }
}
