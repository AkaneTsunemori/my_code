package code.hot.f;

import code.hot.TreeNode;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

public class a_38_199_right_side_view {
    public List<Integer> rightSideView(TreeNode root){
        Deque<TreeNode> deque = new ArrayDeque<>();
        List<Integer>res = new ArrayList<>();
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
            res.add(subRes.get(subRes.size()-1));

        }
        return res;
    }
}
