package DP;

import javafx.util.Pair;

import java.util.*;

@SuppressWarnings({"Duplicates", "unused"})
public class GFG {

    //    longest-increasing-subsequence
    public static void lis() {

        Scanner sc = new Scanner(System.in);
        int t = sc.nextInt();

        while (t-- > 0) {

            int n = sc.nextInt();
            int[] ar = new int[n];
            for (int i = 0; i < n; i++) {
                ar[i] = sc.nextInt();
            }

            if (n < 2) {
                System.out.println(n);
                continue;
            }

            int[] s = new int[n];
            s[0] = 1;

            int max = 1;

            for (int i = 1; i < n; i++) {
                int m = 0;
                for (int j = i - 1; j >= 0; j--) {
                    if (ar[j] < ar[i]) {
                        if (s[j] > m) {
                            m = s[j];
                        }
                    }
                }
                s[i] = m + 1;
                if (s[i] > max) {
                    max = s[i];
                }
            }

            System.out.println(max);

        }
    }

    //    Longest Common Subsequence
    public static void lcs() {
        Scanner sc = new Scanner(System.in);
        int t = sc.nextInt();
        while (t-- > 0) {

            int m = sc.nextInt();
            int n = sc.nextInt();

            String s1 = sc.next();
            String s2 = sc.next();

            int[][] arr = new int[m + 1][n + 1];

            for (int i = 0; i <= m; i++) {
                for (int j = 0; j <= n; j++) {
                    if (i == 0 || j == 0) {
                        arr[i][j] = 0;
                    } else {
                        if (s1.charAt(i - 1) == s2.charAt(j - 1)) {
                            arr[i][j] = 1 + arr[i - 1][j - 1];
                        } else {
                            arr[i][j] = Math.max(arr[i - 1][j], arr[i][j - 1]);
                        }
                    }
                }
            }
            System.out.println(arr[m][n]);

        }
    }

    //    Edit Distance (memoization) 0.15s
    private static int[][] arr;
    private static String s1, s2;

    public static void editDistance() {
        Scanner sc = new Scanner(System.in);
        int t = sc.nextInt();
        while (t-- > 0) {

            int m = sc.nextInt();
            int n = sc.nextInt();

            s1 = sc.next();
            s2 = sc.next();

            arr = new int[m + 1][n + 1];
            for (int i = 0; i < m; i++) {
                for (int j = 0; j < n; j++) {
                    arr[i][j] = -1;
                }
            }
            System.out.println(f(0, 0));
        }
    }

    static int f(int m, int n) {
        if (arr[m][n] == -1) {
            if (s1.charAt(m) == s2.charAt(n)) {
                if (m == s1.length() - 1) {
                    arr[m][n] = (s2.length() - n - 1);
                    return arr[m][n];
                }
                if (n == s2.length() - 1) {
                    arr[m][n] = (s1.length() - m - 1);
                    return arr[m][n];
                }
                arr[m][n] = f(m + 1, n + 1);
                return arr[m][n];
            } else {
                int x = Integer.MAX_VALUE;
                int y = Integer.MAX_VALUE;
                int z = Integer.MAX_VALUE;
                if (m == s1.length() - 1 && n == s2.length() - 1) {
                    arr[m][n] = 1;
                    return arr[m][n];
                }
                if (m < s1.length() - 1) {
                    x = f(m + 1, n);
                }
                if (n < s2.length() - 1) {
                    y = f(m, n + 1);
                }
                if (m < s1.length() - 1 && n < s2.length() - 1) {
                    z = f(m + 1, n + 1);
                }
                arr[m][n] = 1 + minof3(x, y, z);
                return arr[m][n];
            }
        } else {
            return arr[m][n];
        }
    }

    private static int minof3(int x, int y, int z) {
        if (x <= y && x <= z) return x;
        if (y <= x && y <= z) return y;
        else return z;
    }

    //    Edit Distance (tabular) 0.09s
    public static void editDistance2() {

        Scanner sc = new Scanner(System.in);
        int t = sc.nextInt();
        while (t-- > 0) {

            int m = sc.nextInt();
            int n = sc.nextInt();

            s1 = sc.next();
            s2 = sc.next();

            if (m == 0) {
                System.out.println(n);
                continue;
            }
            if (n == 0) {
                System.out.println(m);
                continue;
            }

            arr = new int[m + 1][n + 1];

            for (int i = 0; i <= m; i++) {
                for (int j = 0; j <= n; j++) {
                    if (i == 0) {
                        arr[i][j] = j;
                        continue;
                    }
                    if (j == 0) {
                        arr[i][j] = i;
                        continue;
                    }
                    if (s1.charAt(i - 1) == s2.charAt(j - 1)) {
                        arr[i][j] = arr[i - 1][j - 1];
                    } else {
                        arr[i][j] = 1 + minof3(arr[i][j - 1], arr[i - 1][j], arr[i - 1][j - 1]);
                    }
                }
            }
            System.out.println(arr[m][n]);
        }
    }

    //    Path in Matrix
    public static void maxCostPath() {
        Scanner sc = new Scanner(System.in);
        int t = sc.nextInt();
        while (t-- > 0) {
            int n = sc.nextInt();

            int[][] arr = new int[n][n];

            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    arr[i][j] = sc.nextInt();
                }
            }

            for (int i = 1; i < n; i++) {
                for (int j = 0; j < n; j++) {

                    if (j == 0) {
                        arr[i][j] += Math.max(arr[i - 1][j], arr[i - 1][j + 1]);
                        continue;
                    }
                    if (j == n - 1) {
                        arr[i][j] += Math.max(arr[i - 1][j], arr[i - 1][j - 1]);
                        continue;
                    }

                    arr[i][j] += maxof3(arr[i - 1][j - 1], arr[i - 1][j], arr[i - 1][j + 1]);
                }
            }

            int max = arr[n - 1][0];
            for (int j = 1; j < n; j++) {
                if (arr[n - 1][j] > max)
                    max = arr[n - 1][j];
            }

            System.out.println(max);
        }
    }

    private static int maxof3(int x, int y, int z) {
        if (x >= y && x >= z) return x;
        if (y >= x && y >= z) return y;
        else return z;
    }

    //    Coin Change
    public static void coinChange() {
        Scanner sc = new Scanner(System.in);
        int t = sc.nextInt();
        while (t-- > 0) {
            int m = sc.nextInt();
            ArrayList<Integer> coins = new ArrayList<>(m);
            for (int i = 0; i < m; i++) {
                coins.add(sc.nextInt());
            }
            int n = sc.nextInt();
            Collections.sort(coins);

            int[] arr = new int[n + 1];
            arr[0] = 1;

            for (int c : coins) {
                for (int i = c; i <= n; i++) {
                    arr[i] += arr[i - c];
                }
            }

            System.out.println(arr[n]);
        }
    }

    //    matrix-chain-multiplication (memoization) 0.468sec
    private static int[] mat;
    private static HashMap<Pair<Integer, Integer>, Integer> map;

    public static void matrixMul() {

        Scanner sc = new Scanner(System.in);
        int t = sc.nextInt();
        while (t-- > 0) {
            int n = sc.nextInt();
            mat = new int[n];

            for (int i = 0; i < n; i++) {
                mat[i] = sc.nextInt();
            }

            map = new HashMap<>();

            System.out.println(ff(0, n - 1));
        }
    }

    private static Integer ff(int s, int e) {
        Pair<Integer, Integer> key = new Pair<>(s, e);
        if (map.containsKey(key)) {
            return map.get(key);
        } else {

            if (e - s + 1 == 2) {
                return 0;
            }

            if (e - s + 1 == 3) {
                int z = mat[s] * mat[s + 1] * mat[s + 2];
                map.put(key, z);
                return z;
            }

            int min = Integer.MAX_VALUE;

            for (int i = s + 1; i < e; i++) {

                int z = 0;

                z += ff(s, i);

                z += ff(i, e);

                z += mat[s] * mat[i] * mat[e];

                if (z < min)
                    min = z;

            }

            map.put(key, min);

            return min;
        }
    }

    //    matrix-chain-multiplication (tabulation) 0.28s
//    private static int[] mat;
    private static int[][] dp;

    public static void matrixMul2() {

        Scanner sc = new Scanner(System.in);
        int t = sc.nextInt();
        while (t-- > 0) {
            int n = sc.nextInt();
            mat = new int[n];
            for (int i = 0; i < n; i++) {
                mat[i] = sc.nextInt();
            }

            if (n == 2) {
                System.out.println(0);
                continue;
            }
            if (n == 3) {
                int z = mat[0] * mat[1] * mat[2];
                System.out.println(z);
                continue;
            }

            dp = new int[n][n];

            for (int len = 2; len < n; len++) {
                for (int j = 0; j + len < n; j++) {
                    ff2(j, j + len);
                }
            }

            System.out.println(dp[0][n - 1]);
        }

    }

    private static void ff2(int s, int e) {

        if (dp[s][e] == 0) {

            if (e - s + 1 == 2) {
                return;
            }

            if (e - s + 1 == 3) {
                int z = mat[s] * mat[s + 1] * mat[s + 2];
                dp[s][e] = z;
                return;
            }

            int min = Integer.MAX_VALUE;

            for (int i = s + 1; i < e; i++) {

                int z = 0;

                z += dp[s][i];

                z += dp[i][e];

                z += mat[s] * mat[i] * mat[e];

                if (z < min)
                    min = z;

            }

            dp[s][e] = min;

        }

    }

    //    nCr (tabulation) 0.19s
    public static void nCr() {
        Scanner sc = new Scanner(System.in);
        int t = sc.nextInt();
        while (t-- > 0) {
            int n = sc.nextInt();
            int r = sc.nextInt();
            if (r > n || r < 0) {
                System.out.println(0);
                continue;
            }
            int[][] arr = new int[n + 1][n + 1];
            arr[0][0] = 1;
            for (int i = 1; i <= n; i++) {
                for (int j = 0; j <= i; j++) {
                    if (j == 0 || j == i) {
                        arr[i][j] = 1;
                        continue;
                    }
                    arr[i][j] = ((arr[i - 1][j] % 1000000007) + (arr[i - 1][j - 1] % 1000000007)) % 1000000007;
                    if (i == n && j == r) {
                        System.out.println(arr[i][j]);
                        break;
                    }
                }
            }
        }
    }

    public static void knapsack01() {

        Scanner sc = new Scanner(System.in);
        int t = sc.nextInt();
        while (t-- > 0) {
            int n = sc.nextInt();
            int w = sc.nextInt();

            ArrayList<Integer> v = new ArrayList<>(n);
            ArrayList<Pair<Integer, Integer>> list = new ArrayList<>(n);

            for (int i = 0; i < n; i++) {
                v.add(sc.nextInt());
            }
            for (int i = 0; i < n; i++) {
                list.add(new Pair<>(sc.nextInt(), v.get(i)));
            }

            list.sort((o1, o2) -> {
                int k = Integer.compare(o1.getKey(), o2.getKey());
                if (k == 0) {
                    return (-1 * Integer.compare(o1.getValue(), o2.getValue()));
                } else
                    return k;
            });

            if (list.get(0).getKey() > w) {
                System.out.println(0);
                continue;
            }
            for (int i = 1; i < list.size(); i++) {
                if (list.get(i).getKey() > w) {
                    while (i < list.size()) {
                        list.remove(i);
                    }
                    break;
                }
            }

            ArrayList<Pair<Integer, HashSet<Integer>>> dp = new ArrayList<>();
            dp.add(new Pair<>(0, new HashSet<>()));

            for (int i = 1; i <= w; i++) {
                Pair<Integer, HashSet<Integer>> bef = dp.get(i - 1);

                Pair<Integer, HashSet<Integer>> toAdd = new Pair<>(bef.getKey(), bef.getValue());

                int max = bef.getKey();
                for (int j = 0; j < list.size(); j++) {
                    Pair<Integer, Integer> item = list.get(j);
                    Integer ww = item.getKey();
                    if (ww <= i) {
                        Pair<Integer, HashSet<Integer>> ll = dp.get(i - ww);
                        if (ll.getValue().contains(j)) {

                        } else {
                            int i1 = ll.getKey() + item.getValue();
                            if (i1 > max) {
                                HashSet<Integer> value = ll.getValue();
                                HashSet<Integer> ss = new HashSet<>(value);
                                ss.add(j);
                                toAdd = new Pair<>(i1, ss);
                            }
                        }
                    } else {
                        break;
                    }
                }

                dp.add(toAdd);

            }

            System.out.println(dp.get(w).getKey());
        }

    }

    public static void eggDropping() {

        Scanner sc = new Scanner(System.in);
        int t = sc.nextInt();
        while (t-- > 0) {
            int n = sc.nextInt();
            int k = sc.nextInt();


        }

    }

    //    longest-palindromic-subsequence (tabulation) 0.32s
    public static void lps() {

        Scanner sc = new Scanner(System.in);
        int t = sc.nextInt();
        while (t-- > 0) {
            String s = sc.next();
            int l = s.length();
            int[][] arr = new int[l][l];

            for (int i = 0; i < l; i++) {
                arr[i][i] = 1;
            }
            for (int i = 1; i < l; i++) {
                for (int x = 0; x+i < l; x++) {
                    int m = Integer.MIN_VALUE;

                    int y = x+i;

                    if(s.charAt(x)==s.charAt(y)){
                        if(i==1){
                            m=2;
                        }else {
                            m= 2+arr[x +1][y-1];
                        }
                    }

                    int mm = Math.max(arr[x][y-1], arr[x +1][y]);
                    if(mm>m){
                        m = mm;
                    }
                    arr[x][y] = m;
                }
            }

            System.out.println(arr[0][l-1]);

        }

    }

    //    number-that-are-not-divisible
    public static void numNotDiv() {

        Scanner sc = new Scanner(System.in);
        int t = sc.nextInt();
        while (t-- > 0) {

            long x = sc.nextLong();
            long a = x;

            x -= a/2;
            x -= a/3;
            x -= a/5;
            x -= a/7;

            x += Math.floorDiv(a, 2*3);
            x += Math.floorDiv(a, 3*5);
            x += Math.floorDiv(a, 5*7);
            x += Math.floorDiv(a, 2*5);
            x += Math.floorDiv(a, 2*7);
            x += Math.floorDiv(a, 3*7);

            x -= Math.floorDiv(a, 2*3*5);
            x -= Math.floorDiv(a, 2*3*7);
            x -= Math.floorDiv(a, 2*5*7);
            x -= Math.floorDiv(a, 7*3*5);

            x += Math.floorDiv(a, 2*3*5*7);

            System.out.println(x);

        }
    }


}
