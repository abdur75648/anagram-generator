/**
 * Node
 */
class BinaryNode{
    String key;
    int height; // Height of the AVL tree considering given Node as root
    BinaryNode left;
    BinaryNode right;
    public BinaryNode(String data){
        key = data;
        left = null;
        right = null;
        height = 0;
    }
}

/**
 * AVlTree
 */

public class AvlTree{

    BinaryNode root;
    public AvlTree(){
        root = null;
    }

    private void inorderTraversal(BinaryNode root){
        if (root==null) {
            return;
        }
        inorderTraversal(root.left);
        System.out.println(root.key);
        inorderTraversal(root.right);
    }

    public void inorderTraversal(){
        inorderTraversal(root);
    }

    public int size(BinaryNode root){
        if (root==null) {
            return 0;
        }
        return 1 + size(root.left) + size(root.right);
    }

    // Helper functions
    private boolean comparator(String s1, String s2){
        return s1.compareTo(s2) < 0;
    }
    private int getHeight(BinaryNode root){
        if (root==null) {
            return -1;
        } else {
            return root.height;
        }
    }
    private BinaryNode clockwise(BinaryNode root){
        BinaryNode var;
        var = root.left;
        root.left = var.right;
        var.right = root;
        root.height = 1 + Math.max(getHeight(root.left),getHeight(root.right));
        var.height = 1 + Math.max(getHeight(var.left),getHeight(var.right));
        return var;
    }
    private BinaryNode anticlockwise(BinaryNode root){
        BinaryNode var;
        var = root;
        root = var.right;
        var.right = root.left;
        root.left = var;
        var.height = 1 + Math.max(getHeight(var.left),getHeight(var.right));
        root.height = 1 + Math.max(getHeight(root.left),getHeight(root.right));
        return root;
    }

    private BinaryNode insertRecursive(BinaryNode root, String key){
        if (root==null) {
            root = new BinaryNode(key);
            return root;
        }
        
        if (this.comparator(key,root.key)) root.left = insertRecursive(root.left, key);
        else if (this.comparator(root.key,key)) root.right= insertRecursive(root.right, key);
        else return root;

        root.height = 1 + Math.max(getHeight(root.left),getHeight(root.right));

        ///// Rotations Now /////
        if (getHeight(root.left) - getHeight(root.right) > 1) { // Left - X case
            if (this.comparator(key,root.left.key)) { // Left - Left case
                // Clockwise Root
                root = clockwise(root);
            }
            else { // Left - Right case
                // Anti-clockwise Root.left
                root.left = anticlockwise(root.left);
                // Clockwise Root
                root = clockwise(root);
            }
        }
        else if (getHeight(root.right) - getHeight(root.left) > 1){ // Right - X case
            if (this.comparator(root.right.key,key)) { // Right - Right case
                // Anti-clockwise Root
                root = anticlockwise(root);
            }
            else { // Right - Left case
                // Clockwise Root.right
                root.right = clockwise(root.right);
                root = anticlockwise(root);
            }
        }

        return root;
    }
    void insertKey(String key){
        root = insertRecursive(root, key);
    }
}
