package srtnglgrthms.model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javafx.collections.ObservableList;
import javafx.scene.chart.XYChart;

public class BackwardRadix {	
	private static int digitNumber = getMaxDigit();
	private static int begin = 0;
	private static int end = Algorithms.getNumbers().length - 1;
	private static int actualDigit = digitNumber-1;
	private static int lower = begin;
	private static int upper = end;
	private static int stepNumber = 0;
	private static List<Integer> recursiveCall = new ArrayList<>();
	private static int i = 0;
	private static int actualSeries = 0;
	
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
	
	private static boolean checkEmpty(ObservableList<XYChart.Data<String,Integer>> data){
		for(int i=0; i<Algorithms.getNumbers().length; ++i) {
			if(data.get(i).getYValue()!=0) {
				return true;
			}
		}
		return false;
	}
	
	public static void RadixStep (ObservableList<XYChart.Data<String,Integer>> data1, ObservableList<XYChart.Data<String,Integer>> data2) {
		if(actualDigit > 0) {
			//BEGIN END csere van ha visszafelé megy, tehát nem megy bele az if-be...
			if(i>=begin && i<=end) {
				if(actualSeries%2==0) {
					System.out.println(fillWithZeros(Integer.toBinaryString(data1.get(i).getYValue())));
					System.out.println(actualDigit);
					if(fillWithZeros(Integer.toBinaryString(data1.get(i).getYValue())).charAt(actualDigit) == '0') {
						data2.get(lower).setYValue(data1.get(i).getYValue());
						lower++;
					}
					if (fillWithZeros(Integer.toBinaryString(data1.get(i).getYValue())).charAt(actualDigit) == '1') {
						data2.get(upper).setYValue(data1.get(i).getYValue());
						upper--;
					}
					data1.get(i).setYValue(0);
				}
				else {
					System.out.println(fillWithZeros(Integer.toBinaryString(data2.get(i).getYValue())));
					System.out.println(actualDigit);
					if(fillWithZeros(Integer.toBinaryString(data2.get(i).getYValue())).charAt(actualDigit) == '0') {
						data1.get(lower).setYValue(data2.get(i).getYValue());
						lower++;
					}
					if (fillWithZeros(Integer.toBinaryString(data2.get(i).getYValue())).charAt(actualDigit) == '1') {
						data1.get(upper).setYValue(data2.get(i).getYValue());
						upper--;
					}
					data2.get(i).setYValue(0);
				}
				if(begin<end) i++;
				if(begin>end) i--;
			}
			else {
				recursiveCall.add(begin);
				recursiveCall.add(lower-1);
				recursiveCall.add(end);
				recursiveCall.add(upper+1);
				begin=recursiveCall.get(stepNumber);
				System.out.println(begin);
				end=recursiveCall.get(stepNumber+1);
				System.out.println(end);
				i = begin;
				if(actualSeries%2==0) {
					lower = 0;
					upper = Algorithms.getNumbers().length - 1;
				}
				if(checkEmpty(data1) || checkEmpty(data2)) {
					actualSeries++;
				}
				actualDigit--;
				if(begin<end) i=0;
				else i=Algorithms.getNumbers().length - 1;
				stepNumber+=2;
			}
		}
		else {
			for(int i=0; i<Algorithms.getNumbers().length; ++i) {
				System.out.println(fillWithZeros(Integer.toBinaryString(data1.get(i).getYValue())));
			}
		}
	}
}
