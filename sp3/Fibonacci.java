package sp3;

public class Fibonacci {

	public static long linearFibonacci(long n, long p){
		long[] f = new long[(int)n+1];
		f[0] = 0;
		f[1] = 1;
		for (int i=2;i<=n;i++){
			f[i] = f[i-1]+f[i-2];
			f[i] %= p;
		}
		return f[(int)n];
	}
	
	public static void main(String[] args){
		System.out.println(linearFibonacci(100000000,999953));
	}
}
