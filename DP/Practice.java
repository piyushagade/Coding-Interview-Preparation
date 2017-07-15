public class Practice{

    // MARK: Longest substring with repeating a character (Not a DP problem)
    public int longestSubstringWithoutRepeatingCharacters(String s){
        if(s.length() <= 1) return s.length();

        int left = 0;
        int right = 0;
        int longest = 0;

        HashMap<Character, Integer> map = new HashMap<>();

        for(int right = 0; right < s.length(); right++){
            if(map.containsKey(s.charAt(right)))
                left = Math.max(left, map.get(s.charAt(right)) + 1);
            map.put(s.charAt(right), right);
            longest = Math.max(longest, right - left + 1);
        }
        return longest;
    }

    // MARK: Minimum edit distance
    public int minimumEditDistance(String word1, String word2){
        int[][] edits = new int[words1.length() + 1][words2.length() + 1];

        // Initialize table
        for(int i = 0; i < edits.length; i++)
            edits[i][0] = i;
        for(int i = 1; i < edits[0].length; i++)
            edits[0][i] = i;

        for(int i = 1; i < edits.length; i++)
            for(int j = 1; j < edits[0].length; j++){
                if(word1.charAt(i - 1) == word2.chaAt(j - 1))
                    edits[i][j] = edits[i - 1][j - 1];
                else{
                    int a = edits[i - 1][j];
                    int b = edits[i][j - 1];
                    int c = edits[i - 1][j - 1];
                    edits[i][j] = getMin(a, b, c) + 1;
                }
            }
        
        return edits[edits.length - 1][edits[0].length - 1];
    }

    // MARK: SubArray with maximum sum
    public int maxSumSubarray(int[] nums){
        int[] sums = new int[nums.length];
        int max = nums[0];
        int sums[0] = nums[0];

        for(int i = 1; i < nums.length; i++){
            sums[i] = nums[i] + sums[i - 1] < 0 ? 0 : sums[i - 1];
            max = Math.max(max, sums[i]);
        }
        return max;
    }

    // MARK: SubArray with maximum product
    public int maxProductSubarray(int[] nums){
        int[] pos = new int[nums.length];
        int[] neg = new int[nums.length];
        
        int max = nums[0];
        pos[0] = nums[0];
        neg[0] = nums[0];

        for(int i = 1; i < nums.length; i++){
            pos[i] = getMax(nums[i], nums[i] * pos[i - 1], nums[i] * neg[i - 1]);
            neg[i] = getMin(nums[i], nums[i] * pos[i - 1], nums[i] * neg[i - 1]);

            max = Math.max(max, pos[i]);
        }
        return max;
    }

    // MARK: Climbing stairs
    public int climbStairs(int n){
        int[] ways = new int[n + 1];

        if(n <= 1)
            return n;

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
        if(nums.length == 0)
            return 0;
        if(nums.length == 1)
            return nums[0];

        int[] inclusive = new int[nums.length];
        int[] exclusive = new int[nums.length];

        inclusive[0] = nums[0];
        exclusive[0] = 0;

        for(int i = 1; i < nums.length; i++){
            inclusive[i] = nums[i] + exclude[i - 1];
            exclusive[i] = Math.max(inclusive[i - 1], exclusive[i - 1]);
        }
        return Math.max(inclusive[nums.length - 1], exclusive[nums.length - 1]);
    }

    // MARK: Count bits
    public in[] countBits(int n){
        int[] ones = new int[n + 1];

        ones[0] = 0;

        for(int i = 1; i <= n; i++)
            ones[i] = ones[i / 2] + i % 2;

        return ones;
    }

    // MARK: Word Break
    public boolean wordBreak(String s, List<String> dict){
        boolean[] breakable = new boolean[s.length() + 1];
        breakable[0] = true;

        for(int right = 1; right <= s.length(); right++)
            for(int left = 0; left < right; left++){
                if(breakable[left] && dict.contains(s.substring(left, right + 1))){
                    breakable[right] = true;
                }
            }
        return breakable[breakable.length - 1];
    }
}