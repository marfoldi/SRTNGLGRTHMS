package srtnglgrthms.model;

import java.util.LinkedList;

import srtnglgrthms.controller.OverviewChartController;

public class QuickSort extends SortingAlgorithm {
	private static int begin;
	private static int end;
	private static int lower;
	private static int upper;
	private static int pivot;
	private static boolean pivotSwapped = false;
	
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
    
    public void setDefaults() {
    	begin = 0;
    	end = SortingAlgorithm.getNumbers().length-1;
    	pivot = data.get(begin+(end-begin)/2).getYValue();
    	lower = begin;
    	upper = end;
		counterData.clear();
		counterData.add(new CounterData("ÖH", 0));
		counterData.add(new CounterData("CS", 0));
		counterData.add(new CounterData("Vezérelem indexe", begin+(end-begin)/2));
	}
	
	public void step() {
		OverviewChartController.setColor(data.get(begin+(end-begin)/2).getNode(), "select");
		setRestColor();
        if (lower <= upper) {
            while (data.get(lower).getYValue() < pivot) {
                lower++;
            }
            while (data.get(upper).getYValue() > pivot) {
                upper--;
            }
            counterData.get(0).incValue();
            if (lower <= upper ) {
            	OverviewChartController.setColor(data.get(lower).getNode(), "swap");
            	OverviewChartController.setColor(data.get(upper).getNode(), "swap");
                if(lower==begin+(end-begin)/2 && !pivotSwapped) {
                	OverviewChartController.setColor(data.get(upper).getNode(), "select");
                	counterData.get(2).setValue(upper);
                	pivotSwapped=true;
                }
                else if(upper==begin+(end-begin)/2 && !pivotSwapped){
                	OverviewChartController.setColor(data.get(lower).getNode(), "select");
                	counterData.get(2).setValue(lower);
                	pivotSwapped=true;
                }
                else pivotSwapped=false;
                counterData.get(1).incValue();
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
    			counterData.get(2).setValue(begin+(end-begin)/2);
    			OverviewChartController.setColor(data.get(begin+(end-begin)/2).getNode(), "select");
    			setRestColor();
            }
            else {
            	for (int i = 0; i < data.size(); i++) {
            		OverviewChartController.setColor(data.get(i).getNode(), "done");
            		OverviewChartController.getAnimation().stop();
        		}
            }
        }
	}
	private void setRestColor() {
		for (int i = 0; i < data.size(); i++) {
			if(i!=begin+(end-begin)/2) OverviewChartController.setColor(data.get(i).getNode(), "default");
		}
	}
}
