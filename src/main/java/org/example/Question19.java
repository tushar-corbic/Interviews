
120. Triangle

Given a triangle array, return the minimum path sum from top to bottom.

For each step, you may move to an adjacent number of the row below. More formally, if you are on index i on the current row, you may move to either index i or index i + 1 on the next row.



nput: triangle = [[2],[3,4],[6,5,7],[4,1,8,3]]
Output: 11
Explanation: The triangle looks like:
   2
  3 4
 6 5 7
4 1 8 3
The minimum path sum from top to bottom is 2 + 3 + 5 + 1 = 11 (underlined above).

Input: triangle = [[-10]]
Output: -10


class Solution {
   public:
    int minimumTotal(vector<vector<int>>& triangle) {
        int n = triangle.size();
        vector<vector<int>> dp(n, vector<int>(n, -1));
        for (int j = 0; j < n; j++) dp[n - 1][j] = triangle[n - 1][j];
        for (int i = n - 2; i >= 0; i--) {
            for (int j = 0; j < i + 1; j++) {
                int lower_left = triangle[i][j] + dp[i + 1][j];
                int lower_right = triangle[i][j] + dp[i + 1][j + 1];
                dp[i][j] = min(lower_left, lower_right);
            }
        }
        return dp[0][0];
    }
};


class Solution {
   public:
    int minimumTotal(vector<vector<int>>& triangle) {
        int n = triangle.size();
        vector<int> next_row(triangle[n - 1]);
        vector<int> curr_row(n, 0);
        for (int i = n - 2; i >= 0; i--) {
            for (int j = 0; j < i + 1; j++) {
                int lower_left = triangle[i][j] + next_row[j];
                int lower_right = triangle[i][j] + next_row[j + 1];
                curr_row[j] = min(lower_left, lower_right);
            }
            swap(curr_row, next_row);
        }
        return next_row[0];  // because we swapped at last iteration
    }
};
