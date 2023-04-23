package code.design.mode.action.pattern.chainOfResponsibility;

public class Programmer {
    Type type;

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public Programmer(Type type) {
        this.type = type;
    }

    public void solve(Bug bug){
        System.out.println( type + " programmer solved bug with value of  " + bug.getValue() );
    }
}
