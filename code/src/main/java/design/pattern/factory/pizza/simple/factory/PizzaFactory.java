package design.pattern.factory.pizza.simple.factory;

import design.pattern.factory.pizza.simple.factory.pazza.CheessPizza;
import design.pattern.factory.pizza.simple.factory.pazza.ClamPizza;
import design.pattern.factory.pizza.simple.factory.pazza.PepperoniPizza;
import design.pattern.factory.pizza.simple.factory.pazza.Pizza;

/**
 * @author hwj
 */
public class PizzaFactory {
    public static Pizza createPizza(PizzaType pizzaType){
        if(pizzaType==PizzaType.cheese){
            return new CheessPizza();
        }
        if(pizzaType==PizzaType.pepperoni){
            return new PepperoniPizza();
        }
        if(pizzaType==PizzaType.clam){
            return new ClamPizza();
        }
        return null;
    }
}
