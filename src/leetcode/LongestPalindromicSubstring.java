package leetcode;

import com.sun.org.apache.xalan.internal.xsltc.compiler.Template;

/**
 * 给定一个字符串 s，找到 s 中最长的回文子串。你可以假设 s 的最大长度为 1000。

示例 1：

输入: "babad"
输出: "bab"
注意: "aba" 也是一个有效答案。
示例 2：

输入: "cbbd"
输出: "bb"

链接：https://leetcode-cn.com/problems/longest-palindromic-substring
 * @author 人生自古谁无死
 *
 */
public class LongestPalindromicSubstring {
	public static void main(String[] args) {
		System.out.println(solution("cbbd"));
	}
	
    public static String solution(String s) {
    	if (s.length() == 0) return s;
    	byte[] chars = s.getBytes();
    	int[] temp;
    	int[] temp1 = new int[] {0, 0};
    	for (int i = 0; i < chars.length - 1; i++) {
			temp = getPalindromicSubstring(chars, i, i);
			int long1 = temp[1] - temp[0] + 1;
			int long2 = temp1[1] - temp1[0] + 1;
			if (long2 < long1) temp1 = temp;
			if (chars[i] == chars[i + 1]) {
				temp = getPalindromicSubstring(chars, i, i + 1);
				long1 = temp[1] - temp[0] + 1;
				long2 = temp1[1] - temp1[0] + 1;
				if (long2 < long1) temp1 = temp;
			}
		}
    	return new String(chars, temp1[0], temp1[1] - temp1[0] + 1);
    }
    
    private static int[] getPalindromicSubstring(byte[] chars, int start, int end) {
    	if (start >= 0 && end < chars.length) {
			if (chars[start] == chars[end]) {
				int[] temp = getPalindromicSubstring(chars, start - 1, end + 1);
				if (temp.length == 0) return new int[] {start, end};
				else return temp;
			} else return new int[] {};
		} else return new int[] {};
    }
}
