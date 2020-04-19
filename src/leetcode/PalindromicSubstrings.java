package leetcode;

/**
 * 给定一个字符串，你的任务是计算这个字符串中有多少个回文子串。

具有不同开始位置或结束位置的子串，即使是由相同的字符组成，也会被计为是不同的子串。

示例 1:

输入: "abc"
输出: 3
解释: 三个回文子串: "a", "b", "c".
示例 2:

输入: "aaa"
输出: 6
说明: 6个回文子串: "a", "a", "a", "aa", "aa", "aaa".
注意:

输入的字符串长度不会超过1000。

链接：https://leetcode-cn.com/problems/palindromic-substrings
 * @author 人生自古谁无死
 *
 */
public class PalindromicSubstrings {
	public static void main(String[] args) {
		System.out.println(solution("abc"));
	}
	
    public static int solution(String s) {
    	if (s.length() == 0) return 1;
    	byte[] chars = s.getBytes();
    	int result = 0;
    	for (int i = 0; i < chars.length; i++) {
			result += getPalindromicSubstring(chars, i, i);
			if (i < chars.length - 1 && chars[i] == chars[i + 1]) {
				result += getPalindromicSubstring(chars, i, i + 1);
			}
		}
    	return result;
    }
    
    private static int getPalindromicSubstring(byte[] chars, int start, int end) {
    	if (start >= 0 && end < chars.length) {
			if (chars[start] == chars[end]) {
				return 1 + getPalindromicSubstring(chars, start - 1, end + 1);
			} else return 0;
		} else return 0;
    }
}
