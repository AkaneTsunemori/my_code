package code.design.mode.struct.pattern.decorator.enhance;

/***
 * 增强一个类原有的功能
 * 为一个类添加新的功能
 * 装饰模式也不会改变原有的类
 *
 * 实现方式: ,装饰者和被装饰者继承同一个接口, 装饰者将接口作为自己的成员变量, 通过set或者构造器传入
 *
 *
 * 就增加对象功能 来说，装饰模式比生成子类实现更为灵活
 */
public class Test {
    public static void main(String[] args) {
        Me me = new Me();
        RingDecorator ringDecorator = new RingDecorator(me);
        System.out.println(ringDecorator.getBeautyValue());

    }
}
