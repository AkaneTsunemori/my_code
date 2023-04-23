package code.hot.d;

import code.hot.TreeNode;

import java.util.*;

/**
 * 需要用两个队列, 第一个用于二叉树的层序遍历,
 * 第二个用于存每层的结果,按照时候reverse选择头部插入还是尾部插入
 */

public class a_019_binary_tree_zigzag {
    public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
        List<List<Integer>> res = new ArrayList<>();
        Queue<TreeNode> queue = new LinkedList<>();
        if(root==null)return res;
        queue.add(root);
        boolean rightFirst = true;
        int size = queue.size();
        while(!queue.isEmpty()){
            Deque<Integer> tempRes = new ArrayDeque<>();
            while (size-->0){
                TreeNode poll = queue.poll();
                if(poll.left!=null){
                    queue.add(poll.left);
                }
                if(poll.right!=null){
                    queue.add(poll.right);
                }
                if(rightFirst){
                    tempRes.addLast(poll.val);
                }else {
                    tempRes.addFirst(poll.val);
                }
            }
            rightFirst = !rightFirst;
            res.add(new ArrayList<>(tempRes));
            size = queue.size();
        }
        return res;
    }
}
