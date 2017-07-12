# Binary Search

### Important stuff
####  1) Class A Binary Search problems
These problems are the once where you are supposed to figure out which item to be searched for first and then look for it and return it.<br>
Examples of this kind of problems:
a. Find the minimum in a rotated sorted array

        
####  2) Class B Binary Search problems
In this kind of problems, you already know what you are to be looking for. <br>
Examples of this kind of problems:
a. Find a number in an array
b. Two sum in a sorted array.

####  3) Finding mid
Doing ```(lo + hi) >>> 1``` is same as doing ```(lo + hi) / 2```. However, the later can cause problems if the numbers at lo and hi are very big, so that their sum exceeds what the programming language allows for an integer.<br>
So, to avoid this, ```lo + (hi - lo) / 2``` should be used.

#### 4) lo, mid, and hi
Keep in mind that these are merely indices and not the values themselves.

---

### Find minimum in a rotated sorted array (Class A)
#### Time complexity: O(log n)

#### Use example: {7, 8, 9, 1, 2, 3} and {7, 8, 9, 1, 2, 3, 4, 5, 6}



```java
public int minRotatedSortedArray(int[] nums){
    if(nums.length == 0) throw new IllegalStateException("Invalid input");

    int lo = 0;
    int hi = nums.length - 1;

    while(lo < hi){
        int mid = (lo + hi) >>> 2;

        // Comparison on hi and mid.
        // Comparison on lo and mid fails in [2, 1];
        if(nums[hi] < nums[mid])
            lo = mid + 1;
        else
            hi = mid;
    }
    return nums[lo];    // Could be lo or hi.
}
```

---

### Search in rotated sorted array (Class B)
#### Time complexity: O(log n)
#### Use example: {7, 8, 9, 1, 2, 3} and {7, 8, 9, 1, 2, 3, 4}  

```java
public int searchRotatedSortedArray(int[] nums, int target){
    int lo = 0;
    int hi = nums.length - 1;

    while(lo <= hi){
        int mid = (lo + hi) >>> 1;

        if(nums[mid] == target) return mid;

        // Comparison on hi and mid.
        // Comparison on lo and mid fails in [2, 1];
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

```

---


### Implement pow(x, n) function
#### Time complexity: O(log n) ? Not sure.

```java
public int pow(int x, int n){
    if(x == 0) return 0;
    if(n == 1) return x;
    if(n == 0) return 1;

    if(n < 0){
        n = -n;
        x = 1 / x;
    }
    return x % 2 == 0 ? pow(x * x, n / 2) : pow(x * x, n / 2) * x;
}
```

---

### Search in 2D matrix (Class B)
1) Integers in each row are sorted from left to right.
2) The first integer of each row is greater than the last integer of the previous row.

#### Time complexity: O(log n)

```
[
    [1,   3,  5,  7],
    [10, 11, 16, 20],
    [23, 30, 34, 50]
]
```

```java
public boolean searchIn2DMatrix(int[][] matrix, int target){
    if(target < matrix[0][0] || matrix[matrix.length - 1][matrix[0].length - 1] < target)
        return false;
    
    int lo = 0;
    int hi = matrix.length * matrix[0].length - 1;

    while(lo <= hi){
        int mid = (lo + hi) >>> 1;
        int row_mid = mid / col;    // Remember these expressions
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
```

---

### Search a 2D matrix II (Class B)
1) Integers in each row are sorted in ascending from left to right.
2) Integers in each column are sorted in ascending from top to bottom.

#### Time complexity: O(n)


```
[
    [1,  4,  7, 11, 15],
    [2,  5,  8, 12, 19],
    [3,  6,  9, 16, 22],
    [10, 13, 14, 17, 24],
    [18, 21, 23, 26, 30]
]
```

```java
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

```


---

### Find peak element
#### Time complexity: O(n)

#### Use example: {2, 3, 5, 4, 1}. Should return 5.

```java
public int findPeakElement(int[] nums){
    if(nums.length == 0) throw new IllegalStateException("Invalid input");

    for(int i = 1; i < nums.length; i++){
        if(nums[i] < nums[i - 1])
            return nums[i - 1];
    }
    return Integer.MIN_VALUE;
}

```

---

### Guess the number game (Class B)
1) I pick a number from 1 to n. You have to guess which number I picked.
2) Every time you guess wrong, I'll tell you whether the number is higher or lower.
3) You call a pre-defined API guess(int num) which returns 3 possible results (-1, 1, or 0)

#### Time complexity: O(log n)

```java
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

```

---

### First bad version (Class A)
1) Suppose you have n versions [1, 2, ..., n] and you want to find out the first bad one, which causes all the following ones to be bad.
2) You are given an API bool isBadVersion(version) which will return whether version is bad. Implement a function to find the first bad version.

#### Time complexity: O(log n)

```java
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

```

---

### Two sum in a sorted array (Class B)
See Arrays

### Search insertion position (Class B)
1) Given a sorted array and a target value, return the index if the target is found. 
2) If not, return the index where it would be if it were inserted in order.

#### Time complexity: O(log n)

```java
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

```


<br><br>
# Repeated Questions

### Intersection of two arrays
See Arrays
