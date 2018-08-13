import java.util.List;

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
}
