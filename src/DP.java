import java.util.*;

@SuppressWarnings("Duplicates")
public class DP {

    public static int longestConsecutiveSubsequenceLength(final List<Integer> A) {

        int t = -9; //dec
        if (A.size() <= 1)
            return A.size();


        int curr = 0;
        int last = 0;
        int max = 1;

        for (int i = 1; i < A.size(); i++) {
            if (A.get(i) > A.get(i - 1)) {
                if (t == 1) {
                    curr++;
                    if (curr + last > max)
                        max = curr + last;
                } else {
                    if (t == -9) {
                        curr = 2;
                        last = 0;
                        max = 2;
                        t = 1;
                    } else {
                        last = curr;
                        if (t == 0)
                            curr = 2;
                        else
                            curr = 1;
                        t = 1;
                        if (curr + last > max)
                            max = curr + last;
                    }
                }
            } else if (A.get(i) < A.get(i - 1)) {
                if (t == -1) {
                    curr++;
                    if (curr + last > max)
                        max = curr + last;
                } else {
                    if (t == -9) {
                        curr = 2;
                        last = 0;
                        max = 2;
                        t = -1;
                    } else {
                        last = curr;
                        if (t == 0)
                            curr = 2;
                        else
                            curr = 1;
                        t = -1;
                        if (curr + last > max)
                            max = curr + last;
                    }
                }
            } else {
                if (t == 0) {
                    curr = 1;
                    last = 1;
                    t = 0;
                } else {
                    if (t == -9) {
                        curr = 1;
                        last = 0;
                        t = 0;
                    } else {
                        last = curr;
                        curr = 1;
                        t = 0;
                        if (curr + last > max)
                            max = curr + last;
                    }
                }
            }
        }

        return max;

    }

    public static int longestSubsequenceLength(final List<Integer> A) {

        ArrayList<Integer> l1 = new ArrayList<>(A.size());

        int max1 = 1;
        if (A.size() == 0 || A.size() == 1)
            return A.size();

        HashSet<Integer> set1 = new HashSet<>();

        for (int i = 0; i < A.size(); i++) {
            int j = i - 1;
            int m = -1, n = -1;

            while (j >= 0) {
                if (A.get(i) > A.get(j)) {
                    if (l1.get(j) > m) {
                        n = j;
                        m = l1.get(j);
                        if (set1.contains(j)) {
                            break;
                        }
                    }
                }
                j--;
            }

            if (m == -1) {
                l1.add(1);
            } else {
                int i1 = 1 + m;
                if (i1 > max1) {
                    max1 = i1;
                    set1.add(n);
                }
                l1.add(i1);
            }
        }


        ArrayList<Integer> ll = new ArrayList<>(A.size());

        int max = 1;
        if (A.size() == 0 || A.size() == 1)
            return A.size();

        HashSet<Integer> set = new HashSet<>();

        for (int i = A.size() - 1; i >= 0; i--) {
            int j = i + 1;
            int m = -1, n = -1;
            while (j < A.size()) {
                if (A.get(i) > A.get(j)) {
                    if (ll.get(A.size()-j-1) > m) {
                        n = j;
                        m = ll.get(A.size()-j-1);
                        if (set.contains(j)) {
                            break;
                        }
                    }
                }
                j++;
            }
            if (m == -1) {
                ll.add(1);
            } else {
                int i1 = 1 + m;
                if (i1 > max) {
                    max = i1;
                    set.add(n);
                }
                ll.add(i1);
            }
        }

        int a = 0;
        for (int i = 0; i < A.size(); i++) {
            int k = l1.get(i)+ll.get(A.size()-i-1)-1;
            if(k>a){
                a = k;
            }
        }

        return a;
    }

    private int minof3(int x,int y,int z) {
        if (x <= y && x <= z) return x;
        if (y <= x && y <= z) return y;
        else return z;
    }

    public int minDistance(String str1, String str2) {
        int m = str1.length();
        int n = str2.length();
        // Create a table to store results of subproblems
        int dp[][] = new int[m+1][n+1];

        // Fill d[][] in bottom up manner
        for (int i=0; i<=m; i++)
        {
            for (int j=0; j<=n; j++)
            {
                // If first string is empty, only option is to
                // isnert all characters of second string
                if (i==0)
                    dp[i][j] = j;  // Min. operations = j

                    // If second string is empty, only option is to
                    // remove all characters of second string
                else if (j==0)
                    dp[i][j] = i; // Min. operations = i

                    // If last characters are same, ignore last char
                    // and recur for remaining string
                else if (str1.charAt(i-1) == str2.charAt(j-1))
                    dp[i][j] = dp[i-1][j-1];

                    // If the last character is different, consider all
                    // possibilities and find the minimum
                else
                    dp[i][j] = 1 + minof3(dp[i][j-1],  // Insert
                            dp[i-1][j],  // Remove
                            dp[i-1][j-1]); // Replace
            }
        }

        return dp[m][n];
    }

    public int minDistance2(String A, String B) {

        int diff = B.length()- A.length();

        HashMap<Character, ArrayList<Integer>> map = new HashMap<>();
        for (int i = 0; i < A.length(); i++) {
            char c = A.charAt(i);
            if(map.containsKey(c)){
                ArrayList<Integer> list1 = map.get(c);
                list1.add(i);
                map.put(c, list1);
            }else
                map.put(c, new ArrayList<>(Collections.singletonList(i)));
        }

        ArrayList<Integer> list = new ArrayList<>(B.length());
        int j=0;
        for (int i = 0; i < B.length(); i++) {
            char c = B.charAt(i);
            ArrayList<Integer> list1 = map.get(c);
            if(list1!=null){

            }
        }

        return diff;
    }

    public int lis(final List<Integer> A) {
        ArrayList<Integer> l1 = new ArrayList<>(A.size());

        int max1 = 1;
        if (A.size() == 0 || A.size() == 1)
            return A.size();

        HashSet<Integer> set1 = new HashSet<>();

        for (int i = 0; i < A.size(); i++) {
            int j = i - 1;
            int m = -1, n = -1;

            while (j >= 0) {
                if (A.get(i) > A.get(j)) {
                    if (l1.get(j) > m) {
                        n = j;
                        m = l1.get(j);
                        if (set1.contains(j)) {
                            break;
                        }
                    }
                }
                j--;
            }

            if (m == -1) {
                l1.add(1);
            } else {
                int i1 = 1 + m;
                if (i1 > max1) {
                    max1 = i1;
                    set1.add(n);
                }
                l1.add(i1);
            }
        }
        return max1;
    }

    public ArrayList<ArrayList<Integer>> queenAttackInefficient(ArrayList<String> A) {

        ArrayList<ArrayList<Integer>> list = new ArrayList<>();
        int m = A.size();

        if(m ==0)
            return list;

        int n = A.get(0).length();
        ArrayList<Integer> line = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            line.add(0);
        }

        for (int i = 0; i < m; i++) {
            list.add(new ArrayList<>(line));
        }

        for (int i = 0; i < m; i++) {
            String s = A.get(i);
            for (int j = 0; j < s.length(); j++) {
                if(s.charAt(j)=='1'){

                    int p = i+1;
                    int q = j;
                    while (p<m){
                        list.get(p).set(q,list.get(p).get(q)+1);
                        if(A.get(p).charAt(q)=='1')
                            break;
                        p++;
                    }

                    p = i-1;
                    q = j;
                    while (p>=0){
                        list.get(p).set(q,list.get(p).get(q)+1);
                        if(A.get(p).charAt(q)=='1')
                            break;
                        p--;
                    }

                    p = i;
                    q = j-1;
                    while (q>=0){
                        list.get(p).set(q,list.get(p).get(q)+1);
                        if(A.get(p).charAt(q)=='1')
                            break;
                        q--;
                    }

                    p = i;
                    q = j+1;
                    while (q<n){
                        list.get(p).set(q,list.get(p).get(q)+1);
                        if(A.get(p).charAt(q)=='1')
                            break;
                        q++;
                    }


                    p = i-1;
                    q = j-1;
                    while (q>=0 && p>=0){
                        list.get(p).set(q,list.get(p).get(q)+1);
                        if(A.get(p).charAt(q)=='1')
                            break;
                        q--;
                        p--;
                    }

                    p = i+1;
                    q = j+1;
                    while (q<n && p<m){
                        list.get(p).set(q,list.get(p).get(q)+1);
                        if(A.get(p).charAt(q)=='1')
                            break;
                        q++;
                        p++;
                    }

                    p = i+1;
                    q = j-1;
                    while (q>=0 && p<m){
                        list.get(p).set(q,list.get(p).get(q)+1);
                        if(A.get(p).charAt(q)=='1')
                            break;
                        q--;
                        p++;
                    }

                    p = i-1;
                    q = j+1;
                    while (q<n && p>=0){
                        list.get(p).set(q,list.get(p).get(q)+1);
                        if(A.get(p).charAt(q)=='1')
                            break;
                        q++;
                        p--;
                    }

                }
            }
        }

        return list;
    }

    boolean[][] q;
    int[][][] a;
    int m, n;
    private int getAttacks(int i, int j, int k){
        if(a[i][j][k]!=-1)
            return a[i][j][k];

//                0 1 2
//                3   4
//                5 6 7

        switch (k){
            case 0:
                if(i>0 && j>0){
                    if(q[i-1][j-1]) {
                        a[i][j][k] = 1;
                    }
                    else {
                        a[i][j][k] = getAttacks(i-1, j-1, k);
                    }

                }else {
                    a[i][j][k] = 0;
                }
                break;
            case 1:
                if(i>0){
                    if(q[i-1][j]) {
                        a[i][j][k] = 1;
                    }
                    else {
                        a[i][j][k] = getAttacks(i-1, j, k);
                    }

                }else {
                    a[i][j][k] = 0;
                }
                break;
            case 2:
                if(i>0 && j<n-1){
                    if(q[i-1][j+1]) {
                        a[i][j][k] = 1;
                    }
                    else {
                        a[i][j][k] = getAttacks(i-1, j+1, k);
                    }

                }else {
                    a[i][j][k] = 0;
                }
                break;
            case 3:
                if(j>0){
                    if(q[i][j-1]) {
                        a[i][j][k] = 1;
                    }
                    else {
                        a[i][j][k] = getAttacks(i, j-1, k);
                    }

                }else {
                    a[i][j][k] = 0;
                }
                break;
            case 4:
                if(j<n-1){
                    if(q[i][j+1]) {
                        a[i][j][k] = 1;
                    }
                    else {
                        a[i][j][k] = getAttacks(i, j+1, k);
                    }

                }else {
                    a[i][j][k] = 0;
                }
                break;
            case 5:
                if(i<m-1 && j>0){
                    if(q[i+1][j-1]) {
                        a[i][j][k] = 1;
                    }
                    else {
                        a[i][j][k] = getAttacks(i+1, j-1, k);
                    }

                }else {
                    a[i][j][k] = 0;
                }
                break;
            case 6:
                if(i<m-1){
                    if(q[i+1][j]) {
                        a[i][j][k] = 1;
                    }
                    else {
                        a[i][j][k] = getAttacks(i+1, j, k);
                    }

                }else {
                    a[i][j][k] = 0;
                }
                break;
            case 7:
                if(i<m-1 && j<n-1){
                    if(q[i+1][j+1]) {
                        a[i][j][k] = 1;
                    }
                    else {
                        a[i][j][k] = getAttacks(i+1, j+1, k);
                    }

                }else {
                    a[i][j][k] = 0;
                }
                break;
        }
        return a[i][j][k];
    }

    public int[][] queenAttack(String[] A) {

        m = A.length;
        if(m ==0)
            return null;

        n = A[0].length();

        int list[][] = new int[m][n];

        a = new int[m][n][8];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                for (int k = 0; k < 8; k++) {
                    a[i][j][k] = -1;
                }
            }
        }

        q = new boolean[m][n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if(A[i].charAt(j)=='1')
                    q[i][j] = true;
            }
        }

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                for (int k = 0; k < 8; k++) {
                    list[i][j]+=getAttacks(i, j, k);
                }
            }
        }


        return list;

    }

    public int anytwo(String A) {
        int

    }

}
