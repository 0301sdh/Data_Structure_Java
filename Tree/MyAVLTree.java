package Tree;

public class MyAVLTree<T extends Comparable<T>> {

   private static class Node<T> {
      T data;
      int height;
      Node<T> left, right;

      Node(T data) {
         this.data = data;
         this.height = 1;
      }
   }

   private Node<T> root;

   private int height(Node<T> node) {
      return (node == null) ? 0 : node.height;
   }

   private int balanceFactor(Node<T> node) {
      return (node == null) ? 0 : height(node.left) - height(node.right);
   }

   private void updateHeight(Node<T> node) {
      node.height = 1 + Math.max(height(node.left), height(node.right));
   }

   // ===== 회전 =====

   // 오른쪽 회전 (LL 케이스)
   // @formatter:off
   //         y                  x
   //        / \                / \
   //       x   T3   ──────►   T1   y
   //      / \                    / \
   //    T1   T2                T2   T3
   // @formatter:on

   private Node<T> rotateRight(Node<T> y) {
      Node<T> x = y.left;
      Node<T> T2 = x.right;

      x.right = y;
      y.left = T2;
      updateHeight(y);
      updateHeight(x);
      return x;
   }

   // 왼쪽 회전 (RR 케이스)
   // @formatter:off
   //     x                        y
   //    / \                      / \
   //   T1   y      ──────►      x   T3
   //       / \                 / \
   //     T2   T3             T1   T2
   // @formatter:on

   private Node<T> rotateLeft(Node<T> x) {
      Node<T> y = x.right;
      Node<T> T2 = y.left;

      y.left = x;
      x.right = T2;

      updateHeight(x);
      updateHeight(y);
      return y;
   }

   private Node<T> rebalance(Node<T> node) {
      updateHeight(node);
      int balance = balanceFactor(node);

      // LL : 왼쪽이 무겁고 왼쪽 자식이 왼쪽으로 치우침
      if (balance > 1 && balanceFactor(node.left) >= 0)
         return rotateRight(node);

      // LR : 왼쪽이 무겁고 왼쪽 자식이 오른쪽으로 치우침
      if (balance > 1 && balanceFactor(node.left) < 0) {
         node.left = rotateLeft(node.left);
         return rotateRight(node);
      }

      // RR : 오른쪽이 무겁고 오른쪽 자식이 오른쪽으로 치우침
      if (balance < -1 && balanceFactor(node.right) <= 0)
         return rotateLeft(node);

      // RL : 오른쪽이 무겁고 오른쪽 자식이 왼쪽으로 치우침
      if (balance < -1 && balanceFactor(node.right) > 0) {
         node.right = rotateRight(node.right);
         return rotateLeft(node);
      }

      return node; // 이미 균형
   }

   // insert : 데이터 삽입
   public void insert(T data) {
      root = insert(root, data);
   }

   private Node<T> insert(Node<T> node, T data) {
      if (node == null)
         return new Node<>(data);

      int cmp = data.compareTo(node.data);
      if (cmp < 0) {
         node.left = insert(node.left, data);
      } else if (cmp > 0) {
         node.right = insert(node.right, data);
      } else { // 중복일때
         return node;
      }
      return rebalance(node);
   }

   // 삭제

   public void delete(T data) {
      root = delete(root, data);
   }

   private Node<T> delete(Node<T> node, T data) {
      if (node == null)
         return null;
      int cmp = data.compareTo(node.data);
      if (cmp < 0) {
         node.left = delete(node.left, data);
      } else if (cmp > 0) {
         node.right = delete(node.right, data);
      } else {
         if (node.left == null)
            return node.right;
         if (node.right == null)
            return node.left;
         Node<T> minNode = findMin(node.right);
         node.data = minNode.data;
         node.right = delete(node.right, minNode.data);
      }
      return rebalance(node);
   }

   private Node<T> findMin(Node<T> node) {
      while (node.left != null) {
         node = node.left;
      }
      return node;
   }

   public boolean contains(T data) {
      Node<T> current = root;
      if (current == null) {
         return false;
      }
      while (current != null) {
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

   public void inorder() {
      inorder(root);
      System.out.println();
   }

   private void inorder(Node<T> node) {
      if (node == null)
         return;
      inorder(node.left);
      System.out.print(node.data + " ");
      inorder(node.right);
   }

   // AVL 균형 조건 : 모든 노드의 bf <=1
   public boolean isBalanced() {
      return isBalanced(root);
   }

   private boolean isBalanced(Node<T> node) {
      if (node == null)
         return true;
      if (Math.abs(balanceFactor(node)) > 1)
         return false;

      return isBalanced(node.right) && isBalanced(node.left);
   }

}
