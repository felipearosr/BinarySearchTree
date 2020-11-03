import java.util.List;
import java.util.Queue;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.LinkedList;

class Node {
    int key;
    Node left, right;

    Node() {
    }

    Node(int key) {
        this.key = key;
    }

    Node(int key, Node left, Node right) {
        this.key = key;
        this.left = left;
        this.right = right;
    }
}

class BinarySearchTree {

    static Node root;

    void insert(int key) {
        root = insertRecursive(root, key);
    }

    Node insertRecursive(Node root, int key) {
        if (root == null) {
            root = new Node(key);
            return root;
        }
        if (key < root.key)
            root.left = insertRecursive(root.left, key);
        else if (key > root.key)
            root.right = insertRecursive(root.right, key);
        return root;
    }

    boolean search(int key) {
        root = searchRecursive(root, key);
        if (root != null)
            return true;
        else
            return false;
    }

    Node searchRecursive(Node root, int key) {
        if (root == null || root.key == key)
            return root;
        if (root.key > key)
            return searchRecursive(root.left, key);
        return searchRecursive(root.right, key);
    }

    void preOrderTraversal() {
        preOrderTraversalRecursive(root);
    }

    void preOrderTraversalRecursive(Node node) {
        if (node == null)
            return;
        System.out.print(node.key + " ");
        preOrderTraversalRecursive(node.left);
        preOrderTraversalRecursive(node.right);
    }

    void inOrderTraversal() {
        inOrderTraversalRecursive(root);
    }

    void inOrderTraversalRecursive(Node node) {
        if (node == null)
            return;
        inOrderTraversalRecursive(node.left);
        System.out.print(node.key + " ");
        inOrderTraversalRecursive(node.right);
    }

    void postOrderTraversal() {
        postOrderTraversalRecursive(root);
    }

    void postOrderTraversalRecursive(Node node) {
        if (node == null)
            return;
        postOrderTraversalRecursive(node.left);
        postOrderTraversalRecursive(node.right);
        System.out.print(node.key + " ");
    }

    void deleteKey(int key) {
        root = deleteKeyRecursive(root, key);
    }

    Node deleteKeyRecursive(Node root, int key) {
        if (root == null)
            return root;
        if (key < root.key)
            root.left = deleteKeyRecursive(root.left, key);
        else if (key > root.key)
            root.right = deleteKeyRecursive(root.right, key);
        else {
            if (root.left == null)
                return root.right;
            else if (root.right == null)
                return root.left;
            root.key = minValue(root.right);
            root.right = deleteKeyRecursive(root.right, root.key);
        }
        return root;
    }

    int minValue(Node root) {
        int minval = root.key;
        while (root.left != null) {
            minval = root.left.key;
            root = root.left;
        }
        return minval;
    }

    void levelO() {
        levelOrder(root);
    }

    public List<List<Integer>> levelOrder(Node root) {
        List<List<Integer>> res = new ArrayList<>();  
        if (root == null)
            return res;
        Queue<Node> queue = new LinkedList<>();  
        queue.add(root);
        while (!queue.isEmpty()) {
            List<Integer> level = new ArrayList<>();  
            int cnt = queue.size();  
            for (int i = 0; i < cnt; i++) {
                Node node = queue.poll();
                level.add(node.key);
                if (node.left != null)
                    queue.add(node.left);
                if (node.right != null)
                    queue.add(node.right);
            }  
            res.add(level);   
        }  
        return res;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        BinarySearchTree tree = new BinarySearchTree();
        for (int i = 0; i < n; i++)
            tree.insert(scanner.nextInt());
        tree.preOrderTraversal();
        tree.inOrderTraversal();
        tree.postOrderTraversal();
        System.out.println(levelO());
        scanner.close();
    }
}