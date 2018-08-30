public class Graphs {

    //    valid-path
    static int X, Y;
    static boolean[][] mat, done;
    public static String solve(int x, int y, int N, int r, int[] A, int[] B) {

        String sy = "YES";
        String sn = "NO";

        if (x <= 0 || y <= 0)
            return sy;
        if (N == 0) {
            return sy;
        }

        mat = new boolean[x + 1][y + 1];
        done = new boolean[x + 1][y + 1];
        X = x;
        Y = y;

        boolean[][] circle = new boolean[r + 1][r + 1];

        for (int i = r; i >= 0; i--) {
            for (int j = r; j >= i; j--) {

                if (circle[i][j])
                    continue;

                double d = Math.sqrt(i * i + j * j);
                if (d <= (double) r) {
                    int m = i, n = j;
                    for (; m >= 0; m--) {
                        for (; n >= 0; n--) {
                            circle[m][n] = true;
                            circle[n][m] = true;
                        }
                    }
                    break;
                }
            }
        }

        for (int i1 = 0; i1 < A.length; i1++) {

            int p = A[i1], q = B[i1];

            int m1 = -1, m2 = -1;
            for (int i2 = 0; i2 < 2; i2++, m1 *= -1) {
                for (int i3 = 0; i3 < 2; i3++, m2 *= -1) {

                    for (int i = 0; i <= r; i++) {
                        boolean f = false;
                        for (int j = 0; j <= r; j++) {

                            if (!circle[i][j]) {
                                break;
                            }

                            int x1 = p + i * m1;
                            if (x1 > x || x1 < 0) {
                                f = true;
                                break;
                            }

                            int y1 = q + j * m2;
                            if (y1 > y || y1 < 0) {
                                continue;
                            }

                            if (x1 == 0 && y1 == 0)
                                return sn;
                            if (x1 == x && y1 == y)
                                return sn;

                            mat[x1][y1] = true;

                        }
                        if (f)
                            break;

                    }

                }
            }

        }

        for (int i = 0; i <= x; i++) {
            for (int j = 0; j <= y; j++) {
                if (mat[i][j])
                    System.out.print(1 + " ");
                else
                    System.out.print(0 + " ");
            }
            System.out.println();
        }

        mat[0][0] = true;
        done[0][0] = true;
        if(ff(0, 0))
            return sy;
        else
            return sn;

    }
    private static boolean ff(int x, int y) {
        if (x == X && y == Y) {
            return true;
        } else {
            if (x > X || y > Y || x < 0 || y < 0) {
                return false;
            }

            for (int i = -1; i < 2; i++) {
                for (int j = -1; j < 2; j++) {
                    if (i == 0 && j == 0)
                        continue;
                    int x1 = x + i;
                    int y1 = y + j;

                    if(x1>X || x1<0 || y1>Y || y1<0){
                        continue;
                    }

                    if (!mat[x1][y1] && !done[x1][y1]) {
                        done[x1][y1] = true;
                        if (ff(x + i, y1))
                            return true;
//                        else
//                            mat[x1][y1] = false;
                    }
                }
            }

            return false;

        }
    }

}
