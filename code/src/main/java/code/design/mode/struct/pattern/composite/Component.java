package code.design.mode.struct.pattern.composite;

import java.util.List;

public abstract class Component {
    public String position;
    public String job;

    Component(String position, String job) {
        this.position = position;
        this.job = job;
    }

    public void work() {
        System.out.println("i am " + position + " my job is " + job);
    }

    abstract void addComponent(Component component);

    abstract void removeComponent(Component component);

    abstract public void check();


}
