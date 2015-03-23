package srtnglgrthms.model;


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

	public void step() {
		if(firstStep){
			setColor(outerIndex+1, "green");
			firstStep = false;
			return;
		}
		if (data.get(outerIndex).getYValue() <= data.get(outerIndex+1).getYValue()) {
			setRestColor("default");
			if(innerIndex<data.size()-1) innerIndex++;
			else {
				setRestColor("red");
				return;
			}
			outerIndex=innerIndex-1;
			setColor(outerIndex+1, "green");
		}
		else if (data.get(outerIndex).getYValue() > data.get(outerIndex+1).getYValue()) {
			swap(outerIndex+1, outerIndex);
			setColor(outerIndex+1, "navy");
			setColor(outerIndex, "green");
			if (outerIndex>0) outerIndex--;
		}
	}
	
	private void setRestColor(String color) {
		for (int i = 0; i < data.size(); i++) {
			setColor(i, color);
		}
	}
}
