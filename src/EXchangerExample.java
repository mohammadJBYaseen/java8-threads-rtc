import java.text.MessageFormat;
import java.util.concurrent.Exchanger;

public class EXchangerExample {

    public static void main(String [] argc){
        Exchanger<String> value = new Exchanger<>();
        Thread t1 = new Thread(new Exchange("x",value));
        Thread t2 = new Thread(new Exchange("y",value));
        t1.start();
        t2.start();
        try {
            t1.join();
            t2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }


    public static class Exchange implements Runnable{

        private String name;
        private final Exchanger<String> exchanger;

        public Exchange(String name, Exchanger<String> exchanger){
            this.name =name;
            this.exchanger = exchanger;
        }

        @Override
        public void run() {
            String previousName = this.name;
            try {
                this.name = this.exchanger.exchange(previousName);
                System.out.println(MessageFormat.format("old message was {0} changed to {1}",previousName, this.name));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }
    }
}
