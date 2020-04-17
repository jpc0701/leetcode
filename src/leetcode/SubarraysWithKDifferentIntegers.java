package leetcode;

import java.util.HashMap;

/**
 * 给定一个正整数数组 A，如果 A 的某个子数组中不同整数的个数恰好为 K，则称 A 的这个连续、不一定独立的子数组为好子数组。

（例如，[1,2,3,1,2] 中有 3 个不同的整数：1，2，以及 3。）

返回 A 中好子数组的数目。

 

示例 1：

输入：A = [1,2,1,2,3], K = 2
输出：7
解释：恰好由 2 个不同整数组成的子数组：[1,2], [2,1], [1,2], [2,3], [1,2,1], [2,1,2], [1,2,1,2].
示例 2：

输入：A = [1,2,1,3,4], K = 3
输出：3
解释：恰好由 3 个不同整数组成的子数组：[1,2,1,3], [2,1,3], [1,3,4].
 

提示：

1 <= A.length <= 20000
1 <= A[i] <= A.length
1 <= K <= A.length

链接：https://leetcode-cn.com/problems/subarrays-with-k-different-integers
 * @author 人生自古谁无死
 *
 */
public class SubarraysWithKDifferentIntegers {
	public static void main(String[] args) {
		int[] a = new int[] {2,2,1,2,2,2,1,1};
//		int[] a = new int[] {1,2,1,2,3};
		System.out.println(solution(a, 2));
	}
	
    public static int solution(int[] A, int K) {
    	//这种方法超时
//    	int count = 0;
//    	for (int left  = K - 1; left  < A.length; left ++) {
//    		HashMap<Integer, Integer> temp = new HashMap<Integer, Integer>();
//			for (int right = left; right >= 0; right --) {
//				temp.put(A[right], right);
//				if (left - right + 1 >= K ) {
//					if (temp.size() == K) count ++;
//					else if (temp.size() > K) break;
//				}
//			}
//		}
//    	return count;
    	
    	//又一个超时的
//    	int count = 0;
//    	int left = 0;
//    	int right = K - 1;
//    	int length = 0;
//    	HashMap<Integer, Integer> temp = new HashMap<Integer, Integer>();
//    	for (int i = left; i <= right; i++) {
//    		if (temp.containsKey(A[i])) temp.put(A[i], temp.get(A[i]).intValue() + 1);
//			else {
//				temp.put(A[i], 1);
//				length ++;
//			}
//    	}
//    	while (right < A.length) {
//    		if (length == K) count ++;
//    		int right1 = right + 1;
//    		int length1 = length;
//    		HashMap<Integer, Integer> temp1 = (HashMap<Integer, Integer>) temp.clone();
//    		while (right1 < A.length) {
//				if (!temp1.containsKey(A[right1]) || temp1.get(A[right1]).intValue() == 0) {
//					temp1.put(A[right1], 1);
//					length1 ++;
//				}
//				if (length1 == K) count ++;
//				right1 ++;
//			}
//    		if (temp.get(A[left]).intValue() == 1) length --;
//    		temp.put(A[left], temp.get(A[left]).intValue() - 1);
//			left ++;
//
//			right ++;
//			if (right == A.length) break;
//    		if (temp.containsKey(A[right])) temp.put(A[right], temp.get(A[right]).intValue() + 1);
//			else temp.put(A[right], 1);
//    		if (temp.get(A[right]).intValue() == 1) length ++;
//		}
//    	return count;
    	
    	//实在做不出来，仿写的
//    	int[] cl = new int[A.length + 1];
//    	int[] cr = new int[A.length + 1];
//    	int l = 0, r = 0, res = 0, nl = 0, nr = 0;
//    	for (int i = 0; i < A.length; i++) {
//			if (cl[A[i]] == 0) nl ++;
//			cl[A[i]] ++;
//			while (nl > K) {
//				cl[A[l]] --;
//				if (cl[A[l]] == 0) nl --;
//				l ++;
//			}
//			if (cr[A[i]] == 0) nr ++;
//			cr[A[i]] ++;
//			while (nr >= K) {
//				cr[A[r]] --;
//				if (cr[A[r]] == 0) nr --;
//				r ++;
//			}
//			res += (r - l);
//		}
//    	return res;
    	
    	//自己按照思路写的，维持两个一大一小的窗口，这两个窗口右边界一样，大窗口满足K，小窗口满足K-1
    	int result = 0;
    	HashMap<Integer, Integer> bigWindow = new HashMap<Integer, Integer>();
    	HashMap<Integer, Integer> smallWindow = new HashMap<Integer, Integer>();
    	int leftBig = 0, leftSmall = 0;
    	for (int right = 0; right < A.length; right ++) {
			if (!bigWindow.containsKey(A[right])) bigWindow.put(A[right], 1);
			else bigWindow.put(A[right], bigWindow.get(A[right]).intValue() + 1);
			if (!smallWindow.containsKey(A[right])) smallWindow.put(A[right], 1);
			else smallWindow.put(A[right], smallWindow.get(A[right]).intValue() + 1);
			while (bigWindow.size() > K) {
				bigWindow.put(A[leftBig], bigWindow.get(A[leftBig]).intValue() - 1);
				if (bigWindow.get(A[leftBig]).intValue() == 0) bigWindow.remove(A[leftBig]);
				leftBig ++;
			}
			while (smallWindow.size() >= K) {
				smallWindow.put(A[leftSmall], smallWindow.get(A[leftSmall]).intValue() - 1);
				if (smallWindow.get(A[leftSmall]).intValue() == 0) 
					smallWindow.remove(A[leftSmall]);
				leftSmall ++;
			}
			result += (leftSmall - leftBig);
		}
    	return result;
    	
    }
    //[1,2], [2,1], [1,2], [2,3], [1,2,1], [2,1,2], [1,2,1,2]
}
