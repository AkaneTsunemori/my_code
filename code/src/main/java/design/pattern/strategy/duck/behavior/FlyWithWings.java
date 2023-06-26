package design.pattern.strategy.duck.behavior;

/**
 * @author hwj
 */
public class FlyWithWings implements FlyBehavior{
    @Override
    public void fly() {
        System.out.println("fly with wings");
    }
}
