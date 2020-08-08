package algo;

import static algo.ListNode.buildList1;

public class ReverseList {
    public static void main(String[] args){
        ListNode node = buildList1();
        System.out.println("--run program---:"+new ReverseList().reverseList(node));

    }


    private ListNode reverseList(ListNode head) {

        // base case
        if(head.next==null)
            return head;

        // recursively call 
        ListNode last = reverseList(head.next);
        head.next.next = head;
        head.next = null;
        return last;
    }
}