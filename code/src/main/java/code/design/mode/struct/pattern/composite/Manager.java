package code.design.mode.struct.pattern.composite;

import java.util.ArrayList;
import java.util.List;

public class Manager extends Component {
    List<Component> components = new ArrayList<>();

    Manager(String position, String job) {
        super(position, job);
    }

    @Override
    void addComponent(Component component) {
        components.add(component);
    }

    @Override
    void removeComponent(Component component) {
        components.remove(component);
    }

    @Override
    public void check() {
        work();
        components.forEach(Component::check);
    }
}
