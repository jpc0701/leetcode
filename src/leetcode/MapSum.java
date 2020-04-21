package leetcode;

import java.util.HashMap;

/**
 * 实现一个 MapSum 类里的两个方法，insert 和 sum。

对于方法 insert，你将得到一对（字符串，整数）的键值对。字符串表示键，整数表示值。如果键已经存在，那么原来的键值对将被替代成新的键值对。

对于方法 sum，你将得到一个表示前缀的字符串，你需要返回所有以该前缀开头的键的值的总和。

示例 1:

输入: insert("apple", 3), 输出: Null
输入: sum("ap"), 输出: 3
输入: insert("app", 2), 输出: Null
输入: sum("ap"), 输出: 5

来源：力扣（LeetCode）
链接：https://leetcode-cn.com/problems/map-sum-pairs
著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @author 人生自古谁无死
 *
 */
public class MapSum {
	//字典树
	public static void main(String[] args) {
		MapSum mapSum = new MapSum();
		mapSum.insert("aa", 3);
		System.out.println(mapSum.sum("a"));
		mapSum.insert("aa", 2);
		System.out.println(mapSum.sum("a"));
	}
	
	private Node root;
	
    /** Initialize your data structure here. */
    public MapSum() {
    	this.root = new Node();
    }
    
    public void insert(String key, int val) {
    	Node tempNode = this.root;
    	for (int i = 0; i < key.length(); i++) {
			if (!tempNode.chilren.containsKey(key.charAt(i))) 
				tempNode.chilren.put(key.charAt(i), new Node(key.charAt(i), tempNode));
			tempNode = tempNode.chilren.get(key.charAt(i));
			if (tempNode.weight != 0) tempNode.weight = 0;
		}
		tempNode.weight = val;
    }
    
    public int sum(String prefix) {
    	return this.find(this.root, prefix, 0, 0);
    }
    
    private int find(Node currNode, String prefix, int index, int weight) {
    	if (prefix.length() == 0) return 0;
    	if (index < prefix.length()) {
			if (currNode.chilren.containsKey(prefix.charAt(index))) 
				return this.find(currNode.chilren.get(prefix.charAt(index)), prefix, index + 1, weight);
			else 
				return 0;
		} else {
	    	weight += currNode.weight;
			if (currNode.chilren.size() != 0) {
				for (Node childNode : currNode.chilren.values()) 
					weight = this.find(childNode, prefix, index + 1, weight);
			}
	    	return weight;
		}
    }
    
    class Node {
    	char character;
    	Node parent;
    	HashMap<Character, Node> chilren = new HashMap<Character, Node>();
    	int weight = 0;
    	public Node() {}
    	public Node(char character, Node parent) {
    		this.character = character;
    		this.parent = parent;
    	}
    }
}

/**
 * Your MapSum object will be instantiated and called as such:
 * MapSum obj = new MapSum();
 * obj.insert(key,val);
 * int param_2 = obj.sum(prefix);
 */
