package leetcode;

import java.util.HashMap;


/**
 * 设计一个支持以下两种操作的数据结构：

void addWord(word)
bool search(word)
search(word) 可以搜索文字或正则表达式字符串，字符串只包含字母 . 或 a-z 。 . 可以表示任何一个字母。

示例:

addWord("bad")
addWord("dad")
addWord("mad")
search("pad") -> false
search("bad") -> true
search(".ad") -> true
search("b..") -> true

来源：力扣（LeetCode）
链接：https://leetcode-cn.com/problems/add-and-search-word-data-structure-design
著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @author 人生自古谁无死
 *
 */
public class WordDictionary {
	public static void main(String[] args) {
		WordDictionary wordDictionary = new WordDictionary();
		wordDictionary.addWord("bad");
		wordDictionary.addWord("dad");
		wordDictionary.addWord("mad");
		System.out.println(wordDictionary.search("pad"));
		System.out.println(wordDictionary.search("bad"));
		System.out.println(wordDictionary.search(".ad"));
		System.out.println(wordDictionary.search("b.."));
	}
	
	private Node rootNode;
	
    /** Initialize your data structure here. */
    public WordDictionary() {
    	this.rootNode = new Node();
    }
    
    /** Adds a word into the data structure. */
    public void addWord(String word) {
    	Node tempNode = this.rootNode;
    	for (int i = 0; i < word.length(); i++) {
			if (!tempNode.chilren.containsKey(word.charAt(i))) 
				tempNode.chilren.put(word.charAt(i), new Node(word.charAt(i), tempNode));
			tempNode = tempNode.chilren.get(word.charAt(i));
		}
    	tempNode.isWord = true;
    }
    
    /** Returns if the word is in the data structure. A word could contain the dot character '.' to represent any one letter. */
    public boolean search(String word) {
    	return this.find(this.rootNode, word, 0);
    }
    
    private boolean find(Node curNode, String word, int index) {
    	if (index == word.length()) 
    		return false;
    	char curChar = word.charAt(index);
    	if (index == word.length() - 1) {
			if (curChar == '.') {
				for (Node childNode : curNode.chilren.values()) {
					if (childNode.isWord) 
						return true;
				}
			} else {
				if (curNode.chilren.containsKey(curChar) && curNode.chilren.get(curChar).isWord) 
					return true;
			}
		}
    	if (curNode.chilren.size() == 0) {
			return false;
		} else {
			if (curChar == '.') {
				boolean temp = false;
				for (Node childNode : curNode.chilren.values()) 
					temp = temp || this.find(childNode, word, index + 1);
				return temp;
			} else {
				if (curNode.chilren.containsKey(curChar)) {
					return this.find(curNode.chilren.get(curChar), word, index + 1); 
				} else {
					return false;
				}
			}
		}
    }
    
    class Node {
    	char character;
    	Node parent;
    	HashMap<Character, Node> chilren = new HashMap<Character, Node>();
    	boolean isWord = false;
    	public Node() {}
    	public Node(char character, Node parent) {
    		this.character = character;
    		this.parent = parent;
    	}
    }
}

/**
 * Your WordDictionary object will be instantiated and called as such:
 * WordDictionary obj = new WordDictionary();
 * obj.addWord(word);
 * boolean param_2 = obj.search(word);
 */
