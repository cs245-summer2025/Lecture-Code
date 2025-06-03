
public class DList<T> {
    private class DListNode {
        DListNode prev;
        DListNode next;
        T item;
        private DListNode(T value){
            this.item = value;
            this.prev = null;
            this.next = null;
        }

        private DListNode(T value, DListNode prev, DListNode next){
            this.item = value;
            this.prev = prev;
            this.next = next;
        }
    }

    private DListNode sentFront;
    private DListNode sentBack;
    private int listSize;

    public DList(T value){
        sentFront = new DListNode(null);
        sentBack = new DListNode(null);
        
        DListNode headNode = new DListNode(value, sentFront, sentBack);
        
        sentFront.next = headNode;
        sentBack.prev = headNode;
        listSize = 1;
    }

    public DList(){
        sentFront = new DListNode(null);
        sentBack = new DListNode(null, sentFront, null);
        sentFront.next = sentBack;
        listSize = 0;
    }

    public void insertElement(int index, T value){
        /*
            Examples:
            List: sf <--> 10 <-->5 <--> 7 <--> 12 <--> 3 <--> 4 <--> sb
            insert 100 at index 2: sf <--> 10 <--> 5 <--> 100 <--> 7 <--> 12 <--> 3 <--> 4 <--> sb
         */
        if(index < 0 || index > size()) {
            throw new IndexOutOfBoundsException("Index out of bounds for list of size " + size());
        }

        DListNode runner = sentFront.next;
        int runnerIdx = 0;

        while(runnerIdx < index) {
            runner = runner.next;
            runnerIdx += 1;
        }

        DListNode nodeToInsert = new DListNode(value, runner.prev, runner);
        nodeToInsert.prev.next = nodeToInsert;
        nodeToInsert.next.prev = nodeToInsert;
        listSize += 1;
    }

    // Deleting elements from a Doubly Linked List is left as an exercise to the reader.

    public int size(){
        return listSize;
    }

    public String toString(){
        StringBuilder result = new StringBuilder();
        DListNode runner = sentFront.next;
        while(runner != sentBack){
            result.append(runner.item);
            runner = runner.next;
            if(runner != sentBack){
                result.append(",");
            }
        }
        return result.toString();
    }
}
