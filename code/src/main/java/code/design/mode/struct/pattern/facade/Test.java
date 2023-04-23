package code.design.mode.struct.pattern.facade;

/**
 * 外观模式：外部与一个子系统的通信必须通过一个统一的外观对象进行，
 * 为子系统中的一组接口提供一个一致的界面，外观模式定义了一个高层接口，
 * 这个接口使得这一子系统更加容易使用。外观模式又称为门面模式。
 *
 *
 * 外观模式非常常用，（当然了！写代码哪有不封装的！）尤其是在第三方库的设计中，我们应该提供尽量简洁的接口供别人调用。
 * 另外，在 MVC 架构中，C 层（Controller）就可以看作是外观类，
 * Model 和 View 层通过 Controller 交互，减少了耦合。
 */
public class Test {
    public static void main(String[] args) {
        Browser b = new Browser();
        Idea i = new Idea();
        Wechat w = new Wechat();
        Facade f = new Facade(b,i,w);
        f.open();
        f.close();
    }
}
