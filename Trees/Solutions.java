public class Solution{
    
    // MARK: Binary tree inorder traversal
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
}