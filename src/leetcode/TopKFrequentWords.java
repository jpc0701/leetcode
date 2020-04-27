package leetcode;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * 给一非空的单词列表，返回前 k 个出现次数最多的单词。

返回的答案应该按单词出现频率由高到低排序。如果不同的单词有相同出现频率，按字母顺序排序。

示例 1：

输入: ["i", "love", "leetcode", "i", "love", "coding"], k = 2
输出: ["i", "love"]
解析: "i" 和 "love" 为出现次数最多的两个单词，均为2次。
    注意，按字母顺序 "i" 在 "love" 之前。
 

示例 2：

输入: ["the", "day", "is", "sunny", "the", "the", "the", "sunny", "is", "is"], k = 4
输出: ["the", "is", "sunny", "day"]
解析: "the", "is", "sunny" 和 "day" 是出现次数最多的四个单词，
    出现次数依次为 4, 3, 2 和 1 次。
 

注意：

假定 k 总为有效值， 1 ≤ k ≤ 集合元素数。
输入的单词均由小写字母组成。

来源：力扣（LeetCode）
链接：https://leetcode-cn.com/problems/top-k-frequent-words
著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @author 人生自古谁无死
 *
 */
public class TopKFrequentWords {
	public static void main(String[] args) {
		
	}
	
    public List<String> topKFrequent(String[] words, int k) {
        Map<String, Long> map = Arrays.stream(words).collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));
        PriorityQueue<String> pQueue = new PriorityQueue<>(k, (a, b) -> (int) (map.get(a) == map.get(b) ? b.compareTo(a) : map.get(a) - map.get(b)));
        for (String str : map.keySet()) {
            pQueue.offer(str);
            if (pQueue.size() > k)
                pQueue.poll();
        }
        String[] res = new String[k];
        for (int i = k - 1; i > -1; --i)
            res[i] = pQueue.poll();
        return Arrays.asList(res);
    }
}

