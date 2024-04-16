package test;
import java.util.*;

class TrieNode {
    Map<Character, TrieNode> children;
    boolean isEndOfWord;

    TrieNode() {
        children = new HashMap<>();
        isEndOfWord = false;
    }
}

public class Trie {
    private TrieNode root;

    public Trie() {
        root = new TrieNode();
    }

    // Insert a word into the trie
    public void insert(String word) {
        TrieNode current = root;
        for (int i = 0; i < word.length(); i++) {
            char ch = word.charAt(i);
            TrieNode node = current.children.get(ch);
            if (node == null) {
                node = new TrieNode();
                current.children.put(ch, node);
            }
            current = node;
        }
        current.isEndOfWord = true;
    }

    // Search for a word in the trie
    public boolean search(String word) {
        TrieNode current = root;
        for (int i = 0; i < word.length(); i++) {
            char ch = word.charAt(i);
            TrieNode node = current.children.get(ch);
            if (node == null) {
                return false; // Word not found
            }
            current = node;
        }
        return current.isEndOfWord; // Check if it's the end of a word
    }

    // Print all words with the given prefix
    public void autoComplete(String prefix) {
        TrieNode current = root;
        for (int i = 0; i < prefix.length(); i++) {
            char ch = prefix.charAt(i);
            TrieNode node = current.children.get(ch);
            if (node == null) {
                System.out.println("No words found with prefix: " + prefix);
                return;
            }
            current = node;
        }
        List<String> words = new ArrayList<>();
        findWordsWithPrefix(current, prefix, words);
        System.out.println("Words with prefix '" + prefix + "': " + words);
    }

    // Helper method to recursively find words with given prefix
    private void findWordsWithPrefix(TrieNode node, String prefix, List<String> words) {
        if (node.isEndOfWord) {
            words.add(prefix);
        }
        for (char ch : node.children.keySet()) {
            findWordsWithPrefix(node.children.get(ch), prefix + ch, words);
        }
    }

    public static void main(String[] args) {
        Trie trie = new Trie();
        String[] names = {"Alice", "Anand","Bob", "Charlie", "David", "Emma", "Frank", "Grace", "Hannah", "Ivy", "Jack"};

        // Insert names into the trie
        for (String name : names) {
            trie.insert(name.toLowerCase()); // Insert names in lowercase for case-insensitive search
        }

        // // Search for specific names
        // System.out.println("Search results:");
        // System.out.println("Alice: " + trie.search("alice".toLowerCase()));
        // System.out.println("Zoe: " + trie.search("zoe".toLowerCase()));

        // // Autocomplete for given prefixes
        // System.out.println("\nAutocomplete results:");
        trie.autoComplete("a");
        // trie.autoComplete("B");
        // trie.autoComplete("C");
        // trie.autoComplete("D");
    }
}
