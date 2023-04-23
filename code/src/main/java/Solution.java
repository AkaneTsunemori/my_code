import code.hot.TreeNode;

import java.util.*;

public class Solution {


    static int res = 0;

    public static int func(TreeNode root) {
        process(root);
        return res;
    }

    public static int process(TreeNode root) {
        if (root == null) return 0;
        int left = process(root.left);
        int right = process(root.right);
        res = Math.max(res, left + right);
        return Math.max(left, right) + 1;
    }

    public static void main(String[] args) {
        Map<Integer,Integer>map =new HashMap<>();
        map.put(1,1);
        map.put(1,1);

    }


}
