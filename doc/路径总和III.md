# 路径总和 III
给定一个二叉树的根节点 root ，和一个整数 targetSum ，求该二叉树里节点值之和等于 targetSum 的 路径 的数目。
路径 不需要从根节点开始，也不需要在叶子节点结束，但是路径方向必须是向下的（只能从父节点到子节点）。

## 解题思路
输入一个二叉树的根节点和一个目标值，需要找出所有节点值之和等于目标值的路径数量。
    也就是说，需要一个容器来存储节点值/前缀和，如果用逐步遍历，会损性能，可以用哈希表来存储。
怎么找到目标节点呢？
    在二叉树以及红黑树这种树结构中，往往利用dfs的走到头遍历形式和特别的回溯机制，
    并且，dfs的显示栈结构可以控制空间使用率

#### 代码体

```java

import java.util.HashMap;
import java.util.Map;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
// 二叉树节点
@NoConstructor
@AllConstructor
@Data
class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;
}

public class Solution {
    // 使用前缀和的方法来解决问题
    public int pathSum(TreeNode root, int targetSum) {
        // 使用哈希表存储路径和出现的次数
        Map<Long, Integer> prefixSumCount = new HashMap<>();
        // 初始前缀和为0，出现1次
        prefixSumCount.put(0L, 1);
        return dfs(root, 0, targetSum, prefixSumCount);
    }

    // 深度优先搜索
    private int dfs(TreeNode node, long currentSum, int targetSum, Map<Long, Integer> prefixSumCount) {
        if (node == null) {
            return 0;
        }

        // 当前路径和
        currentSum += node.val;
        // 查找是否有前缀和等于 currentSum - targetSum
        int count = prefixSumCount.getOrDefault(currentSum - targetSum, 0);

        // 更新哈希表中当前前缀和的次数
        prefixSumCount.put(currentSum, prefixSumCount.getOrDefault(currentSum, 0) + 1);

        // 递归处理左右子树
        count += dfs(node.left, currentSum, targetSum, prefixSumCount);
        count += dfs(node.right, currentSum, targetSum, prefixSumCount);

        // 回溯，恢复哈希表状态，避免影响其他路径
        prefixSumCount.put(currentSum, prefixSumCount.get(currentSum) - 1);

        return count;
    }
}
```

#### 复杂度分析
时间复杂度：O(n)，其中n是二叉树中的节点数量。每个节点只被访问一次。
空间复杂度：O(n)，主要用于存储哈希表和递归调用栈。最坏情况下（树退化为链表），递归深度为n。
