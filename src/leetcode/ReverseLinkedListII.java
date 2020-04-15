package leetcode;

/**
 * 反转从位置 m 到 n 的链表。请使用一趟扫描完成反转。

说明:
1 ≤ m ≤ n ≤ 链表长度。

示例:

输入: 1->2->3->4->5->NULL, m = 2, n = 4
输出: 1->4->3->2->5->NULL

链接：https://leetcode-cn.com/problems/reverse-linked-list-ii
 * @author 人生自古谁无死
 *
 */
public class ReverseLinkedListII {
	public static void main(String[] args) {
		
	}
	
	/**
	 * 由于指定范围，用迭代算法，主要记录下开始结束的位置和开始前结束后的位置
	 * @param head
	 * @param m
	 * @param n
	 * @return
	 */
    public ListNode solution(ListNode head, int m, int n) {
    	ListNode pre = null;
    	ListNode curr = head;
    	ListNode temp;
    	ListNode startPre = null;
    	ListNode start = null;
    	ListNode end = null;
    	ListNode endNext = null;
    	int count = 1;
    	while (curr != null) {
			temp = curr.next;
			if (count + 1 == m) startPre = curr;
			if (count == m) start = curr;
			if (count == n) end = curr;
			if (count - 1 == n) endNext = curr;
			if (count >= m + 1 && count <= n) curr.next = pre;
			pre = curr;
			curr = temp;
			count ++;
		}
    	if (startPre != null) startPre.next = end;
        else head = end;
    	if (start != null) start.next = endNext;
    	return head;
    }
}
