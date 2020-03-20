package rtc;

import static rtc.TrialCalls.call;

public class Factorial{
    public static TailCall<Long> factorialTailRec(final long factorial, final long number) {
        if (number == 1L)
            return TrialCalls.done(factorial);
        else
            return call(() -> factorialTailRec(factorial * number, number - 1L));
    }

    public static void main(String[] argc){
        System.out.println(Factorial.factorialTailRec(1L,10L).invoke());
    }
}