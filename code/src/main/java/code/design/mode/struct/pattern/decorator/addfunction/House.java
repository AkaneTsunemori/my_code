package code.design.mode.struct.pattern.decorator.addfunction;

public class House implements IHouse{
    @Override
    public void live() {
        System.out.println("this can be lived in , basic function");
    }
}
