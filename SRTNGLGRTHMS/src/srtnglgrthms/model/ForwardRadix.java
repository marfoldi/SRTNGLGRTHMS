package srtnglgrthms.model;

import java.util.LinkedList;

import srtnglgrthms.controller.BarChartController;

public class ForwardRadix extends Radix {
	private ForwardRadix() {
		init();
	}
	
	private static class SortHolder {
        private static final ForwardRadix INSTANCE = new ForwardRadix();
    }
	
    public static ForwardRadix getInstance() {
        return SortHolder.INSTANCE;
    }
    
    private static void init() {
		actualDigit = 0;
		begin = 0;
		end = SortingAlgorithm.getNumbers().length-1;
		lower = begin;
		upper = end;
		recursiveCall = new LinkedList<>();
    }

	@Override
	public void step() {
		if(actualDigit < getMaxDigit()) {
			if(lower<=upper) {
			while(lower <= upper &&
					fillWithZeros(Integer.toBinaryString(data.get(lower).getYValue())).charAt(actualDigit) == '0') ++lower;
			while(lower <= upper &&
					fillWithZeros(Integer.toBinaryString(data.get(upper).getYValue())).charAt(actualDigit) == '1') --upper;
			if (lower <= upper) {
				BarChartController.setColor(data.get(lower).getNode(), "swap");
				BarChartController.setColor(data.get(upper).getNode(), "swap");
				swap(lower, upper);
			}
			else step();
			}
			else {
				if(begin!=lower-1) {
				recursiveCall.add(new RecursiveParameter(begin, lower-1, actualDigit+1, null));
				setBucketColor(begin ,lower);
				}
				if(lower!=end) {
				recursiveCall.add(new RecursiveParameter(lower, end, actualDigit+1, null));
				setBucketColor(lower, end+1);
				}
				RecursiveParameter nextParameters = recursiveCall.remove();
				begin=nextParameters.getBegin();
				end=nextParameters.getEnd();
				actualDigit=nextParameters.getDigit();
				lower=begin;
				upper=end;
			}
		}
	}
	
	private static void setBucketColor(int lower, int upper) {
		String bucketColor = BarChartController.getRandomColor();
		for (int i = lower; i < upper; i++) {
			BarChartController.setColor(data.get(i).getNode(), bucketColor);
		}

	}

	@Override
	public void setDefaults() {
		// TODO Auto-generated method stub
		
	}
}
