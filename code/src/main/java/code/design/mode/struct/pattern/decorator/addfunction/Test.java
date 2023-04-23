package code.design.mode.struct.pattern.decorator.addfunction;

public class Test {
    public static void main(String[] args) {
        IHouse house = new House();
        house.live();
        IStickyHookHouse stickyHookHouse = new StickyHookDecorator(house);
        stickyHookHouse.live();
        stickyHookHouse.hangThings();


        IMirrorHouse houseWithStickyHookMirror = new MirrorDecorator(stickyHookHouse);
        houseWithStickyHookMirror.live();
/**
 * 那么我们能否让 IMirrorHouse 继承自 IStickyHookHouse，以实现新增两个功能呢？
 * 可以，但那样做的话两个装饰类之间有了依赖关系，那就不是装饰模式了。
 * 装饰类不应该存在依赖关系，而应该在原本的类上进行装饰。这就意味着，半透明装饰模式中，我们无法多次装饰。
 */
//        houseWithStickyHookMirror.hangThings(); // 这里会报错，找不到 hangThings 方法
        houseWithStickyHookMirror.lookMirror();
    }
}
