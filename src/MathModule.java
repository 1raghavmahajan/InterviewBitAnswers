import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

@SuppressWarnings("WeakerAccess")
public class MathModule {

    public static int isPalindrome(int A) {
        ArrayList<Integer> digits = new ArrayList<>();
        int o = A;
        while (A > 0) {
            digits.add(A % 10);
            A = A / 10;
        }
        int K = 0;
        for (int i = digits.size() - 1; i >= 0; i--) {
            K += digits.get(i) * java.lang.Math.pow(10, digits.size() - 1 - i);
        }
        if (K == o) {
            return 1;
        } else
            return 0;
    }

    public static int reverse(int A) {
        ArrayList<Integer> digits = new ArrayList<>();
        int o = A;
        if (A < 0)
            A *= -1;
        while (A > 0) {
            digits.add(A % 10);
            A = A / 10;
        }
        int K = 0;
        for (int i = digits.size() - 1; i >= 0; i--) {
            double t = K + digits.get(i) * java.lang.Math.pow(10, digits.size() - 1 - i);
            if (t > Integer.MAX_VALUE) {
                return 0;
            }
            K += digits.get(i) * java.lang.Math.pow(10, digits.size() - 1 - i);
        }
        if (o >= 0) {
            return K;
        } else {
            return (-1) * K;
        }
    }

    public static ArrayList<Integer> allFactors(int A) {
        ArrayList<Integer> list = new ArrayList<>();
        int sqrt = (int) Math.sqrt(A);
        for (int i = 1; i < Math.sqrt(A); i++) {
            if (A % i == 0) {
                list.add(i);
            }
        }
        int size = list.size();
        if (sqrt * sqrt == A) {
            list.add(sqrt);
        }
        for (int i = size - 1; i >= 0; i--) {
            Integer integer = list.get(i);
            list.add(A / integer);
        }
        return list;
    }

    public static int sqrt(int a) {

        if (a == 0 || a == 1)
            return a;

        long start = 1, end = 46340;
        long ans = 0;

        while (start <= end) {
            long mid = (start + end) / 2;

            if (mid * mid == a)
                return (int) mid;

            if (mid * mid < a) {
                start = mid + 1;
                ans = mid;
            } else
                end = mid - 1;
        }
        return (int) ans;

    }

    public static int pow(long x, long n, long d) {

        if (x == 0)
            return 0;
        if (n == 0)
            return 1;

        boolean neg = false;
        if (x < 0) {
            if (n % 2 != 0)
                neg = true;
            x *= -1;
        }

        x = x % d;

        long k = pow2(x, n, d);

        if (neg)
            return (int) (d - k);
        else
            return (int) k;

    }

    private static long pow2(long x, long n, long d) {
        if (n == 0)
            return 1;
        if (n == 1)
            return x;

        if (n % 2 == 0) {
            long g = pow2(x, n / 2, d) % d;
            return ((g * g) % d);
        } else {
            long g = pow2(x, (n - 1) / 2, d) % d;
            return ((g * g * x) % d);
        }
    }

    int b[][];
    public int uniquePaths(int A, int B) {

        if(A==0 && B==0)
            return 1;
        else if(A==1 || B==1)
            return 1;

        b = new int[A][B];
        for (int i = 0; i < A; i++) {
            Arrays.fill(b[i], -1);
        }
        return uniquePaths2(0, 0);
    }

    private int uniquePaths2(int r, int c) {
        int R = b.length;
        int C = b[0].length;
        if (r >= R || c >= C) return 100000000; // Infinity
        if (r == R - 1 && c == C - 1) return 1;

        if(b[r][c]!=-1){
            return b[r][c];
        }else {
            b[r][c] = 0;
            if(r<R-1)
                b[r][c] += uniquePaths2(r+1, c);
            if(c<C-1)
                b[r][c] += uniquePaths2(r, c+1);
            return b[r][c];
        }
    }

    public static int solve(ArrayList<Integer> A, int B, int C) {

        //n num
        //len = min {len(start), B}
        //calc all
        //- 0 vale
        //if( len(C) = B )
        // for i 0->len-1
        //

//        12345
        return 0;
    }

    @SuppressWarnings("Duplicates")
    public static int kthsmallest(final List<Integer> A, int B) {
        if(B<=A.size()/2) {
            ArrayList<Integer> list = new ArrayList<>();
            for (int i = 0; i < B; i++) {
                list.add(A.get(i));
            }
            list.sort(Comparator.naturalOrder());
            for (int i = B; i < A.size(); i++) {
                int j=B-1;
                while (j>=0 && A.get(i)<=list.get(j)){
                    j--;
                }
                if(j<B-1)
                    list.add(j+1, A.get(i));
            }
            return list.get(B - 1);
        }else {
            B = A.size()-B+1;

            ArrayList<Integer> list = new ArrayList<>();
            for (int i = 0; i < B; i++) {
                list.add(A.get(i));
            }
            list.sort(Comparator.reverseOrder());
            for (int i = B; i < A.size(); i++) {
                int j=B-1;
                while (j>=0 && A.get(i)>=list.get(j)){
                    j--;
                }
                if(j<B-1)
                    list.add(j+1, A.get(i));
            }
            return list.get(B - 1);
        }
    }

    public static int numRange(ArrayList<Integer> A, int B, int C) {
        int x = 0;
        for (int i = 0; i < A.size(); i++) {
            int sum = A.get(i);
            int j=i+1;
            while (sum<=C){
                if(sum>=B)
                    x++;
                if(j>=A.size())
                    break;
                sum+=A.get(j);
                j++;
            }
        }
        return x;
    }

    private static int fr(String str, int low, int high) {
        int countRight = 0, i;

        for (i = low + 1; i <= high; ++i)
            if (str.charAt(i) < str.charAt(low))
                ++countRight;

        return countRight;
    }
    public static int findRank (String str) {
        int len = str.length();
        int mul = 1;
        int rank = 1;
        int countRight;

        for (int i = len-1; i >=0; i--)
        {
            if(i<len-1) {
                mul *= len - i - 1;
            }
            mul = mul % 1000003;

            countRight = fr(str, i, len-1);

            rank += (countRight * mul) % 1000003 ;
            rank = rank % 1000003;
        }

        return rank;
    }
    static int fact(int n)
    {
        return (n <= 1)? 1 :n * fact(n-1);
    }

    public static void arrange(ArrayList<Integer> a) {
        if(a.size()<2)
            return;

        for (int i = 0; i < a.size(); i++) {
            int j = i;
            int saved = a.get(i);
            int n = a.get(j);
            if(n<0)
                continue;
            while (n>=0){
                if(n==i){
                    a.set(j, -1*saved);
                    j = n;
                    n = saved;
                    break;
                }
                Integer q = a.get(n);
                if(q<0){
                    break;
                }
                if(q==0) {
                    a.set(j, Integer.MIN_VALUE);
                }else {
                    a.set(j, -1 * q);
                }
                j = n;
                n = q;
            }
        }

        for (int i = 0; i < a.size(); i++) {
            Integer integer = a.get(i);
            if(integer == Integer.MIN_VALUE){
                a.set(i, 0);
            }else {
                a.set(i, -1*integer);
            }
        }

    }

    public ArrayList<String> fizzBuzz(int A) {
        ArrayList<String> list = new ArrayList<>();
        for (int i = 1; i < A; i++) {
            if(i%3==0 && i%5==0){
                list.add("FizzBuzz");
            }else if(i%3==0){
                list.add("Fizz");
            }else if(i%5==0){
                list.add("Buzz");
            }else {
                list.add(String.valueOf(i));
            }
        }
        return list;
    }
}
