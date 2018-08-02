import java.util.ArrayList;
import java.util.Stack;
import java.util.TreeSet;

public class StacksAndQueues {
    public static String simplifyPath(String A) {
        //        String s = split[split.length-1];
//        s = "/"+s;
//        s = s.replace("/./", "/");
//        if(s.length()>1 && s.charAt(s.length()-1) == '/'){
//            s = s.substring(0, s.length()-1);
//        }
        String[] split = A.split("/");
        Stack<String> l = new Stack<>();
        for (String s : split) {
            if(s.isEmpty() || s.equals(".")){
            }else if(s.equals("..")){
                if(!l.empty())
                    l.pop();
            }else {
                l.push(s);
            }
        }
        StringBuilder b = new StringBuilder("/");
        int i = 0;
        for (; i < l.size()-1; i++) {
            b.append(l.get(i));
            b.append("/");
        }
        if(i<l.size())
            b.append(l.get(i));

        return b.toString();
    }
    public static int evalRPN(ArrayList<String> A) {
        Stack<String> stack = new Stack<>();
        for (String s : A) {
            if(s.matches("[+-/*]")){
                int x2 = Integer.parseInt(stack.pop());
                int x1 = Integer.parseInt(stack.pop());
                switch (s) {
                    case "/":
                        stack.push(String.valueOf((Integer) (x1 / x2)));
                        break;
                    case "*":
                        stack.push(String.valueOf((Integer) (x1 * x2)));
                        break;
                    case "+":
                        stack.push(String.valueOf((Integer) (x1 + x2)));
                        break;
                    case "-":
                        stack.push(String.valueOf((Integer) (x1 - x2)));
                        break;
                }

            }else if(s.matches("^(\\d+)")){
                stack.push(s);
            }
        }

        if (stack.empty())
            return 0;

        return Integer.parseInt(stack.pop());
    }

    public static ArrayList<Integer> prevSmaller(ArrayList<Integer> A) {

        ArrayList<Integer> min = new ArrayList<>();
        if(A.size()>0) {
            min.add(-1);
            for (int i = 1; i < A.size(); i++) {
                if(A.get(i)>A.get(i-1)){
                    min.add(A.get(i-1));
                }else {
                    int j=i-1;
                    while (j>=0){
                        if(A.get(i)<=A.get(j)){
                            if(A.get(i)>min.get(j)) {
                                min.add(min.get(j));
                                break;
                            }
                            j--;
                        }else {
                            min.add(min.get(j));
                            break;
                        }
                    }
                }
            }
        }
        return min;
    }

    public static int largestRectangleArea2(ArrayList<Integer> A) {

        if(A.size()==0)
            return 0;
        int max = A.get(0);

        ArrayList<Integer> maxH = new ArrayList<>(A.size());
        ArrayList<Integer> maxW = new ArrayList<>(A.size());
        maxH.add(A.get(0));
        maxW.add(1);

        for (int i = 1; i < A.size(); i++) {
            if(A.get(i).equals(maxH.get(i - 1))){
                maxH.add(A.get(i));
                maxW.add(maxW.get(i-1)+1);
                if(maxH.get(i)*maxW.get(i)>max){
                    max = maxH.get(i)*maxW.get(i);
                }
            }else if (A.get(i) < maxH.get(i-1)){
                maxH.add(A.get(i));
                maxW.add(maxW.get(i-1)+1);
                if(maxH.get(i)*maxW.get(i)>max){
                    max = maxH.get(i)*maxW.get(i);
                }
            }else if(A.get(i)>maxH.get(i-1)){
                int w = maxW.get(i-1);
                int oh = maxH.get(i-1);

                int m2 = 0;

//                if(oh*(w+1)>= max){
                    maxH.add(oh);
                    maxW.add(w+1);
                    m2 = oh*(w+1);
//                }

                if(A.get(i-1)>maxH.get(i-1)){
                    int hh = Math.min(A.get(i-1), A.get(i));
                    if(hh*2>=m2){
                        if(i<maxH.size()){
                            maxH.set(i, hh);
                            maxW.set(i, 2);
                        }else {
                            maxH.add(hh);
                            maxW.add(2);
                        }
                        m2 = hh*2;
                    }
                }

                if(A.get(i) >=  m2){
                    if(i<maxH.size()){
                        maxH.set(i, A.get(i));
                        maxW.set(i, 1);
                    }else {
                        maxH.add(A.get(i));
                        maxW.add(1);
                    }
                    m2 = A.get(i);
                }

                if(m2>max)
                    max = m2;
            }
        }

        return max;
    }

    public static int largestRectangleArea3(ArrayList<Integer> A){

        int max = 0;
        Stack<Integer> s = new Stack<>();
        for (int i = 0; i < A.size(); i++) {
            if(s.empty() || A.get(i) > A.get(s.peek())){
                s.push(i);
            }
            if(A.get(i)< A.get(s.peek())){
                while (!s.empty() && A.get(s.peek())>A.get(i)){
                    int h = A.get(s.pop());
                    int r = i;
                    int l = s.peek();
                    max = Math.max(max, (r-l+1)*h);
                }
                s.push(i);
            }
        }
        if(!s.empty()){
            while (!s.empty()){
                int h = A.get(s.peek());
                int r = s.pop();
                int l = s.peek();
                max = Math.max(max, (r-l+1)*h);
            }
        }
        return max;
    }

    public static int largestRectangleArea(ArrayList<Integer> A){

        int n = A.size();
        Integer[] hist = A.toArray(new Integer[0]);

        Stack<Integer> s = new Stack<>();

        int max_area = 0; // Initialize max area
        int tp;  // To store top of stack
        int area_with_top; // To store area with top bar as the smallest bar

        // Run through all bars of given histogram
        int i = 0;
        while (i < n)
        {
            // If this bar is higher than the bar on top stack, push it to stack
            if (s.empty() || hist[s.peek()] <= hist[i])
                s.push(i++);

                // If this bar is lower than top of stack, then calculate area of rectangle
                // with stack top as the smallest (or minimum height) bar. 'i' is
                // 'right index' for the top and element before top in stack is 'left index'
            else
            {
                tp = s.peek();  // store the top index
                s.pop();  // pop the top

                // Calculate the area with hist[tp] stack as smallest bar
                area_with_top = hist[tp] * (s.empty() ? i : i - s.peek() - 1);

                // update max area, if needed
                if (max_area < area_with_top)
                    max_area = area_with_top;
            }
        }

        // Now pop the remaining bars from stack and calculate area with every
        // popped bar as the smallest bar
        while (!s.empty())
        {
            tp = s.peek();
            s.pop();
            area_with_top = hist[tp] * (s.empty() ? i : i - s.peek() - 1);

            if (max_area < area_with_top)
                max_area = area_with_top;
        }

        return max_area;
    }

}
