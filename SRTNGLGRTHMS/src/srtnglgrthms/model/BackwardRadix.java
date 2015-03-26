package srtnglgrthms.model;

import java.util.LinkedList;

import javafx.collections.ObservableList;
import javafx.scene.chart.XYChart;

public class BackwardRadix extends Radix{	
	private static int i = 0;
	private static int actualSeries = 0;
	private static String direction;
	private static ObservableList<XYChart.Data<String,Integer>> data2;
	
	private BackwardRadix() {
		init();
	}
	
	private static class SortHolder {
        private static final BackwardRadix INSTANCE = new BackwardRadix();
    }
	
    public static BackwardRadix getInstance() {
        return SortHolder.INSTANCE;
    }
    
    private static void init() {
		actualDigit = getMaxDigit()-1;
		begin = 0;
		end = SortingAlgorithm.getNumbers().length-1;
		lower = begin;
		upper = end;
		recursiveCall = new LinkedList<>();
		direction = "forward";
    }
	
	private static boolean checkEmpty(ObservableList<XYChart.Data<String,Integer>> data){
		for(int i=0; i<SortingAlgorithm.getNumbers().length; ++i) {
			if(data.get(i).getYValue()!=0) {
				return false;
			}
		}
		return true;
	}
	
	@Override
	public void step() {
		if(actualDigit > 0) {
			//BEGIN END csere van ha visszafelé megy, tehát nem megy bele az if-be...
			if(lower<=upper && i<=end && i>=begin && begin<=end) {
				if(actualSeries%2==0) {
					if(fillWithZeros(Integer.toBinaryString(data.get(i).getYValue())).charAt(actualDigit) == '0') {
						data2.get(lower).setYValue(data.get(i).getYValue());
						lower++;
					}
					else if (fillWithZeros(Integer.toBinaryString(data.get(i).getYValue())).charAt(actualDigit) == '1') {
						data2.get(upper).setYValue(data.get(i).getYValue());
						upper--;
					}
					data.get(i).setYValue(0);
				}
				else {
					if(fillWithZeros(Integer.toBinaryString(data2.get(i).getYValue())).charAt(actualDigit) == '0') {
						data.get(lower).setYValue(data2.get(i).getYValue());
						lower++;
					}
					if (fillWithZeros(Integer.toBinaryString(data2.get(i).getYValue())).charAt(actualDigit) == '1') {
						data.get(upper).setYValue(data2.get(i).getYValue());
						upper--;
					}
					data2.get(i).setYValue(0);
				}
				if(direction == "forward") i++;
				else i--;
			}
			else {
				recursiveCall.add(new RecursiveParameter(0, lower-1, actualDigit-1, "forward"));
				recursiveCall.add(new RecursiveParameter(lower, SortingAlgorithm.getNumbers().length - 1, actualDigit-1, "backward"));
				RecursiveParameter nextParameters = recursiveCall.remove();
				begin=nextParameters.getBegin();
				end=nextParameters.getEnd();
				actualDigit=nextParameters.getDigit();
				direction=nextParameters.getDirection();
				if(direction == "forward") i=begin;
				else i=end;
				if(checkEmpty(data) || checkEmpty(data2)) {
					actualSeries++;
					lower = 0;
					upper = SortingAlgorithm.getNumbers().length - 1;
					if(direction == "forward") i=begin;
					else i=end;
				}
				System.out.println(actualDigit);
			}
		}
	}
	
	public static void setData2(ObservableList<XYChart.Data<String,Integer>> data)
	{
		BackwardRadix.data2=data;
	}

	@Override
	public void setDefaults() {
		// TODO Auto-generated method stub
		
	}
}
