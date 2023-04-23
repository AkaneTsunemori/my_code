package code.design.mode.struct.pattern.composite;

public class Employee extends Component {
    Employee(String position, String job) {
        super(position, job);
    }


    /***
     * 此处违背了接口隔离原则
     * 接口隔离原则：客户端不应依赖它不需要的接口。如果一个接口在实现时，
     * 部分方法由于冗余被客户端空实现，则应该将接口拆分，让实现类只需依赖自己需要的接口方法。
     *
     * 此实现方式为透明方式, 如果使用非透明方式, 则可以将addComponent和removeComponent 接口去掉
     *
     * 工作中大部分情况还是使用透明模式
     *
     * @param component
     */
    @Override
    void addComponent(Component component) {
        System.out.println("no authority! ");
    }

    @Override
    void removeComponent(Component component) {
        System.out.println("no authority! ");
    }

    @Override
    public void check() {
        work();
    }
}
