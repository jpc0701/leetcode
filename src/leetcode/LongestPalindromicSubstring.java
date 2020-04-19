package leetcode;


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
		System.out.println(solution1("cbbd"));
	}
	
	//中心扩展法
    public static String solution(String s) {
    	if (s.length() == 0) return s;
    	byte[] chars = s.getBytes();
    	int[] temp;
    	int[] temp1 = new int[] {0, 0};
    	for (int i = 0; i < chars.length; i++) {
    		for (int j = i; j < chars.length && j < i + 2; j++) {
    			temp = getPalindromicSubstring(chars, i, j);
    			if (temp.length == 2) {
        			int length = temp[1] - temp[0] + 1;
        			int length1 = temp1[1] - temp1[0] + 1;
        			if (length1 < length) temp1 = temp;
				}
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
    
    //马拉车
    public static String solution1(String s) {
        String tempString = "^";
        if (s.equals("")) {
			tempString = "$";
		} else {
		    for (int i = 0; i < s.length(); i++)
		    	tempString += "#" + s.charAt(i);
		    tempString += "#$";
		}
        
        int center = 0, radius = 0;
        int n = tempString.length();
        int[] p = new int[n];
        for (int i = 1; i < n - 1; i++) {
			int i_mirror = 2 * center - i;
			if (i >= radius) p[i] = 0; 
			else p[i] = Math.min(p[i_mirror], radius - i);
			while (tempString.charAt(i + p[i] + 1) == tempString.charAt(i - p[i] - 1)) 
				p[i] ++;
			if (i + p[i] > radius) {
				radius = i + p[i];
				center = i;
			}
		}
        
        int maxPosition = 0;
        for (int i = 0; i < p.length; i++) 
			maxPosition = p[maxPosition] > p[i] ? maxPosition : i;
		int start = (maxPosition - p[maxPosition]) / 2;
		return s.substring(start, start + p[maxPosition]);
    }
}
