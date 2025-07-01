import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.util.*;
class TrieTest {
    @Test
    void testEmptyInsert(){
        Trie wordTrie = new Trie();
        wordTrie.insert("");
        assertTrue(wordTrie.contains(""));
    }

    @Test
    void testOnlyWhiteSpace(){
        Trie wordTrie = new Trie();
        wordTrie.insert("   ");

        assertTrue(wordTrie.contains("   "));
        assertFalse(wordTrie.contains(" "));
    }

    @Test
    void contains() {
        Trie wordTrie = new Trie();

        wordTrie.insert("Hello");
        wordTrie.insert("hero");
        wordTrie.insert("apple");
        wordTrie.insert("sad");
        wordTrie.insert("same");

        assertTrue(wordTrie.contains("Hello"));
        assertFalse(wordTrie.contains("hello"));

        assertTrue(wordTrie.contains("apple"));
        assertFalse(wordTrie.contains("sam"));

        assertTrue(wordTrie.contains("same"));
        assertTrue(wordTrie.contains("sad"));
        assertFalse(wordTrie.contains("sa"));
        assertFalse(wordTrie.contains("ad"));
        assertFalse(wordTrie.contains("d"));
    }
}