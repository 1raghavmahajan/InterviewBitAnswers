import java.util.*;

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
            boxes.set(3* (i/3), boxes.get(3* (i/3)) + rows.get(i).substring(0, 3));
            boxes.set(3* (i/3) + 1, boxes.get(3* (i/3) + 1) + rows.get(i).substring(3, 6));
            boxes.set(3* (i/3) + 2, boxes.get(3* (i/3) + 2) + rows.get(i).substring(6, 9));
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


    public int longestConsecutive(final List<Integer> A) {
        if(A==null || A.size()==0)
            return 0;

        HashSet<Integer> uni = new HashSet<>(A);

        HashMap<Integer, Integer> set = new HashMap<>();

        int ms = 1;

        for (Integer i : uni) {
            if(set.containsKey(i-1) && set.containsKey(i+1)){

                Integer s = set.get(i - 1);
                Integer e = set.get(i + 1);

                if(s<i-1){
                    set.remove(i-1);
                    set.put(s, e);
                }else if(s == i-1){
                    set.put(s, e);
                }

                if(e>i+1){
                    set.remove(i+1);
                    set.put(e, s);
                }else if(e == i+1){
                    set.put(e, s);
                }

                if(e-s+1>ms){
                    ms = e-s+1;
                }

            } else if(set.containsKey(i-1)){
                Integer o = set.get(i - 1);
                if(o<i-1){
                    set.remove(i-1);
                    set.put(i, o);
                    set.put(o, i);
                    if(i-o+1>ms)
                        ms = i-o+1;
                }else if(o == i-1){
                    set.put(i, i-1);
                    set.put(i-1, i);
                    if(ms<2)
                        ms = 2;
                }
            }else if(set.containsKey(i+1)){
                Integer o = set.get(i + 1);
                if(o>i+1){
                    set.remove(i+1);
                    set.put(i, o);
                    set.put(o, i);
                    if(o-i+1>ms)
                        ms = o-i+1;
                }else if(o == i+1){
                    set.put(i, i+1);
                    set.put(i+1, i);
                    if(ms<2)
                        ms = 2;
                }
            }
            else {
                set.put(i, i);
            }
        }
        return ms;
    }

}
