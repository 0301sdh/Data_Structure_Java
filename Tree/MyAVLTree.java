package Tree;

import java.util.ArrayList;
import java.util.List;

public class MyAVLTree{

     private static class Node{
        int key;
        int height;
        Node left, right;

        Node(int key){
            this.key = key;
            this.height = 1;
        }
     }

     private Node root;

     private int height(Node node){
        return (node == null) ? 0 : node.height;
     }

     private int balanceFactor(Node node){
        return (node == null) ? 0 : height(node.left) - height(node.right);
     }

     private void updateHeight(Node node){
        node.height = 1 + Math.max(height(node.left), height(node.right));
     }

    // ===== 회전 =====

    // 오른쪽 회전 (LL 케이스)
    //        y                x
    //       / \              / \
    //      x   T3   --->    T1  y
    //     / \                  / \
    //    T1  T2               T2  T3

     private Node rotateRight(Node y){
        Node x = y.left;
        Node T2 = x.right;

        x.right = y;
        y.left = T2;
        updateHeight(y);
        updateHeight(x);
        return x;
     }

    // 왼쪽 회전 (RR 케이스)
    //      x                    y
    //     / \                  / \
    //    T1  y      --->      x   T3
    //       / \              / \
    //      T2  T3           T1  T2

    private Node rotateLeft(Node x){
        Node y = x.right;
        Node T2 = y.left;

        y.left = x;
        x.right = T2;

        updateHeight(x);
        updateHeight(y);
        return y;
    }

    private Node rebalance(Node node){
        updateHeight(node);
        int balance = balanceFactor(node);
        
    }


}