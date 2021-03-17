/*
Given a square matrix mat, return the sum of the matrix diagonals.
Only include the sum of all the elements on the primary diagonal and all
 the elements on the secondary diagonal that are not part of the primary diagonal.
 */

public class matrixDiagonalsSum {
    public static int sumMat(int[][] A) {
        int sum = 0;
        for (int i = 0; i < A.length; i++) {
            sum += A[i][i];
            sum += A[i][A.length - i - 1];
        }
        if (A.length % 2 != 0) {
            sum -= A[(A.length / 2)][(A.length / 2)];
        }
        return sum;
    }
    public static void main(String args[]) {
        int[][] A = { {0,1,2}, {3,4,5}, {6,7,8} };
        int[][] b = {{0, 1, 2, 7}, {3, 4, 5, 7}, {6, 7, 8, 7}, {6, 7, 8, 7}};
        System.out.println("sumMat(A)"+sumMat(A));
        System.out.println("sumMat(b)"+sumMat(b));
    }
}
