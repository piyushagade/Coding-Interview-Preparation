public class Practice{
    
    // MARK: Copy LinkedList with random pointers
    public Node copyListWithRandomPointers(Node head){
        HashMap<Node, Node> map = new HashMap();

        for(Node cur = head; cur != null; cur = cur.next)
            map.put(cur, new Node(Cur.value));
        
        for(Node cur = head; cur != null; cur = cur.next){
            map.get(cur).next = map.get(cur.next);
            map.get(cur).random = map.get(cur.random);
        }
        return map.get(head);
    }

    // MARK: Group anagrams (Friendly words)
    public List<List<String>> groupAnagrams(String[] words){
        List<List<String>> result = new ArrayList<>();
        HashMap<String, ArrayList<String>> map = new HashMap<>();

        for(String word : words){
            char[] chars = word.toCharArray();
            chars.sort();
            String key = String.valueOf(chars);

            if(!map.containsKey(key))
                map.put(key, new ArrayList<>());

            map.get(key).add(word);
        }
        return result;
    }

    // MARK: Validate an anagram
    public boolean validAnagram(String word1, word2){
        int[] hash = new int[256];

        for(char c : words1.toCharArray())
            hash[c]++;
        
        for(char c : word2.toCharArray())
            hash[c]--;

        for(int frequency : hash)
            if(frequency != 0)
                return false;

        return true;
    }

    // MARK: Find repeated substrings of length 10 DNA subsequences
    public List<String> repeatedSequence(String s){
        HashSet<String> seen = new HashSet<>();
        HashSet<String> repeated = new HashSet<>();

        for(int i = 0; i < s.length() - 9; i++){
            if(!seen.add(s.substring(i, i + 10)))
                repeated.add(s.substring(i, i + 10));
        }
        return new ArrayList(repeated);
    }

    // MARK: Top k frequent elements in an array
    public List<Integer> topKFrequent(int[] nums, int k){
        List<Integer> result = new ArrayList<>();
        PriorityQueue<Map.Entry<Integer, Integer>> pq = 
                new PriorityQueue((a,b) -> (b.getValue() - a.getValue()));

        for(int i = 0; i < nums.length; i++){
            map.put(nums[i], map.getOrDefault(nums[i], 0) + 1);
        }

        for(Map.Entry<Integer, Integer> entry : map.entrySet()){
            pq.offer(entry);
        }

        for(int i = 0; i < k; i++){
            result.add(pq.poll().getKey());
        }

        return result;
    }

    // MARK: Count the number of primes up to the integer n
    public int countPrimes(int n){
        boolean[] notPrimes = new boolean[n + 1];

        for(int i = 0; i < n; i++)
            notPrimes[i] = false;
        
        notPrimes[0] = true;
        notPrimes[1] = true;

        for(int i = 2; i <= n; i++){
            if(!notPrimes[i]){
                count++;

                for(int j = 2; i * j <= n; j++){
                    notPrimes[i * j] = true;
                }
            }
        }
        return count;
    }

    // MARK: Isomorphic strings
    public boolean isomorphicStrings(String word1, String word2){
        HashMap<Character, Character> map = new HashMap<>();

        for(int i = 1; i <=2; i++){
            for(int j = 0; j = word1.length(); j++){
                if(map.containsKey(word1.charAt(j) && map.get(word1.charAt(j)) != word2.charAt(j))
                    return false;
                map.put(word1.charAt(j), word2.charAt(j));
            }

            String temp = word1;
            word1 = word2;
            word2 = temp;

            map = new HashMap<>();
        }
        return true;
    }

    // MARK: Island Perimeter
    public int islandPerimeter(int[][] matrix){
        int rows = matrix.length;
        int cols = matrix[0].length;

        int lands = 0;
        int neighbours = 0;

        for(int i = 0; i < rows; i++){
            for(int j = 0; j < cols; j++){
                land++;

                if(i < rows - 1 && matrix[i + 1][j] == 1)
                    neighbours++;
                if(j < cols - 1 && matrix[i][j + 1] == 1)
                    neighbours++;
            }
        }
        return 4 * lands - 2 * neighbours;
    }

    // MARK: Word pattern

}