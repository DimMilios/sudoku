package test;

import java.util.Arrays;

public class SudokuGenerator
{
    int[][] mat;
    int size; // number of columns/rows.
    int sizeSqrt; // square root of N
    int missingDigits; // No. Of missing digits

    // Constructor
    public SudokuGenerator(int size, int missingDigits)
    {
        this.size = size;
        this.missingDigits = missingDigits;

        // Compute square root of N
        Double SRNd = Math.sqrt(size);
        sizeSqrt = SRNd.intValue();

        mat = new int[size][size];
    }

    // SudokuGenerator Generator
    public void fillValues()
    {
        // Fill the diagonal of SRN x SRN matrices
        fillDiagonal();

        // Fill remaining blocks
        fillRemaining(0, sizeSqrt);

        // Remove Randomly K digits to make game
        removeKDigits();
    }

    // Fill the diagonal SRN number of SRN x SRN matrices
    void fillDiagonal()
    {

        for (int i = 0; i< size; i=i+ sizeSqrt)

            // for diagonal box, start coordinates->i==j
            fillBox(i, i);
    }

    // Returns false if given 3 x 3 block contains num.
    boolean unUsedInBox(int rowStart, int colStart, int num)
    {
        for (int i = 0; i< sizeSqrt; i++)
            for (int j = 0; j< sizeSqrt; j++)
                if (mat[rowStart+i][colStart+j]==num)
                    return false;

        return true;
    }

    // Fill a 3 x 3 matrix.
    void fillBox(int row,int col)
    {
        int num;
        for (int i = 0; i< sizeSqrt; i++)
        {
            for (int j = 0; j< sizeSqrt; j++)
            {
                do
                {
                    num = randomGenerator(size);
                }
                while (!unUsedInBox(row, col, num));

                mat[row+i][col+j] = num;
            }
        }
    }

    // Random generator
    int randomGenerator(int num)
    {
        return (int) Math.floor((Math.random()*num+1));
    }

    // Check if safe to put in cell
    boolean CheckIfSafe(int i,int j,int num)
    {
        return (unUsedInRow(i, num) &&
                unUsedInCol(j, num) &&
                unUsedInBox(i-i% sizeSqrt, j-j% sizeSqrt, num));
    }

    // check in the row for existence
    boolean unUsedInRow(int i,int num)
    {
        for (int j = 0; j< size; j++)
            if (mat[i][j] == num)
                return false;
        return true;
    }

    // check in the row for existence
    boolean unUsedInCol(int j,int num)
    {
        for (int i = 0; i< size; i++)
            if (mat[i][j] == num)
                return false;
        return true;
    }

    // A recursive function to fill remaining
    // matrix
    boolean fillRemaining(int i, int j)
    {
        //  System.out.println(i+" "+j);
        if (j>= size && i< size -1)
        {
            i = i + 1;
            j = 0;
        }
        if (i>= size && j>= size)
            return true;

        if (i < sizeSqrt)
        {
            if (j < sizeSqrt)
                j = sizeSqrt;
        }
        else if (i < size - sizeSqrt)
        {
            if (j == (i/ sizeSqrt)* sizeSqrt)
                j =  j + sizeSqrt;
        }
        else
        {
            if (j == size - sizeSqrt)
            {
                i = i + 1;
                j = 0;
                if (i>= size)
                    return true;
            }
        }

        for (int num = 1; num<= size; num++)
        {
            if (CheckIfSafe(i, j, num))
            {
                mat[i][j] = num;
                if (fillRemaining(i, j+1))
                    return true;

                mat[i][j] = 0;
            }
        }
        return false;
    }

    // Remove the K no. of digits to
    // complete game
    public void removeKDigits()
    {
        int count = missingDigits;
        while (count != 0)
        {
            int cellId = randomGenerator(size * size);

            // System.out.println(cellId);
            // extract coordinates i  and j
            int i = (cellId/ size);
            int j = cellId%9;
            if (j != 0)
                j = j - 1;

            if (i >= 9 || j >= 9) continue;

            // petaei ArrayIndexOutOfBoundsException merikes fores
//            System.out.println(i+" "+j);
            if (mat[i][j] != 0)
            {
                count--;
                mat[i][j] = 0;
            }
        }
    }

    // Print sudoku
    public void printSudoku()
    {
        for (int i = 0; i< size; i++)
        {
            for (int j = 0; j< size; j++)
                System.out.print(mat[i][j] + " ");
            System.out.println();
        }
        System.out.println();
    }

    public int[][] getMat() {
        return mat;
    }

    public void emptyMat() {
        for (int i = 0; i < size; i++) {
            Arrays.fill(this.mat[i], 0);
        }
    }

    // Driver code
    public static void main(String[] args)
    {
        int N = 9, K = 20;
        SudokuGenerator sudoku = new SudokuGenerator(N, K);
        sudoku.fillValues();
        sudoku.printSudoku();
    }
}
