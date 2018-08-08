import java.util.ArrayList;

@SuppressWarnings("Duplicates")
public class BacktrackingModule {


    private ArrayList<Integer> retValid(final ArrayList<ArrayList<Character>> A, int r, int c) {

        ArrayList<String> rows = new ArrayList<>(9);

        for (ArrayList<Character> characters : A) {
            String s = "";
            for (Character character : characters) {
                s += character;
            }
            rows.add(s);
        }

        String col = "";

        String box = "";

        boolean[] num = new boolean[9];
        for (int i = 0; i < 9; i++) {
            num[i] = true;
        }

        for (int i = 0; i < 9; i++) {
            char c1 = rows.get(r).charAt(i);
            if (c1 != '.') {
                int x = c1 - '1';
                num[x] = false;
            }
        }

        box += rows.get(3 * (r / 3)).substring(3 * (c / 3), 3 * (c / 3) + 3);
        box += rows.get(3 * (r / 3) + 1).substring(3 * (c / 3), 3 * (c / 3) + 3);
        box += rows.get(3 * (r / 3) + 2).substring(3 * (c / 3), 3 * (c / 3) + 3);

        for (int i = 0; i < 9; i++) {
            col += rows.get(i).charAt(c);
        }

        for (int i = 0; i < 9; i++) {
            char c1 = box.charAt(i);
            if (c1 != '.') {
                int x = c1 - '1';
                num[x] = false;
            }
        }

        for (int i = 0; i < 9; i++) {
            char c1 = col.charAt(i);
            if (c1 != '.') {
                int x = c1 - '1';
                num[x] = false;
            }
        }

        ArrayList<Integer> valid = new ArrayList<>();
        for (int i = 0; i < 9; i++) {
            if (num[i])
                valid.add(i + 1);
        }
        return valid;
    }
    public boolean solve(ArrayList<ArrayList<Character>> a) {

        boolean f = true;
        for (int r = 0; r < 9; r++) {

            ArrayList<Character> row = a.get(r);

            for (int c = 0; c < 9; c++) {
                if (row.get(c) == '.') {
                    f = false;

                    ArrayList<Integer> ll = retValid(a, r, c);

                    if (ll == null)
                        return false;

                    for (Integer integer : ll) {
                        row.set(c, integer.toString().charAt(0));
                        if (!solve(a))
                            row.set(c, '.');
                        else
                            return true;
                    }

                }

                if (row.get(c).equals('.'))
                    return false;

            }

            if (row.contains('.')) {
                return false;
            }

        }

        if (f) {
            for (ArrayList<Character> characters : a) {
                System.out.print(characters.subList(0, 3));
                System.out.print(characters.subList(3, 6));
                System.out.println(characters.subList(6, 9));
            }
            System.out.println();
            return true;
        }

        return false;

    }

}
