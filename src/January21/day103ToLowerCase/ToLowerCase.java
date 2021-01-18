package January21.day103ToLowerCase;

/**
 * @author Joush
 * @time 2021.01.18
 */
public class ToLowerCase {

    /*
        使用 StringBuilder 拼接字符串，效率更高
        结果：
            0 ms, 100.00%
            36.5 MB, 10.75%
     */
    public String toLowerCase(String str) {
        StringBuilder stringBuilder = new StringBuilder();
        char[] chars = str.toCharArray();
        for (int i = 0; i < chars.length; i++) {
            if (chars[i] >= 'A' && chars[i] <= 'Z'){
                chars[i] += 32;
            }
            stringBuilder.append(chars[i]);
        }
        return stringBuilder.toString();
    }
}
