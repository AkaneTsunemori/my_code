package design.pattern.strategy.duck.behavior;

/**
 * @author hwj
 */
public class MuteQuack implements QuackBehavior{
    @Override
    public void quack() {
        System.out.println("mute");
    }
}
