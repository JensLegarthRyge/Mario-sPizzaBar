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
        int minutes = finishedByTime.getMinutes();
        StringBuilder stringbuilder = new StringBuilder();
        if (minutes<10){
            stringbuilder.append(0).append(minutes);
        } else
            stringbuilder.append(minutes);

        return finishedByTime.getHours()+":"+stringbuilder+" - "+pizzaOrdered;
    }
}
