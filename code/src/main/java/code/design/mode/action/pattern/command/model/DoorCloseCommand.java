package code.design.mode.action.pattern.command.model;

import code.design.mode.action.pattern.command.ICommand;

public class DoorCloseCommand implements ICommand {
    private Door door;

    public Door getDoor() {
        return door;
    }

    public void setDoor(Door door) {
        this.door = door;
    }

    @Override
    public void execute() {
        door.closeDoor();
    }

    @Override
    public void undo() {
        door.openDoor();
    }
}
