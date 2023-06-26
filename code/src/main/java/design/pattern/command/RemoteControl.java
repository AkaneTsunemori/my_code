package design.pattern.command;

public class RemoteControl {
    private Command slotLightOn;
    private Command slotLightOff;

    public void setSlotLightOn(Command slotLightOn) {
        this.slotLightOn = slotLightOn;
    }

    public void setSlotLightOff(Command slotLightOff) {
        this.slotLightOff = slotLightOff;
    }

    public void btnOnPressed(){

    }

    public void btnOffPressed(){

    }
}
