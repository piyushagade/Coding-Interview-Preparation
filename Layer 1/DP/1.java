public class Solution{

    // MARK: Longest substring without repeating a character
    public int longestSubstring(String s){
        if(s.length() <= 1) return s.length();

        Map<Character, Integer> map = new HashMap<>();
        int longest = 0;
        
        int left = 0;
        int right = 0;

        while(right < s.length()){
            if(map.containsKey(s.charAt(right)))
                left = Math.max(left, map.get(s.charAt(right)) + 1);

            map.put(s.charAt(right), right);

            longest = Math.max(longest, right - left + 1);

            right++;
        }
    } 

    // MARK: Minimum edit distance
    public int minumumEditDistance(String word1, String word2){
        int n = word1.length();
        int m = word2.length();

        int[][] dp = new int[n + 1][m + 1];

        //initialize dp
        for(int i = 0; i < m + 1; i++) dp[0][i] = i;
        for(int i = 0; i < n + 1; i++) dp[i][0] = i;

        for(int i = 1; i < n + 1; i++){                         //mind the limits
            for(int k = 1; k < m + 1; k++){
                if(word1.charAt(i - 1) == word2.charAt(k - 1))  //mind the  -1
                    dp[i][k] = dp[i - 1][k - 1];
                else{
                    int a = dp[i - 1][k];
                    int b = dp[i - 1][k - 1];
                    int c = dp[i][k - 1];

                    dp[i][k] = Math.min(a, Math.min(b,c)) + 1;
                }
            }
        }
        return dp[n][m];
    }


    // MARK: Best time to buy and sell stocks; Only one transaction allowed (Class A DP Solution)
    public int maxProfit(int[] nums) {
        int[] profits = new int[nums.length];
        int min = nums[0];
        profits[0] = nums[0];

        for (int i = 1; i < nums.length; i++) {
            int min = Math.min(min, nums[i]);
            profits[i] = Math.max(profits[i - 1], nums[i] - min);
        }

        return profits[nums.length - 1];
    }
    
    // MARK: Best time to buy and sell stocks; Multiple transactions allowed (Not a DP problem)
    public int maxProfitMultipleTransactions(int[] nums) {
        int max = 0;

        for (int i = 1; i < nums.length; i++) {
            if (nums[i] > nums[i - 1]) {
                max += nums[i] - nums[i - 1];
            }
        }
        return max;
    }

    
    // MARK: Subarray with maximum sum (Class B DP Solution)
    public int maxSumSubarray(int[] nums){
        int[] sums[] = new int[nums.length];
        int max = nums[0];
        sums[0] = nums[0];

        for(int i = 1; i < nums.length; i++){
            sums[i] = nums[i] + sums[i - 1] < 0 ? 0 : sums[i - 1];
            max = Math.max(max, sums[i]);
        }
        return max;
    }

    // MARK: Maximum product subarray
    publis int maxProductSubarray(int[] nums){
        int[] pos = new int[nums.length];
        int[] neg = new int[nums.length];
        
        int max = nums[0];
        pos[0] = nums[0];
        neg[0] = nums[0];

        for(int i = 1; i < nums.length; i++){
            pos[i] = getMax(nums[i], pos[i - 1] * nums[i], neg[i - 1] * nums[i]);
            neg[i] = getMin(nums[i], pos[i - 1] * nums[i], neg[i - 1] * nums[i]);
            max = getMax(pos[i], max);
        }
        return max;
    }
}