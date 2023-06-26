package design.pattern.strategy.duck;

import design.pattern.exception.MethodNotSupportException;
import design.pattern.strategy.duck.behavior.FlyBehavior;
import design.pattern.strategy.duck.behavior.FlyWithWings;
import design.pattern.strategy.duck.behavior.MuteQuack;
import design.pattern.strategy.duck.behavior.QuackBehavior;

/**
 * @author hwj
 */
public class Duck {
    protected String apperence;
    public QuackBehavior quackBehavior;
    public FlyBehavior flyBehavior;

    public void swim() {
    }

    public void display() {
    }

    public void setApperence(String apperence) {
        this.apperence = apperence;
    }

    public void preformQuack() {
        quackBehavior.quack();
    }

    public void preformFly() throws MethodNotSupportException {
        flyBehavior.fly();
    }

    public static void main(String[] args) throws MethodNotSupportException {
        Duck duck = new MallardDuck(new FlyWithWings(),new MuteQuack());
        duck.preformFly();
        duck.preformQuack();
        duck.display();
    }
}
