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

    public Pizza(int pizzaID, String pizzaName, String pizzaToppings, int pizzaPrice) {
        this.pizzaID = pizzaID;
        this.pizzaName = pizzaName;
        this.pizzaToppings = pizzaToppings;
        this.pizzaPrice = pizzaPrice;


    }
}

