package code.design.mode.struct.pattern.facade;

public class Browser implements Action{

    @Override
    public void open() {
        System.out.println("open browser");
    }

    @Override
    public void close() {
        System.out.println("close browser");

    }
}
