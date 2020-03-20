import java.util.Comparator;
import java.util.Objects;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.PriorityBlockingQueue;

public class LinkedPriorityBlockingQueueTest {

    public static void main(String[] argc) throws InterruptedException {
        BlockingQueue queue   = new PriorityBlockingQueue();

        //String implements java.lang.Comparable
        queue.put(new PriorityItem<String>("test",1));
        queue.put(new PriorityItem<String>("test2",2));
        queue.put(new PriorityItem<String>("test5",5));
        queue.put(new PriorityItem<String>("test3",3));
        queue.put(new PriorityItem<String>("test10",10));


        System.out.println(((PriorityItem<String >) queue.take()).value);
        System.out.println(((PriorityItem<String >) queue.take()).value);
        System.out.println(((PriorityItem<String >) queue.take()).value);
        System.out.println(((PriorityItem<String >) queue.take()).value);
        System.out.println(((PriorityItem<String >) queue.take()).value);
    }

    public static class PriorityItem<T> implements Comparable<PriorityItem>{

        private int priority;
        private T value;

        public PriorityItem(T value, int priority){
            this.value = value;
            this.priority = priority;
        }

        @Override
        public int compareTo(PriorityItem o) {
            return Objects.compare(priority, o.priority, Comparator.reverseOrder());
        }
    }

}
