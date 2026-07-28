package Trie;

public class Trie {

    static class Node {
        Node[] children; // 자식 노드 26개
        boolean isEnd;

        public Node() {
            this.children = new Node[26];
            this.isEnd = false;
        }
    }

    private final Node root;

    public Trie() {
        root = new Node();
    }

    public void insert(String word) {
        Node cur = root;

        for (char ch : word.toCharArray()) {
            int idx = ch - 'a'; // 'a' = 0 , 'b' = 1...

            if (cur.children[idx] == null) {
                cur.children[idx] = new Node();
            }

            cur = cur.children[idx];
        }

        cur.isEnd = true;
    }

    public boolean search(String word) {
        Node node = getNode(word);
        return node != null && node.isEnd;
    }

    public boolean startsWith(String prefix) {
        return getNode(prefix) != null;
    }

    private Node getNode(String word) {
        Node cur = root;
        for (char ch : word.toCharArray()) {
            int idx = ch - 'a';
            if (cur.children[idx] == null) {
                return null;
            }
            cur = cur.children[idx];
        }
        return cur;
    }

    public static void main(String[] args) {
        Trie trie = new Trie();
        trie.insert("rebro");
        trie.insert("replay");
        trie.insert("hi");
        trie.insert("high");
        System.out.println(trie.search("hi")); // true
        System.out.println(trie.search("h")); // false (경로는 있지만 끝 표시 없음)
        System.out.println(trie.startsWith("h")); // true (h로 시작하는 게 있음)
        System.out.println(trie.search("rebro")); // true
        System.out.println(trie.search("rebr")); // false
        System.out.println(trie.startsWith("re")); // true
    }
}
