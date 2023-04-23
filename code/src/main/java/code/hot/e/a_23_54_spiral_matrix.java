package code.hot.e;

import java.util.ArrayList;
import java.util.List;

public class a_23_54_spiral_matrix {
    public List<Integer> spiralOrder(int[][] matrix) {
        List<Integer> res = new ArrayList<>();
        int fRow = 0;
        int fCol = 0;
        int row = matrix.length - 1;
        int col = matrix[0].length - 1;
        while (fRow <= row && fCol <= col) {
            for (int i = fCol; i <= col; ++i) {
                res.add(matrix[fRow][i]);
            }
            for (int i = fRow + 1; i <= row; ++i) {
                res.add(matrix[i][col]);
            }
            if (fRow <= row - 1 && fCol <= col - 1) {
                for (int i = col - 1; i >= fRow; --i) {
                    res.add(matrix[row][i]);
                }
                for (int i = row - 1; i >= fRow + 1; --i) {
                    res.add(matrix[i][fCol]);
                }
            }
            fRow++;
            fCol++;
            row--;
            col--;
        }
        return res;

    }
    public static List<Integer>   spiralOrder2(int[][] matrix) {
        int row = matrix.length;
        int col = matrix[0].length;
        int left = 0;
        int right = col-1;
        int top = 0;
        int button = row-1;
        List<Integer>res = new ArrayList<>();

        while(left<right&&top<button){
            for (int i = left;i<right;++i){
                res.add(matrix[top][i]);
            }
            for(int i = top;i<button;++i){
                res.add(matrix[i][right]);
            }
            for(int i =right;i>left;--i ){
                res.add(matrix[button][i]);
            }
            for(int i = button;i>top;--i){
                res.add(matrix[i][left]);
            }
            left++;
            right--;
            top++;
            button--;
        }
        if(right<left||button<top){
            return res;
        }
        if (left==right&&top==button){
            res.add(matrix[left][top]);
        }else if(left==right){
            for (int i = top;i<=button;++i){
                res.add(matrix[i][left]);
            }
        }else {
            for (int i = left;i<=right;++i){
                res.add(matrix[top][i]);
            }
        }
        return res;
    }

    public static void main(String[] args) {
        System.out.println(spiralOrder2(new int[][]{{3}, {2}}));
    }


}
