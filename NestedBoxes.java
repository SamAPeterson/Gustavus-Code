import java.util.*;
import java.io.*;
public class NestedBoxes{
	public static Box[] boxList;
	public static int[] d;


	public static class Box{
		private int[] dimen;
		private int boxNum;
		private ArrayList neighbors;
		public Box(int[] dim, int num, ArrayList neigh){
			int[] dimen = dim;
			int boxNum = num;
			ArrayList neighbors=neigh;
		}
	}

	

	public static boolean fits(Box box1,Box box2){
		
		Arrays.sort(box1.dimen);
		Arrays.sort(box2.dimen);
		return (box1.dimen[0] < box2.dimen[0] && box1.dimen[1] < box2.dimen[1] && box1.dimen[2] < box2.dimen[2]);
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
		while (true){
			int numberBoxes = sc.nextInt();
			if (numberBoxes == -1){
				break;
			}

			else{
				boxList = new Box[numberBoxes];
				for (int i=0; i<numberBoxes;i++){
					int dim1 = sc.nextInt();
					//System.out.println(dim1);
					int dim2 = sc.nextInt();
					int dim3 = sc.nextInt();
					 
					int[] dim4 = {dim1,dim2,dim3};
					System.out.println(Arrays.toString(dim4));
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
				System.out.print(larg+1);


			}
		}
	}
}