package code.design.mode.action.pattern.chainOfResponsibility.forthVersion;

import code.design.mode.action.pattern.chainOfResponsibility.Bug;

public abstract class Programmer {
    private Programmer next;

    public Programmer getNext() {
        return next;
    }

    public void setNext(Programmer next) {
        this.next = next;
    }

    abstract void handle(Bug bug);
}
