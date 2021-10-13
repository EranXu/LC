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
    double maxValue;
    
    public double maximumAverageSubtree(TreeNode root) {
        if(root==null) return 0;
        helper(root);
        return maxValue;
    }
    
    private int[] helper(TreeNode root){
        int[] res=new int[2];
        if(root==null) return res;
        
        int[] leftSub=helper(root.left);
        int[] rightSub=helper(root.right);
        
        res[0] = leftSub[0] + rightSub[0] + root.val;
        res[1] = leftSub[1] + rightSub[1] + 1;
        
        maxValue=Math.max(maxValue, (double)res[0]/res[1]);//难点！化成double
        return res;
    }
}
