package design.pattern.strategy.duck;

import design.pattern.strategy.duck.behavior.FlyBehavior;
import design.pattern.strategy.duck.behavior.FlyWithWings;
import design.pattern.strategy.duck.behavior.QuackBehavior;

public class MallardDuck extends Duck {

    public MallardDuck(FlyBehavior flyBehavior, QuackBehavior quackBehavior) {
        this.flyBehavior = flyBehavior;
        this.quackBehavior = quackBehavior ;
    }

    @Override
    public void display() {
        System.out.println("this is a " + apperence + " duck");
    }
}
