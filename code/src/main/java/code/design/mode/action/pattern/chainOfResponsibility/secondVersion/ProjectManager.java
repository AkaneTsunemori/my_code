package code.design.mode.action.pattern.chainOfResponsibility.secondVersion;

import code.design.mode.action.pattern.chainOfResponsibility.Bug;
import code.design.mode.action.pattern.chainOfResponsibility.Programmer;
import code.design.mode.action.pattern.chainOfResponsibility.Type;

public class ProjectManager {
    public static void main(String[] args) {
        ProjectManager projectManager = new ProjectManager();
        projectManager.assignBug(new Bug(40));
    }
    Programmer newbie = new Programmer(Type.NEWBIE);
    Programmer normal = new Programmer(Type.NORMAL);
    Programmer good = new Programmer(Type.GOOD);

    public void assignBug(Bug bug) {
        if (bug.getValue() > 0 && bug.getValue() <= 20) {
            System.out.println("项目经理将这个简单的 bug 分配给了菜鸟程序员");
            newbie.solve(bug);
        } else if (bug.getValue() > 20 && bug.getValue() <= 50) {
            System.out.println("项目经理将这个中等的 bug 分配给了普通程序员");
            normal.solve(bug);
        } else if (bug.getValue() > 50 && bug.getValue() <= 100) {
            System.out.println("项目经理将这个困难的 bug 分配给了优秀程序员");
            good.solve(bug);
        }
    }
}
