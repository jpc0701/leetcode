package leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

/**
 * 给定一组唯一的单词， 找出所有不同 的索引对(i, j)，使得列表中的两个单词， words[i] + words[j] ，可拼接成回文串。

示例 1:

输入: ["abcd","dcba","lls","s","sssll"]
输出: [[0,1],[1,0],[3,2],[2,4]] 
解释: 可拼接成的回文串为 ["dcbaabcd","abcddcba","slls","llssssll"]
示例 2:

输入: ["bat","tab","cat"]
输出: [[0,1],[1,0]] 
解释: 可拼接成的回文串为 ["battab","tabbat"]k

来源：力扣（LeetCode）
链接：https://leetcode-cn.com/problems/palindrome-pairs
著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @author 人生自古谁无死
 *
 */
public class PalindromePairs {
	public static void main(String[] args) {
		String[] words = {"abcd","dcba","lls","s","sssll"};
		System.out.println(new PalindromePairs().solution(words));
	}
	
	//马拉车+哈希
    public List<List<Integer>> solution(String[] words) {
    	List<List<Integer>> result = new ArrayList<List<Integer>>();
    	HashMap<String, Integer> dict = new HashMap<String, Integer>();
    	for (int i = 0; i < words.length; i++) 
			dict.put(words[i], i);
    	for (int i = 0; i < words.length; i++) {
    		String word = words[i];
			if (word.equals("")) {
				//空字符串与本身就是回文的字符串组成两对
				for (int j = 0; j < words.length; j++) {
					if (i != j) {
						boolean isalindrome = true;
						int left = 0, right = words[j].length() - 1;
						while (left <= right && isalindrome) {
							if (words[j].charAt(left ++) != words[j].charAt(right --)) 
								isalindrome = false;
						}
						if (isalindrome) {
							List<Integer> temp = new ArrayList<Integer>();
							temp.addAll(Arrays.asList(i, j));
							result.add(temp);
							temp = new ArrayList<Integer>();
							temp.addAll(Arrays.asList(j, i));
							result.add(temp);
						}
					}
				}
			} else {
				//不是空字符串
		        String tempString = "^";
			    for (int j = 0; j < word.length(); j++)
			    	tempString += "#" + word.charAt(j);
			    tempString += "#$";
		        int center = 0, radius = 0;
		        int n = tempString.length();
		        int[] p = new int[n];
		        for (int j = 1; j < n - 1; j++) {
					int j_mirror = 2 * center - j;
					if (j >= radius) p[j] = 0; 
					else p[j] = Math.min(p[j_mirror], radius - j);
					while (tempString.charAt(j + p[j] + 1) == tempString.charAt(j - p[j] - 1)) 
						p[j] ++;
					if (j + p[j] > radius) {
						radius = j + p[j];
						center = j;
					}
				}
		        
		        String wordReverse = new StringBuffer(word).reverse().toString();
		        ArrayList<String> head = new ArrayList<String>();
		        ArrayList<String> tail = new ArrayList<String>();
		        for (int j = 1; j < n - 1; j++) {
					int start = (j - p[j]) / 2;
					int end = start + p[j];
					if (start == end) continue;
					if (start == 0 && end < word.length()) 
						head.add(new StringBuffer(word.substring(end, word.length())).reverse().toString());
					else if (start > 0 && end == word.length()) 
						tail.add(new StringBuffer(word.substring(0, start)).reverse().toString());
				}
		        
		        if (dict.containsKey(wordReverse)) {
		        	if (dict.get(wordReverse).intValue() != i) {
						List<Integer> temp = new ArrayList<Integer>();
						temp.addAll(Arrays.asList(i, dict.get(wordReverse).intValue()));
						result.add(temp);
					}
				}
				for (String string : head) {
					if (dict.containsKey(string)) {
						List<Integer> temp = new ArrayList<Integer>();
						temp.addAll(Arrays.asList(dict.get(string).intValue(), i));
						result.add(temp);
					}
				}
				for (String string : tail) {
					if (dict.containsKey(string)) {
						List<Integer> temp = new ArrayList<Integer>();
						temp.addAll(Arrays.asList(i, dict.get(string).intValue()));
						result.add(temp);
					}
				}
			}
		}
    	return result;
    }
}
