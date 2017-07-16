public class Practice{

    // MARK: 3 Sum
    public List<List<Integer>> threeSum(int[] nums, int sum) {
        List<List<Integer>> result = new ArrayList<>();

        Arrays.sort(nums);

        for(int i = 0; i < nums.length - 2; i++){                           //! Remember the limits
            int diff = 0 - nums[i];

            int begin = 0;
            int end = nums.length - 1;

            while(begin < end){
                if(i == 0 || (i > 0 && nums[i] != nums[i - 1]))
                    if(nums[begin] + nums[end] == diff){
                        result.add(new ArrayList<>(Arrays.asList(nums[i], nums[begin], nums[end])));

                        while(begin < end && nums[begin] == nums[begin + 1])    //! begin + 1 here
                            begin++;
                        while(begin < end && nums[end] == nums[end - 1])        //! end - 1 here
                            end--;

                        begin++;
                        end--;
                    }
                    else if((nums[begin] + nums[end]) < diff)
                        begin++;
                    else
                        end--;
            }
            return result;
        }
    }

    // MARK: Majority element
    public int majorityElement(int[] nums){
        int count = 0;
        int me_ix = 0;

        for(int i = 0; i < nums.length; i++){
            if(count == 0)
                me_ix = i;
            else if(nums[me_ix] == nums[i])
                count++;
            else
                count--;
        }
        return nums[me_ix];
    }

    // MARK:  Move zeroes in an array
    public int[] moveZeroes(int[] nums){
        int ix = 0;

        for(int i = 0; i < nums.length; i++){
            if(nums[i] != 0)
                nums[ix++] = nums[i];
        }

        for(int i = ix; i < nums.length; i++){
            nums[i] = 0;
        }
        return nums;
    }

    // MARK: Remove duplicates in a sorted array
    public void removeDuplicates(int[] nums){
        int ix = 0;

        for(int i = 0; i < nums.length - 1; i++){
            if(nums[i] != nums[i + 1])
                nums[ix++] = nums[i++];
        }
    }

    // MARK: Remove all occurences of a particular value
    public void removeElements(int[] nums, int target){
        int ix = 0;

        for(int i = 0; i < num.length; i++){
            if(nums[i] != target)
                nums[ix++] = nums[i];
        }
    }

    // MARK: Add 1 to a number represented by an array (First index is MSB)
    public void addOneToAnArray(int[] nums){
        int sum = 1;

        for(int i = nums.length - 1; i >= 0; i--){
            sum += nums[i];

            nums[i] = sum % 10;
            sum /= 10;
        }

        if(sum == 1){
            // Create an new array of length nums.length + 1 and return that.
        }

        return nums;
    }
    
    // MARK: Product of array except self
    public int[] productExceptSelf(int[] nums){
        int[] pre = new int[nums.length];
        int[] post = new int[nums.length];
        int productExceptSelf = new int[nums.legnth];

        pre[0] = 1;
        post[nums.length - 1] = 1;

        for(int i = 1; i < pre.length; i++)
            pre[i] = pre[i - 1] * nums[i - 1];

        for(int i = nums.length - 2; i >= 0; i--)
            post[i] = post[i + 1] * nums[i + 1];

        for(int i = 0; i < nums.length; i++)
            productExceptSelf[i] = pre[i] * post[i];

        return productExceptSelf;
    }

    // MARK: Find if an array contains a duplicate
    public boolean findDuplicateInAnArray(int[] nums){
        HashSet<Integer> set = new HashSet<>();

        for(int i = 0; i < nums.length; i++){
            if(!set.add(nums[i]))
                return true;
        }
        return false;
    }

    // MARK: Shortest distance between two elements in an array (Shortest word distance)
    public int shortestDistance(int[] nums, int a, int b){
        int x1 = -1;
        int x2 = -1;
        int min = Integer.MAX_VALUE;

        for(int i = 0; i < nums.length; i++){
            if(nums[i] == a)
                x1 = i;

            else if(nums[i] == b)
                x2 = i;

            if(x1 != -1 && x2 != -1)
                min = Math.min(min, Math.abs(x1 - x2));
        }
        return min;
    }

    // MARK: Merge intervals
    public List<Interval> mergeIntervals(Interval[] intervals){
        List<interval> result = new ArrayList<>();
        Interval prev = null;

        intervals.sort((a, b) -> Integer.compare(a.start - b.start));                   //! Remember the comparator

        for(Interval interval : intervals){
            if(prev == null || (prev.end < interval.begin))
                result.add(interval);
            
            prev.end = Math.max(prev.end, interval.end);
        }
    }

    // MARK: Find all duplicates in an array. This technique is applicable only when following condition holds.
    // Given an array of integers, 1 ≤ a[i] ≤ n (n = size of array), some elements appear twice and others appear once.

    public List<Integer> findAllDuplicates(int[] nums){
        List<Integer> result = new ArrayList<>();

        for(int i = 0; i < nums.length; i++){
            int x = Math.abs(nums[i]) - 1;
            if(nums[x] < 0)
                result.add(x + 1);

            nums[x] = -nums[x];
        }
        return result;
    }

    // MARK: Rotate Image
    public void rotateImage(int[][] matrix){
        int rows = matrix.length;

        for(int i = 0; i < rows / 2; i++)
            for(int j = 0; j < (rows + 1) / 2; j++){
                int temp = matrix[i][j];
                matrix[i][j] = matrix[rows - 1 - j][i];
                matrix[rows - 1- j][i] = matrix[rows - 1 - i][rows - 1 - j];
                matrix[rows - 1 - i][rows - 1 - j] = matrix[j][rows - 1 - i];
                matrix[j][rows - 1 - i] = temp;
            }
    }
    
}