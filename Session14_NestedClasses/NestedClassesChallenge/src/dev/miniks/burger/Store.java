package dev.miniks.burger;

public class Store {
    public static void main(String[] args) {

        Meal regularMeal = new Meal();
        regularMeal.addToppings("Ketchup", "Mayo", "Bacon", "Cheddar");
        System.out.println(regularMeal);

        System.out.println("_".repeat(40));

        Meal USRegularMeal = new Meal(0.8);
        USRegularMeal.addToppings("Ketchup", "Mayo", "Bacon", "Cheese");
        System.out.println(USRegularMeal);
    }
}
