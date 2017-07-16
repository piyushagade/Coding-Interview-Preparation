public class Solutions{

    public class LRU{
        int capacity;
        LinkedList<Node> list;
        HashMap<Integer, Node> map;
        Node head;
        Node tail;

        LRU(Integer capacity){
            this.capacity = capacity;
            this.list = new LinkedList<>();
            this.map = new HashMap<>();
            this.head = new Node(0);
            this.tail = new Node(0);
            this.size = 0;
        }

        private class Node{
            int value;
            int key;
            Node prev;
            Node next;

            Node(int key, int value){
                this.value = value;
                this.key = key;
            }
        }

        public int get(int key){
            if(!map.containsKey(key))
                return -1;

            Node node = map.get(key);
            deleteNode(node);
            addToFront(node);

            return node.value;
        }

        public void put(int key, int value){
            if(map.containsKey(key)){
                Node node = map.get(key);

                node.value = value;
                map.put(key, node);
                deleteNode(node);
                addToFront(node);
            }
            else{
                if(map.size() > this.capacity){
                    Node old = map.get(tail.prev);
                    map.remove(old.key);
                }
                
                Node node = new Node(key, value);
                addToFront(node);
                map.put(key, value);
            }
        }
    }

    // MARK: Pythagorean triplet
    public boolean pythagoreanTriplet(int[] nums){
        for(int i = 0; i < nums.length; i++)
        nuns[i] = nums[i] * nums[i];
    
        Arrays.sort(nums);

        for(int i = nums.length - 1; i <= 0; i--){
            //look for pair that adds up to nums[i]
            int left = 0;
            int right = i - 1;
            while(left < right){
                if(nums[left] + nums[right] = nums[i])
                    return true;
                if(nums[left] + nums[right] < nums[i])
                    left++;
                else
                    right--;
            }
        }
        return false;
    }

    // MARK: Min stack
    Stack<Integer> stack = new Stack<>();
    int min = Integer.MAX_VALUE;

    public void push(int data){
        if(data <= min){
            stack.push(min);
        }
        stack.push(data);
    }

    public int pop(){
        int data = stack.pop();
        if(data == min) min = stack.pop();
        return data;
    }

    public int min(){
        return min;
    }
}