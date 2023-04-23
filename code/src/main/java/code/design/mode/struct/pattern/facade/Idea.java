package code.design.mode.struct.pattern.facade;

public class Idea  implements Action{
    @Override
    public void open() {
        System.out.println("open idea");
    }

    @Override
    public void close() {
        System.out.println("close idea");

    }
}
