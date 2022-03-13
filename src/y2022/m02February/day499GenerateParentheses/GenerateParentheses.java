package y2022.m02February.day499GenerateParentheses;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Rex Joush
 * @time 2022.03.08
 */

/*
    括号生成
    https://leetcode-cn.com/problems/generate-parentheses/

    数字 n 代表生成括号的对数，请你设计一个函数，用于能够生成所有可能的并且 有效的 括号组合。

    示例 1：
        输入：n = 3
        输出：["((()))","(()())","(())()","()(())","()()()"]
    示例 2：
        输入：n = 1
        输出：["()"]

    提示：
        1 <= n <= 8

 */
public class GenerateParentheses {

    /*
        深度优先遍历，遍历结果为一个完全二叉树，当右括号比左括号少时，就出现了剪枝
     */
    List<String> res = new ArrayList<>();

    /*
        1 ms, 75.26%
        41.6 MB, 6.24%
     */
    public List<String> generateParenthesis(int n) {
        dfs(n, n, "");
        return res;
    }

    private void dfs(int left, int right, String curStr) {
        // 左右括号都不剩余了，递归终止，加入结果
        if (left == 0 && right == 0) {
            res.add(curStr);
            return;
        }
        // 如果左括号还剩余的话，可以拼接左括号，将左括号 - 1拼接
        if (left > 0) {
            dfs(left - 1, right, curStr + "(");
        }
        // 如果右括号剩余多于左括号剩余的话，可以拼接右括号，不大于的话出现剪枝
        if (right > left) {
            dfs(left, right - 1, curStr + ")");
        }
    }
}
