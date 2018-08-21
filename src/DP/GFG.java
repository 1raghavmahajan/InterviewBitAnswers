package DP;

import java.util.Scanner;

public class GFG {
//    longest-increasing-subsequence
    public static void lis(){

        Scanner sc = new Scanner(System.in);
        int t = sc.nextInt();

        while (t-->0){

            int n = sc.nextInt();
            int[] ar = new int[n];
            for (int i = 0; i < n; i++) {
                ar[i] = sc.nextInt();
            }

            if(n<2) {
                System.out.println(n);
                continue;
            }

            int[] s = new int[n];
            s[0] = 1;

            int max = 1;

            for (int i = 1; i < n; i++) {
                int m = 0;
                for (int j = i-1; j >=0 ; j--) {
                    if(ar[j]<ar[i]){
                        if(s[j]>m){
                            m = s[j];
                        }
                    }
                }
                s[i] = m+1;
                if(s[i]>max){
                    max = s[i];
                }
            }

            System.out.println(max);

        }
    }


}
