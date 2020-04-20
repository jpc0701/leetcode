package leetcode;

import java.util.HashMap;


/**
 * 给出一个字符串数组words组成的一本英语词典。从中找出最长的一个单词，该单词是由words词典中其他单词逐步添加一个字母组成。若其中有多个可行的答案，则返回答案中字典序最小的单词。

若无答案，则返回空字符串。

示例 1:

输入: 
words = ["w","wo","wor","worl", "world"]
输出: "world"
解释: 
单词"world"可由"w", "wo", "wor", 和 "worl"添加一个字母组成。
示例 2:

输入: 
words = ["a", "banana", "app", "appl", "ap", "apply", "apple"]
输出: "apple"
解释: 
"apply"和"apple"都能由词典中的单词组成。但是"apple"得字典序小于"apply"。
注意:

所有输入的字符串都只包含小写字母。
words数组长度范围为[1,1000]。
words[i]的长度范围为[1,30]。

来源：力扣（LeetCode）
链接：https://leetcode-cn.com/problems/longest-word-in-dictionary
著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @author 人生自古谁无死
 *
 */
public class LongestWordInDictionary {
	public static void main(String[] args) {
		String[] words = {"ts","e","x","pbhj","opto","xhigy","erikz","pbh","opt","erikzb","eri","erik","xlye","xhig","optoj","optoje","xly","pb","xhi","x","o"};
		System.out.println(new LongestWordInDictionary().solution(words));
	}
	
	//字典树，并且是遍历查出符合要求的最长单词
    public String solution(String[] words) {
    	Node rootNode = new Node();
    	for (String word : words) {
			byte[] letters = word.getBytes();
			Node tempNode = rootNode;
			for (byte letter : letters) {
				if (!tempNode.chilren.containsKey(letter)) {
					Node newNode = new Node();
					newNode.character = letter;
					newNode.parent = tempNode;
					tempNode.chilren.put(letter, newNode);
				}
				tempNode = tempNode.chilren.get(letter);
			}
			tempNode.isWord = true;
		}
    	String result = "";
    	result = getLongestByTraversal(rootNode, result, 0, "");
    	return result;
    }
    
    private String getLongestByTraversal(Node rootNode, String result, int count, String longest) {
    	if (rootNode != null) {
    		if (count != longest.length()) return result;
    		if (rootNode.character != 0) longest = longest + new String(new byte[] {rootNode.character});
    		if (rootNode.isWord) count ++;
    		for (byte i = 97; i < 123; i++) {
				if (rootNode.chilren.containsKey(i)) 
					result = getLongestByTraversal(rootNode.chilren.get(i), result, count, longest);
			}
			if (count >= 1 && rootNode.isWord && count == longest.length()) result = longest.length() > result.length() ? longest : result;
			if (rootNode.character != 0) longest = longest.substring(0, longest.length() - 1);
    		if (rootNode.isWord) count --;
		}
    	return result;
    }
    
    class Node {
    	byte character;
    	Node parent;
    	HashMap<Byte, Node> chilren = new HashMap<Byte, Node>();
    	boolean isWord = false;
    	
    	public Node() {}
    }
    
}

class Node {
	byte character;
	Node parent;
	HashMap<Byte, Node> chilren = new HashMap<Byte, Node>();
	boolean isWord = false;
	
	public Node() {}
}
