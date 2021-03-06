package dp.other;

/**
 * @author ZhangDaPang 285296372@qq.com
 * @date 2018/7/6 14:39
 */

import util.ZDaPangArrayUtil;

/**
 * 经典问题:最长公共子序列 Longest Common Sequence
 * 1:ABCBDAB
 * 2:BDCABA
 * Answer:BCBA 长度4
 * LeetCode上没有
 */
public class LCS {
    public static void main(String[] args) {
        String x = "ABCBDAB";
        String y = "BDCABA";
        System.out.println("longestCS_dp");
        System.out.println(longestCS_dp(x, y));
        System.out.println("longestCS_dp_betterlook");
        System.out.println(longestCS_dp_betterlook(x, y));
    }

    /**
     * 状态转换方程
     * if(x[m] == y[n])
     *  dp[m][n] = dp[m-1][n-1] + 1;
     * else
     *  dp[m][n] = max(dp[m-1][n],dp[m][n-1]);
     *
     *  dp[m][n] 代表x长度为x[m]与x[n]的最长公共子序列长度
     */
    public static int longestCS_dp(String x, String y){
        char[] xList = x.toCharArray();
        char[] yList = y.toCharArray();
        int dp[][] = new int[xList.length][yList.length];

        /**
         * 横向扫描 初始化第一行和第一例
         * 默认是0 匹配上为1
         */
        for (int i = 1; i < yList.length; i++)
            if(xList[0] == yList[i])
                dp[0][i] = 1;
            else
                dp[0][i] = dp[0][i - 1];

        for (int i = 1; i < xList.length; i++)
            if(yList[0] == xList[i] )
                dp[i][0] = 1;
            else
                dp[i][0] = dp[i - 1][0];

        /**
         * 开始根据方程递推
         */
        for (int i = 1; i < xList.length; i++) {
            for (int j = 1; j < yList.length; j++) {
                if(xList[i] == yList[j])
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                else
                    dp[i][j] = Math.max(dp[i - 1][j],dp[i][j - 1]);
            }
        }

        ZDaPangArrayUtil.printArray2(dp);

        return dp[xList.length - 1][yList.length - 1];
    }

    /**
     * 状态转换方程:
     * if(x[m-1] == y[n-1])
     *  dp[m][n] = dp[m-1][n-1] + 1;
     * else
     *  dp[m][n] = max(dp[m-1][n],dp[m][n-1]);
     *
     *  dp[m][n] 代表x长度为x[m-1]与x[n-1]的最长公共子序列长度
     *
     * 另一种划分方式
     * 增加一行一列 动态推导出初始化的行列 代码更加简洁
     */
    public static int longestCS_dp_betterlook(String x, String y){
        char[] xList = x.toCharArray();
        char[] yList = y.toCharArray();
        int m = xList.length;
        int n = yList.length;
        int dp[][] = new int[m + 1][n + 1];

        /**
         * 开始根据方程递推
         */
        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                if(xList[i - 1] == yList[j - 1])
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                else
                    dp[i][j] = Math.max(dp[i - 1][j],dp[i][j - 1]);
            }
        }

        ZDaPangArrayUtil.printArray2(dp);

        return dp[m][n];
    }
}
