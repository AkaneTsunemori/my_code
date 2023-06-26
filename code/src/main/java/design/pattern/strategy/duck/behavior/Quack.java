package design.pattern.strategy.duck.behavior;

/**
 * @author hwj
 */
public class Quack implements QuackBehavior{
    @Override
    public void quack() {
        System.out.println("quack");
    }
}
