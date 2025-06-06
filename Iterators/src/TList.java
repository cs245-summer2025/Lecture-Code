public class TList<T> extends SList<T>{
    private ListNode sentinelNode;
    private ListNode tailNode;

    public TList() {
        super();
        this.tailNode = this.sentinelNode;
    }

    public TList(T value) {
        //super(value);
        this.tailNode = sentinelNode.next;
    }

    public void addFirst(T item) {
        super.addFirst(item);
        if(size() == 1) {
            tailNode = sentinelNode.next;
        }
    }
}
