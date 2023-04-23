package code.design.mode.struct.pattern.decorator.enhance;

public class RingDecorator implements IBeauty{
    IBeauty iBeauty;

    public IBeauty getiBeauty() {
        return iBeauty;
    }

    public void setiBeauty(IBeauty iBeauty) {
        this.iBeauty = iBeauty;
    }

    public RingDecorator(IBeauty iBeauty) {
        this.iBeauty = iBeauty;
    }

    @Override
    public int getBeautyValue() {
        return iBeauty.getBeautyValue()+30;
    }
}
