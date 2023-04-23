package code.design.mode.observer;

import java.util.EventObject;

public class MethodExecutionEvent extends EventObject {
     MethodExecutionEvent(Object source){
         super(source);
     }
}
