package algo;


import java.util.List;

public class ListNode {
      int val;
      ListNode next;
      ListNode(int x) { val = x; }

      public static ListNode buildList1(){
          ListNode node1=new ListNode(1);
          ListNode node2 = new ListNode(2);
          ListNode node3 = new ListNode(3);
          ListNode node4 = new ListNode(4);

          node1.next=node2;
          node2.next=node4;
          return node1;
      }

    public static ListNode buildList2(){
        ListNode node1=new ListNode(1);
        ListNode node3 = new ListNode(3);
        ListNode node4 = new ListNode(4);

        node1.next=node3;
        node3.next=node4;
        return node1;
    }
  }

