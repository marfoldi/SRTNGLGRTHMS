package srtnglgrthms.model;

public class InsertionSort extends SortingAlgorithm{
	private static int j = 1;
	private static int i = j-1;
	
	private InsertionSort() {}
	
	private static class SortHolder {
        private static final InsertionSort INSTANCE = new InsertionSort();
    }
	
    public static InsertionSort getInstance() {
        return SortHolder.INSTANCE;
    }

	public void step() {
		while ((i < 0) || ( data.get(i).getYValue() <= data.get(i+1).getYValue() )) {
			j++;
			i=j-1;
			for (int i = 0; i < SortingAlgorithm.getNumbers().length-1; i++) {
				setColor(i, "orange");
			}
		}
		if ((i >= 0) && ( data.get(i).getYValue() > data.get(i+1).getYValue() )) {
			swap(i+1, i);
			setColor(i+1, "navy");
			setColor(i, "red");
			i--;
		}
	}
}
