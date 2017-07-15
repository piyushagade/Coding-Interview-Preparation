public class Solution{

    // MARK: Longest substring without repeating a character (Not a DP problem)
    // It is a 'two pointer' problem
    public int longestSubstring(String s){
        if(s.length() <= 1) return s.length();

        Map<Character, Integer> map = new HashMap<>();
        int longest = 0;
        
        int left = 0;
        int right = 0;

        for(right = 0; right < s.length(); right++){
            if(map.containsKey(s.charAt(right)))
                left = Math.max(left, map.get(s.charAt(right)) + 1);        //! Don't forget to add 1

            map.put(s.charAt(right), right);            //! Don't forget updating the map
            longest = Math.max(longest, right - left + 1);
        }
    } 

    // MARK: Minimum edit distance
    // Given two words, find the minimum changes (insert, substitute or delete) 
    // that could be made to word1 to convert it to word2.
    public int minimumEditDistance(String word1, String word2){
        int[][] edits = new int[words1.length() + 1][words2.length() + 1];

        // Initialize dp
        for(int i = 0; i < edits.length; i++)
            edits[i][0] = 0;
        for(int i = 0; i < edits[0].length; i++)
            edits[0][i] = 0;

        for(int i = 1; i < edits.length; i++){
            for(int j = 1; j < edits[0].length; j++){
                if(word1.charAt(i - 1) == word2.charAt(k - 1))      //! Don't forget the '- 1'
                    edits[i][j] = edits[i - 1][j - 1];
                else
                    int a = edits[i - 1][j];
                    int b = edits[i - 1][j - 1];
                    int c = edits[i][j - 1];

                    edits[i][j] = getMin(a, b, c) + 1;              //! Don't forget to add 1
            }
        }

        return edits[edits.length - 1][edits[0].length - 1];
    }




    // MARK: Best time to buy and sell stocks; Only one transaction allowed (Class A DP Solution)
    public int maxProfit(int[] nums) {
        int[] profits = new int[nums.length];
        int min = nums[0];
        profits[0] = nums[0];

        for(int i = 1; i < nums.length; i++){
            min = Math.min(min, nums[i]);
            profits[i] = Math.max(profits[i - 1], nums[i] - min);
        }
        return profits[profits.length - 1];
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

    
    // MARK: SubArray with maximum sum (Class B DP Solution)
    public int maxSumSubarray(int[] nums){
        int[] sums = new int[nums.length];
        int max = nums[0];
        sums[0] = nums[0];

        for(int i = 1; i < nums.length; i++){
            sums[i] = nums[i] + sums[i - 1] < ? 0 : sums[i - 1];
            max = Math.max(max, sums[i]);
        }
        return max;
    }

    // MARK: Subarray with maximum product
    public int maxProductSubarray(int[] nums){
        int[] pos = new int[nums.length];
        int[] neg = new int[nums.length];
        
        int max = nums[0];
        pos[0] = nums[0];
        neg[0] = nums[0];

        for(int i = 1; i < nums.length; i++){
            pos[i] = Math.max(nums[i], Math.max(nums[i] * pos[i - 1], nums[i] * neg[i - 1]));
            neg[i] = Math.min(nums[i], Math.min(nums[i] * pos[i - 1], nums[i] * neg[i - 1]));

            max = Math.max(max, pos[i]);
        }
        return max;
    }

    // MARK: Climbing stairs
    // Given n number of stairs, if you can take 1 or 2 steps at a time, in how many ways
    // can you reach the top of the staircase.
    public int climbStairs(int n){
        if(n <= 1)
            return n;
       
        // For a given n, there will be n + 1 stairs including 0th stair. So, we add 1 to
        // force ith index to represent ith stair. 
        int[] ways = new int[n + 1];

        // Initialize DP
        for(int i = 0; i <= 2; i++)
            ways[i] = i;

        for(int i = 3; i <= n; i++)
            ways[i] = ways[i - 1] + ways[i - 2];

        return ways[n];
    }

    // MARK: House robber
    // In a neighborhood represented by nums[], what is the maximum loot a robber can manage
    // if he cannot rob consecutive houses.
    public int houseRobber(int[] nums){
        if(nums.length == 0) return 0;

        int[] include = new int[nums.length];
        int[] exclude = new int[nums.length];

        include[0] = nums[0];
        exclude[0] = 0;

        for(int i = 1; i < nums.length; i++){
            include[i] = nums[i] + exclude[i - 1];
            exclude[i] = Math.max(include[i - 1], exclude[i - 1]);
        }
        return Math.max(include[nums.length - 1], exclude[nums.length - 1]);
    }

    // MARK: Count bits
    // Given a non negative integer number n. For every numbers i in the range 0 ≤ i ≤ n calculate 
    // the number of 1's in their binary representation and return them as an array.
    // Example: For n = 5 you should return [0,1,1,2,1,2].
    public int[] countBits(int n){
        // For a given n, there are n + 1 numbers including zero. So, to force ith index to
        // represent ith number, we add 1.
        int[] ones = new int[n + 1];

        ones[0] = 0;

        for(int i = 1; i <= n; i++){
            ones[i] = ones[i / 2] + i % 2;
        }
        return ones;
    }

    // MARK: Word break
    // Given a dictionary of words, determine if the given string s can be constructed using
    // words present in the dictionary
    public boolean wordBreak(String s, List<String> dict){
        boolean[] breakable = new boolean[s.length() + 1];
        breakable[0] = true;

        A: for(int right = 1; right < s.length() + 1; right++){
            B: for(int left = 0; left < right; left++){
                if(breakable[left] && dict.contains(s.substring(left, right + 1))){
                    breakable[right] = true;
                    break B;
                }
            }
        }
        return breakable[breakable.length - 1];
    }

}