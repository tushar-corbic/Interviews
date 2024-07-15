897. Increasing Order Search Tree
Solved
Easy
Topics
Companies
Given the root of a binary search tree, rearrange the tree in in-order so that the leftmost node in the tree is now the root of the tree, and every node has no left child and only one right child.

Input: root = [5,3,6,2,4,null,8,1,null,null,null,7,9]
Output: [1,null,2,null,3,null,4,null,5,null,6,null,7,null,8,null,9]

Input: root = [5,1,7]
Output: [1,null,5,null,7]


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
    TreeNode pre= null, head=null;
    public TreeNode increasingBST(TreeNode root) {
        if(root==null)
            return null;
        increasingBST(root.left);
        if(pre!=null){
            root.left = null;
            pre.right =root;
        }
        if(head==null)
            head = root;
        pre = root;
        increasingBST(root.right);
        return head;
    }
}
