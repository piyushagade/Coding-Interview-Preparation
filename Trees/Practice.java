public class Practice{

    // MARK: Binary tree in-order traversal
    public List<Integer> inOrder(Node root){
        List<Integer> result = new ArrayList<>();
        Stack<Node> stack = new Stack<>();

        Node cur = root;

        while(cur != null || !stack.isEmpty()){
            while(cur != null){
                stack.push(cur);
                cur = cur.left;
            }

            cur = stack.pop();
            result.add(cur.value);
            cur = cur.right;
        }
        return result;
    }

    // Binary tree level order / zig-zag level order traversal
    public List<List<Integer>> levelOrder(Node head){
        List<List<Integer>> result = new LinkedList<>();
        Queue<Node> q = new LinkedList<>();

        boolean order = true;

        q.offer(root);

        while(!q.isEmpty()){
            int level = q.size();
            List<Integer> row = new LinkedList<>();

            for(int i = 0; i < level; i++){
                Node node = q.poll();
                if(node != null && node.left != null) q.offer(node.left);
                if(node != null && node.right != null) q.offer(node.right);
                if(order) row.add(node.val);
                else row.add(0, node.val);
            }
            order = !order;
            result.add(row);
        }
        return result;
    }

    //Validate BST
    public boolean isValid(Node root){
        return isValid(root, Integer.MIN_VALUE, Integer.MAX_VALUE);
    }

    public boolean isValid(Node root, int min, int max){
        if(root == null) return true;

        if(root.val < min || root.val > max) return false;
        return isValid(root.left, min, root.val) && isValid(root.right, root.val, max);
    }

    //Symmetric tree
    public boolean isSymmetric(Node root){
        if(root == null) return true;

        return isSymmetric(root.left, root.right);
    }

    public boolean isSymmetric(Node left, Node right){
        if(left == null && right == null) return true;
        if(left == null || right == null) return false;

        return (left.val == right.val) && isSymmetric(left.left, right.right) && isSymmetric(left.right, right.left);
    }

    //Maximum depth of binary tree
    public int maxDepth(Node root){
        if(root == null) return 0;

        int left = maxDepth(root.left);
        int right = maxDepth(root.right);

        return 1 + Math.max(left, right);
    }

    //Balanced binary tree (needs depth function)
    public boolean isBalanced(Node root){
        if(root == null) return true;
        return (depth(root.left) - depth(root.right) <= 1) && isBalanced(root.left) && isBalanced(root.right);
    }

    public int depth(Node root){
        if(root == null) return 0;
        return 1 + Math.max(depth(root.left), depth(root.right));
    }

    //Convert sorted array to BST (needs overloading)
    public Node toBST(int[] nums){
        return toBST(nums, 0, nums.length - 1);
    }

    public Node toBST(int[] nums, int lo, int hi){
        if(lo > hi) return null;

        int mid = lo + (hi - lo) / 2;
        Node node = new Node(mid);

        node.left = toBST(nums, lo, mid - 1);
        node.right = toBST(nums, mid + 1, hi);

        return node;
    }

    //Populating next right pointers in each node
    public void populateRight(Node root){
        if(root == null) return root;

        if(root.left != null){
            root.left.next = root.right;
            if(root.right != null && root.next != null) 
                root.right.next = root.next.left;
        }

        populateRight(root.left);
        populateRight(root.right);
    }
}