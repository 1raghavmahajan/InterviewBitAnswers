import java.util.ArrayList;

public class TreesModule {

    private boolean isLess(TreeNode l, int v) {
        if (l == null)
            return true;
        System.out.print("isLess:"+v);
        System.out.print(" val:"+l.val);
        if(l.left!=null)
            System.out.print(" left:"+ l.left.val);
        if(l.right!=null)
            System.out.print(" right:"+ l.right.val);
        System.out.println();

        if (l.val < v) {
            if (l.left == null || isLess(l.left, v)) {
                if (l.right == null || isLess(l.right, v)) {
                    return true;
                } else {
                    return false;
                }
            } else {
                return false;
            }
        } else {
            return false;
        }
    }

    private boolean isGreater(TreeNode l, int v) {
        if (l == null)
            return true;
        System.out.print("isGreater:"+v);
        System.out.print(" val:"+l.val);
        if(l.left!=null)
            System.out.print(" left:"+ l.left.val);
        if(l.right!=null)
            System.out.print(" right:"+ l.right.val);
        System.out.println();
        if (l.val > v) {
            if (l.left == null || isGreater(l.left, v)) {
                if (l.right == null || isGreater(l.right, v)) {
                    return true;
                } else {
                    return false;
                }
            } else {
                return false;
            }
        } else {
            return false;
        }
    }

    public int isValidBST(TreeNode A) {

        int arr[];


        if (A == null)
            return 1;

        System.out.println("isValidBST: "+A.val);
        int v1 = 1;
        if (A.left != null) {
            if (isLess(A.left, A.val)) {
                v1 = isValidBST(A.left);
            }else {
                v1 = 0;
            }
        }
        if (v1 == 0)
            return 0;
        int v2 = 1;
        if (A.right != null) {
            if (isGreater(A.right, A.val)) {
                v2 = isValidBST(A.right);
            }else {
                v2 = 0;
            }
        }
        return v2;

    }

}
