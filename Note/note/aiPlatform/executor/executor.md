
job运行状态
```java
public enum JobRunStatus {
    CRON,
    RUN_SINGLE,
    RUN_ON_THIS,//运行到此任务
    RUN_AFTER_THIS,//从此任务开始运行
    RUN_ALL,
    OFFLINE,
    RUN_MULTI,
    LOOP,
    LOOP_FAILED,
    LOOP_SUCCESS,
    LOOP_RUNNING
}
```
表示是否上线,或删除 job状态
```java
public enum ActiveStatus {
	YES,
	NO,
	DELETE;
}
```

任务调度: 
-   OnlineExecutor
    SpringJobConfig  
    ---->ScheduledJobConfig(taskEngine, )
        
            ---->AbstractJobConfig(jobName, collection<TTaskConfig{genTask()}>)
                --->TJobConfig:genJob()


使用到的类: 
-   AutowireCapableBeanFactory:
    -   Spring 框架提供的一个接口，它可以在运行时自动装配（autowire）Spring Bean 的依赖关系，以及对 Bean 进行一些其他的处理。
        它是 Spring IoC（控制反转）容器的一部分，允许你以编程方式实现 Bean 的自动装配和创建，而不仅仅依赖于 XML 或注解配置。
    -   使用该类的原因: dag调度是有用户动态提交的,所以需要通过代码的方式动态的生产对应的bean对象
    ```java
    AutowireCapableBeanFactory factory = context.getAutowireCapableBeanFactory();
    ScheduledJobExecutor sde = new ScheduledJobExecutor();
    sde.setJobConfig(springJobConfig);
    factory.autowireBean(sde);
    factory.initializeBean(sde, "jobExecute-" + springJobConfig.getJobName());
    ```
    
spring整合quartz的方式: 
主要的三个类: Scheduler,JobDetail,Trigger
实现方式: 
-   MethodInvokingJobDetailFactoryBean:
    -   Spring 提供的一个特殊的 JobDetail 工厂，用于创建 Quartz 的 JobDetail，使得任务调度可以调用指定的方法。
        它允许你直接调用一个 Spring Bean 中的方法作为定时任务的执行逻辑，而不需要编写一个单独的 Job 类。





















