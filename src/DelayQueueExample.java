import java.util.Comparator;
import java.util.Date;
import java.util.Objects;
import java.util.concurrent.DelayQueue;
import java.util.concurrent.Delayed;
import java.util.concurrent.TimeUnit;

public class DelayQueueExample {

    public static void main(String [] argc) throws InterruptedException {
        DelayQueue delayQueue = new DelayQueue();
        delayQueue.add(new DelayedItem(10,TimeUnit.SECONDS));
        delayQueue.add(new DelayedItem(2,TimeUnit.SECONDS));
        delayQueue.add(new DelayedItem(5,TimeUnit.SECONDS));
        ((DelayedItem)delayQueue.take()).print();
        ((DelayedItem)delayQueue.take()).print();
        ((DelayedItem)delayQueue.take()).print();

    }

    public static class DelayedItem implements Delayed{
        private long delay;
        private TimeUnit timeUnit;

        public DelayedItem(long delay, TimeUnit unit){
            this.delay = delay;
            this.timeUnit = unit;
        }

        @Override
        public long getDelay(TimeUnit unit) {
            return unit.convert(delay,timeUnit) -unit.convert(new Date().getTime(),timeUnit);
        }

        @Override
        public int compareTo(Delayed o) {
            return Objects.compare(this.getDelay(timeUnit), o.getDelay(timeUnit), Comparator.naturalOrder());
        }

        public void print(){
            System.out.println(TimeUnit.SECONDS.convert(delay,timeUnit));
        }
    }

}
