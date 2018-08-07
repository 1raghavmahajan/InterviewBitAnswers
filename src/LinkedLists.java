public class LinkedLists {


    //     Definition for singly-linked list.
    ListNode start;

    private void recursiveSol(ListNode a){
        if(a.next!=null){
            recursiveSol(a.next);
        }
        start.val =  a.val - start.val;
        start = start.next;
    }

    private ListNode revSol(ListNode a){
        ListNode curr = a.next;
        if(a.next!=null) {
            a.next = null;
            ListNode prev = a;
            ListNode nxt;
            while (curr != null) {
                nxt = curr.next;
                curr.next = prev;
                prev = curr;
                curr = nxt;
            }
            a = prev;
        }
        return a;
    }

    public ListNode subtract(ListNode A) {

        ListNode aa = new ListNode(1);
        aa.next = new ListNode(2);
        aa.next.next = new ListNode(3);

        A = aa;

        start = A;
        ListNode last = A;
        int len = 0;
        if(last!=null) {
            len = 1;
            while (last.next != null) {
                last = last.next;
                len++;
            }

            if(len>1) {
                int m = len/2;
                if(len%2!=0)
                    m = len - m;

                ListNode mid = A;
                for (int i = 0; i < m; i++) {
                    mid = mid.next;
                }

                ListNode rev = revSol(mid);

                while(rev!=null &&  start!=null) {
                    start.val = rev.val - start.val;
                    start = start.next;
                    rev = rev.next;
                }

                revSol(last);

            }
        }

        return A;
    }

    public static ListNode deleteDuplicates(ListNode A) {
        ListNode listNode = A;
        ListNode prev = A;
        A = A.next;
        while (A!=null){
            if(A.val == prev.val){
                prev.next = A.next;
            }else {
                prev = A;
            }
            A = A.next;
        }
        return listNode;
    }

    public static ListNode deleteDuplicates2(ListNode A) {
        ListNode listNode = A;
        ListNode prev = A;
        ListNode saved = null, bef = null;
        A = A.next;
        while (A!=null){
            if(A.val == prev.val){
                if(saved==null)
                    saved = prev;
                prev.next = A.next;
            }else {
                if(saved!=null){
                    if(bef!=null) {
                        bef.next = saved.next;
                    }else {
                        listNode = saved.next;
                    }
                    saved = null;
                }else
                    bef = prev;
                prev = A;
            }
            A = A.next;
        }
        if(saved!=null) {
            if(bef!=null) {
                bef.next = saved.next;
            }else {
                return null;
            }
        }
        return listNode;
    }


}
