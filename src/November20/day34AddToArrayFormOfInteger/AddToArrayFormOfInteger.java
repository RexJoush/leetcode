package November20.day34AddToArrayFormOfInteger;


import java.util.ArrayList;
import java.util.IllegalFormatCodePointException;
import java.util.List;
import java.util.Stack;

/**
 * @author Joush
 * @time 2020.11.10
 */

/*
    数组形式的整数加法
    https://leetcode-cn.com/problems/add-to-array-form-of-integer/
    
    对于非负整数 X 而言，X 的数组形式是每位数字按从左到右的顺序形成的数组。例如，如果 X = 1231，那么其数组形式为 [1,2,3,1]。
    给定非负整数 X 的数组形式 A，返回整数 X+K 的数组形式。

    示例 1：
        输入：A = [1,2,0,0], K = 34
        输出：[1,2,3,4]
        解释：1200 + 34 = 1234
    示例 2：
        输入：A = [2,7,4], K = 181
        输出：[4,5,5]
        解释：274 + 181 = 455
    示例 3：
        输入：A = [2,1,5], K = 806
        输出：[1,0,2,1]
        解释：215 + 806 = 1021
    示例 4：
        输入：A = [9,9,9,9,9,9,9,9,9,9], K = 1
        输出：[1,0,0,0,0,0,0,0,0,0,0]
        解释：9999999999 + 1 = 10000000000
    提示：
        1 <= A.length <= 10000
        0 <= A[i] <= 9
        0 <= K <= 10000
        如果 A.length > 1，那么 A[0] != 0

 */
public class AddToArrayFormOfInteger {


    public List<Integer> addToArrayForm(int[] A, int K) {

        Stack<Integer> stack = new Stack<>();

        int length = String.valueOf(K).length();
        char[] chars = String.valueOf(K).toCharArray();

        int[] B = new int[length];

        // 将数字 K 转换为 int 数组
        for (int i = 0; i < length; i++){
            B[i] = Integer.parseInt(chars[i]+"");
        }

        boolean flag = false;
        int m = A.length - 1;
        int n = B.length - 1;
        int temp;

        // 从最后一位开始，flag 标记是否有进位
        for (; m >= 0 && n >= 0;m--,n--){

            // 如果有进位，则加上进 1
            if (flag){
                temp = A[m] + B[n] + 1;
            }
            // 否则本位相加
            else {
                temp = A[m] + B[n];
            }

            // 如果大于10，产生进位，则将 flag 置为 true，并将数字 -10 后压栈
            if (temp >= 10){
                flag = true;
                stack.push(temp - 10);
            }
            // 未产生进位，则直接压栈
            else {
                flag = false;
                stack.push(temp);
            }
            // 置空
            temp = 0;
        }

        // 剩余数组继续比较，然后进行压栈
        while (m >= 0){
            if (flag){
                temp = A[m--] + 1;
            }
            else {
                temp = A[m--];
            }
            if (temp >= 10){
                flag = true;
                stack.push(temp - 10);
            } else {
                flag = false;
                stack.push(temp);
            }
        }
        while (n >= 0){
            if (flag){
                temp = B[n--] + 1;
            }
            else {
                temp = B[n--];
            }
            if (temp >= 10){
                flag = true;
                stack.push(temp - 10);
            } else {
                flag = false;
                stack.push(temp);
            }
        }

        // 如果最后一位也有进位，将第一位 1 压栈
        if (flag){
            stack.push(1);
        }

        // 封装结果，将栈里的数字存入 list 中
        List<Integer> result = new ArrayList<>();

        int size = stack.size();
        for (int i = 0; i < size; i++) {
            result.add(stack.pop());
        }

        // 返回 list
        return result;
    }
}
