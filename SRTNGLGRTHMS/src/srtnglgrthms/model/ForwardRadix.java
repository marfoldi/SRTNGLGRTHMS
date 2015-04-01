package srtnglgrthms.model;

import java.util.LinkedList;

import srtnglgrthms.controller.OverviewChartController;

public class ForwardRadix extends Radix {
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
					OverviewChartController.setColor(data.get(lower).getNode(), "swap");
					OverviewChartController.setColor(data.get(upper).getNode(), "swap");
					swap(lower, upper);
				}
				else step();
			}
			else {
				setBucketColor(begin ,lower);
				setBucketColor(lower, end+1);
				if(begin!=lower-1) {
				recursiveCall.add(new RecursiveParameter(begin, lower-1, actualDigit+1, null));
				}
				if(lower!=end) {
				recursiveCall.add(new RecursiveParameter(lower, end, actualDigit+1, null));
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
		String bucketColor = OverviewChartController.getRandomColor();
		for (int i = lower; i < upper; i++) {
			OverviewChartController.setColor(data.get(i).getNode(), bucketColor);
		}

	}
}
