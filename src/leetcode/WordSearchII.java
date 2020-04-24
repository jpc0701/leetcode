package leetcode;

import java.util.ArrayList;
import java.util.List;

/**
 * 给定一个二维网格 board 和一个字典中的单词列表 words，找出所有同时在二维网格和字典中出现的单词。

单词必须按照字母顺序，通过相邻的单元格内的字母构成，其中“相邻”单元格是那些水平相邻或垂直相邻的单元格。同一个单元格内的字母在一个单词中不允许被重复使用。

示例:

输入: 
words = ["oath","pea","eat","rain"] and board =
[
  ['o','a','a','n'],
  ['e','t','a','e'],
  ['i','h','k','r'],
  ['i','f','l','v']
]

输出: ["eat","oath"]
说明:
你可以假设所有输入都由小写字母 a-z 组成。

提示:

你需要优化回溯算法以通过更大数据量的测试。你能否早点停止回溯？
如果当前单词不存在于所有单词的前缀中，则可以立即停止回溯。什么样的数据结构可以有效地执行这样的操作？散列表是否可行？为什么？ 前缀树如何？如果你想学习如何实现一个基本的前缀树，请先查看这个问题： 实现Trie（前缀树）。

来源：力扣（LeetCode）
链接：https://leetcode-cn.com/problems/word-search-ii
著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @author 人生自古谁无死
 *
 */
public class WordSearchII {
	public static void main(String[] args) {
		WordSearchII wordSearchII = new WordSearchII();
		char[][] board = new char[][] {{'o','a','a','n'},
									  {'e','t','a','e'},
									  {'i','h','k','r'},
									  {'i','f','l','v'}};
		String[] words = {"oath","pea","eat","rain"};
		System.out.println(wordSearchII.solution(board, words));
	}
	
    public List<String> solution(char[][] board, String[] words) {
        List<String> result = new ArrayList<>();
        int[][] used = new int[board.length][board[0].length];
        for (String word : words) {
        	boolean flag = false;
        	for (int i = 0; i < board.length; i++) {
        		for (int j = 0; j < board[i].length; j++) {
        			used = new int[board.length][board[0].length];
        			if (this.find(used, board, i, j, word, 0)) {
        				result.add(word);
        				flag = true;
        				break;
        			}
				}
        		if (flag) break;
			}
		}
        return result;
    }
    
    private boolean find(int[][] used, char[][] board, int x, int y,String word, int index) {
        if (x < 0 || x == board.length || y < 0 || y == board[0].length) 
        	return false;
        if (used[x][y] == 1) 
        	return false;
        if (board[x][y] == word.charAt(index)) {
			used[x][y] = 1;
			if (index == word.length() - 1) 
				return true;
			if (this.find(used, board, x - 1, y, word, index + 1) ||
				this.find(used, board, x + 1, y, word, index + 1) ||
				this.find(used, board, x, y - 1, word, index + 1) ||
				this.find(used, board, x, y + 1, word, index + 1)) 
				return true;
			used[x][y] = 0;
			return false;
		} else {
			return false;
		}
    }
}
