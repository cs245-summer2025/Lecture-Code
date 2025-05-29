public class AList<Item> {
    private int listSize = 0;
    private Item[] elements;
    private static final int growthFactor = 2;

    private static final int initialCapacity = 4;

    public AList(){
        this.elements = (Item[]) new Object[initialCapacity];
    }

    // Retrieves the Item at index i in the AList
    public Item get(int i){
        if(i < 0 || i >= size()) {
            String errorMessage = "Index % out of bounds for list of size %d";
            throw new IndexOutOfBoundsException(String.format(errorMessage, i, size()));
        }
        return elements[i];
    }

    // Returns the number of elements in the AList
    public int size(){
        return listSize;
    }

    // Insert value at the location at a specified index
    public void insertElement(int insertionIdx, Item value){
        // If and when should this function throw an error?
        if(insertionIdx < 0 || insertionIdx > size()) {
            String errorMessage = "Index %d out of bounds for list size %d";
            errorMessage = String.format(errorMessage, insertionIdx, size());
            throw new IndexOutOfBoundsException(errorMessage);
        }

        if(size() == elements.length) {
            increaseCapacity();
        }

        for(int idx = size() - 1; idx >= insertionIdx; idx --) {
            elements[idx + 1] = get(idx);
        }
        elements[insertionIdx] = value;
        listSize += 1;
    }

    // Remove the value at a specified index
    public void removeElement(int removalIdx){
        // If and when should this function throw an error?
        if(removalIdx < 0 || removalIdx > size()) {
            String errorMessage = "Index %d out of bounds for list size %d";
            errorMessage = String.format(errorMessage, removalIdx, size());
            throw new IndexOutOfBoundsException(errorMessage);
        }

        for(int idx = removalIdx; idx < size() - 1; idx ++) {
            elements[idx] = get(idx + 1);
        }
        listSize -= 1;

        for(int idx = elements.length - 1; idx >= size(); idx --) {
            elements[idx] = null;
        }
        // We can also dynamically shrink the underlying array when appropriate to make this more robust
    }

    private void increaseCapacity() {
        Item[] newElements =  (Item[]) new Object[growthFactor * size()];
        System.arraycopy(elements, 0, newElements, 0, size());
        this.elements = newElements;
    }

    public String toString(){
        StringBuilder builder = new StringBuilder();
        for(int idx = 0; idx < size(); idx++){
            if(get(idx) != null){
                builder.append(get(idx).toString());
                if(idx < size() - 1) {
                    builder.append(",");
                }
            }
        }
        return builder.toString();
    }

    /*
        We should have an equals method for this class. The implementation
        is left to the reader.
     */
}
