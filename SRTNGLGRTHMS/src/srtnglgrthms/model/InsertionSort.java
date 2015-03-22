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
		System.out.println(outerIndex);
		System.out.println(innerIndex);
		if(firstStep){
			setColor(outerIndex+1, "red");
			firstStep = false;
			return;
		}
		if (data.get(outerIndex).getYValue() <= data.get(outerIndex+1).getYValue()) {
			setRestColor();
			innerIndex++;
			outerIndex=innerIndex-1;
			setColor(outerIndex+1, "red");
			//setColor(outerIndex, "navy");
		}
		else if (data.get(outerIndex).getYValue() > data.get(outerIndex+1).getYValue()) {
			swap(outerIndex+1, outerIndex);
			setColor(outerIndex+1, "navy");
			setColor(outerIndex, "red");
			if (outerIndex>0) outerIndex--;
		}
	}
	
	private void setRestColor() {
		for (int i = 0; i < data.size(); i++) {
			setColor(i, "orange");
		}
	}
}
