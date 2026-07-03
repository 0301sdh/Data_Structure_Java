package Tree;

public class MyRedBlackTree<T extends Comparable<T>> {

    // 색상 : 코드가 읽기 쉽도록 boolean으로 표시 (true = Red, false = Black)
    private static final boolean RED = true;
    private static final boolean BLACK = false;

    private class Node<T> {
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
            y = root;
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
            x = root;
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
                    newNode.parent.parent.color = RED:
                    rotateRight(newNode.parent.parent);
                }
            }
            else{
                Node<T> uncle = newNode.parent.parent.left;
                if(uncle.color == RED){
                    newNode.parent.color = BLACK;
                    uncle.color = BLACK;
                    newNode.parent.parent.color = RED;
                    newNode = newNode.parent.parent;
                }
                else{
                    if(newNode == newNode.left){
                        newNode = newNode.parent;
                        rotateRight(newNode);
                    }
                    newNode.parent.color = BLACK;
                    newNode.parent.parent.color = RED;
                    rotateLeft(newNode.parent.parent);
                }
            }
            root.color = BLACK; // 루트 노드는 항상 BLACK
        }
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

    // 여기서 부터 다시하기
    public void delete(T data) {
        if (data == null) {
            throw new NullPointerException("NPE");
        }
        Node<T> current = root;
        while (current != NIL) {
            int cmp = data.compareTo(current.data);
            if (cmp < 0) {
                current = current.left;
            } else if (cmp > 0) {
                current = current.right;
            } else {
                break;
            }
        }
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