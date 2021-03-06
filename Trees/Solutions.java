public class Solution{
    
    // MARK: Binary tree in-order traversal
    public List<Integer> inOrder(Node root){
        List<Integer> list = new ArrayList<>();
        Stack<Node> s = new Stack<>();
        Node cur = root;

        while(cur != null || !s.isEmpty()){
            while(cur != null){
                s.push(cur);
                cur = cur.left;
            }

            cur = s.pop();
            list.add(cur.val);
            cur = cur.right;
        }
        return list;
    }

    // MARK: kth smallest in a BST
    public class KthSmallestInABST{
        int result = Integer.MIN_VALUE;
        int rank = 0;

        public int kthSmallest(Node root, int k){
            inorder(root, k);
            return result;
        }

        private void inorder(Node root, int k){
            if(root == null) return;

            inorder(root.left, k);
            if(++rank == k) result = root.val;
            inorder(root.right, k);
        }
    }

    // MARK: Closet BST value
    public int closestValue(Node root, int target){
        int a = root.val;
        Node child = null;

        if(a < target) 
            child = root.left;
        else 
            child = root.right;

        if(child == null) 
            return a;

        int b = closestValue(child, target);

        return Math.abs(a - target) < Math.abs(b - target) ? a : b; 
    }

    

    // MARK: Binary tree level order / zig-zag level order traversal
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

    // MARK: Validate BST
    public boolean isValid(Node root){
        return isValid(root, Integer.MIN_VALUE, Integer.MAX_VALUE);
    }

    public boolean isValid(Node root, int min, int max){
        if(root == null) return true;

        if(root.val < min || root.val > max) return false;
        return isValid(root.left, min, root.val) && isValid(root.right, root.val, max);
    }

    // MARK: Symmetric tree
    public boolean isSymmetric(Node root){
        if(root == null) return true;

        return isSymmetric(root.left, root.right);
    }

    public boolean isSymmetric(Node left, Node right){
        if(left == null && right == null) return true;
        if(left == null || right == null) return false;

        return (left.val == right.val) && isSymmetric(left.left, right.right) && isSymmetric(left.right, right.left);
    }

    // MARK: Maximum depth of binary tree
    public int maxDepth(Node root){
        if(root == null) return 0;

        int left = maxDepth(root.left);
        int right = maxDepth(root.right);

        return 1 + Math.max(left, right);
    }

    // MARK: Balanced binary tree (needs depth function)
    public boolean isBalanced(Node root){
        if(root == null) return true;
        return (depth(root.left) - depth(root.right) <= 1) && isBalanced(root.left) && isBalanced(root.right);
    }

    public int depth(Node root){
        if(root == null) return 0;
        return 1 + Math.max(depth(root.left), depth(root.right));
    }

    // MARK: Convert sorted array to BST (needs overloading)
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

    // MARK: Populating next right pointers in each node
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

    // MARK: Binary tree to Doubly Linked List
    public Node BTToDLL(Node root){
        return inorder(root);
    }

    public Node inorder(Node root){
        if(root == null) return root;

        Node prev = inorder(root.left);
        Node head = root;
        Node next = inorder(root.right);

        head.left = prev;
        head.right = next;

        return head;
    }

    // MARK: Lowest common ancestor in Binary tree
    public Node lcaBT(Node root, Node p, Node q){
        if(root == null || root == p || root == q) return root;

        Node left = lcaBT(root.left, p, q);
        Node right = lcaBT(root.right, p, q);

       if(left == null) return right;
       if(right == null) return left;
       
       return root;
    }

    // MARK: Lowest common ancestor in Binary search tree
    public Node lcaBST(Node root, Node p, Node q){
        if(root == null) return null;

        if(p.val < root.val && root.val < q.val) return root;
        else if(root.val < p.val) return lcaBST(root.right, p, q);
        else if(q.val < root.val) return lcaBST(root.left, p, q);
    }
}