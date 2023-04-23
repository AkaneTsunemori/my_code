package code.design.mode.action.pattern.command;

import code.design.mode.action.pattern.command.model.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class Test {
    Stack<ICommand> commands = new Stack<>();
    public  void test() {
        Door door =new Door();
        Light light = new Light();
        DoorOpenCommand doorOpenCommand = new DoorOpenCommand();
        DoorCloseCommand doorCloseCommand = new DoorCloseCommand();
        doorOpenCommand.setDoor(door);
        doorCloseCommand.setDoor(door);
        LightOnCommand lightOnCommand = new LightOnCommand();
        LightOffCommand lightOffCommand = new LightOffCommand();
        lightOnCommand.setLight(light);
        lightOffCommand.setLight(light);

        Switch switchDoor = new Switch();
        Switch switchLight = new Switch();
        switchDoor.setOnCheckedChangeListener((view, isChecked) -> {
            handleCommand(isChecked, doorOpenCommand, doorCloseCommand);
        });
        // 电灯开关遥控
        switchLight.setOnCheckedChangeListener((view, isChecked) -> {
            handleCommand(isChecked, lightOnCommand, lightOffCommand);
        });


        // 撤销按钮
//        btnUndo.setOnClickListener(view -> {
//            if (commands.isEmpty()) return;
//            // 撤销上一个命令
//            ICommand lastCommand = commands.pop();
//            lastCommand.undo();
//        });
    }

    private  void handleCommand(boolean isChecked, ICommand openCommand, ICommand closeCommand) {
        if (isChecked) {
            commands.push(openCommand);
            openCommand.execute();
        } else {
            commands.push(closeCommand);
            closeCommand.execute();
        }
    }
}
