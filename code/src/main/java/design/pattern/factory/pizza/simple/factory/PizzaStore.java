package design.pattern.factory.pizza.simple.factory;

import design.pattern.factory.pizza.simple.factory.pazza.Pizza;

/**
 * @author hwj
 */
public class PizzaStore {
    public Pizza createPizza(PizzaType pizzaType){
        return PizzaFactory.createPizza(pizzaType);
    }
}
