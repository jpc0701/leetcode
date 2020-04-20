package leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;


/**
 * 在英语中，我们有一个叫做 词根(root)的概念，它可以跟着其他一些词组成另一个较长的单词——我们称这个词为 继承词(successor)。例如，词根an，跟随着单词 other(其他)，可以形成新的单词 another(另一个)。

现在，给定一个由许多词根组成的词典和一个句子。你需要将句子中的所有继承词用词根替换掉。如果继承词有许多可以形成它的词根，则用最短的词根替换它。

你需要输出替换之后的句子。

示例 1:

输入: dict(词典) = ["cat", "bat", "rat"]
sentence(句子) = "the cattle was rattled by the battery"
输出: "the cat was rat by the bat"
注:

输入只包含小写字母。
1 <= 字典单词数 <=1000
1 <=  句中词语数 <= 1000
1 <= 词根长度 <= 100
1 <= 句中词语长度 <= 1000

来源：力扣（LeetCode）
链接：https://leetcode-cn.com/problems/replace-words
著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @author 人生自古谁无死
 *
 */
public class ReplaceWords {
	public static void main(String[] args) {
		ArrayList<String> dict = new ArrayList<String>(Arrays.asList("e","k","c","harqp","h","gsafc","vn","lqp","soy","mr","x","iitgm","sb","oo","spj","gwmly","iu","z","f","ha","vds","v","vpx","fir","t","xo","apifm","tlznm","kkv","nxyud","j","qp","omn","zoxp","mutu","i","nxth","dwuer","sadl","pv","w","mding","mubem","xsmwc","vl","farov","twfmq","ljhmr","q","bbzs","kd","kwc","a","buq","sm","yi","nypa","xwz","si","amqx","iy","eb","qvgt","twy","rf","dc","utt","mxjfu","hm","trz","lzh","lref","qbx","fmemr","gil","go","qggh","uud","trnhf","gels","dfdq","qzkx","qxw"));
		String sentence= "ikkbp miszkays wqjferqoxjwvbieyk gvcfldkiavww vhokchxz dvypwyb bxahfzcfanteibiltins ueebf lqhflvwxksi dco kddxmckhvqifbuzkhstp wc ytzzlm gximjuhzfdjuamhsu gdkbmhpnvy ifvifheoxqlbosfww mengfdydekwttkhbzenk wjhmmyltmeufqvcpcxg hthcuovils ldipovluo aiprogn nusquzpmnogtjkklfhta klxvvlvyh nxzgnrveghc mpppfhzjkbucv cqcft uwmahhqradjtf iaaasabqqzmbcig zcpvpyypsmodtoiif qjuiqtfhzcpnmtk yzfragcextvx ivnvgkaqs iplazv jurtsyh gzixfeugj rnukjgtjpim hscyhgoru aledyrmzwhsz xbahcwfwm hzd ygelddphxnbh rvjxtlqfnlmwdoezh zawfkko iwhkcddxgpqtdrjrcv bbfj mhs nenrqfkbf spfpazr wrkjiwyf cw dtd cqibzmuuhukwylrnld dtaxhddidfwqs bgnnoxgyynol hg dijhrrpnwjlju muzzrrsypzgwvblf zbugltrnyzbg hktdviastoireyiqf qvufxgcixvhrjqtna ipfzhuvgo daee r nlipyfszvxlwqw yoq dewpgtcrzausqwhh qzsaobsghgm ichlpsjlsrwzhbyfhm ksenb bqprarpgnyemzwifqzz oai pnqottd nygesjtlpala qmxixtooxtbrzyorn gyvukjpc s mxhlkdaycskj uvwmerplaibeknltuvd ocnn frotscysdyclrc ckcttaceuuxzcghw pxbd oklwhcppuziixpvihihp";
		System.out.println(new ReplaceWords().solution(dict, sentence));
	}
	
	//利用字典树
    public String solution(List<String> dict, String sentence) {
    	Node rootNode = new Node();
    	for (String word : dict) {
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
    	String[] words = sentence.split(" ");
    	for (String word : words) 
			result = result + this.getRoot(word, rootNode, "") + " ";
    	result = result.substring(0, result.length() - 1);
    	return result;
    }
    
    private String getRoot(String successor, Node rootNode, String root) {
		if (rootNode != null) {
			if (rootNode.character != 0) {
				root = root + new String(new byte[] {rootNode.character});
				if (root.length() == successor.length()) return successor;
				if (rootNode.isWord) return root;
			}
			byte character = successor.getBytes()[root.length()];
			if (rootNode.chilren.containsKey(character) && rootNode.chilren.get(character).character == character) root = getRoot(successor, rootNode.chilren.get(character), root);
			else return successor;
		}
		return root;
	}
    
    class Node {
    	byte character;
    	Node parent;
    	HashMap<Byte, Node> chilren = new HashMap<Byte, Node>();
    	boolean isWord = false;
    }

}
