package code.design.mode.action.pattern.chainOfResponsibility.thridVersion;

import code.design.mode.action.pattern.chainOfResponsibility.Bug;
import code.design.mode.action.pattern.chainOfResponsibility.Programmer;
import code.design.mode.action.pattern.chainOfResponsibility.Type;

public class Client3 {

    public static void main(String[] args) {

    }
    public void test() throws Exception {
        Programmer newbie = new Programmer(Type.NEWBIE);
        Programmer normal = new Programmer(Type.NORMAL);
        Programmer good = new Programmer(Type.GOOD);

        Bug easy = new Bug(20);
        Bug middle = new Bug(50);
        Bug hard = new Bug(100);

        // 链式传递责任
        if (!handleBug(newbie, easy)) {
            if (!handleBug(normal, easy)) {
                if (!handleBug(good, easy)) {
                    throw new Exception("Kill the fake good programmer!");
                }
            }
        }

        if (!handleBug(newbie, middle)) {
            if (!handleBug(normal, middle)) {
                if (!handleBug(good, middle)) {
                    throw new Exception("Kill the fake good programmer!");
                }
            }
        }

        if (!handleBug(newbie, hard)) {
            if (!handleBug(normal, hard)) {
                if (!handleBug(good, hard)) {
                    throw new Exception("Kill the fake good programmer!");
                }
            }
        }
    }

    public boolean handleBug(Programmer programmer, Bug bug) {
        if (programmer.getType().equals(Type.NEWBIE) && bug.getValue() > 0 && bug.getValue() <= 20) {
            programmer.solve(bug);
            return true;
        } else if (programmer.getType().equals(Type.NORMAL) && bug.getValue() > 20 && bug.getValue() <= 50) {
            programmer.solve(bug);
            return true;
        } else if (programmer.getType().equals(Type.GOOD) && bug.getValue() > 50 && bug.getValue() <= 100) {
            programmer.solve(bug);
            return true;
        }
        return false;
    }
}
