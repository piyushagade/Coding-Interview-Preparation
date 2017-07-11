/*!
//! LinkedList functions:
    - poll(), pollFirst(), remove(), or removeFirst()
    - pollLast(), or removeLast()
    - peek(), or peekFirst()
    - peekLast()
    - offerFirst(), addFirst()
    - offer(), or offerLast(), add(), or addLast()
    - remove(int index) - removes the element at index
    - remove(Object o) - removes the first occurence of the Object o
*/

public class Soluion {

    // MARK: Add two numbers represented by a LinkedList.
    // First node is the LSB
    public Node addLL(Node head1, Node head2) {
        Node dummy_head = new Node(0);
        Node cur = dummy_head;              //! Uses dummy_head

        Node cur1 = head1;
        Node cur2 = head2;

        int sum = 0;

        while (cur1 != null || cur2 != null) {
            if (cur1 != null)
                sum += cur1.val;
            if (cur2 != null)
                sum += cur2.val;

            Node new_node = new Node(sum % 10);
            cur.next = new_node;
            cur = new_node;

            sum /= 10;

            if (cur1 != null)
                cur1 = cur1.next;
            if (cur2 != null)
                cur2 = cur2.next;
        }

        if (sum == 1)
            cur.next = new Node(1);

        return dummy_head.next;
    }

    // MARK: Delete a given value from a singly linked list
    public void deleteNodeSLL(Node head, int value) {
        if (head == null)
            return;

        for (Node cur = head; cur != null; cur = cur.next) {
            if (cur.next != null && cur.next.val == value)
                cur.next = cur.next.next;
        }
    }

    // MARK: Reverse a LinkedList
    public Node reverseLL(Node head){
        Node cur = head;
        Node new_head = null;
        while(cur != null){
            Node next = cur.next;
            cur.next = new_head;
            new_head = cur;
            cur = next;
        }
        return new_head;
    }

    // MARK: Merge Sort a LinkedList
    public Node sortList(Node head){
        if(head == null || head.next == null)
            return head;
        
        Node hare = head;
        Node turtle = head;
        Node prev = null;

        while(hare != null || hare.next != null){
            prev = turtle;
            turtle = turtle.next;
            hare = hare.next.next;
        }

        prev.next = null;

        mergeLists(sortList(head), sortList(turtle));
    }

    // MARK: Merge two sorted lists into one sorted list
    public mergeLists(Node head1, Node head2){
        if(head1 == null) return head2;
        if(head2 == null) return head1;

        Node new_head = null;

        if(head1.val < head2.val){
            new_head = head1;
            mergeLists(head1.next, head2);
        }
        else{
            new_head = head2;
            mergeLists(head1, head2.next);
        }

        return new_head;
    }

    // MARK: Detect a cycle in a linked list
    public boolean cycleInLL(Node head){
        if(head == null || head.next == null || head.next.next == null) return false;

        Node hare = head;
        Node turtle = head;

        while(hare != null || hare.next != null){
            turtle = turtle.next;
            hare = hare.next.next;
            
            if(hare == turtle) return true;
        }
        return false;
    }

    // MARK: Detect a cycle in a linked list and return the node where the cycle starts
    public Node cycleStartNodeInLL(Node head){
         if(head == nul || head.next == null || head.next.next == null) return null;

         Node turtle = head;
         Node hare = head;

         while(hare != null || hare.next != null){
             turtle = turtle.next;
             hare = hare.next.next;

             if(hare == turtle){
                 turtle = head;
                 while(hare != turtle){
                     hare = hare.next;
                     turtle = turtle.next;
                 }
                 return turtle;
             }
             return null;
         }
    }

    // MARK: Odd even Linked list
    public Node restructLLOddEven(Node head){
        if(head == null || head.next == null || head.next.next == null) return head;

        Node head_odd = head;
        Node head_even = head.next;

        Node cur_odd = head_odd;
        Node cur_even = head_even;

        while(cur_even != null && cur_even.next != null){
            cur_odd.next = cur_even.next;
            cur_odd = cur_odd.next;

            cur_even.next = cur_odd.next;
            cur_even = cur_even.next;
        }

        odd_cur.next = even_head;
        return odd_head;
    }

    // MARK: Intersection of two LinkedLists
    // Find the node where two lists meet
    public Node intersectionOfLists(Node head1, Node head2){
        Node cur1 = head1;
        Node cur2 = head2;

        while(cur1 != cur2){
            cur1 = cur1.next != null ? cur1.next : head2;
            cur2 = cur2.next != null ? cur2.next : head1;
        }
        return cur1;
    }

    // MARK: Copy a list with random pointer
    public Node copyListWithRandomPointer(Node head){
         if(head == null) return  head;

         HashMap<Node, Node> map = new HashMap<>();

         for(Node cur = head; cur != null; cur = cur.next){
             Node new_node = new Node(cur.val);
             map.put(cur, new_node);
         }

         for(Node cur = head; cur != null; cur = cur.nect){
             map.get(cur).next = map.get(cur.next);
             map.get(cur).random = map.get(cur.random);
         }

         return map.get(head);
    }

    // MARK: Palindrome linked list
    public boolean palindromeLL(Node head){
        if(head == null || head.next == null) return true;
        
        Node hare = head;
        Node turtle = head;

        while(hare != null && hare.next != null){
            turtle = turtle.next;
            hare = hare.next.next;
        }

        // Make the left half bigger
        if(hare != null) turtle = turtle.next;

        turtle = reverseList(turtle);

        hare = head;
        while(turtle != null){
            if(hare.val != turtle.val) return false;
            turtle = turtle.next;
            hare = hare.next;
        }
        return true;
    }

    // MARK: Remove nth node from the end of the list
    public void removeNth(Node head, int n){
        if(head == null) return head;

        Node pawn = head;
        Node queen = head;
        Node prev = null;

        // Move the pawn n - 1 steps
        for(int i = 0; i < n - 1; i++){
            pawn = pawn.next;
        }

        // Move queen and pawn and prev together
        while(pawn.next != null){
            prev = queen;
            queen = queen.next;
            pawn = pawn.next;
        }

        // delete the node next to prev (queen)
        prev.next = prev.next.next;
    }

    // MARK: Reorder list 1 - 2 - 3 - 4 - 5 to 1 - 5 - 2 - 4 - 3
    public Node reorderList(Node head){
        if(head == null || head.next == null || head.next.next == null) return head;

        //find middle
        Node turtle = head;
        Node hare = head;
        Node prev = head;

        while(hare != null && hare.next != null){
            prev = turtle;
            turtle = turtle.next;
            hare = hare.next.next;
        }
        
        //break the list
        prev.next = null;

        //reverse the second list
        turtle = reverseList(turtle);
    }

    //! Similar to mergeLists
    public Node combineLists(Node head1, Node head2, boolean turn){
        if(head1 == null) return head2;
        if(head2 == null) return head1;

        Node new_head = null;

        if(turn){
            new_head = head1;
            new_head.next = combineLists(head1.next, head2);
        }
        else{
            new_head = head2;
            new_head.next = combineLists(head1, head2.next);
        }

        return new_head;
    }

    // MARK: Swap nodes in pairs; 1 -> 2 -> 3 -> 4 to 2 -> 1 -> 4 -> 3
    public Node swapNodes(Node head){
        if(head == null) return head;

        Node first = head;
        Node second = head.next;
        Node next = head.next.next;

        second.next = first;
        first.next = swapNodes(next);

        return second;
    }

    // MARK: Remove linked list nodes having a particular value
    public Node removeElementsIterative(Node head, int target){
        if(head == null) return head;

        for(Node cur = head; cur != null; cur = cur.next){
            if(cur != null && cur.next.val == target) 
                cur.next = cur.next.next;
        }
        return head;
    }

    public Node removeElementsRecursive(Node head, int target){
        if(head == null) return head;
        
        head.next = removeElementsRecursive(head.next, target);     //! Remember this statement
        return head == target ? head.next : head;
    }

    // MARK: Remove duplicates from a sorted Linked List
    public Node removeDuplicates(Node head){
        if(head == null) return head;

        head.next = removeDuplicates(head.next);
        return head.next == null ? head : (head.val == head.next.val ? head.next : head);   //! Remember this statement
    }

    // MARK: Sorted lists to BST
    public TreeNode listToBST(ListNode head){
        if(head == null) return null;

        return listToBST(head, null);
    }

    public TreeNode listToBST(ListNode head, ListNode tail){        //! Remember the overloading of the function and its signature
        if(head == tail) return head;

        Node hare = head;
        Node turtle = head;

        while(hare != null){
            turtle = turtle.next;
            hare = hare.next.next;
        }

        TreeNode node = new TreeNode(turtle.val);
        node.left = listToBST(head, turtle);
        node.right = listToBST(turtle.next, tail);

        return node;
    }

    // MARK: Rotate list (uses pawn and queen pointers)
    public Node rotateList(Node head, int k){
        if(head == null || head.next == null) return head;

        Node prev = null;
        Node pawn = head;
        Node queen = head;
        
        for(int i = 0; i < k - 1; i++){
            pawn = pawn.next;
        }
        
        while(pawn.next != null){
            pawn = pawn.next;
            
            prev = queen;
            queen = queen.next;
        }

        prev.next = null;
        pawn.next = head;

        return queen;
    }

    // MARK: Add two numbers represented by linked lists, similar to a problem we did earlier, 
    // but the only difference is that here the first node is MSB
    public Node addTwoLists(Node head1, Node head2){
        Stack<Integer> s1 = new Stack<>();
        Stack<Integer> s2 = new Stack<>();

        for(Node cur = head1; cur != null; cur = cur.next)
            s1.push(cur.val);

        for(Node cur = head2; cur != null; cur = cur.next)
            s2.push(cur.val);

        Node dummy_head = new Node(0);
        Node cur = dummy_head;

        int sum = 0;
        while(!s1.isEmpty() || !s2.isEmpty()){
            if(!s1.isEmpty()) sum += s1.pop();
            if(!s2.isEmpty()) sum += s2.pop();

            Node new_node = new Node(sum % 10);

            cur.next = new_node;
            cur = new_node;

            sum /= 10;
        }
        if(sum == 1) cur.next = new Node(1);

        return dummy_head.next;
    }

}
