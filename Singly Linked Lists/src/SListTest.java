import static org.junit.jupiter.api.Assertions.*;

class SListTest {

    @org.junit.jupiter.api.Test
    void testAddFirst() {
        SList<Integer> myList = new SList<Integer>();
        myList.addFirst(10);
        myList.addFirst(20);
        assertTrue(myList.toString().equals("20,10"));
    }

    @org.junit.jupiter.api.Test
    void testGetFirst() {
        SList<Integer> myList = new SList<Integer>();
        myList.addFirst(10);
        myList.addFirst(20);
        assertTrue(myList.getFirst().equals(20));
    }

    @org.junit.jupiter.api.Test
    void testGetFirstSolo() {
        SList<Integer> myList = new SList<Integer>();
        myList.addFirst(2000);
        assertTrue(myList.getFirst().equals(2000));
    }

    @org.junit.jupiter.api.Test
    void testEmptyListSize(){
        SList<Integer> myList = new SList<Integer>();
        assertTrue(myList.size() == 0);
    }

    @org.junit.jupiter.api.Test
    void testNonEmptyListSize(){
        SList<Integer> myList = new SList<Integer>();
        myList.addFirst(1);
        myList.addFirst(2);
        myList.addFirst(5);
        assertTrue(myList.size() == 3);
    }

    @org.junit.jupiter.api.Test
    void testEmptyReverse(){
        SList<Integer> myList = new SList<Integer>();
        String expected = "";
        assertTrue(myList.reverse().toString().equals(expected));
    }

    @org.junit.jupiter.api.Test
    void testSoloReverse(){
        SList<Integer> myList = new SList<Integer>();
        myList.addFirst(17);
        String expected = "17";
        assertTrue(myList.reverse().toString().equals(expected));
    }

    @org.junit.jupiter.api.Test
    void testMultipleReverse(){
        SList<Integer> myList = new SList<Integer>();
        myList.addFirst(17);
        myList.addFirst(20);
        myList.addFirst(-5);
        myList.addFirst(3);
        // myList --> 3,-5,20,17
        String expected = "17,20,-5,3";
        assertTrue(myList.reverse().toString().equals(expected));
    }

    @org.junit.jupiter.api.Test
    void testPalindromeInt(){
        SList<Integer> myList = new SList<Integer>();
        int[] intstoAdd = {1,2,3,4};
        for(int i: intstoAdd) {
            myList.addFirst(i);
        }
        assertFalse(myList.isPalindrome());
    }

    @org.junit.jupiter.api.Test
    void testPalindromeIntV2(){
        SList<Integer> myList = new SList<Integer>();
        int[] intstoAdd = {1,2,2,1};
        for(int i: intstoAdd) {
            myList.addFirst(i);
        }
        assertTrue(myList.isPalindrome());
    }

    @org.junit.jupiter.api.Test
    void testPalindromeString(){
        SList<String> myList = new SList<String>();
        String[] stringstoAdd = {"hello", "world", "dog", "cat", "hello"};
        for(String s: stringstoAdd) {
            myList.addFirst(s);
        }
        assertFalse(myList.isPalindrome());
    }

    @org.junit.jupiter.api.Test
    void testPalindromeStringV2(){
        SList<String> myList = new SList<String>();
        String[] stringstoAdd = {"hello", "cat", "dog", "cat", "hello"};
        for(String s: stringstoAdd) {
            myList.addFirst(s);
        }
        assertTrue(myList.isPalindrome());
    }

    @org.junit.jupiter.api.Test
    void testEquals(){
        SList<String> sList1 = new SList<String>();
        SList<String> sList2 = new SList<String>();
        String[] stringstoAdd = {"hello", "cat", "dog", "cat", "hello"};
        for(String s: stringstoAdd) {
            sList1.addFirst(s);
            sList2.addFirst(s);
        }
        assertTrue(sList1.equals(sList2));
    }

    @org.junit.jupiter.api.Test
    void testEqualsV2(){
        SList<String> sList1 = new SList<String>();
        SList<String> sList2 = new SList<String>();
        String[] stringstoAdd = {"hello", "cat", "dog", "cat", "hello"};
        for(String s: stringstoAdd) {
            sList1.addFirst(s);
        }

        String[] newStrings = {"hello", "cat", "dog", "cat"};
        for(String s: newStrings) {
            sList2.addFirst(s);
        }

        assertFalse(sList1.equals(sList2));
    }

    @org.junit.jupiter.api.Test
    void testEqualsV3(){
        SList<String> sList1 = new SList<String>();
        SList<String> sList2 = new SList<String>();
        String[] stringstoAdd = {"hello", "cat", "dog", "cat", "hello"};
        for(String s: stringstoAdd) {
            sList1.addFirst(s);
        }

        String[] newStrings = {"hello", "cat", "dog", "cat", "dog"};
        for(String s: newStrings) {
            sList2.addFirst(s);
        }

        assertFalse(sList1.equals(sList2));
    }
}