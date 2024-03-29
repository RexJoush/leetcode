package y2021.m03March.day174DivisorGame;

/**
 * @author Rex Joush
 * @time 2021.03.30
 */

/*
    除数博弈
    https://leetcode-cn.com/problems/divisor-game/

    爱丽丝和鲍勃一起玩游戏，他们轮流行动。爱丽丝先手开局。
    最初，黑板上有一个数字 N 。在每个玩家的回合，玩家需要执行以下操作：
    选出任一 x，满足 0 < x < N 且 N % x == 0 。
    用 N - x 替换黑板上的数字 N 。
    如果玩家无法执行这些操作，就会输掉游戏。
    只有在爱丽丝在游戏中取得胜利时才返回 True，否则返回 False。假设两个玩家都以最佳状态参与游戏。

    示例 1：
        输入：2
        输出：true
        解释：爱丽丝选择 1，鲍勃无法进行操作。
    示例 2：
        输入：3
        输出：false
        解释：爱丽丝选择 1，鲍勃也选择 1，然后爱丽丝无法进行操作。

    提示：
        1 <= N <= 1000

 */
public class DivisorGame {

    /*
        博弈论的思想
        即参与游戏的两个人都足够聪明且会选择最有利于自己的情况
        考虑游戏进行到最后的情况
        输入为 2 时，先手取 1，必赢。
        输入为 3 时，先手可取 1，后手取 1，后手必赢
        输入为 4 时，先手那么可取 1，2，当取 1 时，进入为 3，即必输，所以取 2 进入情况 2 先手必赢
        输入为 5 时，可取，进入情况 4，则先手必输
        输入为 6 时，可取，1，2，3，考虑先手取 1，进入情况 5，则先手必赢
        ···
        可以观察到，当为偶数时，总可以先取 1，进入奇数，导致先手必赢
        同理，当为奇数时，即无论如何取，n 的因子一定为奇数，n - x 结果即为偶数，先手必输

        结果：
            0 ms, 100.00%
            35.1 MB, 50.77%
     */
    public boolean divisorGame(int n) {
        return n % 2 == 0;
    }
}
