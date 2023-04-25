package meituan;

import code.hot.TreeNode;

import java.util.*;

public class Code3 {
    class Solution41 {
        public String longestCommonPrefix(String[] strs) {
            String res = strs[0];
            for (int i = 1; i < strs.length; i++) {
                int j = 0;
                for (; j < res.length() && j < strs[i].length(); ++j) {
                    if (res.charAt(j) != strs[i].charAt(j)) break;
                }
                res = res.substring(0, j + 1);
            }
            return res;
        }
    }

    class Solution42 {
        public int removeDuplicates(int[] nums) {
            int cur = 0;
            for (int i = 1; i < nums.length; ) {
                if (nums[i] != nums[cur]) {
                    nums[++cur] = nums[i++];
                } else {
                    i++;
                }
            }
            return cur + 1;
        }
    }

    class Solution43 {
        public int trap(int[] height) {
            int res = 0;
//            上小,下大
            Stack<Integer> stack = new Stack<>();
            for (int i = 0; i < height.length; i++) {
                while (!stack.isEmpty() && height[stack.peek()] < height[i]) {
                    Integer pop = stack.pop();
                    if (!stack.isEmpty())
                        res += (i - stack.peek() - 1) * (Math.min(height[i], height[stack.peek()]) - height[pop]);
                }
                stack.push(i);
            }
            return res;
        }

    }

    class Solution44 {
        public int[] getLeastNumbers(int[] arr, int k) {
            Queue<Integer> queue = new PriorityQueue<>();
            for (int i = 0; i < arr.length; i++) {
                while (queue.size() > k) {
                    queue.poll();
                }
                queue.add(arr[i]);
            }
            int[] res = new int[k];
            for (int i = 0; i < res.length; i++) {
                res[i] = queue.poll();
            }
            return res;
        }

    }

    class Solution45 {
        public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
            List<List<Integer>> res = new ArrayList<>();
            if (root == null) return res;
            Queue<TreeNode> queue = new LinkedList<>();
            queue.add(root);
            int size = queue.size();
            boolean reverse = false;
            while (!queue.isEmpty()) {
                Deque<Integer> inner = new ArrayDeque<>();
                while (size-- > 0) {
                    TreeNode poll = queue.poll();
                    if (poll.left != null) {
                        queue.add(poll.left);
                    }
                    if (poll.right != null) {
                        queue.add(poll.right);
                    }
                    if (reverse) {
                        inner.addFirst(poll.val);
                    } else {
                        inner.addLast(poll.val);
                    }

                }
                reverse = !reverse;
                size = queue.size();
                res.add(new ArrayList<>(inner));
            }
            return res;
        }

    }

    //需要转化为负数处理, 这里还要记得判断符号位应该通过无符号位移拿到最高位的值
    class Solution46 {
        public int reverse(int x) {
            boolean neg = (x >>> 31 & 1) == 1;
            x = neg ? x : -x;
            int m = Integer.MIN_VALUE / 10;
            int n = Integer.MIN_VALUE % 10;
            int res = 0;
            while (x != 0) {
                if (res < m || res == m && x % 10 < n) return 0;
                res = res * 10 + x % 10;
                x = x / 10;

            }
            return res;

        }

    }

    class Solution48 {
        public int majorityElement(int[] nums) {
            int curNum = nums[0];
            int count = 1;
            for (int i = 1; i < nums.length; i++) {
                if (count == 0) {
                    curNum = nums[i];
                    count++;
                } else if (nums[i] == curNum) {
                    count++;
                } else {
                    count--;
                }
            }
            return curNum;
        }

    }

    static  class Solution49 {
        public static String reverseWords(String s) {
            int l = 0;

            int r = s.length()-1;
            while(s.charAt(l)==' '){
                l++;
            }
            while(s.charAt(r)==' '){
                r--;
            }
            Deque<String>deque = new ArrayDeque<>();
            int cur = l;
            while(cur<r){
                if(s.charAt(cur+1)==' '){
                    deque.addFirst(s.substring(l,cur+1));
                    cur++;
                    while(s.charAt(cur)==' '){
                        cur++;
                    }
                    l =cur;
                }
                cur++;
            }
            deque.addFirst(s.substring(l,cur+1));
            return String.join(" ",deque);
        }

    }

    public static void main(String[] args) {
        System.out.println(Solution49.reverseWords("F R  I   E    N     D      S      "));

    }


}
