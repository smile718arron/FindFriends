package apartmentlist;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

//created by Huiyi Cao

public class Coding_Challenge {
	public static void main(String args[]) throws FileNotFoundException{
		ArrayList<String> words=new ArrayList<>();
		FileReader fr=null;
		BufferedReader br = null;
		String currentWord=null;
		int position=0;
		int lines=0;
		try {
			fr=new FileReader("dictionary.txt");
			br = new BufferedReader(fr);
			currentWord=br.readLine();
			while(currentWord!=null){
				words.add(currentWord);
				lines++;
				if(currentWord.equals("LISTY")){
					position=lines-1;
				}
				currentWord=br.readLine();
			}
			fr.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		long start = System.nanoTime();
		System.out.println(findFriends(words,position));
		long end = System.nanoTime();
		System.out.println("Elapsed " + (end - start) / 1.0e6 + " ms");
	}
	public static int findFriends(ArrayList<String> words,int index){
		int temp=1;
		int max=1;
		Queue<String> nowVisiting=new LinkedList<String>();
		//add a new string to queue
		nowVisiting.add(words.get(index));
		System.out.println(words.get(index));
		//remove the added string
		words.remove(index);
		//bfs
		while(!nowVisiting.isEmpty()){
			String root=nowVisiting.poll();
			for(int i=0;i<words.size();i++){
				if(isDist1(root,words.get(i))){
					nowVisiting.add(words.get(i));
					words.remove(i);
					temp++;
					i--;
					max=Math.max(max, temp);
				}
			}
		}
		return max;
	}
	public static boolean isDist1(String s1,String s2){
		if(s1.length()>s2.length()) return isDist1(s2,s1);
		if(s2.length()-s1.length()>1){
			return false;
		}
		for(int i=0;i<s1.length();i++){
			if(s1.charAt(i)!=s2.charAt(i)){
				//case 0: s2 longer, move that char
				if(s2.length()>s1.length()){
					String removed=s2.substring(0, i)+s2.substring(i+1);
					return s1.equals(removed);
				}
				//case 1: equal length, substitute that char
				if(s2.length()==s1.length()){
					String n1=s1.substring(0, i)+s1.substring(i+1);
					String n2=s2.substring(0, i)+s2.substring(i+1);
					return n1.equals(n2);
				}
			}
		}
		//distance==0
		return s1.length()!=s2.length();
	}
}
