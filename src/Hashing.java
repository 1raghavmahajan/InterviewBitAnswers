import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

public class Hashing {

    public int diffPossible(final List<Integer> A, int B) {
        HashSet<Integer> map = new HashSet<>();
        for (Integer integer : A) {
            if(map.contains(integer+B) || map.contains(integer-B))
                return 1;
            else {
                //x-y = B
                //y-x = B
                map.add(integer);
            }
        }
        return 0;
    }

    public int isValidSudoku(final List<String> A) {
        ArrayList<String> rows = new ArrayList<>(A);

        ArrayList<String> cols = new ArrayList<>(9);

        ArrayList<String> boxes = new ArrayList<>(9);

        for (int i = 0; i < 9; i++) {
            cols.add("");
            boxes.add("");
        }


        for (int i = 0; i < 9; i++) {
            if(!check(rows.get(i))){
                return 0;
            }
            boxes.set(3*(int)(i/3), boxes.get(3*(int)(i/3)) + rows.get(i).substring(0, 3));
            boxes.set(3*(int)(i/3) + 1, boxes.get(3*(int)(i/3) + 1) + rows.get(i).substring(3, 6));
            boxes.set(3*(int)(i/3) + 2, boxes.get(3*(int)(i/3) + 2) + rows.get(i).substring(6, 9));
            for (int j = 0; j < 9; j++) {
                cols.set(j, cols.get(j)+rows.get(i).charAt(j));
            }
        }
        for (int i = 0; i < 9; i++) {
            if(!check(cols.get(i)))
                return 0;
            if(!check(boxes.get(i)))
                return 0;
        }

        return 1;
    }

    private boolean check(String s){
        boolean[] num = new boolean[9];
        for (int i = 0; i < 9; i++) {
            num[i] = false;
        }
        for (int i = 0; i < 9; i++) {
            if(s.charAt(i)!='.'){
                int x = s.charAt(i)-'1';
                if(num[x]){
                    return false;
                }else {
                    num[x] = true;
                }
            }
        }
        return true;
    }

}
