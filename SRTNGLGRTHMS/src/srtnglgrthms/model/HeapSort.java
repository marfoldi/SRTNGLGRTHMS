package srtnglgrthms.model;

import srtnglgrthms.controller.OverviewChartController;

public class HeapSort extends SortingAlgorithm {
	private static int bound = data.size()-1;
	private static int root = (bound-1)/2;
	private static int outerIndex = root;
	private static int innerIndex = root;
	private static boolean swapped = false;
	private static int left, right, middle;
	
	private HeapSort() {}
	
	private static class SortHolder {
        private static final HeapSort INSTANCE = new HeapSort();
    }
	
    public static HeapSort getInstance() {
        return SortHolder.INSTANCE;
    }
	
	@Override
	public void setDefaults() {
		bound = data.size()-1;
		root = (bound-1)/2;
		outerIndex = root;
		innerIndex = root;
		swapped = false;
		counterData.clear();
		counterData.add(new CounterData("Összehasonlítások", "0"));
		counterData.add(new CounterData("Mozgatások", "0"));
	}
	
	@Override
	public void step() {
			if(bound>0) {
				setRestColor();
				if (outerIndex>=0) {
					if (innerIndex>=0) {
						left = (2*innerIndex) + 1;
						right = (2*innerIndex) + 2;
						if((left <= bound) && (right <= bound)) {
							if(data.get(right).getYValue() >= data.get(left).getYValue())
								middle = right;
							else
								middle = left;
						}
						else {
							if(right > bound)
								middle = left;
							else
								middle = right;
						}
	        			OverviewChartController.setColor(data.get(innerIndex).getNode(), "swap");
	        			OverviewChartController.setColor(data.get(middle).getNode(), "swap");
	        			counterData.get(0).incValue();
						if(data.get(innerIndex).getYValue() < data.get(middle).getYValue()) {
							OverviewChartController.setColor(data.get(innerIndex).getNode(), "select");
							counterData.get(1).incValue();
							swap(innerIndex, middle);
							swapped = true;
						}
							if(innerIndex>0) {
								innerIndex--;
								return;
							}
							else if(swapped) {
								innerIndex = root;
								swapped = false;
							}
					}
					outerIndex--;
				}
				else {
					counterData.get(1).incValue();
					swap(0,bound);
        			OverviewChartController.setColor(data.get(0).getNode(), "swap");
        			OverviewChartController.setColor(data.get(bound).getNode(), "done");
					bound--;			
					root = (bound-1)/2;
					outerIndex = root;
					innerIndex = root;
				}
			}
			else {
				OverviewChartController.setColor(data.get(0).getNode(), "done");
			}
	}
	
	private void setRestColor() {
		for (int i = 0; i < bound+1; i++) {
			OverviewChartController.setColor(data.get(i).getNode(), "default");
		}
	}
	
	public static Runnable sort = () -> {
		int[] numbers = new int[SortingAlgorithm.getNumbers().length];
	    int left, right, middle, temp;
		int i = root;
		int j = root;
		int bound = data.size()-1;
		int root = (bound-1) / 2;
		System.arraycopy( SortingAlgorithm.getNumbers(), 0, numbers, 0, SortingAlgorithm.getNumbers().length);
	    int swapCounter = 0; //Increment this counter whenever a swap takes place
	    int moveCounter=0; //Increment this counter whenever a movement takes place
		while(bound>0) {
			while(i>=0 && j>=0) {
					left = (2*j) + 1;
					right = (2*j) + 2;
					if((left <= bound) && (right <= bound)) {
						if(numbers[right] >= numbers[left])
							middle = right;
						else
							middle = left;
					}
					else {
						if(right > bound)
							middle = left;
						else
							middle = right;
					}
					moveCounter++;
					if(numbers[j] < numbers[middle]) {
						swapCounter++;
						temp = numbers[j];
						numbers[j] = numbers[middle];
						numbers[middle] = temp;
					}
					i--;
					j--;
			}
			swapCounter++;
			temp = numbers[0];
			numbers[0] = numbers[bound];
			numbers[bound] = temp;
			bound--;
			root=(bound-1) / 2;
			i = root;
			j = root;
		}
	    benchmarkData.add(new BenchmarkData("Kupac rendezés", moveCounter, swapCounter));
	};
}