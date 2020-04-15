package leetcode;

/**
 * 反转一个单链表。

示例:

输入: 1->2->3->4->5->NULL
输出: 5->4->3->2->1->NULL

https://leetcode-cn.com/problems/reverse-linked-list/
 * @author 人生自古谁无死
 *
 */
public class ReverseLinkedList {
	public static void main(String[] args) {
		
	}
	
	/**
	 * 迭代算法
	 * @param head
	 * @return
	 */
    public ListNode sulution(ListNode head) {
        ListNode pre = null;
        ListNode curr = head;
        ListNode tempNext;
        while (curr != null) {
			tempNext = curr.next;
			curr.next = pre;
			pre = curr;
			curr = tempNext;
		}
        return pre;
    }
	
    /**
     * 递归算法
     * @param head
     * @return
     */
    public ListNode sulution1(ListNode head) {
        if(head == null || head.next == null) return head;
        ListNode cur = sulution1(head.next);
        head.next.next = head;
        head.next = null;
        return cur;
    }
}
