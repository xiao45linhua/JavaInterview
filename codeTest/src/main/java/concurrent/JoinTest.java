package concurrent;

/**
 * @author linhua
 * @apiNote
 * @date 2020-03-22 9:27 下午
 */

public class JoinTest {
    public static void main(String[] args) throws InterruptedException {
        System.out.println(Thread.currentThread().getName() + ": start");
        long start = System.currentTimeMillis();
        Thread t = new Thread(new MyRunnable());
        t.start();
        //阻塞主线程直到子线程完成 join底层是当前线程调用wait方法，也就是main方法调用wait，直到线程完成才继续执行

        t.join();
        long end = System.currentTimeMillis();
        System.out.println("子线程花费的时间是：" + (end - start) + "ms");
        System.out.println(Thread.currentThread().getName() + ": end");
    }

    static class MyRunnable implements Runnable{
        @Override
        public void run() {
            System.out.println(Thread.currentThread().getName() + ": start");
            for (int i = 0; i < 100000; i ++);
            System.out.println(Thread.currentThread().getName() + ": end");
        }
    }

}
