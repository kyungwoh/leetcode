import java.util.ArrayDeque;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Scanner;
import java.util.TreeSet;

public class CowsAndFoldersDropbox{

	public static void main(String[] args) {
		String input = "3\r\n" + 
				"2 1\r\n" + 
				"1 1 0\r\n" + 
				"2 1 1\r\n" + 
				"3 3 0 1 2\r\n" + 
				"2\r\n" + 
				"1 2\r\n" + 
				"1 3\r\n" + 
				"";
		Scanner scan = new Scanner(input);
		int q = scan.nextInt();
		int m = scan.nextInt();
		int n = scan.nextInt();
		HashMap<Integer,Folder> folderMap = new HashMap<Integer,Folder>();
		HashSet<Integer> folderSet = new HashSet<Integer>();
		for(int i=0; i<m; i++) {
			int id = scan.nextInt();
			Folder f = new Folder(id, true);
			int k = scan.nextInt();
			for(int j=0; j<k; j++) f.cowSet.add(scan.nextInt());
			folderMap.put(id, f);
			folderSet.add(id);
		}
		for(int i=0; i<n; i++) {
			int id = scan.nextInt();
			Folder f = new Folder(id, false);
			int k = scan.nextInt();
			for(int j=0; j<k; j++) f.cowSet.add(scan.nextInt());
			folderMap.put(id, f);
			folderSet.add(id);
		}
		
		//System.out.println(folderMap);
		//System.out.println(folderSet);
		
		int g = scan.nextInt();
		HashSet<Integer> leafSet = (HashSet<Integer>) folderSet.clone();
		HashSet<Integer> rootSet = (HashSet<Integer>) folderSet.clone();
		for(int i=0; i<g; i++) {
			int u = scan.nextInt();
			int v = scan.nextInt();
			folderMap.get(u).childSet.add(v);
			folderMap.get(v).parent = u;
			leafSet.remove(u);
			rootSet.remove(v);
		}
		scan.close();
		
		//System.out.println(leafSet);
		//System.out.println(rootSet);
		
		//Interit permissions (only if it is shared)
		ArrayDeque<Integer> deque = new ArrayDeque<Integer>();
		for(int r : rootSet) deque.offer(r);
		while(!deque.isEmpty()) {
			Folder f = folderMap.get(deque.poll());
			if(f.isShared) {
				for(int c : f.childSet) {
					Folder fc = folderMap.get(c);
					if(fc.isShared) {
						fc.cowSet.addAll(f.cowSet);
						if(!fc.childSet.isEmpty()) deque.offer(fc.id);
					}
				}
			}
		}
		//System.out.println(folderMap);
		
		//Find uncool cows
		TreeSet<Integer> resultSet = new TreeSet<Integer>();
		for(int l : leafSet) {
			Folder f = folderMap.get(l);
			//System.out.println(f);
			for(int i=0; i<q; i++) {
				if(!f.cowSet.contains(i)) resultSet.add(i);
			}
		}
		
		for(int r : resultSet) System.out.print(r+" ");
		System.out.println();
		//Expected: 2
	}
}

class Folder{
	int id, parent;
	HashSet<Integer> childSet;
	boolean isShared;
	HashSet<Integer> cowSet;
	Folder(int id, boolean isShared){
		this.id = id;
		this.isShared = isShared;
		childSet = new HashSet<Integer>();
		cowSet = new HashSet<Integer>();
	}
/*	public String toString() {
		return "("+id+","+parent+","+isShared+","+cowSet.toString()+","+childSet.toString()+")";
	}*/
}
