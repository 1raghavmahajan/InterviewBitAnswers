package DP;

import javafx.util.Pair;
import java.util.*;

@SuppressWarnings("Duplicates")
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
            }else {
                int x = Integer.MAX_VALUE;
                int y = Integer.MAX_VALUE;
                int z = Integer.MAX_VALUE;
                if(m==s1.length()-1 && n == s2.length()-1){
                    arr[m][n] = 1;
                    return arr[m][n];
                }
                if(m<s1.length()-1){
                    x = f(m+1, n);
                }
                if(n<s2.length()-1){
                    y = f(m, n+1);
                }
                if(m<s1.length()-1 && n<s2.length()-1){
                    z = f(m+1, n+1);
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

            if(m==0) {
                System.out.println(n);
                continue;
            }
            if(n==0) {
                System.out.println(m);
                continue;
            }

            arr = new int[m + 1][n + 1];

            for (int i = 0; i <= m; i++) {
                for (int j = 0; j <= n; j++) {
                    if(i==0) {
                        arr[i][j] = j;
                        continue;
                    }
                    if(j==0) {
                        arr[i][j] = i;
                        continue;
                    }
                    if(s1.charAt(i-1)==s2.charAt(j-1)){
                        arr[i][j] = arr[i-1][j-1];
                    }else {
                        arr[i][j] = 1 + minof3(arr[i][j-1], arr[i-1][j], arr[i-1][j-1]);
                    }
                }
            }
            System.out.println(arr[m][n]);
        }
    }

    //    Path in Matrix
    public static void maxCostPath(){
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

                    if(j==0) {
                        arr[i][j] += Math.max(arr[i-1][j], arr[i-1][j+1]);
                        continue;
                    }
                    if(j==n-1) {
                        arr[i][j] += Math.max(arr[i-1][j], arr[i-1][j-1]);
                        continue;
                    }

                    arr[i][j] += maxof3(arr[i-1][j-1], arr[i-1][j], arr[i-1][j+1]);
                }
            }

            int max = arr[n-1][0];
            for (int j = 1; j < n; j++) {
                if(arr[n-1][j]>max)
                    max = arr[n-1][j];
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
    public static void coinChange(){
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

            int[] arr = new int[n+1];
            arr[0] = 1;

            for (int c : coins) {
                for (int i = c; i <= n; i++) {
                    arr[i] += arr[i-c];
                }
            }

            System.out.println(arr[n]);
        }
    }

    //    matrix-chain-multiplication
    private static int[] mat;
    private static HashMap<Pair<Integer, Integer>,Integer> map;
    public static void matrixMul(){
        Random random = new Random(Calendar.getInstance().getTime().getTime());

        Scanner sc = new Scanner(System.in);
        int t = sc.nextInt();
        while (t-- > 0) {
            int n = sc.nextInt();
//            int n = random.nextInt(10)+3;
//            System.out.println(n);
            mat = new int[n];

            for (int i = 0; i < n; i++) {
                mat[i] = sc.nextInt();
//                mat[i] = random.nextInt(20)+1;
//                System.out.print(mat[i]+" ");
            }
//            System.out.println();

            map = new HashMap<>();

            System.out.println(ff(0, n-1));
        }
    }
    private static Integer ff(int s, int e){
        Pair<Integer, Integer> key = new Pair<>(s, e);
        if(map.containsKey(key)){
            return map.get(key);
        }else {

            if(e-s+1==2){
                return 0;
            }

            if(e-s+1==3){
                int z = mat[s] * mat[s + 1] * mat[s + 2];
                map.put (key, z);
                return z;
            }

            int min = Integer.MAX_VALUE;

            for (int i = s+1; i < e; i++) {

                int z = 0;

                z += ff(s, i);

                z += ff(i, e);

                z += mat[s] * mat[i] * mat[e];

                if(z<min)
                    min = z;

            }

            map.put(key, min);

            return min;
        }
    }


}
