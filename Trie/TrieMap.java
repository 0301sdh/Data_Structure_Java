package Trie;

import java.util.HashMap;
import java.util.Map;

public class TrieMap {

    static class Node {
        Map<Character, Node> children;

        boolean isEnd;

        public Node() {
            this.children = new HashMap<>();
            this.isEnd = false;
        }
    }

    private final Node root;

    public TrieMap() {
        this.root = new Node();
    }

    public void insert(String word) {
        Node cur = root;

        for (char ch : word.toCharArray()) {
            if (!cur.children.containsKey(ch)) {
                cur.children.put(ch, new Node());
            }

            cur = cur.children.get(ch);
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
            if (!cur.children.containsKey(ch)) {
                return null;
            }
            cur = cur.children.get(ch);
        }
        return cur;
    }

    public void delete(String word) {
        delete(root, word, 0);
    }

    private boolean delete(Node cur, String word, int depth) {
        if (cur == null) {
            return false;
        }

        if (depth == word.length()) {
            if (!cur.isEnd) {
                return false;
            }
            cur.isEnd = false;

            return cur.children.isEmpty();
        }

        char ch = word.charAt(depth);
        boolean shouldDeleteChild = delete(cur.children.get(ch), word, depth + 1);

        if (shouldDeleteChild) {
            cur.children.remove(ch);

            return !cur.isEnd && cur.children.isEmpty();
        }

        return false;
    }

    public static void main(String[] args) {
        TrieMap trie = new TrieMap();
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

        System.out.println("--- delete 테스트 ---");
        trie.delete("hi");
        System.out.println(trie.search("hi")); // false (지워짐)
        System.out.println(trie.search("high")); // true (안 건드려짐)
        System.out.println(trie.startsWith("hi")); // true (high가 hi 경로를 씀)

        trie.delete("rebro");
        System.out.println(trie.search("rebro")); // false
        System.out.println(trie.search("replay")); // true (re까지 공유, 나머지는 유지)
    }
}
