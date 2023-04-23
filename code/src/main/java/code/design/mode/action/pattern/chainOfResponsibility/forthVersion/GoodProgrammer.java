package code.design.mode.action.pattern.chainOfResponsibility.forthVersion;

import code.design.mode.action.pattern.chainOfResponsibility.Bug;

public class GoodProgrammer extends Programmer {
    @Override
    void handle(Bug bug) {
        if (bug.getValue() > 80 && bug.getValue() <= 100) {
            solve(bug);
        }else if(getNext()!=null){
            getNext().handle(bug);
        }
    }
    private void solve(Bug bug){
        System.out.println( "Good " + " programmer solved bug with value of  " + bug.getValue() );

    }
}
