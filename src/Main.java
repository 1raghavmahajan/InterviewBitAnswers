import java.util.*;

public class Main {
    public static void main(String[] args) {
        System.out.println("Hello World!");

        while (true) {

            Scanner sc = new Scanner(System.in).useDelimiter("\\s");
            int x1 = sc.nextInt();
            if (x1 == 0) {
                break;
            }
//            int x2 = sc.nextInt();
//            if (x2 == 0) {
//                break;
//            }
//            int x3 = sc.nextInt();
//            if (x3 == 0) {
//                break;
//            }

//            String x = sc.next();
//            if (x.isEmpty()) {
//                break;
//            }
//            String x1 = sc.next();
//            if (x1.isEmpty()) {
//                break;
//            }

//            ArrayList<Integer> list = new ArrayList<>();
//            for (int j = 0; j < x1; j++) {
//                int k = sc.nextInt();
//                list.add(k);
//            }

            ArrayList<String> list = new ArrayList<>();
            for (int j = 0; j < x1; j++) {
                String k = sc.next();
                list.add(k);
            }

//            ListNode aa = new ListNode(1);
//            ListNode s = aa;
//
//            for (int j = 0; j < x1; j++) {
//                int k = sc.nextInt();
//                if(j == 0)
//                    aa.val = k;
//                else {
//                    aa.next = new ListNode(k);
//                    aa = aa.next;
//                }
//            }

//            ArrayList<ArrayList<Character>> list = new ArrayList<>();
//            for (int j = 0; j < x1; j++) {
//                String k = sc.next();
//                ArrayList<Character> characters = new ArrayList<>();
//                for (char c : k.toCharArray()) {
//                    characters.add(c);
//                }
//                list.add(characters);
//            }

            String[] aa = new String[list.size()];
            for (int i = 0; i < list.size(); i++) {
                aa[i] = list.get(i);
            }
            System.out.println(Arrays.deepToString(new DP().queenAttack(aa)));

//            System.out.println(StacksAndQueues.largestRectangleArea(list));

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

