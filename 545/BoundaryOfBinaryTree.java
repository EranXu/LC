/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode() {}
 *     TreeNode(int val) { this.val = val; }
 *     TreeNode(int val, TreeNode left, TreeNode right) {
 *         this.val = val;
 *         this.left = left;
 *         this.right = right;
 *     }
 * }
 */
class Solution {
    public List<Integer> boundaryOfBinaryTree(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        if (root == null) return res;
        
        // add root 不能忘记根结点
        res.add(root.val);
        
        // left boundary
        List<Integer> left = new ArrayList<>();
        leftBoundary(root.left, left);
        if (!left.isEmpty()) res.addAll(left);
        
        // leaves
        List<Integer> leaves = new ArrayList<>();
        leaves(root.left, leaves);
        leaves(root.right, leaves);
        if (!leaves.isEmpty()) res.addAll(leaves);
        
        // right boundary
        List<Integer> right = new ArrayList<>();
        rightBoundary(root.right, right);
        if(!right.isEmpty()) res.addAll(right);
        return res;
    }
    
    //左右边界都要==null之后再判断是不是叶子，是叶子就return不加了
    
    private void rightBoundary(TreeNode root, List<Integer> right) {
        if (root == null) return;
        // return if it's a leaf
        if (root.left == null && root.right == null) return;
        right.add(0, root.val);//反着从上往下加!!! 本题精髓
        if (root.right != null) rightBoundary(root.right, right);
        else rightBoundary(root.left, right);
    }
    
    private void leaves(TreeNode root, List<Integer> leaves) {
        if (root == null) return;
        if (root.left == null && root.right == null) {
            leaves.add(root.val);
            return;
        }
        leaves(root.left, leaves);//dfs递归
        leaves(root.right, leaves);
    }
    
    private void leftBoundary(TreeNode root, List<Integer> left) {
        if (root == null) return;
        
        // return if it's a leaf
        if (root.left == null && root.right == null) return;
        left.add(root.val);
        if (root.left != null) leftBoundary(root.left, left);
        else leftBoundary(root.right, left);
    }
}
