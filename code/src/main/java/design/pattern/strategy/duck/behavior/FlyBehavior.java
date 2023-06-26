package design.pattern.strategy.duck.behavior;

import design.pattern.exception.MethodNotSupportException;

/**
 * @author hwj
 */
public interface FlyBehavior {

    /**
     * define fly strategy
     * @MethodNotSupportException may cause exception
     */
    void fly() throws MethodNotSupportException;
}
