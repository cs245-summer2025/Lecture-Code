import java.util.*;

public class Trie {
    private static final int CHARACTER_SET_SIZE = 128;
    TrieNode root; // Acts as a sentinel node

    public static class TrieNode{
        char letter;
        boolean isKey;
        TrieNode[] children;

        TrieNode parent;

        public TrieNode(TrieNode parent, char k){
            this.letter = k;
            children = new TrieNode[CHARACTER_SET_SIZE];
            this.parent = parent;
        }
        private boolean isLeaf(){
            for(int i = 0; i < CHARACTER_SET_SIZE; i++){
                if(children[i] != null){
                    return false;
                }
            }
            return true;
        }
    }

    public Trie(){
        //Initialize root to take on any value.
        root = new TrieNode(null, 'a');
    }

    //Iteratively add String s to the trie
    public void insert(String s){
        if(s == null) {
            return;
        }
        TrieNode currentNode = root;
        int stringLength = s.length();
        for(int charIdx = 0; charIdx < stringLength; charIdx ++) {
            char currentCharacter = s.charAt(charIdx);
            if(currentNode.children[currentCharacter] == null) {
                TrieNode childNode = new TrieNode(currentNode, currentCharacter);
                currentNode.children[currentCharacter] = childNode;
            }
            currentNode = currentNode.children[currentCharacter];
        }
        currentNode.isKey = true;
    }

    //Iterative implementation to check if trie contains s
    public boolean contains(String s){
        if(s == null){
            return false;
        }
        int stringLength = s.length();
        TrieNode currentNode = root;

        for(int idx = 0; idx < stringLength; idx ++){
            char currentLetter = s.charAt(idx);
            if(currentNode.children[currentLetter] == null){
                return false;
            }
            currentNode = currentNode.children[currentLetter];
        }
        return currentNode.isKey;
    }
}
