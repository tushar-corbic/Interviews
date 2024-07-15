package org.example;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

//
//https://leetcode.com/problems/maximum-profit-in-job-scheduling/description/
//        1235. Maximum Profit in Job Scheduling
//We have n jobs, where every job is scheduled to be done from startTime[i] to endTime[i], obtaining a profit of profit[i].
//
//You're given the startTime, endTime and profit arrays, return the maximum profit you can take such that there are no two jobs in the subset with overlapping time range.
//
//If you choose a job that ends at time X you will be able to start another job that starts at time X.
//
//
//Input: startTime = [1,2,3,3], endTime = [3,4,5,6], profit = [50,10,40,70]
//Output: 120
//Explanation: The subset chosen is the first and fourth job.
//Time range [1-3]+[3-6] , we get profit of 120 = 50 + 70.
//
//Input: startTime = [1,2,3,4,6], endTime = [3,5,10,6,9], profit = [20,20,100,70,60]
//Output: 150
//Explanation: The subset chosen is the first, fourth and fifth job.
//Profit obtained 150 = 20 + 70 + 60.
//
//Input: startTime = [1,1,1], endTime = [2,3,4], profit = [5,6,4]
//Output: 6
public class Question1 {
    public int jobScheduling(int[] startTime, int[] endTime, int[] profit) {
        int n = startTime.length;
        int [][] jobs = new int[n][3];
        for(int i =0; i<n; i++){
            jobs[i]= new int[]{startTime[i], endTime[i],profit[i]};
        }
        Arrays.sort(jobs, (a,b)->{
            return a[1]-b[1];
        });
        TreeMap<Integer,Integer> dp = new TreeMap<>();
        dp.put(0,0);
        for(int[] job: jobs){
            int cur = dp.floorEntry(job[0]).getValue() + job[2];
            if(cur>dp.lastEntry().getValue()){
                dp.put(job[1],cur);
            }
        }
        return dp.lastEntry().getValue();
    }
}


class Solution {
    private Map<Integer, Integer> dp;

    public int jobScheduling(int[] startTime, int[] endTime, int[] profit) {
        int[][] jobs = new int[startTime.length][3];
        for (int i = 0; i < startTime.length; i++) {
            jobs[i] = new int[]{startTime[i], endTime[i], profit[i]};
        }
        Arrays.sort(jobs, (a, b) -> a[0] - b[0]);

        dp = new HashMap<>();
        return dfs(0, jobs);
    }
    private int findNext(int[][] jobs, int idx) {
        int lo = idx + 1;
        int hi = jobs.length -1;
        while(lo <= hi) {
            int mid = lo + (hi - lo)/2;
            if(jobs[mid][0] >= jobs[idx][1]) {
                if(jobs[mid-1][0] >= jobs[idx][1])
                    hi = mid - 1;
                else
                    return mid;
            } else {
                lo = mid + 1;
            }
        }

        return -1;
    }

    private int dfs(int cur, int[][] jobs) {
        if (cur == jobs.length) {
            return 0;
        }

        if (dp.containsKey(cur)) {
            dp.get(cur);
        }

        int next = findNext(cur, jobs);
        int inclProf = jobs[cur][2] + (next == -1 ? 0 : dfs(next, jobs));
        int exclProf = dfs(cur + 1, jobs);

        dp.put(cur, Math.max(inclProf, exclProf));
        return Math.max(inclProf, exclProf);
    }

    int findNext(int cur, int[][] jobs) {
        for (int next = cur + 1; next < jobs.length; next++) {
            if (jobs[next][0] >= jobs[cur][1]) {
                return next;
            }
        }
        return -1;
    }
}
