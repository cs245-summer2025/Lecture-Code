public class SList<T> {
    private class ListNode<T>{
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

    ListNode sentinelNode;

    public SList(){
        sentinelNode = new ListNode(null);
    }
    public SList(T value){
        ListNode headNode = new ListNode(value);
        sentinelNode = new ListNode(null, headNode);
    }

    public void addFirst(T value){
        if(sentinelNode.next == null){
            sentinelNode.next = new ListNode(value);
        } else {
            ListNode currentHead = sentinelNode.next;
            ListNode newheadNode = new ListNode(value, currentHead);
            sentinelNode.next = newheadNode;
        }
    }

    public T getFirst(){
        if(sentinelNode.next == null){
            return null;
        }
        return (T)sentinelNode.next.item;
    }

    /*
        Adding elements anywhere in the list and removing values is left as an
        exercise to the reader.
     */

    /*
        Exercise for the reader: How do we modify the SList class so that the size function
        runs in constant time (i.e. independent of the size of the list)?

        Question: Why not just make the sizeHelper method public and have downstream users
        call that method?
     */
    public int size(){
        return sizeHelper(sentinelNode.next);
    }
    private int sizeHelper(ListNode node){
        if(node == null){
            return 0;
        }
        return 1 + sizeHelper(node.next);
    }

    public boolean isPalindrome(){
        // TODO
        if(size() == 0) {
            return true;
        }
        SList reversedList = reverse();
        return this.equals(reversedList);
    }

    public SList reverse(){
        SList reversedList = new SList();
        reverseHelper(sentinelNode.next, reversedList);
        return reversedList;
    }
    private void reverseHelper(ListNode runner, SList result){
        /*
            1,2,3,4 []
            4,3,2,1
         */
        while(runner != null) {
            result.addFirst(runner.item);
            runner = runner.next;
        }
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
}
