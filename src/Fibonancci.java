import java.text.MessageFormat;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;

public class Fibonancci {

    public static void main(String [] argc){
        long startTime = System.currentTimeMillis();
        //System.out.println(new FibonacciProblem(50).solve());
        long processingTime = System.currentTimeMillis()-startTime;
        //System.out.println(MessageFormat.format("time used to calculate Fibonacci {0}", (processingTime*1D)/1000));
        try {
            int i = Runtime.getRuntime().availableProcessors();
            startTime = System.currentTimeMillis();
            System.out.println(new ForkJoinPool(i).submit(new FibonacciTask(20,new FibonacciProblem(50))).get());
            processingTime = System.currentTimeMillis()-startTime;
            System.out.println(MessageFormat.format("time used to calculate Fibonacci {0}", (processingTime*1D)/1000));
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
    }

    public static class FibonacciTask extends RecursiveTask<Long>{

        private int threshold=5;
        private FibonacciProblem problem;


        public FibonacciTask(int threshold,FibonacciProblem problem){
            this.problem =problem;
            this.threshold = threshold;
        }

        @Override
        protected Long compute() {
            if(problem.n <= threshold){
                return problem.solve();
            }else{
                FibonacciTask t1 = new FibonacciTask(threshold,new FibonacciProblem(problem.n-1));
                problem.n = problem.n-2;
                t1.fork();
                return compute()+t1.join();
            }
        }
    }

    public static class FibonacciProblem {

        public int n;

        public FibonacciProblem(int n) {
            this.n = n;
        }

        public long solve() {
            return fibonacci(n);
        }

        private long fibonacci(int n) {
            if (n <= 1)
                return 1;
            else
                return fibonacci(n-1) + fibonacci(n-2);
        }

    }
}
