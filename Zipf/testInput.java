
public class testInput {
	
	public double factorial(double i){
		double sum = 1;
		while (i!=0){
			sum = sum * i;
			i--;
		}
		return sum;
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		testInput test = new testInput();
		//System.out.print(test.factorial(100));
		for (int i = 1; i<=10; i+=2){
			System.out.println(0 + " " + "song" + i);
			System.out.println(test.factorial(10)/(i+1) + " " + "song" + (i+1));
		}
	}
/* 0 song1
1814400 song2
0 song3
907200 song4
0 song5
604800 song6
0 song7
453600 song8
0 song9
362880 song10
 * */
}
