import java.util.Date;
public class Orders implements Comparable<Orders>{
    private Date finishedByTime;
    private Pizza pizzaOrdered;



    public Orders(Pizza pizzaOrdered, Date finishedByTime) {
        this.pizzaOrdered = pizzaOrdered;
        this.finishedByTime = finishedByTime;

    }

    @Override
    public String toString() {

        int minutes = finishedByTime.getMinutes();
        StringBuilder stringBuilder = new StringBuilder();
        if (minutes<10){
            stringBuilder.append(0).append(minutes);
        } else
            stringBuilder.append(minutes);
        return finishedByTime.getHours()+":"+stringBuilder+" - "+pizzaOrdered;
    }

    @Override
    public int compareTo(Orders o) {
        return finishedByTime.compareTo(o.finishedByTime);
    }
}
