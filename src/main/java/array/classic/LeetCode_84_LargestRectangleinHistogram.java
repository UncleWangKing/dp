package array.classic;

import java.util.Stack;

public class LeetCode_84_LargestRectangleinHistogram {
    public static void main(String[] args) {
        int list[] = {2,2,1,5,6,4,2,3};
        System.out.println(largestRectangleArea3(list));
    }
    //暴力n^2 剪枝
    //每次取到矩形右上角再算面积
    public static int largestRectangleArea(int[] heights) {
        int res = 0;
        for (int i = 0; i < heights.length; ++i) {
            //剪枝部分
            //比右边更大的值 相当于矩形右上角
            if (i + 1 < heights.length && heights[i] <= heights[i + 1])
                continue;
            int minH = heights[i];
            for (int j = i; j >= 0 ; j--) {
                minH = Math.min(minH, heights[j]);
                int area = minH * (i - j + 1);
                res = Math.max(res, area);
            }
        }
        return res;
    }
    //单调栈
    public static int largestRectangleArea2(int[] heights) {
        int res = 0;
        Stack<Integer> st = new Stack<>();
        int list[] = new int[heights.length + 1];
        System.arraycopy(heights, 0, list,0,  heights.length);
        list[list.length - 1] = 0;
        for (int i = 0; i < list.length; ++i) {
            if (st.empty() || list[st.peek()] < list[i]) {
                st.push(i);
            } else {
                int cur = st.peek(); st.pop();
                res = Math.max(res, list[cur] * (st.empty() ? i : (i - st.peek() - 1)));
                --i;
            }
        }
        return res;
    }

    //100% 二分法
    public static int largestRectangleArea3(int[] heights) {
        return find(heights, 0, heights.length - 1);
    }

    public static int find(int[] h, int left, int right) {
        if (left > right) {
            return 0;
        }
        if (left == right) {
            return h[left];
        }

        int minIndex = left;
        boolean sorted = true;

        for (int i = left + 1; i <= right; i++) {
            if (h[i] < h[i - 1]) {
                sorted = false;
            }
            if (h[i] < h[minIndex]) {
                minIndex = i;
            }
        }

        if (sorted) {
            int max = 0;
            for (int i = left; i <= right; i++) {
                if (h[i] * (right - i + 1) > max) max = h[i] * (right - i + 1);
            }
            return max;
        } else {
            int maxLeft = find(h, left, minIndex - 1);
            int maxRight = find(h, minIndex + 1, right);
            int crossMax = h[minIndex] * (right - left + 1);
            return Math.max(Math.max(maxLeft, maxRight), crossMax);
        }
    }
}
