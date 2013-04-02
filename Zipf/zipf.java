import java.util.*;
import java.util.regex.Pattern;
import java.io.BufferedReader;
import java.io.InputStreamReader;

/* The zipf class is designed to act like an ArrayList<zipfNode> with 
 * a few extended features. It will automatically fill itself from standard input
 *  and, as well, it now contains a print method that prints the top m nodes. 
 *  The ArrayList is loaded up unordered to achieve constant time. Then sorted at
 *  O(nlogn) and unloaded at constant time.
 *  */
public class zipf {
	
	ArrayList<zipfNode> zipfList;
	int n,m;
	boolean bool = true;
	
	public zipf(){
		this.zipfList = new ArrayList<zipfNode>(50000);
		this.takeInput();
	}
	
	public void takeInput(){ 
		//loads the ArrayList with nodes created from input. The try-catch is used to assure
		//the data is input correctly in a number of ways. It will just catch any exception
		//and then exit.
		
		BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
		
		try {
			String s = input.readLine();
			int space = s.indexOf(' ');
			this.n = Integer.parseInt(s.substring(0, space));
			this.m = Integer.parseInt(s.substring(space + 1));
			//Check to make sure that n and m are within correct bounds.
			if (this.m > this.n || this.m < 1){throw new Exception();}
			if (this.n > 50000 || this.n < 1){throw new Exception();}
			
			//read in n lines.
			int freq; String title = "Astro Lounge";
			for (int n = 1; n <= this.n; n++) {				
				s = input.readLine();
				space = s.indexOf(' ');
				freq = Integer.parseInt(s.substring(0, space));
				title = s.substring(space + 1);
				//Check that freq is in bounds and title is a-z,0-9,_ only between 1-30 characters.
				if (freq < 0 || freq > Math.pow(10,12)){throw new Exception();}	
				if (title.length() > 30 || !Pattern.matches("[a-z0-9_]+", title)){throw new Exception();}
				//make new node and add to arraylist.
				zipfNode pikachu = new zipfNode(freq,n,title);
				this.zipfList.add(pikachu);
			}
			Collections.sort(this.zipfList);
			
		} catch (Throwable e){
			this.bool = false;
		}
	}
	
	public void print(){
		//Unloads m nodes of the tree to output. The stringbuilder creates one large
		//string (of the top m songs) that is then printed all at once.
		StringBuilder AllStar = new StringBuilder();
		zipfNode onyx;
		while (this.m != 1){
			onyx = this.zipfList.remove(zipfList.size() - 1);
			AllStar.append(onyx.title).append("\n");
			this.m-=1;
		}
		onyx = this.zipfList.remove(zipfList.size() - 1);
		AllStar.append(onyx.title);
		System.out.print(AllStar.toString());
	}
	
	public static void main(String[] args) {
		zipf album = new zipf();
		if (album.bool == true){
			album.print();
		}
	}
	
	/* A zipfNode is essentially a song from the album. Each node contains 
	 * its track number(i), song title, and Zipf's Law adjusted play frequencies.
	 */
	private class zipfNode implements Comparable<zipfNode>{
		private int i;
		private int q;
		private String title = "";
		
		public zipfNode (int freq, int i, String song){
			this.title = song;
			this.i = i;
			this.q = freq * i;
		}
		
		public int compareTo(zipfNode other) {
			//compareTo is used to enforce the natural order so that
			//Collections.sort() sorts the nodes by q value and when
			// tied, by their tracklisting. 
			if (other.q > this.q){
				return -1;
			} else if (other.q < this.q){
				return 1;
			} else {
				if (other.i < this.i) {
					return -1;
				} else if (other.i > this.i){
					return 1;
				} else {
					return 0;
				}
			}
		}
	}
}

