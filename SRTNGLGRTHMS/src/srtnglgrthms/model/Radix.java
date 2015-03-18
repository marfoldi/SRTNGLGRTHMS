package srtnglgrthms.model;

import java.util.Arrays;
import java.util.Queue;

public abstract class Radix extends SortingAlgorithm {
	protected static int actualDigit;
	protected static int begin = 0;
	protected static int end;
	protected static int lower;
	protected static int upper;
	
	protected static int getMaxDigit() {
		return Arrays.stream(SortingAlgorithm.getNumbers())
				.map(n -> Integer.toBinaryString(n).length())
				.max()
				.getAsInt();
	}
	
	protected static String fillWithZeros(String binaryNumber) {
	       StringBuilder builder = new StringBuilder();
	        while (builder.length() < getMaxDigit()-binaryNumber.length()) {
	            builder.append('0');
	        }
	        builder.append(binaryNumber);
	        return builder.toString();
	}
}
