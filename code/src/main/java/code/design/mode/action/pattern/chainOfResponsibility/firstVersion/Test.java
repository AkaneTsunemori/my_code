package code.design.mode.action.pattern.chainOfResponsibility.firstVersion;

import code.design.mode.action.pattern.chainOfResponsibility.Bug;
import code.design.mode.action.pattern.chainOfResponsibility.Programmer;
import code.design.mode.action.pattern.chainOfResponsibility.Type;

public class Test {
    public static void main(String[] args) {
        test();
    }
    public static void test() {
        Programmer newbie = new Programmer(Type.NEWBIE);
        Programmer normal = new Programmer(Type.NORMAL);
        Programmer good = new Programmer(Type.GOOD);

        Bug easy = new Bug(20);
        Bug middle = new Bug(50);
        Bug hard = new Bug(100);

        // 依次尝试解决 bug
        handleBug(newbie, easy);
        handleBug(normal, easy);
        handleBug(good, easy);

        handleBug(newbie, middle);
        handleBug(normal, middle);
        handleBug(good, middle);

        handleBug(newbie, hard);
        handleBug(normal, hard);
        handleBug(good, hard);
    }

    public static void handleBug(Programmer programmer, Bug bug) {
        if (programmer.getType().equals(Type.NEWBIE) && bug.getValue() > 0 && bug.getValue() <= 20) {
            programmer.solve(bug);
        } else if (programmer.getType().equals(Type.NORMAL) && bug.getValue() > 20 && bug.getValue() <= 50) {
            programmer.solve(bug);
        } else if (programmer.getType().equals(Type.GOOD) && bug.getValue() > 50 && bug.getValue() <= 100) {
            programmer.solve(bug);
        }
    }
}
