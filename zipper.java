package zipper;

import java.util.Arrays;
import java.util.*;
import java.io.*;





public class zipper {
	public static boolean m(int i, int j, String a, String b, String c){
		//System.out.println(i+ " " +j);
		//int cI;
		
		boolean t1[][] = new boolean[i+1][j+1];
		for (int zI=-1 ;zI<i;zI++ ){
			for (int xJ=-1; xJ<j; xJ++){
				//System.out.println(Arrays.deepToString(t1));
				//System.out.println(zI + " " + xJ);
				//System.out.println("cow");
				
				int cI = zI + xJ + 1;
				if (zI<0 && xJ<0){
					t1[zI+1][xJ+1]=true;
					//System.out.println(Arrays.deepToString(t1));
					continue;
				}
				//System.out.println(a.substring(0, zI+1) +" "+b.substring(0, xJ+1)+" "+c.substring(0, xJ+zI+1));
				else if(xJ<0){
					//System.out.println(xJ + " " + zI);
					t1[zI+1][xJ+1] = (a.substring(0, zI+1).equals(c.substring(0,zI+1)));
					//System.out.println(a.substring(0, zI+1) + " "+ c.substring(cI,cI+ zI+1));
					//System.out.println(Arrays.deepToString(t1));
					continue;
				}
				else if(zI<0){
					t1[zI+1][xJ+1]=b.substring(0, xJ+1).equals(c.substring(0,xJ+1));
					//System.out.print("here"+zI + " " + xJ);
					//System.out.println((b.substring(0, xJ+1).equals(c.substring(cI,cI+xJ+1))));
					//t1[0][xJ+1] = (b.substring(0, xJ+1).equals(c.substring(cI,cI+xJ+1)));
					
					//System.out.println(b.substring(0, xJ+1) + " " + c.substring(cI,cI+xJ+1));
					//System.out.println(Arrays.deepToString(t1));
					continue;
				}
				else if(a.charAt(zI)==c.charAt(zI+xJ+1) && b.charAt(xJ)==c.charAt(zI+xJ+1)){
					t1[zI+1][xJ+1] = t1[zI+1][xJ] || t1[zI][xJ+1];
					continue;
				}
				else if(a.charAt(zI)==c.charAt(zI+xJ+1)){
					//System.out.println("a");
					//System.out.println(zI + " "+xJ);
					t1[zI+1][xJ+1] = t1[zI][xJ+1];
					//System.out.println(Arrays.deepToString(t1));
					continue;
				}
				else if(b.charAt(xJ)==c.charAt(zI+xJ+1)){
					//System.out.println("b");
					t1[zI+1][xJ+1] = t1[zI+1][xJ];
					continue;
				}
				
			}}
		//System.out.println(Arrays.deepToString(t1));
		return t1[i][j];
		}
//	public static boolean m(int i, int j, String a, String b, String c){
//		System.out.println(i+ " " +j);
//		//int cI;
//		
//		boolean t1[][] = new boolean[i+1][j+1];
//		for (int zI=i ;zI>=0;zI-- ){
//			for (int xJ=j; xJ>=0; xJ--){
//				//System.out.println(Arrays.deepToString(t1));
//				System.out.println(zI + " " + xJ);
//				//System.out.println("cow");
//				
//				int cI = zI + xJ + 1;
//				if (zI==i && xJ==j){
//					t1[zI][xJ]=a.charAt(zI)==c.charAt(cI) || b.charAt(xJ)==c.charAt(cI);
//					System.out.println(Arrays.deepToString(t1));
//					continue;
//				}
//				//System.out.println(a.substring(0, zI+1) +" "+b.substring(0, xJ+1)+" "+c.substring(0, xJ+zI+1));
//				else if(xJ<=0){
//					//System.out.println(xJ + " " + zI);
//					t1[zI][xJ] = (a.substring(0,zI+1).equals(c.substring(0,zI+1)));
//					System.out.println(a.substring(0, zI+1) + " "+ c.substring(0,zI+1));
//					//System.out.println(Arrays.deepToString(t1));
//					continue;
//				}
//				else if(zI<=0){
//					t1[zI][xJ]=b.substring(0, xJ+1).equals(c.substring(0,xJ+1));
//					//System.out.print("here"+zI + " " + xJ);
//					//System.out.println((b.substring(0, xJ).equals(c.substring(0,xJ))));
//					//t1[0][xJ+1] = (b.substring(0, xJ+1).equals(c.substring(cI,cI+xJ+1)));
//					
//					System.out.println(b.substring(0, xJ) + " " + c.substring(0,xJ));
//					//System.out.println(Arrays.deepToString(t1));
//					continue;
//				}
//				else if(a.charAt(zI)==c.charAt(cI) && b.charAt(xJ)==c.charAt(cI)){
//					t1[zI][xJ] = t1[zI+1][xJ] || t1[zI][xJ+1];
//					continue;
//				}
//				else if(a.charAt(zI)==c.charAt(cI)){
//					//System.out.println("a");
//					//System.out.println(zI + " "+xJ);
//					t1[zI][xJ] = t1[zI+1][xJ];
//					//System.out.println(Arrays.deepToString(t1));
//					continue;
//				}
//				else if(b.charAt(xJ)==c.charAt(cI)){
//					System.out.println("b");
//					t1[zI][xJ] = t1[zI][xJ+1];
//					continue;
//				}
//				else{
//					t1[zI][xJ] = false;
//					continue;
//			}}}
//		System.out.println(Arrays.deepToString(t1));
//		return t1[0][0];
//		}
	
//	public static boolean memom(int i, int j, String a, String b, String c){
//		if (c.equals("")){
//			return(a.equals("") && b.equals(""));
//			}
//		if (j<0 && i<0){
//			return(c.equals(""));
//		}
//		int[][] t1 = new int[i+1][j+1];
//		//System.out.print(Arrays.deepToString(t1));
//		for (int z = 0; z<i+1;z++){
//			for(int x= 0; x<j+1;x++){
//				t1[z][x] = -1;
//			}
//		}
//		System.out.print(Arrays.deepToString(t1));
//		return (memomAux(i,j,t1,a,b,c))==1;
//	}
//	public static int memomAux(int i, int j, int[][] t1, String a, String b, String c){
//	
//			
//		
//		if (j<0){
//			
//			boolean r =  (a.substring(0,i+1).equals(c.substring(0,i+1)));
//			t1[i][j] = r? 1:0;
//		}
//		else if (i<0){
//			boolean r = (b.substring(0,j+1).equals(c.substring(0,j+1)));
//			t1[i][j] = r?1:0;
//		}
//		else if (t1[i][j] != -1){
//			return t1[i][j];
//		}
//		else{
//		if (a.charAt(i) == c.charAt(i+j+1) && b.charAt(j) == c.charAt(i+j+1)){
//			t1[i][j] = Math.max(memomAux(i-1,j,t1,a,b,c), memomAux(i,j-1,t1,a,b,c));
//		}
//		else if(a.charAt(i) == c.charAt(i+j+1)){
//			t1[i][j] = memomAux(i-1,j,t1,a,b,c);
//		}
//		else if (b.charAt(j) == c.charAt(i+j+1)){
//			t1[i][j] = memomAux(i,j-1,t1,a,b,c);
//		}
//		else{
//			t1[i][j]=0;
//		}}
//		return t1[i][j];
//		
//	}
	public static void main(String[] args){
		Scanner in = new Scanner(new BufferedReader(new InputStreamReader(System.in)));
		int caseID = 1;
		//change this check for period
		String a = in.next();
		while(a != "."){
			String b = in.next();
			String c = in.next();
			
			boolean res = m(a.length(),b.length(),a,b,c);
			String yn = "no";
			if(res == true){
				yn = "yes";
			}
			System.out.println("Case " + caseID + ": " + yn);
			caseID++;
			a = in.next();
		}
//		String a = args[0];
//		String b = args[1];
//		String c = args[2];
//		int I = a.length()-1;
//		int J = b.length()-1;
//		System.out.print(memom(I,J,a,b,c));
	}

	}
	


