package code.hot.c;

import code.hot.TreeNode;

import java.util.*;

//层序遍历
public class a011_102_BinaryTreeLevelOrderTraversal {
    public List<List<Integer>> levelOrder(TreeNode root) {
        Deque<TreeNode> deque = new ArrayDeque<>();
        List<List<Integer>>res = new ArrayList<>();
        if(root==null)return res;
        deque.addFirst(root);
        int count = 1;
        while (!deque.isEmpty()){
            List<Integer>subRes= new ArrayList<>();
            while (count-->0){
                TreeNode treeNode = deque.pollLast();
                if(treeNode.left!=null){
                    deque.addFirst(treeNode.left);
                }
                if(treeNode.right!=null){
                    deque.addFirst(treeNode.right);
                }
                subRes.add(treeNode.val);
            }
            count = deque.size();
            res.add(subRes);

        }
        return res;

    }
}
