import static org.junit.jupiter.api.Assertions.*;

class DListTest {

    @org.junit.jupiter.api.Test
    void testSizeEmpty() {
        DList myList = new DList();
        assertTrue(myList.size() == 0);
    }

    @org.junit.jupiter.api.Test
    void testSizeSingle() {
        DList myList = new DList();
        myList.insertElement(0, 10);
        assertTrue(myList.size() == 1);
    }

    @org.junit.jupiter.api.Test
    void testGeneralSize() {
        DList myList = new DList();
        myList.insertElement(0, 10);
        myList.insertElement(1, 20);
        myList.insertElement(2, 30);
        myList.insertElement(0, 14);
        assertTrue(myList.size() == 4);
    }

    @org.junit.jupiter.api.Test
    void testFrontInsert() {
        DList myList = new DList();
        myList.insertElement(0, 10);
        myList.insertElement(1, 20);
        myList.insertElement(2, 30);
        myList.insertElement(0, 14);
        assertTrue(myList.toString().equals("14,10,20,30"));
    }

    @org.junit.jupiter.api.Test
    void testMiddleInsert() {
        DList myList = new DList();
        myList.insertElement(0, 10);
        myList.insertElement(1, 20);
        myList.insertElement(2, 30);
        myList.insertElement(1, 14);
        assertTrue(myList.toString().equals("10,14,20,30"));
    }

    @org.junit.jupiter.api.Test
    void testInsertEnd() {
        DList myList = new DList();
        myList.insertElement(0, 10);
        myList.insertElement(1, 20);
        myList.insertElement(2, 30);
        myList.insertElement(3, 14);
        assertTrue(myList.toString().equals("10,20,30,14"));
    }
}