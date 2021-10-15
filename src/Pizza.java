public class Pizza {
    private int pizzaID;
    private String pizzaName;
    private String pizzaToppings;
    private int pizzaPrice;

    @Override
    public String toString() {
        return pizzaID+". "+
                pizzaName+" - "+
                pizzaToppings+
                "\t\tDKK " + pizzaPrice + ",-";
    }
    public String modifiedToString() {
        return pizzaID+". "+
                pizzaName+" - "+
                pizzaToppings;
    }

    public Pizza(int pizzaID, String pizzaName, String pizzaToppings, int pizzaPrice) {
        this.pizzaID = pizzaID;
        this.pizzaName = pizzaName;
        this.pizzaToppings = pizzaToppings;
        this.pizzaPrice = pizzaPrice;


    }

    public int getPizzaPrice() {
        return pizzaPrice;
    }

    public int getPizzaID() {
        return pizzaID;
    }

    public String getPizzaName() {
        return pizzaName;
    }
}

