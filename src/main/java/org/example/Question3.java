package org.example;

//Longest Palindromic Substring
//Given a string s, return the longest
//palindromic
//
//        substring
//in s.
//
//
//
//Example 1:
//
//Input: s = "babad"
//Output: "bab"
//Explanation: "aba" is also a valid answer.
//        Example 2:
//
//Input: s = "cbbd"
//Output: "bb"
public class Question3 {
    public String longestPalindrome(String s) {
        int len = s.length();
        if(len<2)
            return s;
        int ans = 0;
        int start = -1;
        int[]temp = new int[]{ans, start};
        for(int i=0;i<len-1; i++){
            helper(s, i, i, temp);
            helper(s, i, i+1,temp);
        }
        if(temp[1]==-1)
            return "";
        return s.substring(temp[1], temp[1]+temp[0]);
    }
    public void helper(String str, int i, int j,int[]temp){
        while(i>=0 && j<str.length() && str.charAt(i)==str.charAt(j)){
            i--;
            j++;
        }
        if(temp[0]<j-i-1){
            temp[0] = j-i-1;
            temp[1] = i+1;
        }
    }

}
