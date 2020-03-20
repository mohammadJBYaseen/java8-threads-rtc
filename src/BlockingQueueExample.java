import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class BlockingQueueExample {

    public static void main(String[] argc){
        final BlockingQueue<String> blockingQueue = new ArrayBlockingQueue<>(100);
        Thread producer = new Thread(()->{
            try {
                blockingQueue.put("1");
                Thread.sleep(1000);
                blockingQueue.put("2");
                Thread.sleep(1000);
                blockingQueue.put("3");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        });
        Thread consumer = new Thread(()->{
            try {
                System.out.println(blockingQueue.take());
                System.out.println(blockingQueue.take());
                System.out.println(blockingQueue.take());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        });
        producer.start();
        consumer.start();
    }
}
