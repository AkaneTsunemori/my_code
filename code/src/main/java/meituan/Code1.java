package meituan;

import code.hot.ListNode;
import code.hot.TreeNode;

import java.util.*;

public class Code1 {
    static class Solution1 {
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
    }

    //注意一下partation的过程
    class Solution2 {

        public int[] sortArray(int[] nums) {
            sort(nums, 0, nums.length - 1);
            return nums;
        }

        public void sort(int[] nums, int l, int r) {
            if (l >= r) {
                return;
            }
            int[] edge = partation(nums, l, r);
            sort(nums, l, edge[0]);
            sort(nums, edge[1], r);

        }

        private int[] partation(int[] nums, int l, int r) {
            int select = nums[l + (r - l) / 2];
            int cur = l;
            int leftEdge = l - 1;
            int rightEdge = r + 1;
            while (cur < rightEdge) {
                if (nums[cur] < select) {
                    swap(nums, cur++, ++leftEdge);
                } else if (nums[cur] > select) {
                    swap(nums, cur, --rightEdge);
                } else {
                    cur++;
                }
            }
            return new int[]{leftEdge, rightEdge};
        }

        private void swap(int[] nums, int m, int n) {
            int temp = nums[m];
            nums[m] = nums[n];
            nums[n] = temp;
        }

    }

    class Solution3 {

        public void merge(int[] nums1, int m, int[] nums2, int n) {
            int sumNum = nums1.length;
            while (m != 0 && n != 0) {
                if (nums1[m - 1] > nums2[n - 1]) {
                    nums1[sumNum - 1] = nums1[m - 1];
                    m--;
                } else {
                    nums1[sumNum - 1] = nums2[n - 1];
                    n--;
                }
                sumNum--;
            }
            while (n != 0) {
                nums1[--sumNum] = nums2[--n];
            }
        }

    }

    class Solution4 {
        public int findKthLargest(int[] nums, int k) {
            int KthSmallest = nums.length - k + 1;
            return process(nums, KthSmallest, 0, nums.length - 1);
        }

        public int process(int[] nums, int k, int l, int r) {
            int select = partition(nums, l, r);
            if (select == k) {
                return nums[select];
            } else if (select < k) {
                return process(nums, k, select + 1, r);
            } else {
                return process(nums, k, l, select - 1);
            }
        }

        private int partition(int[] nums, int l, int r) {
            int leftEdge = l - 1;
            while (l < r) {
                if (nums[l] < nums[r]) {
                    swap(nums, l, ++leftEdge);
                }
                l++;
            }
            swap(nums, ++leftEdge, r);
            return leftEdge;
        }

        private void swap(int[] nums, int m, int n) {
            int temp = nums[m];
            nums[m] = nums[n];
            nums[n] = temp;
        }
    }

    class Solution5 {
        public boolean hasCycle(ListNode head) {
            if (head == null || head.next == null || head.next.next == null) {
                return false;
            }
            ListNode slow = head.next;
            ListNode fast = head.next.next;

            while (fast.next != null && fast.next.next != null) {
                if (slow == fast) return true;
                slow = slow.next;
                fast = fast.next.next;
            }
            return false;
        }
    }

    class Solution6 {
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
    }

    //对于链表的问题,需要注意的点是截断问题
    class Solution7 {
        public ListNode reverseBetween(ListNode head, int left, int right) {
            int reverseNum = right - left + 1;
            ListNode preStart = new ListNode();
            ListNode hair = preStart;
            preStart.next = head;
            while (--left > 0) {
                preStart = preStart.next;
            }
            ListNode cur = preStart.next;
            preStart.next = null;

            ListNode end = cur;
            while (--reverseNum > 0) {
                end = end.next;
            }
            ListNode endNext = end.next;
            end.next = null;

            ListNode finalEnd = cur;
            ListNode pre = new ListNode();
            while (cur != null) {
                ListNode next = cur.next;
                cur.next = pre;
                pre = cur;
                cur = next;
            }
            preStart.next = pre;
            finalEnd.next = endNext;
            return hair.next;
        }
    }

    class Solution8 {
        public List<List<Integer>> levelOrder(TreeNode root) {
            List<List<Integer>> res = new ArrayList<>();
            if (root == null) {
                return res;
            }
            Deque<TreeNode> deque = new ArrayDeque<>();
            deque.addFirst(root);
            int count = 1;
            while (!deque.isEmpty()) {
                List<Integer> inner = new ArrayList<>();
                while (count-- > 0) {
                    TreeNode cur = deque.pollLast();
                    inner.add(cur.val);
                    if (cur.left != null) {
                        deque.addFirst(cur.left);
                    }
                    if (cur.right != null) {
                        deque.addFirst(cur.right);
                    }
                }
                count = deque.size();
                res.add(inner);
            }

            return res;
        }

    }

    class Solution9 {
        public int lengthOfLongestSubstring(String s) {
            char[] chars = s.toCharArray();
            int[] map = new int[128];
            Arrays.fill(map, -1);
            int res = 0;
            int leftEdge = -1;
            for (int i = 0; i < chars.length; i++) {
                leftEdge = Math.max(leftEdge, map[chars[i]]);
                res = Math.max(res, i - leftEdge);
                map[chars[i]] = i;
            }
            return res;
        }
    }

    class Solution10 {
        public ListNode deleteDuplicates(ListNode head) {
            ListNode pre = new ListNode();
            ListNode hair = pre;
            while (head != null) {
                ListNode[] listNodes = nextPair(head);
                if (listNodes[0] == listNodes[1]) {
                    pre.next = head;
                    pre = head;
                    head = head.next;
                } else {
                    head = listNodes[1].next;
                }
            }
            pre.next = head;
            return hair.next;

        }

        public ListNode[] nextPair(ListNode cur) {
            ListNode first = cur;
            while (cur.next != null && cur.val == cur.next.val) {
                cur = cur.next;
            }
            return new ListNode[]{first, cur};
        }
    }

    class Solution11 {
        public ListNode detectCycle(ListNode head) {
            if (head == null || head.next == null || head.next.next == null) {
                return null;
            }
            ListNode slow = head.next;
            ListNode fast = head.next.next;
            while (fast.next.next != null) {
                if (slow != fast) {
                    slow = slow.next;
                    fast = fast.next.next;
                } else {
                    fast = head;
                    while (slow != fast) {
                        slow = slow.next;
                        fast = fast.next;
                    }
                    return fast;
                }

            }
            return null;
        }
    }

    class Solution12 {
        public void reorderList(ListNode head) {
            if (head == null) return;
            ListNode slow = head;
            ListNode fast = head;
            while (fast.next != null && fast.next.next != null) {
                slow = slow.next;
                fast = fast.next.next;
            }
            ListNode nextStart = slow.next;
            slow.next = null;
            ListNode pre = new ListNode();
            while (nextStart != null) {
                ListNode next = nextStart.next;
                nextStart.next = pre;
                pre = nextStart;
                nextStart = next;
            }

            ListNode cur = head;
            while (cur != null && pre != null) {
                ListNode fNext = cur.next;
                ListNode sNext = pre.next;
                cur.next = pre;
                pre.next = fNext;
                cur = fNext;
                pre = sNext;
            }
        }
    }

    class LRUCache {
        private int capacity;
        private int curSize;
        private Map<Integer, Node> map;
        Node hair;
        Node tail;

        class Node {
            int key;
            int val;
            Node next;
            Node pre;

            public Node(int key, int val) {
                this.key = key;
                this.val = val;
            }

            public Node() {
            }
        }

        public LRUCache(int capacity) {
            this.capacity = capacity;
            curSize = 0;
            map = new HashMap<>();
            hair = new Node();
            tail = new Node();
            hair.next = tail;
            tail.pre = hair;
        }

        public int get(int key) {
            if (!map.containsKey(key)) {
                return -1;
            } else {
                Node node = map.get(key);
                removeNode(node);
                addFirst(node);
                return node.val;
            }
        }

        public void put(int key, int value) {
            if (map.containsKey(key)) {
                Node node = map.get(key);
                node.val = value;
                removeNode(node);
                addFirst(node);
            } else {
                Node node = new Node(key, value);
                map.put(key, node);
                addFirst(node);
                curSize++;
                if (curSize > capacity) {
                    Node remove = tail.pre;
                    removeNode(remove);
                    map.remove(remove.key);
                }
            }
        }

        private void addFirst(Node node) {
            Node next = hair.next;
            hair.next = node;
            node.pre = hair;
            node.next = next;
            next.pre = node;
        }

        private void removeNode(Node node) {
            Node pre = node.pre;
            Node next = node.next;
            pre.next = next;
            next.pre = pre;
        }


    }

    class Solution14 {
        public int maxSubArray(int[] nums) {
            int max = nums[0];
            int res = nums[0];
            for (int i = 1; i < nums.length; i++) {
                max = Math.max(max + nums[i], nums[i]);
                res = Math.max(res, max);
            }
            return res;
        }
    }

    class Solution15 {
        public int[] twoSum(int[] nums, int target) {
            Map<Integer, Integer> map = new HashMap<>();
            for (int i = 0; i < nums.length; i++) {
                if (map.containsKey(target - nums[i])) {
                    return new int[]{map.get(target - nums[i]), i};
                } else {
                    map.put(nums[i], i);
                }
            }
            return new int[]{-1, -1};
        }
    }

    class Solution16 {
        public int search(int[] nums, int target) {
            int l = 0;
            int r = nums.length - 1;
            while (l <= r) {
                int mid = l + ((r - l) >> 1);
                if (nums[mid] == target) {
                    return mid;
                } else if (nums[mid] < target) {
                    l = mid + 1;
                } else {
                    r = mid - 1;
                }
            }
            return -1;
        }
    }

    class Solution17 {
        public boolean isValid(String s) {
            Map<Character, Character> map = new HashMap<>();
            map.put('}', '{');
            map.put(']', '[');
            map.put(')', '(');

            char[] chars = s.toCharArray();
            Stack<Character> stack = new Stack<>();
            for (char aChar : chars) {
                if (stack.isEmpty() || stack.peek() != map.get(aChar)) {
                    stack.push(aChar);
                } else {
                    stack.pop();
                }
            }
            return stack.isEmpty();
        }
    }

    class Solution18 {
        public List<List<Integer>> threeSum(int[] nums) {
            List<List<Integer>> res = new ArrayList<>();
            Arrays.sort(nums);
            for (int i = 0; i < nums.length - 2; i++) {
                if (i > 0 && nums[i] == nums[i - 1]) continue;
                int target = -nums[i];
                //注意点 在for循环里没有写l++,那么需要注意在其他的地方加上限制
                for (int l = i + 1, r = nums.length - 1; l < r; ) {
                    if (l > i + 1 && nums[l] == nums[l - 1]) {
                        l++;
                        continue;
                    }
                    if (nums[l] + nums[r] == target) {
                        List<Integer> inner = new ArrayList<>();
                        inner.add(nums[i]);
                        inner.add(nums[l++]);
                        inner.add(nums[r--]);
                        res.add(inner);
                    } else if (nums[l] + nums[r] < target) {
                        l++;
                    } else {
                        r--;
                    }
                }
            }
            return res;
        }
    }

    class Solution19 {
        int res = Integer.MIN_VALUE;
        public int maxPathSum(TreeNode root) {
              process(root);
              return res;
        }

        public int  process(TreeNode root) {
            if(root==null)return 0 ;

            int left = process(root.left);
            int right = process(root.right);
            res = Math.max(res,left+right+root.val);
            return Math.max(0,root.val+Math.max(left,right));
        }
    }
    class MyQueue {

        Stack<Integer> stack1;
        Stack<Integer> stack2;

        public MyQueue() {
            stack1 = new Stack<>();
            stack2 = new Stack<>();

        }

        public void push(int x) {
            stack1.push(x);
        }

        public int pop() {
            if(!stack2.isEmpty()){
                return stack2.pop();
            }else {
                while(!stack1.isEmpty()){
                    stack2.push(stack1.pop());
                }
                return stack2.pop();
            }
        }

        public int peek() {
            if(!stack2.isEmpty()){
                return stack2.peek();
            }else {
                while(!stack1.isEmpty()){
                    stack2.push(stack1.pop());
                }
                return stack2.peek();
            }
        }

        public boolean empty() {
            return stack2.isEmpty()&&stack1.isEmpty();
        }
    }
}
