package srtnglgrthms.model;

import srtnglgrthms.controller.OverviewChartController;


public class InsertionSort extends SortingAlgorithm{
	private static int innerIndex = 1;
	private static int outerIndex = innerIndex-1;
	private static boolean firstStep = true;
	
	private InsertionSort() {}
	
	private static class SortHolder {
        private static final InsertionSort INSTANCE = new InsertionSort();
    }
	
    public static InsertionSort getInstance() {
        return SortHolder.INSTANCE;
    }
    
	public void setDefaults() {
		outerIndex = 0;
		innerIndex = 1;
		firstStep=true;
		counterData.clear();
		counterData.add(new CounterData("ÖH", 0));
		counterData.add(new CounterData("CS", 0));
	}

    @Override
	public void step() {
		if(firstStep){
			OverviewChartController.setColor(data.get(outerIndex+1).getNode(), "select");
			firstStep = false;
			return;
		}
		if (data.get(outerIndex).getYValue() <= data.get(outerIndex+1).getYValue()) {
			setRestColor("default");
			if(innerIndex<data.size()-1) innerIndex++;
			else {
				setRestColor("done");
				OverviewChartController.getAnimation().stop();
				return;
			}
			outerIndex=innerIndex-1;
			OverviewChartController.setColor(data.get(outerIndex+1).getNode(), "select");
			if (data.get(innerIndex-1).getYValue() < data.get(innerIndex).getYValue()) {
				counterData.get(0).incValue();
			}
		}
		else if (data.get(outerIndex).getYValue() > data.get(outerIndex+1).getYValue()) {
			counterData.get(0).incValue();
			swap(outerIndex+1, outerIndex);
			counterData.get(1).incValue();
			OverviewChartController.setColor(data.get(outerIndex+1).getNode(), "swap");
			OverviewChartController.setColor(data.get(outerIndex).getNode(), "select");
			if (outerIndex>0) outerIndex--;
		}
	}
	
	private void setRestColor(String color) {
		for (int i = 0; i < data.size(); i++) {
			OverviewChartController.setColor(data.get(i).getNode(), color);
		}
	}
	
	public static Runnable sort = () -> {
		int[] numbers = new int[SortingAlgorithm.getNumbers().length];
		System.arraycopy( SortingAlgorithm.getNumbers(), 0, numbers, 0, SortingAlgorithm.getNumbers().length);
	    int swapCounter = 0;//Increment this counter whenever a swap takes place
	    int comparsionCounter=0;//Increment this counter whenever a comparison takes place
	    for (int i=1; i < numbers.length; i++)
	    {
	       int index = numbers[i];
	       int j=i-1;
	       while (j >= 0)
	       {
	          // Every time this line is reached, a comparison will be performed
	    	   comparsionCounter++;
	          if (numbers[j] > index)
	          {
	        	 swapCounter++;
	             numbers[j + 1] = numbers[j];
	          }
	          j--;
	       }
	       numbers[j+1] = index;
	    }
	    benchmarkData.add(new BenchmarkData("Beszúrórendezés", comparsionCounter, swapCounter));
	};
}
