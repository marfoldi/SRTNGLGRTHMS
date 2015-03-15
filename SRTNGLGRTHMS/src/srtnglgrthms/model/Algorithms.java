package srtnglgrthms.model;

public class Algorithms {
	private static int[] numbers;
	
	public void bubbleShort(int [] a) {
		for(int i=1; i<a.length; i++) {
		    boolean is_sorted = true;

		    for(int j=0; j<a.length - i; j++) {
		       if(a[j] > a[j+1]) {
		          int temp = a[j];
		          a[j] = a[j+1];
		          a[j+1] = temp;
		         is_sorted = false;
		       }
		    } 

		    if(is_sorted) break;
		}
	}
	
	public static int[] getNumbers() {
		return numbers;
	}

	public static void setNumbers(int[] numbers) {
		Algorithms.numbers = numbers;
	}
}
