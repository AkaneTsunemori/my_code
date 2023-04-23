package code.hot.f;

import code.hot.TreeNode;

/**
 * 124题
 * dfs返回左右子树单边的最大贡献值
 * 每一个节点执行dfs又会更新全局的最大路径
 */
public class a_30_124_binary_tree_maximum_path_sum {
    public int res = Integer.MIN_VALUE;
    public int maxPathSum(TreeNode root) {
        maxSide(root);
        return res;
    }
    public int maxSide(TreeNode root){
        if(root==null)return 0;
        int left = Math.max(maxSide(root.left),0);
        int right = Math.max(maxSide(root.right),0);
        res = Math.max(res,left+right+root.val);
        // 返回节点最大贡献值,最大贡献值是单边的
        return root.val+ Math.max(left,right);
    }
}
