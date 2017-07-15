public class Solution{
    // MARK: Validate perfect square
    // This solution is based on the observation that sum of any consecutive numbers (starting from 1) in the series 1, 3, 5, 7, 9,... is a perfect square.  
    public boolean validatePerfectSquare(int n){
        int diff = 1;

        while(n > 0){
            n -= diff;
            dif = dif + 2;
        }
        return n == 0;
    }
    
}