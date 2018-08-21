import com.sun.deploy.util.ArrayUtil;

import java.util.*;

@SuppressWarnings("Duplicates")
public class ArraysModule {
    public static class Interval {
        int start;
        int end;

        Interval() {
            start = 0;
            end = 0;
        }

        Interval(int s, int e) {
            start = s;
            end = e;
        }
    }

    public static ArrayList<Interval> insert(ArrayList<Interval> intervals, Interval newInterval) {

        int s = newInterval.start;
        int e = newInterval.end;

        if (s > e) {
            int k = s;
            s = e;
            e = k;
        }

        Integer saved = null;
        boolean f1 = false;
        int i = 0;
        for (; i < intervals.size(); i++) {
            if (s <= intervals.get(i).start) {
                saved = intervals.get(i).start;
//                intervals.get(i).start = s;
                f1 = true;
                break;
            } else if (s > intervals.get(i).start && s <= intervals.get(i).end) {
                f1 = true;
                break;
            }
        }


        if (f1) {
            int j = i;
            boolean found = false;
            for (; j < intervals.size(); j++) {

                if (e <= intervals.get(j).end && e >= intervals.get(j).start) {
                    if (saved != null)
                        intervals.get(i).start = s;
                    intervals.get(i).end = intervals.get(j).end;
                    if (i != j)
                        intervals.remove(j);
                    found = true;
                    break;
                } else if (e <= intervals.get(j).end && e <= intervals.get(j).start) {
                    if (saved != null) {
                        intervals.get(i).start = s;
                        if (j == i) {
                            intervals.get(i).start = saved;
                            intervals.add(i, new Interval(s, e));
                        } else {
                            intervals.get(i).end = e;
                        }
                    } else {
                        intervals.get(i).end = e;
                    }
                    found = true;
                    break;
                } else {
                    if (j != i) {
                        intervals.remove(j);
                        --j;
                    }
                }
            }
            if (!found) {
                if (saved != null) {
                    intervals.get(i).start = s;
                    intervals.get(i).end = e;
                } else {
                    intervals.get(i).end = e;
                }
            }
        } else {
            intervals.add(new Interval(s, e));
        }

        return intervals;
    }

    public static ArrayList<Interval> merge(ArrayList<Interval> intervals) {

        //        intervals.sort(new Comparator<Interval>() {
//            @Override
//            public int compare(Interval o1, Interval o2) {
//                return 0;
//            }
//        });
//
//        intervals.sort((o1, o2) -> {
//            //
//            //
//            //
//            return 0;
//        });

//        intervals.sort((o1, o2) -> (o1.end<o2.end));

        intervals.sort((o1, o2) -> {
            if (o1.start < o2.start)
                return -1;
            else if (o1.start == o2.start) {
                if (o1.end < o2.end)
                    return -1;
                if (o1.end > o2.end) {
                    return 1;
                }

//                intervals.remove(o2);
                return 0;
            } else
                return 1;
        });

        for (int i = 1; i < intervals.size(); i++) {

            if (intervals.get(i).start > intervals.get(i - 1).end) {

            } else if (intervals.get(i).start == intervals.get(i - 1).end) {
                intervals.get(i - 1).end = intervals.get(i).end;
                intervals.remove(i);
                --i;
            } else {

                if (intervals.get(i).end <= intervals.get(i - 1).end) {
                    intervals.remove(i);
                    --i;
                } else {
                    intervals.get(i - 1).end = intervals.get(i).end;
                    intervals.remove(i);
                    --i;
                }

            }

        }

        return intervals;
    }

    public static ArrayList<Integer> wave(ArrayList<Integer> A) {
        A.sort((o1, o2) -> {
            if (o1 < o2)
                return -1;
            else if (o1.equals(o2))
                return 0;
            else
                return 1;
        });

        for (int i = 0; i < A.size() - 1; i += 2) {
            int k = A.get(i);
            A.set(i, A.get(i + 1));
            A.set(i + 1, k);
        }
        return A;
    }

    public static int repeatedNumber2(final List<Integer> a) {

        Boolean[] s = new Boolean[a.size()];
        for (int i = 0; i < s.length; i++) {
            s[i] = false;
        }

        for (Integer integer : a) {
            if (!s[integer]) {
                s[integer] = true;
            } else {
                return integer;
            }
        }

        return -1;
    }

    public static ArrayList<Integer> primesum(int A) {
        ArrayList<Integer> l = new ArrayList<>();

        boolean sd[] = new boolean[9782];

        sd[0] = false;
        sd[1] = false;
        for (int i = 3; i < 9782; i++) {
            sd[i] = true;
        }

        for (int i = 2; i < 98; i++) {
            for (int j = 2; i * j < 9781; j++) {
                sd[i * j] = false;
            }
        }

        for (int i = 1; i < 9782; i++) {

            if (sd[i]) {

                int n = A - i;

                if (n == 2 || n == 3) {
                    l.add(i);
                    l.add(A - i);
                    return l;
                }

                // This is checked so that we can skip
                // middle five numbers in below loop
                if (n % 2 == 0 || n % 3 == 0)
                    continue;

                boolean f = true;
                for (int j = 5; j * j <= n; j = j + 6)
                    if (n % j == 0 || n % (j + 2) == 0) {
                        f = false;
                        break;
                    }

                if (f) {
                    l.add(i);
                    l.add(A - i);
                    return l;
                }
            }
        }

        return l;
    }

    public static int repeatedNumber(final List<Integer> a) {
        int x = a.size() / 3;

        HashMap<Integer, Integer> hm = new HashMap<>();

        for (int i = 0; i < a.size(); i++) {
            if (hm.containsKey(a.get(i))) {
                int num = hm.get(a.get(i)) + 1;
                if (num > x) {
                    return a.get(i);
                }
                hm.put(a.get(i), num);
            } else {
                int num = 1;
                hm.put(a.get(i), num);
                if (num > x) {
                    return a.get(i);
                }
            }
        }

        return -1;
    }

    public static int firstMissingPositive(ArrayList<Integer> A) {

        HashMap<Integer, Boolean> hm = new HashMap<>(A.size(), 1);

        for (Integer integer : A) {
            if (integer > 0)
                hm.put(integer, true);
        }

        int i = 1;
        while (true) {
            if (hm.get(i) == null) {
                return i;
            } else {
                i++;
            }
        }
    }

    public static ArrayList<Integer> getRow(int A) {
        ArrayList<Integer> list = new ArrayList<>();
        int x = (A / 2) + 1, p = 1;
        list.add(1);
        for (int i = 1; i < x; i++) {
            p = (p * (A + 1 - i)) / i;
            list.add(p);
        }
        if (A % 2 == 0)
            x--;
        for (int i = x - 1; i >= 0; i--) {
            list.add(list.get(i));
        }
        return list;
    }

    public static ArrayList<ArrayList<Integer>> generate(int A) {
        ArrayList<ArrayList<Integer>> ll = new ArrayList<>();
        for (int Q = 0; Q < A; Q++) {
            ArrayList<Integer> list = new ArrayList<>();
            int x = (Q / 2) + 1, p = 1;
            list.add(1);
            for (int i = 1; i < x; i++) {
                p = (p * (Q + 1 - i)) / i;
                list.add(p);
            }
            if (Q % 2 == 0)
                x--;
            for (int i = x - 1; i >= 0; i--) {
                list.add(list.get(i));
            }
            ll.add(list);
        }
        return ll;
    }

    public static ArrayList<Integer> findPerm(final String A, int B) {
        ArrayList<Integer> list = new ArrayList<>(B);
        int mx = 1;
        list.add(1);
        for (int i = 1; i < B; i++) {
            int num = list.get(i - 1);
            //IIII
            if (A.charAt(i - 1) == 'I') {
                list.add(num + 1);
                mx++;
            } else
                list.add(num);
        }
        //DDDD
        for (int i = B - 2; i >= 0; i--) {
            int num = list.get(i + 1);
            if (A.charAt(i) == 'D') {
                if (num + 1 <= mx) {
                    list.set(i, mx + 1);
                    mx = mx + 1;
                } else {
                    list.set(i, num + 1);
                    if (num + 1 > mx)
                        mx = num + 1;
                }

            }
        }
        return list;
    }

    public static void nextPermutation(ArrayList<Integer> a) {

        int h = -1;
        for (int i = 0; i <= a.size() - 2; i++) {
            if (a.get(i) < a.get(i + 1)) {
                h = i;
            }
        }
        if (h == -1) {
            int x = a.size() / 2;
            for (int i = 0; i < x; i++) {
                int k = a.get(i);
                a.set(i, a.get(a.size() - i - 1));
                a.set(a.size() - i - 1, k);
            }
        } else {
            int h2 = -1;
            for (int i = h + 1; i < a.size(); i++) {
                if (a.get(h) < a.get(i)) {
                    h2 = i;
                }
            }

            int z = a.get(h2);
            a.set(h2, a.get(h));
            a.set(h, z);

            Integer[] integers = a.subList(h + 1, a.size()).toArray(new Integer[a.size() - h - 1]);
            for (int j = 0; j < integers.length; j++) {
                a.set(a.size() - 1 - j, integers[j]);
            }

        }
    }

    @SuppressWarnings("Duplicates")
    public static void rotate(ArrayList<ArrayList<Integer>> a) {
        int n = a.size();
        if (n % 2 == 0) {

            for (int i = n / 2; i < n; i++) {
                for (int j = 0; j < n / 2; j++) {
                    int s = a.get(j).get(i);
                    int x = i, y = j;
                    for (int k = 0; k < 3; k++) {
                        int ty = n - 1 - x, tx = y;
                        int p = a.get(ty).get(tx);
                        a.get(y).set(x, p);
                        y = ty;
                        x = tx;
                    }
                    a.get(y).set(x, s);
                }
            }

        } else {
            for (int i = n / 2 + 1; i < n; i++) {
                for (int j = 0; j < n / 2 + 1; j++) {
                    int s = a.get(j).get(i);
                    int x = i, y = j;
                    for (int k = 0; k < 3; k++) {
                        int ty = n - 1 - x, tx = y;
                        int p = a.get(ty).get(tx);
                        a.get(y).set(x, p);
                        y = ty;
                        x = tx;
                    }
                    a.get(y).set(x, s);
                }
            }
        }
    }

    public static ArrayList<Integer> flip3(String A) {

        //11001001

        ArrayList<Integer> st = new ArrayList<>();
        ArrayList<Integer> sz = new ArrayList<>();
        boolean in = false;
        for (int i = 0; i < A.length(); i++) {
            if (A.charAt(i) == '0') {
                if (!in) {
                    st.add(i);
                    sz.add(1);
                    in = true;
                } else {
                    sz.set(sz.size() - 1, sz.get(sz.size() - 1) + 1);
                }
            } else {
                if (in) {
                    in = false;
                }
            }
        }

        if (sz.size() == 0) {
            return new ArrayList<>();
        }

        Integer p = st.get(0), q = st.get(0) + sz.get(0) - 1;
        int mx = sz.get(0), l = sz.get(0);

        boolean reset = false;
        Integer saved = null;
        for (int i = 1; i < st.size(); i++) {

            int both = l + sz.get(i) - (st.get(i) - (st.get(i - 1) + sz.get(i - 1)));
            if (both >= sz.get(i)) {
                l = both;
                if (both > mx) {
                    if (reset) {
                        p = saved;
                        reset = false;
                    }
                    q = st.get(i) + sz.get(i) - 1;
                    mx = both;
                }
            } else {
                l = sz.get(i);
                reset = true;
                saved = st.get(i);
                if (sz.get(i) > mx) {
                    mx = sz.get(i);
                    p = st.get(i);
                    q = st.get(i) + sz.get(i) - 1;
                }
            }
        }

        ArrayList<Integer> ss = new ArrayList<>();
        if (p != -1 && q != -1) {
            ss.add(p + 1);
            ss.add(q + 1);
        }
        return ss;
    }

    public static ArrayList<Integer> flip2(String A) {

        //11001001

        int arr[] = new int[A.length()];
        for (int i = 0; i < A.length(); i++) {
            if (A.charAt(i) == '0') {
                arr[i] = 1;
            } else
                arr[i] = -1;
        }

        Integer p = -1, q = -1;

        int msf = 0, h = 0;
        for (int i = 0; i < arr.length; i++) {

            h = h + arr[i];

            if (h < 0) {
                h = 0;
            }

            if (msf < h) {
                msf = h;
                if (p != -1) {
                    q = i;
                } else {
                    p = i;
                    q = i;
                }
            } else if (msf > h) {

            }
        }

        ArrayList<Integer> ss = new ArrayList<>();
        if (p != -1 && q != -1) {
            ss.add(p + 1);
            ss.add(q + 1);
        }
        return ss;
    }

    public static ArrayList<Integer> flip(String A) {

        int arr[] = new int[A.length()];
        for (int i = 0; i < A.length(); i++) {
            if (A.charAt(i) == '0') {
                arr[i] = 1;
            } else
                arr[i] = -1;
        }


        int last = 0;
        Integer p = -1, q = -1;
        int max = 0;
        for (int i = 0; i < A.length(); i++) {
            last = 0;
            for (int j = i; j < A.length(); j++) {
                last += arr[j];
                if (last > max) {
                    p = i;
                    q = j;
                    max = last;
                }
            }
        }


        ArrayList<Integer> ss = new ArrayList<>();
        if (p != -1 && q != -1) {
            ss.add(p + 1);
            ss.add(q + 1);
        }
        return ss;
    }

    public static int maximumGap(final List<Integer> A) {

        int n = A.size();
        if (n < 2) {
            return 0;
        }

        int max = Integer.MIN_VALUE;
        int min = Integer.MAX_VALUE;

        for (int i = 0; i < n; i++) {
            max = Math.max(max, A.get(i));
            min = Math.min(min, A.get(i));
        }

        //n-1 buckets -  we only care about max and min in each buckets
        int[] bucketMaxima = new int[n - 1];
        Arrays.fill(bucketMaxima, Integer.MIN_VALUE);
        int[] bucketMinima = new int[n - 1];
        Arrays.fill(bucketMinima, Integer.MAX_VALUE);

        //bucket width
        float delta = (float) (max - min) / ((float) n - 1);

        //populate the bucket maxima and minima
        for (int i = 0; i < n; i++) {
            if (A.get(i) == max || A.get(i) == min) {
                continue;
            }

            int bucketIndex = (int) Math.floor((A.get(i) - min) / delta);
            bucketMaxima[bucketIndex] = bucketMaxima[bucketIndex] == Integer.MIN_VALUE ? A.get(i) : Math.max(bucketMaxima[bucketIndex], A.get(i));
            bucketMinima[bucketIndex] = bucketMinima[bucketIndex] == Integer.MAX_VALUE ? A.get(i) : Math.min(bucketMinima[bucketIndex], A.get(i));
        }

        //find the maxgap - maxgaps
        int prev = min;
        int maxGap = 0;
        for (int i = 0; i < n - 1; i++) {

            //empty bucket according to Pigeonhole principle
            if (bucketMinima[i] == Integer.MAX_VALUE) {
                continue;
            }

            maxGap = Math.max(maxGap, bucketMinima[i] - prev);
            prev = bucketMaxima[i];
        }

        maxGap = Math.max(maxGap, max - prev);

        return maxGap;
    }

    public static int solve(ArrayList<Integer> A) {
        A.sort((o1, o2) -> {
            if(o1>o2)
                return 1;
            else if(o1<o2)
                return -1;
            else
                return 0;
        });
        int size = A.size();
        for (int i = 0; i < size; i++) {
            if(i<size-1 && A.get(i).equals(A.get(i + 1))){
                continue;
            }
            if(A.get(i) == size-i-1){
                return 1;
            }
        }
        return -1;
    }

    @SuppressWarnings("Duplicates")
    public static ArrayList<ArrayList<Integer>> generateMatrix(int A) {
        ArrayList<ArrayList<Integer>> list = new ArrayList<>(A);
        for (int i = 0; i < A; i++) {
            ArrayList<Integer> l = new ArrayList<>();
            for (int j = 0; j < A; j++) {
                l.add(0);
            }
            list.add(l);
        }

        ArrayList<ArrayList<Boolean>> flag = new ArrayList<>(A);
        for (int i = 0; i < A; i++) {
            ArrayList<Boolean> l = new ArrayList<>();
            for (int j = 0; j < A; j++) {
                l.add(false);
            }
            flag.add(l);
        }

        int k=1;
        int x=0, y=0;
        while(k<=A*A){

            while (x<A && k<=A*A){
                if(flag.get(y).get(x)) {
                    break;
                }
                else {
                    list.get(y).set(x, k);
                    k++;
                    flag.get(y).set(x,true);
                    x++;
                }
            }
            x--;
            y++;

            while (y<A && k<=A*A){
                if(flag.get(y).get(x)) {
                    break;
                }
                else {
                    list.get(y).set(x, k);
                    k++;
                    flag.get(y).set(x,true);
                    y++;
                }
            }
            y--;
            x--;

            while (x>=0 && k<=A*A){
                if(flag.get(y).get(x)) {
                    break;
                }
                else {
                    list.get(y).set(x, k);
                    k++;
                    flag.get(y).set(x,true);
                    x--;
                }
            }
            x++;
            y--;

            while (y>=0 && k<=A*A){
                if(flag.get(y).get(x)) {
                    break;
                }
                else {
                    list.get(y).set(x, k);
                    k++;
                    flag.get(y).set(x,true);
                    y--;
                }
            }
            y++;
            x++;
        }

        return list;
    }

    //largest-number - tle
    public String largestNumber(final List<Integer> A) {
        A.sort(new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                String s1 = o1.toString();
                String s2 = o2.toString();
                int i=0;
                while (i<s1.length() && i<s2.length()){
                    int c1 = s1.charAt(i) - '0';
                    int c2 = s2.charAt(i) - '0';

                    if(c1>c2){
                        return -1;
                    }else if(c1<c2){
                        return 1;
                    }

                    i++;
                }

                String s12 = s1+s2;
                String s21 = s2+s1;

                i=0;
                while (i<s12.length() && i<s21.length()){
                    int c1 = s12.charAt(i) - '0';
                    int c2 = s21.charAt(i) - '0';

                    if(c1>c2){
                        return -1;
                    }else if(c1<c2){
                        return 1;
                    }

                    i++;
                }

                return 0;
            }
        });

        if(A.get(0)==0){
            return "0";
        }

        String s = "";
        for (Integer integer : A) {
            s+=integer;
        }

        return s;
    }
}
