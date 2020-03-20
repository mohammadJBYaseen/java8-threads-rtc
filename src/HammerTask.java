import java.util.*;
import java.util.stream.Collectors;

public class HammerTask {

    public int solution(int[] A, int Y) {
        Long maxCount =0L;
        TreeMap<Integer, Long> counts = Arrays.stream(A).boxed().collect(Collectors.groupingBy(string -> string, TreeMap::new, Collectors.counting()));
        for( Integer nailLength: counts.keySet()){
            long count =counts.get(nailLength);
            long d=Y;
            for (int i=nailLength; i<=counts.lastKey();i++){
                if (d == 0) {
                    maxCount = Math.max(maxCount, count);
                    count =counts.get(nailLength);
                    break;
                }
                if(!counts.containsKey(i)){
                    continue;
                }
                Long numberOfNails = counts.get(i);
                if(numberOfNails >=d){
                    count = count+ d;
                    d=0;

                }else{
                    count = count+numberOfNails;
                    d-= numberOfNails;
                }
            }
        }
        return maxCount.intValue();
    }





    public static void main(String [] argc){
        int [] A = new int[]{1,1,3,3,3,4,5,5,5,5};
        System.out.println(new HammerTask().solution(A,2));
    }
}
