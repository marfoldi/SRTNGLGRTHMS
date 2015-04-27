package srtnglgrthms.model.algorithm;

import java.util.LinkedList;

import srtnglgrthms.controller.OverviewChartController;
import srtnglgrthms.model.CounterData;
import srtnglgrthms.model.RecursiveParameter;

public class QuickSort extends ChartAlgorithm {
	private static int begin;
	private static int end;
	private static int lower;
	private static int upper;
	private static int pivot;
	private static boolean pivotSwapped = false;

	private QuickSort() {}

	private static class SortHolder {
        private static final QuickSort INSTANCE = new QuickSort();
    }

    public static QuickSort getInstance() {
        return SortHolder.INSTANCE;
    }

    public void setDefaults() {
    	begin = 0;
    	end = numbers.length-1;
    	pivot = data.get(begin+(end-begin)/2).getYValue().intValue();
    	pivotSwapped = false;
    	lower = begin;
    	upper = end;
    	recursiveCall = new LinkedList<>();
		counterData.clear();
		counterData.add(new CounterData("Összehasonlítások", "0"));
		counterData.add(new CounterData("Mozgatások", "0"));
		counterData.add(new CounterData("Vezérelem", "t["+ Integer.toString(begin+(end-begin)/2)+ "]"));
	}

	public void step() {
		OverviewChartController.setColor(data.get(begin+(end-begin)/2).getNode(), "select");
		setRestColor();
        if (lower <= upper && !pivotSwapped) {
            if(lower!=upper) counterData.get(0).incValue();
            if (data.get(lower).getYValue().intValue() < pivot) {
                lower++;
                OverviewChartController.setColor(data.get(lower).getNode(), "swap");
                return;
            }
            if (data.get(upper).getYValue().intValue() > pivot) {
                upper--;
                OverviewChartController.setColor(data.get(upper).getNode(), "swap");
                return;
            }
            if (lower <= upper ) {
            	if(data.get(lower).getYValue()!=data.get(upper).getYValue()) {
            	OverviewChartController.setColor(data.get(lower).getNode(), "swap");
            	OverviewChartController.setColor(data.get(upper).getNode(), "swap");
                if(lower==begin+(end-begin)/2 && !pivotSwapped) {
                	OverviewChartController.setColor(data.get(upper).getNode(), "select");
                	counterData.get(2).setValue("t["+ Integer.toString(upper)+ "]");
                	pivotSwapped=true;
                }
                else if(upper==begin+(end-begin)/2 && !pivotSwapped){
                	OverviewChartController.setColor(data.get(lower).getNode(), "select");
                	counterData.get(2).setValue("t["+ Integer.toString(lower)+ "]");
                	pivotSwapped=true;
                }
                else pivotSwapped=false;
                if(lower!=upper) {
                	counterData.get(1).incValue();
                	swap(lower,upper);
                    lower++;
                    upper--;
                }
            	}
            	else {
                    lower++;
                    upper--;
            		step();
            	}
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
            	begin=(int) nextParameters.getFirstParameter();
    			end=(int) nextParameters.getSecondParameter();
    			lower=begin;
    			upper=end;
    			pivot = data.get(begin+(end-begin)/2).getYValue().intValue();
    			pivotSwapped = false;
    			counterData.get(2).setValue("t["+ Integer.toString(begin+(end-begin)/2)+ "]");
    			OverviewChartController.setColor(data.get(begin+(end-begin)/2).getNode(), "select");
    			setRestColor();
            }
            else {
            	for (int i = 0; i < data.size(); i++) {
            		OverviewChartController.setColor(data.get(i).getNode(), "done");
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