class Solution {
    public double myPow(double x, int n) {
        long N = n;
        if(n == 0 || x == 1) {
            return 1;
        }
        if(N < 0) {
            //double pPow = myPow(x, n*(-1));// don't do this 1/ number avoid
            x = 1/x;
            N = -N; /// corner case when n is -2147483648 as changing it to positive value is not posibble , max positive int is 2147483647
        } 
        /*double res = 1;
        for(long i=0;i<N;i++) {
            res = res*x;
        }
        return res;*/
        
        return processHalf(x, N);
        
    }
    
    private double processHalf(double x, long n) {
        if( n == 0) {
            return 1;
        }
        double half = processHalf(x, n/2);
        if(n%2 == 0) {
            return half * half;
        } else {
            return half * half * x;
        }
    }
}
