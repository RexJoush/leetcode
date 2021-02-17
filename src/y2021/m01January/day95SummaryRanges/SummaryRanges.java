package y2021.m01January.day95SummaryRanges;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Joush
 * @time 2021.01.10
 */

/*
    汇总区间
    https://leetcode-cn.com/problems/summary-ranges/

    给定一个无重复元素的有序整数数组 nums 。
    返回 恰好覆盖数组中所有数字 的 最小有序 区间范围列表。也就是说，nums 的每个元素都恰好被某个区间范围所覆盖，并且不存在属于某个范围但不属于 nums 的数字 x 。
    列表中的每个区间范围 [a,b] 应该按如下格式输出：
    "a->b" ，如果 a != b
    "a" ，如果 a == b

    示例 1：
        输入：nums = [0,1,2,4,5,7]
        输出：["0->2","4->5","7"]
        解释：区间范围是：
        [0,2] --> "0->2"
        [4,5] --> "4->5"
        [7,7] --> "7"
    示例 2：
        输入：nums = [0,2,3,4,6,8,9]
        输出：["0","2->4","6","8->9"]
        解释：区间范围是：
        [0,0] --> "0"
        [2,4] --> "2->4"
        [6,6] --> "6"
        [8,9] --> "8->9"
    示例 3：
        输入：nums = []
        输出：[]
    示例 4：
        输入：nums = [-1]
        输出：["-1"]
    示例 5：
        输入：nums = [0]
        输出：["0"]

    提示：
        0 <= nums.length <= 20
        -231 <= nums[i] <= 231 - 1
        nums 中的所有值都 互不相同
        nums 按升序排列

 */
public class SummaryRanges {

    /*
        StringBuilder 遍历
        结果：
            0 ms, 100.00%
            36.9 MB, 57.00%
     */
    public List<String> summaryRanges2(int[] nums) {
        List<String> ret = new ArrayList<String>();
        int i = 0;
        int n = nums.length;
        while (i < n) {
            int low = i;
            i++;
            while (i < n && nums[i] == nums[i - 1] + 1) {
                i++;
            }
            int high = i - 1;
            StringBuffer temp = new StringBuffer(Integer.toString(nums[low]));
            if (low < high) {
                temp.append("->");
                temp.append(Integer.toString(nums[high]));
            }
            ret.add(temp.toString());
        }
        return ret;
    }

    /*
        遍历即可
        结果：
            9 ms, 31.00%
            36.9 MB, 57.00%
     */
    public List<String> summaryRanges(int[] nums) {

        if (nums.length == 0) {
            return new ArrayList<>();
        }
        int temp = 0,i;
        List<String> result = new ArrayList<>();
        for (i = 0; i < nums.length - 1; i++){
            temp = nums[i];
            while (nums[i] + 1 == nums[i+1]){
                i++;
                if (i + 1 >= nums.length){
                    if (nums[i] == temp){
                        result.add(nums[i - 1]+"");
                    } else {
                        result.add(temp + "->" + nums[i]);
                    }
                    return result;
                }

            }
            if (nums[i] == temp){
                result.add(nums[i]+"");
            } else {
                result.add(temp + "->" + nums[i]);
            }
        }
        if (i != nums.length){
            result.add(nums[i]+"");
        }
        return result;
    }

}
