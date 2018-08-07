import javafx.util.Pair;

import java.util.List;

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

    private ListNode revList(ListNode a){
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

                ListNode rev = revList(mid);

                while(rev!=null &&  start!=null) {
                    start.val = rev.val - start.val;
                    start = start.next;
                    rev = rev.next;
                }

                revList(last);

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

    public int lPalin(ListNode A) {
        if(A==null)
            return 1;

        ListNode start = A;
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

                ListNode rev = revList(mid);

                while(rev!=null &&  start!=null) {
                    if(start.val != rev.val)
                        return 0;
                    start = start.next;
                    rev = rev.next;
                }

                revList(last);

            }
        }

        return 1;
    }

    public ListNode reorderList(ListNode A) {
        if(A==null)
            return A;

        ListNode start = A;
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

                ListNode rev = revList(mid);

                ListNode t, t2;
                while(rev!=null &&  start!=null) {
                    t = start.next;
                    t2 = rev.next;
                    start.next = rev;
                    rev.next = t;

                    start = rev.next;
                    rev = t2;
                }

                if(start!=null)
                    start.next = null;

//                revList(last);

            }
        }

        return A;
    }

    public ListNode reverseList(ListNode A, int B) {
        if(B==1)
            return A;
        else if(B==0){
            A.next = null;
            return A;
        }

        ListNode a = A;

        ListNode next = null;
        ListNode newStart = null;

        ListNode curr = a.next;
        if(a.next!=null) {
            a.next = null;
            ListNode prev = a;
            ListNode nxt;
            int i=1;
            while (curr != null && i<B) {
                nxt = curr.next;
                curr.next = prev;
                prev = curr;
                curr = nxt;
                ++i;
            }
            next = curr;
            newStart = prev;
        }

        ListNode end = a;

        while (next!=null) {

            a = next;
            curr = next.next;
            if(a.next!=null) {
                a.next = null;
                ListNode prev = a;
                ListNode nxt;
                int i=1;
                while (curr != null && i<B) {
                    nxt = curr.next;
                    curr.next = prev;
                    prev = curr;
                    curr = nxt;
                    i++;
                }

                next = curr;
                end.next = prev;
                end = a;
            }
        }

        return newStart;
    }

    public ListNode reverseBetween(ListNode A, int startPos, int C) {

        ListNode start = A;

        ListNode prev2 = null;
        for (int i = 1; i < startPos; i++) {
            if(i == startPos-1){
                prev2 = A;
            }
            A = A.next;
        }
        int len = C-startPos+1;


        if(len==1)
            return start;
        else if(len==0){
            A.next = null;
            return start;
        }

        ListNode a = A;

        ListNode next = null;
        ListNode newStart = null;

        ListNode curr = a.next;
        if(a.next!=null) {
            a.next = null;
            ListNode prev = a;
            ListNode nxt;
            int i=1;
            while (curr != null && i<len) {
                nxt = curr.next;
                curr.next = prev;
                prev = curr;
                curr = nxt;
                ++i;
            }
            next = curr;
            newStart = prev;
        }

        if(prev2!=null)
            prev2.next = newStart;

        a.next = next;

        if(startPos==1)
            return newStart;
        else
            return start;

    }

    public ListNode detectCycle(ListNode a) {

        if(a ==null || a.next == null || a.next.next == null)
            return null;

        ListNode start = a;

        ListNode x = a, y = a;
        while (true){
            x = x.next;
            if(y.next==null)
                return null;
            y = y.next.next;
            if(x==null || y==null){
                return null;
            }
            if(x==y){
                while (true){
                    if(start == y)
                        return start;
                    start = start.next;
                    y = y.next;
                }
            }
        }

    }

    public ListNode partition(ListNode A, int B) {

        ListNode last = null;
        ListNode prev = null;
        ListNode start = null;

        ListNode a = A, a2 = A;
        boolean f = false;
        while (a!=null && a.val>=B){
            prev = a;
            a = a.next;
            f = true;
        }

        while (a!=null){
            if(a.val<B){

                if(last==null){
                    last = a;

                    if(f){
                        prev.next = a.next;
                        last.next = a2;
                        start = last;
                        f = false;
                        a = prev.next;
                    }else {
                        prev = a;
                        a = a.next;
                    }

                }else {
                    if(last.next != a) {
                        ListNode t = a.next;
                        a.next = last.next;
                        last.next = a;

                        prev.next = t;

                        last = a;
                        a = t;
                    }else {
                        prev = a;
                        last = a;
                        a = a.next;
                    }
                }

            }else {
                prev = a;
                a = a.next;
            }
        }

        if(start!=null)
            return start;
        else
            return A;

    }

}
