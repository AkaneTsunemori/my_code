
import java.util.concurrent.*;

public class Sync {

    ThreadPoolExecutor threadPool;

    public Sync() {
        this.threadPool = new ThreadPoolExecutor(2, 10,
                30L, TimeUnit.SECONDS,
                new ArrayBlockingQueue<Runnable>(100000),
                Executors.defaultThreadFactory(),
                new ThreadPoolExecutor.CallerRunsPolicy());
    }

    public static void main(String[] args) {
        Sync sync = new Sync();
        for (int i = 0;i<2;++i){
            sync.threadPool.submit(new Runnable() {
                @Override
                public void run() {
                    int count = (int) (5 * Math.random());
                    System.out.println("count:"+count);
                    CountDownLatch countDownLatch= new CountDownLatch(count);
                    sync.func(countDownLatch);
                    try {
                        countDownLatch.await(2,TimeUnit.SECONDS);
                        System.out.println("结束");
                        sync.threadPool.shutdownNow();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                }
            });
        }
    }
    public void func(CountDownLatch countDownLatch){
        threadPool.submit(new Runnable() {
            @Override
            public void run() {
                int circle = (int) (20 * Math.random());
                System.out.println("circle:"+circle);
                while(circle-->0){
                    for(int i = 0;i<circle;++i){
                        System.out.println("1");
                        countDownLatch.countDown();
                    }
                }

            }
        });

    }
}



