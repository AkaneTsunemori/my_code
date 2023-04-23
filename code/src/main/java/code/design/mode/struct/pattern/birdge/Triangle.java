package code.design.mode.struct.pattern.birdge;

import code.design.mode.struct.pattern.birdge.color.IColor;

public class Triangle implements IShape{
    IColor iColor;

    public IColor getColor() {
        return iColor;
    }

    public void setColor(IColor color) {
        this.iColor = color;
    }

    @Override
    public void draw() {
        System.out.println("draw "+iColor.getColor()+" Triangle");
    }
}
