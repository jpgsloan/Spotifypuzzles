import java.io.BufferedReader;
import java.io.InputStreamReader;


public class RevBin {

	String batman;
	
	public RevBin(){
		try {
			//Take input.
			BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
			String s = input.readLine();		
			int x = Integer.parseInt(s);
			//Check to make sure input is in bounds.
			if (x > 1000000000 || x < 1) {throw new Exception();}
			//Change input from decimal to binary.
			batman = Integer.toBinaryString(x);
		} catch (Throwable e){
			System.exit(0);
		}
	}
	
	public String reverse(String tmp){
		//Appends characters of binary string in reverse order to stringbuffer
		StringBuffer robin = new StringBuffer();	
		for (int i = tmp.length() - 1; i>=0; i = i-1){
			robin.append(tmp.charAt(i));
		}
		return robin.toString();
	}

	public static void main(String[] args) {
			RevBin hey = new RevBin();
			System.out.print(Integer.valueOf(hey.reverse(hey.batman),2));
	}

}
