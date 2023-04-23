package code.design.mode.action.pattern.command.model;

import code.design.mode.action.pattern.command.ICommand;

public class LightOffCommand implements ICommand {
    private Light light;

    public Light getLight() {
        return light;
    }

    public void setLight(Light light) {
        this.light = light;
    }

    @Override
    public void execute() {
        light.lightOff();
    }

    @Override
    public void undo() {
        light.lightOn();
    }
}
