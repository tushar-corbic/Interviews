5. Longest Palindromic Substring

Input: s = "babad"
Output: "bab"
Explanation: "aba" is also a valid answer.

class Solution {
    public String longestPalindrome(String s) {
        int n = s.length();
        if(n<2)
            return s;
        int maxLength = 1;
        int start = 0;
        int low, high;
        for(int i = 0; i<n; i++){
             low = i-1;
            high = i+1;
            while(low>=0 && s.charAt(low)==s.charAt(i))
             low--;
            while(high<n && s.charAt(high)==s.charAt(i))
                high++;
            while(low>=0 && high<n && s.charAt(low)==s.charAt(high)){
                low--;
                high++;
            }
            int temp = high-low-1;
            if(temp>maxLength){
                maxLength = temp;
                start = low+1;
            }
        }
        return s.substring(start, start+maxLength);
    }
}
