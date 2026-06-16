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
        int cmp = data.compareTo(root.data);
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
            return serach(node.right, data);
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
        else{
            if()
        }

        return node;
    }




}
