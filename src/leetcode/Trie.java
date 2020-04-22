package leetcode;

import java.util.HashMap;


/**
 * 实现一个 Trie (前缀树)，包含 insert, search, 和 startsWith 这三个操作。

示例:

Trie trie = new Trie();

trie.insert("apple");
trie.search("apple");   // 返回 true
trie.search("app");     // 返回 false
trie.startsWith("app"); // 返回 true
trie.insert("app");   
trie.search("app");     // 返回 true
说明:

你可以假设所有的输入都是由小写字母 a-z 构成的。
保证所有输入均为非空字符串。

来源：力扣（LeetCode）
链接：https://leetcode-cn.com/problems/implement-trie-prefix-tree
著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @author 人生自古谁无死
 *
 */
public class Trie {
	public static void main(String[] args) {
		Trie trie = new Trie();
		trie.insert("apple");
		System.out.println(trie.search("apple"));
		System.out.println(trie.search("app"));
		System.out.println(trie.startsWith("app"));
		trie.insert("app");   
		System.out.println(trie.search("app"));
		
	}
	
	private Node rootNode;
	
    /** Initialize your data structure here. */
    public Trie() {
    	this.rootNode = new Node();
    }
    
    /** Inserts a word into the trie. */
    public void insert(String word) {
    	Node tempNode = this.rootNode;
    	for (int i = 0; i < word.length(); i++) {
			if (!tempNode.chilren.containsKey(word.charAt(i))) 
				tempNode.chilren.put(word.charAt(i), new Node(word.charAt(i), tempNode));
			tempNode = tempNode.chilren.get(word.charAt(i));
		}
    	tempNode.isWord = true;
    }
    
    /** Returns if the word is in the trie. */
    public boolean search(String word) {
    	boolean result = false;
    	Node tempNode = this.rootNode;
    	int index = 0;
    	while(true) {
    		if (index == word.length()) break;
    		if (tempNode.chilren.size() == 0) break;
    		else {
    			char currchar = word.charAt(index ++);
    			if (tempNode.chilren.containsKey(currchar)) {
        			tempNode = tempNode.chilren.get(currchar);
        			if (tempNode.character == currchar && index == word.length() && tempNode.isWord) {
						result = true;
						break;
					}
				}else break;
    		}
    	}
    	return result;
    }
    
    /** Returns if there is any word in the trie that starts with the given prefix. */
    public boolean startsWith(String prefix) {
    	boolean result = false;
    	Node tempNode = this.rootNode;
    	int index = 0;
    	while(true) {
    		if (index == prefix.length()) break;
    		if (tempNode.chilren.size() == 0) break;
    		else {
    			char currchar = prefix.charAt(index ++);
    			if (tempNode.chilren.containsKey(currchar)) {
        			tempNode = tempNode.chilren.get(currchar);
        			if (tempNode.character == currchar && index == prefix.length()) {
						result = true;
						break;
					}
				}else break;
    		}
    	}
    	return result;
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
 * Your Trie object will be instantiated and called as such:
 * Trie obj = new Trie();
 * obj.insert(word);
 * boolean param_2 = obj.search(word);
 * boolean param_3 = obj.startsWith(prefix);
 */
