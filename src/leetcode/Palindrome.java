package leetcode;

import java.util.ArrayList;

/**
 * 请判断一个链表是否为回文链表。

示例 1:

输入: 1->2
输出: false
示例 2:

输入: 1->2->2->1
输出: true
进阶：
你能否用 O(n) 时间复杂度和 O(1) 空间复杂度解决此题？

链接：https://leetcode-cn.com/problems/palindrome-linked-list
 * @author 人生自古谁无死
 *
 */
public class Palindrome {
	public static void main(String[] args) {
		ListNode aListNode = new ListNode(-129);
		ListNode bListNode = new ListNode(-129);
		aListNode.next = bListNode;
		System.out.println(solution(aListNode));
	}
	
	/**
	 * 时间O(n)，空间O(n)
	 * @param head
	 * @return
	 */
    public static boolean solution(ListNode head) {
    	boolean result = true;
    	ArrayList<Integer> temp = new ArrayList<Integer>();
    	while (head != null) {
			temp.add(head.val);
			head = head.next;
		}
    	int left = 0;
    	int right = temp.size() - 1;
    	while (left < right) {
			if (temp.get(left).intValue() != temp.get(right).intValue()) {
				result = false;
				break;
			}
			left ++;
			right --;
		}
    	return result;
    }
    
	/**
	 * 时间O(n)，空间O(1)
	 * @param head
	 * @return
	 */
    public static boolean solution1(ListNode head) {
    	boolean result = true;
    	int count = 0;
    	ListNode temp = head;
    	while (temp != null) {
			count ++;
			temp = temp.next;
		}
    	ListNode pre = null;
    	ListNode curr = head;
    	for (int i = 0; i < count / 2; i++) {
			temp = curr.next;
			curr.next = pre;
			pre = curr;
			curr = temp;
		}
    	if (count % 2 == 1) curr = curr.next;
    	while (curr != null) {
			if (curr.val != pre.val) {
				result = false;
				break;
			}
			curr = curr.next;
			pre = pre.next;
		}
    	return result;
    }
}
