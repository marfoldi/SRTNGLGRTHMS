package srtnglgrthms.model.algorithm;

import java.util.LinkedList;

import srtnglgrthms.controller.OverviewDoubleChartController;
import srtnglgrthms.model.RecursiveParameter;
import javafx.collections.ObservableList;
import javafx.scene.chart.XYChart;
import javafx.scene.chart.XYChart.Data;

public class BackwardRadix extends RadixAlgorithm{	
	private static int i = 0;
	private static int actualSeries = 0;
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
		actualDigit = getMaxDigit()-1;
		begin = 0;
		end = numbers.length-1;
		lower = begin;
		upper = end;
		recursiveCall = new LinkedList<>();
		direction = "forward";
    }
	
	@Override
	public void step() {
		if(actualDigit >= 0) {
			//BEGIN END csere van ha visszafelé megy, tehát nem megy bele az if-be...
			if(lower<=upper && i<=end && i>=begin && begin<=end) {
				if(actualSeries%2==0) {
					if(fillWithZeros(Integer.toBinaryString((int) listOne.get(i).getYValue())).charAt(actualDigit) == '0') {
						listTwo.get(lower).setYValue((Integer) listOne.get(i).getYValue());
						lower++;
					}
					else if (fillWithZeros(Integer.toBinaryString((int) listOne.get(i).getYValue())).charAt(actualDigit) == '1') {
						listTwo.get(upper).setYValue((Integer) listOne.get(i).getYValue());
						upper--;
					}
					listOne.get(i).setYValue(0);
				}
				else {
					if(fillWithZeros(Integer.toBinaryString((int) listTwo.get(i).getYValue())).charAt(actualDigit) == '0') {
						listOne.get(lower).setYValue(listTwo.get(i).getYValue());
						lower++;
					}
					if (fillWithZeros(Integer.toBinaryString((int) listTwo.get(i).getYValue())).charAt(actualDigit) == '1') {
						listOne.get(upper).setYValue(listTwo.get(i).getYValue());
						upper--;
					}
					listTwo.get(i).setYValue(0);
				}
				if(direction == "forward") i++;
				else i--;
			}
			else {
				//attól függõen, hogy mi volt az utolsó lépés kell beállítani a határt nem jó a lower-1...
				//az elseben csak akkor kell hozzáadni új hívást, ha már mindkettõ lement...
				if (recursiveCall.isEmpty() && actualDigit!=0) {
				recursiveCall.add(new RecursiveParameter(0, lower-1, actualDigit-1, "forward"));
				recursiveCall.add(new RecursiveParameter(lower, numbers.length - 1, actualDigit-1, "backward"));
				}
				if(!recursiveCall.isEmpty()) {
				RecursiveParameter nextParameters = recursiveCall.remove();
				begin=(int) nextParameters.getFirstParameter();
				end=(int) nextParameters.getSecondParameter();
				actualDigit=(int) nextParameters.getThirdParameter();
				direction=nextParameters.getStringParameter();
				if(direction == "forward") i=begin;
				else i=end;
				if(direction == "forward") {
					actualSeries++;
					begin=0;
					end = numbers.length - 1;
					lower = begin;
					upper = end;
					if(direction == "forward") i=begin;
					else i=end;
				}
			}
			}
		}
	}
}
