Arrays
---
####  1) Reverse sort an array
        Arrays.sort(a, Collections.reverseOrder());
        
####  2) Sort array using a comparator
        intervals.sort((a, b) -> Integer.compare(a.start, b.start));
        Arrays.sort(intervals, (a, b) -> Integer.compare(a.start, b.start));

####  2) How to sort a collection (ArrayList)
        Collections.sort(list);
        Collections.reverse(list);

####  3) Initialize an array
        int[] array = { 1, 2, 3, 4, 5 };
        int[] array = new int[] {1, 2, 3, 4, 5};
        int[] array = new int[];
        

---
### Two sum in an unsorted array

#### Find two numbers in an array which add up to 'sum'.
1) For every number we now 'search' for its complement in the array. 
2) We have used HashTable here because searching is an array is expensive. 
3) If the array is sorted, we use binary search in the array instead of using a HashTable.
    
```java
public int[] twoSum(int[] numbers, int sum) {
    if (numbers.length <= 1)
        throw new IllegalStateException("Insufficient array length");

    HashMap<Integer, Integer> map = new HashMap<>();

    for (int i = 0; i < numbers.length; i++)
        map.put(numbers[i], i);


    for (int i = 0; i < nums.length; i++) {
        int complement = sum - numbers[i];

        if (map.containsKey(complement) && map.get(complement) != i)
            return new int[] { numbers[i], complement };
    }

    throw new IllegalStateException("No such numbers found.");
}
```

---
### Two sum in a sorted array (Class B binary search problem)
1) Unlike in an unsorted array, we don't require a HashTable to expedite the searching of 'complement' in the array.
2) Instead, since, the array is sorted, we use Binary search.

```java
public int[] twoSumSortedArray(int[] numbers, int sum) {
    if (numbers.length <= 1)
        throw new IllegalStateException("Insufficient array length");

    for (int i = 0; i < numbers.length; i++) {
        int complement = sum - numbers[i];

        int lo = i + 1;
        int hi = numbers.length - 1;
        while (lo <= hi) {
            int mid = (lo + hi) >>> 1;

            if (numbers[mid] == complement)
                return new int[] { numbers[i], complement };

            else if (complement < numbers[mid])
                hi = mid - 1;

            else
                lo = mid + 1;
        }
    }
    throw new IllegalStateException("No such numbers found.");
}
```

---
### Three sum 
#### Time complexity: O(n^2)

```java
public List<List<Integer>> threeSum(int[] nums, int sum) {
    Lis<List<Integer>> list = new ArrayList<>();

    Arrays.sort(nums);                                              // O(n log n)

    for (int i = 0; i < nums.length - 2; i++) {                     //! Why are the last two numbers skipped?
        if (i == 0 || (i > 0 && nums[i] != nums[i - 1])) {
            int diff = 0 - nums[i];

            int lo = i + 1;
            int hi = nums.length - 1;

            while (lo < hi) {
                if (nums[lo] + nums[hi] == diff) {
                    list.add(Arrays.asList(nums[lo], nums[i], nums[hi]));

                    while (lo < hi && nums[lo] == nums[lo + 1])
                        lo++;
                    while (lo < hi && nums[hi] == nums[hi - 1])
                        hi--;

                    lo++;
                    hi--;
                } else {
                    if (nums[lo] + nums[hi] > diff)
                        hi--;
                    else
                        lo++;
                }
            }
        }
    }
    return list;
}

```


---
### Majority element (Moore-Boyer voting algorithm)
#### Time complexity: O(n)

```java
public int majorityElement(int[] nums) {
    int me_ix = 0;
    int count = 0;

    for (int i = 0; i < nums.length; i++) {
        if (count == 0)
            me_ix = i;
        else if (nums[me_ix] == nums[i])
            count++;
        else
            count--;
    }
    return me_ix;
}

```

---
### Move zeroes in an array
#### Time complexity: O(n)

```java
public void moveZeroes(int[] nums) {
    int ix = 0;

    for (int i = 0; i < nums.length; i++) {
        if (nums[i] != 0)
            nums[ix++] = nums[i];
    }

    for (int i = ix; i < nums.length; i++) {
        nums[i] = 0;
    }
}

```

---
### Remove duplicates in a sorted array
#### Time complexity: O(n)

#### Not similar to
1) Find all duplicates in an array
2) Find the duplicate in an array (See Two Pointers)


```java
public int removeDuplicates(int[] nums) {
    int ix = 0;

    for (int i = 0; i < nums.length - 1; i++) {
        if (nums[i] != nums[i + 1])
            nums[++ix] = nums[i + 1];
    }
    return ix + 1;
}

```

---
### Remove duplicates from sorted arrays with at most two duplicates allowed
#### Time complexity: O(n)

#### Use example {7, 7, 7, 8, 9}

```java
public int removeDuplicates(int[] nums){
    int ix = 0;

    for(int i = 0; i < nums.length; i++){
        if(ix < 2 || nums[i] != nums[ix - 2])
            nums[ix++] = nums[i];
    }
    return ix;
}

```

---
### Remove all occurrences of a particular value
#### Time complexity: O(n)

```java
public int removeElementsArray(int[] nums, int target){
    int ix = 0;

    for(int i = 0; i < nums.length; i++){
        if(nums[i] != target)
            nums[ix++] = nums[i];
    }
    return ix;
}

```

---
### Add 1 to an array (First index is MSB)
#### Time complexity: O(n)

```java
public int[] plusOne(int[] nums) {
    if (nums.length == 0)
        throw new IllegalStateException("Invalid input");

    int sum = 1;

    for (int i = nums.length - 1; i <= 0; i--) {
        sum += nums[i];
        nums[i] = sum % 10;
        sum /= 10;
    }

    if (sum == 1) {
        int[] result = new int[nums.length + 1];
        result[0] = 1;

        for (int i = 1; i < result.length; i++) {
            result[i] = nums[i - 1];
        }
        return result;
    }
    return nums;
}

```

---
### Product of array except self
#### Time complexity: O(n)

```java
public int[] productExceptSelf(int[] nums) {
    int[] prev = new int[nums.length];
    int[] post = new int[nums.length];

    int[] result = new int[nums.length];

    prev[0] = 1;
    post[nums.length - 1] = 1;

    for (int i = 1; i < nums.length - 1; i++) {
        prev[i] = nums[i - 1] * prev[i - 1];
    }

    for (int i = nums.length - 2; i >= 0; i--) {
        post[i] = nums[i + 1] + post[i + 1];
    }

    for (int i = 0; i < nums.length; i++) {
        result[i] = prev[i] * post[i];
    }
    return result;
}
```

---
### Find if an array contains at least one duplicate
#### Time complexity: O(n)
#### Space complexity: O(n)

```java
public boolean containsDuplicate(int[] nums) {
    HashSet<Integer> set = new HashSet<>();

    for (int i = 0; i < nums.length; i++) {
        if (!set.add(nums[i]))
            return true;
    }
    return false;
}

```

---
### Find if an array contains duplicates with difference in their indices at max k
#### Time complexity: O(n)

```java
public boolean containsDuplicateII(int[] nums){
    HashMap<Integer, Integer> map = new HashMap<>();

    for(int i = 0; i < nums.length - 1; i++){
        map.put(nums[i], i);
    }

    for(int i = 0; i < nums.length - 1; i++){
        if(map.containsKey(nums[i]) && Math.abs(map.get(nums[i]) - i <= k)
            return true;

        //! Update position
        map.put(nums[i], i);
    }
    return false;
}

```

---
### Pascal's triangle
#### Time complexity: O(n^2)

```java
public List<List<Integers>> pascalsTriangle(int n){
    List<List<Integer>> result = new ArrayList<>();

    for(int i = 1; i <= n; i++){
        List<Integer> row = new ArrayList<>();

        for(int j = 1; j <= i; j++){
            if(j == 1 || j == i)
                row.add(1);
            else
                row.add(result.get(i - 1).get(j) + result.get(i - 1).get(j - 1));
        }
        result.add(row);
    }
    return result;
}

```

---
### Merge two sorted arrays into the first array
#### Time complexity: O(n)

```java
public mergeSortedArrays(int[] nums1, int m, int[] nums2, int n){
    // m is the number of elements in nums1, whereas k is the length of nums1
    int i = m - 1;
    int j = n - 1;
    int k = i + j - 1;

    while(i >= 0 && j >= 0){
        nums[k--] = nums[i] < nums[j] ? nums[j--] : nums[i--];
    }

    // The following loop handles the case if all the elements in nums1 are larger than those in nums2
    // Example: nums1 = [7, 8, 9], and nums2 = [1, 2, 3] 
    while(j >= 0){
        nums[k--] = nums[j--];
    }

    return nums1;
}

```

---
### Insert, delete, get random
#### Time complexity: All actions are O(1)

```java
//* Uses a HashMap and an ArrayList
public class InsertDeleteGetRandom{
    List<Integer> list;
    HashMap<Integer, Integer> map;
    int size;
    java.util.Random random;

    InsertDeleteGetRandom(){
        this.list = new ArrayList<>();
        this.map = new HashMap<>();
        this.size = 0;
        this.random = new java.util.Random();
    }

    public boolean insert(int data){
        if(!map.containsKey(data)){
            map.put(data, list.size());
            list.add(data);
            return true;
        }
        else
            return false;
    }

    public boolean delete(int data){
        if(map.containsKey(data)){
            list.remove(data);
            map.remove(data);
            return true;
        }
        else
            return false;
    }

    public int getRandom(){
        return list.get(random.nextInt(list.size()));   // nextInt(n + 1) gives a random number between 0 and n
    }
}
```

---
### Shortest word distance
1) Find shortest distance between two elements in an array.
2) Given a list of words and two words word1 and word2, return the shortest distance between these two words in the list.

#### Time complexity: O(n)

```java
public int shortestDistance(int[] words, String word1, String word2){
    int x1 = -1;
    int x2 = -1;
    int min = Integer.MAX_VALUE;

    for(int i = 0; i < words.length; i++){
        if(words[i].equals(word1))
            x1 = i;
        else if(words[i].equals(word2))
            x2 = i;
        
        if(x1 != -1 && x2 != -1)
            min = Math.min(min, Math.abs(x1 - x2));
    }
    return min;
}

```

---
### Third largest number in an array. 
1) If third largest doesn't exist, return the largest number.

#### Time complexity: O(n)

#### Not same as, but similar to
1) Top k frequent elements in an array

```java
public int thirdLargest(int[] nums){
    PriorityQueue<Integer> pq = new PriorityQueue<>();
    HashSet<Integer> set = new HashSet<>();

    for(int number : nums){
        if(set.add(number)){
            pq.offer(number);

            if(pq.size() > 3){
                pq.poll();
            }
        }
    }

    if(pq.size() < 3){
        while(pq.size() > 1){
            pq.poll();
        }
    }
    return pq.peek();
} 

```

---
### Rotate array, similar to rotate linked list.
1) Rotate Linked list can also be solved using queen and pawn.

#### Time complexity: O(n) because of reverseArray() function

```java
public int[] rotateArray(int[] nums, int k){
    if(nums.length <= 1) return nums;

    k = k % nums.length;                        // Required when k is greater or equal to nums.length

    reverseArray(nums, 0, nums.length - 1);
    reverseArray(nums, 0, k - 1);
    reverseArray(nums, k, nums.length - 1);

    return nums;
}
```

---
### Reverse an array
#### Time complexity: O(n)

```java
public int[] reverseArray(int[] nums, int begin, int end){
    while(begin < end){
        int temp = nums[begin];
        nums[begin] = nums[end];
        nums[end] = temp;
        begin++;
        end--;    
    }
    return nums;
}

```

---
### Spiral matrix
#### Time complexity: O(n^2)

```java
public List<Integer> spiralMatrix(int[][] matrix){
    int rowBegin = 0;
    int rowEnd = matrix.length - 1;
    int colBegin = 0;
    int colEnd = matrix[0].length - 1;

    List<Integer> result = new ArrayList<>();

    while(rowBegin <= rowEnd && colBegin <= colEnd){
        for(int i = colBegin; i <= colEnd; i++){                // '<=' because colEnd = matrix.length - 1;
            result.add(matrix[rowBegin][i]);
        }
        rowBegin++;

        for(int i = rowBegin; i <= rowEnd; i++){
            result.add(matrix[i][colEnd]);
        }
        colEnd--;

        if(rowEnd < rowBegin || colEnd < colBegin) 
            break;

        for(int i = colEnd; i >= 0; i--){
            result.add(matrix[rowEnd][i]);
        }
        rowEnd--;

        for(int i = rowBegin; i <= rowEnd; i++){
            result.add(matrix[i][colBegin]);
        }
        colBegin++;
    }
    return result;
}
```

---
### Merge intervals
#### Time complexity: O(n)

```java
public List<Interval> mergeInterval(List<Interval> intervals){
    List<Interval> result = new ArrayList<>();
    Interval prev = null;

    // Sort based on the start times
    intervals.sort((i1, i2) -> Integer.compare(i1.start, i2.start));    //! Sort array using a comparator

    for(int i = 0; i < intervals.length; i++){
        if(prev == null || (prev.end < intervals[i].start)){
            result.add(interval);
        }
        
        prev.end = Math.max(prev.end, interval.end);
    }
    return result;
}

```

---
### Find all duplicates in an array. 
#### This technique is applicable only when following condition holds.
1) Given an array of integers, 1 ≤ a[i] ≤ n (n = size of array), some elements appear twice and others appear once.

#### Time complexity: O(n)

```java
public List<Integer> findAllDuplicates(int[] nums){
    List<Integer> result = new ArrayList<>();

    for(int i = 0; i < nums.length; i++){
        int x = Math.abs(nums[i]) - 1;              // -1 because the numbers in the array are from range 1 to n.
            
        if(nums[x] < 0){
            result.add(Math.abs(x + 1));            // From above, we get x + 1 = the number nums[i]
        }

        nums[x] = -nums[x];
    }
    return result;
}

```

---
### Find the numbers which are absent in the array
1) Given an array of integers where 1 ≤ a[i] ≤ n (n = size of array), some elements appear twice and others appear once.

#### Time complexity: O(n)

```java
public List<Integer> findDisappearedNumbers(int[] nums){
    List<Integer> result = new ArrayList<>();
    int n = nums.length;

    for(int i = 0; i < nums.length; i++)
        nums[(nums[i] - 1) % n] += n;

    for(int i = 0; i < nums.length; i++)
        if(nums[i] <= n)
            res.add(i + 1);

    return result;
}

```

---
### Sort colors
See Two Pointers

