public class Solution{
    
    // MARK: Copy LinkedList with random pointers
    //* See LinkedList

    // MARK: Group anagrams (friendly words)
    public List<List<String>> groupAnagrams(String[] words){
        List<List<String>> result = new ArrayList<>();
        HashMap<String, List<String>> map = new HashMap<>();

        for(String word : words){
            char[] chars = word.toCharArray();

            Arrays.sort(chars);
            String key = String.valueOf(chars);

            if(!map.containsKey(key))
                map.put(key, new ArrayList<String>);

            map.get(key).add(word);
        }
        return result;
    }

    // MARK: Validate an anagram
    // If the inputs contain unicode characters, an array with length of 128 is not enough.
    // Unicode is 2 ^ 21. Using the array approach will not work.
    public boolean validAnagram(String word1, word2){
        int[] hash = new int[128];

        for(char c : word1.toCharArray())
            hash[c]++;

        for(char c : word2.toCharArray())
            hash[c]--;

        for(int number : hash)
            if(number != 0) return false;

        return true;
    }

    // MARK: Find repeated sequences in a DNA
    // Find repeated substrings that are 10 characters long in a string
    public List<String> repeatedSequence(String s){
        HashSet<String> seen = new HashSet<>();
        HashSet<String> repeated = new HashSet<>();

        for(int i = 0; i < s.length() - 9; i++){
            if(!seen.add(s.substring(i, i + 10)))
                repeated.add(s.substring(i, i + 10));
        }
        return new ArrayList(repeated);       //! Make ArrayList out of a HashSet
    }

    // MARK: Find intersection of two arrays
    public int[] intersectionOfArrays(int[] nums1, int[] nums2){
        HashSet<Integer> set = new HashSet<>();
        HashSet<Integer> intersection = new HashSet<>();

        for(int number : nums1)
            set.add(number);

        for(int number : nums2)
            if(!set.add(number))
                intersection.add(number);
        
        int[] result = new int[intersection.size()];

        int i = 0;
        for(int number : intersection){     //! Traversing a HashSet
            result[i++] = number; 
        }
        return result;
    }

    // MARK: Intersection of two arrays II (report same elements even if they are present multiple times)
    public int[] intersection(int[] nums1, int[] nums2){
        HashMap<Integer, Integer> map = new HashMap<Integer, Integer>();
        ArrayList<Integer> result = new ArrayList<Integer>();
        
        for(int i = 0; i < nums1.length; i++)
            map.put(nums1[i], map.getOrDefault(nums1[i], 0) + 1);
    
        for(int i = 0; i < nums2.length; i++)
            if(map.containsKey(nums2[i]) && map.get(nums2[i]) > 0){
                result.add(nums2[i]);
                map.put(nums2[i], map.get(nums2[i]) - 1);
            }
        
    
        int[] res = new int[result.size()];
        for(int i = 0; i < result.size(); i++) 
            res[i] = result.get(i);

       return res;
    }

    // MARK: Top K frequent elements in an array
    public List<Integer> topKFrequent(int[] nums, int k){
        List<Integer> result = new ArrayList<>();
        HashMap<Integer, Integer> map = new HashMap<>();
        PriorityQueue<Map.Entry<Integer, Integer>> pq = 
                new PriorityQueue<>((a, b) -> (b.getValue() - a.getValue()));     //! This gives a max heap.
        
        // Build a HashMap
        for(int i = 0; i < nums.length; i++){
            map.put(nums[i], map.getOrDefault(nums[i], 0) + 1);
        }

        // Put values in PriorityQueue
        for(Map.Entry<Integer, Integer> entry : map.entrySet()){    //! Traversing a HashMap
            pq.offer(entry);        
        }

        //! buildHeap() is O(n), so putting the values in a heap is O(n) 

        // Poll top k elements and add them to the result ArrayList
        for(int i = 0; i < k; i++){
            result.add(pq.poll().getKey());
        }

        return result;
    }

    // Count primes up to the given integer n
    public int countPrimes(int n){
        boolean[] notPrimes = new boolean[n + 1];   //! N + 1 because we want index 1 to represent 1, and so on.
        int count = 0;

        notPrimes[0] = true;
        notPrimes[1] = true;

        for(int i = 2; i <= n; i++)
            notPrimes[i] = false;

        for(int i = 2; i <= n; i++){
            if(!notPrimes[i]){
                count++;

                // Mark the multiples of i as not primes
                for(int j = 2; i * j <= n; j++){
                    notPrimes[i * j] = true;
                }
            }
        }
        return count;
    }

    // MARK: Isomorphic strings
    // Example: egg and add -> true;  tad and abc -> true; fall and fool -> false
    public boolean isomorphicStrings(String word1, String word2){
        HashMap<Character, Character> map = new HashMap<>();
        
        // Two outer iterations. One for egg and add, the other for add and egg.
        // This is required for the following like cases: adm and egg.
        for(int i = 1; i <= 2; i++){
            for(int j = 0; word1.length(); j++){
                if(map.containsKey(word1.charAt(j)) && map.get(word1.charAt(j) != word2.charAt(j)))
                    return false;
                
                map.put(word1.charAt(j), word2.charAt(j));
            }

            // Swap words
            String temp = word1;
            word1 = word2;
            word2 = word1;

            // Reset hashmap
            map = new HashMap<>();
        }
        return true;
    }

    // MARK: Island perimeter
    public int islandPerimeter(int[][] matrix){
        int neighbors = 0;
        int land = 0;

        int rows = matrix.length;
        int cols = matrix[0].length;

        for(int i = 0; i < rows; i++){
            for(int j = 0; j < cols; j++){
                if(matrix[i][j] == 1){
                    land++;

                    if(j < cols - 1 && matrix[i][j + 1] == 1) 
                        neighbors++;
                    if(i < rows - 1 && matrix[i + 1][j] == 1) 
                        neighbors++;
                }
            }
        }
        return (4 * land - 2 * neighbors);
    }

    // MARK: Word pattern
    // Pattern = "abba", str = "dog cat cat dog" should return true.
    public boolean wordPattern(String pattern, String str){
        HashMap<Character, String> map = new HashMap<>();
        String[] words = str.split(" ");

        for(int i = 0; i < pattern.toCharArray().length; i++){
            if(map.containsKey(pattern.charAt(i)) && !map.get(pattern.charAt(i)).equals(words[i]))
                return false;

            map.put(pattern.charAt(i), words[i]);
        }
        return true;
    }

    // MARK: Palindrome permutation
    // Given a string, determine if a permutation of the string could form a palindrome.
    public boolean palindromePermutation(String str){
        HashSet<Character> set = new HashSet<>();

        for(char c : str.toCharArray()){
            if(set.contains(c))
                set.remove(c);
            else
                set.add(c);
        }
        return set.size() <= 1;
    }
}