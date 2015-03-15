package srtnglgrthms.model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javafx.collections.ObservableList;
import javafx.scene.chart.XYChart;
import javafx.scene.Node;

public class ForwardRadix {	
	private static int digitNumber = getMaxDigit();
	private static int begin = 0;
	private static int end = Algorithms.getNumbers().length;
	private static int actualDigit = 0;
	private static int lower = begin;
	private static int upper = end;
	private static int stepNumber = 0;
	private static List<Integer> recursiveCall = new ArrayList<>();
	
	private static int getMaxDigit() {
		return Arrays.stream(Algorithms.getNumbers())
				.map(n -> Integer.toBinaryString(n).length())
				.max()
				.getAsInt();
	}
	
	private static String fillWithZeros(String binaryNumber) {
	       StringBuilder builder = new StringBuilder();
	        while (builder.length() < digitNumber-binaryNumber.length()) {
	            builder.append('0');
	        }
	        builder.append(binaryNumber);
	        return builder.toString();
	}
	
	public static void RadixStep (ObservableList<XYChart.Data<String,Integer>> data) {
		if(actualDigit < digitNumber) {
			while(lower < end && 
					fillWithZeros(Integer.toBinaryString(data.get(lower).getYValue())).charAt(actualDigit) == '0') ++lower;
			while(lower < upper && 
					fillWithZeros(Integer.toBinaryString(data.get(upper - 1).getYValue())).charAt(actualDigit) == '1') --upper;
			if (lower < upper - 1) {
			Node node = data.get(lower).getNode();
			node.setStyle("-fx-bar-fill: navy;");
			node = data.get(upper - 1).getNode();
			node.setStyle("-fx-bar-fill: navy;");
			/*System.out.println("ALSO:" + fillWithZeros(Integer.toBinaryString(data.get(lower).getYValue())));
			System.out.println("ALSOSZAMA:" + fillWithZeros(Integer.toBinaryString(data.get(lower).getYValue())).charAt(actualDigit));
			System.out.println("FELSO:" + fillWithZeros(Integer.toBinaryString(data.get(upper - 1).getYValue())));
			System.out.println("FELSZOSZAMA:" + fillWithZeros(Integer.toBinaryString(data.get(upper - 1).getYValue())).charAt(actualDigit));
			System.out.println("JEGY:" + actualDigit);*/
			int temp = data.get(lower).getYValue();
			data.get(lower).setYValue(data.get(upper - 1).getYValue());
			data.get(upper - 1).setYValue(temp);
			//--upper;
			//++lower;
			}
		else {
			for(int i=begin; i<lower; ++i) {
				Node node = data.get(i).getNode();
				node.setStyle("-fx-bar-fill: #c62b00;");
			}
			for(int i=upper; i<end; ++i) {
				Node node = data.get(i).getNode();
				node.setStyle("-fx-bar-fill: #a9e200;");
			}
			recursiveCall.add(begin);
			recursiveCall.add(upper);
			recursiveCall.add(lower);
			recursiveCall.add(end);
			++actualDigit; //ez nem jó...
			begin=recursiveCall.get(stepNumber);
			end=recursiveCall.get(stepNumber+1);
			lower=begin;
			upper=end;
			stepNumber+=2;
		}
		}
	}
}
