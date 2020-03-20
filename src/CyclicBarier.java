import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.atomic.AtomicInteger;

public class CyclicBarier {

    public static void main(String [] argc){
        Runnable barrier1Action = new Runnable() {
            public void run() {
                System.out.println("BarrierAction 1 executed ");
            }
        };
        Runnable barrier2Action = new Runnable() {
            public void run() {
                System.out.println("BarrierAction 2 executed ");
            }
        };

        CyclicBarrier cyclicBarrier = new CyclicBarrier(2, barrier1Action);
        CyclicBarrier cyclicBarrier1 = new CyclicBarrier(2, barrier2Action);


        Thread t1 = new Thread(()->{
           try {
               System.out.println("t1 Waiting for another thread 1");
               cyclicBarrier.await();
               System.out.println("t1 Waiting for another thread 2");
               cyclicBarrier.await();
               System.out.println("t1 finish");
           } catch (InterruptedException | BrokenBarrierException e) {
               e.printStackTrace();
           }
        });
        Thread t2 = new Thread(()->{
            try {
                Thread.sleep(100);
                System.out.println("t2 Waiting for another thread 1");
                cyclicBarrier.await();
                System.out.println("t2 Waiting for another thread 2");
                Thread.sleep(100);
                cyclicBarrier.await();
                System.out.println("t2 finish");
            } catch (InterruptedException | BrokenBarrierException e) {
                e.printStackTrace();
            }
        });

        t1.start();
        t2.start();
    }

}
