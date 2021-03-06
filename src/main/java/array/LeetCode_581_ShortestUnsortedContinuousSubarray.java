package array;

import java.util.Arrays;

public class LeetCode_581_ShortestUnsortedContinuousSubarray {
    public static void main(String[] args) {
        int list[] = {2,6,4,8,10,9,15};//5
//        int list[] = {1,3,2,4,5};//2
//        int list[] = {1,3,5,2,4};//4
//        int list[] = {2,1,5,3,4};//5
        System.out.println(findUnsortedSubarray(list));
    }
    //逆序数组在中间 好办 双指针往中间走 然后回走 求差值
    public static int findUnsortedSubarray(int[] nums) {
        int left = 0, right = nums.length - 1;
        //找左右的第一个出现逆的位置
        while (left + 1 <= nums.length - 1 && nums[left] <= nums[left + 1])
            left++;
        while (right - 1 >= 0 && nums[right] >= nums[right - 1])
            right--;
        if(left >= right)//没有逆
            return 0;
        else{
            int min = Integer.MAX_VALUE, max = Integer.MIN_VALUE;
            for (int i = left; i <= right; i++) {
                min = Math.min(min, nums[i]);
                max = Math.max(max, nums[i]);
            }
            //有逆左右往回找 必须右边大于中间总体的最大值 左边小于中间总体最小值
            while (left - 1 >= 0 && nums[left - 1] > min) left--;
            while (right + 1 <= nums.length - 1 && nums[right + 1] < max) right++;
            return right - left + 1;
        }
    }

    public static int findUnsortedSubarray2(int[] nums) {
        int res = 0, start = -1, n = nums.length;
        for (int i = 1; i < n; ++i) {
            if (nums[i] < nums[i - 1]) {
                int j = i;
                while (j > 0 && nums[j] < nums[j - 1]) {
                    swap(nums, j, j - 1);
                    --j;
                }
                if (start == -1 || start > j) start = j;
                res = Math.max(res, i - start + 1);
            }
        }
        return res;
    }

    public static void swap(int nums[], int left, int right){
        int temp = nums[left];
        nums[left] = nums[right];
        nums[right] = temp;
    }

    /**
     * 我们新一个跟原数组一摸一样的数组，然后排序。
     * 从数组起始位置开始，两个数组相互比较，
     * 当对应位置数字不同的时候停止，同理再从末尾开始，
     * 对应位置上比较，也是遇到不同的数字时停止，
     * 这样中间一段就是最短无序连续子数组了
     */
    public static int findUnsortedSubarray3(int[] nums) {
        int n = nums.length, i = 0, j = n - 1;
        int[] t = nums.clone();
        Arrays.sort(t);
        while (i < n && nums[i] == t[i]) ++i;
        while (j > i && nums[j] == t[j]) --j;
        return j - i + 1;
    }
    //精彩
    public static int findUnsortedSubarray4(int[] nums) {
        int n = nums.length, start = -1, end = -2;
        int min = nums[n - 1], max = nums[0];
        for (int i = 1; i < n; ++i) {
            max = Math.max(max, nums[i]);
            min = Math.min(min, nums[n - 1 - i]);
            if (max > nums[i]) end = i;//左边有数更大
            if (min < nums[n - 1 - i]) start = n - 1 - i;//右边有数更小
        }
        return end - start + 1;
    }
}
