package srtnglgrthms.model.algorithm;

import java.util.LinkedList;

import srtnglgrthms.controller.OverviewChartController;
import srtnglgrthms.model.CounterData;
import srtnglgrthms.model.RecursiveParameter;

public class ForwardRadix extends RadixAlgorithm {
	private static boolean lowerFound;
	private static boolean upperFound;
	private static boolean colored;
	
	private ForwardRadix() {}
	
	private static class SortHolder {
        private static final ForwardRadix INSTANCE = new ForwardRadix();
    }
	
    public static ForwardRadix getInstance() {
        return SortHolder.INSTANCE;
    }

	@Override
	public void setDefaults() {
		actualDigit = 0;
		begin = 0;
		end = numbers.length-1;
		lower = begin;
		upper = end;
		lowerFound = false;
		upperFound = false;
		colored = false;
		recursiveCall = new LinkedList<>();
		counterData.clear();
		counterData.add(new CounterData("Vizsgálatok", "0"));
		counterData.add(new CounterData("Cserék", "0"));
		counterData.add(new CounterData("Aktuálsi bit", Integer.toString(actualDigit+1)));
	}

	@Override
	public void step() {
		if(actualDigit < getMaxDigit()) {
			if(lower<=upper) {
				if(!colored) {
					OverviewChartController.setColor(data.get(lower).getNode(), "select");
					OverviewChartController.setColor(data.get(upper).getNode(), "select");
					colored=true;
					return;
				}
				if(!lowerFound) {
					counterData.get(0).incValue();
				if(lower <= upper &&
						fillWithZeros(Integer.toBinaryString((int) data.get(lower).getYValue())).charAt(actualDigit) == '0') {
					OverviewChartController.setColor(data.get(lower).getNode(), "fade");
					++lower;
					if(lower<=upper) {
						if(lower<data.size()) OverviewChartController.setColor(data.get(lower).getNode(), "select");
					}
					//else counterData.get(0).decValue();
					return;
				}
				else {
					OverviewChartController.setColor(data.get(lower).getNode(), "swap");
					lowerFound=true;
					return;
				}
				}
				if(!upperFound) {
					counterData.get(0).incValue();
				if(lower <= upper &&
						fillWithZeros(Integer.toBinaryString((int) data.get(upper).getYValue())).charAt(actualDigit) == '1') {	
						OverviewChartController.setColor(data.get(upper).getNode(), "fade");	
						--upper;
						if(upper>=lower) {
							if(upper>0) OverviewChartController.setColor(data.get(upper).getNode(), "select");
						}
						else counterData.get(0).decValue();
						return;
				}
				else {
					OverviewChartController.setColor(data.get(upper).getNode(), "swap");
					upperFound = true;
					return;
				}
				}
				if (lower <= upper) {
					counterData.get(1).incValue();
					swap(lower, upper);
					OverviewChartController.setColor(data.get(lower).getNode(), "fade");
					OverviewChartController.setColor(data.get(upper).getNode(), "fade");
					lowerFound=false;
					upperFound = false;
					colored=false;
					lower++;
					upper--;
				}
				else step();
			}
			else {
				setBucketColor(begin ,lower);
				setBucketColor(lower, end+1);
				//if(begin!=lower-1 && begin<lower-1) {
					recursiveCall.add(new RecursiveParameter(begin, lower-1, actualDigit+1, null));
				//}
				//if(lower!=end && lower<end) {
					recursiveCall.add(new RecursiveParameter(lower, end, actualDigit+1, null));
				//}
				if(!recursiveCall.isEmpty()) {
					RecursiveParameter nextParameters = recursiveCall.remove();
					begin=(int) nextParameters.getFirstParameter();
					end=(int) nextParameters.getSecondParameter();
					actualDigit=(int) nextParameters.getThirdParameter();
					lower=begin;
					upper=end;
					lowerFound=false;
					upperFound = false;
					colored=false;
					if(actualDigit<getMaxDigit()) counterData.get(2).setValue(Integer.toString(actualDigit+1));
					else counterData.get(2).setValue(Integer.toString(getMaxDigit()));
				}
				else {
					for (int i = 0; i < data.size(); i++) {
	            		OverviewChartController.setColor(data.get(i).getNode(), "done");
	        		}
				}
			}
		}
		else {
			for (int i = 0; i < data.size(); i++) {
        		OverviewChartController.setColor(data.get(i).getNode(), "done");
    		}
		}
	}
	
	private static void setBucketColor(int lower, int upper) {
		String bucketColor = OverviewChartController.getRandomColor();
		for (int i = lower; i < upper; i++) {
			OverviewChartController.setColor(data.get(i).getNode(), bucketColor);
		}
	}
}
