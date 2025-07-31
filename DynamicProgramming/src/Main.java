import java.util.*;

public class Main {

    public static int fibonacci(int k) {
        if(k <= 0){
            throw new IllegalArgumentException("Argument must be greater than 0");
        }
        if(k <= 2){
            return 1;
        }
        Map<Integer, Integer> memo = new HashMap<>();
        memo.put(1, 1);
        memo.put(2, 1);
        return fibonacciMemoized(k, memo);
    }
    private static int fibonacciMemoized(int k, Map<Integer, Integer> memo){
        if(memo.containsKey(k)){
            return memo.get(k);
        }
        int k2 = fibonacciMemoized(k-2, memo);
        int k1 = fibonacciMemoized(k-1, memo);
        memo.put(k, k1 + k2);
        return memo.get(k);
    }

    /*
        Given a string and a set of dictionary words, we want to write a function that determines if the string
        can be partitioned such that each substring is in the dictionary.

        Example 1: dictionary = ["hello, "world"] and string = "helloworld" ---> True
        Example 2: dictionary = {"cats","dog","sand","and","cat"} and string = "catsandog" ---> False

        Intuition: Let S(i) = True if s[i:] can be partitioned to meet property above
            Sub Problem Definition: Suppose looking at substring s[i:j + 1]. S(i) = True if characters from [i, j) is a valid word and S(j) is True
     */
    public static boolean wordSplit(String s, List<String> words){
        int sLen = s.length();
        boolean[] dpSplit = new boolean[sLen + 1]; // Stores true/false if s[i:] can be partitioned
        dpSplit[sLen] = true; // Partitioning empty string is always possible

        for(int i = sLen; i >= 0; i --) {
            for(int j = i + 1; j <= sLen; j ++) {
                String subWord = s.substring(i, j);
                if(words.contains(subWord)) {
                    dpSplit[i] = dpSplit[i] || dpSplit[j];
                }
            }
        }
        return dpSplit[0];
    }

    public static int longestIncreasingSubsequence(int[] values){
        int numValues = values.length;
        int[] dpLIS = new int[numValues];
        int maxLIS = 1;

        // Each value is in it's own longest increasing subsequence
        for(int idx = 0; idx < numValues; idx ++) {
            dpLIS[idx] = 1;
        }
        for(int i = numValues - 2; i >= 0; i --) {
            for(int j = i + 1; j < numValues; j ++) {
                if(values[j] > values[i]) {
                    dpLIS[i] = Math.max(dpLIS[i], 1 + dpLIS[j]);
                    maxLIS = Math.max(maxLIS, dpLIS[i]);
                }
            }
        }
        return maxLIS;
    }

    public static void main(String[] args) {
        System.out.println(fibonacci(30));
    }
}

