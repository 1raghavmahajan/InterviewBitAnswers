import java.util.ArrayList;
import java.util.Arrays;

public class StringModule {

    public static String longestCommonPrefix(ArrayList<String> A) {
        StringBuilder s = new StringBuilder();

        int i = 0;

        while (true) {
            char c;
            if (A.get(0).length() > i) {
                c = A.get(0).charAt(i);
            } else
                break;

            boolean flag = true;
            for (String s1 : A) {
                if (!(s1.length() > i && c == s1.charAt(i))) {
                    flag = false;
                    break;
                }
            }
            if (flag)
                s.append(c);
            else
                break;

            i++;
        }

        return s.toString();

    }

    @SuppressWarnings({"ConstantConditions", "Duplicates"})
    public static String longestPalindrome(String A) {

        if (A.length() > 0) {
            int m = 0, n = 1;
            int len = 1;

            for (int i = 0; i < A.length(); ++i) {

                if (i < A.length() - 1 && A.charAt(i + 1) == A.charAt(i)) {
                    int m1 = i, n1 = i + 1;
                    int j = 0;
                    if (len >= 2)
                        j = (len - 2) / 2 + 1;
                    while (m1 - j >= 0 && n1 + j < A.length()) {

                        if (checkPal(A.substring(m1 - j, n1 + j + 1))) {
                            if (((n1 - m1) + 2 * j + 1) > len) {
                                m = m1 - j;
                                n = n1 + j + 1;
                                len = (n1 - m1) + 2 * j + 1;
                            }

                        } else {
                            break;
                        }

                        j++;
                    }

                }

                int m1 = i, n1 = i;
                int j = (len - 1) / 2 + 1;
                while (m1 - j >= 0 && n1 + j < A.length()) {

                    if (checkPal(A.substring(m1 - j, n1 + j + 1))) {
                        if (((n1 - m1) + 2 * j + 1) > len) {
                            m = m1 - j;
                            n = n1 + j + 1;
                            len = (n1 - m1) + 2 * j + 1;
                        }

                    } else {
                        break;
                    }

                    j++;
                }

            }

            return A.substring(m, n);
        } else
            return A;

    }

    private static boolean checkPal(String a) {
        StringBuilder b = new StringBuilder();
        int n = a.length();

        for (int i = n - 1; i >= 0; i--)
            b.append(a.charAt(i));

        return a.equals(b.toString());
    }

    public static int strStr(final String A, final String B) {

        if (A.length() == 0 || B.length() == 0 || A.length() < B.length())
            return -1;

        for (int i = 0; i <= A.length() - B.length(); i++) {

            boolean f = true;
            for (int j = 0; j < B.length(); j++) {
                if (A.charAt(i + j) == B.charAt(j)) {

                } else {
                    f = false;
                    break;
                }
            }

            if (f) {
                return i;
            }
        }

        return -1;
    }

    public static int atoi(final String A) {
        if (A.length() == 0)
            return 0;

        int i = 0;

        Boolean neg = null;
        ArrayList<Integer> list = new ArrayList<>();

        while (i < A.length()) {

            if (list.size() == 0 && A.charAt(i) == ' ' && neg == null) {
                i++;
                continue;
            }
            if (list.size() == 0 && A.charAt(i) == '-') {
                if (neg == null) {
                    neg = true;
                    i++;
                    continue;
                } else {
                    break;
                }
            }
            if (list.size() == 0 && A.charAt(i) == '+') {
                if (neg == null) {
                    neg = false;
                    i++;
                    continue;
                } else {
                    break;
                }
            }

            int k = '0';
            if (A.charAt(i) == '0' ||
                    A.charAt(i) == '1' ||
                    A.charAt(i) == '2' ||
                    A.charAt(i) == '3' ||
                    A.charAt(i) == '4' ||
                    A.charAt(i) == '5' ||
                    A.charAt(i) == '6' ||
                    A.charAt(i) == '7' ||
                    A.charAt(i) == '8' ||
                    A.charAt(i) == '9') {
                int z = A.charAt(i) - k;
                list.add(z);
                i++;
            } else {
                break;
            }
        }

        int num = 0;
        for (int k = list.size() - 1; k >= 0; k--) {
            num += Math.pow(10, list.size() - 1 - k) * list.get(k);
        }


        if (neg != null && neg) {
            if (num == Integer.MAX_VALUE) {
                return Integer.MIN_VALUE;
            }
            num = num * -1;
        }

        return num;
    }

    public static ArrayList<String> restoreIpAddresses(String A) {
//        Your Input: 12105
//        Expected output is 1.2.10.5     1.21.0.5    12.1.0.5
//        255.255.255.255
//        Your Input: 0000
//        Expected output is 0.0.0.0

        ArrayList<String> list = new ArrayList<>();

        if (A.length() >= 4 && A.length() <= 12) {
            for (int i = 1; i < A.length() - 2; i++) {
                ArrayList<Integer> dots = new ArrayList<>();
//                StringBuilder b = new StringBuilder(A);
                dots.add(i);

                for (int i2 = i + 1; i2 < A.length() - 1; i2++) {
                    dots.add(i2);

                    for (int i3 = i2 + 1; i3 < A.length(); i3++) {
                        dots.add(i3);
                        isValid(list, A, dots);
                        dots.remove(new Integer(i3));
                    }

                    dots.remove(new Integer(i2));
                }
            }
        }

        return list;
    }

    private static void isValid(ArrayList<String> list, String s, ArrayList<Integer> dots) {
        StringBuilder b = new StringBuilder(s);
        for (int i = 0; i < dots.size(); i++) {
            b.insert(dots.get(i) + i, '.');
        }
        s = b.toString();

        String[] split = s.split("[.]");
        if (split.length == 4) {
            for (String s1 : split) {
                if (s1.length() > 1) {
                    if (s1.charAt(0) == '0')
                        return;
                }
                Integer p = Integer.valueOf(s1);
                if (!(p >= 0 && p <= 255))
                    return;
            }
        } else {
            return;
        }
        list.add(s);
    }

    public static int isNumber(final String A) {
        String s = A.trim();

        if (s.matches("^(-)?+((\\d+(\\.\\d+)?)|(\\.\\d+))+(e+(-)?+(\\d+))?")) {
            return 1;
        } else
            return 0;
    }

    public static ArrayList<String> prettyJSON(String A) {

        A = A.trim();
        A = A.replace(" ", "");
        A = A.replace("\n", "");
        A = A.replace("{", "\n{\n");
        A = A.replace("}", "\n}");
        A = A.replace("[", "\n[\n");
        A = A.replace("]", "\n]");
        A = A.replace(",", ",\n");

        String[] split = A.split("\n");
        ArrayList<String> list = new ArrayList<>(Arrays.asList(split));
        list.remove(0);
        while (list.remove(""))
            ;
        int i=0;
        for (int j = 0; j < list.size()-1; j++) {

            String s = list.get(j);
            if(s.matches("^[{\\[](.)?")){
                String ind = "";
                for (int k = 0; k < i; k++) {
                    ind += '\t';
                }
                list.set(j, ind+s);
                i++;
            }else if(s.matches("^[}\\]](.)?")){
                --i;
                String ind = "";
                for (int k = 0; k < i; k++) {
                    ind += '\t';
                }
                list.set(j, ind+s);
            }else {
                String ind = "";
                for (int k = 0; k < i; k++) {
                    ind += '\t';
                }
                list.set(j, ind+s);
            }
        }
        return list;
    }
}
