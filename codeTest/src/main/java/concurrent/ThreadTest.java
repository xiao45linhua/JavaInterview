package concurrent;


import java.util.ArrayList;
import java.util.List;

/**
 * @author linhua
 * @apiNote
 * @date 2020-03-21 9:48 下午
 */

public class ThreadTest {

    private class Buffer {
        private static final int MAX_CAPACITY = 1;
        private List innerList = new ArrayList<>(MAX_CAPACITY);

        void add() {
            if (isFull()) {
                throw new IndexOutOfBoundsException();
            } else {
                innerList.add(new Object());
            }
            System.out.println(Thread.currentThread().toString() + " add");

        }

        void remove() {
            if (isEmpty()) {
                throw new IndexOutOfBoundsException();
            } else {
                innerList.remove(MAX_CAPACITY - 1);
            }
            System.out.println(Thread.currentThread().toString() + " remove");
        }

        boolean isEmpty() {
            return innerList.isEmpty();
        }

        boolean isFull() {
            return innerList.size() == MAX_CAPACITY;
        }
    }

    private Buffer mBuf = new Buffer();

    public void produce() {
        synchronized (this) {
            while (mBuf.isFull()) {
                try {
                    System.out.println("produce start into wait");
                    wait();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            mBuf.add();
            System.out.println("produce notifyAll");
            notifyAll();
        }
    }

    public void consume() {
        synchronized (this) {
            while (mBuf.isEmpty()) {
                try {
                    System.out.println("consume start into wait");
                    wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            mBuf.remove();
            notifyAll();
        }
    }

    /**
     * JVM会为一个使用内部锁（synchronized）的对象维护两个集合，Entry Set和Wait Set，也有人翻译为锁池和等待池，意思基本一致。
     *
     * 对于Entry Set：如果线程A已经持有了对象锁，此时如果有其他线程也想获得该对象锁的话，它只能进入Entry Set，并且处于线程的BLOCKED状态。
     *
     * 对于Wait Set：如果线程A调用了wait()方法，那么线程A会释放该对象的锁，进入到Wait Set，并且处于线程的WAITING状态。
     *还有需要注意的是，某个线程B想要获得对象锁，一般情况下有两个先决条件，一是对象锁已经被释放了（如曾经持有锁的前任线程A执行完了synchronized代码块或者调用了wait()方法等等），二是线程B已处于RUNNABLE状态。
     *
     * @param args
     */

    public static void main(String[] args) {
        ThreadTest sth = new ThreadTest();
        Runnable runProduce = new Runnable() {
            int count = 4;

            @Override
            public void run() {
                while (count-- > 0) {
                    sth.produce();
                }
            }
        };
        Runnable runConsume = new Runnable() {
            int count = 4;

            @Override
            public void run() {
                while (count-- > 0) {
                    sth.consume();
                }
            }
        };
        for (int i = 0; i < 1; i++) {
            new Thread(runConsume).start();
        }
        for (int i = 0; i < 1; i++) {
            new Thread(runProduce).start();
        }
    }
}

