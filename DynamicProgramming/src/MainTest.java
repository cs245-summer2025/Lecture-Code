import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

import java.util.*;

class MainTest {
    @Test
    public void splitV1(){
        String s= "helloworld";
        String[] words = {"hello","world"};
        List<String> input = new ArrayList<>();
        for(String w: words){
            input.add(w);
        }
        assertTrue(Main.wordSplit(s, input));
    }

    @Test
    public void splitV2(){
        String s= "applepenapple";
        String[] words = {"app","apple", "lepen"};
        List<String> input = new ArrayList<>();
        for(String w: words){
            input.add(w);
        }
        assertTrue(Main.wordSplit(s, input));
    }

    @Test
    public void splitV3(){
        String s= "catsandog";
        String[] words = {"cats","dog","sand","and","cat"};
        List<String> input = new ArrayList<>();
        for(String w: words){
            input.add(w);
        }
        assertFalse(Main.wordSplit(s, input));
    }

    @Test
    public void splitV4(){
        String s= "aaaaaaa";
        String[] words = {"aaaa", "aaa"};
        List<String> input = new ArrayList<>();
        for(String w: words){
            input.add(w);
        }
        assertTrue(Main.wordSplit(s, input));
    }

    @Test
    public void splitV5(){
        String s= "catsandog";
        String[] words = {"og","sand", "dog", "cat"};
        List<String> input = new ArrayList<>();
        for(String w: words){
            input.add(w);
        }
        assertTrue(Main.wordSplit(s, input));
    }

//    @Test
//    public void lisV1(){
//        int[] vals = {7, 4, 9, 3, 2, 1, 10, 12, 6, 15};
//        assertTrue(Main.longestIncreasingSubsequence(vals) == 5);
//    }
//
//    @Test
//    public void lisV2(){
//        int[] vals = {10, 9, 8, 7, 6, 5, 4, 3, 2, 1};
//        assertTrue(Main.longestIncreasingSubsequence(vals) == 1);
//    }
//
//    @Test
//    public void lisV3(){
//        int[] vals = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
//        assertTrue(Main.longestIncreasingSubsequence(vals) == 10);
//    }
//
//    @Test
//    public void lisV4(){
//        int[] vals = {10, 5, 10, 5, 10, 5, 6, 7, 4, 3, 2};
//        assertTrue(Main.longestIncreasingSubsequence(vals) == 3);
//    }

}