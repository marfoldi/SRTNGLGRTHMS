package srtnglgrthms.model;

import java.util.LinkedList;

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
				setColor(lower, "navy");
				setColor(upper, "navy");
				swap(lower, upper);
			}
			else step();
			}
			else {
				recursiveCall.add(new RecursiveParameter(begin, lower-1, actualDigit+1, null));
				recursiveCall.add(new RecursiveParameter(lower, end, actualDigit+1, null));
				setBucketColor();
				RecursiveParameter nextParameters = recursiveCall.remove();
				begin=nextParameters.getBegin();
				end=nextParameters.getEnd();
				actualDigit=nextParameters.getDigit();
				lower=begin;
				upper=end;
				step();
			}
		}
	}
	
	private static void setBucketColor() {
		for (int i = begin; i < lower; i++) {
			setColor(i, "green");
		}
		for (int i = lower; i < end; i++) {
			setColor(i, "black");
		}
	}
}
