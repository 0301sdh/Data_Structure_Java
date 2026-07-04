package Tree;

public class MyRedBlackTree<T extends Comparable<T>> {

    // 색상 : 코드가 읽기 쉽도록 boolean으로 표시 (true = Red, false = Black)
    private static final boolean RED = true;
    private static final boolean BLACK = false;

    private static class Node<T> {
        T data;
        boolean color;
        Node<T> left, right, parent;

        Node(T data, boolean color) {
            this.data = data;
            this.color = color;
        }
    }

    private Node<T> root;
    private final Node<T> NIL; // 모든 리프가 공유하는 검정 센티넬(Null 대신 사용)

    public MyRedBlackTree() {
        NIL = new Node<>(null, BLACK);
        NIL.left = NIL;
        NIL.right = NIL;
        NIL.parent = NIL;
        root = NIL;
    }

    // 왼쪽 회전 (RR 케이스)
    // @formatter:off
    //     x                        y
    //    / \                      / \
    //   T1   y      ──────►      x   T3
    //       / \                 / \
    //     T2   T3             T1   T2
    // @formatter:on

    private void rotateLeft(Node<T> x) {
        Node<T> y = x.right;
        Node<T> T2 = y.left;

        x.right = T2;
        if (T2 != NIL) {
            T2.parent = x;
        }
        y.parent = x.parent;
        if (x.parent == NIL) {
            root = y;
        } else if (x == x.parent.left) {
            x.parent.left = y;
        } else {
            x.parent.right = y;
        }

        y.left = x;
        x.parent = y;
    }

    // ===== 회전 =====

    // 오른쪽 회전 (LL 케이스)
    // @formatter:off
    //         y                  x
    //        / \                / \
    //       x   T3   ──────►   T1  y
    //      / \                    / \
    //    T1   T2                T2   T3
    // @formatter:on

    private void rotateRight(Node<T> y) {
        Node<T> x = y.left;
        Node<T> T2 = x.right;

        y.left = T2;

        if (T2 != NIL) {
            T2.parent = y;
        }
        x.parent = y.parent;
        if (y.parent == NIL) {
            root = x;
        } else if (y == y.parent.left) {
            y.parent.left = x;
        } else {
            y.parent.right = x;
        }
        x.right = y;
        y.parent = x;
    }

    // 삽입

    public void insert(T data) {
        if (data == null) {
            throw new NullPointerException("NPE");
        }

        Node<T> newNode = new Node<>(data, RED);
        newNode.left = newNode.right = NIL;

        Node<T> prev = NIL;
        Node<T> x = root;

        while (x != NIL) {
            prev = x;
            int cmp = data.compareTo(x.data);
            if (cmp < 0) {
                x = x.left;
            } else if (cmp > 0) {
                x = x.right;
            } else {
                return;
            }
        }
        newNode.parent = prev;
        if (prev == NIL) {
            root = newNode;
        } else if (newNode.data.compareTo(prev.data) < 0) {
            prev.left = newNode;
        } else {
            prev.right = newNode;
        }

        insertFixup(newNode);
    }

    private void insertFixup(Node<T> newNode) {
        while (newNode.parent.color == RED) {
            if (newNode.parent == newNode.parent.parent.left) {
                Node<T> uncle = newNode.parent.parent.right;
                if (uncle.color == RED) {
                    // Case 1: 삼촌 Red -> 색만 바꾸고 위로 전파
                    newNode.parent.color = BLACK;
                    uncle.color = BLACK;
                    newNode.parent.parent.color = RED;
                    newNode = newNode.parent.parent;
                } else {
                    // Case 2: 삼촌 Black -> 회전
                    if (newNode == newNode.parent.right) {// LR 케이스 -> LL 케이스로
                        newNode = newNode.parent;
                        rotateLeft(newNode);
                    }
                    newNode.parent.color = BLACK;
                    newNode.parent.parent.color = RED;
                    rotateRight(newNode.parent.parent);
                }
            } else {
                Node<T> uncle = newNode.parent.parent.left;
                if (uncle.color == RED) {
                    newNode.parent.color = BLACK;
                    uncle.color = BLACK;
                    newNode.parent.parent.color = RED;
                    newNode = newNode.parent.parent;
                } else {
                    if (newNode == newNode.parent.left) {
                        newNode = newNode.parent;
                        rotateRight(newNode);
                    }
                    newNode.parent.color = BLACK;
                    newNode.parent.parent.color = RED;
                    rotateLeft(newNode.parent.parent);
                }
            }
        }
        root.color = BLACK; // 루트 노드는 항상 BLACK
    }

    // 삭제

    // u 자리에 v를 삽입
    private void transplant(Node<T> u, Node<T> v) {
        if (u.parent == NIL) {
            root = v;
        } else if (u == u.parent.left) {
            u.parent.left = v;
        } else {
            u.parent.right = v;
        }
        v.parent = u.parent;
    }

    private Node<T> minNode(Node<T> node) {
        while (node.left != NIL) {
            node = node.left;
        }
        return node;
    }

    public void delete(T data) {
        if (data == null) {
            throw new NullPointerException("NPE");
        }
        Node<T> target = root;
        while (target != NIL) {
            int cmp = data.compareTo(target.data);
            if (cmp < 0) {
                target = target.left;
            } else if (cmp > 0) {
                target = target.right;
            } else {
                break;
            }
        }
        if (target == NIL)
            return; // 없는 키

        Node<T> removed = target;

        boolean removedColor = removed.color;

        Node<T> fixNode; // removed 자리를 채울 노드(이중 블랙 후보)

        if (target.left == NIL) {
            fixNode = target.right;
            transplant(target, target.right);
        } else if (target.right == NIL) {
            fixNode = target.left;
            transplant(target, target.left);
        } else {
            removed = minNode(target.right);
            removedColor = removed.color;
            fixNode = removed.right;

            if (removed.parent == target) {
                fixNode.parent = removed;
            } else {
                transplant(removed, removed.right);
                removed.right = target.right;
                removed.right.parent = removed;
            }
            transplant(target, removed);
            removed.left = target.left;
            removed.left.parent = removed;
            removed.color = target.color;
        }

        if (removedColor == BLACK) {
            deleteFixup(fixNode);
        }
    }

    private void deleteFixup(Node<T> fixNode) {
        while (fixNode != root && fixNode.color == BLACK) {
            if (fixNode == fixNode.parent.left) {
                Node<T> sibling = fixNode.parent.right;

                if (sibling.color == RED) { // case 1 : 형제 red
                    sibling.color = BLACK;
                    fixNode.parent.color = RED;
                    rotateLeft(fixNode.parent);
                    sibling = fixNode.parent.right;
                }

                if (sibling.left.color == BLACK && sibling.right.color == BLACK) { // 형제 : black, 조카 둘다 black
                    sibling.color = RED;
                    fixNode = fixNode.parent;
                } else {
                    if (sibling.right.color == BLACK) { // case 3-1 왼쪽 조카가 RED
                        sibling.left.color = BLACK;
                        sibling.color = RED;
                        rotateRight(sibling);
                        sibling = fixNode.parent.right;
                    }
                    // case 3-2 오른쪽 조카가 RED
                    sibling.color = fixNode.parent.color;
                    fixNode.parent.color = BLACK;
                    sibling.right.color = BLACK;
                    rotateLeft(fixNode.parent);
                    fixNode = root; // 탈출용
                }
            } else { // fixNode = fixNode.parent.right
                Node<T> sibling = fixNode.parent.left;
                if (sibling.color == RED) {
                    sibling.color = BLACK;
                    fixNode.parent.color = RED;
                    rotateRight(fixNode.parent);
                    sibling = fixNode.parent.left;
                }
                if (sibling.right.color == BLACK && sibling.left.color == BLACK) {
                    sibling.color = RED;
                    fixNode = fixNode.parent;
                } else {
                    if (sibling.left.color == BLACK) {
                        sibling.right.color = BLACK;
                        sibling.color = RED;
                        rotateLeft(sibling);
                        sibling = fixNode.parent.left;
                    }
                    sibling.color = fixNode.parent.color;
                    fixNode.parent.color = BLACK;
                    sibling.left.color = BLACK;
                    rotateRight(fixNode.parent);
                    fixNode = root;
                }
            }
        }
        fixNode.color = BLACK;
    }

    // 조회
    public boolean contains(T data) {
        if (data == null) {
            return false;
        }
        Node<T> current = root;
        while (current != NIL) {
            int cmp = data.compareTo(current.data);
            if (cmp < 0) {
                current = current.left;
            } else if (cmp > 0) {
                current = current.right;
            } else {
                return true;
            }
        }
        return false;
    }

    // 순회

    public void inorder() {
        inorder(root);
        System.out.println();
    }

    private void inorder(Node<T> node) {
        if (node == NIL) {
            return;
        }
        inorder(node.left);
        System.out.println(node.data);
        inorder(node.right);
    }

    public boolean isBalanced() {
        if (root.color != BLACK) {
            return false;
        }
        return blackHeight(root) != -1;
    }

    private int blackHeight(Node<T> node) {
        if (node == NIL)
            return 1;
        if (node.color == RED && (node.left.color == RED || node.right.color == RED))
            return -1;
        int left = blackHeight(node.left);
        int right = blackHeight(node.right);

        if (left == -1 || right == -1 || left != right)
            return -1;

        return left + (node.color == BLACK ? 1 : 0);
    }
}