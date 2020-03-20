import java.util.Arrays;
import java.util.concurrent.atomic.AtomicInteger;

class Solution {
    public int solution(int[] A) {
        // write your code in Java SE 8
        AtomicInteger min =new AtomicInteger(1);
        return Arrays.stream(A).sorted().filter( a-> a>=0 && a ==min.get()).map( t-> min.incrementAndGet()).reduce(Integer::max).orElse(1);
    }

    public static void main(String [] argc){
        int [] A = new int[]{1,2, 3};
        int [] B = new int[] {-1,-2};
        int [] c= new int [] {1, 3, 6, 4, 1, 2};
        Solution solution = new Solution();
        System.out.println(solution.solution(A));
        System.out.println(solution.solution(B));
        System.out.println(solution.solution(c));
    }
}
