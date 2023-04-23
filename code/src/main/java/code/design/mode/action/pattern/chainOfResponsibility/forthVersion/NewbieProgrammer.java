package code.design.mode.action.pattern.chainOfResponsibility.forthVersion;

import code.design.mode.action.pattern.chainOfResponsibility.Bug;


public class NewbieProgrammer extends Programmer{

    @Override
    void handle(Bug bug) {
        if (bug.getValue() > 0 && bug.getValue() <= 20) {
            solve(bug);
        }else if(getNext()!=null){
            getNext().handle(bug);
        }
    }
    private void solve(Bug bug){
        System.out.println( "Newbie" + " programmer solved bug with value of  " + bug.getValue() );

    }

}
