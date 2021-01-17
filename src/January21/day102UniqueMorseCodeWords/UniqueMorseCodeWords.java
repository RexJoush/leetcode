package January21.day102UniqueMorseCodeWords;

import java.util.HashSet;
import java.util.Set;

/**
 * @author Joush
 * @time 2021.01.17
 */

/*
    唯一摩尔斯密码词
    https://leetcode-cn.com/problems/unique-morse-code-words/

    国际摩尔斯密码定义一种标准编码方式，将每个字母对应于一个由一系列点和短线组成的字符串，
    比如: "a" 对应 ".-", "b" 对应 "-...", "c" 对应 "-.-.", 等等。
    为了方便，所有26个英文字母对应摩尔斯密码表如下：
    [".-","-...","-.-.","-..",".","..-.","--.","....","..",".---","-.-",".-..","--","-.","---",".--.","--.-",".-.","...","-","..-","...-",".--","-..-","-.--","--.."]
    给定一个单词列表，每个单词可以写成每个字母对应摩尔斯密码的组合。
    例如，"cab" 可以写成 "-.-..--..."，(即 "-.-." + ".-" + "-..." 字符串的结合)。
    我们将这样一个连接过程称作单词翻译。
    返回我们可以获得所有词不同单词翻译的数量。

    例如:
        输入: words = ["gin", "zen", "gig", "msg"]
        输出: 2
        解释:
        各单词翻译如下:
        "gin" -> "--...-."
        "zen" -> "--...-."
        "gig" -> "--...--."
        "msg" -> "--...--."
        共有 2 种不同翻译, "--...-." 和 "--...--.".

    注意:
        单词列表words 的长度不会超过 100。
        每个单词 words[i]的长度范围为 [1, 12]。
        每个单词 words[i]只包含小写字母。

 */
public class UniqueMorseCodeWords {

    /*
        遍历数组，算出所有的摩尔斯密码，加入 set 集合，返回 set 集合长度即可
        使用 map 集合存储 morse 表耗时 4ms，使用 String 存储临时字符串，耗时 3ms，使用 StringBuilder 最快
        结果：
            2 ms, 97.68%
            36.4 MB, 48.73%
     */
    public int uniqueMorseRepresentations(String[] words) {
        Set<String> set = new HashSet<>();
        String[] morse = new String[]{".-","-...","-.-.","-..",".","..-.","--.","....","..",".---","-.-",".-..","--","-.","---",".--.","--.-",".-.","...","-","..-","...-",".--","-..-","-.--","--.."};

        for (String word : words) {
            StringBuilder morseString = new StringBuilder();
            // 遍历单词，拼接摩尔斯字符串
            for (char c : word.toCharArray()) {
                morseString.append(morse[c - 'a']);
            }
            // 加入 set 集合
            set.add(morseString.toString());
        }

        return set.size();
    }

}
