package srtnglgrthms.model;

import srtnglgrthms.controller.OverviewChartController;


public class ShellSort extends SortingAlgorithm{
	private static final int[] gapArray = {1750,701,301,132,57,23,10,4,1};
	private static int gapIdx = selectGap();
	private static int outerIndex = gapArray[gapIdx];
	private static int innerIndex = 0;
	private static boolean isSelected = false;
	
	private ShellSort() {}
	
	private static class SortHolder {
        private static final ShellSort INSTANCE = new ShellSort();
    }
	
    public static ShellSort getInstance() {
        return SortHolder.INSTANCE;
    }
    
	public void setDefaults() {
		gapIdx = selectGap();
		outerIndex = gapArray[gapIdx];
		innerIndex = 0;
		isSelected=false;
		counterData.clear();
		counterData.add(new CounterData("Összehasonlítások", 0));
		counterData.add(new CounterData("Mozgatások", 0));
	}
	
	private static int selectGap() {
		for(int i=0; i<gapArray.length-1; ++i) {
			if(data.size()>gapArray[i]) return i;
		}
		return 0;
	}

    @Override
	public void step() {
    	if(!isSelected) {
    		OverviewChartController.setColor(data.get(outerIndex).getNode(), "select");
    		isSelected=true;
    	}
    	else if(outerIndex<data.size()) {
			if (data.get(innerIndex+gapArray[gapIdx]).getYValue() >= data.get(innerIndex).getYValue()) {	
    			OverviewChartController.setColor(data.get(innerIndex).getNode(), "swap");
    			OverviewChartController.setColor(data.get(innerIndex+gapArray[gapIdx]).getNode(), "select");
    			isSelected=false;
    			if(outerIndex<data.size()-1) {
    				outerIndex++;
        			innerIndex++;
        			System.out.println("NÖVELTEM");
    			}
    			else if(gapIdx<gapArray.length-1) {
    					System.out.println("GAPCSERE, MINDEN0");
    					gapIdx++;
    					outerIndex = gapArray[gapIdx];
    					innerIndex = 0;
    					setRestColor();
    			}
    		}
    		else if(data.get(innerIndex+gapArray[gapIdx]).getYValue() < data.get(innerIndex).getYValue()) {
    			System.out.println("SWAP");
    			swap(innerIndex+gapArray[gapIdx], innerIndex);
    			counterData.get(1).incValue();
    			OverviewChartController.setColor(data.get(innerIndex).getNode(), "swap");
    			OverviewChartController.setColor(data.get(innerIndex+gapArray[gapIdx]).getNode(), "select");
    			if (innerIndex-gapArray[gapIdx]>=0 && data.get(innerIndex-gapArray[gapIdx]).getYValue() > data.get(innerIndex).getYValue())
    				innerIndex=innerIndex-gapArray[gapIdx];
    			else innerIndex = outerIndex-gapArray[gapIdx];
    		}
    		counterData.get(0).incValue();
    	}
    }
	
	private void setRestColor() {
		for (int i = 0; i < data.size(); i++) {
			OverviewChartController.setColor(data.get(i).getNode(), "default");
		}
	}
	
	public static Runnable sort = () -> {
		int[] numbers = new int[SortingAlgorithm.getNumbers().length];
		System.arraycopy( SortingAlgorithm.getNumbers(), 0, numbers, 0, SortingAlgorithm.getNumbers().length);
	    int swapCounter = 0; //Increment this counter whenever a swap takes place
	    int moveCounter=0; //Increment this counter whenever a movement takes place
	    for (int i=0; i < numbers.length-1; i++)
	    {
	       int temp = numbers[i+1];
	       int j;
	       for(j=i; j>=0; --j) {
	    	   moveCounter++;
	    	   if(temp<numbers[j]) {
	    		   swapCounter++;
	    		   numbers[j+1] = numbers[j];
	    	   }
	    	   else {
	    		   break;
	    	   }
	       }
	       numbers[j+1] = temp;
	    }
	    benchmarkData.add(new BenchmarkData("Shell rendezés", moveCounter, swapCounter));
	};
}
