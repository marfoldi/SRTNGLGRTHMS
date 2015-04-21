package srtnglgrthms.model.algorithm;

import java.util.Arrays;

/**
 * 
 * @author <a href="mailto:marfoldi@caesar.elte.hu">Márföldi Péter Bence</a>
 */
public abstract class RadixAlgorithm extends ChartAlgorithm {
	protected static int actualDigit;
	protected static int begin = 0;
	protected static int end;
	protected static int lower;
	protected static int upper;
	
	protected static int getMaxDigit() {
		return Arrays.stream(numbers)
				.map(n -> Integer.toBinaryString(n).length())
				.max()
				.getAsInt();
	}
	
	public static String fillWithZeros(String binaryNumber) {
	       StringBuilder builder = new StringBuilder();
	        while (builder.length() < getMaxDigit()-binaryNumber.length()) {
	            builder.append('0');
	        }
	        builder.append(binaryNumber);
	        return builder.toString();
	}
}
