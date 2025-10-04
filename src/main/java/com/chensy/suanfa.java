package com.chensy;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class suanfa {
    //    class Solution {
//        public List<Integer> partitionLabels(String s) {
//            int[] last=new int[26];
//            char[] cs = s.toCharArray();
//            for(int i=0;i<cs.length;i++) last[cs[i]-'a']=i;
//            int left=0,right=0;
//            List<Integer> res=new ArrayList<>();
//            for(int i=0;i<cs.length;i++){
//                right=Math.max(right,last[cs[i]-'a']);
//                if(i==right){
//                    res.add(right-left+1);
//                    left=right+1;
//                }
//            }
//            return res;
//        }
//    }
    class soluton1 {
        public List<Integer> partitionalLabels(String s) {
            int[] last = new int[26];//将所有的26个字母映射到一个数组中
            char[] arr = s.toCharArray();//将原本的字符串转变成一个字符数组来进行遍历
            for (int i = 0; i < arr.length; i++) {
                last[arr[i] - 'a'] = i;//通过循环来确定每一个字母所对应的最大索引
            }
            int left = 0, right = 0;//初始化分区的左右边界
            List<Integer> result = new ArrayList<>();//新建一个ArrayList来记录每一个分区的长度
            for (int i = 0; i < arr.length; i++) {
                right = Math.max(right, last[arr[i] - 'a']);//动态地判断更新分区的右边界例如abcdefab
                // 这一串字符中只有b的索引大于第一次确定的右边界会使得右边界发生改变
                if (i == right) {
                    result.add(i);//当i == 右边界时说明此分区已经可以结束了，后面需要更改左边界使得开始下一个分区
                    left = right + 1;
                }
            }
            return result;
        }

    }

class solution2{
        public String longestPalindrome(String s) {
            int len = s.length();
            if (len <= 1) {
                return s;
            }
            int start = 0;
            int maxLen = 1;
            char[] arr = s.toCharArray();
            for (int i = 0; i < len; i++) {
                int len1 = expandAroundCenter(arr, i, i);//一个字符为回文中心的情况
                int len2 = expandAroundCenter(arr, i, i + 1);//两个字符为回文中心的情况
                int currentLen = Math.max(len1, len2);
                if (currentLen > maxLen) {
                    maxLen = currentLen;
                    start = i - (currentLen - 1) / 2;
                }

            }
            return s.substring(start, start + maxLen);
        }

        //当start与end在expandAroundCenter方法中传递相同的值时既是用一个字符作为回文中心，当传递的值相差为1时既是用两个字符作为回文中心
        private int expandAroundCenter(char[] arr, int start, int end){
            while(start>0 && end<arr.length &&  arr[start] == arr[end]){
                start--;
                end++;
            }
            int currentLen = end - start + 1;
            return currentLen;



        }

        //思路交换nums[i]与第一个大于它的数字nums[j]的位置，并且将新的nums[j]往后的数字都按照升序排列
        class Solution3 {
        public void nextPermutation(int[] nums) {
            int len = nums.length;
            int i = len - 2;
            // 步骤1: 从后向前找到第一个升序对
            while (i >= 0 && nums[i] >= nums[i + 1]) {
                i--;
            }
            if (i >= 0) {
                int j = len - 1;
                // 步骤2: 从后向前找到第一个大于nums[i]的元素
                while (nums[j] <= nums[i]) {
                    j--;
                }
                // 交换nums[i]和nums[j]
                swap(nums, i, j);
            }
            // 步骤3: 反转i之后的元素
            reverse(nums, i + 1);

            System.out.println(Arrays.toString(nums));
        }
        //交换nums[i]与第一个大于它的数nums[j]的位置
        private void swap(int[] nums, int i, int j) {
            int temp = nums[i];
            nums[i] = nums[j];
            nums[j] = temp;
        }

        //实现将num[i]后面的数按照升序排列
        //这里我开始没想明白它是怎么实现升序排序的，实际上是因为我没考虑到第一个swap方法的实现逻辑已经保证原本的数字是按照降序排列了
        //正是因为有降序排列的前提才能实现
        private void reverse(int[] nums, int start) {
            int left = start;
            int right = nums.length - 1;
            while (left < right) {
                swap(nums, left, right);
                left++;
                right--;
            }
        }
    }
}
}








