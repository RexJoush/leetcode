package October20.simple.day15PlusOne;

/**
 * @author Joush
 * @time 2020.10.22
 */

/*

    加一
    https://leetcode-cn.com/problems/plus-one/
    
    给定一个由整数组成的非空数组所表示的非负整数，在该数的基础上加一。
    最高位数字存放在数组的首位， 数组中每个元素只存储单个数字。
    你可以假设除了整数 0 之外，这个整数不会以零开头。
    示例 1:
        输入: [1,2,3]
        输出: [1,2,4]
        解释: 输入数组表示数字 123。
    示例 2:
        输入: [4,3,2,1]
        输出: [4,3,2,2]
        解释: 输入数组表示数字 4321。
你们没有实验室，所以平常你就去图书馆啊
    
 */
public class PlusOne {

    // 从最后一位非9的数字开始，知道确定 +1 的位置，如果全都是9，那么就新建一个length+1的数组
    public static int[] plusOne2(int[] digits) {

        for (int i = digits.length - 1; i >= 0; i--) {
            // 如果不等于9，加一直接结束
            if (digits[i] != 9){
                digits[i]++;
                return digits;
            }
            // 否则就将9变为0
            digits[i] = 0;
        }

        // 即全部为9，新建数组，返回 100000000....
        int[] temp = new int[digits.length + 1];
        temp[0] = 1;
        return temp;

    }




    // 使用 BigInteger 类实现
    public int[] plusOne(int[] digits) {
        String s = "";
        for (int i = 0; i < digits.length; i++) {
            s += digits[i];
        }
        // 将数组变成 数字
        java.math.BigInteger bg1 = new java.math.BigInteger(s);

        // 加1
        java.math.BigInteger bg2 = bg1.add(new java.math.BigInteger("1"));

        // 变成字符数组
        char[] chars = bg2.toString().toCharArray();
        int[] result = new int[chars.length];
        // 变成整型数组
        for (int i = 0; i < chars.length; i++) {
            result[i] = Integer.parseInt(String.valueOf(chars[i]));
        }
        // 返回
        return result;

    }

}
