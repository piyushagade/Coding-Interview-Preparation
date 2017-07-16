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

    // MARK: Reverse an Integer
    public int reverseInteger(int n){
        long result = 0;

        while(n > 0){
            result = result * 10 + n % 10;
            n /= 10;
        }
        return (int) result;
    }

    // MARK: Implement pow(x, n)
    public int pow(double x, int n){
        if(n == 0) return 1;

        if(n < 0){
            n = -n;
            x = 1 / x;
        }
        return n % 2 == 0 ? pow(x * x, n / 2) : x * pow(x * x, n / 2);
    }
    
    // MARK: Square root
    public int sqrt(int n){
        long result = n;

        while(result * result > n)
            result = (result + n / result) / 2; 
        
        return (int) result;
    }

    // MARK: Count primes
    public int countPrimes(int n){
        int count = 0;

        boolean[] notPrime = new boolean[n];

        for(int i = 0; i < n; i++) notPrime[i] = false;

        for(int i = 2; i < n; i++){     //limits start with 2
            if(!notPrime[i]){
                count++;

                for(int j = 2; j * i < n; j++){     //limits start with 2
                    notPrime[i * j] = true;
                }
            }
        }        
        return count;
    }

    // MARK: Coin change
    public int coinChange(int[] coins, int amount){
        Arrays.sort(coins);
        int count = 0;

        for(int i = coins.length - 1; i >= 0; i--){
            while(amount >= coins[i]){
                amount = amount - coins[i];
                count++;
            }
        }
        if(amount != 0) return -1;
        
        return count;
    }

    // MARK: Excel sheet column number
    public int excelColNumber(String s){
        if(s.length() == 0) return 0;

        int result = 0;

        for (char c : s.toCharArray()){
            result = result * 26 + (c - 'A' + 1);
        }
        return result;
    }

    // MARK: Find median from data stream
    PriorityQueue<Integer> min = new PriorityQueue<>();
    PriorityQueue<Integer> max = new PriorityQueue<>(1000, Collections.reverseOrder());

    public void add(int num){
        max.offer(num);
        min.offer(max.poll());

        if(max.size() < min.size()) max.offer(min.poll());
    }

    public int getMean(){
        if(max.size() == min.size()) return (max.peek() + min.peek()) / 2;
        return max.peek();
    }

}