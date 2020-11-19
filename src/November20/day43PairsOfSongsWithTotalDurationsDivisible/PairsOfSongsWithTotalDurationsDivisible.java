package November20.day43PairsOfSongsWithTotalDurationsDivisible;

/**
 * @author Joush
 * @time 2020.11.19
 */

/*
    总持续时间可被 60 整除的歌曲
    https://leetcode-cn.com/problems/pairs-of-songs-with-total-durations-divisible-by-60/

    在歌曲列表中，第 i 首歌曲的持续时间为 time[i] 秒。
    返回其总持续时间（以秒为单位）可被 60 整除的歌曲对的数量。形式上，我们希望索引的数字 i 和 j 满足  i < j 且有 (time[i] + time[j]) % 60 == 0。

    示例 1：
        输入：[30,20,150,100,40]
        输出：3
        解释：这三对的总持续时间可被 60 整数：
        (time[0] = 30, time[2] = 150): 总持续时间 180
        (time[1] = 20, time[3] = 100): 总持续时间 120
        (time[1] = 20, time[4] = 40): 总持续时间 60
    示例 2：
        输入：[60,60,60]
        输出：3
        解释：所有三对的总持续时间都是 120，可以被 60 整数。

    提示：
        1 <= time.length <= 60000
        1 <= time[i] <= 500

 */
public class PairsOfSongsWithTotalDurationsDivisible {

    public static void main(String[] args) {
        int[] time = new int[]{30,20,150,100,40};

        System.out.println(new PairsOfSongsWithTotalDurationsDivisible().numPairsDivisibleBy60(time));

    }

    /*
        - 整数对60取模，可能有60种余数。故初始化一个长度为60的数组，统计各余数出现的次数。
        - 遍历time数组，每个值对60取模，并统计每个余数值（0-59）出现的个数。因为余数部分需要找到合适的cp组合起来能被60整除。
        - 余数为0的情况，只能同余数为0的情况组合（如60s、120s等等）。0的情况出现k次，则只能在k中任选两次进行两两组合。本题解单独写了个求组合数的方法，也可以用k * (k - 1) / 2表示。
        - 余数为30的情况同上。
        - 其余1与59组合，2与58组合，故使用双指针分别从1和59两头向中间遍历。1的情况出现m次，59的情况出现n次，则总共有m*n种组合。
     */

    public int numPairsDivisibleBy602(int[] time) {

        int count = 0;
        int[] seconds = new int[60];
        for(int t : time) {
            seconds[t % 60] += 1;
        }
        count += combination(seconds[30], 2);
        count += combination(seconds[0], 2);
        int i = 1, j = 59;
        while(i < j) {
            count += seconds[i++] * seconds[j--];
        }
        return count;

    }
    // 求组合数
    public int combination(int n, int k) {
        long result = 1;
        for(int i = 1; i <= k; i++) {
            result = result * (n - i + 1) / i;
        }
        return (int)result;
    }


    /*
        遍历每一对组合，n2级，第32个用例会超出限制
     */
    public int numPairsDivisibleBy60(int[] time) {

        int count = 0;

        for (int i = 0; i < time.length; i++){
            for (int j = i + 1; j < time.length; j++){
                if ((time[i] + time[j]) % 60 == 0){
                    count++;
                }
            }
        }

        return count;

    }

}
