package leetcode;

/**
 * 给出两个 非空 的链表用来表示两个非负的整数。其中，它们各自的位数是按照 逆序 的方式存储的，并且它们的每个节点只能存储 一位 数字。

如果，我们将这两个数相加起来，则会返回一个新的链表来表示它们的和。

您可以假设除了数字 0 之外，这两个数都不会以 0 开头。

示例：

输入：(2 -> 4 -> 3) + (5 -> 6 -> 4)
输出：7 -> 0 -> 8
原因：342 + 465 = 807

链接：https://leetcode-cn.com/problems/add-two-numbers
 * @author 人生自古谁无死
 *
 */
public class AddTwoNumbers {
	public static void main(String[] args) {
		
	}
	
	/**
	 * 就是做加法的思路，代码还可以精简一些，吧两个循环合并成一个循环
	 * @param l1
	 * @param l2
	 * @return
	 */
    public ListNode solution(ListNode l1, ListNode l2) {
        ListNode result = new ListNode(0);
        ListNode head = result;
        ListNode tail = result;
        int temp = 0;
        while (l1 != null && l2 != null) {
			int sum = l1.val + l2.val + temp;
			result.val = sum % 10;
			temp = sum / 10;
			result.next = new ListNode(0);
			tail = result;
			result = result.next;
			l1 = l1.next;
			l2 = l2.next;
		}
        ListNode noEmptyListNode = l1 == null ? l2 : l1;
		while (noEmptyListNode != null) {
			int sum = temp + noEmptyListNode.val;
			result.val = sum % 10;
			temp = sum / 10;
			result.next = new ListNode(0);
			tail = result;
			result = result.next;
			noEmptyListNode = noEmptyListNode.next;
		}
		if (temp != 0) 
			result.val = temp;
		else 
			tail.next = null;
        return head;
    }
}

class ListNode {
    int val;
    ListNode next;
    ListNode(int x) { val = x; }
}