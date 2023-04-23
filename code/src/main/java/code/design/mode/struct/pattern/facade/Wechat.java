package code.design.mode.struct.pattern.facade;

public class Wechat implements Action{
    @Override
    public void open() {
        System.out.println("open wechat");
    }

    @Override
    public void close() {
        System.out.println("close wechat");

    }
}
