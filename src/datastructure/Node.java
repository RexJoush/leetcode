package datastructure;

import java.util.List;

/**
 * @author Rex Joush
 * 多叉树的节点结构
 */

public class Node {
    public int val;
    public List<Node> children;

    public Node() {}

    public Node(int _val) {
        val = _val;
    }

    public Node(int _val, List<Node> _children) {
        val = _val;
        children = _children;
    }
}
