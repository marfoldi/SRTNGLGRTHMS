package srtnglgrthms.model;

import srtnglgrthms.controller.OverviewChartController;


public class ShellSort extends SortingAlgorithm{
	private static final int[] gapArray = {1750,701,301,132,57,23,10,4,1};
	private static int gapIdx = selectGap();
	private static int outerIndex = gapArray[gapIdx] - 1;
	private static int innerIndex = outerIndex - gapArray[gapIdx];
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
		outerIndex = gapArray[gapIdx] - 1;
		innerIndex = outerIndex - gapArray[gapIdx];
		isSelected = false;
		counterData.clear();
		counterData.add(new CounterData("Összehasonlítások", "0"));
		counterData.add(new CounterData("Mozgatások", "0"));
		counterData.add(new CounterData("Lépésköz", "0"));
	}
	
	private static int selectGap() {
		for(int i=0; i<gapArray.length; ++i) {
			if(data.size()>gapArray[i]) return i;
		}
		return 0;
	}

    @Override
	public void step() {
    	if(!isSelected) {
    		setRestColor("default");
    		if(outerIndex<data.size()-1) {
				outerIndex++;
        		OverviewChartController.setColor(data.get(outerIndex).getNode(), "select");
    			innerIndex = outerIndex - gapArray[gapIdx];
			}
			else if(gapIdx<gapArray.length-1) {
				gapIdx++;
				outerIndex = gapArray[gapIdx];
				innerIndex = outerIndex - gapArray[gapIdx];
			}
			else {
				setRestColor("done");
			}
    		counterData.get(2).setValue(Integer.toString(gapArray[gapIdx]));
    		isSelected=true;
    	}
    	else if(outerIndex<data.size()){
   			setRestColor("default");
			if (data.get(innerIndex+gapArray[gapIdx]).getYValue() >= data.get(innerIndex).getYValue()) {
    			OverviewChartController.setColor(data.get(innerIndex).getNode(), "swap");
    			OverviewChartController.setColor(data.get(innerIndex+gapArray[gapIdx]).getNode(), "select");
    			isSelected=false;
    		}
    		else if(data.get(innerIndex+gapArray[gapIdx]).getYValue() < data.get(innerIndex).getYValue()) {
    			swap(innerIndex+gapArray[gapIdx], innerIndex);
    			counterData.get(1).incValue();
    			OverviewChartController.setColor(data.get(innerIndex).getNode(), "swap");
    			OverviewChartController.setColor(data.get(innerIndex+gapArray[gapIdx]).getNode(), "select");
    			if (innerIndex-gapArray[gapIdx]>=0) innerIndex = innerIndex-gapArray[gapIdx];
    			else isSelected=false;
    		}
    		counterData.get(0).incValue();
    	}
    	else isSelected = false;
    }
	
	private void setRestColor(String color) {
		for (int i = 0; i < data.size(); i++) {
			OverviewChartController.setColor(data.get(i).getNode(), color);
		}
	}
	
	public static Runnable sort = () -> {
		int[] numbers = new int[SortingAlgorithm.getNumbers().length];
		int i,j, temp;
		System.arraycopy( SortingAlgorithm.getNumbers(), 0, numbers, 0, SortingAlgorithm.getNumbers().length);
	    int swapCounter = 0; //Increment this counter whenever a swap takes place
	    int moveCounter=0; //Increment this counter whenever a movement takes place
	    for ( int gap : gapArray ) {
	        i = gap;
	        while ( i < numbers.length ) {
	            temp = numbers[i];
	            j = i-gap;
	            while ( j >= 0 ) {
	            	moveCounter++;
	            	if( numbers[j] > temp ) {
	            		swapCounter++;
	            		numbers[j + gap] = numbers[j];
	            	}
	            	else break;
            		j -= gap;
	            }
	            numbers[j + gap] = temp;
	            i++;
	        }
	    }
	    benchmarkData.add(new BenchmarkData("Shell rendezés", moveCounter, swapCounter));
	};
}
