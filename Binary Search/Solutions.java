public class Solution{

    // MARK: Find minimum in a rotated sorted array 
    // (Class A binary search solution)
    //* Use example: (7 8 9 1 2 3) and (7 8 9 1 2 3 4 5 6)
    public int minRotatedSortedArray(int[] nums){
        if(nums.length == 0) throw new IllegalStateException("Invalid input");

        int lo = 0;
        int hi = nums.length - 1;

        while(lo < hi){
            int mid = (lo + hi) >>> 2;

            //! Comparison on hi and mid.
            //! Comparison on lo and mid fails in [2, 1];
            if(nums[hi] < nums[mid])
                lo = mid + 1;
            else
                hi = mid;
        }
        return nums[lo];    //* Could be lo, hi or mid, but mid is not available outside the scope of the while loop.
    }

    // MARK: Search in rotated sorted array 
    // (Class B binary search solution)
    //* Use example: (7 8 9 1 2 3) and (7 8 9 1 2 3 4)  
    public int searchRotatedSortedArray(int[] nums, int target){
        int lo = 0;
        int hi = nums.length - 1;

        while(lo <= hi){
            int mid = (lo + hi) >>> 1;

            if(nums[mid] == target) return mid;

            //! Comparison on hi and mid.
            //! Comparison on lo and mid fails in [2, 1];
            if(nums[hi] < nums[mid]){
                if(nums[lo] <= target && target < nums[hi])
                    hi = mid - 1;
                else
                    lo = mid + 1;
            }
            else{
                if(nums[mid] < target && target <= nums[hi])
                    lo = mid + 1;
                else
                    hi = mid - 1;
            }
        }
        return -1;
    }

    // MARK: Implement pow(x, n) function
    // See Math
    // Time complexity: O(n)
    // MARK: Search in 2D matrix
    // Integers in each row are sorted from left to right.
    // The first integer of each row is greater than the last integer of the previous row.
    /*
    [
        [1,   3,  5,  7],
        [10, 11, 16, 20],
        [23, 30, 34, 50]
    ]
    */
    public boolean searchIn2DMatrix(int[][] matrix, int target){
        if(target < matrix[0][0] || matrix[matrix.length - 1][matrix[0].length - 1] < target)
            return false;
        
        int lo = 0;
        int hi = matrix.length * matrix[0].length - 1;

        while(lo <= hi){
            int mid = (lo + hi) >>> 1;
            int row_mid = mid / col;    //! Remember these expressions
            int col_mid = mid % col;

            if(matrix[row_mid][col_mid] == target)
                return true;
            else if (target > matrix[row_mid][col_mid])
                lo = mid + 1;
            else
                hi = mid - 1;
        }
        return false;
    }

    // MARK: Search a 2D matrix II
    // Integers in each row are sorted in ascending from left to right.
    // Integers in each column are sorted in ascending from top to bottom.
    /*
    [
        [1,  4,  7, 11, 15],
        [2,  5,  8, 12, 19],
        [3,  6,  9, 16, 22],
        [10, 13, 14, 17, 24],
        [18, 21, 23, 26, 30]
    ]
    */
    public boolean searchIn2DMatrixII(int[][] matrix, int target){
        int row = 0;
        int col = matrix[0].length();

        while(row <= matrix.length && col >= 0){
            if(target == matrix[row][col])
                return true;
            else if(target > matrix[row][col])
                row++;
            else if(target < matrix[row][col])
                col--;
        }
        return false;
    }

    // MARK: Intersection of two arrays
    // See Arrays

    // MARK: Find peak element
    // Example: {2, 3, 5, 4, 1} should return 5
    public int findPeakElement(int[] nums){
        if(nums.length == 0) throw new IllegalStateException("Invalid input");

        for(int i = 1; i < nums.length; i++){
            if(nums[i] < nums[i - 1])
                return nums[i - 1];
        }
        return Integer.MIN_VALUE;
    }

    // MARK: Guess the number game
    // I pick a number from 1 to n. You have to guess which number I picked.
    // Every time you guess wrong, I'll tell you whether the number is higher or lower.
    // You call a pre-defined API guess(int num) which returns 3 possible results (-1, 1, or 0)
    public int guessGame(int n){
        int lo = 1;
        int hi = n;

        while(lo <= hi){
            int mid = (! + n) >>> 2;

            if(guess(mid) == 0)
                return mid;
            else if(guess(mid) == 1){
                lo = mid + 1;
            }
            else{
                hi = mid - 1;
            }
            throw new IllegalStateException("Couldn't guess.");
        }
    }

    // MARK: First bad version (Class A)
    // Suppose you have n versions [1, 2, ..., n] and you want to find out the first bad one, which causes all the following ones to be bad.
    // You are given an API bool isBadVersion(version) which will return whether version is bad. Implement a function to find the first bad version.
    public int firstBadVersion(int n){
        int lo = 1;
        int hi = n; 

        while(lo < hi){
            int mid = (lo + hi) >>> 1;
            
            if(isBadVersion(mid))
                hi = mid;
            else
                lo = mid + 1;
        }
        return lo;
    }

    // MARK: Two sum in a sorted array
    // See Arrays

    // MARK: Search insertion position
    // Given a sorted array and a target value, return the index if the target is found. 
    // If not, return the index where it would be if it were inserted in order.
    public int searchPosition(int nums, int target){
        int lo = 0;
        int hi = nums.length - 1;

        while(lo <= hi){
            int mid = (lo + hi) >>> 1;

            if(nums[mid] ==  target)
                return mid;
            
            else if(target > nums[mid])
                lo = mid + 1;
            else
                hi = mid - 1;
        }
        return lo;
    }
    
}