package srtnglgrthms.model;

import srtnglgrthms.controller.OverviewChartController;

public class BubbleSort extends SortingAlgorithm {
	private static int outerIdx = 0;
	private static int innerIdx = 1;

	private BubbleSort() {
	}

	private static class SortHolder {
		private static final BubbleSort INSTANCE = new BubbleSort();
	}

	public static BubbleSort getInstance() {
		return SortHolder.INSTANCE;
	}
	
	public void setDefaults() {
		outerIdx = 0;
		innerIdx = 1;
		counterData.clear();
		counterData.add(new CounterData("Összehasonlítások", 0));
		counterData.add(new CounterData("Cserék", 0));
	}

	@Override
	public void step() {
		if (innerIdx < data.size() - outerIdx) {
			if (outerIdx > 0) {
				OverviewChartController.setColor(data.get(data.size()-outerIdx-1).getNode(), "default");
			}
			OverviewChartController.setColor(data.get(innerIdx-1).getNode(), "swap");
			OverviewChartController.setColor(data.get(innerIdx).getNode(), "swap");
			counterData.get(0).incValue();
			if (data.get(innerIdx - 1).getYValue() > data.get(innerIdx).getYValue()) {
				swap(innerIdx - 1, innerIdx);
				counterData.get(1).incValue();
			}
			innerIdx++;
			if (innerIdx == data.size() - outerIdx) {
				OverviewChartController.setColor(data.get(innerIdx-1).getNode(), "done");
				setRestColor();
				innerIdx = 1;
				outerIdx++;
			}
			setRestColor();
		}
		else {
			OverviewChartController.setColor(data.get(0).getNode(), "done");
			OverviewChartController.getAnimation().stop();
		}
	}

	private void setRestColor() {
		for (int i = 0; i < innerIdx - 2; i++) {
			OverviewChartController.setColor(data.get(i).getNode(), "default");
		}
	}
	
	public static Runnable sort = () -> {
		int[] numbers = new int[SortingAlgorithm.getNumbers().length];
		System.arraycopy( SortingAlgorithm.getNumbers(), 0, numbers, 0, SortingAlgorithm.getNumbers().length);
	    int swapCounter = 0; //Increment this counter whenever a swap takes place
	    int comparsionCounter=0; //Increment this counter whenever a comparison takes place
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
