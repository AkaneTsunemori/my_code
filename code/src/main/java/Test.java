import code.hot.ListNode;
import code.hot.TreeNode;

import java.util.*;
import java.util.concurrent.locks.ReentrantLock;

public class Test {
    private static ThreadLocal<Map<String, Integer>> threadLocal = new ThreadLocal<>();
    static ReentrantLock reentrantLock = new ReentrantLock();
    ListNode pre;


    public TreeNode buildTree(int[] preorder, int[] inorder) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < inorder.length; ++i) {
            map.put(inorder[i], i);
        }
        return buildTree(preorder, 0, preorder.length - 1, 0, preorder.length - 1, map);
    }

    public TreeNode buildTree(int[] preorder, int preLeft, int preRight, int inLeft, int inright, Map<Integer, Integer> map) {
        if (preLeft > preRight) {
            return null;
        }
        int rootIndex = map.get(preorder[preLeft]);
        TreeNode root = new TreeNode(preorder[preLeft]);
        int leftSubTreeSize = rootIndex - inLeft;
        root.left = buildTree(preorder, preLeft + 1, preLeft + leftSubTreeSize, inLeft, rootIndex - 1, map);
        root.right = buildTree(preorder, preLeft + leftSubTreeSize + 1, preRight, rootIndex + 1, inright, map);
        return root;
    }

    public int longestOnes(int[] nums, int k) {
        int left = 0;
        int leftSum = 0, rightSum = 0;
        int res = 0;
        for (int right = 0; right < nums.length; ++right) {
            rightSum += 1 - nums[right];
            while (rightSum - leftSum > k) {
                leftSum += nums[left++];
            }
            res = Math.max(res, right - left + 1);
        }
        return res;
    }

    public int lengthOfLIS(int[] nums) {

        int[] dp = new int[nums.length];
        int res = 1;
        dp[0] = 1;
        for (int i = 0; i < nums.length; i++) {
            for (int j = i; j >= 0; ++j) {
                dp[i] = 1;
                if (nums[j] < nums[i]) {
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                }
                res = Math.max(res, dp[i]);
            }
        }
        return res;
    }

    public int maximumProduct(int[] nums) {
        Arrays.sort(nums);
        int len = nums.length;
        int resRc1 = nums[0] * nums[1] * nums[2];
        int resRc2 = nums[len - 1] * nums[len - 2] * nums[len - 3];
        int resRc3 = nums[0] * nums[1] * nums[len - 1];
        int resRc4 = nums[0] * nums[len - 1] * nums[len - 2];
        return Math.max(Math.max(resRc1, resRc2), Math.max(resRc3, resRc4));
    }

    public int maxSubArray(int[] nums) {
        int temp = nums[0];
        int res = nums[0];
        for (int i = 1; i < nums.length; i++) {
            temp = Math.max(temp + nums[i], nums[i]);
            res = Math.max(temp, res);
        }
        return res;
    }

    public int[][] generateMatrix(int n) {
        int[][] res = new int[n][n];
        int rowTop = 0, rowButton = n - 1;
        int colLeft = 0, colRight = n - 1;
        int num = 1;
        while (rowTop != rowButton) {
            for (int i = colLeft; i <= colRight; ++i) {
                res[rowTop][i] = num++;
            }
            for (int i = rowTop + 1; i <= rowButton; ++i) {
                res[i][colRight] = num++;
            }
            for (int i = colRight - 1; i >= colLeft; --i) {
                res[rowButton][i] = num++;
            }
            for (int i = rowButton - 1; i >= rowTop + 1; --i) {
                res[i][colLeft] = num++;
            }
            rowTop++;
            rowButton--;
            colLeft++;
            colRight--;
        }
        return res;

    }


    public String decodeString(String s) {
        Stack<Integer> stackNum = new Stack<>();
        Stack<String> stackStr = new Stack<>();
        char[] chars = s.toCharArray();
        for (int i = 0; i < chars.length; ) {
            if (chars[i] - '0' <= 9 && 0 <= chars[i] - '0') {
                int num = s.charAt(i) - '0';
                i++;
                while (s.charAt(i) - '0' >= 0 && s.charAt(i) <= 9) {
                    num = num * 10 + (s.charAt(i) - '0');
                    i++;
                }
                stackNum.push(num);
                i++;
            }
            if (chars[i] - 'a' < 26 && 0 <= chars[i] - 'a') {
                int edge = i;
                while (s.charAt(i) - 'a' < 26 && 0 <= s.charAt(i) - 'a') {
                    i++;
                }
                stackStr.push(s.substring(edge, i));
            }
            if (chars[i] == ']') {
                String str = stackStr.pop();
                StringBuilder sb = new StringBuilder();
                if (!stackNum.isEmpty()) {
                    int num = stackNum.pop();
                    while (num-- > 0) {
                        sb.append(str);
                    }
                } else {
                    sb.append(str);
                }
                stackStr.push(sb.toString());
                i++;
            }
        }
        while (stackStr.size() != 1) {
            String pop1 = stackStr.pop();
            String pop2 = stackStr.pop();
            stackStr.push(pop2 + pop1);
        }
        return stackStr.pop();
    }

    public int fourSumCount(int[] nums1, int[] nums2, int[] nums3, int[] nums4) {
        int res = 0;
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums1.length; i++) {
            for (int j = 0; j < nums2.length; j++) {
                int num = nums1[i] + nums2[j];
                map.put(num, map.getOrDefault(-num, 0) + 1);
            }
        }
        for (int i = 0; i < nums3.length; i++) {
            for (int j = 0; j < nums4.length; j++) {
                res += map.getOrDefault(nums3[i] + nums4[j], 0);
            }
        }
        return res;
    }

    List<List<Integer>> combineRes = new ArrayList<>();

    public List<List<Integer>> combine(int n, int k) {
        processCombine(n, k, 1, new ArrayList<>());
        return combineRes;
    }

    public void processCombine(int n, int k, int index, List<Integer> path) {
        if (path.size() == k) {
            combineRes.add(new ArrayList<>(path));
        } else {
            for (int i = index; i <= n; ++i) {
                if (path.size() > k) continue;
                path.add(i);
                processCombine(n, k, i + 1, path);
                path.remove(path.size() - 1);
            }
        }
    }


    public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
        List<List<Integer>> res = new ArrayList<>();
        Deque<TreeNode> deque = new ArrayDeque<>();
        deque.addFirst(root);
        int size = deque.size();
        boolean reverse = false;
        while (!deque.isEmpty()) {
            Deque<Integer> resRc = new ArrayDeque<>();
            while (size-- > 0) {
                TreeNode treeNode = deque.pollLast();
                if (reverse) {
                    resRc.addLast(treeNode.val);
                } else {
                    resRc.addFirst(treeNode.val);
                }
                if (treeNode.left != null) {
                    deque.addFirst(treeNode.left);
                }
                if (treeNode.right != null) {
                    deque.addFirst(treeNode.right);
                }
            }
            reverse = !reverse;
            size = deque.size();
            res.add(new ArrayList<>(resRc));
        }
        return res;

    }

    public List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> res = new ArrayList<>();
        Deque<TreeNode> deque = new ArrayDeque<>();
        deque.addFirst(root);
        int size = deque.size();
        while (!deque.isEmpty()) {
            List<Integer> resRc = new ArrayList<>();
            while (size-- > 0) {
                TreeNode treeNode = deque.pollLast();
                resRc.add(treeNode.val);
                if (treeNode.left != null) {
                    deque.addFirst(treeNode.left);
                }
                if (treeNode.right != null) {
                    deque.addFirst(treeNode.right);
                }
            }
            size = deque.size();
            res.add(resRc);
        }
        return res;
    }

    public int[] twoSum(int[] nums, int target) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            if (map.containsKey(target - nums[i])) {
                return new int[]{map.get(target - nums[i]), i};
            }
            map.put(nums[i], i);
        }
        return new int[]{-1, -1};
    }

    public ListNode mergeTwoLists(ListNode list1, ListNode list2) {
        if (list1 == null) return list2;
        if (list2 == null) return list1;
        if (list1.val < list2.val) {
            list1.next = mergeTwoLists(list1.next, list2);
            return list1;
        } else {
            list2.next = mergeTwoLists(list1, list2.next);
            return list2;
        }
    }


    public int findKthLargest(int[] nums, int k) {
        return processFindKthLargest(nums, 0, nums.length - 1, k - 1);

    }

    public int[] quickSortArray(int[] nums) {
        quickSortProcess(nums, 0, nums.length - 1);
        return nums;
    }

    public void quickSortProcess(int[] nums, int l, int r) {
        if (l >= r) return;
        int index = quickPartition(nums, l, r);
        quickPartition(nums, l, index - 1);
        quickPartition(nums, index + 1, r);
    }

    public int quickPartition(int[] nums, int l, int r) {
        int target = l + (int) (Math.random() * (r - l));
        swap(nums, target, r);
        int edge = l - 1;
        while (l < r) {
            if (nums[l] < nums[r]) {
                swap(nums, l, ++edge);
            }
            l++;
        }
        swap(nums, ++edge, r);
        return edge;
    }

    public static List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        Arrays.sort(nums);
        for (int i = 0; i < nums.length - 2; ++i) {
            if (i > 0 && nums[i] == nums[i - 1]) continue;
            int target = -nums[i];
            for (int l = i + 1, r = nums.length - 1; l < r; ) {
                if (nums[l] + nums[r] == target) {
                    List<Integer> resRc = new ArrayList<>();
                    resRc.add(nums[i]);
                    resRc.add(nums[l]);
                    resRc.add(nums[r]);
                    res.add(resRc);
                    l++;
                    while (nums[l] == nums[l - 1]) l++;
                } else if (nums[l] + nums[r] < 0) {
                    l++;
                } else {
                    r--;
                }
            }
        }
        return res;
    }

    public ListNode reverseKGroup(ListNode head, int k) {
        ListNode pre = new ListNode();
        ListNode left = head;
        ListNode right = null;
        ListNode tail;
        pre.next = head;
        ListNode cur = head;

        while (cur != null) {
            for (int i = 1; i < k; ++i) {
                if (cur.next != null) {
                    cur = cur.next;
                }
            }
            right = cur;
            tail = right.next;
            ListNode[] listNodes = reverseSub(left, right);
            listNodes[0].next = pre;
            if (tail != null) {
                tail.next = listNodes[1];
                left = tail;
            }
            pre = listNodes[1];
        }
        return pre;
    }

    public ListNode[] reverseSub(ListNode start, ListNode end) {
        ListNode pre = null;
        ListNode resE = start;
        while (start != end) {
            ListNode next = start.next;
            start.next = pre;
            pre = start;
            start = next;
        }
        return new ListNode[]{start, resE};
    }


    public int processFindKthLargest(int[] nums, int l, int r, int k) {
        int index = processPartition(nums, l, r);
        if (index == k) return nums[index];
        if (index < k) {
            return processFindKthLargest(nums, index + 1, r, k);
        } else {
            return processFindKthLargest(nums, l, index - 1, k);
        }
    }

    public int processPartition(int[] nums, int l, int r) {
        int edge = l - 1;
        while (l < r) {
            if (nums[l] > nums[r]) {
                swap(nums, ++edge, l);
            }
            l++;
        }
        swap(nums, ++edge, r);
        return edge;

    }

    public void swap(int[] nums, int l, int r) {
        int temp = nums[l];
        nums[l] = nums[r];
        nums[r] = temp;
    }


    public ListNode reverseList(ListNode head) {
        ListNode pre = null;
        while (head != null) {
            ListNode next = head.next;
            head.next = pre;
            pre = head;
            head = next;
        }
        return pre;
    }

    public static int lengthOfLongestSubstring(String s) {
        char[] chars = s.toCharArray();
        int[] map = new int[128];
        int res = Integer.MIN_VALUE;
        int edge = -1;
        Arrays.fill(map, -1);
        for (int i = 0; i < chars.length; i++) {
            edge = Math.max(map[chars[i]], edge);
            res = Math.max(res, i - edge);
            map[chars[i]] = i;
        }
        return res;

    }

    //"aabaab"
    public void func(int count1, int count2) {
        process1(count1, count2, "");
        process2(count2, count1, "");

    }

    public void process1(int rest1, int rest2, String path) {
        if (rest1 == 0 && rest2 == 0) {
            System.out.println(path);
            return;
        }
        if (rest1 < 0 || rest2 < 0) {
            return;
        }
        if (rest2 == 0 && rest1 <= 2) {
            if (rest1 == 1)
                process1(0, rest2, path + "a");
            if (rest1 == 2)
                process1(0, rest2, path + "aa");
        }
        process1(rest1 - 1, rest2 - 1, path + "ab");
        process1(rest1 - 2, rest2 - 1, path + "aab");
        process1(rest1 - 1, rest2 - 2, path + "abb");
        process1(rest1 - 2, rest2 - 2, path + "aabb");
    }

    public void process2(int rest1, int rest2, String path) {
        if (rest1 == 0 && rest2 == 0) {
            System.out.println(path);
            return;
        }
        if (rest1 < 0 || rest2 < 0) {
            return;
        }
        if (rest2 == 0 && rest1 <= 2) {
            if (rest1 == 1)
                process1(0, rest2, path + "b");
            if (rest1 == 2)
                process1(0, rest2, path + "bb");
        }
        process2(rest1 - 1, rest2 - 1, path + "ba");
        process2(rest1 - 2, rest2 - 1, path + "bba");
        process2(rest1 - 1, rest2 - 2, path + "baa");
        process2(rest1 - 2, rest2 - 2, path + "bbaa");
    }

    public String removeKdigits2(String num, int k) {
        Stack<Integer> stack = new Stack<>();
        for (int i = 0; i < num.length(); ++i) {
            while (k != 0 && !stack.isEmpty() && stack.peek() > num.charAt(i)) {
                stack.pop();
                k--;
            }
            stack.push(Integer.valueOf(num.charAt(i)));
        }
        if (stack.isEmpty()) {
            return "0";
        }
        StringBuilder sb = new StringBuilder();
        while (!stack.isEmpty()) {
            sb.append(stack.pop());
        }
        return String.valueOf(Integer.parseInt(sb.toString()));
    }

    class Solution {
        public int reversePairs(int[] nums) {
            if (nums == null || nums.length < 2) {
                return 0;
            }
            return process(nums, 0, nums.length - 1);
        }

        // arr[L..R]既要排好序，也要求逆序对数量返回
        // 所有merge时，产生的逆序对数量，累加，返回
        // 左 排序 merge并产生逆序对数量
        // 右 排序 merge并产生逆序对数量
        public int process(int[] arr, int l, int r) {
            if (l == r) {
                return 0;
            }
            // l < r
            int mid = l + ((r - l) >> 1);
            return process(arr, l, mid) + process(arr, mid + 1, r) + merge(arr, l, mid, r);
        }

        public int merge(int[] arr, int L, int m, int R) {
            int[] help = new int[R - L + 1];
            int i = help.length - 1;
            int p1 = m;
            int p2 = R;
            int res = 0;
            while (p1 >= L && p2 > m) {
                res += arr[p1] > arr[p2] ? (p2 - m) : 0;
                help[i--] = arr[p1] > arr[p2] ? arr[p1--] : arr[p2--];
            }
            while (p1 >= L) {
                help[i--] = arr[p1--];
            }
            while (p2 > m) {
                help[i--] = arr[p2--];
            }
            for (i = 0; i < help.length; i++) {
                arr[L + i] = help[i];
            }
            return res;
        }
    }


    public static void main(String[] args) {

        // 创建一个TreeMap来存储学生的分数，键是学生的姓名，值是分数
        TreeMap<String, Integer> studentScores = new TreeMap<>();

        // 添加学生分数
        studentScores.put("Alice", 95);
        studentScores.put("Charlie", 92);
        studentScores.put("David", 78);
        studentScores.put("Bob", 88);

        studentScores.put("Eve", 100);

        // 输出整个TreeMap，按键的字母顺序排序
        System.out.println("All Students and Scores:");
        for (Map.Entry<String, Integer> entry : studentScores.entrySet()) {
            System.out.println(entry.getKey() + ": " + entry.getValue());
        }

        // 获取某个学生的分数
        String studentName = "Bob";
        if (studentScores.containsKey(studentName)) {
            int score = studentScores.get(studentName);
            System.out.println(studentName + "'s Score: " + score);
        } else {
            System.out.println("Student not found.");
        }

        // 获取分数在一定范围内的学生
        System.out.println("Students with Scores between 90 and 95:");
        SortedMap<String, Integer> studentsInRange = studentScores.subMap("Alice", "David");
        for (Map.Entry<String, Integer> entry : studentsInRange.entrySet()) {
            System.out.println(entry.getKey() + ": " + entry.getValue());
        }
    }

    public int jump(int[] nums) {
        int[] dp = new int[nums.length];
        dp[0] = nums[0];
        int max = dp[0];
        for (int i = 0; i < nums.length; i++) {
            dp[i] = max >= i ? nums[i] + i : 0;
            max = Math.max(max, nums[i] + 1);
        }
        int target = nums.length - 1;
        int count = 0;
        while (target != 0) {
            for (int i = 0; i < dp.length; i++) {
                if (dp[i] >= target) {
                    count++;
                    target = i;
                    break;
                }
            }
        }
        return count;

    }


    public int searchEqOrSmall(int[] nums, int target) {
        int l = 0;
        int r = nums.length - 1;
        while (l <= r) {
            int mid = l + (r - l) / 2;
            if (nums[mid] == target) {
                return mid;
            }
            if (nums[mid] < target) {
                l = mid + 1;
            } else {
                r = mid - 1;
            }
        }
        return r;
    }

    public int searchEqOrBig(int[] nums, int target) {
        int l = 0;
        int r = nums.length - 1;
        while (l <= r) {
            int mid = l + (r - l) / 2;
            if (nums[mid] == target) {
                return mid;
            }
            if (nums[mid] < target) {
                l = mid + 1;
            } else {
                r = mid - 1;
            }
        }
        return r;
    }

    static class Node {
        int val;
        Node next;
        Node random;

        public Node(int val) {
            this.val = val;
            this.next = null;
            this.random = null;
        }
    }

    public Node copyRandomList(Node head) {
        Node cur = head;
        while (cur != null) {
            Node next = cur.next;
            cur.next = new Node(cur.val);
            cur.next.next = next;
            cur = next;
        }
        cur = head;
        while (cur != null) {
            Node fake = cur.next;
            fake.random = cur.random == null ? null : cur.random.next;
            cur = cur.next.next;
        }
        cur = head;
        Node rst = head.next;
        while (cur != null) {
            Node fake = cur.next;
            fake.next = cur.next == null ? null : fake.next.next;
            cur.next = cur.next.next;
            cur = fake.next;
        }
        return rst;
    }

    public String reverseWords(String s) {
        StringBuilder sb = new StringBuilder();
        int len = s.length();
        int l = len - 1;
        int r = len - 1;
        while (r > 0) {
            if (s.charAt(r) != ' ') {
                l = r;
                while (l > 0 && s.charAt(l - 1) != ' ') {
                    l--;
                }
                sb.append(s.substring(l, r + 1)).append(' ');
                r = l;
            } else {
                r--;
            }
        }
        return sb.substring(0, sb.length() - 1).toString();
    }

    public String minWindow(String s, String t) {
        Map<Character, Integer> srcMap = new HashMap<>();
        for (int i = 0; i < t.length(); i++) {
            srcMap.put(t.charAt(i), srcMap.getOrDefault(t.charAt(i), 0) + 1);
        }
        int l = 0, r = 0;
        int len = Integer.MAX_VALUE;
        String rst = "";
        Map<Character, Integer> targetMap = new HashMap<>();
        while (r < s.length()) {
            while (r < s.length() && !valid(srcMap, targetMap)) {
                char c = s.charAt(r);
                if (srcMap.containsKey(c)) {
                    targetMap.put(c, targetMap.getOrDefault(c, 0) + 1);
                }
                r++;
            }
            while (valid(srcMap, targetMap) && l <= r) {
                if (len > r - l) {
                    len = r - l;
                    rst = s.substring(l, r);
                }
                char c = s.charAt(l);
                if (srcMap.containsKey(c)) {
                    int val = targetMap.get(c);
                    if (val > 1) {
                        targetMap.put(c, val - 1);
                    } else {
                        targetMap.remove(c);
                    }
                }
                l++;
            }
        }
        return rst;
    }

    public boolean valid(Map<Character, Integer> srcMap, Map<Character, Integer> targetMap) {
        for (Map.Entry<Character, Integer> entry : srcMap.entrySet()) {
            if (targetMap.get(entry.getKey()) == null || targetMap.get(entry.getKey()) < entry.getValue()) {
                return false;
            }
        }
        return true;
    }

    public ListNode reverseList2(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        } else {
            ListNode next = head.next;
            //漏掉这一步会导致前两个节点成环
            head.next = null;
            //递归获取最后一个节点
            ListNode last = reverseList2(next);
            //反转
            next.next = head;
            return last;
        }

    }

    public int mySqrt(int x) {
        int l = 0;
        int r = x;
        int rst = 0;
        while (l <= r) {
            int mid = l + (r - l) / 2;
            if ((long) (mid * mid) <= x) {
                rst = mid;
                l = mid + 1;
            } else {
                r = mid - 1;
            }
        }
        return rst;
    }


    public boolean processPalindrome(ListNode head) {
        if (head.next != null) {
            return processPalindrome(head.next);
        } else {
            if (head.val != pre.val) {
                return false;
            }
            pre = pre.next;
        }
        return true;
    }


    public boolean isPalindrome(ListNode head) {
        pre = head;
        return processPalindrome(head);
    }


//
//    public boolean isPalindrome(ListNode head) {
//        pre = head;
//        return process(head);
//    }
//
//    public boolean process(ListNode head) {
//        if (head != null) {
//            if (!process(head.next)) {
//                return false;
//            }
//            if (head.val != pre.val) {
//                return false;
//            }
//            pre = pre.next;
//
//        }
//        return true;
//    }

}
