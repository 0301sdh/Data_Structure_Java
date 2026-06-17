package Tree;

public class MyBinarySearchTree<T extends Comparable<T>> {
    
    private static class Node<T>{
        T data;
        Node<T> left, right;
        Node(T data){
            this.data =data;
        }
    }

    private Node<T> root;

    // 데이터 삽입
    public void insert(T data){
        root = insert(root, data);
    }

    private Node<T> insert(Node<T> node, T data){
        if(node == null){
            return new Node<>(data);
        }
        int cmp = data.compareTo(node.data);
        if(cmp < 0){
            node.left =  insert(node.left, data);
        }
        else if(cmp > 0){
            node.right = insert(node.right,data);
        }
        return node;
    }

    //검색
    public boolean search(T data){
        return search(root, data);
    }

    private boolean search(Node<T> node, T data){
        if(node == null)
            return false;
        int cmp = data.compareTo(node.data);
        if(cmp<0){
            return search(node.left,data);
        }
        else if(cmp > 0){
            return search(node.right, data);
        }
        return true;
    }

    //삭제
    public void delete(T data){
        root = delete(root, data);
    }

    private Node<T> delete(Node<T> node, T data){
        if(node == null)
            return null;

        int cmp = data.compareTo(node.data);
        if(cmp< 0){
            node.left = delete(node.left, data);
        }
        else if(cmp > 0){
            node.right = delete(node.right, data);
        }
        else{ // 삭제할 노드를 찾음
            // 자식이 없거나 하나인 경우
            if(node.left == null)
                return node.right;
            if(node.right == null)
                return node.left;
            //자식이 2개인 경우 오른쪽 서브트리에서 가장 작은 값과 교체후 삭제
            Node<T> minNode = findMin(node.right);
            node.data = minNode.data;
            node.right = delete(node.right, minNode.data);
        }
        return node;
    }

    //서브트리에서 최솟값 찾기
    private Node<T> findMin(Node<T> node){
        while(node.left != null){
            node = node.left;
        }
        return node;
    }

    // 최솟값 찾기
    public T min(){
        if(this.root == null)
            throw new IllegalStateException("tree is empty");
        return findMin(this.root).data;
    }

    //최댓값 찾기
    public T max(){
        if(this.root == null)
            throw new IllegalStateException("tree is empty");
        Node<T> newNode = this.root;
        while(newNode.right != null)
            newNode = newNode.right;
        return newNode.data;
    }

    //중위 순회 : 왼쪽 -> 루트 -> 오른쪽(정렬된 순서로 출력됨)
    public void inorder(){
        inorder(this.root);
        System.out.println();
    }
    
    private void inorder(Node<T> node){
        if(node == null)
            return;
        inorder(node.left);
        System.out.print(node.data + " ");
        inorder(node.right);
    }

    //전위 순회: 루트 -> 왼쪽 -> 오른쪽
    public void preorder(){
        preorder(this.root);
        System.out.println();
    }

    private void preorder(Node<T> node){
        if(node == null)
            return;
        System.out.print(node.data + " ");
        preorder(node.left);
        preorder(node.right);
    }

    //후위 순회: 왼쪽 -> 오른쪽 -> 루트
    public void postorder(){
        postorder(this.root);
        System.out.println();
    }

    private void postorder(Node<T> node){
        if(node == null)
            return;
        postorder(node.left);
        postorder(node.right);
        System.out.print(node.data + " ");
    }
}
