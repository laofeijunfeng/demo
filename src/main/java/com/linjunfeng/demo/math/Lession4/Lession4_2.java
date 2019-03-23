package com.linjunfeng.demo.math.Lession4;

public class Lession4_2 {

    /**
     * 使用函数的递归（嵌套）调用，进行数学归纳法的证明，当前验证的是 2 ^ n - 1
     * @param k 放到第几格
     * @param result 当前格子麦子的总数
     * @return
     */
    public static boolean prove(int k, Result result) {
        if (k == 1) {
            if ((Math.pow(2, 1) - 1) == 1) {
                result.wheatNum = 1;
                result.wheatTotalNum = 1;
                return true;
            } else
                return false;
        } else {
            boolean proveOfPreviousOne = prove(k - 1, result);
            result.wheatNum *= 2;
            result.wheatTotalNum += result.wheatNum;
            boolean proveOfCUrrentOne = false;
            if (result.wheatTotalNum == (Math.pow(2, k) - 1))
                proveOfCUrrentOne = true;
            return proveOfPreviousOne && proveOfCUrrentOne;
        }
    }

    public static void main(String[] args) {
        int grid = 63;
        Result result = new Result();
        System.out.println(Lession4_2.prove(grid, result));
    }

    private static class Result {
        public long wheatNum = 0;
        public long wheatTotalNum = 0;
    }
}
