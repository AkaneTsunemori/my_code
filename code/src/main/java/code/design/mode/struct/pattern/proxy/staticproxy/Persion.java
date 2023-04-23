package code.design.mode.struct.pattern.proxy.staticproxy;

public class Persion implements  IPerson{
    @Override
    public void sleep() {
        System.out.println("person sleep");
    }

    @Override
    public void eat() {
        System.out.println("person eat");
    }
}
