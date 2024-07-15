package org.example;
////Egg Drop With 2 Eggs and N Floors
//ou are given two identical eggs and you have access to a building with n floors labeled from 1 to n.
//
//You know that there exists a floor f where 0 <= f <= n such that any egg dropped at a floor higher than f will break, and any egg dropped at or below floor f will not break.
//
//In each move, you may take an unbroken egg and drop it from any floor x (where 1 <= x <= n). If the egg breaks, you can no longer use it. However, if the egg does not break, you may reuse it in future moves.
//
//Return the minimum number of moves that you need to determine with certainty what the value of f is.
public class Question2 {
    static int[] dp= new int[1001];
    public int twoEggDrop(int n) {
        if (dp[n] == 0)
            for (int i = 1; i <= n; ++i)
                dp[n] = Math.min(dp[n] == 0 ? n : dp[n], 1 + Math.max(i - 1, twoEggDrop(n - i)));
        return dp[n];
    }
}
