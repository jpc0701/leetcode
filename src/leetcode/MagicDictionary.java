package leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

import leetcode.ReplaceWords.Node;

/**
 * 实现一个带有buildDict, 以及 search方法的魔法字典。

对于buildDict方法，你将被给定一串不重复的单词来构建一个字典。

对于search方法，你将被给定一个单词，并且判定能否只将这个单词中一个字母换成另一个字母，使得所形成的新单词存在于你构建的字典中。

示例 1:

Input: buildDict(["hello", "leetcode"]), Output: Null
Input: search("hello"), Output: False
Input: search("hhllo"), Output: True
Input: search("hell"), Output: False
Input: search("leetcoded"), Output: False
注意:

你可以假设所有输入都是小写字母 a-z。
为了便于竞赛，测试所用的数据量很小。你可以在竞赛结束后，考虑更高效的算法。
请记住重置MagicDictionary类中声明的类变量，因为静态/类变量会在多个测试用例中保留。 请参阅这里了解更多详情。

来源：力扣（LeetCode）
链接：https://leetcode-cn.com/problems/implement-magic-dictionary
著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @author 人生自古谁无死
 *
 */
public class MagicDictionary {
	//用字典树解决
	public static void main(String[] args) {
		MagicDictionary magicDictionary = new MagicDictionary();
		String[] dict = {"hello", "leetcode"};
		magicDictionary.buildDict(dict);
		System.out.println(magicDictionary.search("hhllo"));
	}
	
	private Node root;
	
    /** Initialize your data structure here. */
    public MagicDictionary() {
    	this.root = new Node();
    }
    
    /** Build a dictionary through a list of words */
    public void buildDict(String[] dict) {
    	for (String word : dict) {
        	Node temp = this.root;
			for (int i = 0; i < word.length(); i++) {
				if (!temp.chilren.containsKey(word.charAt(i))) 
					temp.chilren.put(word.charAt(i), new Node(word.charAt(i), temp));
				temp = temp.chilren.get(word.charAt(i));
			}
			temp.isWord = true;
		}
    }
    
    /** Returns if there is any word in the trie that equals to the given word after modifying exactly one character */
    public boolean search(String word) {
    	return this.find(root, word, 0, 0) == 1;
    }
    
    private int find(Node rootNode, String word, int index, int count) {
    	if (word.length() == 0) return 0;
    	//已经到了字符串最后一个字符
        if (index == word.length() - 1) {
            if (count == 0) {//之前没有更换过字符
            	//之前没换字符，现在开始换字符，并且判断是否是单词
            	for (Node childNode : rootNode.chilren.values()) {
					if (childNode.character != word.charAt(index) && childNode.isWord) 
						return 1;
				}
            	return 0;
            } else {//之前更换过
            	//只有下层字符与当前字符相等且具有单词标志位，返回1，其他都返回0
                if (rootNode.chilren.containsKey(word.charAt(index)) && rootNode.chilren.get(word.charAt(index)).isWord) 
                	return 1;
                else 
                	return 0;
            }
        }
        if (count == 0) {//之前没有换过字符
            //换当前字符，就是遍历所有下一层中与当前字符不同的节点
        	for (Node childNode : rootNode.chilren.values()) {
				if (childNode.character != word.charAt(index)) 
					//如果换了一次字符就返回，其他情况如0，就不返回
					if (find(childNode, word, index + 1, 1) == 1) 
						return 1;
			}
            //保持当前字符不变
            if (rootNode.chilren.containsKey(word.charAt(index))) 
                return find(rootNode.chilren.get(word.charAt(index)), word, index + 1, 0);
        } else {//之前换过字符
        	//下一层没有找到对应的字符，则不符合要求
            if (!rootNode.chilren.containsKey(word.charAt(index))) 
                return 0;
            //下层还有字符相同，则继续寻找
            else 
                return find(rootNode.chilren.get(word.charAt(index)), word, index + 1, 1);
        }
        return 0;
		
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
 * Your MagicDictionary object will be instantiated and called as such:
 * MagicDictionary obj = new MagicDictionary();
 * obj.buildDict(dict);
 * boolean param_2 = obj.search(word);
 */
