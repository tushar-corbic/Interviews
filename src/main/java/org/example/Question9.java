You are given an integer array nums. You are initially positioned at the array's first index, and each element in the array represents your maximum jump length at that position.

Return true if you can reach the last index, or false otherwise.

Input: nums = [2,3,1,1,4]
Output: true
Explanation: Jump 1 step from index 0 to 1, then 3 steps to the last index.
Input: nums = [3,2,1,0,4]
Output: false
Explanation: You will always arrive at index 3 no matter what. Its maximum jump length is 0, which makes it impossible to reach the last index.


 bool canJump(vector<int>& nums) {
      int n = nums.size(), farest = 0;
      for(int i = 0;i < n; i++)
      {
        if(farest < i) return false;
        farest = max(i + nums[i], farest);
      }
      
      return true;
    }




 bool canJump(vector<int>& nums) {
        int n = nums.size();
        vector<bool> jump(n,false);
        jump[n-1]=true;
        
        for(int i=n-2;i>=0;i--)
        {
            for(int j=0;j<=nums[i] && i+j<n;j++)
            {
                if(jump[i+j]==true) 
                {
                    jump[i]=true; 
                    break;
                }
            }
        }
        
        return jump[0];
    }
