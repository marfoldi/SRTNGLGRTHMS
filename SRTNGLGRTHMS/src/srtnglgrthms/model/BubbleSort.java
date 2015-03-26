package srtnglgrthms.model;

import srtnglgrthms.controller.OverviewChartController;

public class BubbleSort extends SortingAlgorithm {
	private static int outerIndex = 0;
	private static int innerIndex = 1;

	private BubbleSort() {
	}

	private static class SortHolder {
		private static final BubbleSort INSTANCE = new BubbleSort();
	}

	public static BubbleSort getInstance() {
		return SortHolder.INSTANCE;
	}
	
	public void setDefaults() {
		outerIndex = 0;
		innerIndex = 1;
		counterData.clear();
		counterData.add(new CounterData("ÖH", 0));
		counterData.add(new CounterData("CS", 0));
	}

	@Override
	public void step() {
		if (innerIndex < data.size() - outerIndex) {
			if (outerIndex > 0) {
				OverviewChartController.setColor(data.get(data.size()-outerIndex-1).getNode(), "default");
			}
			OverviewChartController.setColor(data.get(innerIndex-1).getNode(), "swap");
			OverviewChartController.setColor(data.get(innerIndex).getNode(), "swap");
			counterData.get(0).incValue();
			if (data.get(innerIndex - 1).getYValue() > data.get(innerIndex).getYValue()) {
				swap(innerIndex - 1, innerIndex);
				counterData.get(1).incValue();
			}
			innerIndex++;
			if (innerIndex == data.size() - outerIndex) {
				OverviewChartController.setColor(data.get(innerIndex-1).getNode(), "done");
				setRestColor();
				innerIndex = 1;
				outerIndex++;
			}
			setRestColor();
		}
		else {
			OverviewChartController.setColor(data.get(0).getNode(), "done");
			OverviewChartController.getAnimation().stop();
		}
	}

	private void setRestColor() {
		for (int i = 0; i < innerIndex - 2; i++) {
			OverviewChartController.setColor(data.get(i).getNode(), "default");
		}
	}
	
	public static Runnable sort = () -> {
		int[] numbers = new int[SortingAlgorithm.getNumbers().length];
		System.arraycopy( SortingAlgorithm.getNumbers(), 0, numbers, 0, SortingAlgorithm.getNumbers().length);
	    int swapCounter = 0;//Increment this counter whenever a swap takes place
	    int comparsionCounter=0;//Increment this counter whenever a comparison takes place
	    for(int i=1;i<numbers.length;i++)
	    {
	      for(int j=0;j<numbers.length-i;j++)
	      {
	    	  comparsionCounter++;
	          if((numbers[j])>(numbers[j+1]))
	          {
	        	  swapCounter++;
	              int temp=numbers[j];
	              numbers[j]=numbers[j+1];
	              numbers[j+1]=temp;
	            }
	       }
	    }
	    benchmarkData.add(new BenchmarkData("Buborékrendezés", comparsionCounter, swapCounter));
	};
}
