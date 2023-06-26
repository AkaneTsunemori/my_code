package design.pattern.strategy.duck.behavior;

import design.pattern.exception.MethodNotSupportException;

/**
 * @author hwj
 */
public class FlyNoWay implements FlyBehavior{
    @Override
    public void fly() throws MethodNotSupportException {
        throw new MethodNotSupportException();
    }
}
