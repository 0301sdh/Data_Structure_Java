package Tree;

public class MyBPlusTree<T extends Comparable<T>> {

    // minDegree(T) : 최소 차수 t>=2
    // 노드당 키 개수 : 최소 t-1개 ~ 최대 2t-1개 (루트는 최소 조건 예외)
    // 리프/내부 모두 키 배열 크기는 2t-1

    private final int minDegree;

    private static class Node {
        int numKeys;
        Object[] keys;
        Node[] children;
        boolean isLeaf;
        Node next; // B+Tree: 리프끼리 잇는 연결 리스트 포인터

        Node(int minDegree, boolean isLeaf) {
            this.isLeaf = isLeaf;
            this.numKeys = 0;
            this.keys = new Object[2 * minDegree - 1];
            this.children = new Node[2 * minDegree];
            this.next = null;
        }
    }

    private Node root;

    public MyBPlusTree(int minDegree) {
        if (minDegree < 2) {
            throw new IllegalArgumentException("minimum degree needs to be at least 2");
        }
        this.minDegree = minDegree;
        this.root = new Node(minDegree, true);
    }

    @SuppressWarnings("unchecked")
    private T keyAt(Node node, int index) {
        return (T) node.keys[index];
    }

    // 내려갈 자식 index 찾기
    private int findChild(Node node, T data) {
        int i = 0;
        while (i < node.numKeys && data.compareTo(keyAt(node, i)) >= 0) {
            i++;
        }
        return i;
    }

    // 삽입
    public void insert(T data) {
        if (data == null) {
            return;
        }
        if (root.numKeys == 2 * minDegree - 1) {
            Node newRoot = new Node(minDegree, false);
            newRoot.children[0] = root;
            root = newRoot;
            splitChild(newRoot, 0);
            insertNonFull(newRoot, data);
        } else {
            insertNonFull(root, data);
        }
    }

    // 부모의 childIndex번째 자식(꽉 찬 노드)를 둘로 분할한다
    private void splitChild(Node parent, int childIndex) {
        Node fullChild = parent.children[childIndex];
        Node newRight = new Node(minDegree, fullChild.isLeaf);
        Object upKey; // 부모로 올라갈 키

        if (fullChild.isLeaf) {
            newRight.numKeys = minDegree - 1;
            for (int i = 0; i < minDegree - 1; i++) {
                newRight.keys[i] = fullChild.keys[i + minDegree];
                fullChild.keys[i + minDegree] = null;
            }
            fullChild.numKeys = minDegree;
            upKey = newRight.keys[0]; // 오른쪽 리프의 첫 키를 복사해서 올림
            newRight.next = fullChild.next;
            fullChild.next = newRight;
        } else {
            newRight.numKeys = minDegree - 1;
            for (int i = 0; i < minDegree - 1; i++) {
                newRight.keys[i] = fullChild.keys[i + minDegree];
                fullChild.keys[i + minDegree] = null;
            }
            for (int i = 0; i < minDegree; i++) {
                newRight.children[i] = fullChild.children[i + minDegree];
                fullChild.children[i + minDegree] = null;
            }
            upKey = fullChild.keys[minDegree - 1];
            fullChild.keys[minDegree - 1] = null;
            fullChild.numKeys = minDegree - 1;
        }
        for (int i = parent.numKeys; i > childIndex; i--) {
            parent.children[i + 1] = parent.children[i];
        }
        parent.children[childIndex + 1] = newRight;

        for (int i = parent.numKeys - 1; i >= childIndex; i--) {
            parent.keys[i + 1] = parent.keys[i];
        }

        parent.keys[childIndex] = upKey;
        parent.numKeys++;
    }

    private void insertNonFull(Node node, T data) {
        if (node.isLeaf) {
            int i = node.numKeys - 1;
            while (i >= 0 && data.compareTo(keyAt(node, i)) < 0) {
                node.keys[i + 1] = node.keys[i];
                i--;
            }
            node.keys[i + 1] = data;
            node.numKeys++;
        } else {
            int childIndex = findChild(node, data);
            if (node.children[childIndex].numKeys == 2 * minDegree - 1) {
                splitChild(node, childIndex);
                if (data.compareTo(keyAt(node, childIndex)) >= 0) {
                    childIndex++;
                }
            }
            insertNonFull(node.children[childIndex], data);
        }
    }

    // 탐색
    public boolean contains(T data) {
        if (data == null) {
            return false;
        }
        return contains(root, data);
    }

    private boolean contains(Node node, T data) {
        if (node.isLeaf) {
            for (int i = 0; i < node.numKeys; i++) {
                if (data.compareTo(keyAt(node, i)) == 0) {
                    return true;
                }
            }
            return false;
        }
        return contains(node.children[findChild(node, data)], data);
    }

    public void delete(T data) {
        if (data == null) {
            return;
        }
        delete(root, data);
        if (!root.isLeaf && root.numKeys == 0) {
            root = root.children[0];
        }
    }

    private void delete(Node node, T data) {
        if (node.isLeaf) {
            int idx = 0;
            while (idx < node.numKeys && data.compareTo(keyAt(node, idx)) > 0) {
                idx++;
            }
            if (idx < node.numKeys && data.compareTo(keyAt(node, idx)) == 0) {
                removeFromLeaf(node, idx);
            }
            return;
        }

        int childIndex = findChild(node, data);
        if (node.children[childIndex].numKeys < minDegree) {
            fill(node, childIndex);
            childIndex = findChild(node, data);
        }
        delete(node.children[childIndex], data);

        // 삭제한 값이 이 노드의 분리키와 같다면
        for (int j = 0; j < node.numKeys; j++) {
            if (data.compareTo(keyAt(node, j)) == 0) {
                node.keys[j] = getMin(node.children[j + 1]);
                break;
            }
        }
    }

    private void removeFromLeaf(Node node, int index) {
        for (int i = index + 1; i < node.numKeys; i++) {
            node.keys[i - 1] = node.keys[i];
        }
        node.keys[node.numKeys - 1] = null;
        node.numKeys--;
    }

    private void fill(Node node, int childIndex) {
        boolean leaf = node.children[childIndex].isLeaf;
        if (childIndex != 0 && node.children[childIndex - 1].numKeys >= minDegree) {
            if (leaf) {
                borrowFromPrevLeaf(node, childIndex);
            } else {
                borrowFromPrevInternal(node, childIndex);
            }
        } else if (childIndex != node.numKeys && node.children[childIndex + 1].numKeys >= minDegree) {
            if (leaf) {
                borrowFromNextLeaf(node, childIndex);
            } else {
                borrowFromNextInternal(node, childIndex);
            }
        } else {
            if (childIndex != node.numKeys) {
                merge(node, childIndex);
            } else {
                merge(node, childIndex - 1);
            }
        }
    }

    private void borrowFromPrevLeaf(Node node, int childIndex) {
        Node child = node.children[childIndex];
        Node left = node.children[childIndex - 1];
        for (int i = child.numKeys - 1; i >= 0; i--) {
            child.keys[i + 1] = child.keys[i];
        }
        child.keys[0] = left.keys[left.numKeys - 1];
        left.keys[left.numKeys - 1] = null;
        left.numKeys--;
        child.numKeys++;
        node.keys[childIndex - 1] = child.keys[0];
    }

    private void borrowFromNextLeaf(Node node, int childIndex) {
        Node child = node.children[childIndex];
        Node right = node.children[childIndex + 1];
        child.keys[child.numKeys] = right.keys[0];
        child.numKeys++;
        for (int i = 1; i < right.numKeys; i++) {
            right.keys[i - 1] = right.keys[i];
        }
        right.keys[right.numKeys - 1] = null;
        right.numKeys--;
        node.keys[childIndex] = right.keys[0];
    }

    private void borrowFromPrevInternal(Node node, int childIndex) {
        Node child = node.children[childIndex];
        Node left = node.children[childIndex - 1];
        for (int i = child.numKeys - 1; i >= 0; i--) {
            child.keys[i + 1] = child.keys[i];
        }
        for (int i = child.numKeys; i >= 0; i--) {
            child.children[i + 1] = child.children[i];
        }
        child.keys[0] = node.keys[childIndex - 1];
        child.children[0] = left.children[left.numKeys];
        left.children[left.numKeys] = null;
        node.keys[childIndex - 1] = left.keys[left.numKeys - 1];
        left.keys[left.numKeys - 1] = null;
        child.numKeys++;
        left.numKeys--;
    }

    private void borrowFromNextInternal(Node node, int childIndex) {
        Node child = node.children[childIndex];
        Node right = node.children[childIndex + 1];
        child.keys[child.numKeys] = node.keys[childIndex];
        child.children[child.numKeys + 1] = right.children[0];
        node.keys[childIndex] = right.keys[0];
        for (int i = 1; i < right.numKeys; i++) {
            right.keys[i - 1] = right.keys[i];
        }
        right.keys[right.numKeys - 1] = null;
        for (int i = 1; i <= right.numKeys; i++) {
            right.children[i - 1] = right.children[i];
        }
        right.children[right.numKeys] = null;
        child.numKeys++;
        right.numKeys--;
    }

    private void merge(Node node, int childIndex) {
        Node left = node.children[childIndex];
        Node right = node.children[childIndex + 1];
        if (left.isLeaf) {
            for (int i = 0; i < right.numKeys; i++) {
                left.keys[left.numKeys + i] = right.keys[i];
            }
            left.numKeys += right.numKeys;
            left.next = right.next;
        } else {
            left.keys[left.numKeys] = node.keys[childIndex];
            for (int i = 0; i < right.numKeys; i++) {
                left.keys[left.numKeys + 1 + i] = right.keys[i];
            }
            for (int i = 0; i <= right.numKeys; i++) {
                left.children[left.numKeys + 1 + i] = right.children[i];
            }
            left.numKeys += right.numKeys + 1;
        }

        for (int i = childIndex + 1; i < node.numKeys; i++) {
            node.keys[i - 1] = node.keys[i];
        }
        node.keys[node.numKeys - 1] = null;

        for (int i = childIndex + 2; i <= node.numKeys; i++) {
            node.children[i - 1] = node.children[i];
        }
        node.children[node.numKeys] = null;
        node.numKeys--;
    }

    private T getMin(Node node) {
        while (!node.isLeaf) {
            node = node.children[0];
        }
        return keyAt(node, 0);
    }

    public void printLeaves() {
        Node node = root;
        while (!node.isLeaf) {
            node = node.children[0];
        }
        StringBuilder sb = new StringBuilder();
        while (node != null) {
            sb.append("[");
            for (int i = 0; i < node.numKeys; i++) {
                sb.append(keyAt(node, i));
                if (i < node.numKeys - 1) {
                    sb.append(" ");
                }
            }
            sb.append("]");
            if (node.next != null) {
                sb.append("->");
            }
            node = node.next;
        }
        System.out.print(sb);
    }

    public static void main(String[] args) {
        MyBPlusTree<Integer> tree = new MyBPlusTree<>(2);
        int[] data = { 1, 2, 3, 4, 5, 6, 7, 8, 9, 10 };
        for (int x : data) {
            tree.insert(x);
        }

        System.out.println("데이터 순차 출력");
        tree.printLeaves();
        System.out.println();

        tree.delete(5);
        tree.delete(3);

        tree.printLeaves();
    }
}