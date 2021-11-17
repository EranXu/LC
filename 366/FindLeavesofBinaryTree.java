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
    List<List<Integer>> res;
    public List<List<Integer>> findLeaves(TreeNode root) {
        res = new ArrayList<>();
        findLevel(root);
        return res;
    }
    
    private int findLevel(TreeNode root){
        if(root == null) return -1; // null是-1，最底层叶子才是0
        
        //findLevel(root.left);
        //findLevel(root.right);
        
        int level = Math.max(findLevel(root.left),findLevel(root.right)) + 1;
        
        addList(root, level, root.val);
        return level;
    }
    
    private void addList(TreeNode root, int level, int val){
        if(res.size() == level){ // 这句重要
            List<Integer> lst = new ArrayList<>();
            res.add(lst);
        }
        res.get(level).add(val);
        return;
    }
}
