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

            return isEmpty(cur);
        }

        int idx = word.charAt(depth) - 'a';
        boolean shouldDeleteChild = delete(cur.children[idx], word, depth + 1);

        if (shouldDeleteChild) {
            cur.children[idx] = null;

            return !cur.isEnd && isEmpty(cur);
        }

        return false;

    }

    private boolean isEmpty(Node node) {
        for (Node child : node.children) {
            if (child != null) {
                return false;
            }
        }
        return true;
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
