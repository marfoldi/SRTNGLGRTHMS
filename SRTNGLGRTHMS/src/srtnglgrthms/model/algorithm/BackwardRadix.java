package srtnglgrthms.model.algorithm;

import java.util.LinkedList;

import srtnglgrthms.controller.OverviewChartController;
import srtnglgrthms.controller.OverviewDoubleChartController;
import srtnglgrthms.model.RecursiveParameter;
import javafx.collections.ObservableList;
import javafx.scene.chart.XYChart.Data;

public class BackwardRadix extends RadixAlgorithm{	
	private static int i;
	private static int actualSeries;
	private static String direction;
	private static ObservableList<Data<String, Number>> listOne;
	private static ObservableList<Data<String, Number>> listTwo;
	
	private BackwardRadix() {}
	
	private static class SortHolder {
        private static final BackwardRadix INSTANCE = new BackwardRadix();
    }
	
    public static BackwardRadix getInstance() {
        return SortHolder.INSTANCE;
    }
    
    @Override
    public void setDefaults() {
    	listOne = OverviewDoubleChartController.getListOne();
    	listTwo = OverviewDoubleChartController.getListTwo();
    	actualSeries = 0;
		actualDigit = getMaxDigit()-1;
		begin = 0;
		i=0;
		end = numbers.length-1;
		lower = begin;
		upper = end;
		recursiveCall = new LinkedList<>();
		direction = "forward";
    }
	
	@Override
	public void step() {
		if(actualDigit >= 0) {
			if(lower<=upper && i<=end && i>=begin && begin<=end) {
				if(actualSeries%2==0) {
					if(fillWithZeros(Integer.toBinaryString((int) listOne.get(i).getYValue())).charAt(actualDigit) == '0') {
						listTwo.get(lower).setYValue((Integer) listOne.get(i).getYValue());
						OverviewChartController.setColor(listTwo.get(lower).getNode(), "swap");
						lower++;
					}
					else if (fillWithZeros(Integer.toBinaryString((int) listOne.get(i).getYValue())).charAt(actualDigit) == '1') {
						listTwo.get(upper).setYValue((Integer) listOne.get(i).getYValue());
						OverviewChartController.setColor(listTwo.get(upper).getNode(), "select");
						upper--;
					}
					listOne.get(i).setYValue(0);
				}
				else {
					if(fillWithZeros(Integer.toBinaryString((int) listTwo.get(i).getYValue())).charAt(actualDigit) == '0') {
						listOne.get(lower).setYValue(listTwo.get(i).getYValue());
						OverviewChartController.setColor(listOne.get(lower).getNode(), "swap");
						lower++;
					}
					if (fillWithZeros(Integer.toBinaryString((int) listTwo.get(i).getYValue())).charAt(actualDigit) == '1') {
						listOne.get(upper).setYValue(listTwo.get(i).getYValue());
						OverviewChartController.setColor(listOne.get(upper).getNode(), "select");
						upper--;
					}
					listTwo.get(i).setYValue(0);
				}
				if(direction == "forward") i++;
				else i--;
			}
			else {
				if (recursiveCall.isEmpty()) {
					recursiveCall.add(new RecursiveParameter(0, lower-1, actualDigit-1, "forward"));
					recursiveCall.add(new RecursiveParameter(lower, numbers.length - 1, actualDigit-1, "backward"));
				}
				if(!recursiveCall.isEmpty()) {
					RecursiveParameter nextParameters = recursiveCall.remove();
					direction=nextParameters.getStringParameter();
					if(direction == "forward") {
						actualSeries++;
						begin = 0;
						end = (int) nextParameters.getSecondParameter();
						lower = begin;
						upper = numbers.length-1;
						actualDigit =(int) nextParameters.getThirdParameter();
						i=begin;
					}
					else {
						begin=(int) nextParameters.getFirstParameter();
						end = numbers.length-1;
						actualDigit =(int) nextParameters.getThirdParameter();
						i=end;
					}
			}
			}
		}
		else {
			if(i<=end) {
				if(actualSeries%2==0) {
					listTwo.get(i).setYValue(listOne.get(i).getYValue());
					listOne.get(i).setYValue(0);
					OverviewChartController.setColor(listTwo.get(i).getNode(), "done");
				}
				else {
					listOne.get(i).setYValue(listTwo.get(i).getYValue());
					listTwo.get(i).setYValue(0);
					OverviewChartController.setColor(listOne.get(i).getNode(), "done");
				}
				i++;
				return;
			}
			if(i>end && i<numbers.length) {
				if(actualSeries%2==0) {
					listTwo.get(i).setYValue(listOne.get(upper).getYValue());
					listOne.get(upper).setYValue(0);
					OverviewChartController.setColor(listTwo.get(i).getNode(), "done");
				}
				else {
					listOne.get(i).setYValue(listTwo.get(upper).getYValue());
					listTwo.get(upper).setYValue(0);
					OverviewChartController.setColor(listOne.get(i).getNode(), "done");
				}
				i++;
				upper--;
				return;
			}
			else {
				for(int i=0; i<numbers.length; ++i) {
					OverviewChartController.setColor(data.get(i).getNode(), "done");
				}
			}
		}
	}
}
