-   正常spring项目 通过jmeter压测存在超卖现象
    -   单服务下通过 synchronized 解决 (但是无法解决多服务问题)
    -   通过mysql数据库锁也可以解决:
        -   方式1:将判断和执行逻辑放到一个sql里面执行
        -   方式2:悲观锁:需要添加事务,并使用select... for update(必须等其他事务数据提交后才执行) 来查询
        -   方式3:乐观锁:增加version列,sql更新的时候判断和读取的时候是否是为更改的,也即比较并更新
    -   redis锁:
        -   redis乐观锁(基本不用):  redis监听+事务 watch  multi  exec
        -   使用setnx指令()
            -   问题1.如果setnx刚刚获取到锁,然后服务宕机,导致锁无法释放,其他服务拿不到锁
                解决方式:添加过期时间:eg: set key value ex 3 nx
            -   问题2:删除锁的时候需要判断是否是当前服务线程加的锁,所以需要绑定一个uuid
            -   引出问题3:绑定uuid后删除的时候需要有额外的判断逻辑导致无法做到原子性所以使用lua脚本
            -   问题4:没有保证可重入性:解决方案：redis + Hash +lua
            -   问题5:自动续期
        -   redis集群情况下 红锁算法  redission工具使用
    