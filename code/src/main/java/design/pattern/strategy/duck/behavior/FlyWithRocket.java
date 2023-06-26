package design.pattern.strategy.duck.behavior;

/**
 * @author hwj
 */
public class FlyWithRocket implements FlyBehavior{
    @Override
    public void fly() {
        System.out.println("fly with rocket");
    }
}
