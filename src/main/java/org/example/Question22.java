97. Interleaving String

Given strings s1, s2, and s3, find whether s3 is formed by an interleaving of s1 and s2.

An interleaving of two strings s and t is a configuration where s and t are divided into n and m 
substrings
 respectively, such that:

s = s1 + s2 + ... + sn
t = t1 + t2 + ... + tm
|n - m| <= 1
The interleaving is s1 + t1 + s2 + t2 + s3 + t3 + ... or t1 + s1 + t2 + s2 + t3 + s3 + ...
Note: a + b is the concatenation of strings a and b.

Input: s1 = "aabcc", s2 = "dbbca", s3 = "aadbbcbcac"
Output: true
Explanation: One way to obtain s3 is:
Split s1 into s1 = "aa" + "bc" + "c", and s2 into s2 = "dbbc" + "a".
Interleaving the two splits, we get "aa" + "dbbc" + "bc" + "a" + "c" = "aadbbcbcac".
Since s3 can be obtained by interleaving s1 and s2, we return true.

Input: s1 = "aabcc", s2 = "dbbca", s3 = "aadbbbaccc"
Output: false
Explanation: Notice how it is impossible to interleave s2 with any other string to obtain s3.
Example 3:




Here is some explanation:

DP table represents if s3 is interleaving at (i+j)th position when s1 is at ith position, and s2 is at jth position. 0th position means empty string.

So if both s1 and s2 is currently empty, s3 is empty too, and it is considered interleaving. If only s1 is empty, then if previous s2 position is interleaving and current s2 position char is equal to s3 current position char, it is considered interleaving. similar idea applies to when s2 is empty. when both s1 and s2 is not empty, then if we arrive i, j from i-1, j, then if i-1,j is already interleaving and i and current s3 position equal, it s interleaving. If we arrive i,j from i, j-1, then if i, j-1 is already interleaving and j and current s3 position equal. it is interleaving.




    public boolean isInterleave(String s1, String s2, String s3) {
        if (s1.length() + s2.length() != s3.length()) return false;
        boolean[][] visited = new boolean[s1.length() + 1][s2.length() + 1];
        Queue<int[]> queue = new LinkedList<>();
        queue.offer(new int[]{0, 0});
        while (!queue.isEmpty()) {
            int[] p = queue.poll();
            if (visited[p[0]][p[1]]) continue;
            if (p[0] == s1.length() && p[1] == s2.length()) return true;
            
            if (p[0] < s1.length() && s1.charAt(p[0]) == s3.charAt(p[0] + p[1]))
                queue.offer(new int[]{p[0] + 1, p[1]});
            if (p[1] < s2.length() && s2.charAt(p[1]) == s3.charAt(p[0] + p[1]))
                queue.offer(new int[]{p[0], p[1] + 1});
            visited[p[0]][p[1]] = true;
        }
        return false;
    }


class Solution {
    public boolean isInterleave(String s1, String s2, String s3) {
        if(s3.length()!= s2.length() + s1.length()){
            return false;
        }
        boolean [][] dp = new boolean[s1.length()+1][s2.length()+1];
        for(int i = 0; i<=s1.length(); i++){
            for(int j = 0; j<=s2.length(); j++){
                if(i==0 && j==0){
                    dp[i][j] = true;
                }
                else if(i==0){
                    dp[i][j] = (dp[i][j-1] && s2.charAt(j-1)==s3.charAt(i+j-1));
                }else if(j==0){
                    dp[i][j] = (dp[i-1][j] && s1.charAt(i-1)==s3.charAt(i+j-1));
                }else{
                    dp[i][j] = (dp[i-1][j] && s1.charAt(i-1)==s3.charAt(i+j-1) || dp[i][j-1] && s2.charAt(j-1)==s3.charAt(j+i-1));
                }
            }

        }
        return dp[s1.length()][s2.length()];

    }
}
