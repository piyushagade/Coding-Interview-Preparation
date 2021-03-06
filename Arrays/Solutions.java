/*
//!  1) Reverse sort an array
        Arrays.sort(a, Collections.reverseOrder());
        
//!  2) Sort array using a comparator
        intervals.sort((a, b) -> Integer.compare(a.start, b.start));
        Arrays.sort(intervals, (a, b) -> Integer.compare(a.start, b.start));

//!  2) How to sort a collection (ArrayList)
        Collections.sort(list);
        Collections.reverse(list);

//!  3) Initialize an array
        int[] array = { 1, 2, 3, 4, 5 };
//*     int[] array = new int[] {1, 2, 3, 4, 5};
        int[] array = new int[];
        
*/

public class Solution {
    // MARK: Two sum in unsorted array
    // Find two numbers in an array which add up to 'sum'.
    public int[] twoSum(int[] numbers, int sum) {
        if (numbers.length <= 1)
            throw new IllegalStateException("Insufficient array length");

        HashMap<Integer, Integer> map = new HashMap<>();

        for (int i = 0; i < numbers.length; i++) {
            map.put(numbers[i], i);
        }

        // For every number we now 'search' for its complement in the array. We have used HashTable here
        // because searching is an array is expensive. If the array is sorted, we use binary search in the
        // array instead of using a HashTable.
        for (int i = 0; i < nums.length; i++) {
            int complement = sum - numbers[i];

            if (map.containsKey(complement) && map.get(complement) != i)
                return new int[] { numbers[i], complement };
        }

        throw new IllegalStateException("No such numbers found.");
    }

    // MARK: Two sum in a sorted array (Class B binary search problem)
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

    // MARK: Three sum -> O(n^2)
    public List<List<Integer>> threeSum(int[] nums, int sum) {
        Arrays.sort(nums);
        Lis<List<Integer>> list = new ArrayList<>();

        for (int i = 0; i < nums.length - 2; i++) {         //! Why are the last two numbers skipped?
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

    // MARK: Majority element (Moore-Boyer voting algorithm)
    public int majorityElemeent(int[] nums) {
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

    // MARK: Move zeroes in an array
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

    // MARK: Remove duplicates in a sorted array

    //! Not similar to-
    //*   - Find all duplicates in an array
    //*   - Find the duplicate in an array (See Two Pointers)
    
    public int removeDuplicates(int[] nums) {
        int ix = 0;

        for (int i = 0; i < nums.length - 1; i++) {
            if (nums[i] != nums[i + 1])
                nums[++ix] = nums[i + 1];
        }
        return ix + 1;
    }

    // MARK: Remove duplicates from sorted arrays with at most two duplicates allowed
    //* Use example {7, 7, 7, 8, 9}
    public int removeDuplicates(int[] nums){
        int ix = 0;

        for(int i = 0; i < nums.length; i++){
            if(ix < 2 || nums[i] != nums[ix - 2])
                nums[ix++] = nums[i];
        }
        return ix;
    }

    // MARK: Find the duplicate in an array
    // See Two Pointers

    // MARK: Remove all occurrences of a particular value
    public int removeElementsArray(int[] nums, int target){
        int ix = 0;

        for(int i = 0; i < nums.length; i++){
            if(nums[i] != target)
                nums[ix++] = nums[i];
        }
        return ix;
    }

    // MARK: Add 1 to an array (First index is MSB)
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

    // MARK: Product of array except self
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

    // Mark: Find if an array contains at least one duplicate
    public boolean containsDuplicate(int[] nums) {
        HashSet<Integer> set = new HashSet<>();

        for (int i = 0; i < nums.length; i++) {
            if (!set.add(nums[i]))
                return true;
        }
        return false;
    }

    // MARK: Find if an array contains duplicates with difference in their indices at max k
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

    // MARK: Pascal's triangle
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

    // MARK: Merge two sorted arrays into the first array
    public mergeSortedArrays(int[] nums1, int m, int[] nums2, int n){
        // m is the number of elements in nums1, whereas k is the length of nums1
        int i = m - 1;
        int j = n - 1;
        int k = i + j - 1;

        while(i >= 0 && j >= 0){
            nums[k--] = nums[i] < nums[j] ? nums[j--] : nums[i--];
        }

        //* The following loop handles the case if all the elements in nums1 are larger than those in nums2
        //* Example: nums1 = [7, 8, 9], and nums2 = [1, 2, 3] 
        while(j >= 0){
            nums[k--] = nums[j--];
        }

        return nums1;
    }

    // MARK: Sort colors
    // See Two Pointers

    public int[] swap(int i, int j, int[] nums){
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;

        return nums;
    }

    // MARK: Insert, delete, get random
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

    // MARK: Shortest word distance
    // Shortest distance between two elements in an array.s
    // Given a list of words and two words word1 and word2, return the shortest distance between these two words in the list.
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

    // MARK: Third largest number in an array. 
    // If third largest doesn't exist, return the largest number.
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

    // MARK: Rotate array, similar to rotate linked list.
    // Rotate Linked list can also be solved using queen and pawn.
    public int[] rotateArray(int[] nums, int k){
        if(nums.length <= 1) return nums;

        k = k % nums.length;    //! Don't forget to do this

        reverseArray(nums, 0, nums.length - 1);
        reverseArray(nums, 0, k - 1);
        reverseArray(nums, k, nums.length - 1);

        return nums;
    }

    // MARK: Reverse an array
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

    // MARK: Spiral matrix
    public List<Integer> spiralMatrix(int[][] matrix){
        int rowBegin = 0;
        int rowEnd = matrix.length - 1;
        int colBegin = 0;
        int colEnd = matrix[0].length - 1;

        List<Integer> result = new ArrayList<>();

        while(rowBegin <= rowEnd && colBegin <= colEnd){
            for(int i = colBegin; i <= colEnd; i++){     // '<=' because colEnd = matrix.length - 1;
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

    // MARK: Merge intervals
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

    // MARK: Find all duplicates in an array. This technique is applicable only when following condition holds.
    // Given an array of integers, 1 ≤ a[i] ≤ n (n = size of array), some elements appear twice and others appear once.

    public List<Integer> findAllDuplicates(int[] nums){
        List<Integer> result = new ArrayList<>();

        for(int i = 0; i < nums.length; i++){
            int x = Math.abs(nums[i]) - 1;  // -1 because the numbers in the array are from range 1 to n.
                
            if(nums[x] < 0){
                result.add(Math.abs(x + 1));    // From above, we get x + 1 = the number nums[i]
            }

            nums[x] = -nums[x];
        }
        return result;
    }

    // MARK: Find the numbers which are absent in the array
    // Given an array of integers where 1 ≤ a[i] ≤ n (n = size of array), some elements appear twice and others appear once.
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

    // MARK: Rotate Image
    public void rotateImage(int[][] matrix){
        int rows = matrix.length;

        for(int i = 0; i < rows / 2; i++){
            for(int j = 0; j < (rows + 1) / 2; j++){
                int temp = matrix[i][j];
                matrix[i][j] = matrix[rows - 1 - j][i];
                matrix[rows - 1 - j][i] = matrix[rows - 1 - i][rows - 1 - j];
                matrix[rows - 1 - i][rows - 1 - j] = matrix[j][rows - 1 - i];
                matrix[j][rows - 1 - i] = temp;
            }
        }
    }

    // MARK: Number of islands
    public int numberIslands(int[][] matrix){
        int count = 0;
        for(int i = 0; i < matrix.length; i++){
            for(int j = 0; j < matrix[0].length; j++){
                count++;
                clear(matrix, i, j);
            }
        }
    }

    public void clear(int[][] matrix, int i, int j){
        if(i < 0 || i > matrix.length - 1 || j < 0 || j > matrix[0].length - 1 || matrix[i][j] == 0) return;

        matrix[i][j] = 0;
        clear(matrix, i - 1, j);
        clear(matrix, i + 1, j);
        clear(matrix, i, j - 1);
        clear(matrix, i, j + 1);
    }

    // MARK: Merge sort an array
    public void mergeSort(int[] nums, int lo, int hi){
        if(lo < hi){
            int mid = lo + (hi - lo) / 2;

            mergeSort(nums, lo, mid);
            mergeSort(nums, mid + 1, hi);
            nums = merge(nums, lo, mid, hi);
        }
        return nums;
    }

    public void merge(int[] nums, int lo, int mid, int hi){
        int[] result = new int[hi - lo + 1];
        int i = 0;
        int j = 0;
        int k = 0;

        while(i <= mid && mid + j > nums.length){
            if(i == 0){
                result[k++] = nums[mid + 1 + j++];
                continue;
            }
            else if(j == 0){
                result[k++] = nums[i++];
                continue;
            }
            result[k++] = nums[i] < nums[mid + 1 + j] ? nums[i++] : nums[mid + 1 + j++];
        }
        return result;
    }
}