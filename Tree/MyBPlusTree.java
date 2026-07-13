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
        while (i < node.numKeys && data.compareTo(keyAt(node, i)) > 0) {
            i++;
        }
        return i;
    }

    // 삽입
    public void insert(T data){
        if(data == null){
            return;
        }
        if(root.numKeys == 2*minDegree -1){
            Node newRoot = new Node(minDegree, false));
            newRoot.children[0] = root;
            root = newRoot;
            splitChild(newRoot,0);
            insertNonFull(newRoot, data);
        }
        else{
            insertNonFull(Root, data);
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

        for (int i = parent.numKeys - 1; i > childIndex; i--) {
            parent.keys[i + 1] = parent.keys[i];
        }

        parent.children[childIndex] = upKey;
        parent.numKeys++;
    }

    private void insertNonFull(Node node, T data) {

    }
}