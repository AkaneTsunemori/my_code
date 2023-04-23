package code.design.mode.action.pattern.chainOfResponsibility.forthVersion;

import code.design.mode.action.pattern.chainOfResponsibility.Bug;

public class NormalProgrammer extends Programmer{
    @Override
    void handle(Bug bug) {
        if (bug.getValue() > 20 && bug.getValue() <= 50) {
            solve(bug);
        }else if(getNext()!=null){
            getNext().handle(bug);
        }
    }
    void solve(Bug bug){
        System.out.println( "Normal " + " programmer solved bug with value of  " + bug.getValue() );

    }

}
