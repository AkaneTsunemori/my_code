package code.design.mode.struct.pattern.proxy.staticproxy;

public class Test {
    /**
     * 代理模式：给某一个对象提供一个代理，并由代理对象控制对原对象的引用。
     * @param args
     */
    public static void main(String[] args) {
        Persion  p =new Persion();
        PersonProxy pp = new PersonProxy(p);
        pp.sleep();
        pp.eat();
    }
}
