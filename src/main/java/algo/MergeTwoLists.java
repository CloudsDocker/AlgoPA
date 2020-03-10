package algo;

import java.util.List;

public class MergeTwoLists {
    /*
    21. Merge Two Sorted Lists
Easy

Merge two sorted linked lists and return it as a new list. The new list should be made by splicing together the nodes of the first two lists.

Example:

Input: 1->2->4, 1->3->4
Output: 1->1->2->3->4->4
     */
    public static void main(String[] args) {
        ListNode node=mergeTwoLists(ListNode.buildList1(),ListNode.buildList2());
        node=mergeTwoListsIte(ListNode.buildList1(),ListNode.buildList2());
        System.out.println("===:"+node);

    }
    public static ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        if(l1==null) return l2;
        if(l2==null) return l1;

        if(l1.val<l2.val){
            l1.next=mergeTwoLists(l1.next,l2);
            return l1;
        }else{
            l2.next=mergeTwoLists(l2.next,l1);
            return l2;
        }
    }

    public static ListNode mergeTwoListsIte(ListNode l1, ListNode l2){
        if(l1==null) return l2;
        else if(l2==null) return l1;

        ListNode headDummy = new ListNode(0);
        ListNode curr = headDummy;
        while(l1 !=null && l2!=null){
            if(l1.val<l2.val){
                curr.next=l1;
                l1=l1.next;
            }else{
                curr.next=l2;
                l2=l2.next;
            }
            curr=curr.next;
        }
        curr.next=l1==null?l2:l1;
        return headDummy.next;
    }
}
