package algo;

import static algo.ListNode.buildList1;

public class ReverseListFirstN{
    public static void main(String[] args) {
        ListNode head = buildList1();
        System.out.println("==execution result is:"+new ReverseListFirstN().reverseListFirstN(head, 3));
    }

    private ListNode reverseListFirstN(ListNode head, int n) {
        ListNode successor =null;
        if(n==1){
            successor=head.next; // save next postion to temp variable
            return head;

        }

        ListNode last =     reverseListFirstN(head.next, n-1);
        head.next.next=head;
        head.next=successor;
        return last;
    }
}