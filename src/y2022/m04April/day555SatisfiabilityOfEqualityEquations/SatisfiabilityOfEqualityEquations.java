package y2022.m04April.day555SatisfiabilityOfEqualityEquations;

/**
 * @author Rex Joush
 * @time 2022.04.15
 */

/*
    等式方程的可满足性
    https://leetcode.cn/problems/satisfiability-of-equality-equations/

    给定一个由表示变量之间关系的字符串方程组成的数组，每个字符串方程 equations[i] 的长度为 4，
    并采用两种不同的形式之一："a==b" 或"a!=b"。在这里，a 和 b 是小写字母（不一定不同），表示单字母变量名。
    只有当可以将整数分配给变量名，以便满足所有给定的方程时才返回true，否则返回 false。

    示例 1：
        输入：["a==b","b!=a"]
        输出：false
        解释：如果我们指定，a = 1 且 b = 1，那么可以满足第一个方程，但无法满足第二个方程。没有办法分配变量同时满足这两个方程。
    示例 2：
        输入：["b==a","a==b"]
        输出：true
        解释：我们可以指定 a = 1 且 b = 1 以满足满足这两个方程。
    示例 3：
        输入：["a==b","b==c","a==c"]
        输出：true
    示例 4：
        输入：["a==b","b!=c","c==a"]
        输出：false
    示例 5：
        输入：["c==c","b==d","x!=z"]
        输出：true

    提示：
        1 <= equations.length <= 500
        equations[i].length == 4
        equations[i][0] 和 equations[i][3]是小写字母
        equations[i][1] 要么是'='，要么是'!'
        equations[i][2]是'='

 */
public class SatisfiabilityOfEqualityEquations {

    /*
        并查集（Union-Find Algorithm），也叫 不相交集合（Disjoint Sets）
            1.并查集主要用于判断一对元素是否相连，他们的关系是动态添加的，这一类问题叫做动态连通性问题
            2.并查集主要支持 “合并” 与 “查询是否在同一集合” 两种操作
            3.底层的数据结果一般为数组或者哈希表，用于表示 结点 指向的父节点，初始化时指向自己
            4.合并就是把一个集合的根节点指向另一个集合的根节点，只要根节点一样，就表示在同一个集合里
            5.这种表示不相交集合的方法称之为代表元法，以每个节点的根节点作为一个集合的代表元
              并查集最知名的应用即为 最小生成树 kruskal 算法
        并查集的查找性能主要与树高有关，但随着合并的进行，树会越来越高，
        因此，对于并查集的优化主要为 “路径压缩（Path Compressed）” 和 “按秩合并”
            1.路径压缩指的是在查询过程中，更改结点的指向，，使得树高更低，一般而言有隔代压缩和完全压缩两种
                1.1 隔代压缩指的是在查找过程中，将节点指向父节点的父节点
                    只需修改一行代码  parent[x] = parent[parent[x]] 实现简单
                            0                     0                       0
                           /                     /                      /   \
                          1                     1                      1     2
                         /    4 指向父父节点    /       2 指向父父节点        /  \
                        2        --->         2           --->             3    4
                       /                     / \
                      3                     3   4
                     /
                    4
                1.2 完全压缩指的是将所有节点指向根节点
                    此时需要使用递归来实现，同时隔代压缩多执行几次也可达到完全压缩的效果
                            0                         0
                           /                     /   /  \   \
                          1                     1   2    3   4
                         /    所有都指向根节点
                        2        --->
                       /
                      3
                     /
                    4
            2.按秩合并，指的是在合并过程中，使得高度较低的树指向高度较高的树的根节点，能够保证书的高度不再增加
              当按秩合并和路径压缩时，秩 的定义很难维护，但也有参考意义
            同时使用 路径压缩 和 按秩合并 时的查找效率可以达到 O(1)

        结果：
            1 ms, 81.63%
            40.8 MB 49.46%
     */
    public boolean equationsPossible(String[] equations) {
        // 初始化一个长度为 26 的并查集
        UnionFind unionFind = new UnionFind(26);

        // 遍历所有相等的等式，将所有相等的放入相同的并查集中
        for (String equation : equations) {
            if (equation.charAt(1) == '=') {
                int x = equation.charAt(0) - 'a';
                int y = equation.charAt(3) - 'a';
                unionFind.union(x, y);
            }
        }

        // 遍历所有不相等的等式，判断是否在不同的并查集中
        for (String equation : equations) {
            if (equation.charAt(1) == '!') {
                int x = equation.charAt(0) - 'a';
                int y = equation.charAt(3) - 'a';
                // 如果在同一个连通分量中，表示矛盾，返回 false
                if (unionFind.isConnected(x, y)) {
                    return false;
                }
            }
        }
        // 满足条件
        return true;
    }

    /*
        定义并查集
     */
    private class UnionFind {
        private int[] parent; // 父节点

        // 构造容量为 n 的并查集
        public UnionFind(int n) {
            parent = new int[n];
            // 初始时每个节点的父节点为自己
            for (int i = 0; i < n; i++) {
                parent[i] = i;
            }
        }

        // 找到 n 的根节点
        public int find(int n) {
            while (n != parent[n]) {
                parent[n] = parent[parent[n]];
                n = parent[n];
            }
            return n;
        }

        /**
         * 合并两个节点
         */
        public void union(int x, int y) {
            int rootX = find(x);
            int rootY = find(y);
            parent[rootX] = rootY;
        }

        // 判断两个节点是否属于一个连通分量
        public boolean isConnected(int x, int y) {
            return find(x) == find(y);
        }
    }


    /*
        方法二，简化版的并查集
        结果：
            0 ms, 100.00
            40.7 MB, 55.28%
     */
    public boolean equationsPossible2(String[] equations) {
        int[] parent = new int[26];
        for (int i = 0; i < 26; i++) {
            parent[i] = i;
        }

        // 遍历等式，将同样的放入同一个并查集中
        for (String equation : equations) {
            if (equation.charAt(1) == '=') {
                int x = equation.charAt(0) - 'a';
                int y = equation.charAt(3) - 'a';
                union(parent, x, y);
            }
        }

        // 遍历所有不相等的等式，判断是否在不同的并查集中
        for (String equation : equations) {
            if (equation.charAt(1) == '!') {
                int x = equation.charAt(0) - 'a';
                int y = equation.charAt(3) - 'a';
                // 如果在同一个连通分量中，表示矛盾，返回 false
                if (find(parent, x) == find(parent, y)) {
                    return false;
                }
            }
        }
        return true;
    }

    // 合并
    private void union(int[] parent, int x, int y) {
        parent[find(parent, x)] = find(parent, y);
    }

    // 找到当前节点的父节点
    private int find(int[] parent, int n) {
        while (parent[n] != n) {
            // 路径压缩
            parent[n] = parent[parent[n]];
            n = parent[n];
        }
        return n;
    }

}
