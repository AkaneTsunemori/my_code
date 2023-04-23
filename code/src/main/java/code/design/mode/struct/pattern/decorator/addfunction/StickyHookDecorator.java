package code.design.mode.struct.pattern.decorator.addfunction;

public class StickyHookDecorator implements IStickyHookHouse{

    private IHouse iHouse;

    public StickyHookDecorator(IHouse iHouse) {
        this.iHouse = iHouse;
    }

    @Override
    public void live() {
        iHouse.live();
    }

    @Override
    public void hangThings() {
        System.out.println("there is StickyHook, hanging things function added");
    }
}
