# Dynamic Programming

### Longest substring without repeating a character
The task is to find the longest substring in a string without repeating characters. Clearly, to keep track of characters, we will need a HashMap or a HashSet.

```java
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
```

---

### Minimum edit distance
Given two words, find the minimum changes (insert, substitute or delete) that could be made to word1 to convert it to word2.
  
```java
public int minimumEditDistance(String word1, String word2){
    int n = word1.length();
    int m = word2.length();

    int[][] dp = new int[n + 1][m + 1];

    // initialize dp
    for(int i = 0; i < m + 1; i++) 
        dp[0][i] = i;
    for(int i = 0; i < n + 1; i++) 
        dp[i][0] = i;

    for(int i = 1; i < n + 1; i++){                         // mind the limits
        for(int k = 1; k < m + 1; k++){
            if(word1.charAt(i - 1) == word2.charAt(k - 1))  // mind the  -1
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
```

---

### Best time to buy and sell stocks; Only one transaction allowed (Class A)

```java
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
```

---

### Best time to buy and sell stocks; Multiple transactions allowed (Not a DP problem)

```java
public int maxProfitMultipleTransactions(int[] nums) {
    int max = 0;

    for (int i = 1; i < nums.length; i++) {
        if (nums[i] > nums[i - 1]) {
            max += nums[i] - nums[i - 1];
        }
    }
    return max;
}
```

---

### Subarray with maximum sum (Class B)

```java
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
```

---

### Maximum product subarray

```java
public int maxProductSubarray(int[] nums){
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
```

---

### Climbing stairs
Given n number of stairs, if you can take 1 or 2 steps at a time, in how many ways can you reach the top of the staircase.

```java
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
```

---

### House robber
In a neighborhood represented by nums[], what is the maximum loot a robber can manage if he cannot rob consecutive houses.

```java
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
```

---

### Count bits
Given a non negative integer number n. For every numbers i in the range 0 ≤ i ≤ n calculate the number of 1's in their binary representation and return them as an array.
Example: For n = 5 you should return [0,1,1,2,1,2].

```java
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
```

---

### Word break
Given a dictionary of words, determine if the given string s can be constructed using words present in the dictionary.

```java
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
```