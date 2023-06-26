package design.pattern.factory.pizza.simple.factory.pazza;

import java.util.List;

public class Pizza {
    private String name;
    //生面团
    private String dough;
    private String sauce;
    private List<String> toppings;

    public String getName() {
        return name;
    }

    public void prepare() {
    }

    public void bake() {
    }

    public void cut() {
    }

    public void box() {
    }
}
