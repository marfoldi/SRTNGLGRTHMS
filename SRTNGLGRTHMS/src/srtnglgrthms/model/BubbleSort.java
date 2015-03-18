package srtnglgrthms.model;

public class BubbleSort extends SortingAlgorithm {
	private static int j = 1;
	private static int i = 0;
	
	private BubbleSort() {}
	
	private static class SortHolder {
        private static final BubbleSort INSTANCE = new BubbleSort();
    }
	
    public static BubbleSort getInstance() {
        return SortHolder.INSTANCE;
    }
	
	public void step() {
		if(i>0) {
			setColor(data.size()-i-1, "orange");
		}
		setColor(j-1, "navy");
		setColor(j, "navy");
		if (data.get(j - 1).getYValue() > data.get(j).getYValue()) {
			swap(j-1, j);
		}
		j++;
		if (j == data.size()-i) {
			setColor(j-1, "red");
			setRestColor();
			j = 1;
			i++;
		}
		setRestColor();
	}
	
	private void setRestColor() {
		for (int i = 0; i < j-2; i++) {
			setColor(i, "orange");
		}
	}
}
