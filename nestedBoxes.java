import java.util.*;

import java.io.*;

public class nestedBoxes{
	public static Box[] boxList;
	public static int[] d;

	private static class Box{
		private ArrayList<Integer> dimen;
		private int boxNum;
		private ArrayList<Box> neighbors;

		public Box(ArrayList dim, int num, ArrayList neigh){
			dimen = dim;
			boxNum = num;
			neighbors=neigh;
		}
}

	public static boolean fits(Box box1,Box box2){
		Collections.sort(box1.dimen);
		Collections.sort(box2.dimen);
		return (box1.dimen.get(0) < box2.dimen.get(0) && box1.dimen.get(1) < box2.dimen.get(1) && box1.dimen.get(2) < box2.dimen.get(2));
	}

	public static void makeGraph(Box[] ba){
		for (int i = 0; i<ba.length;i++){
			for (int j=0; j<ba.length;j++){
				if (fits(ba[i],ba[j])){
					ba[i].neighbors.add(ba[j]);
				}
			}
		}
	}

	public static void dfs(Box v){
		d[v.boxNum]=0;
		for (int i=0; i<((v.neighbors).size());i++){
			if (d[((Box)v.neighbors.get(i)).boxNum]==-1){
				dfs((Box)v.neighbors.get(i));
			}
			d[v.boxNum]=Math.max(d[v.boxNum], 1 + d[((Box)v.neighbors.get(i)).boxNum]);
		}
	}

	public static void main(String[] args){
		Scanner sc = new Scanner(new BufferedReader(new InputStreamReader(System.in)));
		int caseID = 0;
		int numberBoxes = sc.nextInt();
		while (true){
		if (numberBoxes == -1){
			break;
		}
		else{
			boxList = new Box[numberBoxes];
			for (int i=0; i<numberBoxes;i++){
				int dim1 = sc.nextInt();
				int dim2 = sc.nextInt();
				int dim3 = sc.nextInt();
				ArrayList dim4 = new ArrayList();
				dim4.add(dim1);
				dim4.add(dim2);
				dim4.add(dim3);
				ArrayList al = new ArrayList();
				Box newBox = new Box(dim4, i, al);
				boxList[i]=newBox;
			}
			makeGraph(boxList);
			d = new int[numberBoxes];
			for (int i=0; i<numberBoxes;i++){
				d[i] = -1;
			}

			for (int i=0; i<numberBoxes;i++){
				if (d[i] == -1){
					dfs(boxList[i]);
			}
		}
		int larg = 0;
		for(int i=0; i<d.length;i++){
			if (d[i]>larg){
				larg=d[i];
			}
		}
		caseID++;
		numberBoxes = sc.nextInt();
		if (numberBoxes == -1){
			if(larg==0){
			System.out.print("Case " + (caseID) + ": "+ (larg+1) + " box");
		}
			else{
				System.out.print("Case " + (caseID) + ": "+ (larg+1) + " boxes");
			}
			break;
		}
		if(larg==0){
			System.out.println("Case " + (caseID) + ": "+ (larg+1) + " box");
		}
		else{
			System.out.println("Case " + (caseID) + ": "+ (larg+1) + " boxes");
		}


	}
	}
}
}

