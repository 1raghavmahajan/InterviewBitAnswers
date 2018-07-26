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

            Scanner sc = new Scanner(System.in).useDelimiter("\\s");
            int x = sc.nextInt();
            if (x == 0) {
                break;
            }

//            ArrayList<Integer> list = new ArrayList<>();
//            for (int j = 0; j < x; j++) {
//                int k = sc.nextInt();
//                list.add(k);
//            }

            System.out.println(ArraysModule.generateMatrix(x));

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

