package leetcode;

import com.sun.org.apache.bcel.internal.generic.StackConsumer;

/**
 * 给定两个大小为 m 和 n 的有序数组 nums1 和 nums2。

请你找出这两个有序数组的中位数，并且要求算法的时间复杂度为 O(log(m + n))。

你可以假设 nums1 和 nums2 不会同时为空。

示例 1:

nums1 = [1, 3]
nums2 = [2]

则中位数是 2.0
示例 2:

nums1 = [1, 2]
nums2 = [3, 4]

则中位数是 (2 + 3)/2 = 2.5

链接：https://leetcode-cn.com/problems/median-of-two-sorted-arrays
 * @author 人生自古谁无死
 *
 */
public class MedianOf2SortedArrays {
	public static void main(String[] args) {
		int[] a = new int[] {-1, 3};
		int[] b = new int[] {1, 2};
		System.out.println(solution(a, b));
	}
	
	//时间复杂度O(m+n)
    public static double solution(int[] nums1, int[] nums2) {
        int length = nums1.length + nums2.length;
        int medianPosition = length / 2 + length % 2, num1Position = -1, num2Position = -1;
        double median = 0, tmp = 0;
        while (true){
            if (num1Position == nums1.length - 1 && num2Position < nums2.length -1) num2Position ++;
            else if (num1Position < nums1.length - 1 && num2Position == nums2.length -1) num1Position ++;
            else {
                num1Position ++;
                num2Position ++;
                if(nums1[num1Position] > nums2[num2Position]) num1Position --;
                else num2Position --;
            }
            if (num1Position + num2Position + 2 == medianPosition){
                if (num1Position == -1) tmp = (double) nums2[num2Position];
                else if(num2Position == -1) tmp = (double) nums1[num1Position];
                else tmp = nums1[num1Position] > nums2[num2Position] ? (double) nums1[num1Position] : (double) nums2[num2Position];
                median += tmp;
                if(length % 2 == 0) {
                    medianPosition ++;
                    length ++;
                    continue;
                }
                break;
            }
        }
        return median / ((nums1.length + nums2.length) % 2 == 0 ? 2 : 1);
    }
    
	//时间复杂度O(log(m + n))
    public static double solution1(int[] nums1, int[] nums2) {
        int n = nums1.length;
        int m = nums2.length;
        int left = (n + m + 1) / 2;
        int right = (n + m + 2) / 2;
        //将偶数和奇数的情况合并，如果是奇数，会求两次同样的 k 。
        return (getKth(nums1, 0, n - 1, nums2, 0, m - 1, left) + getKth(nums1, 0, n - 1, nums2, 0, m - 1, right)) * 0.5;
    }
    
    private static int getKth(int[] nums1, int start1, int end1, int[] nums2, int start2, int end2, int k) {
        int len1 = end1 - start1 + 1;
        int len2 = end2 - start2 + 1;
        //让 len1 的长度小于 len2，这样就能保证如果有数组空了，一定是 len1 
        if (len1 > len2) 
        	return getKth(nums2, start2, end2, nums1, start1, end1, k);
        if (len1 == 0) 
        	return nums2[start2 + k - 1];
        if (k == 1) 
        	return Math.min(nums1[start1], nums2[start2]);
        int i = start1 + Math.min(len1, k / 2) - 1;
        int j = start2 + Math.min(len2, k / 2) - 1;
        if (nums1[i] > nums2[j]) 
            return getKth(nums1, start1, end1, nums2, j + 1, end2, k - (j - start2 + 1));
        else 
            return getKth(nums1, i + 1, end1, nums2, start2, end2, k - (i - start1 + 1));
    }
}
