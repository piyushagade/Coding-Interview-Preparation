public class Solution{
    
    // MARK: Reverse a string
    public String reverseString(String s){
        char[] chars = s.toCharArray();

        int begin = 0;
        int end = chats.length;

        while(begin < end){
            char temp = s.chars[begin];
            chars[begin] = chars[end];
            chars[end] = temp;

            begin++;
            end--;
        }

        return String.valueOf(chars);
    }

    // MARK: Longest substring without repeating characters
    //! This is not a Dynamic programming problem
    // Similar to Longest chain of consecutive even numbers.
    public int longestSubstring(String s){
        HashMap<Character> map = new HashMap<>();

        int left = 0;
        int right = 0;
        int longest = 0;

        for(right = 0; right < s.length(); right++){
            if(map.containsKey(s.charAt(right)))
                left = Math.max(left, map.get(right));
            
            map.put(s.charAt(right), right);                    //! Don't forget updating the map
            longest = Math.max(longest, right - left + 1);
        }
        return longest;
    }

    // MARK: Moves zeros in an array
    // See Arrays


    // MARK: Remove element from an array
    // See Arrays


    // MARK: Remove duplicates from sorted arrays
    // See Arrays


    // MARK: Remove duplicates from sorted arrays with at most two duplicates allowed
    // See Arrays


    // MARK: Container with most water
    public int mostWater(int[] heights){
        int begin = 0;
        int end = heights.length - 1;
        int max = 0;

        while(begin < end){
            max = Math.max(max, Math.min(heights[begin], heights[end]) * (end - begin));

            // the smaller one of first and last line can't support a higher water level and can thus be safely removed from further consideration.
            if(heights[begin] < heights[end]) 
                begin++;
            else 
                end--;
        }
        return max;
    }

    // MARK: Linked list cycle
    // See LinkedList


    // MARK: Linked list cycle an return node where cycle begins
    // See LinkedList


    // MARK: Merge two sorted arrays into first array
    //* Use example: {7, 8, 9} and {1, 2, 3}
    // See Arrays


    // MARK: Palindrome linked list
    // See LinkedList


    // MARK: Find the duplicate number
    // Given an array containing n + 1 integers where each integer is between 1 and n (inclusive)

    // The main idea is the same with problem Linked List Cycle II.
    // Use two pointers the fast and the slow. The fast one goes forward two steps each time, 
    // while the slow one goes only step each time. They must meet the same item when slow==fast. 
    // In fact, they meet in a circle, the duplicate number must be the entry point of the circle 
    // when visiting the array from nums[0]. Next we just need to find the entry point. 
    // We use a point(we can use the fast one before) to visit form beginning  with one step each time, 
    // do the same job to slow. When fast==slow, they meet at the entry point of the circle.
    
    //! Not similar to-
    //*   - Find all duplicates in an array
    //*   - Remove all duplicates in a sorted array        
    
    public int findDuplicate(int[] nums){
        int turtle = nums[0];
        int hare = nums[nums[0]];

        while(turtle != hare){
            turtle = nums[turtle];
            hare = nums[nums[hare]];
        }

        hare = 0;
        while(turtle != hare){
            turtle = nums[turtle];
            hare = nums[hare];
        }

        return turtle;
    }

    // MARK: Reverse vowels in a string
    // See Strings


    // MARK: Implement strStr
    // See Strings


    // MARK: Intersection of two arrays
    // See HashMaps


    // MARK: Return Nth node from end of the list
    // See LinkedLists


    // MARK: Validate a sentence with spaces, symbols as a palindrome
    public boolean validPalindrome(String s){
        s = s.replaceAll("[^a-zA-Z0-9", "").toLowerCase();   // remove all spaces and non-alphanumeric characters

        int begin = 0;
        int end = s.length() - 1;

        while(begin <= end){
            if(s.charAt(begin) == ' ' || s.charAt(end) == ' ') 
                return false;
            if(s.charAt(begin) != s.charAt(end)) 
                return false;
            begin++;
            end--;
        }
        return true;
    }

    // MARK: Sort colors
    public int[] sortColors(int[] nums){
        int begin = 0;
        int end = nums.length - 1;

        for(int i = 0; i < nums.length; i++){
            if(nums[i] == 0)
                nums = swap(i, begin++, nums);
            else if(nums[i] == 2)
                nums = swap(i--, end--, nums);
        }
        return nums;
    }


    // MARK: Two sum in a sorted array
    // See Arrays


    // MARK: Rotate a LinkedList
    // See LinkedList

    
}