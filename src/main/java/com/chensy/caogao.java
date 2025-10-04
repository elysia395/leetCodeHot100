package com.chensy;

import java.util.*;

public class caogao {


//        if (heights == null || heights.length == 0) {
//            return 0;
//        }
//
//        int maxArea = 0;
//        int n = heights.length;
//        // 单调递增栈，存储柱子的索引
//        Deque<Integer> stack = new ArrayDeque<>();
//
//        for (int i = 0; i < n; i++) {
//            // 当当前柱子高度小于栈顶柱子高度时，弹出栈顶元素并计算面积
//            while (!stack.isEmpty() && heights[i] < heights[stack.peek()]) {
//                // 弹出栈顶元素，该元素对应的柱子高度作为矩形高度
//                int height = heights[stack.pop()]; //stack.pop记录的是索引
//                // 计算宽度：如果栈为空，说明左侧没有比当前高度小的柱子，宽度为i
//                // 否则宽度为当前索引与新栈顶索引的差值
//                int width = stack.isEmpty() ? i : i - stack.peek() - 1;
//                // 更新最大面积
//                maxArea = Math.max(maxArea, height * width);
//            }
//            // 将当前柱子索引入栈
//            stack.push(i);
//        }
//
//        // 处理栈中剩余的元素
//        while (!stack.isEmpty()) {
//            int height = heights[stack.pop()];
//            // 右侧没有比当前高度小的柱子，宽度为n
//            int width = stack.isEmpty() ? n : n - stack.peek() - 1;
//            maxArea = Math.max(maxArea, height * width);
//        }
//
//        return maxArea;
public int largestRectangleArea(int[] heights) {
    if(heights==null||heights.length==0){
        return 0;
    }
    int len=heights.length;
    int maxArea = 0;
    //创建一个栈对象
    Deque<Integer> stack = new ArrayDeque<>();
    for ( int i = 0; i<len; i++ ){
        while(!stack.isEmpty() && heights[i] < heights[stack.peek()]){
            int height=stack.pop();
            int width = stack.isEmpty()?i : i-stack.peek()-1;
            int area = height*width;
            maxArea = Math.max(maxArea, area);
        }
        stack.push(i);
    }
    while(!stack.isEmpty()){
        int height=stack.pop();
        int width = stack.isEmpty()?len:len-stack.peek()-1;
        maxArea = Math.max(maxArea, height*width);
    }
    return maxArea;
  }


    public int[] dailyTemperatures(int[] temperatures) {
        if (temperatures == null || temperatures.length == 0) {
            return new int[0];
        }

        int len = temperatures.length;
        Deque<Integer> stack = new ArrayDeque<>();
        int[] result = new int[len]; // 结果数组长度与输入相同

        for (int i = 0; i < len; i++) {
            // 当栈不为空且当前温度大于栈顶索引对应的温度时
            while (!stack.isEmpty() && temperatures[i] > temperatures[stack.peek()]) {
                int prevIndex = stack.pop();
                result[prevIndex] = i - prevIndex; // 计算天数差
            }
            stack.push(i); // 存储索引而非温度值
        }

        // 栈中剩余元素对应的结果保持为0（已初始化）
        return result;
    }

//    public List<List<Integer>> threeSum(int[] nums) {
//        List<List<Integer>> res = new ArrayList<>();
//        Arrays.sort(nums);//使用方法先将原本的数组升序排列
//
//        //这李的len-2很关键，因为解法采用的双指针的方法，在确定一个索引的情况下去找另外两个
//        //首先左侧的至少为i+1，右侧的最大为n-1，i-1<n-1即i<n-2
//        for (int i = 0; i < nums.length - 2; i++)
//        {
//            if (i > 0 && nums[i] == nums[i - 1])
//                continue;//跳过重复的数字
//            int min = i+1;
//            int max = nums.length-1;
//            while(min<max){
//                int sum = nums[i] + nums[min] + nums[max];
//                if(sum == 0) {
//                    res.add(Arrays.asList(nums[i],nums[min],nums[max]));
//                    //后面的两步骤为跳过重复
//                    while (min<max && nums[min] == nums[++min]) min++;
//                    while (min<max && nums[max] == nums[--max]) max--;
//                }
//                    res.add(Arrays.asList(nums[i], nums[min], nums[max]));
//
//                    // 修正：先移动指针，再跳过重复值（关键修复点）
//                    min++;
//                    max--;
//                }
//                // 跳过左指针的重复值
//                while (min < max && nums[min] == nums[min - 1]) {
//                    min++;
//                }
//                // 跳过右指针的重复值
//                while (min < max && nums[max] == nums[max + 1]) {
//                    max--;
//                }
//                 if(sum > 0){
//                    max = max-1;
//                } else if (sum < 0){
//                    min = min+1;
//                }
//            }
//        }
//        return res;
//
//    }




        public List<List<Integer>> threeSum(int[] nums) {
            List<List<Integer>> res = new ArrayList<>();
            Arrays.sort(nums); // 先将数组升序排列

            for (int i = 0; i < nums.length - 2; i++) {
                if (i > 0 && nums[i] == nums[i - 1]) {
                    continue; // 跳过重复的数字
                }

                int min = i + 1;
                int max = nums.length - 1;

                while (min < max) {
                    int sum = nums[i] + nums[min] + nums[max];

                    if (sum == 0) {
                        res.add(Arrays.asList(nums[i], nums[min], nums[max]));

                        // 移动指针
                        min++;
                        max--;

                        // 跳过左指针的重复值（添加min > i + 1判断防止越界）
                        while (min > i + 1 && min < max && nums[min] == nums[min - 1]) {
                            min++;
                        }

                        // 跳过右指针的重复值（添加max < nums.length - 1判断防止越界）
                        while (max < nums.length - 1 && min < max && nums[max] == nums[max + 1]) {
                            max--;
                        }
                    } else if (sum > 0) {
                        max--;
                    } else {
                        min++;
                    }
                }
            }

            return res;
        }
//与上面类似的寻找四个数的和为0，先用两层循环固定两个数在用双指针找另外两个数，以此类推，寻找m个数的和为0时间复杂度度为O(n**m-1)
    public List<List<Integer>> fourSum(int[] nums, int target) {
        List<List<Integer>> result = new ArrayList<>();
        if (nums == null || nums.length < 4) {
            return result;
        }

        Arrays.sort(nums); // 排序数组

        int n = nums.length;

        // 第一层循环：确定第一个数
        for (int i = 0; i < n - 3; i++) {
            // 跳过第一个数的重复值
            if (i > 0 && nums[i] == nums[i - 1]) {
                continue;
            }

            // 第二层循环：确定第二个数
            for (int j = i + 1; j < n - 2; j++) {
                // 跳过第二个数的重复值
                if (j > i + 1 && nums[j] == nums[j - 1]) {
                    continue;
                }

                // 双指针寻找剩下的两个数
                int left = j + 1;
                int right = n - 1;

                while (left < right) {
                    long sum = (long) nums[i] + nums[j] + nums[left] + nums[right];

                    if (sum == target) {
                        // 找到符合条件的四元组
                        result.add(Arrays.asList(nums[i], nums[j], nums[left], nums[right]));

                        // 跳过第三个数的重复值
                        while (left < right && nums[left] == nums[left + 1]) {
                            left++;
                        }

                        // 跳过第四个数的重复值
                        while (left < right && nums[right] == nums[right - 1]) {
                            right--;
                        }

                        // 移动双指针
                        left++;
                        right--;
                    } else if (sum < target) {
                        // 和太小，左指针右移
                        left++;
                    } else {
                        // 和太大，右指针左移
                        right--;
                    }
                }
            }
        }

        return result;
    }



}

