package srtnglgrthms.model.algorithm;

import srtnglgrthms.controller.OverviewChartController;
import srtnglgrthms.model.BenchmarkData;
import srtnglgrthms.model.CounterData;


public class InsertionSort extends ChartAlgorithm{
	private static int outerIndex = 1;
	private static int innerIndex = outerIndex;
	private static boolean isSelected = false;
	private static boolean showSwapped = true;

	private InsertionSort() {}

	private static class SortHolder {
        private static final InsertionSort INSTANCE = new InsertionSort();
    }

    public static InsertionSort getInstance() {
        return SortHolder.INSTANCE;
    }

	public void setDefaults() {
		outerIndex = 1;
		innerIndex = outerIndex;
		isSelected=false;
		showSwapped = true;
		counterData.clear();
		counterData.add(new CounterData("Összehasonlítások", "0"));
		counterData.add(new CounterData("Mozgatások", "0"));
	}

    @Override
	public void step() {
    	if(!isSelected && outerIndex<data.size()) {
    		OverviewChartController.setColor(data.get(outerIndex).getNode(), "select");
    		isSelected=true;
			innerIndex=outerIndex;
			if(outerIndex<data.size()) outerIndex++;
			else {
				setRestColor("done");
				return;
			}
    	}
    	else if(innerIndex>0) {
    		if (data.get(innerIndex-1).getYValue().intValue() <= data.get(innerIndex).getYValue().intValue()) {
    			if(showSwapped) {
        			OverviewChartController.setColor(data.get(innerIndex).getNode(), "select");
        			OverviewChartController.setColor(data.get(innerIndex-1).getNode(), "swap");
    				showSwapped = false;
    				return;
    			}
    			else setRestColor("done");
    			if(outerIndex==data.size()) {
    				counterData.get(0).incValue();
    				setRestColor("done");
    				return;
    			}
    			isSelected=false;
    			showSwapped = true;
    		}
    		else if(data.get(innerIndex-1).getYValue().intValue() > data.get(innerIndex).getYValue().intValue()) {
    			swap(innerIndex-1, innerIndex);
    			counterData.get(1).incValue();
    			OverviewChartController.setColor(data.get(innerIndex).getNode(), "swap");
    			OverviewChartController.setColor(data.get(innerIndex-1).getNode(), "select");
    			if (innerIndex>0) innerIndex--;
    		}
    		counterData.get(0).incValue();
    	}
    	else {
    		setRestColor("done");
    		isSelected = false;
    	}
    }

	private void setRestColor(String color) {
		for (int i = 0; i < outerIndex; i++) {
			OverviewChartController.setColor(data.get(i).getNode(), color);
		}
	}

	public static Runnable sort = () -> {
		int[] numbers = new int[SortingAlgorithm.getNumbers().length];
		System.arraycopy(SortingAlgorithm.getNumbers(), 0, numbers, 0, SortingAlgorithm.getNumbers().length);
	    int moveCounter = 0; //Increment this counter whenever a move takes place
	    int comparisonCounter=0; //Increment this counter whenever a comparison takes place
	    for (int i=0; i<numbers.length-1; i++)
	    {
	       int temp = numbers[i+1];
	       int j;
	       for(j=i; j>=0; --j) {
	    	   comparisonCounter++;
	    	   if(temp<numbers[j]) {
	    		   moveCounter++;
	    		   numbers[j+1] = numbers[j];
	    	   }
	    	   else {
	    		   break;
	    	   }
	       }
	       numbers[j+1] = temp;
	    }
	    benchmarkData.add(new BenchmarkData("Beszúró rendezés", comparisonCounter, moveCounter, 0));
	};
}
