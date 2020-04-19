package leetcode;

/**
 * 给定一个字符串 s，你可以通过在字符串前面添加字符将其转换为回文串。找到并返回可以用这种方式转换的最短回文串。

示例 1:

输入: "aacecaaa"
输出: "aaacecaaa"
示例 2:

输入: "abcd"
输出: "dcbabcd"

链接：https://leetcode-cn.com/problems/shortest-palindrome
 * @author 人生自古谁无死
 *
 */
public class ShortestPalindrome {
	public static void main(String[] args) {
		System.out.println(solution1("abcd"));
	}
	
    public static String solution(String s) {
    	if (s.length() == 0) return s;
    	byte[] chars = s.getBytes();
    	int start1 = 0, end1 = 0;
    	for (int i = 0; i < chars.length; i++) {
    		for (int j = i; j < chars.length && j < i + 2; j++) {
    			int start = i, end = j;
    			while (start >= 0 && end < chars.length) {
    				if (chars[start] == chars[end] && start == 0) {
    					int length1 = end1 - start1 + (chars.length - end1 - 1) * 2;
    					int length = end - start + (chars.length - end - 1) * 2;
    					if (length < length1) {
							start1 = start;
							end1 = end;
						}
    				} else if (chars[start] != chars[end]) break;
    				start --;
    				end ++;
				}
			}
		}
    	byte[] preChars = new byte[chars.length - end1 - 1];
    	for (int i = 0; i < preChars.length; i++) 
			preChars[i] = chars[chars.length - 1 - i];
    	return new String(preChars) + s;
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
        
        int longest = 0, start, end;
        for (int i = 0; i < p.length; i++) {
        	start = (i - p[i]) / 2;
        	end = start + p[i];
        	if (start == 0 && end > longest) longest = end;
        }
		
		byte[] chars = s.getBytes();
    	byte[] preChars = new byte[chars.length - longest];
    	for (int i = 0; i < preChars.length; i++) 
			preChars[i] = chars[chars.length - 1 - i];
    	return new String(preChars) + s;
    }
}
