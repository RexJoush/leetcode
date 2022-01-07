package y2021.m03March.day153LemonadeChange;

/**
 * @author Rex Joush
 * @time 2021.03.09
 */

/*
    柠檬水找零
    https://leetcode-cn.com/problems/lemonade-change/

    在柠檬水摊上，每一杯柠檬水的售价为 5 美元。顾客排队购买你的产品，（按账单 bills 支付的顺序）一次购买一杯。
    每位顾客只买一杯柠檬水，然后向你付 5 美元、10 美元或 20 美元。你必须给每个顾客正确找零，也就是说净交易是每位顾客向你支付 5 美元。
    注意，一开始你手头没有任何零钱。
    给你一个整数数组 bills ，其中 bills[i] 是第 i 位顾客付的账。如果你能给每位顾客正确找零，返回 true ，否则返回 false 。

    示例 1：
        输入：bills = [5,5,5,10,20]
        输出：true
        解释：
        前 3 位顾客那里，我们按顺序收取 3 张 5 美元的钞票。
        第 4 位顾客那里，我们收取一张 10 美元的钞票，并返还 5 美元。
        第 5 位顾客那里，我们找还一张 10 美元的钞票和一张 5 美元的钞票。
        由于所有客户都得到了正确的找零，所以我们输出 true。
    示例 2：
        输入：bills = [5,5,10,10,20]
        输出：false
        解释：
        前 2 位顾客那里，我们按顺序收取 2 张 5 美元的钞票。
        对于接下来的 2 位顾客，我们收取一张 10 美元的钞票，然后返还 5 美元。
        对于最后一位顾客，我们无法退回 15 美元，因为我们现在只有两张 10 美元的钞票。
        由于不是每位顾客都得到了正确的找零，所以答案是 false。
    示例 3：
        输入：bills = [5,5,10]
        输出：true
    示例 4：
        输入：bills = [10,10]
        输出：false

    提示：
        1 <= bills.length <= 10^5
        bills[i] 不是 5 就是 10 或是 20

 */
public class LemonadeChange {

    /*
        模拟顺序操作即可
        结果：
            1 ms, 100.00%
            48.8 MB, 58.67%
     */
    public boolean lemonadeChange(int[] bills) {

        int five = 0; // 5 块的数量
        int ten = 0; // 10 块的数量

        for (int bill : bills) {
            switch (bill) {
                // 遇见 5 块
                case 5:
                    five++;
                    break;
                // 遇见 10 块
                case 10:
                    if (five > 0) {
                        five--;
                        ten++;
                    } else {
                        return false;
                    }
                    break;

                // 遇见 20 块
                case 20:
                default:
                    // 1.首先看有没有 10 块
                    if (ten > 0) {
                        // 2.有 10 块，则看有没有 5 块
                        if (five > 0) {
                            // 有，则正确找零
                            ten--;
                            five--;
                        } else {
                            // 没有返回 false
                            return false;
                        }
                    } else {
                        // 没有 10 块，则看是否有三张 5 块
                        if (five >= 3) {
                            five -= 3;
                        } else {
                            return false;
                        }
                    }
                    break;
            }
        }
        return true;
    }

    /*
        思想和方法一一样，简便写法。
        但此种方法没有剪枝，所以效率会有所损失
        结果：
            2 ms, 89.63%
            48.9 MB, 45.89%
     */
    public boolean lemonadeChange2(int[] bills) {
        int five = 0;
        int ten = 0;
        for (int bill : bills) {
            if (bill == 5) {
                five++;
            } else if (bill == 10) {
                five--;
                ten++;
            } else if (ten > 0) {
                ten--;
                five--;
            } else {
                five -=3;
            }
            if (five < 0) {
                return false;
            }
        }
        return true;
    }

}
