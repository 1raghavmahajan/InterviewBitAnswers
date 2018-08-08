import java.util.ArrayList;

public class NQueenProblem
{
    private int N = 4;
    ArrayList<ArrayList<String>> l;

    NQueenProblem(){
        l = new ArrayList<>();
    }

    private void addSol(int board[][])
    {
        ArrayList<String> ss = new ArrayList<>();
        for (int i = 0; i < N; i++)
        {
            String s = "";
            for (int j = 0; j < N; j++){
                if(board[i][j]==1)
                    s+="Q";
                else
                    s+=".";
            }
            ss.add(s);
        }
        l.add(ss);
    }

    private boolean isSafe(int board[][], int row, int col)
    {
        int i, j;

        /* Check this row on left side */
        for (i = 0; i < col; i++)
            if (board[row][i] == 1)
                return false;

        /* Check upper diagonal on left side */
        for (i=row, j=col; i>=0 && j>=0; i--, j--)
            if (board[i][j] == 1)
                return false;

        /* Check lower diagonal on left side */
        for (i=row, j=col; j>=0 && i<N; i++, j--)
            if (board[i][j] == 1)
                return false;

        return true;
    }

    private boolean solveNQUtil(int board[][], int col)
    {
        if (col >= N) {
            addSol(board);
            return true;
        }
        for (int i = 0; i < N; i++)
        {
            if (isSafe(board, i, col))
            {
                board[i][col] = 1;

                if (solveNQUtil(board, col + 1) == true) {
                }
 
                board[i][col] = 0;
            }
        }
 
        return false;
    }

    public ArrayList<ArrayList<String>> solveNQueens(int a) {

        N = a;

        int board[][] = new int[N][N];

        l.clear();
        solveNQUtil(board, 0);

        return l;
    }

    public static void iterate(){
        NQueenProblem nQueenProblem = new NQueenProblem();

        for (int i = 4; i < 15; i++) {
            long startTime = System.nanoTime();
            nQueenProblem.solveNQueens(i);
//                System.out.println("N="+i+" S:"+nQueenProblem.solveNQueens(i).size());
            long endTime = System.nanoTime();
            System.out.print("{" + i + "," + ((endTime - startTime) / 1000000.0f) + "}, ");
        }
    }

}