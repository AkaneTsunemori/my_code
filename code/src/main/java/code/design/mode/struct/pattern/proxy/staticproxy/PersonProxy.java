package code.design.mode.struct.pattern.proxy.staticproxy;

public class PersonProxy implements IPerson {
    IPerson iPerson;

    public PersonProxy(IPerson iPerson) {
        this.iPerson = iPerson;
    }

    @Override
    public void sleep() {
        iPerson.sleep();
    }

    @Override
    public void eat() {
        iPerson.eat();
    }
}
