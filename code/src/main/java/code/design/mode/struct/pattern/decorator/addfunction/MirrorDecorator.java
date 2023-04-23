package code.design.mode.struct.pattern.decorator.addfunction;

public class MirrorDecorator implements IMirrorHouse{
    private IHouse iHouse;

    public MirrorDecorator(IHouse iHouse) {
        this.iHouse = iHouse;
    }

    @Override
    public void live() {
        iHouse.live();
    }

    @Override
    public void lookMirror() {
        System.out.println("there is mirro, looking mirror function added.");
    }
}
