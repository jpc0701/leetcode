package leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 给你一个包含 n 个整数的数组 nums，判断 nums 中是否存在三个元素 a，b，c ，使得 a + b + c = 0 ？请你找出所有满足条件且不重复的三元组。

注意：答案中不可以包含重复的三元组。

 

示例：

给定数组 nums = [-1, 0, 1, 2, -1, -4]，

满足要求的三元组集合为：
[
  [-1, 0, 1],
  [-1, -1, 2]
]

https://leetcode-cn.com/problems/3sum/
 * @author 人生自古谁无死
 *
 */
public class ThreeSum {
	public static void main(String[] args) {
		int[] nums = new int[] {-1,0,1,2,-1,-4};
		solution1(nums);
	}
	
	/**
	 * 暴力遍历求解
	 * 需要额外去除重复的三元组，此方法会超时
	 * @param nums
	 * @return
	 */
    public static List<List<Integer>> solution(int[] nums) {
    	List<List<Integer>> result = new ArrayList<List<Integer>>();
    	for (int i = 0; i < nums.length - 2; i++) {
			for (int j = i + 1; j < nums.length - 1; j++) {
				for (int k = j + 1; k < nums.length; k++) {
					if (nums[i] + nums[j] + nums[k] == 0) {
						int[] temp = new int[] {nums[i], nums[j], nums[k]};
						Arrays.sort(temp);
						boolean flag =  true;
						for (int m = 0; m < result.size(); m++) {
							if (result.get(m).get(0) == temp[0] && result.get(m).get(1) == temp[1] && result.get(m).get(2) == temp[2]) {
								flag = false;
								break;
							}	
						}
						List<Integer> tempIntegers = new ArrayList<Integer>();
						tempIntegers.add(temp[0]);
						tempIntegers.add(temp[1]);
						tempIntegers.add(temp[2]);
						if (flag) result.add(tempIntegers);
					}
				}
			}
		}
    	return result;
    }
    
    /**
     * 先进行排序再做处理
     * @param nums
     * @return
     */
    public static List<List<Integer>> solution1(int[] nums) {
    	List<List<Integer>> result = new ArrayList<List<Integer>>();
    	Arrays.sort(nums);
    	int first, second, third;
    	for (int i = 0; i < nums.length - 2; i++) {
    		if (nums[i] > 0) break;
    		//切记不要写成nums[i] == nums[i + 1]，否则会少算一种情况
    		if (i > 0 && nums[i] == nums[i - 1]) continue;
			first = i;
			second = i + 1;
			third = nums.length - 1;
	    	while (second < third) {
				int sum = nums[second] + nums[third];
				int target = 0 - nums[first];
				if (sum < target) {
					while (second < third && nums[second] == nums[second + 1]) second ++;
					second ++;
				} else if (sum > target) {
					while (second < third && nums[third] == nums[third - 1]) third --;
					third --;
				} else {
					List<Integer> tempIntegers = new ArrayList<Integer>();
					tempIntegers.add(nums[first]);
					tempIntegers.add(nums[second]);
					tempIntegers.add(nums[third]);
					result.add(tempIntegers);
					while (second < third && nums[second] == nums[second + 1]) second ++;
					while (second < third && nums[third] == nums[third - 1]) third --;
					second ++;
					third --;
				}
			}
		}
    	return result;
    }
}
