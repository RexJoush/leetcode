package y2022.m03March.day524SerializeAndDeserializeBinaryTree;

import datastructure.TreeNode;

import java.util.*;

/**
 * @author Rex Joush
 * @time 2022.03.15
 */

/*
    二叉树的序列化与反序列化（剑指 Offer 37）
    https://leetcode-cn.com/problems/serialize-and-deserialize-binary-tree/

    序列化是将一个数据结构或者对象转换为连续的比特位的操作，进而可以将转换后的数据存储在一个文件或者内存中，同时也可以通过网络传输到另一个计算机环境，采取相反方式重构得到原数据。
    请设计一个算法来实现二叉树的序列化与反序列化。这里不限定你的序列 / 反序列化算法执行逻辑，你只需要保证一个二叉树可以被序列化为一个字符串并且将这个字符串反序列化为原始的树结构。
    提示: 输入输出格式与 LeetCode 目前使用的方式一致，详情请参阅 LeetCode 序列化二叉树的格式。你并非必须采取这种方式，你也可以采用其他的方法解决这个问题。

    示例 1：
                1
              /  \
             2    3
                /   \
               4     5
        输入：root = [1,2,3,null,null,4,5]
        输出：[1,2,3,null,null,4,5]
    示例 2：
        输入：root = []
        输出：[]
    示例 3：
        输入：root = [1]
        输出：[1]
    示例 4：
        输入：root = [1,2]
        输出：[1,2]

    提示：
        树中结点数在范围 [0, 10^4] 内
        -1000 <= Node.val <= 1000

 */
public class SerializeAndDeserializeBinaryTree {

    /*
        层序遍历，结构化成数组即可
        结果：
            13 ms, 67.17%
            43.8 MB, 21.36%
     */
    // Encodes a tree to a single string.
    public String serialize(TreeNode root) {

        if (root == null) {
            return "";
        }
        StringBuilder sb = new StringBuilder();
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                // 取出当前节点
                TreeNode poll = queue.poll();
                // 如果为 -1001，即表示空节点，拼接-1001 结束即可
                if (poll.val == -1001) {
                    sb.append(poll.val).append(',');
                    continue;
                }
                // 否则，继续判断，拼接当前节点的数字加 ,
                sb.append(poll.val).append(',');
                // 左节点为空，则，将-1001入队列，表示左节点为空，否则将左节点入队列
                if (poll.left == null) {
                    queue.offer(new TreeNode( -1001));
                } else {
                    queue.offer(poll.left);
                }
                // 右节点同样的道理
                if (poll.right == null) {
                    queue.offer(new TreeNode( -1001));
                } else {
                    queue.offer(poll.right);
                }
            }
        }
        // 删除最后一个 ,
        return sb.deleteCharAt(sb.length() - 1).toString();
    }

    // Decodes your encoded data to tree.
    public TreeNode deserialize(String data) {
        if ("".equals(data)) {
            return null;
        }
        // 将字符串转成数组
        String[] split = data.split(",");
        TreeNode root = new TreeNode();
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        root.val = Integer.parseInt(split[0]);
        int index = 1;
        while (!queue.isEmpty()){
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                // 获取值
                int value = Integer.parseInt(split[index]);
                TreeNode poll = queue.poll();
                // 如果当前为 -1001表示为空，左节点置空，否则左节点为当前值，并将左节点入队
                if (value ==  -1001) {
                    poll.left = null;
                } else {
                    poll.left = new TreeNode(value);
                    queue.offer(poll.left);
                }
                // 数字后移
                index++;
                // 右侧同理变化
                value = Integer.parseInt(split[index]);
                if (value ==  -1001) {
                    poll.right = null;
                } else {
                    poll.right = new TreeNode(value);
                    queue.offer(poll.right);
                }
                index++;
            }

        }
        return root;
    }
}
