public class Practice{
    
    // MARK: Add two LinkedLists
    public Node addLL(Node head1, Node head2){
        Node dummy_head = new Node(0);
        Node cur = dummy_head;

        Node cur1 = head1;
        Node cur2 = head2;

        int sum = 0;
        while(cur1 != null || cur2 != null){
            if(cur1 != null) sum += cur1.val;
            if(cur2 != null) sum += cur2.val;

            Node new_node = sum % 10;
            cur.next = new_nodel
            cur = new_node;
            
            sum /= 10;
        }
        if(sum == 1)
            cur.next = new Node(1);

        return dummy_head.next;
    }

    // MARK: Reverse a LinkedList
    public Node reverseLinkedList(Node head){
        Node cur = head;
        Node new_head = null;
        while(cur != null){
            Node next = cur.next;
            cur.next = new_head;
            new_head = cur;
            cur = new_head;
        }
        return new_head;
    }

    // MARK: Merge sort a LinkedList
    public Node mergeSortList(Node head){
        if(head == null || head.next == null)
            return head;

        Node hare = head;
        Node turtle = head;
        Node prev = null;

        while(hare != null || hare.next != null){
            prev = turtle;
            turtle = trtle.next;
            hare = hare.next.next;
        }

        prev.next = null;

        return mergeLists(mergeSortList(head), mergeSortList(turtle));
    }

    // MARK: Merge two sorted lists
    public Node mergeLists(Node head1, Node head2){
        if(head1 == null) return head2;
        if(head2 == null) return head1;

        Node new_head = null;

        if(head1.val < head2.val){
            new_head = head1;
            new_head.next = mergeLists(head1.next, head2);
        }
        else{
            new_head = head2;
            new_head.next = mergeLists(head1, head2.next);
        }
        return new_head;
    } 

    // MARK: Odd even linked list
    public restructureLL(Node head){
        Node head_odd = head;
        Node head_even = head.next;

        Node cur_odd = head_odd;
        Node cur_even = head_even;

        while(cur_even != null | cur.next != null){
            cur_odd.next = cur_even.next;
            cur_odd = cur_odd.next;

            cur_even.next = cur_odd.next;
            cur_even = cur_even.next;
        }

        cur_odd.next = head_even;

        return head_odd;
    }

    // MARK: Intersection of two LinkedLists
    public Node intersectionOfTwoLinkedLists(Node head1, Node head2){
        Node cur1 = head1;
        Node cur2 = head2;

        while(cur1 != cur2){
            cur1 = cur1.next == null ? head2 : cur1.next;
            cur2 = cur2.next == null ? head1 : cur2.next;
        }

        return cur1;
    }

    // MARK: Palindrome LinkedList
    public boolean palindromeLinkedList(Node head){
        Node hare = head;
        Node turtle = head;
        Node prev = null;

        while(hare != null || hare.next != null){
            prev = turtle;
            turtle = turtle.next;
            hare = hare.next.next;
        }
        if(hare != null)
            turtle = turtle.next;

        prev.next = null;

        turtle = reverseLinkedList(turtle);
        hare = head;

        while(turtle != null){
            if(turtle.val != hare.val)
                return false;
            
            turtle = turtle.next;
            hare = hare.next;
        }
        return true;
    }

    // MARK: Reorder list
    public boolean reorderLinkedList(Node head){
        if(head == null || head.next == null || head.next.next == null) return head;

        // find middle
        Node turtle = head;
        Node hare = head;
        Node prev = null;

        while(hare != null && hare.next != null){
            prev = turtle;
            turtle = turtle.next;
            hare = hare.next.next;
        }
        
        // break the list
        prev.next = null;

        // reverse the second list
        turtle = reverseList(turtle);

        return combineLists(head, turtle, true);
    }

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
            new_head.next = combineLists(head1,, head2.next);
        }
        return new_head;
    }

    // MARK: Swap nodes in pairs
    public Node swapNodesInPairs(Node head){
        Node first = head;
        Node second = head.next;
        Node next = second.next;

        second.next = first;
        first.next = swapNodesInPairs(next);

        return second;
    }
    
    // MARK: Remove linked list nodes having a particular value
    public Node removeElements(Node head, int target){
        if(head == null) return head;

        head.next = removeElements(head.next, target);
        return head.val == target ? head.next : head;
    }

    // MARK: Remove duplicates from a sorted LinkedList
    public Node removeDuplicates(Node head){
        if(head == null)
            return head;

        head.next = removeDuplicates(head.next);
        return head.next == null ? head : head.val == head.next.val ? head.next : head;
    }

    // MARK: Sorted LinkedList to BST
    public TreeNode listToBST(ListNode head){
        return listToBST(head, null);
    }

    listToBST(ListNode head, ListNode tail){
        Node hare = head;
        Node turtle = head;

        while(hare != null){
            turtle = turtle.next;
            hare = hare.next.next;
        }

        TreeNode root = new TreeNode(turtle.val);

        root.left = listToBST(head, turtle);
        root.right = listToBST(turtle.next, tail);

        return root;
    }


}