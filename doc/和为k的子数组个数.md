# 和为k的子数组个数
给定一个整数数组 nums 和一个整数 k ，请你统计并返回 该数组中和为 k 的子数组的个数。
子数组是数组中元素的连续非空序列。

## 解题思路
输入一个整数数组和一个目标值，需要找出所有连续子数组之和等于目标值的子数组数量。
    也就是说，需要一个容器来存储前缀和，如果用暴力法遍历所有子数组，会严重影响性能，可以用哈希表来存储前缀和及其出现次数。
怎么找到目标子数组呢？
    在一维数组这种线性结构中，可以利用前缀和的思想，通过一次遍历数组，同时维护一个哈希表来记录前缀和及其出现次数。
    当遍历到第i个元素时，当前的前缀和为currentSum，如果存在某个前缀和等于currentSum - k，那么说明从那个前缀和的位置到当前位置的子数组和为k。

#### 代码体

```java
import java.util.HashMap;
import java.util.Map;

public class Solution {
    public int subarraySum(int[] nums, int k) {
        // 使用哈希表存储前缀和出现的次数
        Map<Integer, Integer> prefixSumCount = new HashMap<>();
        // 初始前缀和为0，出现1次
        prefixSumCount.put(0, 1);

        int count = 0;
        int currentSum = 0;

        for (int num : nums) {
            // 计算当前前缀和
            currentSum += num;
            // 查找是否存在前缀和等于 currentSum - k
            count += prefixSumCount.getOrDefault(currentSum - k, 0);
            // 更新哈希表中当前前缀和的次数
            prefixSumCount.put(currentSum, prefixSumCount.getOrDefault(currentSum, 0) + 1);
        }

        return count;
    }
}
```

#### 复杂度分析
时间复杂度：O(n)，其中n是数组中的元素数量。我们只需要遍历数组一次，哈希表的插入和查找操作平均时间复杂度为O(1)。
空间复杂度：O(n)，主要用于存储前缀和哈希表。最坏情况下，所有前缀和都不相同，需要存储n个不同的前缀和。
