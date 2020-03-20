import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class LinkedBlockingQueueTest {

    public static void main(String[] argc) throws InterruptedException {
        BlockingQueue<String> unbounded = new LinkedBlockingQueue<>();
        BlockingQueue<String> bounded   = new LinkedBlockingQueue<>(1024);

        bounded.put("Value");

        System.out.println(bounded.take());
    }

}
