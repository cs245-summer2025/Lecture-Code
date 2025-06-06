
import java.util.Iterator;

public class SList<T> implements Iterable<T>{
    protected class ListNode<T>{
        T item;
        ListNode next;

        private ListNode(T value){
            this.item = value;
            this.next = null;
        }

        private ListNode(T value, ListNode next){
            this.item = value;
            this.next = next;
        }
    }

    private class SListIterator implements Iterator<T> {
        /*
            5 --> 3 --> 7 --> 4 --> 12 --> 19
            ^
            |
            |
            |
            runner
         */
        private ListNode runner;
        private SListIterator(ListNode head) {
            runner = head;
        }
        public T next() {
            // TOOD
            T returnValue = (T)runner.next;
            runner = runner.next;
            return returnValue;
        }
        public boolean hasNext() {
            return runner.next != null;
        }
    }

    private ListNode sentinelNode;
    private int listSize;
    public SList(){
        sentinelNode = new ListNode(null);
        listSize = 0;
    }
    public SList(T value){
        ListNode headNode = new ListNode(value);
        sentinelNode = new ListNode(null, headNode);
        listSize = 1;
    }

    public void addFirst(T value){
        ListNode newheadNode = new ListNode(value, sentinelNode.next);
        this.sentinelNode.next = newheadNode;
        this.listSize += 1;
    }

    public T getFirst(){
        if(sentinelNode.next == null){
            return null;
        }
        return (T)sentinelNode.next.item;
    }

    public int size(){
        return listSize;
    }

    public boolean equals(Object o) {
        if(o == null){
            return false;
        } else if(!(o instanceof SList)) {
            return false;
        }

        ListNode currentRunner = this.sentinelNode.next;
        ListNode oRunner = ((SList) o).sentinelNode.next;
        while(currentRunner != null && oRunner != null) {
            if(!currentRunner.item.equals(oRunner.item)) {
                return false;
            }
            currentRunner = currentRunner.next;
            oRunner = oRunner.next;
        }

        if(currentRunner == null && oRunner == null) {
            return true;
        }
        return false;
    }

    public String toString(){
        StringBuilder stringRepr = new StringBuilder();
        ListNode runner = sentinelNode.next;
        while(runner != null){
            stringRepr.append(runner.item);
            if(runner.next != null){
                stringRepr.append(",");
            }
            runner = runner.next;
        }
        return stringRepr.toString();
    }

    public SListIterator iterator() {
        return new SListIterator(sentinelNode.next);
    }
}
