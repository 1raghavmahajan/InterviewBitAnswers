import java.util.ArrayList;
import java.util.Scanner;
import java.util.Stack;

public class MinStack {

    Stack<Integer> min;
    Stack<Integer> list;

    MinStack(){
        min = new Stack<>();
        list = new Stack<>();
    }

    public static void launchTester(){

        Scanner sc = new Scanner(System.in).useDelimiter("\\s");
        int x1 = sc.nextInt();

        MinStack solution = new MinStack();

        for (int j = 0; j < x1; j++) {
            String k = sc.next();
            switch (k){
                case "P":
                    int x = sc.nextInt();
                    solution.push(x);
                    break;
                case "p":
                    solution.pop();
                    break;
                case "t":
                    System.out.println(solution.top());
                    break;
                case "g":
                    System.out.println(solution.getMin());
                    break;
            }
        }

    }

    public void push(int x) {
        if(min.empty())
            min.push(x);
        else {
            if (x < min.peek())
                min.push(x);
            else
                min.push(min.peek());
        }
        list.push(x);
    }

    public void pop() {
        if(!list.empty()) {
            list.pop();
            min.pop();
        }
    }

    public int top() {
        if(!list.empty())
            return list.peek();
        else
            return -1;
    }

    public int getMin() {
        if(min==null || min.empty())
            return -1;
        return min.peek();
    }

}
