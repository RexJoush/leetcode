package y2022.m02February.day502WordSearch;

/**
 * @author Rex Joush
 * @time 2022.02.21
 */

/*
    单词搜索
    https://leetcode-cn.com/problems/word-search/

    给定一个 m x n 二维字符网格 board 和一个字符串单词 word 。如果 word 存在于网格中，返回 true ；否则，返回 false 。
    单词必须按照字母顺序，通过相邻的单元格内的字母构成，其中“相邻”单元格是那些水平相邻或垂直相邻的单元格。同一个单元格内的字母不允许被重复使用。

    示例 1：
           |A||B||C| D
            S  F |C| S
            A |D||E| E
        输入：board = [["A","B","C","E"],["S","F","C","S"],["A","D","E","E"]], word = "ABCCED"
        输出：true
    示例 2：
            A  B  C  D
            S  F  C |S|
            A  D |E |E|
        输入：board = [["A","B","C","E"],["S","F","C","S"],["A","D","E","E"]], word = "SEE"
        输出：true
    示例 3：
            A  B  C  D
            S  F  C  S
            A  D  E  E
        输入：board = [["A","B","C","E"],["S","F","C","S"],["A","D","E","E"]], word = "ABCB"
        输出：false

    提示：
        m == board.length
        n = board[i].length
        1 <= m, n <= 6
        1 <= word.length <= 15
        board 和 word 仅由大小写英文字母组成

 */
public class WordSearch {

    /*
        回溯法
        结果：
            102 ms, 54.88%
            38.8 MB, 59.28%
     */
    public boolean exist(char[][] board, String word) {
        int m = board.length;
        int n = board[0].length;
        // 定义用过的数组
        boolean[][] used = new boolean[m][n];

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (backtrace(board, word, i, j, 0, used)) {
                    return true;
                }
            }
        }
        return false;
    }

    private boolean backtrace(char[][] board, String word, int i, int j, int index, boolean[][] used) {
        // 找到了，返回 true
        if (index == word.length()) {
            return true;
        }
        // 越界返回 false
        if (i >= board.length || i < 0 || j < 0 || j >= board[0].length) {
            return false;
        }
        if (used[i][j]) {
            return false;
        }

        if (word.charAt(index) != board[i][j]) {
            return false;
        }
        used[i][j] = true;
        // 查找
        boolean res = backtrace(board, word, i + 1, j, index + 1, used) ||
                backtrace(board, word, i - 1, j, index + 1, used) ||
                backtrace(board, word, i, j + 1, index + 1, used) ||
                backtrace(board, word, i, j - 1, index + 1, used);
        // 回溯
        used[i][j] = false;
        return res;
    }
}
