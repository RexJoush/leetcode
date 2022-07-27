package y2022.m05May.day573ValidSudoku;

import java.util.HashSet;
import java.util.Set;

/**
 * @author Rex Joush
 * @time 2022.05.03
 */

/*
    有效的数独
    https://leetcode.cn/problems/valid-sudoku/
    
    请你判断一个 9 x 9 的数独是否有效。只需要 根据以下规则 ，验证已经填入的数字是否有效即可。
    数字 1-9 在每一行只能出现一次。
    数字 1-9 在每一列只能出现一次。
    数字 1-9 在每一个以粗实线分隔的 3x3 宫内只能出现一次。（请参考示例图）
    注意：
        一个有效的数独（部分已被填充）不一定是可解的。
        只需要根据以上规则，验证已经填入的数字是否有效即可。
        空白格用 '.' 表示。
    
    示例 1：
        输入：board =
        [["5","3",".",".","7",".",".",".","."]
        ,["6",".",".","1","9","5",".",".","."]
        ,[".","9","8",".",".",".",".","6","."]
        ,["8",".",".",".","6",".",".",".","3"]
        ,["4",".",".","8",".","3",".",".","1"]
        ,["7",".",".",".","2",".",".",".","6"]
        ,[".","6",".",".",".",".","2","8","."]
        ,[".",".",".","4","1","9",".",".","5"]
        ,[".",".",".",".","8",".",".","7","9"]]
        输出：true
    示例 2：
        输入：board =
        [["8","3",".",".","7",".",".",".","."]
        ,["6",".",".","1","9","5",".",".","."]
        ,[".","9","8",".",".",".",".","6","."]
        ,["8",".",".",".","6",".",".",".","3"]
        ,["4",".",".","8",".","3",".",".","1"]
        ,["7",".",".",".","2",".",".",".","6"]
        ,[".","6",".",".",".",".","2","8","."]
        ,[".",".",".","4","1","9",".",".","5"]
        ,[".",".",".",".","8",".",".","7","9"]]
        输出：false
    解释：除了第一行的第一个数字从 5 改为 8 以外，空格内其他数字均与 示例1 相同。 但由于位于左上角的 3x3 宫内有两个 8 存在, 因此这个数独是无效的。

    提示：
        board.length == 9
        board[i].length == 9
        board[i][j] 是一位数字（1-9）或者 '.'

 */
public class ValidSudoku {

    /*
        优化的暴力判断
        对于 i, j 对应的小单元格为 i/3, j/3
        结果：
            1 ms, 99.24%
            41.2 MB, 78.10%
     */
    public boolean isValidSudoku2(char[][] board) {
        int[][] rows = new int[9][9];
        int[][] cols = new int[9][9];
        int[][][] cell = new int[3][3][9];

        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                char c = board[i][j];
                if (c == '.') {
                    continue;
                }
                int index = c - '0' - 1;
                rows[i][index]++; // 行当前数字 + 1
                cols[j][index]++; // 列当前数字 + 1
                cell[i / 3][j / 3][index]++; // 小九宫格当前数字 + 1
                // 某一个超过一个就返回 false
                if (rows[i][index] > 1 || cols[j][index] > 1 || cell[i / 3][j / 3][index] > 1) {
                    return false;
                }
            }
        }
        return true;
    }

    /*
        暴力判断
        结果：
            3 ms, 14.12%
            41.2 MB, 71.49%
     */
    public boolean isValidSudoku(char[][] board) {
        // 判断行
        Set<Character> setRow = new HashSet<>();
        // 判断列
        Set<Character> setCol = new HashSet<>();

        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                // 判断行
                if (setRow.contains(board[i][j])) {
                    return false;
                }
                // 判断列
                if (setCol.contains(board[j][i])) {
                    return false;
                }
                if (board[i][j] != '.') {
                    setRow.add(board[i][j]);
                }
                if (board[j][i] != '.') {
                    setCol.add(board[j][i]);
                }
            }
            setRow.clear();
            setCol.clear();
        }

        // 判断九宫格
        Set<Character> setCell = new HashSet<>();
        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 3; col++) {
                for (int i = 0; i < 3; i++) {
                    for (int j = 0; j < 3; j++) {
                        if (board[i + row * 3][j + col * 3] == '.') {
                            continue;
                        }
                        if (setCell.contains(board[i + row * 3][j + col * 3])) {
                            return false;
                        }
                        setCell.add(board[i + row * 3][j + col * 3]);
                    }
                }
                setCell.clear();
            }
        }

        return true;
    }

}
