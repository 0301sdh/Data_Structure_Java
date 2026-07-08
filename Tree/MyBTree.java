package Tree;

import javax.swing.text.html.StyleSheet;

public class MyBTree<T extends Comparable<T>> {

    // minDegree(t) : 최소 차수. t>=2
    // - 노드당 키 개수 : 최소 t-1개 ~ 최대 2t -1 개(루트는 최소 조건 예외)
    // - 노드당 자식 개수 : 키 개수 + 1

    private final int minDegree;

    private static class Node {
        int numKeys; // 현재 저장된 키 개수
        Object[] keys; // 키 배열
        Node[] children; // 자식 배열
        boolean isLeaf; // 리프노드의 여부

        @SuppressWarnings("unchecked")
        Node(int minDegree, boolean isLeaf) {
            this.isLeaf = isLeaf;
            this.numKeys = 0;
            this.keys = new Object[2 * minDegree - 1];
            this.children = new Node[2 * minDegree];
        }
    }

    private Node root;

    public MyBTree(int minDegree) {
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

    // 삽입
    public void insert(T data) {
        if (data == null)
            return;
        Node currentRoot = root;
        if (currentRoot.numKeys == 2 * minDegree - 1) {
            Node newRoot = new Node(minDegree, false);
            newRoot.children[0] = currentRoot;
            root = newRoot;
            splitChild(newRoot, 0);
            insertNonFull(newRoot, data);
        } else {
            insertNonFull(currentRoot, data);
        }
    }

    // 부모 노드의 childIndex번째 자식(꽉 찬 노드)을 둘로 분할한다.
    // 가운데 키는 부모로 올라가고, 자식은 왼쪽/오른쪽 두 개로 나뉜다.
    // @formatter:off
    //        [ ... midKey ... ]          부모(parent) : midKey가 올라옴
    //               |                        /        \
    //        [ fullChild ]      ──────►  [왼쪽 절반]   [오른쪽 절반=newRight]
    //     (키 2t-1개로 꽉 참)            (앞 t-1개)     (뒤 t-1개)
    // @formatter:on

    private void splitChild(Node parent, int childIndex) {
        Node fullChild = parent.children[childIndex];
        Node newRight = new Node(minDegree, fullChild.isLeaf);

        // fullChild의 뒤쪽 t-1개 키를 newRight로 옮긴다
        newRight.numKeys = minDegree - 1;
        for (int i = 0; i < minDegree; i++) {
            newRight.keys[i] = fullChild.keys[i + minDegree];
            fullChild.keys[i + minDegree] = null;
        }
        // 리프가 아니면 자식 포인터 t개도 옮긴다
        if (!fullChild.isLeaf) {
            for (int i = 0; i < minDegree; i++) {
                newRight.children[i] = fullChild.children[i + minDegree];
                fullChild.children[i + minDegree] = null;
            }
        }

        Object midKey = fullChild.keys[minDegree - 1]; // 부모로 올라갈 가운데 키
        fullChild.keys[minDegree - 1] = null;
        fullChild.numKeys = minDegree - 1; // fullChild엔 앞쪽 t-1개만 남음

        // 부모의 자식 배열에서 newRight가 들어갈 자리를 뒤로 밀어 만든다
        for (int i = parent.numKeys; i > childIndex; i--) {
            parent.children[i + 1] = parent.children[i];
        }
        parent.children[childIndex + 1] = newRight;

        // 부모의 키 배열에서 midKey가 들어갈 자리를 뒤로 밀어 만든다
        for (int i = parent.numKeys - 1; i > childIndex - 1; i--) {
            parent.keys[i + 1] = parent.keys[i];
        }
        parent.keys[childIndex] = midKey;
    }

    // 꽉 차지 않은 노드에 데이터를 삽입한다
    private void insertNonFull(Node node, T data) {
        int i = node.numKeys - 1;
        if (node.isLeaf) {
            while (i >= 0 && data.compareTo(keyAt(node, i)) < 0) {
                node.keys[i + 1] = node.keys[i];
                i--;
            }
            node.keys[i + 1] = data;
            node.numKeys++;
        } else {
            while (i >= 0 && data.compareTo(keyAt(node, i)) < 0) {
                i--;
            }
            int childIndex = i + 1;

            if (node.children[childIndex].numKeys == 2 * minDegree - 1) {
                splitChild(node, childIndex);
                if (data.compareTo(keyAt(node, childIndex)) > 0) {
                    childIndex++;
                }
            }
            insertNonFull(node.children[childIndex], data);
        }
    }

    // 삭제
    public void delete(T data) {
        delete(root, data);
        // 삭제 후 루트의 키가 0개가 되면(내부 노드였다면) 유일한 자식을 새 루트로 삼아
        // 트리 높이를 하나 줄인다(삽입에서 높이가 늘던 것의 반대)
        if (root.numKeys == 0 && !root.isLeaf) {
            root = root.children[0];
        }
    }

    // delete 부터 다시 하기
    private void delete(Node node, T data) {
        int index = findKeyIndex(node, data);

        if (index < node.numKeys && data.compareTo(keyAt(node, index)) == 0) {
            if (node.isLeaf) {
                removeFromLeaf(node, index);
            } else {
                removeFromNonLeaf(node, index);
            }
        } else {
            if (node.isLeaf) {
                return; // 트리에 존재하지 않는다
            }
            // 내려갈 자식(index번째)이 t-1뿐이면 먼저 t개로 채운다(최소보다 많아야 됨)
            if (node.children[index].numKeys < minDegree) {
                fill(node, index);
            }
        }
    }

    // data 이상인 첫 번째 키의 인덱스를 찾는다
    private int findKeyIndex(Node node, T data) {
        int index = 0;
        while (index < node.numKeys && data.compareTo(keyAt(node, index)) > 0) {
            index++;
        }
        return index;
    }

    // 리프에서 index 위치의 키를 지우고 뒤 키들을 앞으로 당긴다
    private void removeFromLeaf(Node node, int index) {
        for (int i = index + 1; i < node.numKeys; i++) {
            node.keys[i - 1] = node.keys[i];
        }
        node.keys[node.numKeys - 1] = null;
        node.numKeys--;
    }

    // 내부 노드에서 index 위치의 키를 지운다
    private void removeFromNonLeaf(Node node, int index) {
        T key = keyAt(node, index);

        if (node.children[index].numKeys >= minDegree) {
            T predecessor = getMax(node.children[index]);
            node.keys[index] = predecessor;
            delete(node.children[index], predecessor);
        } else if (node.children[index + 1].numKeys >= minDegree) {
            T succesor = getMin(node.children[index + 1]);
            node.keys[index] = succesor;
            delete(node.children[index + 1], succesor);
        } else {
            merge(node, index);
            delete(node.children[index], key);
        }
    }

    private T getMax(Node node) {
        while (!node.isLeaf) {
            node = node.children[node.numKeys];
        }
        return keyAt(node, node.numKeys - 1);
    }

    private T getMin(Node node) {
        while (!node.isLeaf) {
            node = node.children[0];
        }
        return keyAt(node, 0);
    }

    // 탐색
    public boolean contains(T data) {
        if (data == null) {
            return false;
        }
        return contains(root, data);
    }

    private boolean contains(Node node, T data) {
        int i = 0;
        while (i < node.numKeys && data.compareTo(keyAt(node, i)) > 0) {
            i++;
        }
        if (i < node.numKeys && data.compareTo(keyAt(node, i)) == 0) {
            return true;
        }
        if (node.isLeaf) {
            return false;
        }
        return contains(node.children[i], data);
    }

    // 순회
    public void inorder() {
        inorder(root);
        System.out.println();
    }

    private void inorder(Node node) {
        for (int i = 0; i < node.numKeys; i++) {
            if (!node.isLeaf) {
                inorder(node.children[i]);
            }
            System.out.print(keyAt(node, i) + " ");
        }
        if (!node.isLeaf) {
            inorder(node.children[i]);
        }
    }

}
