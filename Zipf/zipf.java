import java.util.*;
import java.util.regex.Pattern;
import java.io.BufferedReader;
import java.io.InputStreamReader;

/* The zipf class is designed to act like an ArrayList<zipfNode> with 
 * a few extended features. It will automatically fill itself from standard input
 *  and, as well, it contains a print method that prints the top m nodes (sorted by highest quality value).
 *  The ArrayList is loaded up unordered at and then sorted at
 *  O(n) + O(nlogn) -> O(nlogn). It is then printed to std output at O(m).
 *  */
public class Zipf {
	
	private ArrayList<ZipfNode> zipfList;
	private int n,m;
	private boolean readyToPrint = true;
	
	public Zipf(){
		this.zipfList = new ArrayList<ZipfNode>();
		this.loadListFromInput();
	}
	
	public void loadListFromInput(){
		//loads the ArrayList with nodes created from input. The try-catch is used to assure
		//the input data correct in a number of ways. It will just catch any exception
		//and then exit. It can easily be changed to be more specific with the types of errors thrown.
		
		BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
		
		try {
			//Take in first line to set n and m
			String newLine = input.readLine();
			int spaceIndex = newLine.indexOf(' ');
			this.n = Integer.parseInt(newLine.substring(0, spaceIndex));
			this.m = Integer.parseInt(newLine.substring(spaceIndex + 1));
			
			//Check to make sure that n and m are within correct bounds.
			if (this.m > this.n || this.m < 1){throw new Exception();}
			if (this.n > 50000 || this.n < 1){throw new Exception();}
			
			//read in n lines.
			Double freq;
			String title = "";
			for (int i = 1; i <= this.n; i++) {								
				//Split line of input
				newLine = input.readLine();
				spaceIndex = newLine.indexOf(' ');
				freq = Double.parseDouble(newLine.substring(0, spaceIndex));
				title = newLine.substring(spaceIndex + 1);
				
                //Check that freq is in bounds and title is a-z,0-9,_ only between 1-30 characters.
				if (freq < 0 || freq > Math.pow(10,12)){throw new Exception();}	
				if (title.length() > 30 || !Pattern.matches("[a-z0-9_]+", title)){throw new Exception();}
				
				//make new node and add to arraylist.
				ZipfNode newNode = new ZipfNode(freq,i,title);
				this.zipfList.add(newNode);
			}
			
			//Sorts the zipfList according to custom compareTo()
			Collections.sort(this.zipfList);
			
		} catch (Throwable e){
			this.readyToPrint = false;
            System.out.println("input data is incorrect!");
		}
	}
	
	public void print(){
		//Unloads top m nodes of zipfList to output and prints to std output.
		StringBuilder strBuilder = new StringBuilder();
		ZipfNode node;
        for (int i = 0; i < this.m; i++){
            node = this.zipfList.get(this.zipfList.size() - i - 1);
			strBuilder.append(node.title).append("\n");
		}
		System.out.print(strBuilder.toString());
	}
	
	public static void main(String[] args) {
		Zipf album = new Zipf();
		if (album.readyToPrint == true){
			album.print();
		}
	}
	
	/* A zipfNode is essentially a song from the album. Each node contains 
	 * its track number (i), song title, and Zipf's Law adjusted play frequency (quality).
	 */
	private class ZipfNode implements Comparable<ZipfNode>{
		private int i;
		private Double quality;
		private String title = "";
		
		public ZipfNode (Double freq, int i, String song){
			this.title = song;
			this.i = i;
			this.quality = freq * i;
		}
		
		public int compareTo(ZipfNode other) {
			// Sorts nodes by highest quality value and when
			// tied, by lowest track number.
			if (other.quality > this.quality){
				return -1;
			} else if (other.quality < this.quality){
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

