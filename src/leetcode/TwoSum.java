package leetcode;

import java.util.HashMap;

/**
 * 给定一个整数数组 nums 和一个目标值 target，请你在该数组中找出和为目标值的那 两个 整数，并返回他们的数组下标。

你可以假设每种输入只会对应一个答案。但是，你不能重复利用这个数组中同样的元素。

示例:

给定 nums = [2, 7, 11, 15], target = 9

因为 nums[0] + nums[1] = 2 + 7 = 9
所以返回 [0, 1]

https://leetcode-cn.com/problems/two-sum/
 * @author 人生自古谁无死
 *
 */
public class TwoSum {
	public static void main(String[] args) {
		System.out.print(new int[] {1, 2});
	}
	
	/**
	 * 直接两次遍历
	 * @param nums
	 * @param target
	 * @return
	 */
	public static int[] solution(int[] nums, int target) {
        int[] result = new int[2];
        for(int i = 0; i < nums.length - 1; i ++) {
            for(int j = 1; j < nums.length; j ++) {
                if(nums[i] + nums[j] == target) {
                    result[0] = i;
                    result[1] = j;
                }
            }
        }
        return result;
	}
	
	/**
	 * 一次遍历，将目标值与元素的差存下来然后在与元素比较；用空间换时间
	 * @param nums
	 * @param target
	 * @return
	 */
	public static int[] solution1(int[] nums, int target) {
        int[] result = new int[2];
        HashMap<Integer, Integer> temp = new HashMap<Integer, Integer>();
        for (int i = 0; i < nums.length; i++) {
			if (temp.containsKey(nums[i])) {
				result[0] = temp.get(nums[i]);
				result[1] = i;
				break;
			}
			temp.put(target - nums[i], i);
		}
        return result;
	}
}
