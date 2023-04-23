package code.hot.f;

import code.hot.TreeNode;

import java.util.ArrayList;
import java.util.List;

public class a_33_94_binary_tree_inorder_traversal {
    List<Integer>res = new ArrayList<>();
    public List<Integer> inorderTraversal(TreeNode root) {
        process(root);
        return res;
    }
    public void process(TreeNode root){
        if(root==null)return;
        process(root.left);
        res.add(root.val);
        process(root.right);
    }
}
