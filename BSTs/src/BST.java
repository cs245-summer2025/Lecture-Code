public class BST<T extends Comparable> {
    private static class BSTNode<T extends Comparable>{
        BSTNode left;
        BSTNode right;
        BSTNode parent;
        T item;

        public BSTNode(T value){
            item = value;
        }

        public BSTNode(T value, BSTNode leftChild, BSTNode rightChild){
            this.item = value;
            this.left = leftChild;
            this.right = rightChild;
        }

        // Get first node in the in-order traversal of the subBST
        private BSTNode getFirst(){
            BSTNode leftRunner = this;
            while(leftRunner.left != null){
                leftRunner = leftRunner.left;
            }
            return leftRunner;
        }

        // Get the successor node in the in-order traversal of the subBST
        private BSTNode getSuccessor(){
            BSTNode traverser = this;
            if(traverser.right != null) {
                return traverser.right.getFirst();
            }

            while(traverser.parent != null && traverser.parent.left != traverser) {
                traverser = traverser.parent;
            }
            return traverser.parent;
        }

        // Check if a node is a leaf
        private boolean isLeaf(){
            return this.left == null && this.right == null;
        }

        public String toString(){
            return item.toString();
        }
    }

    BSTNode root;
    public BST(T rootValue){
        root = new BSTNode(rootValue);
    }

    public BST(){
        root = null;
    }

    // Insert a value into the BST
    public void insert(T newValue){
        root = insert(root, newValue);
    }

    // Helper function to insert a value into the BST
    private BSTNode insert(BSTNode currentNode, T newValue){
        if(currentNode == null) {
            return new BSTNode(newValue);
        } else if(currentNode.item.compareTo(newValue) > 0) {
            currentNode.left = insert(currentNode.left, newValue);
            currentNode.left.parent = currentNode;
        } else if(currentNode.item.compareTo(newValue) < 0) {
            currentNode.right = insert(currentNode.right, newValue);
            currentNode.right.parent = currentNode;
        }
        return currentNode;
    }

    /*
        Returns boolean indicating whether the value is in our BST.
     */
    public boolean contains(T value){
        return contains(root, value);
    }

    private boolean contains(BSTNode currentNode, T value){
        if(currentNode == null){
            return false;
        } else if(currentNode.item.compareTo(value) == 0){
            return true;
        } else if(currentNode.item.compareTo(value) > 0){
            return contains(currentNode.left, value);
        }
        return contains(currentNode.right, value);
    }


    // Delete the value from the BST
    public void delete(T valueToDelete){
        BSTNode nodeToDelete = getNodeToDelete(root, valueToDelete);
        deleteNode(nodeToDelete);
    }
    private BSTNode getNodeToDelete(BSTNode node, T valueToDelete){
        if(node == null) {
            return null;
        }
        if(node.item.compareTo(valueToDelete) == 0){
            return node;
        } else if(node.item.compareTo(valueToDelete) > 0){
            return getNodeToDelete(node.left, valueToDelete);
        }
        return getNodeToDelete(node.right, valueToDelete);
    }

    private void deleteNode(BSTNode nodeToDelete){
        BSTNode parent;
        if(nodeToDelete.isLeaf()){
            parent = nodeToDelete.parent;
            if(parent == null){ // Handle case where nodeToDelete is root
                root = null;
                return;
            }
            if(parent.left == nodeToDelete){
                parent.left = null;
            } else {
                parent.right = null;
            }
            nodeToDelete.parent = null;
        } else if(nodeToDelete.left != null && nodeToDelete.right != null){ // two children
            BSTNode successor = nodeToDelete.getSuccessor();
            swap(successor, nodeToDelete);
            deleteNode(successor);
        } else if(nodeToDelete.left != null){ // nodeToDelete only has left child
            parent = nodeToDelete.parent;
            if(parent == null){ // Handle case where nodeToDelete is root
                root = nodeToDelete.left;
                nodeToDelete.left.parent = null;
                nodeToDelete.left = null;
            } else {
                if(parent.left == nodeToDelete){
                    parent.left = nodeToDelete.left;
                } else {
                    parent.right = nodeToDelete.left;
                }
                nodeToDelete.left.parent = parent;
                nodeToDelete.left = null;
                nodeToDelete.parent = null;
            }
        } else if(nodeToDelete.right != null){ // nodeToDelete only has right child
            parent = nodeToDelete.parent;
            if(parent == null){ // Handle case where nodeToDelete is root
                root = nodeToDelete.right;
                nodeToDelete.right.parent = null;
                nodeToDelete.right = null;
            } else {
                if(parent.left == nodeToDelete){
                    parent.left = nodeToDelete.right;
                } else {
                    parent.right = nodeToDelete.right;
                }
                nodeToDelete.right.parent = parent;
                nodeToDelete.right = null;
                nodeToDelete.parent = null;
            }
        }
    }

    private void swap(BSTNode a, BSTNode b){
        Comparable aValue = a.item;
        a.item = b.item;
        b.item = aValue;
    }

    public int height() {
        return height(root);
    }

    private int height(BSTNode node) {
        if (node == null)
            return 0;
        return Math.max(height(node.left), height(node.right)) + 1;
    }

    /*
       Code for print BST borrowed from https://www.geeksforgeeks.org/print-binary-tree-2-dimensions/
     */
    private static int getcol(int h) {
        if (h == 1)
            return 1;
        return getcol(h - 1) + getcol(h - 1) + 1;
    }

    private static void printBST(String[][] M, BSTNode root, int col, int row, int height) {
        if (root == null)
            return;
        if(root.item == null){
            M[row][col] = null;
        } else {
            M[row][col] = root.item.toString();
        }
        printBST(M, root.left, col - (int)Math.pow(2, height - 2), row + 1, height - 1);
        printBST(M, root.right, col + (int)Math.pow(2, height - 2), row + 1, height - 1);
    }

    public static void printBST(BST bstTree) {
        int h = bstTree.height();
        int col = getcol(h);
        String[][] M = new String[h][col];
        printBST(M, bstTree.root, col / 2, 0, h);
        for (int i = 0; i < h; i++) {
            for (int j = 0; j < col; j++) {
                if (M[i][j] == null)
                    System.out.print("  ");
                else
                    System.out.print(M[i][j] + " ");
            }
            System.out.println();
        }
    }

    public static void main(String[] args){
        int[] numbers = {25, 15, 10, 18, 6, 12, 30, 27, 40, 50};
        BST<Integer> bstTree = new BST();
        for(int n: numbers) {
            bstTree.insert(n);
        }
        System.out.println("The original BST");
        System.out.println("\n");
        printBST(bstTree);
        System.out.println("******************************************************************");
        System.out.println("\n");

        System.out.println("The BST after deleting the root of 25");
        System.out.println("\n");
        bstTree.delete(25);
        printBST(bstTree);
        System.out.println("******************************************************************");
        System.out.println("\n");

        System.out.println("The BST after deleting 30, a node that only had right child");
        System.out.println("\n");
        bstTree.delete(30);
        printBST(bstTree);
        System.out.println("******************************************************************");
        System.out.println("\n");

        System.out.println("The BST after deleting 18 and 15, a leaf node and node with only left child respectively");
        System.out.println("\n");
        bstTree.delete(15);
        bstTree.delete(18);
        printBST(bstTree);
        System.out.println("******************************************************************");
        System.out.println("\n");


        System.out.println("The BST after inserting 15 back into the bST");
        System.out.println("\n");
        bstTree.insert(15);
        printBST(bstTree);
    }
}
