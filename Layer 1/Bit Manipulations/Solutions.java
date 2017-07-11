public class Solution{
    
    // MARK: Find the only single number in an array
    // Return the number which is present only once.
    // All numbers belong to the range- 1 to nums.length.
    public int singleNumber(int[] nums){
        int result = 0;

        for(int i = 0; i < nums.length; i++){
            result ^= nums[i];
        }

        return result ^ 0;
    }
    
}