import java.util.*;
import java.io.*;
import java.math.*;
public class BigRoots{
	public static void main(String[] args){
		Scanner sc = new Scanner(new BufferedReader(new InputStreamReader(System.in)));
		int caseID = 1;
		long timeStart = System.nanoTime();
		BigInteger n = sc.nextBigInteger();
		while (true){
			if ((n.compareTo(BigInteger.valueOf(0)))==0){
				break;
			}
			else{
				BigInteger p = sc.nextBigInteger();
				BigInteger res = bigRooter(n,p);
				String ret = res.toString();
				if (res.compareTo(BigInteger.valueOf(-1))==0){
					ret = "No solution";
				}
				System.out.print("Case " + (caseID) + ": " + (ret) + "\n\n");
				n = sc.nextBigInteger();
				caseID++;


			}
		}
		long endTime = System.nanoTime();
		System.out.print((endTime-timeStart)/1000000);
	}
	public static BigInteger bigRooter(BigInteger n, BigInteger p){
		
		BigInteger one = BigInteger.valueOf(1);
		BigInteger two = BigInteger.valueOf(2);
		BigInteger lo = one;
		BigInteger hi = p;
		while(lo.compareTo(hi) == -1){
			BigInteger pp = (lo.add(hi)).shiftRight(1);
			//System.out.print(pp);
			int compareV = (pow(pp,n)).compareTo(p);
			if (compareV == 0){
				return pp;
			}
			if( compareV == -1 ){
				lo = pp.add(one);
			}
			
			else{
				hi = pp.subtract(one);
			}
		}
		//System.out.print(lo);
		if (!((pow(lo,n).equals(p)))){
			return BigInteger.valueOf(-1);
		}
		return lo;
	}


public static BigInteger pow(BigInteger base, BigInteger exponent) {
  BigInteger result = BigInteger.ONE;
  while (exponent.signum() > 0) {
    if (exponent.testBit(0)) result = result.multiply(base);
    base = base.multiply(base);
    exponent = exponent.shiftRight(1);
  }
  return result;
}

	}