import java.util.*;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        System.out.println("Hello World!");
//        ArrayList<Interval> listw = new ArrayList<>();
//        listw.add(new Interval(2, 6));
//        listw.add(new Interval(8, 10));
//        listw.add(new Interval(1, 3));
//        listw.add(new Interval(15, 18));
//        listw.add(new Interval(18, 28));
//        merge(listw);

        while (true) {

            Scanner sc = new Scanner(System.in).useDelimiter("\n");
//            int x = sc.nextInt();
//            if (x == 0) {
//                break;
//            }
            String x = sc.next();
            if (x.isEmpty()) {
                break;
            }
//            String x1 = sc.next();
//            if (x1.isEmpty()) {
//                break;
//            }

//            ArrayList<String> list = new ArrayList<>();
//            for (int j = 0; j < x; j++) {
//                String k = sc.next();
//                list.add(k);
//            }

            System.out.println(StringModule.prettyJSON(x));

//            ArrayList<ArrayList<Integer>> generate = generate(x);
//            for (ArrayList<Integer> list : generate) {
//                for (Integer aWave : list) {
//                    System.out.print(aWave);
//                    System.out.print(" ");
//                }
//                System.out.print('\n');
//            }


//            if(list.size()>0)
//                System.out.println(list.get(0) + " " + list.get(1));
//            else
//                System.out.println("No flips needed");
        }

    }
}

