package December20.day75DecompressEncodedList;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

/**
 * @author Joush
 * @time 2020.12.21
 */

/*
    解压缩编码列表
    https://leetcode-cn.com/problems/decompress-run-length-encoded-list/

    给你一个以行程长度编码压缩的整数列表 nums 。
    考虑每对相邻的两个元素 [freq, val] = [nums[2*i], nums[2*i+1]] （其中 i >= 0 ）
    每一对都表示解压后子列表中有 freq 个值为 val 的元素，你需要从左到右连接所有子列表以生成解压后的列表。
    请你返回解压后的列表。

    示例 1：
        输入：nums = [1,2,3,4]
        输出：[2,4,4,4]
        解释：第一对 [1,2] 代表着 2 的出现频次为 1，所以生成数组 [2]。
        第二对 [3,4] 代表着 4 的出现频次为 3，所以生成数组 [4,4,4]。
        最后将它们串联到一起 [2] + [4,4,4] = [2,4,4,4]。
    示例 2：
        输入：nums = [1,1,2,3]
        输出：[1,3,3]

    提示：
        2 <= nums.length <= 100
        nums.length % 2 == 0
        1 <= nums[i] <= 100

 */
public class DecompressEncodedList {

    /*
        遍历两遍，第一次计算结果的长度，第二次遍历生成结果数组
        结果：
            1 ms, 98.66%
            39.3 MB, 43.73%
     */
    public int[] decompressRLElist(int[] nums) {
        // 结果数组长度
        int length = 0;

        // 遍历数组，计算结果数组长度
        for (int i = 0; i < nums.length; i += 2){
            length += nums[i];
        }

        int[] result = new int[length];
        int index = 0;

        // 以步长为 2 进行遍历数组
        for (int i = 0; i < nums.length; i+=2) {
            // 将数字放入数组中
            for (int j = 0; j < nums[i]; j++){
                result[index++] = nums[i+1];
            }
        }
        // 返回结果
        return result;

    }
}
