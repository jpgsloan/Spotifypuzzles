import java.io.BufferedReader;
import java.io.InputStreamReader;


public class RevBin {

	int numToRev;
	
	public RevBin() {
		try {
			// Read in number to reverse.
			BufferedReader input = new BufferedReader(new InputStreamReader(System.in));		
			int n = Integer.parseInt(input.readLine());

			// Check that input is in bounds.
			if (n > 1000000000 || n < 1) { 
				throw new Exception();
			} else {
				numToRev = n;
			}
		} catch (Throwable e) {
			System.exit(0);
		}
	}
	
	public int reverse() {
		// Switch to binary string.
		String numAsBin = Integer.toBinaryString(numToRev);

		// Appends characters of binary string to stringbuffer in reverse order.
		StringBuffer reversedBin = new StringBuffer();	
		for (int i = numAsBin.length() - 1; i >= 0; i--) {
			reversedBin.append(numAsBin.charAt(i));
		}
		
		// Switch back to integer.
		int reversedInt = Integer.valueOf(reversedBin.toString(), 2);

		return reversedInt;
	}

	public static void main(String[] args) {
			RevBin reverseBinary = new RevBin();
			System.out.println(reverseBinary.reverse());
	}

}
