package srtnglgrthms.model;

import java.util.LinkedList;

public class QuickSort extends SortingAlgorithm {
	private static int begin;
	private static int end;
	private static int lower;
	private static int upper;
	private static int pivot;
	
	private QuickSort() {
		init();
	}
	
	private static class SortHolder {
        private static final QuickSort INSTANCE = new QuickSort();
    }
	
    public static QuickSort getInstance() {
        return SortHolder.INSTANCE;
    }
    
    private static void init() {
    	begin = 0;
    	end = SortingAlgorithm.getNumbers().length-1;
    	pivot = data.get(begin+(end-begin)/2).getYValue();
    	lower = begin;
    	upper = end;
    	recursiveCall = new LinkedList<>();
    }
	
	public void step() {
		setColor(begin+(end-begin)/2, "red");
		setRestColor();
        if (lower <= upper) {
            while (data.get(lower).getYValue() < pivot) {
                lower++;
            }
            while (data.get(upper).getYValue() > pivot) {
                upper--;
            }
            if (lower <= upper) {
                setColor(lower, "navy");
                setColor(upper, "navy");
                swap(lower,upper);
                lower++;
                upper--;
            }
        	}
        else {
        	if(begin < upper) {
        		recursiveCall.add(new RecursiveParameter(begin, upper));
        	}
        	if(lower < end)  {
        		recursiveCall.add(new RecursiveParameter(lower, end));
        }
            if(!recursiveCall.isEmpty()) {
            	RecursiveParameter nextParameters = recursiveCall.remove();
            	begin=nextParameters.getBegin();
    			end=nextParameters.getEnd();
    			lower=begin;
    			upper=end;
    			pivot = data.get(begin+(end-begin)/2).getYValue();
            }
        }
	}
	private void setRestColor() {
		for (int i = 0; i < SortingAlgorithm.getNumbers().length-1; i++) {
			if(i!=begin+(end-begin)/2) setColor(i, "orange");
		}
	}
}
