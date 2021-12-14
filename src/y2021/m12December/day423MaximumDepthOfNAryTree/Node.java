package y2021.m12December.day423MaximumDepthOfNAryTree;

import java.util.List;

/**
 * @author Rex Joush
 * @time 2021.12.04
 */

class Node {
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
