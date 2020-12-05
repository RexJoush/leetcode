package December20.day58TrisectionNums;

/**
 * @author Joush
 * @time 2020.11.04
 */

/*
    将数组分成和相等的三个部分
    https://leetcode-cn.com/problems/partition-array-into-three-parts-with-equal-sum/
    
    给你一个整数数组 A，只有可以将其划分为三个和相等的非空部分时才返回 true，否则返回 false。
    形式上，如果可以找出索引 i+1 < j 且满足
    A[0] + A[1] + ... + A[i] == A[i+1] + A[i+2] + ... + A[j-1] == A[j] + A[j-1] + ... + A[A.length - 1]
    就可以将数组三等分。

    示例 1：
        输入：[0,2,1,-6,6,-7,9,1,2,0,1]
        输出：true
        解释：0 + 2 + 1 = -6 + 6 - 7 + 9 + 1 = 2 + 0 + 1
    示例 2：
        输入：[0,2,1,-6,6,7,9,-1,2,0,1]
        输出：false
    示例 3：
        输入：[3,3,6,5,-2,2,5,1,-9,4]
        输出：true
        解释：3 + 3 = 6 = 5 - 2 + 2 + 5 + 1 - 9 + 4

    提示：
    3 <= A.length <= 50000
    -10^4 <= A[i] <= 10^4

 */
public class TrisectionNums {

    /*
        第一遍遍历，计算数组的和，除以 3 后得到目标值，从数组起始开始累加，遇到目标值则计数器 +1，最后如果正好达到3组，则返回 true
     */
    public boolean canThreePartsEqualSum(int[] A) {

        // 计算数组的和
        int sum = 0;
        for (int i : A) {
            sum += i;
        }

        // 如果和不能被3除尽，则直接返回 false
        if (sum % 3 != 0){
            return false;
        }

        // 计算目标值
        int target = sum / 3;
        int count = 0;
        sum = 0;
        for (int j : A) {
            // 当前累计不等于 target，就继续加
            if (sum != target) {
                sum += j;
            }
            // 累计够了 target，就将 sum 置零
            if (sum == target) {
                sum = 0;
                count++;
            }
        }

        /*
            当出现 target == 0 时，任意个数个子组合均可表示 0，所以只要大于4个即可
            至于为什么此处大于4，因为 target == 0，所以第一个数进入循环时，因为 sum 初始值为0，所以 count 多记了一次，故写大于 4
         */
        if (count > 4 && target == 0){
            return true;
        }

        // 如果正好有三组，则返回正确，否则返回错误
        return count == 3;
    }
}
