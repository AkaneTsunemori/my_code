package code.hot.d;

import code.hot.TreeNode;

public class a_020_236_lowest_common_ancestor {
    /***
     *
     *    从二叉树递归序理解, 递归会重复回到节点三次
     *    对于本题 第一次获取当前节点是否是p节点或者q节点,以及边界条件 如果是直接返回当前节点,
     *    如果不是,递归左子树右子树,在递归结束回到该节点时,判断左右分支返回值是否是p或者q节点,
     *    如果只有一个则将节点继续向上传递,如果两边分别就是p和q那么当前节点即答案,将当前节点继续上传
     */
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        if (root == null || root == q || root == p) {
            return root;
        }
        TreeNode left = lowestCommonAncestor(root.left, p, q);
        TreeNode right = lowestCommonAncestor(root.right, p, q);
//        将节点信息或者节点的父节点往上传递
        if (left != null && right != null) return root;
        if (left == null) return right;
        return left;
    }

    TreeNode res;

    public TreeNode lowestCommonAncestor2(TreeNode root, TreeNode p, TreeNode q) {
        dfs(root, p.val, q.val);
        return res;
    }

    public boolean dfs(TreeNode root, int p, int q) {
        if (root == null) {
            return false;
        }
        boolean left = dfs(root.left, p, q);
        boolean right = dfs(root.right, p, q);
        if (left && right ||
                ((root.val == p || root.val == q) && (left || right))
        ) {
            res = root;
        }
        return root.val == p || root.val == q || left || right;
    }
}
