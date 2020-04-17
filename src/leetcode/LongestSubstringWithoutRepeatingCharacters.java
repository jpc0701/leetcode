package leetcode;

import java.util.HashMap;

/**
 * 给定一个字符串，请你找出其中不含有重复字符的 最长子串 的长度。

示例 1:

输入: "abcabcbb"
输出: 3 
解释: 因为无重复字符的最长子串是 "abc"，所以其长度为 3。
示例 2:

输入: "bbbbb"
输出: 1
解释: 因为无重复字符的最长子串是 "b"，所以其长度为 1。
示例 3:

输入: "pwwkew"
输出: 3
解释: 因为无重复字符的最长子串是 "wke"，所以其长度为 3。
     请注意，你的答案必须是 子串 的长度，"pwke" 是一个子序列，不是子串。

链接：https://leetcode-cn.com/problems/longest-substring-without-repeating-characters
 * @author 人生自古谁无死
 *
 */
public class LongestSubstringWithoutRepeatingCharacters {
	public static void main(String[] args) {
		System.out.println(solution("pwwkew"));
	}
	
	/**
	 * 利用hashmap维持一个滑动窗口
	 * @param s
	 * @return
	 */
    public static int solution(String s) {
    	HashMap<Character,Integer> record = new HashMap<>();
        int max = 0;
        int left = 0;
        for(int right = 0; right < s.length(); right ++){
            if (record.containsKey(s.charAt(right))) 
				left = left > record.get(s.charAt(right)) ? left : record.get(s.charAt(right)) + 1;
				record.put(s.charAt(right), right);
			record.put(s.charAt(right), right);
            max = Math.max(max, right - left + 1);
        }
        return max;
    }
}
